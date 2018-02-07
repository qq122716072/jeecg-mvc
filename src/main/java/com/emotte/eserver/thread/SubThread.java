package com.emotte.eserver.thread;

import com.emotte.eserver.core.helper.LogHelper;
import com.emotte.eserver.core.kafka.KafkaFactory2;

public class SubThread extends Thread {
	@Override
	public void run() {
		int c = 0;
		while (true) {
			KafkaFactory2.getProducerHandler().produce("test","myname" + c,"caolc" + c);
			LogHelper.info(getClass(), "produce:" + "key=myname" + c +", value=caolc" + c);
			c++;
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		/*while(!Thread.currentThread().isInterrupted()) {
			LogHelper.info(getClass(), Thread.currentThread().getName());
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				LogHelper.error(getClass(), e.getMessage());
				break;
			}
		}*/
	}
}
