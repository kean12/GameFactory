package com.game.util.admin.system.action;

import com.game.util.base.action.BaseAction;
import com.game.util.web.DatabaseConfig;
import com.game.util.web.Validator;

public class DatabaseAction extends BaseAction {
	private static final long serialVersionUID = 5542415256790375271L;
	private String dialect;
	private String driver_class;
	private String username;
	private String password;
	private String url;
	
	public String database() throws Exception {
		return "database";
	}

	public String save() throws Exception {
		if (!Validator.isBlank(dialect)) {
			DatabaseConfig.write("hibernate.dialect", dialect);
		}
		
		if (!Validator.isBlank(driver_class)) {
			DatabaseConfig.write("hibernate.connection.driver_class", driver_class);
		}
		
		if (!Validator.isBlank(username)) {
			DatabaseConfig.write("hibernate.connection.username", username);
		}
		
		if (!Validator.isBlank(password)) {
			DatabaseConfig.write("hibernate.connection.password", password);
		}
		
		if (!Validator.isBlank(url)) {
			DatabaseConfig.write("hibernate.connection.url", url);
		}
		
		DatabaseConfig.write("TIMEOUT", "3");
		super.setIsSuccess(true);
		return SUCCESS;
	}

}
