package com.jyore.spring.scope;

/**
 * A Contextual object for storing Scope information
 * 
 * @author jyore
 */
public interface ScopeContext {

	/**
	 * Retrieve the context data
	 * 
	 * @return The context data
	 */
	public Object get();
	
	/**
	 * Register a callback to be run when the scope is destroyed
	 * 
	 * @param name The name of the callback
	 * @param callback The callback to run
	 */
	public void registerDestructionCallback(String name, Runnable callback);
	
	/**
	 * Execute all destruction callbacks
	 */
	public void executeDesctructionCallbacks();
}
