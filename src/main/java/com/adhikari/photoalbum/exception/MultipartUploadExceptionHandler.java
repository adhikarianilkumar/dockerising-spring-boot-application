package com.adhikari.photoalbum.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

@RestControllerAdvice
public class MultipartUploadExceptionHandler {
	
	@Value("${spring.servlet.multipart.max-file-size}")
	private String maxResourceSize;
	
	@ExceptionHandler(MaxUploadSizeExceededException.class)
	public String handleFileUploadException(MaxUploadSizeExceededException exception, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		return "Resource size is more " + maxResourceSize;
	}

}
