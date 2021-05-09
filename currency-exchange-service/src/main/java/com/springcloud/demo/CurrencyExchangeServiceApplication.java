package com.springcloud.demo;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

@SpringBootApplication
public class CurrencyExchangeServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurrencyExchangeServiceApplication.class, args);
	}
	
	@Bean
	public LocaleResolver getLocaleResolver() {
		
		// SessionLocaleResolver is not required if @RequestHeader is not used.
		//SessionLocaleResolver localeResolver = new SessionLocaleResolver();
		AcceptHeaderLocaleResolver localeResolver = new AcceptHeaderLocaleResolver();
		localeResolver.setDefaultLocale(Locale.US);
		return localeResolver;
	}
	
	// The configuration is now picked from the application.yml file
	// spring.messages.basename=messages
	/*
	 * @Bean public ResourceBundleMessageSource messageSource() {
	 * 
	 * ResourceBundleMessageSource resource = new ResourceBundleMessageSource();
	 * resource.setBasename("message"); return resource; }
	 */
}
