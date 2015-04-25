package com.jyore.spring.scope.route;

import java.util.HashMap;
import java.util.Map;

import org.apache.camel.Exchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jyore.spring.scope.ScopeContext;

/**
 * Holds the exchange and associated attributes and callbacks
 * 
 * @see ScopeContext
 * @author jyore
 * @version 1.0
 */
public class ExchangeAttributes implements ScopeContext {
	private static final Logger log = LoggerFactory.getLogger(ExchangeAttributes.class);
	private static final String DESTRUCTION_CB_PREFIX = ExchangeAttributes.class.getName() + ".DESTRUCTION_CALLBACK.";
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
	
	public Object get() {
		return exchange;
	}

	public void registerDestructionCallback(String name, Runnable callback) {
		synchronized (this.destructionCallbacks) {
			destructionCallbacks.put(DESTRUCTION_CB_PREFIX + name, callback);
		}
	}
	
	public void executeDesctructionCallbacks() {
		synchronized (this.destructionCallbacks) {
			for(String cb : destructionCallbacks.keySet()) {
				log.debug("Executing destruction callback: " + cb);
				destructionCallbacks.get(cb).run();
			}
			destructionCallbacks.clear();
		}
	}

}
