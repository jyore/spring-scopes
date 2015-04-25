package com.jyore.spring.scope.route;

import org.springframework.core.NamedThreadLocal;

import com.jyore.spring.scope.ScopeContext;
import com.jyore.spring.scope.ScopeContextHolder;


/**
 * Holds Exchange contexts
 * 
 * @see ScopeContextHolder
 * @author jyore
 */
public class ExchangeContextHolder implements ScopeContextHolder {
	private static final ExchangeContextHolder instance = new ExchangeContextHolder();
	private static final ThreadLocal<ExchangeAttributes> attributeHolder = new NamedThreadLocal<ExchangeAttributes>("Exchange Context");
	
	private ExchangeContextHolder() {}
	
	
	public static ExchangeContextHolder instance() {
		return instance;
	}
	
	@Override
	public ScopeContext getContext() {
		ExchangeAttributes attributes = attributeHolder.get();
		
		if(attributes == null) {
			throw new IllegalStateException("No thread-bound exchange found");
		}
		
		return attributes;
	}

	@Override
	public void setContext(ScopeContext context) {
		if(context == null) {
			resetContext();
		} else {
			attributeHolder.set((ExchangeAttributes) context);
		}
	}

	@Override
	public void resetContext() {
		attributeHolder.remove();
	}

}
