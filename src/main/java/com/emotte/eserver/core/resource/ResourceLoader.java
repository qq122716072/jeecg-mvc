package com.emotte.eserver.core.resource;

import java.io.IOException;

import com.emotte.eserver.core.helper.ServerHelper;
import com.emotte.eserver.core.resource.impl.ClassPathResource;
import com.emotte.eserver.core.resource.impl.FileResource;

public class ResourceLoader {
	static String CLASSPATH_URL_PREFIX = "classpath:";
	static String CLASSPATH_ALL_URL_PREFIX = "classpath*:";
	static String FILE_CURRENT_PREFIX = "./";
	static String FILE_CURRENT_PREFIX_2 = ".\\";
	public static Resource getResource(String location) throws IOException {
		if (location.startsWith(CLASSPATH_URL_PREFIX)) {
			location = location.substring(CLASSPATH_URL_PREFIX.length());
			return new ClassPathResource(location);
		} else {
			if (location.startsWith(FILE_CURRENT_PREFIX) || location.startsWith(FILE_CURRENT_PREFIX_2)) {
				location = ServerHelper.getConfPath() + location.substring(FILE_CURRENT_PREFIX.length());
			}
			return new FileResource(location);
		}
	}
	public static Resource[] getResources(String locationPattern) throws IOException {
		if (locationPattern.startsWith(CLASSPATH_ALL_URL_PREFIX)) {
			return null;
		} else if (locationPattern.startsWith(CLASSPATH_URL_PREFIX)) {
			locationPattern = locationPattern.substring(CLASSPATH_URL_PREFIX.length());
			return new Resource[]{new ClassPathResource(locationPattern)};
		} else {
			if (locationPattern.startsWith(FILE_CURRENT_PREFIX)) {
				locationPattern = ServerHelper.getConfPath() + locationPattern.substring(FILE_CURRENT_PREFIX.length());
			}
			return new Resource[]{new FileResource(locationPattern)};
		}
	}
}
