package com.emotte.eserver.core.db;

import java.util.ArrayList;
import java.util.List;

public class ESessionManager {
	
	public static List<ESessionFactory> factoryList = new ArrayList<ESessionFactory>();
	
	public static ESessionFactory getFactory(String clazz) {
		for (ESessionFactory factory : factoryList) {
			String pack = factory.getScanPackage();
			if (pack == null) {
				return factory;
			}
			if (clazz.matches(optimizeRegex(pack))) {
				return factory;
			}
		}
		return null;
	}
	
	public static boolean addFactory (ESessionFactory... factories) {
		for (ESessionFactory factory : factories) {
			factoryList.add(factory);
		}
		return Boolean.TRUE;
	}
	
	public static boolean addFactory (List<ESessionFactory> factories) {
		for (ESessionFactory factory : factories) {
			factoryList.add(factory);
		}
		return Boolean.TRUE;
	}
	
	/**
     * 优化正则表达式
     * 规则： <br>
     * com.emotte.*.simple 匹配 com.emotte.a.sample,com.emotte.b.sample <br>
     * com.emotte.**.simple 匹配 com.emotte.a.b.c.sample,com.emotte.a.b.sample,com.emotte.a.sample
     * @param regex
     * @return
     * 2017年3月22日
     */
    private static String optimizeRegex(String regex) {
    	if (regex.contains("**")) {
			// &1& 目的是为了保护**符号
			regex = regex.replaceAll("\\*\\*", "&1&");
		}
		regex = regex.replaceAll("\\.", "\\\\.").replaceAll("\\*", "((?!\\\\.).)*") + "\\..*";
		if (regex.contains("&1&")) {
			regex = regex.replaceAll("&1&", ".*");
		}
    	return regex;
    }
}
