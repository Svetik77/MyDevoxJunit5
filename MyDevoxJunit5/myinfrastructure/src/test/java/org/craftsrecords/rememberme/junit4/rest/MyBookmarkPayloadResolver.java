package org.craftsrecords.rememberme.junit4.rest;

import static java.util.Collections.singletonList;

import org.craftsrecords.rememberme.rest.BookmarkPayload;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

public class MyBookmarkPayloadResolver implements ParameterResolver {

	/**
	 * on which my resolver  will be apply
	 * 
	 */
	@Override
	public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
			throws ParameterResolutionException {
	// What class apply 
		return parameterContext.getParameter().getType() == BookmarkPayload.class;
	}

	/**
	 * provide value I want to inject in my test
	 */
	@Override
	public Object resolveParameter(ParameterContext parameterContext,
			       ExtensionContext extensionContext)
			throws ParameterResolutionException {
		 
		return  new BookmarkPayload(
	            "http://www.test.com",
	            "A test link",
	            singletonList("good-stuff")
	    );
	}

}
