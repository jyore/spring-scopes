package com.jyore.spring.scope.page;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;


/**
 * {@link Filter} implementation to manage setting the correct {@link PageScopeContext} 
 * for the requested page
 * 
 * @see Filter
 * @author jyore
 */
public class PageScopeFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		String page = null;
		if(request instanceof HttpServletRequest) {
			page = ((HttpServletRequest) request).getRequestURI();
		}
		
		PageScopeContextHolder.instance().setContext(new PageScopeContext(page));
		
		
		try {
			chain.doFilter(request, response);
		} finally {
			PageScopeContextHolder.instance().resetContext();
		}
	}

	@Override
	public void init(FilterConfig config) throws ServletException {

	}
	
	@Override
	public void destroy() {

	}

}
