package com.horizonx.currentnews;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.horizonx.currentnews.service.NewsService;

@SpringBootTest
public class CurrentsNewsBaseTest {
	
	@Autowired
	NewsService newsService;
	
}
