package com.jyore.spring.scope.route;

import java.util.EventObject;
import java.util.UUID;

import org.apache.camel.Exchange;
import org.apache.camel.management.event.ExchangeCompletedEvent;
import org.apache.camel.management.event.ExchangeCreatedEvent;
import org.apache.camel.support.EventNotifierSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Applies the exchange to the scope
 * 
 * @see EventNotifierSupport
 * 
 * @author jyore
 * @version 1.0
 */
public class ExchangeEventNotifier extends EventNotifierSupport {

	private static final Logger log = LoggerFactory.getLogger(ExchangeEventNotifier.class);
	
	public void notify(EventObject event) throws Exception {
		if(event instanceof ExchangeCreatedEvent) {
			log.debug("RECEIVED CREATE EVENT");
			ExchangeCreatedEvent ece = (ExchangeCreatedEvent) event;
			
			Exchange exchange = ece.getExchange();
			if(exchange.getProperty(RouteScope.SCOPE_PROPERTY) == null) {
				exchange.setProperty(RouteScope.SCOPE_PROPERTY, UUID.randomUUID().toString());
			}
			
			ExchangeContextHolder.setExchangeAttributes(new ExchangeAttributes(exchange));
		}
		
		if(event instanceof ExchangeCompletedEvent) {
			log.debug("RECEIVED COMPLETE EVENT");
			ExchangeContextHolder.getExchangeAttributes().executeDesctructionCallbacks();
			ExchangeContextHolder.resetExchangeAttributes();
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
