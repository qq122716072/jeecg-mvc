package com.emotte.eserver.listener.impl;

import java.util.List;

import net.sf.json.JSONArray;

import org.zeromq.ZMQ;

import com.emotte.eserver.balancing.BalancingMachine;
import com.emotte.eserver.constant.ListenerConstant;
import com.emotte.eserver.core.helper.LogHelper;
import com.emotte.eserver.listener.Listener;
import com.emotte.kernel.exception.BaseException;

public class IpPortListener implements Listener,Runnable {

	private int port;
	private int timeout;
	private ZMQ.Socket socket;
	private ZMQ.Context context;
	private boolean status = true;
	
	public IpPortListener(int port,int timeout,ZMQ.Context context) {
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
	
	
	public void run(){
			monitor();
	}
	

	public void monitor(){
		try{
			socket = context.socket(ZMQ.REP);  //创建一个response类型的socket，他可以接收request发送过来的请求，其实可以将其简单的理解为服务端
			socket.setSendTimeOut(this.timeout);
			socket.bind ("tcp://*:" + this.port);    //绑定端口
			socket.setReceiveTimeOut(this.timeout);
			while (status) {
				String restr = socket.recvStr();
				if(restr == null){
					continue;
				}
				//getip-sdkid-count 
				if(restr.contains("getip")){
					LogHelper.info(this.getClass(), "获得ip请求消息" + restr);
					List<String> list = BalancingMachine.getIpEntrys();
					if (list.size() == 0) throw new BaseException("服务器全部宕机，请检查kafka是否启动");
					String[] restrArr = restr.split("-");
					String sdkid = restrArr[1];
					int count = Integer.parseInt(restrArr[2]);
					int[] arr = getIntArr(count, list.size());
					JSONArray portList = new JSONArray();
					for(int i = 0 ; i < list.size();i++) {
						String ip = list.get(i);
						if(ip.equalsIgnoreCase(ListenerConstant.ip)) { // 如果是本机IP，则获取可用端口
							portList.addAll(ListenerManager.getFreePorts(arr[i],sdkid));
						} else { // 如果不是本机IP，则继续向指定IP的服务器请求可用端口
							ZMQ.Socket sock = null;
							try{
								sock = context.socket(ZMQ.REQ);
								LogHelper.info(this.getClass(),"向" + ip + "发送ip查询");
								sock.connect("tcp://" + ip + ":4998" );
								sock.setSendTimeOut(this.timeout/3);
								sock.send(("getport-" + sdkid + "-" + arr[i]) .getBytes());
								String json = sock.recvStr(0);
								portList.addAll(JSONArray.fromObject(json));
								sock.close();
							}catch(Exception e){
								LogHelper.info(this.getClass(), "获取" + ip + "异常");
							}finally {
								sock.close();
							}
						}
					}
					LogHelper.error(this.getClass(),"返回总ip" + portList.toString());
					socket.send(portList.toString());
				}else if(restr.contains("getport")){
					String[] org = restr.split("-");
					JSONArray jarr = ListenerManager.getFreePorts(Integer.parseInt(org[2]), org[1]);
					socket.send(jarr.toString());
				}
			}
			socket.close();  //先关闭socket
			context.term();  //关闭当前的上下文
		} catch(BaseException e){
			socket.close();  //先关闭socket
			context.term();  //关闭当前的上下文
			LogHelper.error(this.getClass(),"系统报错" + e.getMessage());
			this.monitor();
		} catch(Exception e){
			socket.close();  //先关闭socket
			context.term();  //关闭当前的上下文
			LogHelper.error(this.getClass(),"系统报错" + e.getMessage(), e);
			this.monitor();
		}
	}
	
	
	
	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}
	
	public int[] getIntArr(int count,int size){
		int mod = count % size;
		int count_m = count - mod;
		int n = count_m / size;
		int[] iarr = new int[size];
		for(int i = 0; i < size ; i++)
		{
			if(mod > 0){
				iarr[i] =  n + 1;
				mod--;
			}else{
				iarr[i] =  n;
			}
		}
		return iarr;
	}

	public long getAccessCount() {
		return 0;
	}
	
	
}
