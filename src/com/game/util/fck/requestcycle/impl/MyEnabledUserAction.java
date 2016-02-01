package com.game.util.fck.requestcycle.impl;

import javax.servlet.http.HttpServletRequest;

import net.fckeditor.requestcycle.UserAction;

import com.game.util.domain.Manage;
import com.game.util.domain.User;
import com.game.util.web.Constant;

public class MyEnabledUserAction implements UserAction {
	private Manage manage ;
	private User user ;
	public boolean isCreateFolderEnabled(HttpServletRequest request) {
		setValue(request);
		return (manage != null || user != null);
	}

	public boolean isEnabledForFileBrowsing(HttpServletRequest request) {
		setValue(request);
		return (manage != null || user != null);
	}
	public boolean isEnabledForFileUpload(HttpServletRequest request) {
		setValue(request);
		return (manage != null || user != null);
	}
	private void setValue(HttpServletRequest request){
		manage = (Manage) MyContextPathBuilder.getSession(request, Constant.MANAGE);
		user = (User) MyContextPathBuilder.getSession(request, Constant.USER) ;
	}
}
