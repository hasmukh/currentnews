package com.horizonx.currentnews.data;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.http.HttpStatus;

import com.horizonx.currentnews.dto.NewsDTO;
import com.horizonx.currentnews.dto.ResponseDTO;

@TestComponent
public class CurrentsNewsTestData {

	public static ResponseDTO getCurrentsNewsResponse() {
		return ResponseDTO.builder().status(HttpStatus.OK.toString()).news(Arrays.asList(getCurrentsNewsDTO())).build();
	}

	public static NewsDTO getCurrentsNewsDTO() {
		return NewsDTO.builder().id("24018463-e043-43c3-8a33-00ce3904a164").title("De Blasio breaks up").description(
				"Hundreds of Orthodox Jewish mourners flooded the sidewalks and intersection of a Brooklyn neighborhood")
				.url("https://www.washingtonpost.com/nation/2020/04/29/rabbi-funeral-coronavirus-deblasio-jews/")
				.author("Timothy Bella")
				.image("https://www.washingtonpost.com/resizer/M09jrMj58rpoMBgq2FkLKYWTAJE=/1440x0/smart/arc-anglerfish-washpost-prod-washpost.s3.amazonaws.com/public/NA2TQ4UGLEI6VANDS2IMTCARCE.jpg")
				.language("en").category(Arrays.asList("regional", "buisness")).published("2020-04-30 18:25:18 +0000")
				.build();
	}

	public static CSVRecord getCSVParseForUtilTest(String testFilePath) {
		File csvFile = new File(testFilePath);
		CSVRecord csvRecordData = null;
		if (csvFile.exists()) {
			try (Reader reader = Files.newBufferedReader(Paths.get(testFilePath));
					CSVParser csvParser = new CSVParser(reader,
							CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

				for (CSVRecord csvRecord : csvParser) {
					csvRecordData = csvRecord;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		return csvRecordData;

	}

	public static Map<String, Long> getTotalNewsPerCategoryPreData() {
		Map<String, Long> countOfNewsPerCategory = new HashMap<String, Long>();
		countOfNewsPerCategory.put("regional", 1L);
		countOfNewsPerCategory.put("buisness", 1L);
		return countOfNewsPerCategory;
	}

}
