package com.jyore.spring.scope.exchange.example.beans;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;


@Component
@Scope(value="exchange",proxyMode=ScopedProxyMode.INTERFACES)
public class ExchangeScopedStringImpl implements ExchangeScopedString {

	private String value = null;
	
	public String getValue() {
		return value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
}
