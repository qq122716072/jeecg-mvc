package com.emotte.eserver.core.helper;

import java.io.File;

import com.emotte.eserver.constant.CommonConstant;

public class ServerHelper {
	public static final String CLASSESROOTPATH = "classes";
	public static final String LIBPATH = "lib";
	public static final String SERVICELIB = "serviceLib";
	
	static boolean isFirstShowConf = true;
	static boolean isFirstShowServer = true;
	
	/**
	 * 获得配置文件路径
	 * @return
	 * 2017年3月28日
	 */
	public static String getConfPath () {
		String confPath = null;
		String rootPath = PropertiesHelper.getValue(CommonConstant.CONFIG, "eserver.home");
		if (null != rootPath && !"".equals(rootPath.trim())) {
			confPath = rootPath + File.separator;
		} else {
			confPath = SystemHelper.getCurrentPath(1);
		}
		confPath += "conf" + File.separator;
		if (isFirstShowConf) {
			LogHelper.info(ServerHelper.class, "项目配置文件路径为:" + confPath);
			isFirstShowConf = false;
		}
		return confPath;
	}
	public static String getServicePath () {
		String rootPath = PropertiesHelper.getValue(CommonConstant.CONFIG, "eserver.home");
		if (null == rootPath || "".equals(rootPath.trim())) {
			rootPath = SystemHelper.getCurrentPath(1);			
		}
		if (rootPath.charAt(rootPath.length()-1) != '\\' && rootPath.charAt(rootPath.length()-1) != '/') {
			rootPath += File.separator;
		}
		rootPath += "service" + File.separator;
		createDir(rootPath, true);
		createDir(rootPath + CLASSESROOTPATH, false);
		createDir(rootPath + LIBPATH, false);
		createDir(rootPath + SERVICELIB, false);
		if (isFirstShowServer) {
			LogHelper.info(ServerHelper.class, "项目根路径为:" + rootPath);
			isFirstShowServer = false;
		}
		return rootPath;
	}
	
	public static void createDir (String filePath, boolean isMul) {
		File file = new File(filePath);
		if (!file.exists()) {
			if (isMul) file.mkdirs();
			else file.mkdir();
		}
	}
}
