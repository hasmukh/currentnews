package com.horizonx.currentnews.service;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.csv.CSVRecord;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.horizonx.currentnews.CurrentsNewsBaseTest;
import com.horizonx.currentnews.data.CurrentsNewsTestData;
import com.horizonx.currentnews.dto.NewsDTO;
import com.horizonx.currentnews.utils.CurrentsNewsUtil;

public class NewsServiceTest extends CurrentsNewsBaseTest {

	@Value("${currents.latestnews.file.path}")
	private String latestNewsFilePath;

	@Autowired
	private NewsService newsService;

	@BeforeEach
	public void setUp() throws IOException {
		List<NewsDTO> latestNews = Arrays.asList(CurrentsNewsTestData.getCurrentsNewsDTO());
		newsService.feedNewsToCSV(latestNews, latestNewsFilePath);
	}

	@AfterEach
	public void wipeFile() {
		File latestNewsFile = new File(latestNewsFilePath);
		if (latestNewsFile.exists()) {
			latestNewsFile.delete();
		}
	}

	@Test
	public void testFeedNewsTOCSV() throws IOException {

		String expectedNewsId = CurrentsNewsTestData.getCurrentsNewsDTO().getId();
		CSVRecord csvRecord = CurrentsNewsTestData.getCSVParseForUtilTest(latestNewsFilePath);
		NewsDTO actualNewsResult = CurrentsNewsUtil.parseRecordToNewsDTO(csvRecord);
		Assertions.assertEquals(expectedNewsId, actualNewsResult.getId());
	}

	@Test
	public void testGetTotalNewsPerCategory() throws IOException {
		Map<String, Long> expectedCountOfNews = CurrentsNewsTestData.getTotalNewsPerCategoryPreData();
		Map<String, Long> totalCountOfNewsPerCategory = newsService.getTotalNewsPerCategory();

		Assertions.assertEquals(expectedCountOfNews.get("regional"), totalCountOfNewsPerCategory.get("regional"));
		Assertions.assertEquals(expectedCountOfNews.get("buisness"), totalCountOfNewsPerCategory.get("buisness"));
	}
}
