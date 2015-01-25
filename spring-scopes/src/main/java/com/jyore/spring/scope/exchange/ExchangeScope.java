package com.jyore.spring.scope.exchange;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;


@SuppressWarnings("rawtypes")
public class ExchangeScope implements Scope {

	private static final Logger LOGGER = LoggerFactory.getLogger(ExchangeScope.class);
	private final Map scope = new HashMap();
	
	
	private static final ObjectFactory MAP_FACTORY = new ObjectFactory() {
		public Object getObject() {
			return new HashMap();
		}
	};
	
	@SuppressWarnings("unchecked")
	private Object get(Map map, String name, ObjectFactory factory) {
		Object bean = map.get(name);
		if(bean == null) {
			bean = factory.getObject();
			map.put(name, bean);
		}
		return bean;
	}
	
	public Object get(String name, ObjectFactory factory) {
		Map beans = (Map) get(scope,getConversationId(),MAP_FACTORY);
		return get(beans,name,factory);
	}
	
	//Might need to clear threadlocal from ExchangeScopeEvent here if there are no more beans in thread??
	public Object remove(String name) {
		Map beans = (Map) get(scope,getConversationId(),MAP_FACTORY);
		return beans == null ? null : beans.remove(name);
	}

	public String getConversationId() {
		LOGGER.info("Conversation String: " + ExchangeScopeEventContext.getConversation());
		return ExchangeScopeEventContext.getConversation();
	}

	public void registerDestructionCallback(String arg0, Runnable arg1) {
		// TODO Auto-generated method stub
		
	}

	public Object resolveContextualObject(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
