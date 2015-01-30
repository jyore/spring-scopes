package com.jyore.spring.scope.route;

import java.util.HashMap;
import java.util.Map;

import org.apache.camel.Exchange;

public class ExchangeAttributes {

	private static final String DESCTRUCTION_CB_PREFIX = ExchangeAttributes.class.getName() + ".DESTRUCTION_CALLBACK.";
	private final Map<String,Runnable> destructionCallbacks = new HashMap<String,Runnable>();
	private volatile Exchange exchange;
	
	
	public ExchangeAttributes(Exchange exchange) {
		this.exchange = exchange;
	}
	
	public Exchange getExchange() {
		return exchange;
	}
	
	public void registerDestructionCallback(String name, Runnable callback) {
		destructionCallbacks.put(DESCTRUCTION_CB_PREFIX + name, callback);
	}

}
