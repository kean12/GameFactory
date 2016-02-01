package com.game.util.system.interceptor;

import com.game.util.web.Struts2Util;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class SysAuthorityInterceptor extends AbstractInterceptor {
	private static final long serialVersionUID = -329776006858559856L;

	public String intercept(ActionInvocation invocation) throws Exception {
		Object manage = (Object) Struts2Util.getManageSession();
		if (manage != null) {
			return invocation.invoke();
		} else {
			// Manage m = new Manage();
			// m.setId(1L);
			// m.setName("系统管理员");
			// m.setPassword("21232F297A57A5A743894A0E4A801FC3");
			// m.setUsername("root");
			// m.setIsuse(1);
			// session.put("manage", manage);
			// return invocation.invoke();
			return "login";
		}
	}
}