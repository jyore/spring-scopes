package com.jyore.spring.scope.exchange;

import java.util.EventObject;

import org.apache.camel.management.event.ExchangeCompletedEvent;
import org.apache.camel.management.event.ExchangeCreatedEvent;
import org.apache.camel.support.EventNotifierSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExchangeEventNotifier extends EventNotifierSupport {

	private static final Logger log = LoggerFactory.getLogger(ExchangeEventNotifier.class);
	
	public void notify(EventObject event) throws Exception {
		if(event instanceof ExchangeCreatedEvent) {
			log.info("RECEIVED CREATE EVENT");
			ExchangeCreatedEvent ece = (ExchangeCreatedEvent) event;
			ExchangeContextHolder.setExchangeAttributes(new ExchangeAttributes(ece.getExchange()));
		}
		
		if(event instanceof ExchangeCompletedEvent) {
			log.info("RECEIVED COMPLETE EVENT");
		}
	}

	public boolean isEnabled(EventObject event) {
		return (
			(event instanceof ExchangeCreatedEvent) ||
			(event instanceof ExchangeCompletedEvent) 
		);	
	}
	
	@Override
	protected void doStart() {
		setIgnoreCamelContextEvents(true);
		setIgnoreExchangeCompletedEvent(false);
		setIgnoreExchangeCreatedEvent(false);
		setIgnoreExchangeEvents(false);
		setIgnoreExchangeFailedEvents(true);
		setIgnoreExchangeRedeliveryEvents(true);
		setIgnoreExchangeSendingEvents(true);
		setIgnoreExchangeSentEvents(true);
		setIgnoreRouteEvents(true);
		setIgnoreServiceEvents(true);
	}

}
