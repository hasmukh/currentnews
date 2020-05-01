package com.horizonx.currentnews.client;

import java.net.URISyntaxException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.horizonx.currentnews.CurrentsNewsBaseTest;
import com.horizonx.currentnews.data.CurrentsNewsTestData;
import com.horizonx.currentnews.dto.ResponseDTO;

public class CurrentsClientTest extends CurrentsNewsBaseTest {

	@Mock
	private RestTemplate restTemplate;

	@InjectMocks
	private CurrentsClientImpl currentsClient;

	@Test
	public void testCurrentsClientFetchLatestNews() throws JsonProcessingException, URISyntaxException {

		ResponseDTO expectedResponse = CurrentsNewsTestData.getCurrentsNewsResponse();
		ResponseDTO actualResponse = null;
		String URL = "https://api.currentsapi.services/v1/latest-news?apiKey=81Htvkaqe5NzqUVXW2PGCsYlgt0Iokkhe6oinDVr5AsTuZJ-&language=en";
		try {
			Mockito.when(restTemplate.getForObject(URL, ResponseDTO.class)).thenReturn(expectedResponse);

			actualResponse = currentsClient.getLatestNewsByLanguage("en");

		} catch (Exception e) {
			e.printStackTrace();
		}

		Assertions.assertEquals(expectedResponse, actualResponse);
	}

}
