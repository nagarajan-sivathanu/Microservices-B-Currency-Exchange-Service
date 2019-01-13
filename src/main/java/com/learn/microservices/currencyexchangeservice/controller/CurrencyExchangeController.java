package com.learn.microservices.currencyexchangeservice.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.learn.microservices.currencyexchangeservice.bean.ExchangeValue;
import com.learn.microservices.currencyexchangeservice.repository.ExchangeValueRepository;

@RestController
public class CurrencyExchangeController {
	
	@Autowired
	private Environment environment;
	@Autowired
	private ExchangeValueRepository repository;
	
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public ExchangeValue retrieveExchangeValue(@PathVariable String from,@PathVariable String to) {
		//ExchangeValue exchangeValue = new ExchangeValue(1000L,from,to,BigDecimal.valueOf(65));
		System.out.println("Inside Controller - From : "+from+" To : "+to);
		ExchangeValue exchangeValue = repository.findByFromAndTo(from, to);
		System.out.println("Inside Controller - After DAO Call");
		exchangeValue.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
		System.out.println("Inside Controller - After Setting Port No");
		return exchangeValue;
	}
}
