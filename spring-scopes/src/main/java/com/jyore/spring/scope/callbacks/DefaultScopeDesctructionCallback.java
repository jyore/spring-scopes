package com.jyore.spring.scope.callbacks;

import org.springframework.beans.factory.config.Scope;

import com.jyore.spring.scope.AbstractScopeDestructionCallback;


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
