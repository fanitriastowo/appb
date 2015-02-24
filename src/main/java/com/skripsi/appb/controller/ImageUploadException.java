package com.skripsi.apsb.controller;

import java.io.IOException;

import org.apache.commons.fileupload.FileUploadException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;

@ControllerAdvice
public class ImageUploadException {

	private static final Logger LOG = LoggerFactory.getLogger(ImageUploadException.class);

	@ExceptionHandler(MaxUploadSizeExceededException.class)
	@ResponseBody
	public String maxUploadException(MaxUploadSizeExceededException e) {
		LOG.error(e.getMessage(), e);
		return "Gambar tidak boleh lebih dari 1 MB";
	}

	@ExceptionHandler(FileUploadException.class)
	@ResponseBody
	public String maxUploadFileException(FileUploadException e) {
		LOG.error(e.getMessage(), e);
		return "Gambar tidak boleh lebih dari 1 MB";
	}

	@ExceptionHandler(value = { IOException.class, MultipartException.class })
	@ResponseBody
	public String handleCustomException(Exception e) {

		LOG.error(e.getMessage(), e);
		return "There was a problem while uploading image. Try again";
	}
}
