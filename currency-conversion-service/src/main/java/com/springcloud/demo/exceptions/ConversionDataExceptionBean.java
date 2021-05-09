package com.springcloud.demo.exceptions;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ConversionDataExceptionBean {

	private LocalDateTime timeStamp;

	private String message;

	private String details;

	public ConversionDataExceptionBean(LocalDateTime timeStamp, String message, String details) {
		super();
		this.timeStamp = timeStamp;
		this.message = message;
		this.details = details;
	}
}
