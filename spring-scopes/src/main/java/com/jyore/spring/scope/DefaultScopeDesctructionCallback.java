package com.jyore.spring.scope;

import org.springframework.beans.factory.config.Scope;


/**
 * Basic functionality needed to destroy a bean in a {@link Scope}
 * 
 * @see AbstractScopeDestructionCallback
 * @author jyore
 */
public class DefaultScopeDesctructionCallback extends AbstractScopeDestructionCallback {

	public DefaultScopeDesctructionCallback(Scope scope, String name) {
		super(scope, name);
	}
	
}
