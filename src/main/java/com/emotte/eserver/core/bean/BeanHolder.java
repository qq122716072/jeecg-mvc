package com.emotte.eserver.core.bean;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.dom4j.Attribute;
import org.dom4j.Element;

public class BeanHolder {
	private static final String ID_ATTR = "id";
	private static final String CLASS_ATTR = "class";
	private static final String INIT_METHOD = "init-method";
	private static final String PROP_NAME_ATTR = "name";
	private static final String PROP_VALUE_ATTR = "value";
	private static final String PROP_REF_ATTR = "ref";

	private String id;
	private String clazz;
	private String initMethod;
	private Map<String, String> properties = new HashMap<String, String>();
	private Map<String, String> refs = new HashMap<String, String>();

	public static BeanHolder parseBeanElement (Element ele) {
		BeanHolder holder = new BeanHolder();
		holder.id = ele.attribute(ID_ATTR).getStringValue();
		holder.clazz = ele.attribute(CLASS_ATTR).getStringValue();
		Attribute initAttr = ele.attribute(INIT_METHOD);
		if (null != initAttr) {
			holder.initMethod = initAttr.getStringValue();
		}
		for (Object obj : ele.elements()) {
			Element element = (Element) obj;
			String name = element.attribute(PROP_NAME_ATTR).getStringValue();
			Attribute attr = element.attribute(PROP_VALUE_ATTR);
			if (null == attr) {
				attr = element.attribute(PROP_REF_ATTR);
				holder.refs.put(name, attr.getStringValue());
			} else {
				String value = attr.getStringValue();
				Pattern p = Pattern.compile("\\$\\{([\\w_\\.]+)\\}");
				Matcher m = p.matcher(value);
				while(m.find()) {
					value = value.replace(m.group(), PropertiesHolder.getValue(m.group(1)));
				}
				holder.properties.put(name, value);
			}
		}
		return holder;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getClazz() {
		return clazz;
	}
	public void setClazz(String clazz) {
		this.clazz = clazz;
	}
	public String getInitMethod() {
		return initMethod;
	}
	public void setInitMethod(String initMethod) {
		this.initMethod = initMethod;
	}
	public Map<String, String> getProperties() {
		return properties;
	}
	public void setProperties(Map<String, String> properties) {
		this.properties = properties;
	}
	public Map<String, String> getRefs() {
		return refs;
	}
	public void setRefs(Map<String, String> refs) {
		this.refs = refs;
	}
}
