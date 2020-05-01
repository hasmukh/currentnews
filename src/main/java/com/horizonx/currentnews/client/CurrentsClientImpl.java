package com.horizonx.currentnews.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.horizonx.currentnews.dto.ResponseDTO;

/**
 * ]
 * 
 * @author Hasmukh Maniya
 *
 */
@Component
public class CurrentsClientImpl implements CurrentsClient {

	private static final Logger LOGGER = LoggerFactory.getLogger(CurrentsClientImpl.class);

	@Autowired
	RestTemplate restTemplate;

	@Value("${currents.baseURL}")
	private String currentsAPIBaseUrl;

	@Value("${currents.latestnews.endpoint}")
	private String latestNewsEndPoint;

	private static String apiKey = "81Htvkaqe5NzqUVXW2PGCsYlgt0Iokkhe6oinDVr5AsTuZJ-";

	@Override
	public ResponseDTO getLatestNewsByLanguage(String language) {
		String url = currentsAPIBaseUrl + latestNewsEndPoint;
		try {
			UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url).queryParam("language", language)
					.queryParam("apiKey", apiKey);

			return restTemplate.getForObject(builder.toUriString(), ResponseDTO.class);

		} catch (HttpClientErrorException e) {
			LOGGER.error("getLatestNewsByLanguage() :: Error occured while fetching Latest news", e);
			throw e;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
