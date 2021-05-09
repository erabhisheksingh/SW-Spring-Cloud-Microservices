package com.springcloud.demo.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springcloud.demo.beans.CurrencyConversionData;
import com.springcloud.demo.clients.CurrencyExchangeServiceProxy;
import com.springcloud.demo.exceptions.CurrencyConversionDataException;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
public class CurrencyConversionController {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private CurrencyExchangeServiceProxy feignProxy;

	@GetMapping("/convert/{from}/{to}/{quantity}")
	//@Retry(name = "convert", fallbackMethod = "convertFallback")
	@CircuitBreaker(name = "default", fallbackMethod = "convertFallback")
	public CurrencyConversionData convertCurrency(@PathVariable String from, @PathVariable String to,
			@PathVariable BigDecimal quantity) {

		ObjectMapper objectMapper = new ObjectMapper();
		CurrencyConversionData currencyConversionData = new CurrencyConversionData();
		try {
			/*
			 * currencyConversionData = objectMapper.readValue(
			 * (restTemplate.getForEntity("http://localhost:8000//exchange/USD/INR",
			 * String.class)).getBody(), CurrencyConversionData.class);
			 */
			Map<String, String> uriVariables = new HashMap<String, String>();
			uriVariables.put("from", from);
			uriVariables.put("to", to);
			ResponseEntity<CurrencyConversionData> entity = restTemplate.getForEntity(
					"http://localhost:8000/exchange/{from}/{to}", CurrencyConversionData.class, uriVariables);
			currencyConversionData = entity.getBody();
			currencyConversionData.setQuantity(quantity);
			currencyConversionData.setConvertedAmount(
					currencyConversionData.getExchangeRate().multiply(currencyConversionData.getQuantity()));
		} catch (RestClientException e) {
			e.printStackTrace();
			throw new CurrencyConversionDataException("Could not convert " + from + " currency " + to + " currency");
		}

		return currencyConversionData;
	}

	@GetMapping("/convert-feign/{from}/{to}/{quantity}")
	@Retry(name = "convert", fallbackMethod = "convertFallback")
	public CurrencyConversionData convertCurrencyFeign(@PathVariable String from, @PathVariable String to,
			@PathVariable BigDecimal quantity) {

		CurrencyConversionData currencyConversionData = new CurrencyConversionData();
		try {

			currencyConversionData = feignProxy.convertCurrencyFeign(from, to);
			currencyConversionData.setQuantity(quantity);
			currencyConversionData.setConvertedAmount(
					currencyConversionData.getExchangeRate().multiply(currencyConversionData.getQuantity()));
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		return currencyConversionData;
	}

	private CurrencyConversionData convertFallback(Exception ex) {

		CurrencyConversionData currencyConversionData = new CurrencyConversionData();
		currencyConversionData.setFrom("Fallback");
		currencyConversionData.setTo("Fallback");
		currencyConversionData.setPort(0);
		return currencyConversionData;
	}
}
