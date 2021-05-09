package com.springcloud.demo.controller;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.springcloud.demo.exceptions.ExchangeDataExceptionBean;
import com.springcloud.demo.exceptions.ExchangeDataNotFoundException;

@ControllerAdvice
@RestController
public class CurrencyExchangeExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	protected ResponseEntity<ExchangeDataExceptionBean> handleAllExceptions(Exception ex, WebRequest request) {

		ExchangeDataExceptionBean exceptionBean = new ExchangeDataExceptionBean(LocalDateTime.now(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<ExchangeDataExceptionBean>(exceptionBean, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(ExchangeDataNotFoundException.class)
	protected ResponseEntity<ExchangeDataExceptionBean> handleExchangeDataNotFoundExceptions(Exception ex, WebRequest request) {

		ExchangeDataExceptionBean exceptionBean = new ExchangeDataExceptionBean(LocalDateTime.now(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<ExchangeDataExceptionBean>(exceptionBean, HttpStatus.NOT_FOUND);
	}
}
