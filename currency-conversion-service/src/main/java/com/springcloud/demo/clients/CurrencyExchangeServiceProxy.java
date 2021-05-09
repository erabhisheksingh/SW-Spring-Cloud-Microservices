package com.springcloud.demo.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.springcloud.demo.beans.CurrencyConversionData;

//@FeignClient(name = "currency-exchange-service", url = "localhost:8001")
@FeignClient(name = "currency-exchange-service")
//@RibbonClient(name = "currency-exchange-service")
public interface CurrencyExchangeServiceProxy {
	
	@GetMapping("/exchange/{from}/{to}")
	public CurrencyConversionData convertCurrencyFeign(@PathVariable("from") String from, @PathVariable("to") String to);
}
