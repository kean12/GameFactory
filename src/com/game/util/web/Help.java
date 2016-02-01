package com.game.util.web;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.game.util.admin.bizkind.services.BizKindService;
import com.game.util.admin.game.services.GameService;
import com.game.util.domain.BizKind;
import com.game.util.domain.Game;

public class Help {

	public static void updateBizKind(String path, BizKindService bizKindService) throws Exception {
		File file = new File(path + "/js/index/bizKindListVal.js");
		if (!file.exists()) {
			file.createNewFile();
		}

		List<BizKind> list = bizKindService.findBizKindByToleration(1);
		String val = "";
		for (BizKind tmp_bizKind : list) {
			val += tmp_bizKind.getId() + "::" + tmp_bizKind.getKindName() + ";;";
		}
		String content = "var bizKindListVal='" + val + "';";

		OutputStream fout = new FileOutputStream(file);
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fout, "utf-8"));

		writer.write(content);
		writer.close();
	}

	// 更新游戏文件
	public static void updateGamePage(String path, GameService gameService) throws Exception {
		List<Game> list = gameService.findGameByState(1);
		String val = "";
		for (Game tmp_game : list) {
			val += tmp_game.getId() + "::" + tmp_game.getGameName() + "::" + tmp_game.getGameIndex() + "::" + tmp_game.getCompany() + "::" + tmp_game.getGameHot() + ";;";
		}
		String content = "var gameListVal='" + val + "';";
		Help.newPage(path + "/js/index/gameListVal.js", content);
		Help.createSearchPage(path, gameService);

	}

	/**
	 * 创建搜索页
	 */
	public static boolean createSearchPage(String path, GameService gameService) {
		boolean flag = false;
		try {
			String searchPageContent = "";
			List<Game> gameList = gameService.findGameByState(1);
			List<String> companyList = gameService.findGameCompany(1);
			ArrayList<Character> array = new ArrayList<Character>();
			int start = 'A';
			int end = 'Z';
			searchPageContent += "<%@ page contentType='text/html; charset=utf-8'%>\r\n";
			searchPageContent += "<%@ include file='/WEB-INF/index/common/taglibs.jsp'%>\r\n";
			searchPageContent += "<div class='search_select' id='search_select_box' style='display:none'>\r\n";
			searchPageContent += "<div class='search_select_in'>\r\n";
			searchPageContent += "<h1><a href=\"javascript:close_nav('search_select_box')\" target=\"_self\">[关闭]</a><strong>请选择游戏</strong></h1>\r\n";
			searchPageContent += "<div class='search_select_navbox' id='search_nav'>\r\n";
			// --字母搜索--
			searchPageContent += "<a class='search_nav1' style=\"width:118px\" href='javascript:void(0)'>热门游戏</a>\r\n";
			char cc;
			for (int i = start; i <= end; i++) {
				cc = (char) i;
				Pattern p = Pattern.compile("[^IUV]");
				Matcher m = p.matcher(cc + "");
				if (m.find()) {
					array.add(cc);
					searchPageContent += "<a href='javascript:void(0)'>" + cc + "</a>\r\n";
				}
			}
			searchPageContent += "</div>\r\n";
			// --字母搜索END--
			// --游戏列表内容处理--
			String gameHot = "";
			int arraylength = array.size();
			Map<Object, String> content = new HashMap<Object, String>();
			for (Game game : gameList) {
				if (game.getGameHot() != null && game.getGameHot() != 0) {
					gameHot += "<a href=\"javascript:doSelectGame('" + game.getGameName() + "','" + game.getId() + "')\" class=\"orange\">" + game.getGameName() + "</a>";
				}

				for (char c : array) {
					if (!content.containsKey(c)) {
						content.put(c, "");
					}
					if (game.getGameIndex() != null
							&& c == game.getGameIndex().toUpperCase().charAt(0)) {
						if (game.getGameHot() != null && game.getGameHot() != 0) {
							content.put(c, content.get(c) + "<a href=\"javascript:doSelectGame('" + game.getGameName() + "','" + game.getId() + "')\" class=\"orange\">" + game.getGameName() + "</a>");
						} else {
							content.put(c, content.get(c) + "<a href=\"javascript:doSelectGame('" + game.getGameName() + "','" + game.getId() + "')\">" + game.getGameName() + "</a>");
						}
					}
				}

				for (String company : companyList) {
					if (!content.containsKey(company)) {
						content.put(company, "");
					}
					if (company.equals(game.getCompany())) {
						if (game.getGameHot() != null && game.getGameHot() != 0) {
							content.put(company, content.get(company) + "<a href=\"javascript:doSelectGame('" + game.getGameName() + "','" + game.getId() + "')\" class=\"orange\">" + game.getGameName() + "</a>");
						} else {
							content.put(company, content.get(company) + "<a href=\"javascript:doSelectGame('" + game.getGameName() + "','" + game.getId() + "')\">" + game.getGameName() + "</a>");
						}
					}
				}
			}

			String letterContent = "";
			int count = 1;
			for (char c : array) {
				if (content.get(c).equals("")) {
					letterContent += "<div id=\"gamelist" + count + "\"><a href=\"javascript:void(0)\">没有相关游戏</a></div>\r\n";
				} else {
					letterContent += "<div id=\"gamelist" + count + "\">" + content.get(c) + "</div>\r\n";
				}
				count++;
			}

			int reckon = arraylength + 1;
			for (String company : companyList) {
				if (content.get(company).equals("")) {
					letterContent += "<div id=\"gamelist" + reckon + "\"><a href=\"javascript:void(0)\">没有相关游戏</a></div>\r\n";
				} else {
					letterContent += "<div id=\"gamelist" + reckon + "\">" + content.get(company) + "</div>\r\n";
				}
				reckon++;
			}
			// --游戏列表内容处理END--

			// --游戏列表内容END--
			searchPageContent += "<div id='gameListContent' class=\"search_listbox\">\r\n";
			searchPageContent += "<div id=\"gamelist0\">" + gameHot + "</div>\r\n";
			searchPageContent += letterContent;
			searchPageContent += "</div>\r\n";
			// --游戏列表内容END--

			// --经销商列表--
			searchPageContent += "<div class=\"search_yx_main\" id='GameProvider'>\r\n";
			for (String company : companyList) {
				searchPageContent += "<a href=\"javascript:void(0)\">" + company + "</a>\r\n";
			}
			searchPageContent += "</div>\r\n";
			// --经销商列表End--
			searchPageContent += "<div class=\"blank5\"></div>\r\n";
			searchPageContent += "</div>\r\n";
			searchPageContent += "</div>\r\n";
			newPage(path + "/WEB-INF/index/common/search_gameList.jsp", searchPageContent);
			flag = true;
		} catch (Exception e) {
		}
		return flag;
	}

	/**
	 * 文件流写入
	 * @param path 路径
	 * @param content 内容
	 */
	public static void newPage(String path, String content) throws IOException {
		File file = new File(path);
		if (!file.exists()) {
			file.createNewFile();
		}
		OutputStream fout = new FileOutputStream(file);
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fout, "utf-8"));
		writer.write(content);
		writer.close();
	}
	
	/**
	 * 图片上传到用户目录
	 * @param upload 上传文件集合
	 * @param uploadFileName 上传文件名集合
	 * @param user 操作用户
	 * @return 上传后的文件名集合
	 */
	public static List<String> uploadImageToUserPath(List<File> upload, List<String> uploadFileName, String subdirectory) {
		String savePath = Constant.USER_UPLOAD_PATH;
		return uploadfile(upload, savePath, uploadFileName, subdirectory);
	}
	
	/**
	 * 图片上传到临时目录
	 * @param upload 上传文件集合
	 * @param uploadFileName 上传文件名集合
	 * @param user 操作用户
	 * @return 上传后的文件名集合
	 */
	public static List<String> uploadImageToTempPath(List<File> upload, List<String> uploadFileName, String subdirectory) {
		String savePath = Constant.USER_UPLOAD_TEMP_PATH;
		return uploadfile(upload, savePath, uploadFileName, subdirectory);
	}
	
	/**
	 * 图片上传到用户目录
	 * @param upload 上传文件
	 * @param uploadFileName 上传文件名
	 * @param user 操作用户
	 * @return 上传后的文件名
	 */
	public static String uploadImageToUserPath(File upload, String uploadFileName, String subdirectory) {
		String savePath = Constant.USER_UPLOAD_PATH;
		return uploadfile(upload, savePath, uploadFileName, subdirectory);
	}
	
	/**
	 * 图片上传到临时目录
	 * @param upload 上传文件
	 * @param uploadFileName 上传文件名
	 * @param user 操作用户
	 * @return 上传后的文件名
	 */
	public static String uploadImageToTempPath(File upload, String uploadFileName, String subdirectory) {
		String savePath = Constant.USER_UPLOAD_TEMP_PATH;
		return uploadfile(upload, savePath, uploadFileName, subdirectory);
	}

	/**
	 * 移动编辑器中临时目录下的图片
	 * @param content 内容
	 * @param path tomcat路径
	 * @param root 上传根目录
	 * @param username 用户名
	 * @param subdirectory 新文件夹子目录
	 * @return 处理过的内容
	 */
	public static String moveKindEditorImage(String content, String subdirectory) throws Exception {
		String directory = "/kindeditor/userupload/images/";
		String path = Struts2Util.getRealPath(""); 
		String root = Constant.USER_UPLOAD_TEMP_PATH;
		String username = Struts2Util.getUserSession().getUsername();
		
		Pattern p = Pattern.compile("src=\"" + root + "/(\\w)*(/){0,1}(\\w)+.(jpg|gif|bmp|png)\"");
		Matcher m = p.matcher(content.toLowerCase());
		if (username != null) {
			directory += MD5.toMD5(username) + "/";
		}
		if (subdirectory != null) {
			directory += subdirectory + "/";
		}
		while (m.find()) {
			String oldChar = m.group();
			String[] arr = oldChar.split("\"");
			// 文件原地址
			File oldFile = new File(path + arr[1]);
			// new一个新文件夹
			File fnewpath = new File(path + directory);
			// 判断文件夹是否存在
			if (!fnewpath.exists()) {
				fnewpath.mkdirs();
			}
			// 将文件移到新文件里
			File fnew = new File(path + directory + oldFile.getName());
			oldFile.renameTo(fnew);

			String newPath = "src=\"" + directory + oldFile.getName() + "\"";
			content = content.replaceAll("(?i)" + oldChar, newPath);
		}

		if (username != null) {
			removeTempImage(path + root + "/" + username);
		}
		return content;
	}
	
	/**
	 * 移动指定目录下的文件
	 * @param paths 上传文件路径集合
	 * @param oldSavePath 旧文件夹路径 ‘/’开头和结尾
	 * @param newPath 新文件夹路径 ‘/’开头和结尾
	 * @return
	 */
	public static List<String> moveFile(List<String> paths, String oldSavePath, String newPath) {
		String relSavePath = Constant.USER_UPLOAD_PATH;
		return moveFile(paths, relSavePath, oldSavePath, newPath);
	}

	/**
	 * 删除临时目录下的所有文件
	 */
	public static void removeTempImage(String path) {
		path = Struts2Util.getRealPath(path);
		if (Validator.isBlank(path)) {
			return;
		}
		File file = new File(path);
		if (!file.exists()) {
			return;
		}
		if (!file.isDirectory()) {
			file.delete();
			return;
		}
		String[] tempList = file.list();
		File temp = null;
		for (int i = 0; i < tempList.length; i++) {
			if (path.endsWith(File.separator)) {
				temp = new File(path + tempList[i]);
			} else {
				temp = new File(path + File.separator + tempList[i]);
			}
			if (temp.isFile()) {
				temp.delete();
			}
			if (temp.isDirectory()) {
				removeTempImage(path + "/" + tempList[i]);// 先删除文件夹里面的文件
			}
		}
	}
	
	public static void chkImage(List<File> fileList, List<String> fileName, int size) throws Exception{
		int index=Validator.chkImage(fileList, size);
		if(index!=-1){
			throw new Exception(fileName.get(index) + "不是图片文件或大小超出" + size + "KB");
		}
	}
	
	public static void chkImage(File fileList, String fileName, int size) throws Exception{
		if(!Validator.chkImage(fileList, size)){
			throw new Exception(fileName + "不是图片文件或文件大小超出" + size + "KB");
		}
	}
	
	/**
	 * @param upload 上传文件集合
	 * @param savePath 存储路径
	 * @param uploadFileName 上传文件名集合
	 * @param user 操作用户
	 * @return 上传后的文件名集合
	 */
	private static List<String> uploadfile(List<File> upload, String savePath, List<String> uploadFileName, String subdirectory) {
		List<String> fileNameList = null;
		if (upload != null && upload.size() > 0) {
			try {
				fileNameList = new ArrayList<String>();
				FileOutputStream fos = null;
				FileInputStream fis = null;
				String path = Struts2Util.getRealPath(savePath);
				if(!Validator.isBlank(subdirectory)) {
					path += "/" + subdirectory;
				}
				
				File file = new File(path);
				if (!file.exists()) {
					file.mkdirs();
				}
				String fileName = null;
				int index = 0;
				for (int i = 0; i < upload.size(); i++) {
					fileName = uploadFileName.get(i);
					index = fileName.lastIndexOf(".");
					fileName = MD5.toMD5(fileName.substring(0, index) + System.currentTimeMillis()) + fileName.substring(index, fileName.length());
					fos = new FileOutputStream(path + "/" + fileName);
					File f = upload.get(i);
					fis = new FileInputStream(f);
					byte[] buffer = new byte[1024 * 8];
					int len = 0;
					while ((len = fis.read(buffer)) > 0) {
						fos.write(buffer, 0, len);
					}
					
					if(Validator.isBlank(subdirectory)) {
						fileNameList.add(savePath + "/" + fileName);
					}else {
						fileNameList.add(savePath + "/" + subdirectory + "/" + fileName);
					}
					
				}
				fos.close();
				fis.close();
			} catch (Exception e) {
				return fileNameList;
			}
		}
		return fileNameList;
	}

	/**
	 * @param upload 上传文件
	 * @param savePath 存储路径
	 * @param uploadFileName 上传文件名
	 * @return 上传后的文件名
	 */
	private static String uploadfile(File upload, String savePath, String uploadFileName, String subdirectory) {
		
		if (upload != null && uploadFileName != null) {
			try {
				FileOutputStream fos = null;
				FileInputStream fis = null;
				
				String path = Struts2Util.getRealPath(savePath);
				if(!Validator.isBlank(subdirectory)) {
					path += "/" + subdirectory;
				}
				
				File file = new File(path);
				if (!file.exists()) {
					file.mkdirs();
				}
				int index = 0;
				index = uploadFileName.lastIndexOf(".");
				uploadFileName = MD5.toMD5(uploadFileName.substring(0, index) + System.currentTimeMillis()) + uploadFileName.substring(index, uploadFileName.length());
				fos = new FileOutputStream(path + "/" + uploadFileName);
				fis = new FileInputStream(upload);
				byte[] buffer = new byte[1024 * 8];
				int len = 0;
				while ((len = fis.read(buffer)) > 0) {
					fos.write(buffer, 0, len);
				}

				fos.close();
				fis.close();
			} catch (Exception e) {
				if(Validator.isBlank(subdirectory)) {
					return savePath + "/" + uploadFileName;
				}else {
					return savePath + "/" + subdirectory + "/" + uploadFileName;
				}
			}
		}
		if(Validator.isBlank(subdirectory)) {
			return savePath + "/" + uploadFileName;
		}else {
			return savePath + "/" + subdirectory + "/" + uploadFileName;
		}
	}

	/**
	 * 移动指定目录下的文件
	 * @param paths 上传文件路径集合
	 * @param oldSavePath 旧文件夹路径 ‘/’开头和结尾
	 * @param newPath 新文件夹路径 ‘/’开头和结尾
	 * @return
	 */
	private static List<String> moveFile(List<String> paths, String relSavePath, String oldSavePath, String newPath) {
		String absSavePath = Struts2Util.getRealPath(relSavePath);
		File nfile = new File(newPath);
		if (!nfile.exists()) {
			nfile.mkdirs();
		}
		List<String> newFileName = new ArrayList<String>();
		String path = null;
		if (paths != null && paths.size() > 0) {
			for (String oldPath : paths) {
				File oldFile = new File(absSavePath + oldSavePath + oldPath);
				path = absSavePath + newPath + oldFile.getName();
				File newFile = new File(path);
				oldFile.renameTo(newFile);
				newFileName.add(relSavePath + newPath + oldFile.getName());
			}
		}
		return newFileName;
	}


}