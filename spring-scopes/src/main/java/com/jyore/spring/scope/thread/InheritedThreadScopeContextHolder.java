package com.jyore.spring.scope.thread;

import com.jyore.spring.scope.ScopeContext;
import com.jyore.spring.scope.ScopeContextHolder;


/**
 * Holds {@link ThreadScopeContext} and provides access to them
 * 
 * @see ScopeContextHolder
 * @author jyore
 */
public class InheritedThreadScopeContextHolder implements ScopeContextHolder {

	private static final InheritedThreadScopeContextHolder instance = new InheritedThreadScopeContextHolder();
	private static final InheritableThreadLocal<ScopeContext> holder = new InheritableThreadLocal<ScopeContext>();
	

	private InheritedThreadScopeContextHolder() {}
	
	/**
	 * Retrieve the instance to the singleton
	 * 
	 * @return The {@link InheritedThreadScopeContextHolder} singleton instance
	 */
	public static InheritedThreadScopeContextHolder instance() {
		return instance;
	}
	
	
	@Override
	public ScopeContext getContext() throws IllegalStateException {
		ScopeContext ctx = holder.get();
		
		if(ctx == null) {
			ctx = new ThreadScopeContext();
			holder.set(ctx);
		}
		
		return ctx;
	}

	@Override
	public void setContext(ScopeContext context) {
		if(context == null) {
			resetContext();
		} else {
			holder.set(context);
		}
	}

	@Override
	public void resetContext() {
		holder.remove();
	}

}
