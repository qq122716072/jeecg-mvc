package com.emotte.eserver.thread;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import com.emotte.eserver.constant.CommonConstant;
import com.emotte.eserver.constant.ListenerConstant;
import com.emotte.eserver.constant.TopicConstant;
import com.emotte.eserver.core.helper.PropertiesHelper;
import com.emotte.eserver.core.helper.SystemHelper;
import com.emotte.eserver.core.kafka.KafkaFactory2;

/**
 * 心跳同步线程
 * @author ChengJing
 * 2017年3月24日
 */
public class HeartBeatingInThread extends Thread {
	@Override
	public void run() {
		if (ListenerConstant.openBeatting) {
			String localIp = PropertiesHelper.getValue(CommonConstant.CONFIG, "local.ip", SystemHelper.getLocalIp());
//			long time = Long.parseLong(PropertiesHelper.getValue(CommonConstant.CONFIG, "heart.beating.time", "1"));
			long time = 1l;
			Timer timer = new Timer();
			timer.scheduleAtFixedRate(new HeartBeatingTask(localIp), new Date(), time * 1000);
		}
	}
	
	class HeartBeatingTask extends TimerTask {
		private String localIp;
		public HeartBeatingTask(String localIp) {
			this.localIp = localIp;
		}
		@Override
		public void run() {
			KafkaFactory2.getProducerHandler().produce(TopicConstant.IP,"ip", localIp);
		}
	}
}
