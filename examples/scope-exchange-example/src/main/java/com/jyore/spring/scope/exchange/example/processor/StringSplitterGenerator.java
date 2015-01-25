package com.jyore.spring.scope.exchange.example.processor;

import java.util.Random;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Component
public class StringSplitterGenerator {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(StringSplitterGenerator.class);

	public void process(Exchange exchange) {
		Message in = exchange.getIn();
		
		StringBuilder builder = new StringBuilder();
		Random len = new Random();
		
		int l = len.nextInt(10)+1;
		
		for(int i=0;i<l;++i) {
			builder.append(i);
			
			if(i != l-1) {
				builder.append(",");
			}
		}
		
		LOGGER.info("Build Splittable String - {}",builder.toString());
		in.setBody(builder.toString());
	}
}
