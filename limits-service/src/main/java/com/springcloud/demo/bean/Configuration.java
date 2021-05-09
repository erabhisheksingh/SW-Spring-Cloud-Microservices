package com.springcloud.demo.bean;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@ConfigurationProperties("limits-service")
public class Configuration {

	private int maximum;
	
	private int minimum;
}
