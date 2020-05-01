package com.horizonx.currentnews.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author Hasmukh Maniya
 *
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor	
public class NewsDTO {

	private String id;
	private String title;
	private String description;
	private String url;
	private String author;
	private String image;
	private String language;
	private List<String> category;
	private String published;

}
