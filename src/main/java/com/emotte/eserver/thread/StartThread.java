package com.emotte.eserver.thread;

import com.emotte.eserver.balancing.BalancingMachine;
import com.emotte.eserver.constant.CommonConstant;
import com.emotte.eserver.core.helper.LogHelper;
import com.emotte.eserver.core.helper.PropertiesHelper;
import com.emotte.eserver.core.helper.ServerHelper;
import com.emotte.eserver.core.scan.InitialScanner;
import com.emotte.eserver.listener.impl.ListenerManager;
import com.emotte.eserver.loader.Loader;

public class StartThread extends Thread {
	
	public static final String SCANREGEX = "scan.regex";
	
	@Override
	public void run() {
		LogHelper.info(getClass(), "开始启动...");
		// 加载class文件和jar包
		Loader loader = new Loader(ServerHelper.getServicePath());
		loader.load(ServerHelper.CLASSESROOTPATH, ServerHelper.LIBPATH, ServerHelper.SERVICELIB);
		// 扫描文件并初始化
		InitialScanner scanner = new InitialScanner(ServerHelper.getServicePath(), loader.getChildClassLoader());
		String scanRegex = PropertiesHelper.getValue(CommonConstant.CONFIG, SCANREGEX);
		scanner.scanJarLib(ServerHelper.SERVICELIB, scanRegex);
		scanner.scanClassPath(ServerHelper.CLASSESROOTPATH, scanRegex);
		// 启动心跳同步，线程
		HeartBeatingInThread heartBeatingThread = new HeartBeatingInThread();
		heartBeatingThread.start();
		// 初始化ZMQ
		initZMQ();
		LogHelper.info(getClass(), "启动成功");
	}
	
	/**
	 * 初始化ZMQ
	 * 2017年3月23日
	 */
	private void initZMQ() {
		LogHelper.info(getClass(), "启动监听...");
		BalancingMachine.init();
		ListenerManager.createListener();
		LogHelper.info(getClass(), "启动监听...");
	}

}
