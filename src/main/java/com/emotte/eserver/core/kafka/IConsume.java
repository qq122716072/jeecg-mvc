package com.emotte.eserver.core.kafka;

public interface IConsume {
	public void consume(String key, String value);
}
