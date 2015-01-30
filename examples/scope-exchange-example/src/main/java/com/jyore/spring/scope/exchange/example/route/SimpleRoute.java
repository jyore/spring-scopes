package com.jyore.spring.scope.exchange.example.route;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jyore.spring.scope.exchange.example.processor.ValueChecker;
import com.jyore.spring.scope.exchange.example.processor.ValueSetter;
import com.jyore.spring.scope.exchange.support.ExchangeScopeAwareRouteBuilder;


//@Component
public class SimpleRoute extends ExchangeScopeAwareRouteBuilder {

	@Autowired
	private ValueSetter valueSetter;
	
	@Autowired
	private ValueChecker valueChecker;
	
	@Override
	public void configure() throws Exception {
		super.configure();
		from("timer://simple?fixedRate=true&period=5000")
			.bean(valueSetter,"process")
			.bean(valueChecker,"process")
		;
	}
}
