package com.jyore.spring.scope.thread;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;

public class SimpleInheritedThreadScope implements Scope {

    private static final Logger log = LoggerFactory.getLogger(SimpleInheritedThreadScope.class);

    private final ThreadLocal<Map<String, Object>> threadScope = new InheritableThreadLocal<Map<String, Object>>();

    public Object get(String name, ObjectFactory<?> objectFactory) {

        Map<String, Object> scope = getScope();

        Object object = scope.get(name);
        if (object == null) {
            object = objectFactory.getObject();
            scope.put(name, object);
        }
        return object;
    }

    public Object remove(String name) {
        Map<String, Object> scope = this.threadScope.get();
        return scope.remove(name);
    }

    public void registerDestructionCallback(String name, Runnable callback) {
        log.warn("SimpleInheritedThreadScope does not support destruction callbacks. " + "Consider using RequestScope in a web environment.");
    }

    public Object resolveContextualObject(String key) {
        return null;
    }

    public String getConversationId() {
        return Thread.currentThread().getName();
    }
    
    
    private Map<String,Object> getScope() {
    	Map<String, Object> scope = this.threadScope.get();

        if (scope == null) {
            this.threadScope.set(new HashMap<String, Object>());
            scope = this.threadScope.get();
        }
        
        return scope;
    }

}
