package com.emotte.eserver.thread;

import java.io.IOException;
import java.io.InputStream;

import net.sf.json.JSONObject;

import com.emotte.eserver.core.helper.LogHelper;
import com.emotte.eserver.dispatcher.Dispatcher;

public class StopThread extends Thread {
	private Thread thread;
	public StopThread(Thread thread) {
		this.thread = thread;
	}
	@Override
	public void run() {
		LogHelper.info(getClass(), "启动监听程序...");
		try {
			InputStream is = System.in;
			byte[] bytes = new byte[1024];
			while(is.read(bytes) > 0) {
				String s = new String(bytes);
				char c = s.charAt(0);
				switch (c) {
				case '#' : destory(); return;
				case 'c' : {
					doInvoke(s.split(" ")[1]);
					break;
				}
				default : System.out.println("请输入#结束");
				}
			}
		} catch (IOException e) {
			LogHelper.error(getClass(), e.getMessage());
		} finally {
			LogHelper.info(getClass(), "程序结束");
		}
	}
	
	public void destory() {
		this.thread.interrupt();
	}
	
	public void doInvoke (String json) {
		JSONObject result = Dispatcher.getInstance().invoke(json);
		LogHelper.info(getClass(), result.toString());
	}
}
