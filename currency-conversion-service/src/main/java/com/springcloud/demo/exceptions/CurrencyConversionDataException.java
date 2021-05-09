package com.springcloud.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class CurrencyConversionDataException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CurrencyConversionDataException(String message) {
		super(message);
	}
}
