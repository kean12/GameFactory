package com.game.util.web;


public class Constant {
	//图片上传路径
	public static final String USER_UPLOAD_TEMP_PATH = "/kindeditor/temp";
	public static final String USER_UPLOAD_PATH = "/userupload";
	//文件路径
	public static final String CONFIG_PATH = Constant.class.getClassLoader().getResource("conf/config.properties").getPath();
	public static final String SYS_CONFIG_PATH = Constant.class.getClassLoader().getResource("conf/sys_config.properties").getPath();
	public static final String MERCHANTINFO_CONFIG_PATH = Constant.class.getClassLoader().getResource("conf/merchantInfo.properties").getPath();
	public static final String IP_FILE = Struts2Util.getRealPath("") + "\\QQWry.dat";
	//session
	public static final String USER = "user111";
	public static final String MANAGE = "manage";
	// 每日提现次数
	public static final Integer SEVERAL = 3;
	//最大积分值
	public static final int MAX_CREDIT = 2147483647;
	//图片默认大小 单位kb
	public static final int IMAGE_SIZE = Integer.parseInt(ConfigUtil.getInstance(SYS_CONFIG_PATH).getKeyValue("upImg_Size"));
	//日期格式
	public static final String YYYYMMDD = "yyyyMMdd";
	public static final String YYYYMMDDSS = "yyyyMMddss";
	public static final String HH_MM = "HH:mm";
	public static final String YYYY_MM_DD = "yyyy-MM-dd";
	public static final String YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";
	public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
	public static final String YYYY_MM_DD_HH_MM_SS_MMM = "yyyy-MM-dd HH:mm:ss:mmm";
	//DWR状态
	public static final String NO_LOGIN = "NO_LOGIN";
	public static final String SUCCESS = "SUCCESS";
	public static final String ONHAVE = "ON_HAVE";
	public static final String ERROR = "ERROR";
	public static final String NONE = "NONE";
	//项目路径
	public static final String CONTEXT_PATH = Struts2Util.getContextPath();
	
}
