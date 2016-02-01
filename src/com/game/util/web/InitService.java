package com.game.util.web;

import java.io.File;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.game.util.admin.bizkind.services.BizKindService;
import com.game.util.admin.game.services.GameService;

public class InitService implements ServletContextListener {
	private static Log log = LogFactory.getLog(InitService.class);

	public void contextInitialized(ServletContextEvent event) {
		try {
			String path = event.getServletContext().getRealPath("");
			File file1 = new File(path + "/js/index/gameListVal.js");
			File file2 = new File(path + "/WEB-INF/index/common/search_gameList.jsp");

			if (!file1.exists() || !file2.exists()) {
				GameService gameService = (GameService) SpringUtil.getBean(event, "gameService");
				Help.updateGamePage(path, gameService);
			}

			file1 = new File(path + "/js/index/bizKindListVal.js");
			if (!file1.exists()) {
				BizKindService bizKindService = (BizKindService) SpringUtil.getBean(event, "bizKindService");
				Help.updateBizKind(path, bizKindService);
			}
		} catch (Exception e) {
			System.err.println("初始化异常");
		}
	}

	public void contextDestroyed(ServletContextEvent event) {
		log.info("servlet destroyed..........");
	}

}
