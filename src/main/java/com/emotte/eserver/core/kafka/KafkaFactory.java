package com.emotte.eserver.core.kafka;

import java.util.Properties;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import com.emotte.eserver.core.helper.PropertiesHelper;


public class KafkaFactory {
	
	public static <T,V> Producer<T,V> createProducer(){
		String servers = PropertiesHelper.getValue("config.properties", "kafka.url");
		Properties props = new Properties();
		props.put("bootstrap.servers", servers);
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		return new KafkaProducer<T, V>(props);
	}
	
	public static <T,V> Consumer<T,V> createConsumer(String groupId){
		String servers = PropertiesHelper.getValue("config.properties", "kafka.url");
		Properties props = new Properties();
		props.put("bootstrap.servers", servers);		
		props.put("group.id", groupId);
		props.put("auto.commit.interval.ms", "1000");
		props.put("enable.auto.commit", "true");
		props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		return new KafkaConsumer<T,V>(props);
	}
	

	public static void main(String[] args) {
		Producer<String,String> producer = KafkaFactory.createProducer();
		ProducerRecord<String, String> record  = new ProducerRecord<String, String>("testtttttt","myname","caolc");
		producer.send(record);
		
		producer.close();
		
		/*Consumer<String, String> consumer = KafkaFactory.createConsumer("aaa");
		consumer.subscribe(Arrays.asList("test"));
        while (true) {
        	
            ConsumerRecords<String, String> records = consumer.poll(100);
            for (ConsumerRecord<String, String> record : records) {
                System.out.println("offset = %d, key = %s, value = %s" + record.offset() + record.key() +  record.value());
            }
        }*/
		
	}
	
	
}
