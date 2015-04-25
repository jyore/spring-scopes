package com.jyore.spring.scope;


/**
 * Interface defining a structure to hold {@link ScopeContext}
 * 
 * @author jyore
 */
public interface ScopeContextHolder {

	/**
	 * Get the stored context
	 * 
	 * @return The {@link ScopeContext} object stored in the holder
	 * @throws IllegalStateException When the context could not be accessed
	 */
	public ScopeContext getContext() throws IllegalStateException;
	
	/**
	 * Set the {@link ScopeContext} to store
	 * 
	 * @param context The {@link ScopeContext} to store
	 */
	public void setContext(ScopeContext context);
	
	/**
	 * Reset/Clear the context being stored
	 */
	public void resetContext();
}
