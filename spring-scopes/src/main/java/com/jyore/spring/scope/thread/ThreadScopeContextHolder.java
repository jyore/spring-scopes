package com.jyore.spring.scope.thread;

import org.springframework.core.NamedThreadLocal;

import com.jyore.spring.scope.ScopeContext;
import com.jyore.spring.scope.ScopeContextHolder;

public class ThreadScopeContextHolder implements ScopeContextHolder {

	private static final ThreadScopeContextHolder instance = new ThreadScopeContextHolder();
	private static final ThreadLocal<ScopeContext> holder = new NamedThreadLocal<ScopeContext>("Thread Context");
	

	private ThreadScopeContextHolder() {}
	
	
	public static ThreadScopeContextHolder instance() {
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
