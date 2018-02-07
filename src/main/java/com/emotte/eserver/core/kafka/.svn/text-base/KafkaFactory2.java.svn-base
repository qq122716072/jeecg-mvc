package com.emotte.eserver.core.kafka;

import com.emotte.eserver.constant.TopicConstant;
import com.emotte.eserver.core.helper.LogHelper;
import com.emotte.eserver.thread.HeartBeatingInThread;

public class KafkaFactory2 {
	
	public static ProducerHandler getProducerHandler () {
		return ProducerHandler.getInstance();
	}
	
	public static ConsumerHandler getConsumerHandler (String groupId) {
		return ConsumerHandler.getInstance(groupId);
	}
	
	public static void main(String[] args) {
		// 生产
//		KafkaFactory2.getProducerHandler().produce("test","myname","caolc");
		new HeartBeatingInThread().start();
		// 消费
		KafkaFactory2.getConsumerHandler("cj").consume(TopicConstant.IP, new IConsume() {
			@Override
			public void consume(String key, String value) {
				LogHelper.info(getClass(), "consume: key=" + key + ",value=" + value);
			}
		});
	}
}
