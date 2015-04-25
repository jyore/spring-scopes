package com.jyore.spring.scope.page;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jyore.spring.scope.ScopeContext;


/**
 * Holds the page name
 * 
 * @see ScopeContext
 * @author jyore
 */
public class PageScopeContext implements ScopeContext {
	private static final Logger log = LoggerFactory.getLogger(PageScopeContext.class);
	private static final String DESTRUCTION_CB_PREFIX = PageScopeContext.class.getName() + ".DESTRUCTION_CALLBACK.";
	private final Map<String,Runnable> destructionCallbacks = new HashMap<String,Runnable>();
	private volatile String page;
	
	/**
	 * Create an instance around a page
	 * 
	 * @param page The name of the page to bind to
	 */
	public PageScopeContext(String page) {
		this.page = page;
	}
	
	@Override
	public Object get() {
		return page;
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
