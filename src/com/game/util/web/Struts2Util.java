package com.game.util.web;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.game.util.domain.Manage;
import com.game.util.domain.User;
import com.opensymphony.xwork2.ActionContext;

public class Struts2Util {
	private static Map<String, Object> session;
	
	public static HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}

	public static HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}

	public static ActionContext getContext() {
		return ServletActionContext.getContext();
	}
	
	public static ServletContext getServletContext() {
		return ServletActionContext.getServletContext();
	}
	
	public static String getContextPath() {
		return getRequest().getContextPath();
	}
	
	public static String getQueryString() {
		return getRequest().getQueryString();
	}
	
	public static String getRequestURL() {
		return getRequest().getRequestURL().toString();
	}

	public static String getIp() {
		return getRequest().getRemoteAddr();
	}

	@SuppressWarnings("unchecked")
	public static Map<String, Object> getApplication() {
		return getContext().getApplication();
	}

	@SuppressWarnings("deprecation")
	public static String getRealPath(String savePath) {
		return getRequest().getRealPath(savePath);
	}
	
	@SuppressWarnings("unchecked")
	public static Map<String, Object> getSession() {
		if(session == null){
			return ActionContext.getContext().getSession();
		}else {
			return session;
		}
	}
	
	public static void clearSession() {
		getSession().clear();
	}
	
	public static Object getSession(String key) {
		return getSession().get(key);
	}
	
	public static void setSession(String key, Object value) {
		getSession().put(key, value);
	}
	
	public static void removeSession(String key) {
		getSession().remove(key);
	}

	public static User getUserSession() {
		return (User) getSession().get(Constant.USER);
	}
	
	public static void setUserSession(User user) {
		getSession().put(Constant.USER, user);
	}
	
	public static void removeUserSession() {
		removeSession(Constant.USER);
	}
	
	public static Manage getManageSession() {
		return (Manage) getSession().get(Constant.MANAGE);
	}
	
	public static void setManageSession(Manage manage) {
		getSession().put(Constant.MANAGE, manage);
	}
	
	public static void removeManageSession() {
		removeSession(Constant.MANAGE);
	}
	
	
}