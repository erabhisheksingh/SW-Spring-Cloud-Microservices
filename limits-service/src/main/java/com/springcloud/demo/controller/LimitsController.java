package com.springcloud.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springcloud.demo.bean.Configuration;
import com.springcloud.demo.bean.LimitConfiguration;

@RestController
public class LimitsController {
	
	@Autowired
	private Configuration config;
	
	@GetMapping("/limits")
	public LimitConfiguration getLimitsFromConfigs() {
		LimitConfiguration limits = new LimitConfiguration();
		limits.setMaximumLimit(config.getMaximum());
		limits.setMinimumLimit(config.getMinimum());
		return limits;
	}
}
