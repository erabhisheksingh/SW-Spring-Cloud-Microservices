package com.springcloud.demo.bean;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "This is a modal object for Currency Exchange.")
@Entity
@Table(name = "Exchange_Data")
public class ExchangeData {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "column_from")
	@NotNull
	@ApiModelProperty(notes = "The from field cannot be empty.")
	private String from;
	
	@Column(name = "column_to")
	@NotNull
	@ApiModelProperty(notes = "The to field cannot be empty.")
	private String to;
	
	@Column(name = "exchange_rate")
	private BigDecimal exchangeRate;
	
	private int port;

	public ExchangeData(Long id, String from, String to, BigDecimal exchangeRate, int port) {
		super();
		this.id = id;
		this.from = from;
		this.to = to;
		this.exchangeRate = exchangeRate;
		this.port = port;
	}
	
	public ExchangeData() {
		
	}
}
