package com.jyore.spring.scope.exchange;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractExchangeScopeRoute extends RouteBuilder {

	@Autowired
	protected ExchangeScopeEventContext context;
	
	@Override
	public void configure() throws Exception {
		intercept().bean(context,"process");
	}

}
