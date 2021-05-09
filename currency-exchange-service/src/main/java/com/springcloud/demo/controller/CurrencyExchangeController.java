package com.springcloud.demo.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.springcloud.demo.bean.ExchangeData;
import com.springcloud.demo.service.CurrencyExchangeService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
public class CurrencyExchangeController {
	
	@Autowired 
	private Environment environment;
	
	@Autowired
	private CurrencyExchangeService service;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CurrencyExchangeController.class);
	
	// Used with SessionLocaleResolver and @RequestHeader
	/*
	 * @GetMapping("/exchange/info/i18n") public String
	 * getServiceInfo(@RequestHeader(name = "Accept-Language", required = false)
	 * Locale locale) { return service.getServiceInfo(locale); }
	 */
	
	@GetMapping("/exchange/info/i18n")
	public String getServiceInfo() {
		return service.getServiceInfo();
	}
	
	@GetMapping("/exchange/{from}/{to}")
	//@Retry(name = "exchange", fallbackMethod = "exchangeFallback")
	@CircuitBreaker(name = "default", fallbackMethod = "exchangeFallback")
	public ExchangeData getExchangeData(@PathVariable("from") String from, @PathVariable("to") String to) {
		LOGGER.info("CurrencyExchangeController.getExchangeData invoked!!");
		Optional<ExchangeData> data = service.getExchangeDataByFromAndTo(from, to);
		if (data.isPresent()) {
			data.get().setPort(Integer.valueOf(environment.getProperty("local.server.port")));
		} 
		//Use this code when @Retyr with fallback is not used. It is used to map a user defined exception to standard HTTP status like 404
		/*
		 * else { throw new ExchangeDataNotFoundException( "Data not found to convert "
		 * + from + " currency to " + to + " currency"); }
		 */
		return data.orElse(new ExchangeData());
	}
	
	private ExchangeData exchangeFallback(Exception ex) {
		
		ExchangeData exchangeData = new ExchangeData();
		exchangeData.setFrom("Fallback");
		exchangeData.setTo("Fallback");
		exchangeData.setPort(0);
		return exchangeData;
	}
	
}
