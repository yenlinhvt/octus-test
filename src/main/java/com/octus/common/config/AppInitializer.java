package com.octus.common.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	protected Class[] getRootConfigClasses() {
		return new Class[] { AppConfig.class };
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	protected Class[] getServletConfigClasses() {
		return null;
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

}
