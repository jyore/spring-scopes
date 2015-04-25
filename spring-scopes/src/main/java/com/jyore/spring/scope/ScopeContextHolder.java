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
	 */
	public ScopeContext getContext();
	
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
