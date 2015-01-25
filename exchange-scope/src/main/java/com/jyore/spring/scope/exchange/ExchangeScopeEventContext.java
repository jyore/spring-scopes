package com.jyore.spring.scope.exchange;

import org.apache.camel.Endpoint;
import org.apache.camel.Exchange;
import org.apache.camel.management.event.ExchangeSendingEvent;

public class ExchangeScopeEventContext extends ExchangeSendingEvent {

	private static final long serialVersionUID = 4792765506855655268L;
	private static ThreadLocal<String> conversation = new ThreadLocal<String>();
	
	
	public ExchangeScopeEventContext(Exchange source, Endpoint endpoint) {
		super(source, endpoint);
		conversation.set(source.getExchangeId());
	}

	public static String getConversationId() {
		return conversation.get();
	}

	public static void clearConversation() {
		conversation.remove();
	}
}
