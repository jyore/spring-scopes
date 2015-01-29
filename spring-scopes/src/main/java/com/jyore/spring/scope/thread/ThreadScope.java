package com.jyore.spring.scope.thread;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;

@SuppressWarnings("rawtypes")
public class ThreadScope implements Scope {

	private final Map<String,List<Runnable>> callbacks = new HashMap<String,List<Runnable>>();
	private final ThreadLocal context = new ThreadLocal() {
		protected Object initialValue() {
			return new HashMap();
		}
	};


	@SuppressWarnings("unchecked")
	public Object get(String name, ObjectFactory factory) {
		Map scope = (Map) context.get();
		Object object = scope.get(name);
		if(object == null) {
			object = factory.getObject();
			scope.put(name, object);
			callbacks.put(name, new ArrayList<Runnable>());
		}
		
		return object;
	}

	public String getConversationId() {
		return null;
	}

	public void registerDestructionCallback(String name, Runnable callback) {
		callbacks.get(name).add(callback);
	}

	public Object remove(String name) {
		Map scope = (Map) context.get();
		
		for(Runnable callback : callbacks.get(name)) {
			callback.run();
		}
		
		return scope.remove(name);
	}

	public Object resolveContextualObject(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}}
