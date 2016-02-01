package com.game.assist.task;

import java.util.ResourceBundle;

public class ConfigUtil {
	private static ResourceBundle rb = null;
	private static final String CONFIG_FILE = "com/game/assist/client/config";

	public static String getValue(String key) {
		if (rb == null) {
			rb = ResourceBundle.getBundle(CONFIG_FILE);
		}
		return rb.getString(key);
	}

}
