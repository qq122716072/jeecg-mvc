package com.emotte.eserver.core.helper;

import org.apache.log4j.Logger;

public class LogHelper {
	private static Logger logger = Logger.getLogger(LogHelper.class);
	
	public static void info (Class<?> clazz, String content) {
		logger.info(clazz + "->" +content);
	}
	public static void info (String clazz, String content) {
		logger.info(clazz + "->" +content);
	}
	
	public static void debug (Class<?> clazz, String content) {
		logger.debug(clazz + "->" +content);
	}
	public static void debug (String clazz, String content) {
		logger.debug(clazz + "->" +content);
	}
	
	public static void error (Class<?> clazz, String content, Throwable t) {
		logger.error(clazz + "->" +content, t);
	}
	public static void error (String clazz, String content, Throwable t) {
		logger.error(clazz + "->" +content, t);
	}
	
	public static void error (Class<?> clazz, String content) {
		logger.error(clazz + "->" +content);
	}
	public static void error (String clazz, String content) {
		logger.error(clazz + "->" +content);
	}
}
