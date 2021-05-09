package com.springcloud.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ExchangeDataNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ExchangeDataNotFoundException(String message) {
		super(message);
	}

}
