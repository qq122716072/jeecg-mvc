package com.emotte;

import org.junit.Test;

import com.emotte.eserver.core.helper.PropertiesHelper;
import com.emotte.eserver.dispatcher.Dispatcher;

import net.sf.json.JSONObject;

public class BaseTest {
	@Test
	public void test() {
		JSONObject json = new JSONObject();
		json.accumulate("name", "cj");
		json.accumulate("age", 1);
		JSONObject result = Dispatcher.getInstance().invoke(json.toString());
		System.out.println(result.toString());
	}
	@Test
	public void readProperties () {
		String s = PropertiesHelper.getValue("config.properties", "kafka.url", "localhost");
		System.out.println(s);
	}
}
