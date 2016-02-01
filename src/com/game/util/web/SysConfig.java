package com.game.util.web;

public class SysConfig {
	
	public static String get(String key){
		return ConfigUtil.getInstance(Constant.SYS_CONFIG_PATH).getKeyValue(key);
	}
	
	public static void write(String keyname, String keyvalue){
		ConfigUtil.getInstance(Constant.SYS_CONFIG_PATH).writeProperties(keyname, keyvalue);
	}
}
