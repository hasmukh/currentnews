package com.horizonx.currentnews.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.horizonx.currentnews.service.NewsService;

/**
 * 
 * @author Hasmukh Maniya
 *
 */
@RestController
@RequestMapping(path = "/latest-news")
public class NewsController {

	@Autowired
	private NewsService newsService;

	@GetMapping("/feed")
	public ResponseEntity feedLatestNews() {
		newsService.feedLatestNews();
		return new ResponseEntity(HttpStatus.OK);
	}

	@GetMapping("/count-categorywise")
	public ResponseEntity<Map<String, Long>> counteTotalNewsForEachCategory() {
		return new ResponseEntity<>(newsService.getTotalNewsPerCategory(), HttpStatus.OK);
	}
}
