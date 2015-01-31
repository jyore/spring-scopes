package com.jyore.spring.scope.route;

import org.springframework.core.NamedThreadLocal;


/**
 * Holds Exchange contexts
 * 
 * @author jyore
 * @version 1.0
 */
public class ExchangeContextHolder {

	private static final ThreadLocal<ExchangeAttributes> attributeHolder = new NamedThreadLocal<ExchangeAttributes>("Exchange Context");
	

	/**
	 * Resets the thread-local attributes
	 */
	public static void resetExchangeAttributes() {
		attributeHolder.remove();
	}
	
	/**
	 * Set the exchange attributes
	 * 
	 * @param attributes
	 */
	public static void setExchangeAttributes(ExchangeAttributes attributes) {
		if(attributes == null) {
			resetExchangeAttributes();
		} else {
			attributeHolder.set(attributes);
		}
	}
	
	/**
	 * Retrives the exchange attributes
	 * 
	 * @return The exchange attributes
	 * @throws IllegalStateException When no thread-bound exchange could be found
	 */
	public static ExchangeAttributes getExchangeAttributes() throws IllegalStateException {
		ExchangeAttributes attributes = attributeHolder.get();
		
		if(attributes == null) {
			throw new IllegalStateException("No thread-bound exchange found");
		}
		
		return attributes;
	}

}
