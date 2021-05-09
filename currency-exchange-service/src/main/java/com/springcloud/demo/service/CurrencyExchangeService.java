package com.springcloud.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import com.springcloud.demo.bean.ExchangeData;
import com.springcloud.demo.repository.CurrencyExchangeRepository;

@Service
public class CurrencyExchangeService {
	
	@Autowired
	private CurrencyExchangeRepository repository;
	
	@Autowired
	private MessageSource messageSource;
	
	public Optional<ExchangeData> getExchangeDataByFromAndTo(String from, String to) {
		
		return repository.findByFromAndTo(from, to);
	}
	
	// Used with SessionLocaleResolver and @RequestHeader
	/*
	 * public String getServiceInfo(Locale locale) {
	 * 
	 * return messageSource.getMessage("service.info", null, locale); }
	 */
	
	public String getServiceInfo() {
		
		return messageSource.getMessage("service.info", null, LocaleContextHolder.getLocale());
	}
}
