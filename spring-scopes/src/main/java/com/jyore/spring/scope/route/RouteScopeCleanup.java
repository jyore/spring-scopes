package com.jyore.spring.scope.route;


public class RouteScopeCleanup implements Runnable {

	private final RouteScope scope;
	private final String name;
	
	public RouteScopeCleanup(RouteScope scope, String name) {
		this.scope = scope;
		this.name = name;
	}

	public void run() {
		scope.remove(name);
	}
	
}
