package com.game.bizinfo.action;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.game.util.domain.BizInfo;

/**
 * 自定义属性
 */
public class CustomInfo {
	private BizInfo bizInfo;
	private String content;// 内容
	private Integer type;// 是否上传图片0.否 1.是
	private Integer pwdType;// 是否上传密保卡
	private List<File> file;// 上传文件
	private List<String> fileFileName;// 上传文件名
	private Map<String, String> map;// 动态属性内容
	private Map<String, Boolean> error;// 错误信息

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
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

	public BizInfo getBizInfo() {
		return bizInfo;
	}

	public void setBizInfo(BizInfo bizInfo) {
		this.bizInfo = bizInfo;
	}

	public Integer getPwdType() {
		return pwdType;
	}

	public void setPwdType(Integer pwdType) {
		this.pwdType = pwdType;
	}
}
