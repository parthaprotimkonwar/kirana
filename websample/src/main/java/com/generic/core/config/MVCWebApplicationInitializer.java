package com.generic.core.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class MVCWebApplicationInitializer implements WebApplicationInitializer{

	public static final String DISPATCHER_SERVLET_NAME = "dispatcher";
	public static final String DISPATCHER_SERVLET_MAPPING = "/";
	
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {

		//Registration of a Spring Application Context
		AnnotationConfigWebApplicationContext annotatedRootApplicationContext = new AnnotationConfigWebApplicationContext();
		annotatedRootApplicationContext.register(ApplicationContext.class);
		
		//Registration of a Web Application Context
		ServletRegistration.Dynamic dispacher = servletContext.addServlet(DISPATCHER_SERVLET_NAME, new DispatcherServlet(annotatedRootApplicationContext));
		dispacher.setLoadOnStartup(1);
		dispacher.addMapping(DISPATCHER_SERVLET_MAPPING);
		
		//Setting up a Spring Application Context Listener
		servletContext.addListener(new ContextLoaderListener(annotatedRootApplicationContext));
	}
}
