package com.emotte.eserver.core.helper;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.dom4j.tree.DefaultElement;

public class XMLHelper {
	
	public static String getValue (String filePath, String name) {
		SAXReader reader = new SAXReader();
		Document dom = null;
		try {
			dom = reader.read(new File(filePath));
			Element ele = dom.getRootElement().element(name);
			return ele.getStringValue();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 获得属性
	 * @param filePath
	 * @return
	 * 2017年3月28日
	 */
	public static Map<String, String> readMap (String filePath) {
		Map<String, String> map = new HashMap<String, String>();
		SAXReader reader = new SAXReader();
		Document dom = null;
		try {
			dom = reader.read(new File(filePath));
			Element root = dom.getRootElement();
			for (Object object : root.elements()) {
				Element delement = (Element) object;
				map.put(delement.getName(), delement.getStringValue());
			}
			return map;
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 获得属性
	 * @param file
	 * @return
	 * 2017年3月28日
	 */
	public static Map<String, String> readMap (File file) {
		Map<String, String> map = new HashMap<String, String>();
		SAXReader reader = new SAXReader();
		Document dom = null;
		try {
			dom = reader.read(file);
			Element root = dom.getRootElement();
			for (Object object : root.elements()) {
				Element delement = (Element) object;
				map.put(delement.getName(), delement.getStringValue());
			}
			return map;
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 通过xpath查询属性
	 * @param filePath 配置文件路径
	 * @param rootXpath 
	 * @return
	 * 2017年3月28日
	 */
	public static Properties getPropertiesByXpath (String filePath, String rootXpath) {
		Properties prop = new Properties();
		SAXReader reader = new SAXReader();
		Document dom = null;
		try {
			dom = reader.read(new File(filePath));
			@SuppressWarnings("rawtypes")
			List nodes = dom.selectNodes(rootXpath);
			for (Object object : nodes) {
				DefaultElement delement = (DefaultElement) object;
				prop.put(delement.getName(), delement.getStringValue());
			}
			return prop;
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * TODO
	 * @param filePath
	 * @param name
	 * @return
	 * 2017年3月28日
	 */
	public static String getValueByXpath(String filePath, String name) {
		SAXReader reader = new SAXReader();
		Document dom = null;
		try {
			dom = reader.read(new File(filePath));
			Node node = dom.selectSingleNode(name);
			return node.getStringValue();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) {
		String path = SystemHelper.getCurrentPath(1);
		System.out.println(XMLHelper.getValue(path + "conf\\config.xml", "eserver.home"));
	}
}
