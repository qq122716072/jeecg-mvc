package com.emotte.eserver.listener.impl;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.zeromq.ZMQ;

import com.emotte.eserver.balancing.BalancingMachine;
import com.emotte.eserver.constant.ListenerConstant;
import com.emotte.eserver.core.helper.LogHelper;
import com.emotte.eserver.dispatcher.Dispatcher;
import com.emotte.eserver.listener.Listener;

import net.sf.json.JSONObject;

public class ServiceListener implements Listener ,Runnable{

	private int port;
	private int timeout;
	private ZMQ.Socket socket;
	private ZMQ.Context context;
	private boolean status = true;
	private long accessCount = 0;
	
	public ServiceListener(int port,int timeout,ZMQ.Context context) {
		this.context = context;
		this.port = port;
		this.timeout = timeout;
	}
	
	public void startMonitor() {
		Thread t = new Thread(this);
		t.start();
	}
	
	public void stopMonitor() {
		this.status = false;
	}
	
	
	public void run() {
			monitor();
	}

	
	public void monitor(){
		try{
			ExecutorService exec = Executors.newFixedThreadPool(1);
			MessageHandler meghandler = new MessageHandler();
			socket = context.socket(ZMQ.REP);  //创建一个response类型的socket，他可以接收request发送过来的请求，其实可以将其简单的理解为服务端
			socket.setSendTimeOut(this.timeout);
			socket.setReceiveTimeOut(this.timeout);
			socket.bind ("tcp://*:" + this.port);    //绑定端口
			while (status) {
					JSONObject jsonObject = new JSONObject();
					String json = socket.recvStr(0);//获得传值
					if(json == null ){
						continue;
					}
					if(json.startsWith("handshake:")){
						LogHelper.info(this.getClass(),json.replace("handshake:", "") + "已经连接上端口:" + ListenerConstant.ip + ":" + this.port);
						socket.send("ok",0);
						continue;
					}
					try {
						meghandler.setJson(json);
						Future<JSONObject> future = exec.submit(meghandler);  
						jsonObject = future.get(timeout, TimeUnit.MILLISECONDS);
					} catch (TimeoutException ex) {
						LogHelper.error(getClass(), ex.getMessage(), ex);
						jsonObject.accumulate("code","1005");
					} catch (Exception e) {
						LogHelper.error(getClass(), e.getMessage(), e);
						jsonObject.accumulate("code","1006");
					}
					jsonObject.put("iplist", BalancingMachine.randomIpList());
					LogHelper.info(this.getClass(),"返回数据" + jsonObject.toString() );
					socket.send(jsonObject.toString(),0);
					accessCount++;
			}
			socket.close();  //先关闭socket
			context.term();  //关闭当前的上下文
		}catch(Exception e){
			socket.close();  //先关闭socket
			context.term();  //关闭当前的上下文
			LogHelper.info(this.getClass(),"系统报错");
			e.printStackTrace();
			this.monitor();
		}
	}
	
	
	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public long getAccessCount() {
		long i  = accessCount;
		accessCount = 0;
		return i;
	}
}

class MessageHandler implements Callable<JSONObject>{
	
	String json;
	
	public JSONObject call() throws Exception {
		Dispatcher dis = Dispatcher.getInstance();
		JSONObject rep = dis.invoke(json);
		return rep;
	}
	public String getJson() {
		return json;
	}
	public void setJson(String json) {
		this.json = json;
	}
}
