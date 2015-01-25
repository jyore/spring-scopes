package com.jyore.spring.scope.exchange.example.processor;

import org.apache.camel.Exchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jyore.spring.scope.exchange.example.beans.ExchangeScopedString;


@Component
public class ExchangeScopeLoggingProcessor {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ExchangeScopeLoggingProcessor.class);
	private ExchangeScopedString exchangeScopedString;
	
	@Autowired
	public void setExchangeScopedString(ExchangeScopedString exchangeScopedString) {
		this.exchangeScopedString = exchangeScopedString;
	}
	
	public void process(Exchange exchange) {
		LOGGER.info("Exchange String found: {}",exchangeScopedString.getValue());
	}
	
}
