package com.horizonx.currentnews.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class NewsServiceTest {

	@Autowired
	private NewsService newsService;

	@Test
	void testExample() {
		newsService.feedLatestNews();
		assertEquals(true, true);
	}
}
