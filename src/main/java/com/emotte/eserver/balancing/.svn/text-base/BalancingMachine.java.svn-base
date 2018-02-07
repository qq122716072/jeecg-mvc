package com.emotte.eserver.balancing;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.emotte.eserver.constant.ListenerConstant;
import com.emotte.eserver.constant.TopicConstant;
import com.emotte.eserver.core.helper.LogHelper;
import com.emotte.eserver.core.kafka.ConsumerHandler;
import com.emotte.eserver.core.kafka.IConsume;

import net.sf.json.JSONObject;

public class BalancingMachine {
	
	
	private static Map<String,Entry> ipEntrys = new Hashtable<String,Entry>();
	
	private static List<String> alive = new ArrayList<String>();
	
	private static List<String> die = new ArrayList<String>();
	
	private static JSONObject ipjson = new JSONObject();
	
	public static void init(){
		if (ListenerConstant.openBeatting) { // 是否开启心跳同步
			new Thread(new Runnable(){
				public void run() {
					while (true) {
						try {
							BalancingMachine.delEntry();
							Thread.sleep(2000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}).start();
			
			new Thread(new Runnable(){
				public void run() {
					ConsumerHandler ch = ConsumerHandler.getInstance(ListenerConstant.ip);
					ch.consume(TopicConstant.IP, new IConsume() {
						public void consume(String key, String value) {
							LogHelper.debug(BalancingMachine.class, value + " 心跳检测 ");
							BalancingMachine.addEntry(value);
						}
					});
				}
			}).start();
		} else {
			alive.add(ListenerConstant.ip); // 只返回本机IP
			ipjson.put("alive", ListenerConstant.ip);
			ipjson.put("die", "");
		}
	}
	
	/**
	 * 添加或更新ip时间
	 * @param ip
	 * 2017年7月6日
	 */
	static synchronized void addEntry(String ip){
		if(ipEntrys.containsKey(ip)) {
			ipEntrys.get(ip).setTimeMillis(System.currentTimeMillis());
		} else {
			ipEntrys.put(ip, new Entry(ip, System.currentTimeMillis()));
		}
	}
	
	static synchronized void delEntry(){
		alive.clear();
		die.clear();
		Iterator<String> it = ipEntrys.keySet().iterator();	
		while(it.hasNext()){
			Entry en = ipEntrys.get(it.next());
			Long times = System.currentTimeMillis() - en.getTimeMillis();
			if(times > 3000){
				LogHelper.info(BalancingMachine.class, en.getIp() + " 已宕机 ");
				die.add(en.getIp());
			}else{
				alive.add(en.getIp());
			}
		}
		checkIP();
	}
	
	static synchronized void checkIP(){
		ipjson.clear();
		StringBuffer sb = new StringBuffer("");
		for(String ip : alive){
			sb.append(ip + ",");
		}
		StringBuffer sb2 = new StringBuffer("");
		for(String ip : die){
			sb2.append(ip + ",");
		}
		if(sb.length() > 0){
			sb.deleteCharAt(sb.length()-1);
		}
		if(sb2.length() > 0){
			sb2.deleteCharAt(sb2.length()-1);
		}
		ipjson.put("alive", sb.toString());
		ipjson.put("die", sb2.toString());
	}
	
	
	
	static class Entry{
		String ip;
		Long timeMillis;
		public Entry(String ip,Long timeMillis) {
			this.ip = ip;
			this.timeMillis = timeMillis;
		}
		public String getIp() {
			return ip;
		}
		public void setIp(String ip) {
			this.ip = ip;
		}
		public Long getTimeMillis() {
			return timeMillis;
		}
		public void setTimeMillis(Long timeMillis) {
			this.timeMillis = timeMillis;
		}
	}


	public static List<String> getIpEntrys() {
		return alive;
	}

	public static synchronized JSONObject randomIpList(){
		return ipjson;
	}

}
