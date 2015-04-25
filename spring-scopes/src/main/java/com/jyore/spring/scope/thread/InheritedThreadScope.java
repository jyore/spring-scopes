package com.jyore.spring.scope.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jyore.spring.scope.AbstractScope;
import com.jyore.spring.scope.ScopeContext;

/**
 * Provides bean scoping per thread and child threads
 * 
 * @see AbstractScope
 * @author jyore
 */
public class InheritedThreadScope extends AbstractScope {

	private static final Logger log = LoggerFactory.getLogger(InheritedThreadScope.class);
	private static final String REFERENCE = "thread-inherit";
	
	
	@Override
	protected ScopeContext getScopeContext() {
		return InheritedThreadScopeContextHolder.instance().getContext();
	}

	@Override
	public String getConversationId() {
		String id = (String) getScopeContext().get();
		log.debug("Scope Bound with Conversation from Inheritable Thread w/ scope id - {}",id);
		return id;
	}

	@Override
	public Object resolveContextualObject(String name) {
		return REFERENCE.equals(name);
	}

}
