package com.springcloud.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springcloud.demo.bean.ExchangeData;

public interface CurrencyExchangeRepository extends JpaRepository<ExchangeData, Long> {
	
	public Optional<ExchangeData> findByFromAndTo(String from, String to);
}
