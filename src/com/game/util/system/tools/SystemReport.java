package com.game.util.system.tools;

import java.util.Properties;

import javax.mail.internet.MimeMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

public class SystemReport {
	private static Log log = LogFactory.getLog(SystemReport.class);

	@SuppressWarnings("unused")
	private String content;

	private static SystemReport systemMail;

	private SystemReport() {
	}

	private SystemReport(String content) {
		this.content = content;
	}

	private void sendMail(String content) {
		try {
			JavaMailSenderImpl jmail = new JavaMailSenderImpl();
			jmail.setHost("smtp.163.com");
			jmail.setUsername("zgjs1208");
			jmail.setPassword("1027128");
			Properties pp = new Properties();
			pp.setProperty("mail.smtp.auth", "true");
			jmail.setJavaMailProperties(pp);

			MimeMessage mailMessage = jmail.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(
					mailMessage, true, "UTF-8");
			messageHelper.setTo("58646775@qq.com");
			messageHelper.setText("<html><head></head><body>" + content
					+ "</body></html>", true);
			messageHelper.setFrom("zgjs1208@163.com");
			messageHelper.setSubject("系统异常报告！");

			jmail.send(mailMessage);
		} catch (Exception e) {
			log.error("系统异常信息发送失败！");
			e.printStackTrace();
		}
	}

	public static void getReport(String content) {
		if (systemMail == null)
			systemMail = new SystemReport(content);
		systemMail.sendMail(content);
	}
}
