package com.emotte;

import java.io.IOException;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import com.emotte.eserver.core.exception.ScanException;

public class Main {

	public static void main(String[] args) throws IOException, ClassNotFoundException {
//		String jarPath = "D:\\Eserver2\\service\\serviceLib\\amib-1.2.9.jar";
		String jarPath = "D:\\Eserver2\\service\\serviceLib\\amibApi-1.0.jar";
		
		/*JSONObject obj = new JSONObject();
		Object[] pars = new Object[]{1l};
		Class<?>[] clazzes = new Class[pars.length];
		for (int i = 0; i < pars.length; i++) {
			clazzes[i] = pars[i].getClass();
		}
		obj.accumulate("params", pars);
		obj.accumulate("paramsTypes", clazzes);
		JSONObject object = JSONObject.fromObject(obj.toString());
		
		JSONArray params = object.getJSONArray("params");
		JSONArray paramsTypes = object.getJSONArray("paramsTypes");
		Object[] objs = (Object[]) JSONArray.toArray(params, Object.class);
		String[] classNames = (String[]) JSONArray.toArray(paramsTypes, String.class);
		Class<?>[] classes = new Class<?>[classNames.length];
		for (int i = 0; i < classNames.length; i++) {
			classes[i] = Class.forName(classNames[i]);
		}
		System.out.println(objs);
		System.out.println(classes);*/
		
		JarFile jarFile = new JarFile(jarPath);
		try {  
            Enumeration<JarEntry> entrys = jarFile.entries();  
            while (entrys.hasMoreElements()) {  
                JarEntry jarEntry = entrys.nextElement();  
                String entryName = jarEntry.getName();  
                System.out.println(entryName);
            }  
        } catch (Exception e) {  
        	throw new ScanException(e);
        }
	}

}
