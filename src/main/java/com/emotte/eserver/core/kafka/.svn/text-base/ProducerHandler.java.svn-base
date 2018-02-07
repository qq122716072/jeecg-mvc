package com.emotte.eserver.core.kafka;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import com.emotte.eserver.core.helper.LogHelper;
import com.emotte.eserver.core.helper.PropertiesHelper;

public class ProducerHandler {
	private static Producer<String, String> producer;
	private static ProducerHandler hanlder;
	
	private ProducerHandler () {}
	
	public static ProducerHandler getInstance() {
		if (hanlder == null) {
			hanlder = new ProducerHandler();
			producer = hanlder.createProducer();
		}
		return hanlder;
	}
	
	private Producer<String, String> createProducer() {
		String servers = PropertiesHelper.getValue("config.properties", "kafka.url");
		Properties props = new Properties();
		props.put("bootstrap.servers", servers);
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		return new KafkaProducer<String, String>(props);
	}
	
	public void produce(String topic, String key, String value) {
		ProducerRecord<String, String> record  = new ProducerRecord<String, String>(topic, key, value);
		producer.send(record);
		LogHelper.debug(getClass(), "produce:key=" + key + ", value=" + value);
	}
}
