package com.springcloud.demo.controller;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.springcloud.demo.exceptions.ConversionDataExceptionBean;
import com.springcloud.demo.exceptions.CurrencyConversionDataException;

@ControllerAdvice
@RestController
public class CurrencyConversionExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	protected ResponseEntity<ConversionDataExceptionBean> handleAllExceptions(Exception ex, WebRequest request) {

		ConversionDataExceptionBean exceptionBean = new ConversionDataExceptionBean(LocalDateTime.now(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<ConversionDataExceptionBean>(exceptionBean, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(CurrencyConversionDataException.class)
	protected ResponseEntity<ConversionDataExceptionBean> handleExchangeDataNotFoundExceptions(Exception ex, WebRequest request) {

		ConversionDataExceptionBean exceptionBean = new ConversionDataExceptionBean(LocalDateTime.now(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<ConversionDataExceptionBean>(exceptionBean, HttpStatus.NOT_FOUND);
	}
}
