package com.emotte.eserver.core.kafka;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import com.emotte.eserver.constant.TopicConstant;
import com.emotte.eserver.core.helper.LogHelper;

public class TestConsumerHandler {
	private static ConsumerHandler consumer = ConsumerHandler.getInstance("");
	private static ConsumerHandler consumer1;
	public static void main(String[] args) {
//		consume();
		new Thread(new Runnable() {
			@Override
			public void run() {
				while(true){
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					KafkaFactory2.getProducerHandler().produce(TopicConstant.IP, "wexd", "tetsAdd");
				}
			}
		}).start();
		KafkaFactory2.getConsumerHandler("wexd").consume(TopicConstant.IP, new IConsume() {
			@Override
			public void consume(String key, String value) {
				System.out.println("11111111111111111111111111111111");
				LogHelper.info(this.getClass(), "consumer: key=" + key + " value=");          
			}
		});
		
	}
	
	
}
