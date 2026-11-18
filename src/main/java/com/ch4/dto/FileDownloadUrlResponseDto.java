package com.ch4.dto;

import lombok.Getter;

@Getter
public class FileDownloadUrlResponseDto {

	private final String url;

	public FileDownloadUrlResponseDto(String url) {
		this.url = url;
	}
}