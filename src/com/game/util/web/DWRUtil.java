package com.game.util.web;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import uk.ltd.getahead.dwr.WebContextFactory;

import com.game.util.domain.User;

@SuppressWarnings("deprecation")
public class DWRUtil {
	public static User getUserSession() {
		return (User) getSession(Constant.USER);
	}
	
	public static void setUserSession(Object obj) {
		setSession(Constant.USER, obj);
	}
	
	public static HttpSession session() {
		return WebContextFactory.get().getSession();
	}
	
	public static Object getSession(String key) {
		return session().getAttribute(key);
	}
	
	public static void setSession(String key,Object value) {
		session().setAttribute(key, value);
	}

	public static HttpServletRequest request() {
		return WebContextFactory.get().getHttpServletRequest();
	}

	public static ServletContext servletContext() {
		return WebContextFactory.get().getServletContext();
	}

	public static Object getBean(String name) {
		return SpringUtil.getBean(servletContext(), name);
	}
}