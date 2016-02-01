package com.game.bizinfo.action;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * 账号信息
 */
public class AccountInfo {
	private String account;
	private String r_account;
	private String password;
	private String r_password;
	private String content;
	private Integer identityType;
	private List<File> file;
	private List<String> fileFileName;
	private Map<String, String> map;
	private Map<String, Boolean> error;

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getR_account() {
		return r_account;
	}

	public void setR_account(String rAccount) {
		r_account = rAccount;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getR_password() {
		return r_password;
	}

	public void setR_password(String rPassword) {
		r_password = rPassword;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getIdentityType() {
		return identityType;
	}

	public void setIdentityType(Integer identityType) {
		this.identityType = identityType;
	}

	public Map<String, String> getMap() {
		return map;
	}

	public void setMap(Map<String, String> map) {
		this.map = map;
	}

	public Map<String, Boolean> getError() {
		return error;
	}

	public void setError(Map<String, Boolean> error) {
		this.error = error;
	}

	public List<File> getFile() {
		return file;
	}

	public void setFile(List<File> file) {
		this.file = file;
	}

	public List<String> getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(List<String> fileFileName) {
		this.fileFileName = fileFileName;
	}

}
