package com.ms.currencyconversionservice;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(name="currency-exchange-service", url ="localhost:9004")
//@FeignClient(name="currency-exchange-service")
@FeignClient(name="zuul-api-gateway-server")
@RibbonClient(name="currency-exchange-service")
public interface CurrencyExchangeProxy {
	
	//@GetMapping("currency-exchange-service/from/{from}/to/{to}")
	@GetMapping("currency-exchange-service/currency-exchange-service/from/{from}/to/{to}")
	public CurrencyConversionBean exchangeCurrency(@PathVariable("from") String from, @PathVariable("to") String to);

}
