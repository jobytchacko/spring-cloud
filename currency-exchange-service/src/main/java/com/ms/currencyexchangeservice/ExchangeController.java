package com.ms.currencyexchangeservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExchangeController {

	@Autowired
	Environment environment;
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	
	@GetMapping("currency-exchange-service/from/{from}/to/{to}")
	public ExchangeValue exchangeCurrency(@PathVariable String from, @PathVariable String to) {
		System.out.println("To Find the ezchange vale for "+from);
		ExchangeValue exchangeValue =  new ExchangeValue(from,to,Integer.parseInt(environment.getProperty("local.server.port")));
		logger.info("{}", exchangeValue);
		return exchangeValue;
	}
}
