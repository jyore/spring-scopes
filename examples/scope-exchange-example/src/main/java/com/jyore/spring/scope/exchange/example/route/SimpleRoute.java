package com.jyore.spring.scope.exchange.example.route;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jyore.spring.scope.exchange.AbstractExchangeScopeRouteBuilder;
import com.jyore.spring.scope.exchange.example.processor.ExchangeScopeLoggingProcessor;
import com.jyore.spring.scope.exchange.example.processor.ExchangeScopeValueSetter;


@Component
public class SimpleRoute extends AbstractExchangeScopeRouteBuilder {

	@Autowired
	private ExchangeScopeValueSetter valueSetter;
	
	@Autowired
	private ExchangeScopeLoggingProcessor loggingProcessor;
	
	@Override
	public void configure() throws Exception {
		super.configure();
		from("timer://simple?fixedRate=true&period=15000")
			.bean(valueSetter,"process")
			.bean(loggingProcessor,"process")
		;
	}	
}
