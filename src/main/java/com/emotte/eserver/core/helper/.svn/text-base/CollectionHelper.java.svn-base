package com.emotte.eserver.core.helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.emotte.eserver.core.annotation.Api.REQUESTYPE;

public class CollectionHelper {
	
	/**
	 * 取出list1中包含的list2中的api并返回去重后的list1
	 * yj
	 * 2017年12月1日	
	 * @param list1 
	 * @param list2
	 * @return
	 * List<Map<String,Object>>
	 */
	public static List<Map<String,Object>> removeDuplicate(List<Map<String, Object>> list1,List<Map<String, Object>> list2){
			Iterator<Map<String, Object>> list = list1.iterator();
			while(list.hasNext()){
				Map<String, Object> tmp = list.next();
				for (Map<String, Object> map : list2) {
					if(tmp.get("version").equals(map.get("version")) && tmp.get("method").equals(map.get("method"))
							&& tmp.get("service").equals(map.get("service"))){
						list.remove();
						
					}
				}
			}
		
		return list1;
	}
	public static void main(String[] args) {
		List<Map<String, Object>> list = new ArrayList<>();
		List<Map<String, Object>> list1 = new ArrayList<>();
		Map<String, Object> map = new HashMap<>();
		Map<String, Object> map1 = new HashMap<>();
		Map<String, Object> map3 = new HashMap<>();
		map.put("VERSION", "1");
		map.put("METHOD", "2");
		map.put("SERVICE", "3"); 
		map1.put("VERSION", "2");
		map1.put("METHOD", "2");
		map1.put("SERVICE", "3");
		map1.put("name", "3");
		map3.put("a", true);
		map3.put("c", REQUESTYPE.POST);
		list.add(map);
		list.add(map1);
		list1.add(map);
		List<Map<String, Object>> list3 = CollectionHelper.removeDuplicate(list, list1);
		System.out.println(list3);
		
		for (Map.Entry<String, Object>entry:map3.entrySet()) {
			System.out.println(entry.getValue());
		}
	}
}
