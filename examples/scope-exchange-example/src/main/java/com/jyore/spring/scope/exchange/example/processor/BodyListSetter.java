package com.jyore.spring.scope.exchange.example.processor;

import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;


@Component
public class BodyListSetter {

	private static final String list = "A,B,C";
	
	public void process(Exchange exchange) {
		exchange.getIn().setBody(list);
	}
}
