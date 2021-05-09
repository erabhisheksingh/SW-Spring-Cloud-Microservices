package com.springcloud.demo.bean;

import lombok.Data;

@Data
public class LimitConfiguration {
	
	private int maximumLimit;
	
	private int minimumLimit;
}
