package com.game.util.admin.system.action;

import java.util.Arrays;
import java.util.List;

import com.game.util.base.action.BaseAction;
import com.game.util.web.SysConfig;
import com.game.util.web.Validator;

public class SystemAction extends BaseAction {
	private static final long serialVersionUID = 5542415256790375271L;
	private String siteDoMain;// 本站域名
	private String siteName;// 站点名称
	private String description;// 描述
	private String keywords;//关键字
	private String siteMail;// 站长信箱
	private String upImg_Size;// 允许上传图片大小
	private String mail_Server;// 发送邮件服务器
	private String mail_Name;// 发送邮件用户名
	private String mail_Pass;// 发送邮件的密码
	private List<String> trade_Type;// 允许交易类型
	private String copyRight;// 版权信息

	public String sysParaSet() throws Exception {
		trade_Type = Arrays.asList(SysConfig.get("trade_Type").split(","));
		return "sysParaSet";
	}

	public String save() throws Exception {
		if (!Validator.isEmpty(trade_Type)) {
			String str = "";
			for (String i : trade_Type) {
				str += i + ",";
			}
			SysConfig.write("trade_Type", str.substring(0, str.lastIndexOf(",")));
		}else{
			SysConfig.write("trade_Type", "");
		}
		
		if (!Validator.isBlank(siteDoMain)) {
			SysConfig.write("siteDoMain", siteDoMain);
		}

		if (!Validator.isBlank(siteName)) {
			SysConfig.write("siteName", siteName);
		}
		
		if (!Validator.isBlank(description)) {
			SysConfig.write("description", description);
		}
		
		if (!Validator.isBlank(keywords)) {
			SysConfig.write("keywords", keywords);
		}

		if (Validator.isEmail(siteMail)) {
			SysConfig.write("siteMail", siteMail);
		}

		if (Validator.isIntNumber(upImg_Size)) {
			SysConfig.write("upImg_Size", upImg_Size);
		}

		if (Validator.isDomainName(mail_Server)) {
			SysConfig.write("mail_Server", mail_Server.toLowerCase());
		}

		if (!Validator.isBlank(mail_Name)) {
			SysConfig.write("mail_Name", mail_Name);
		}

		if (!Validator.isBlank(mail_Pass)) {
			SysConfig.write("mail_Pass", mail_Pass);
		}

		if (!Validator.isBlank(copyRight)) {
			SysConfig.write("copyRight", copyRight);
		}

		super.setIsSuccess(true);
		return SUCCESS;
	}

	public String getSiteDoMain() {
		return siteDoMain;
	}

	public void setSiteDoMain(String siteDoMain) {
		this.siteDoMain = siteDoMain;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getSiteMail() {
		return siteMail;
	}

	public void setSiteMail(String siteMail) {
		this.siteMail = siteMail;
	}

	public String getUpImg_Size() {
		return upImg_Size;
	}

	public void setUpImg_Size(String upImg_Size) {
		this.upImg_Size = upImg_Size;
	}

	public String getMail_Server() {
		return mail_Server;
	}

	public void setMail_Server(String mail_Server) {
		this.mail_Server = mail_Server;
	}

	public String getMail_Name() {
		return mail_Name;
	}

	public void setMail_Name(String mail_Name) {
		this.mail_Name = mail_Name;
	}

	public String getMail_Pass() {
		return mail_Pass;
	}

	public void setMail_Pass(String mail_Pass) {
		this.mail_Pass = mail_Pass;
	}

	public List<String> getTrade_Type() {
		return trade_Type;
	}

	public void setTrade_Type(List<String> trade_Type) {
		this.trade_Type = trade_Type;
	}

	public String getCopyRight() {
		return copyRight;
	}

	public void setCopyRight(String copyRight) {
		this.copyRight = copyRight;
	}

}
