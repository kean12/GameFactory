package com.game.util.web;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Properties;
import java.util.Random;

public class EnvUtil {
	private static Hashtable<String, String> htParam;
	
	public static String get(String key) {
		htParam = new Hashtable<String, String>();
		setHashtable();
		return htParam.get(key);
	}
	
	public static float freememory() {
		return getFreeMemory() / 1024 / 1024;
	}
	
	public static float totalMemory() {
		return getTotalMemory() / 1024 / 1024;
	}
	
	public static float proportion() {
		return (float) (getFreeMemory() / getTotalMemory() * 100 * 0.85);
	}
	
	public static long testNoat() {
		long timeStart = System.currentTimeMillis();
		int i=0;
		while(i<3000000)i++;
		long timeEnd = System.currentTimeMillis();
		long timeUse=timeEnd-timeStart;
		return timeUse;
	}
	
	public static long testSqrt() {
		long timeStart = System.currentTimeMillis();
		int i=0;
		double db=(double)new Random().nextInt(1000);
		while(i<200000){db=Math.sqrt(db);i++;}
		long timeEnd = System.currentTimeMillis();
		long timeUse=timeEnd-timeStart;
		return timeUse;
	}
	
	private static float getFreeMemory() {
		return (float) Runtime.getRuntime().freeMemory();
	}
	
	private static float getTotalMemory() {
		return (float) Runtime.getRuntime().totalMemory();
	}
	
	private static void setHashtable() {
		Properties me = System.getProperties();
		Enumeration<?> em = me.propertyNames();
		while(em.hasMoreElements()) {
			String strKey = (String) em.nextElement();
			String strValue = me.getProperty(strKey);
			htParam.put(strKey,strValue);
		}
	}
}
