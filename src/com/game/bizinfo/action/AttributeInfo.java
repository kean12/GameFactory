package com.game.bizinfo.action;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * 账号交易其他属性
 */
public class AttributeInfo {
	private String professionName;// 职业
	private String sex; // 性别
	private String grade; // 等级
	private String title1;// 标题1
	private String title2;// 标题2
	private String price;// 价格
	private String qq; // 联系QQ
	private String phoneNum;// 联系电话
	private String content;// 内容
	private Integer type;// 是否上传图片0.否 1.是
	private List<File> file;// 上传文件
	private List<String> fileFileName;// 上传文件名
	private Map<String, String> map;// 动态属性内容
	private Map<String, Boolean> error;// 错误信息

	public String getProfessionName() {
		return professionName;
	}

	public void setProfessionName(String professionName) {
		this.professionName = professionName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getTitle1() {
		return title1;
	}

	public void setTitle1(String title1) {
		this.title1 = title1;
	}

	public String getTitle2() {
		return title2;
	}

	public void setTitle2(String title2) {
		this.title2 = title2;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

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

}
