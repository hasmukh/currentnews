package com.horizonx.currentnews.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.csv.CSVRecord;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.horizonx.currentnews.CurrentsNewsBaseTest;
import com.horizonx.currentnews.data.CurrentsNewsTestData;
import com.horizonx.currentnews.dto.NewsDTO;
import com.horizonx.currentnews.service.NewsService;

public class CurrentsNewsUtilsTest extends CurrentsNewsBaseTest {

	@Value("${currents.latestnews.file.path}")
	private String latestNewsFilePath;

	@Autowired
	NewsService newsService;

	public void wipeFile() {
		File latestNewsFile = new File(latestNewsFilePath);
		if (latestNewsFile.exists()) {
			latestNewsFile.delete();
		}
	}

	@Test
	public void testParserRecordToNewsDTO() throws IOException {

		List<NewsDTO> latestNews = Arrays.asList(CurrentsNewsTestData.getCurrentsNewsDTO());
		newsService.feedNewsToCSV(latestNews, latestNewsFilePath);

		String expectedNewsId = CurrentsNewsTestData.getCurrentsNewsDTO().getId();
		CSVRecord csvRecord = CurrentsNewsTestData.getCSVParseForUtilTest(latestNewsFilePath);
		NewsDTO actualNewsResult = CurrentsNewsUtil.parseRecordToNewsDTO(csvRecord);
		Assertions.assertEquals(expectedNewsId, actualNewsResult.getId());
		wipeFile();
	}

	@Test
	public void testSetRecordOfCSVFromNewsDTO() {
		Integer csvRecordSize = 9;
		NewsDTO newsDTO = CurrentsNewsTestData.getCurrentsNewsDTO();
		List<String> csvRecord = new ArrayList<>();
		CurrentsNewsUtil.parseNewsDTOToRecord(newsDTO, csvRecord);

		Assertions.assertEquals(csvRecord.size(), csvRecordSize);
		Assertions.assertEquals(newsDTO.getId(), csvRecord.get(0));

	}

}
