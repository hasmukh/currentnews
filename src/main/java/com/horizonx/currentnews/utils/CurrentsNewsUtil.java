package com.horizonx.currentnews.utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;

import com.horizonx.currentnews.dto.NewsDTO;
import com.horizonx.currentnews.enums.Header;

@Component
public class CurrentsNewsUtil {

	public static NewsDTO parseRecordToNewsDTO(CSVRecord csvRecord) {
		return NewsDTO.builder().id(csvRecord.get(Header.ID.getText())).author(csvRecord.get(Header.AUTHOR.getText()))
				.title(csvRecord.get(Header.TITLE.getText())).description(csvRecord.get(Header.DESCRIPTION.getText()))
				.category(Arrays.asList(csvRecord.get(Header.CATEGORY.getText()).split("\\|")))
				.image(csvRecord.get(Header.IMAGE.getText())).url(csvRecord.get(Header.URL.getText()))
				.language(csvRecord.get(Header.LANGUAGE.getText())).published(csvRecord.get(Header.PUBLISHED.getText()))
				.build();
	}

	public static void setRecord(NewsDTO news, List<String> record) {
		record.add(news.getId());
		record.add(news.getTitle());
		record.add(news.getDescription());
		record.add(news.getUrl());
		record.add(news.getAuthor());
		record.add(news.getImage());
		record.add(news.getLanguage());
		record.add(news.getCategory().stream().collect(Collectors.joining("|")));
		record.add(news.getPublished());
	}
}
