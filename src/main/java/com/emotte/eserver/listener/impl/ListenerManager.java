package com.emotte.eserver.listener.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.emotte.eserver.constant.ListenerConstant;
import com.emotte.eserver.core.helper.LogHelper;
import com.emotte.eserver.listener.Listener;

import net.sf.json.JSONArray;
import org.zeromq.ZMQ;

public class ListenerManager {
	 
	private static List<Listener> servicelist = new ArrayList<Listener>();
	private static List<Listener> iplist = new ArrayList<Listener>();
	private static List<Map<String,String>> ports = new ArrayList<Map<String,String>>();
	private static long accessRate = 0;
	private static ZMQ.Context context = ZMQ.context(1);  //这个表示创建用于一个I/O线程的context
	public static void createListener(){
		
		for(int i = 0; i < ListenerConstant.port_count; i++){
			ServiceListener sl = new ServiceListener(ListenerConstant.start_port + i, ListenerConstant.timeout,context);
			sl.startMonitor();
			servicelist.add(sl);
			Map<String,String> map = new HashMap<String,String>();
			map.put("ipport", (ListenerConstant.start_port + i) + "");
			ports.add(map);
		}
		IpPortListener ipl = new IpPortListener(ListenerConstant.ip_port,ListenerConstant.timeout,context);
		ipl.startMonitor();
		iplist.add(ipl);
		
		new Thread(new Runnable(){
			public void run() {
				while (true) {
					try {
						Thread.sleep(5 * 1000);
						accessRate = Math.round(getAllAccessCount()/5);
						LogHelper.debug(this.getClass(),"运行速率：" + accessRate);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}
	
	public static void stopListener(){
		for(Listener lis: servicelist){
			lis.stopMonitor();
		}
		for(Listener lis: iplist){
			lis.stopMonitor();
		}
	}
	
	
	public static JSONArray getFreePorts(int count,String sdkId){
		for(Map<String,String> map : ports){
			if(map.containsKey("sdkid") && map.get("sdkid").equals(sdkId)){
				map.remove("sdkid");
			}
		}
		JSONArray jarr = new JSONArray();
		for(Map<String,String> map : ports){
			if(map.containsKey("sdkid")){
				continue;
			}
			jarr.add(ListenerConstant.ip + ":" + map.get("ipport"));
			map.put("sdkid", sdkId);
			if(jarr.size() >= count){
				break;
			}
		}
		return jarr;
	}
	
	public static long getAllAccessCount(){
		long sum = 0;
		for(Listener lis : servicelist){
			sum += lis.getAccessCount();
		}
		return sum;
	}

	public static long getAccessRate() {
		return accessRate;
	}
	
}