package com.jyore.spring.scope.exchange.support;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;



public class ExchangeScopeAwareRouteBuilder extends RouteBuilder {

	private ExchangeScopeIntercept exchangeScopeIntercept;
	
	@Autowired
	public void setExchangeScopeIntercept(ExchangeScopeIntercept exchangeScopeIntercept) {
		this.exchangeScopeIntercept = exchangeScopeIntercept;
	}
	
	@Override
	public void configure() throws Exception {
		intercept()
			.bean(exchangeScopeIntercept,"process")
		;
	}

}
