package com.emotte.eserver.core.bean;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.dom4j.Element;

import com.emotte.eserver.core.helper.PropertiesHelper;
import com.emotte.eserver.core.resource.Resource;
import com.emotte.eserver.core.resource.ResourceLoader;

public class PropertiesHolder {
	private static Map<String, String> properties = new HashMap<String, String>();
	private static final String LOCATION_ATTR = "location";
	public static void parseBeanElement (Element ele) throws IOException {
		String location = ele.attribute(LOCATION_ATTR).getStringValue();
		Resource[] resources = ResourceLoader.getResources(location);
		for (Resource resource : resources) {
			Map<String, String> map = PropertiesHelper.readContent(resource.getInputStream());
			properties.putAll(map);
		}
	}
	public static String getValue (String value) {
		return properties.get(value);
	}
}
