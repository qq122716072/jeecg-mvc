package com.emotte.eserver.core.bean;

import java.io.InputStream;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.emotte.eserver.core.resource.Resource;
import com.emotte.eserver.core.resource.ResourceLoader;

public class BeanManager {
	
	public static void readBeans (String beansPath) throws Exception {
		SAXReader reader = new SAXReader();
		Document dom = null;
		Resource[] resources = ResourceLoader.getResources(beansPath);
		for (Resource resource : resources) {
			InputStream is = resource.getInputStream();
			try {
				dom = reader.read(is);
				Element root = dom.getRootElement();
				Element properties = root.element("properties");
				if (null != properties) {
					// 解析properties
					PropertiesHolder.parseBeanElement(properties);
				}
				for (Object obj : root.elements()) {
					Element element = (Element) obj;
					if ("bean".equals(element.getName())) {
						BeanHandler.initBean(element, root);
					}
				}
			} catch (DocumentException e) {
				e.printStackTrace();
			}
		}	
	}
	
}
