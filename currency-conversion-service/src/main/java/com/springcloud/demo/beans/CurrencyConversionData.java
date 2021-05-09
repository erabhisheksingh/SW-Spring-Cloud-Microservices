package com.springcloud.demo.beans;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "This is a modal object for Currency Conversion.")
public class CurrencyConversionData {
	
	private Long id;
	
	@NotNull
	@ApiModelProperty(notes = "The from field cannot be empty.")
	private String from;
	
	@NotNull
	@ApiModelProperty(notes = "The to field cannot be empty.")
	private String to;
	
	private BigDecimal exchangeRate;
	
	@NotNull
	@ApiModelProperty(notes = "The quantity field cannot be empty.")
	private BigDecimal quantity;
	
	private BigDecimal convertedAmount;
	
	private int port;
	
	public CurrencyConversionData() {
		
	}
	
	public CurrencyConversionData(Long id, String from, String to, BigDecimal exchangeRate, BigDecimal quantity,
			BigDecimal convertedAmount, int port) {
		super();
		this.id = id;
		this.from = from;
		this.to = to;
		this.exchangeRate = exchangeRate;
		this.quantity = quantity;
		this.convertedAmount = convertedAmount;
		this.port = port;
	}
	
	
}
