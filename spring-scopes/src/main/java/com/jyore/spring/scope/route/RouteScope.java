package com.jyore.spring.scope.route;

import org.apache.camel.Exchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jyore.spring.scope.AbstractScope;
import com.jyore.spring.scope.ScopeContext;


/**
 *  
 * Scope a bean by a route's execution. Beans are bound by the initial exchange 
 * and maintained even through splits and aggregations.
 * 
 * @see AbstractScope
 * @author jyore
 */
public class RouteScope extends AbstractScope {

	public static final String SCOPE_PROPERTY = "RouteScopeId";
	
	private static final Logger log = LoggerFactory.getLogger(RouteScope.class);
	private static final String REFERENCE = "route";
	
	@Override
	public String getConversationId() {
		String id = ((Exchange) getScopeContext().get()).getProperty(SCOPE_PROPERTY).toString();
		log.debug("Scope Bound with Conversation from Exchange w/ scope id - {}",id);
		return id;
	}
	
	@Override
	public Object resolveContextualObject(String name) {
		return REFERENCE.equals(name);
	}

	@Override
	protected ScopeContext getScopeContext() {
		return ExchangeContextHolder.instance().getContext();
	}
}
