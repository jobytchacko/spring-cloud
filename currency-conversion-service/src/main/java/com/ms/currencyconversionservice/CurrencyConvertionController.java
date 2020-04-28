package com.ms.currencyconversionservice;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CurrencyConvertionController {
	
	@Autowired
	private CurrencyExchangeProxy exchangeProxy;
	private Logger logger = LoggerFactory.getLogger(this.getClass());


	@GetMapping("/currency-conversion-service/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversionBean convertCurrency(@PathVariable String from, @PathVariable String to, @PathVariable String quantity) {
		int rate = 75;
		
		Map<String, String> uriVariables = new HashMap<String, String>();
		uriVariables.put("from", from);
		uriVariables.put("to",to);
		
		ResponseEntity<CurrencyConversionBean> response = new RestTemplate().getForEntity(
																				"http://localhost:9003/currency-exchange-service/from/{from}/to/{to}", 
																				CurrencyConversionBean.class,
																				uriVariables);
		CurrencyConversionBean responseBody = response.getBody();
		
		return new CurrencyConversionBean(from,to,Integer.parseInt(quantity),responseBody.getRate(),responseBody.getPort());
	}
	
	
	@GetMapping("/currency-conversion-service-feign/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversionBean convertCurrencyFeign(@PathVariable String from, @PathVariable String to, @PathVariable String quantity) {
		CurrencyConversionBean responseBody = null;
		try {
		responseBody = exchangeProxy.exchangeCurrency(from, to);
		logger.info("{}", responseBody);
		//System.out.println("------------"+responseBody.getAmount());
		} catch(Exception e) {
			System.out.println("There is some exception");
			e.printStackTrace();
		}
		return new CurrencyConversionBean(from,to,Integer.parseInt(quantity),responseBody.getRate(),responseBody.getPort());
	}
	
	
	
	@GetMapping("/test")
	public String hello() {
		return "Ok";
	}
	
}
