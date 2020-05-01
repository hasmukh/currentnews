package com.horizonx.currentnews.enums;

/**
 * 
 * @author Hasmukh Maniya
 *
 */

public enum Header {

	ID("ID"), TITLE("TITLE"), DESCRIPTION("DESCRIPTION"), URL("URL"), AUTHOR("AUTHOR"), IMAGE("IMAGE"), LANGUAGE("LANGUAGE"), CATEGORY("CATEGORY"),
	PUBLISHED("PUBLISHED");

	private final String name;

	Header(String name) {
		this.name = name;
	}

	public String getText() {
		return name;
	}

}
