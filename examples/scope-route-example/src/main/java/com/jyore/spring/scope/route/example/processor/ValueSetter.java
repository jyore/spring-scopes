package com.jyore.spring.scope.route.example.processor;

import org.apache.camel.Exchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jyore.spring.scope.route.example.beans.RouteScopedTest;


@Component
public class ValueSetter {

	private static final Logger log = LoggerFactory.getLogger(ValueChecker.class);
	private static int counter = 0;
	
	@Autowired
	private RouteScopedTest bean;
	
	
	public void process(Exchange exchange) {
		log.info("Setting bean to {}",counter);
		bean.set(safeIncrement());
	}
	
	//Since we're going to be using a static variable here
	//that is modified from multiple threads, we need to use
	//the sync block to be safe
	private synchronized int safeIncrement() {
		return counter++;
	}
}
