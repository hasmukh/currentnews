package com.horizonx.currentnews.data;

import java.util.Arrays;

import org.springframework.boot.test.context.TestComponent;
import org.springframework.http.HttpStatus;

import com.horizonx.currentnews.dto.NewsDTO;
import com.horizonx.currentnews.dto.ResponseDTO;

@TestComponent
public class CurrentsNewsTestData {

	public static ResponseDTO getCurrentsNewsResponse() {
		return ResponseDTO.builder().status(HttpStatus.OK.toString()).news(Arrays.asList(getCurrentsNewsDTO())).build();
	}

	public static NewsDTO getCurrentsNewsDTO() {
		return NewsDTO.builder().id("24018463-e043-43c3-8a33-00ce3904a164").title("De Blasio breaks up").description(
				"Hundreds of Orthodox Jewish mourners flooded the sidewalks and intersection of a Brooklyn neighborhood")
				.url("https://www.washingtonpost.com/nation/2020/04/29/rabbi-funeral-coronavirus-deblasio-jews/")
				.author("Timothy Bella")
				.image("https://www.washingtonpost.com/resizer/M09jrMj58rpoMBgq2FkLKYWTAJE=/1440x0/smart/arc-anglerfish-washpost-prod-washpost.s3.amazonaws.com/public/NA2TQ4UGLEI6VANDS2IMTCARCE.jpg")
				.language("en").category(Arrays.asList("regional", "buisness")).published("2020-04-30 18:25:18 +0000")
				.build();
	}

}
