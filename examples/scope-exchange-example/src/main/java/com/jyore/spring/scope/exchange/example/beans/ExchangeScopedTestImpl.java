package com.jyore.spring.scope.exchange.example.beans;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;


@Component
@Scope(value="exchange",proxyMode=ScopedProxyMode.INTERFACES)
public class ExchangeScopedTestImpl implements ExchangeScopedTest {

	private Object internal = null;
	
	public Object get() {
		return internal;
	}

	public void set(Object o) {
		internal = o;
	}

}
