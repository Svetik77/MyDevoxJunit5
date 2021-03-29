package org.craftsrecords.rememberme.junit4.rest;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;

import org.craftsrecords.rememberme.rest.BookmarkPayload;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

public class MyResolver implements ParameterResolver {

	@Override
	public boolean supportsParameter(ParameterContext parameterContext, 
			ExtensionContext extensionContext)
			throws ParameterResolutionException {
		 
		return parameterContext.getParameter().getType() == BookmarkPayload.class;
	}

	@Override
	public Object resolveParameter(ParameterContext parameterContext, 
			ExtensionContext extensionContext)
			throws ParameterResolutionException {
		if(parameterContext.isAnnotated(MyFake.class)) {
			return  new BookmarkPayload(
	                "invalid://url.com",
	                "An invalid link",
	                emptyList() );
		}
		 
		return new BookmarkPayload(
	            "http://www.test.com",
	            "A test link",
	            singletonList("good-stuff"));
	}

}
