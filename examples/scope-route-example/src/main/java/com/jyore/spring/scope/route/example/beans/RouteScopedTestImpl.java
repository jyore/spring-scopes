package com.jyore.spring.scope.route.example.beans;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;


@Component
@Scope(value="route",proxyMode=ScopedProxyMode.INTERFACES)
public class RouteScopedTestImpl implements RouteScopedTest {

	private Object internal = null;
	
	public Object get() {
		return internal;
	}

	public void set(Object o) {
		internal = o;
	}

}
