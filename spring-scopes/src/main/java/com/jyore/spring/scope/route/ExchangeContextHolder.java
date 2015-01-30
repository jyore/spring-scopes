package com.jyore.spring.scope.route;

import org.springframework.core.NamedThreadLocal;



public class ExchangeContextHolder {

	private static final ThreadLocal<ExchangeAttributes> attributeHolder = new NamedThreadLocal<ExchangeAttributes>("Exchange Context");
	

	public static void resetExchangeAttributes() {
		attributeHolder.remove();
	}
	
	public static void setExchangeAttributes(ExchangeAttributes attributes) {
		if(attributes == null) {
			resetExchangeAttributes();
		} else {
			attributeHolder.set(attributes);
		}
	}
	
	public static ExchangeAttributes getExchangeAttributes() throws IllegalStateException {
		ExchangeAttributes attributes = attributeHolder.get();
		
		if(attributes == null) {
			throw new IllegalStateException("No thread-bound exchange found");
		}
		
		return attributes;
	}

}
