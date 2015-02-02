package com.jyore.spring.scope.route.example.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jyore.spring.scope.route.example.processor.ValueChecker;
import com.jyore.spring.scope.route.example.processor.ValueSetter;


@Component
public class SimpleRoute extends RouteBuilder {

	@Autowired
	private ValueSetter valueSetter;
	
	@Autowired
	private ValueChecker valueChecker;
	
	@Override
	public void configure() throws Exception {
		from("timer://simple?fixedRate=true&period=6000")
			.bean(valueSetter,"process")
			.delay(1000)
			.bean(valueChecker,"process")
		;
	}
}
