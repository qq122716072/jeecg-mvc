package com.emotte.eserver.core.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PropertiesHelper {
	private static Properties properties = new Properties();
	
	public static void addProperties (Map<String, String> map) {
		properties.putAll(map);
	}
	public static Properties getProperties() {
		return properties;
	}
	/**
	 * 获得property文件中[name]对应的值
	 * @param path 配置文件名
	 * @param name 属性名
	 * @return properties文件中[name]对应的值
	 * 2014年10月31日
	 */
	public static String getValue (String path, String name) {
		String value = properties.getProperty(name);
		if (null != value) {
			return value;
		}
		value = readContent(path, name);
		Pattern p = Pattern.compile("\\$\\{([\\w_]+)\\}");
		Matcher m = p.matcher(value);
		while(m.find()) {
			value = value.replace(m.group(), readContent(path, m.group(1)));
		}
		return value;
	}
	
	/**
	 * 获得property文件中[name]对应的值
	 * @param path 配置文件名
	 * @param name 属性名
	 * @param defaultValue 默认值
	 * @return
	 * 2016年5月6日
	 */
	public static String getValue (String path, String name, String defaultValue) {
		String value = properties.getProperty(name);
		if (null != value) return value;
		value = readContent(path, name);
		if (null == value || "".equals(value)) return defaultValue;
		Pattern p = Pattern.compile("\\$\\{([\\w_]+)\\}");
		Matcher m = p.matcher(value);
		while(m.find()) {
			value = value.replace(m.group(), readContent(path, m.group(1)));
		}
		return value;
	}
	
    /**
     * 读取动态数据
     * @param filePath
     * @param key
     * @return
     * 2015年9月10日
     */
    private static String readContent(String filePath, String key) {
    	Properties props = new Properties();
		String value = null;
		URL url = PropertiesHelper.class.getClassLoader().getResource(filePath);
		if (null != url) {
			try {
				props.load(getInputStream(filePath));
				value = new String(props.getProperty(key).getBytes("ISO-8859-1"), "UTF-8"); // 处理中文乱码
			} catch (FileNotFoundException e) {
				LogHelper.error(PropertiesHelper.class, e.getMessage(), e);
			} catch (IOException e) {
				LogHelper.error(PropertiesHelper.class, e.getMessage(), e);
			}
		}
		return value;
    }
    
    public static Map<String, String> readMap (String filePath) {
    	Map<String, String> map = new HashMap<String, String>();
    	Properties props = new Properties();
		try {
			props.load(new FileInputStream(new File(filePath)));
			for (Entry<Object, Object> entry : props.entrySet()) {
				String key = new String(entry.getKey().toString().getBytes("ISO-8859-1"), "UTF-8");
				String value = new String(props.getProperty(key).getBytes("ISO-8859-1"), "UTF-8"); // 处理中文乱码
				map.put(key, value);
			}
			return map;
		} catch (FileNotFoundException e) {
			LogHelper.error(PropertiesHelper.class, e.getMessage(), e);
		} catch (IOException e) {
			LogHelper.error(PropertiesHelper.class, e.getMessage(), e);
		}
		return null;
    }
    
    public static Map<String, String> readMap (File file) {
    	Map<String, String> map = new HashMap<String, String>();
    	Properties props = new Properties();
		try {
			props.load(new FileInputStream(file));
			for (Entry<Object, Object> entry : props.entrySet()) {
				String key = new String(entry.getKey().toString().getBytes("ISO-8859-1"), "UTF-8");
				String value = new String(props.getProperty(key).getBytes("ISO-8859-1"), "UTF-8"); // 处理中文乱码
				map.put(key, value);
			}
			return map;
		} catch (FileNotFoundException e) {
			LogHelper.error(PropertiesHelper.class, e.getMessage(), e);
		} catch (IOException e) {
			LogHelper.error(PropertiesHelper.class, e.getMessage(), e);
		}
		return null;
    }
    
    public static Map<String, String> readContent (String fileName) {
    	Map<String, String> map = new HashMap<String, String>();
    	Properties props = new Properties();
		try {
			props.load(getInputStream(fileName));
			for (Entry<Object, Object> entry : props.entrySet()) {
				String key = new String(entry.getKey().toString().getBytes("ISO-8859-1"), "UTF-8");
				String value = new String(props.getProperty(key).getBytes("ISO-8859-1"), "UTF-8"); // 处理中文乱码
				map.put(key, value);
			}
			return map;
		} catch (FileNotFoundException e) {
			LogHelper.error(PropertiesHelper.class, e.getMessage(), e);
		} catch (IOException e) {
			LogHelper.error(PropertiesHelper.class, e.getMessage(), e);
		}
		return null;
    }
    
    public static Map<String, String> readContent (InputStream is) {
    	Map<String, String> map = new HashMap<String, String>();
    	Properties props = new Properties();
		try {
			props.load(is);
			for (Entry<Object, Object> entry : props.entrySet()) {
				String key = new String(entry.getKey().toString().getBytes("ISO-8859-1"), "UTF-8");
				String value = new String(props.getProperty(key).getBytes("ISO-8859-1"), "UTF-8"); // 处理中文乱码
				map.put(key, value);
			}
			return map;
		} catch (FileNotFoundException e) {
			LogHelper.error(PropertiesHelper.class, e.getMessage(), e);
		} catch (IOException e) {
			LogHelper.error(PropertiesHelper.class, e.getMessage(), e);
		}
		return null;
    }
    
	private static InputStream getInputStream(String fileName) {
		return PropertiesHelper.class.getClassLoader().getResourceAsStream(fileName);
	}
    
}
