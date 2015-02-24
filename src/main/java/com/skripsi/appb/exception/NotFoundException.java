package com.skripsi.apsb.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {

	private static final long serialVersionUID = -3362472695200620256L;

	public NotFoundException(String message) {
		super(message);
	}

}
