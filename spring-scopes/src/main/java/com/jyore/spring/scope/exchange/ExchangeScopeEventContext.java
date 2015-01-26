package com.jyore.spring.scope.exchange;

import org.apache.camel.Exchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/*
public class ExchangeScopeEventContext extends EventNotifierSupport {

	private static final Logger LOGGER = LoggerFactory.getLogger(ExchangeScopeEventContext.class);
	private static ThreadLocal<String> conversation = new ThreadLocal<String>();
	
	
	public void notify(EventObject event) throws Exception {
		if(event instanceof ExchangeCreatedEvent) {
			ExchangeCreatedEvent created = (ExchangeCreatedEvent) event;
			conversation.set(created.getExchange().getExchangeId());
			LOGGER.info("CONVERSATION SET: {}",conversation.get());
	    } else if(event instanceof ExchangeCompletedEvent) {
			conversation.remove();
			LOGGER.info("CONVERSATION REMOVED");
		}
		
	}
	
	public boolean isEnabled(EventObject event) {
		return (
			( event instanceof ExchangeCreatedEvent   ) ||
			( event instanceof ExchangeCompletedEvent )
		);
	}
	
	public static String getConversation() {
		return conversation.get();
	}
	
}
*/


public class ExchangeScopeEventContext {
	private static final Logger LOGGER = LoggerFactory.getLogger(ExchangeScopeEventContext.class);
	//private static ThreadLocal<String> conversation = new ThreadLocal<String>();
	private static String conversation;
	
	
	public void process(Exchange exchange) {
		LOGGER.info("INTERCEPTED EXCHANGE: " + exchange.getExchangeId() + " FROM: " + exchange.getFromEndpoint().getEndpointUri());
		//conversation.set(exchange.getExchangeId());
		conversation = exchange.getExchangeId();
	}
	
	public static String getConversation() {
		return conversation;
	}
	
}