package com.jyore.spring.scope.thread;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jyore.spring.scope.ScopeContext;


/**
 * Contextual information to identify a thread for scoping
 * 
 * @see ScopeContext
 * @author jyore
 */
public class ThreadScopeContext implements ScopeContext {

	private static final Logger log = LoggerFactory.getLogger(ThreadScopeContext.class);
	private static final String DESTRUCTION_CB_PREFIX = ThreadScopeContext.class.getName() + ".DESTRUCTION_CALLBACK.";
	private final Map<String,Runnable> destructionCallbacks = new HashMap<String,Runnable>();
	private final String name;
	
	
	public ThreadScopeContext() {
		this.name = Thread.currentThread().getName();
	}
	
	@Override
	public Object get() {
		return name;
	}

	@Override
	public void registerDestructionCallback(String name, Runnable callback) {
		synchronized (this.destructionCallbacks) {
			destructionCallbacks.put(DESTRUCTION_CB_PREFIX + name, callback);
		}
	}

	@Override
	public void executeDesctructionCallbacks() {
		synchronized (this.destructionCallbacks) {
			for(String cb : destructionCallbacks.keySet()) {
				log.debug("Executing destruction callback: " + cb);
				destructionCallbacks.get(cb).run();
			}
			destructionCallbacks.clear();
		}
	}

}
