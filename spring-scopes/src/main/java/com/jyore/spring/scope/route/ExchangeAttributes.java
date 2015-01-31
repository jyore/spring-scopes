package com.jyore.spring.scope.route;

import java.util.HashMap;
import java.util.Map;

import org.apache.camel.Exchange;

/**
 * Holds the exchange and associated attributes and callbacks
 * 
 * @author jyore
 * @version 1.0
 */
public class ExchangeAttributes {

	private static final String DESCTRUCTION_CB_PREFIX = ExchangeAttributes.class.getName() + ".DESTRUCTION_CALLBACK.";
	private final Map<String,Runnable> destructionCallbacks = new HashMap<String,Runnable>();
	private volatile Exchange exchange;
	
	/**
	 * Create a new instance from an  @{link Exchange}
	 * 
	 * @param exchange
	 */
	public ExchangeAttributes(Exchange exchange) {
		this.exchange = exchange;
	}
	
	/**
	 * Retrieve the stored {@link Exchange}
	 * 
	 * @return the {@link Exchange}
	 */
	public Exchange getExchange() {
		return exchange;
	}
	
	/**
	 * Register a destruction callback
	 * 
	 * @param name The name of the callback
	 * @param callback A {link Runnable} to call as a callback
	 */
	public void registerDestructionCallback(String name, Runnable callback) {
		destructionCallbacks.put(DESCTRUCTION_CB_PREFIX + name, callback);
	}

}
