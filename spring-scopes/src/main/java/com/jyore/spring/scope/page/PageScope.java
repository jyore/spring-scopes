package com.jyore.spring.scope.page;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jyore.spring.scope.AbstractScope;
import com.jyore.spring.scope.ScopeContext;

/**
 * Scope a bean by it's page or even by a page with URI parameters
 * 
 * <p>
 *     Practical use cases for this scope:
 *     <ul>
 *         <li> Per-page caching of data </li>
 *         <li> Allow multiple users to edit a page simultaneously (like Google Docs) </li>
 *     </ul>
 * </p>
 * 
 * @see AbstractScope
 * @author jyore
 */
public class PageScope extends AbstractScope {

	private static final Logger log = LoggerFactory.getLogger(PageScope.class);
	private static final String REFERENCE = "page";

	@Override
	public String getConversationId() {
		String id = (String) getScopeContext().get();
		log.debug("Scope Bound with Conversation from Page w/ scope id - {}",id);
		return id;
	}

	@Override
	public Object resolveContextualObject(String name) {
		return REFERENCE.equals(name);
	}
	
	@Override
	protected ScopeContext getScopeContext() {
		return PageScopeContextHolder.instance().getContext();
	}

}
