package com.jyore.spring.scope.exchange.example.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jyore.spring.scope.exchange.example.processor.BodyListSetter;
import com.jyore.spring.scope.exchange.example.processor.ValueChecker;
import com.jyore.spring.scope.exchange.example.processor.ValueSetter;



public class SplitRoute extends RouteBuilder {

	@Autowired
	private ValueSetter valueSetter;
	
	@Autowired
	private ValueChecker valueChecker;
	
	@Autowired
	private BodyListSetter bodyList;
	
	@Override
	public void configure() throws Exception {
		from("timer://split?fixedRate=true&period=5000")
			.bean(valueSetter,"process")
			.bean(bodyList,"process")
			.split(body().tokenize(","))
				.bean(valueChecker,"process")
		;
	}

}
