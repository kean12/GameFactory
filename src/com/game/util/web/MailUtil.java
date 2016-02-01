package com.game.util.web;

import java.util.Properties;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.game.util.domain.User;
import com.game.util.user.services.UserService;

public class MailUtil {
	private UserService userService;
	private static MailUtil systemMail;
	private JavaMailSenderImpl jmail;
	private Properties pp;
	MimeMessageHelper messageHelper;

	private MailUtil() {
	}

	/**
	 * 发送邮件
	 */
	private void sendMail(String title, String content, String toMail) {
		try {
			String username = ConfigUtil.getInstance(Constant.SYS_CONFIG_PATH).getKeyValue("mail_Name");
			String password = ConfigUtil.getInstance(Constant.SYS_CONFIG_PATH).getKeyValue("mail_Pass");
			String mailServer = ConfigUtil.getInstance(Constant.SYS_CONFIG_PATH).getKeyValue("mail_Server");
			jmail = new JavaMailSenderImpl();
			jmail.setHost(mailServer);
			jmail.setUsername(username);
			jmail.setPassword(password);
			pp = new Properties();
			pp.setProperty("mail.smtp.auth", "true");
			jmail.setJavaMailProperties(pp);
			MimeMessage mailMessage = jmail.createMimeMessage();
			messageHelper = new MimeMessageHelper(mailMessage, true, "UTF-8");
			messageHelper.setTo(toMail);
			messageHelper.setText("<html><head></head><body>" + content + "</body></html>", true);
			String from = username + "@" + mailServer.replace("smtp.", "");
			messageHelper.setFrom(from);
			messageHelper.setSubject(title);
			jmail.send(mailMessage);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void getReport(String title, String content, String toMail) {
		if (systemMail == null)
			systemMail = new MailUtil();
		systemMail.sendMail(title, content, toMail);
	}

	/**
	 * 通过邮箱找回密码
	 * @param type 1.登录密码 2.支付密码
	 * @throws Exception
	 */
	public static void getPassword(Integer type, User user) throws Exception {
		String title = null;
		String content = null;
		String toMail = user.getEmail();
		if (systemMail == null)
			systemMail = new MailUtil();
		if (user.getRealName() != null) {
			content = "<span style=\"font-size:18px;\">" + user.getRealName() + "，您好！</span><br />";
		} else {
			content = "<span style=\"font-size:18px;\">" + user.getUsername() + "，您好！</span><br />";
		}
		if (type == 1) {
			title = "尊敬的用户，您在游戏买卖网的登录密码";
			content += "<span style=\"font-size:18px; text-indent:2em;\">你在游戏买卖网的登录密码是：" + systemMail.random_password(type, user) + "</span><br />";
		} else if (type == 2) {
			title = "尊敬的用户，您在游戏买卖网的支付密码";
			content += "<span style=\"font-size:18px; text-indent:2em;\">你在游戏买卖网的支付密码是：" + systemMail.random_password(type, user) + "</span><br />";
		}
		content += "<span style=\"font-size:18px; text-indent:2em;margin-top:20px;\">请用此密码重新登录 http://www.gamemaimai.com/</span><br />";
		content += "<span style=\"font-size:18px; text-indent:2em;margin-top:20px;\">提示：建议您立即登录修改密码并删除本邮件，以免密码泄漏。</span>";
		systemMail.sendMail(title, content, toMail);
	}

	private String random_password(Integer type, User user) throws Exception {
		if (userService == null) {
			userService = (UserService) SpringUtil.getBean("userService");
		}
		String password = CryptTool.getPassword(18);
		if (type == 1) {
			user.setPassword(TradeMD5.toMD5(password));
			userService.updateUser(user);
		} else if (type == 2) {
			user.getUserInfo().setApplyPwd(MD5.toMD5(password));
			userService.updateUser(user);
		}
		return password;
	}

}
