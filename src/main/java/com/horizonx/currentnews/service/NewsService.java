package com.horizonx.currentnews.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.horizonx.currentnews.dto.NewsDTO;

public interface NewsService {

	void feedLatestNews();

	Map<String, Long> getTotalNewsPerCategory();

	void feedNewsToCSV(List<NewsDTO> latestNews) throws IOException;
}
