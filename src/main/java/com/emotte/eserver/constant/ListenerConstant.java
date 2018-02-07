package com.emotte.eserver.constant;

import com.emotte.eserver.core.helper.PropertiesHelper;

public class ListenerConstant {
	
	public static int start_port = 5001;
	
	public static int port_count = 50;
	
	public static int ip_port = 4998;
	
	public static int timeout = 20000;
	
	public static String ip = "127.0.0.1";
	
	public static Boolean openBeatting = true; // 是否开启心跳同步
	
	
	static{
		String ipstr = PropertiesHelper.getValue("config.properties", "local.ip");
		String openBeatting = PropertiesHelper.getValue("config.properties", "beatting.open");
		String countstr = PropertiesHelper.getValue("config.properties", "server.port.count");
		String timeoutstr = PropertiesHelper.getValue("config.properties", "timeout");
		if(countstr != null && !countstr.equals(""))
			port_count = Integer.parseInt(countstr);
		if(timeoutstr != null && !timeoutstr.equals(""))
			timeout = Integer.parseInt(timeoutstr);
		if(ipstr != null && !ipstr.equals(""))
			ip = ipstr;
		if (openBeatting != null) ListenerConstant.openBeatting = Boolean.parseBoolean(openBeatting);
	}
	
}
