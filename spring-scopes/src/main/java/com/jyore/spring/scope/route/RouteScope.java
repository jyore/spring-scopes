package com.jyore.spring.scope.route;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;


/**
 *  
 * 
 * 
 * {@see Scope}
 * 
 * @author jyore
 * @version 1.0
 */
@SuppressWarnings("rawtypes")
public class RouteScope implements Scope {

	public static final String SCOPE_PROPERTY = "ExchangeScopeId";
	
	private static final Logger log = LoggerFactory.getLogger(RouteScope.class);
	private static final String REFERENCE = "route";
	private static final Map scope = new HashMap();
	private static final ObjectFactory MAP_FACTORY = new ObjectFactory() {
		public Object getObject() {
			return new HashMap();
		}
	};
	
	
	public String getConversationId() {
		String id = ExchangeContextHolder.getExchangeAttributes().getExchange().getProperty(SCOPE_PROPERTY).toString();
		log.debug("Scope Bound with Conversation from Exchange w/ scope id - {}",id);
		return id;
	}
	
	public Object resolveContextualObject(String name) {
		return REFERENCE.equals(name);
	}
	
	public synchronized Object get(String name,  ObjectFactory factory) {
		log.debug("Retrieving bean {}",name);
		Map beans = (Map) _get(scope,getConversationId(),MAP_FACTORY);
		return _get(beans,name,factory);
	}

	public Object remove(String name) {
		log.debug("Removing bean {}",name);
		Map beans = (Map) scope.get(name);
		return (beans == null ? null : beans.remove(name));
	}

	public void registerDestructionCallback(String name, Runnable callback) {
		log.debug("Registering destruction callback to bean {}",name);
		ExchangeAttributes attributes = ExchangeContextHolder.getExchangeAttributes();
		attributes.registerDestructionCallback(name, callback);
	}
	
	@SuppressWarnings("unchecked")
	private Object _get(Map map, String name, ObjectFactory factory) {
		Object o = map.get(name);
		if(o == null) {
			o = factory.getObject();
			map.put(name, o);
		}
		return o;
	}
}
