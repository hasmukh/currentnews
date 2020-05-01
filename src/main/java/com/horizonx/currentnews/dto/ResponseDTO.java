package com.horizonx.currentnews.dto;

import java.util.List;

import lombok.Builder;
import lombok.Data;

/**
 * 
 * @author Hasmukh Maniya
 *
 */
@Data
@Builder
public class ResponseDTO {
	
	private String status;
	private List<NewsDTO> news;
	
}
