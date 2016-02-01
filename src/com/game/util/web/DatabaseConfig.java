package com.game.util.web;

public class DatabaseConfig {
	
	public static String get(String key){
		return ConfigUtil.getInstance(Constant.CONFIG_PATH).getKeyValue(key);
	}
	
	public static void write(String keyname, String keyvalue){
		ConfigUtil.getInstance(Constant.CONFIG_PATH).writeProperties(keyname, keyvalue);
	}
}
