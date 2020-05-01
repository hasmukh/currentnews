package com.horizonx.currentnews.service;

import static com.horizonx.currentnews.constant.CurrentsNewsCoreConstants.LANGUAGE_EN;
import static com.horizonx.currentnews.constant.CurrentsNewsCoreConstants.LOG_MSG_WRT_CSV_FINISH;
import static com.horizonx.currentnews.constant.CurrentsNewsCoreConstants.LOG_MSG_WRT_CSV_START;
import static com.horizonx.currentnews.constant.CurrentsNewsCoreConstants.NEW_LINE;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.horizonx.currentnews.client.CurrentsClient;
import com.horizonx.currentnews.dto.NewsDTO;
import com.horizonx.currentnews.dto.ResponseDTO;
import com.horizonx.currentnews.enums.Header;
import com.horizonx.currentnews.utils.CurrentsNewsUtil;

/**
 * 
 * @author Hasmukh Maniya
 *
 */
@Service
public class NewsServiceImpl implements NewsService {

	private static final Logger LOGGER = LoggerFactory.getLogger(NewsServiceImpl.class);

	@Value("${currents.latestnews.file.path}")
	private String latestNewsFilePath;

	@Autowired
	private CurrentsClient currentsClient;

	@Scheduled(cron = "${newsfeed.scheduler.exp}")
	@Override
	public void feedLatestNews() {
		try {
			ResponseDTO latestNewsResponse = currentsClient.getLatestNewsByLanguage(LANGUAGE_EN);
			if (!StringUtils.isEmpty(latestNewsResponse) && !CollectionUtils.isEmpty(latestNewsResponse.getNews())) {
				feedNewsToCSV(latestNewsResponse.getNews(), latestNewsFilePath);
			}
		} catch (Exception e) {
			LOGGER.error("feedLatestNews() :: Exception occurred while feeding news", e);
		}

	}

	@Override
	public Map<String, Long> getTotalNewsPerCategory() {
		Map<String, Long> occurancesOfCategory = null;
		try {
			List<NewsDTO> latestNews = parserNewsFeedFromCSV();
			List<String> categories = new ArrayList<>();
			latestNews.forEach(news -> categories.addAll(news.getCategory()));
			occurancesOfCategory = categories.stream()
					.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		} catch (Exception e) {
			LOGGER.error("getTotalNewsPerCategory():: Error occurred while calculating news per category", e);
		}
		return occurancesOfCategory;

	}

	@Override
	public void feedNewsToCSV(List<NewsDTO> latestNews, String latestNewsFilePath) throws IOException {

		CSVFormat csvFormat = CSVFormat.DEFAULT.withRecordSeparator(NEW_LINE).withSkipHeaderRecord();
		CSVPrinter csvPrinter = null;
		Boolean isFileAlreadyCreated = isFileExist(latestNewsFilePath);
		try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(latestNewsFilePath), StandardOpenOption.APPEND,
				StandardOpenOption.CREATE);) {

			csvPrinter = new CSVPrinter(writer, csvFormat);

			if (!isFileAlreadyCreated) {
				List<String> csvHeader = Arrays.stream(Header.values()).map(Header::getText)
						.collect(Collectors.toList());
				csvPrinter.printRecord(csvHeader);
			}

			List<String> record = null;
			// Writing records to CSV
			LOGGER.info(LOG_MSG_WRT_CSV_START);
			for (NewsDTO news : latestNews) {
				record = new ArrayList<>();
				CurrentsNewsUtil.parseNewsDTOToRecord(news, record);
				csvPrinter.printRecord(record);
			}
			LOGGER.info(LOG_MSG_WRT_CSV_FINISH);

		} catch (IOException e) {
			LOGGER.error("feedNewsToCSV() :: Error occurred while writing to CSV file", e);
		} catch (Exception e) {
			throw e;
		} finally {
			if (csvPrinter != null) {
				csvPrinter.close();
			}
		}
	}

	private Boolean isFileExist(String path) {
		return new File(path).exists();
	}

	private List<NewsDTO> parserNewsFeedFromCSV() {

		File csvFile = new File(latestNewsFilePath);
		List<NewsDTO> latestNews = new ArrayList<>();
		if (csvFile.exists()) {
			try (Reader reader = Files.newBufferedReader(Paths.get(latestNewsFilePath));
					CSVParser csvParser = new CSVParser(reader,
							CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

				for (CSVRecord csvRecord : csvParser) {
					latestNews.add(CurrentsNewsUtil.parseRecordToNewsDTO(csvRecord));
				}
			} catch (Exception e) {
				LOGGER.error("parserNewsCSV() :: Error occurred while parsing csv", e);
			}

		}
		return latestNews;

	}

}
