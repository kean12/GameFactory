package com.game.util.fck.requestcycle.impl;

import javax.servlet.http.HttpServletRequest;

import net.fckeditor.handlers.PropertiesLoader;
import net.fckeditor.requestcycle.impl.ContextPathBuilder;

import com.game.util.domain.Manage;
import com.game.util.domain.User;
import com.game.util.web.Constant;
import com.game.util.web.Validator;

public class MyContextPathBuilder extends ContextPathBuilder {
	
	@Override
	public String getUserFilesPath(HttpServletRequest request) {
		String domain = request.getRequestURL().toString();
		domain = domain.substring(0, domain.indexOf("/plugins/")) ;
		
		Manage manage = (Manage) getSession(request, Constant.MANAGE);
		User user = (User) getSession(request, Constant.USER) ;
		
		if( manage != null && manage.getRole() != null){
			return domain + PropertiesLoader.getProperty("connector.manageFilesPath").replaceAll("\\{0\\}", manage.getUsername() );
		}else if( user != null ){
			return domain + PropertiesLoader.getProperty("connector.userFilesPath").replaceAll("\\{0\\}", user.getUsername() ) ;
		}
		return null ;
	}

	@Override
	public String getUserFilesAbsolutePath(HttpServletRequest request) {
		Manage manage = (Manage) getSession(request, Constant.MANAGE);
		User user = (User) getSession(request, Constant.USER) ;
		
		if( manage != null && manage.getRole() != null){
			return PropertiesLoader.getProperty("connector.manageFilesPath").replaceAll("\\{0\\}", manage.getUsername() );
		}else if( user != null ){
			return PropertiesLoader.getProperty("connector.userFilesPath").replaceAll("\\{0\\}", user.getUsername() ) ;
		}
		return null ;
	}
	
	public static Object getSession(HttpServletRequest request , String name){
		if(request == null || Validator.isBlank(name)) return null ;
		return request.getSession().getAttribute(name) ;
	}
}
