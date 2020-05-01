package com.horizonx.currentnews.client;

import com.horizonx.currentnews.dto.ResponseDTO;

public interface CurrentsClient {

	ResponseDTO getLatestNewsByLanguage(String language);
}
