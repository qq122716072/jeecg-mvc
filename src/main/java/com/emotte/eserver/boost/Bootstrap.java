package com.emotte.eserver.boost;

import java.io.File;
import java.util.Properties;
import java.util.Map.Entry;

import com.emotte.eserver.constant.CommonConstant;
import com.emotte.eserver.core.context.Context;
import com.emotte.eserver.core.helper.LogHelper;
import com.emotte.eserver.core.helper.PropertiesHelper;
import com.emotte.eserver.core.helper.ServerHelper;
import com.emotte.eserver.core.helper.XMLHelper;
import com.emotte.eserver.thread.StartThread;
import com.emotte.eserver.thread.StopThread;

public class Bootstrap {
	
	public static void main(String[] args) throws Exception {
		// 初始化配置文件
		initProperties();
		// 初始化
		Context.initialize(PropertiesHelper.getValue(CommonConstant.CONFIG, "context"));
		StartThread start = new StartThread();
		start.start();
		new StopThread(start).start();
	}
	
	/**
	 * 初始化配置文件：将配置文件读入缓存
	 *
	 * 2017年3月28日
	 */
	private static void initProperties () {
		try {
			String configPath = ServerHelper.getConfPath();
			File conf = new File(configPath);
			if (!conf.exists()) {
				LogHelper.info(Bootstrap.class, "未找到配置文件，读取内部配置文件");
				return;
			}
			File[] files = conf.listFiles();
			for (File file : files) {
				if(file.getName().equals("config.xml")) {
					PropertiesHelper.addProperties(XMLHelper.readMap(file));
				} else if (file.getName().equals("config.properties")) {
					PropertiesHelper.addProperties(PropertiesHelper.readMap(file));
				}
			}
			Properties props = PropertiesHelper.getProperties();
			StringBuffer sb = new StringBuffer("配置参数为\n");
			for (Entry<Object, Object> entry : props.entrySet()) {
				sb.append("\t" + entry.getKey().toString() + "=" + entry.getValue().toString()).append("\n");
			}
			sb.setLength(sb.length()-2);
			LogHelper.info("", sb.toString());
		} catch (Exception e) {
			// do nothing
		}
	}

}
