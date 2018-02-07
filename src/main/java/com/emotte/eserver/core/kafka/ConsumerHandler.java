package com.emotte.eserver.core.kafka;

import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import com.emotte.eserver.core.helper.PropertiesHelper;

public class ConsumerHandler {
	
	private static Consumer<String, String> consumer;
	
	private ConsumerHandler (String groupId) {
		this.consumer = createConsumer(groupId);
	}
	
	public static  Consumer<String, String> cc(){
		return consumer;
	}
	
	public static ConsumerHandler getInstance (String groupId) {
		return new ConsumerHandler(groupId);
	}

	private Consumer<String, String> createConsumer(String groupId){
		String servers = PropertiesHelper.getValue("config.properties", "kafka.url");
		Properties props = new Properties();
		props.put("bootstrap.servers", servers);
		props.put("group.id", groupId);
		props.put("enable.auto.commit", "true");
		props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        
		return new KafkaConsumer<String, String>(props);
	}
	
	public void consume (String topic, IConsume consume) {
		consumer.subscribe(Arrays.asList(topic));
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(100);
            for (ConsumerRecord<String, String> record : records) {
            	consume.consume(record.key(), record.value());
            }
        }
	}
}
