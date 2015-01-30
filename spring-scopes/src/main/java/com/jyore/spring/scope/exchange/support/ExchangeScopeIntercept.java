package com.jyore.spring.scope.exchange.support;

import org.apache.camel.Exchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jyore.spring.scope.exchange.ExchangeAttributes;
import com.jyore.spring.scope.exchange.ExchangeContextHolder;



public class ExchangeScopeIntercept {

	private static final Logger log = LoggerFactory.getLogger(ExchangeScopeIntercept.class);
	
	public void process(Exchange exchange) {
		log.info("Intercepted exchange " + exchange.getExchangeId() + " routeId " + exchange.getFromRouteId() + " from " + exchange.getFromEndpoint().getEndpointUri() +
		" correlation_id " + exchange.getProperty(Exchange.CORRELATION_ID));
		ExchangeContextHolder.setExchangeAttributes(new ExchangeAttributes(exchange));
	}
}
