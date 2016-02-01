package com.game.util.web;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;


public class SpringUtil {
	
	public static Object getBean(String name) {
		return getWebApplicationContext().getBean(name);
	}
	
	public static Object getBean(ServletContextEvent event, String name) {
		return getWebApplicationContext(event.getServletContext()).getBean(name);
	}
	
	public static Object getBean(ServletContext servletContext, String name) {
		return getWebApplicationContext(servletContext).getBean(name);
	}
	
	
	protected static WebApplicationContext getWebApplicationContext(ServletContext servletContext) {
		return WebApplicationContextUtils.getWebApplicationContext(servletContext);
	}
	
	protected static WebApplicationContext getWebApplicationContext() {
		return WebApplicationContextUtils.getWebApplicationContext(Struts2Util.getServletContext());
	}
	
}
