package com.jyore.spring.scope.exchange.example.processor;

import org.apache.camel.Exchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jyore.spring.scope.exchange.example.beans.ExchangeScopedString;


@Component
public class ExchangeScopeValueSetter {

	private static int counter = 0;
	private ExchangeScopedString exchangeScopedString;
	
	@Autowired
	public void setExchangeScopedString(ExchangeScopedString exchangeScopedString) {
		this.exchangeScopedString = exchangeScopedString;
	}
	
	public void process(Exchange exchange) {
		exchangeScopedString.setValue(String.format("Exchange Scope Value -- %d",counter));
		counter += 1;
	}
}
