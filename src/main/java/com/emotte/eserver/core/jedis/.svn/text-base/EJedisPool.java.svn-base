package com.emotte.eserver.core.jedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class EJedisPool {
	private String host;
	private String port;
	private String pass;
	private JedisPoolConfig config;
	
	private JedisPool pool;
	
	public void initMethod() {
		if ( pool == null ) {
			pool = new JedisPool(config, host, Integer.parseInt(port), 5000, pass);
		}
	}
	
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	
	public Jedis getResource() {
		return pool.getResource();
	}
	
	public void close () {
		pool.close();
	}
}
