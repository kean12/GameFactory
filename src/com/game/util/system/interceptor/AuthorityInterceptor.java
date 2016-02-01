package com.game.util.system.interceptor;

import com.game.util.web.Struts2Util;
import com.game.util.web.Validator;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class AuthorityInterceptor extends AbstractInterceptor {
	private static final long serialVersionUID = -3907473021615143040L;

	public String intercept(ActionInvocation invocation) throws Exception {
		Object user = Struts2Util.getUserSession();
		if (user != null) {
			return invocation.invoke();
		} else {
			String url;
			if (Validator.isBlank(Struts2Util.getQueryString()))
				url =Struts2Util.getRequestURL();
			else
				url = Struts2Util.getRequestURL()
						+ "?"
						+ Struts2Util.getQueryString();
			Struts2Util.setSession("url", url);
			return "login";
		}

	}
}