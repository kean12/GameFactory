package com.game.util.web;

public class YeePayConfig {
	
	public static String get(String key){
		return ConfigUtil.getInstance(Constant.MERCHANTINFO_CONFIG_PATH).getKeyValue(key);
	}
	
	public static void write(String keyname, String keyvalue){
		ConfigUtil.getInstance(Constant.MERCHANTINFO_CONFIG_PATH).writeProperties(keyname, keyvalue);
	}
}
