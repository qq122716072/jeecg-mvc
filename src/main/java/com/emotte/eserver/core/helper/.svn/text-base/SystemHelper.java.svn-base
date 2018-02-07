package com.emotte.eserver.core.helper;

import java.io.File;
import java.net.InetAddress;

/**
 * 系统帮助工具类
 * @author ChengJing
 * 2017年3月24日
 */
public class SystemHelper {
	
	public static String getLocalIp () {
		String localip = null;
        try {
        	InetAddress ia = InetAddress.getLocalHost();
//            String localname = ia.getHostName();
            localip = ia.getHostAddress();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return localip;
	}
	
	/**
	 * 获取当前路径
	 * @param length 向上级回退长度
	 * @return
	 * 2017年3月27日
	 */
	public static String getCurrentPath (int length) {
		String path = System.getProperty("user.dir");
		while (length-- > 0) {
			path = path.substring(0, path.lastIndexOf(File.separatorChar));
		}
		return path + File.separator;
	}
	
	public static void main(String[] args) {
		System.out.println(SystemHelper.getCurrentPath(1));
	}
}
