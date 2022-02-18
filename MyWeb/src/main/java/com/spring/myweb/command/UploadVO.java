package com.spring.myweb.command;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class UploadVO {

	private String name;
	private MultipartFile file;
	
}
