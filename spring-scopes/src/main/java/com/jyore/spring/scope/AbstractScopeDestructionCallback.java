package com.jyore.spring.scope;

import org.springframework.beans.factory.config.Scope;

/**
 * Callback to register in a {@link Scope} to run when a bean is destroyed
 * 
 * @see Runnable
 * @author jyore
 */
public abstract class AbstractScopeDestructionCallback implements Runnable {

	private final Scope scope;
	private final String name;
	
	/**
	 * Construct the Callback with the {@link Scope} and bean name
	 * 
	 * @param scope The {@link Scope} the destruction callback runs under
	 * @param name The name of the bean
	 */
	public AbstractScopeDestructionCallback(Scope scope, String name) {
		this.scope = scope;
		this.name = name;
	}
	
	/**
	 * Removes the bean from the scope
	 */
	public void run() {
		scope.remove(name);
	}
}
