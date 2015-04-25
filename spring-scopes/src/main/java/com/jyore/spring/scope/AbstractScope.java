package com.jyore.spring.scope;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;

/**
 * Abstract implementation of an {@link Scope}
 * 
 * @author jyore
 */
@SuppressWarnings("rawtypes")
public abstract class AbstractScope implements Scope {

	protected final Logger log = LoggerFactory.getLogger(AbstractScope.class);
	protected final Map scope = new HashMap();
	protected final Class<? extends AbstractScopeDestructionCallback> defaultDestructionCallback = DefaultScopeDesctructionCallback.class;
	protected final ObjectFactory MAP_FACTORY = new ObjectFactory() {
		public Object getObject() {
			return new HashMap();
		}
	};
	
	protected abstract ScopeContext getScopeContext();
	public abstract String getConversationId();
	public abstract Object resolveContextualObject(String arg0);
	
	
	
	public Object get(String name, ObjectFactory<?> factory) {
		log.debug("Retrieving bean {}",name);
		Map beans = (Map) _get(scope,getConversationId(),MAP_FACTORY,false);
		return _get(beans,name,factory,true);
	}
	
	public void registerDestructionCallback(String name, Runnable callback) {
		log.debug("Registering destruction callback to bean {}",name);
		getScopeContext().registerDestructionCallback(name, callback);
	}
	
	public Object remove(String name) {
		log.debug("Removing bean {}",name);
		Map beans = (Map) _get(scope,getConversationId(),MAP_FACTORY,false);
		return beans.remove(name);
	}
	
	@SuppressWarnings("unchecked")
	protected Object _get(Map map, String name, ObjectFactory factory, boolean registerCb) {
		Object o = map.get(name);
		if(o == null) {
			o = factory.getObject();
			map.put(name, o);
			
			if(registerCb && defaultDestructionCallback != null) {
				try {
					Constructor c = defaultDestructionCallback.getConstructor(Scope.class,String.class);
					registerDestructionCallback(name, (Runnable) c.newInstance(this,name));
				} catch(Exception e) {
					log.error("Could not setup destruction callback: " + name, e);
				}
			}
		}
		return o;
	}
	
}
