package com.emotte.eserver.core.proxy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.UndeclaredThrowableException;

import com.emotte.BaseTestService;
import com.emotte.eserver.core.context.Context;
import com.emotte.eserver.core.db.local.model.Account;
import com.emotte.eserver.core.db.local.service.TestService;
import com.emotte.kernel.helper.LogHelper;

public class ServiceProxyTest extends BaseTestService {
	
	public static void main(String[] args) throws Exception {
		init();
		TestService service = (TestService) Context.getBean("com.emotte.eserver.core.db.local.service.impl.testService-0.9");
		try {
			System.out.println(service.deleteAccount(null, new Long[]{1l,2l,3l,4l}));
		} catch (Exception e) {
			LogHelper.error(ServiceProxyTest.class, e.getMessage());
//			e.printStackTrace();
		}
//		test(service);
	}

//	@Test
	public static void test(final TestService service) throws Exception {
		for (int i = 0; i < 1; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
//					Map<String, Object> map = new HashMap<String, Object>();
					Account account = null;
					/* ****************** 查询（返回Map） **********************/
//					System.out.println(service.query());
//					System.out.println(service.queryList());
					/* ****************** 查询（返回实体类） **********************/
					// 查询
//					System.out.println(service.queryAccountList());
					// 带参查询（map）
//					map.put("id", 509444919032868l);
//					System.out.println(service.queryAccount(map));
					// 带参查询（实体类）
//					account = new Account();
//					account.setId(509444919032868l);
//					System.out.println(service.queryAccount(account));
					// 带参查询（数组）
//					System.out.println(service.queryAccount(new Object[]{509444919032868l}));
					// 带参查询（ID）
//					System.out.println(service.queryAccount(509444919032868l));
					/* ****************** 插入 **********************/
					// map插入
//					map.clear();
//					map.put("name", "cj");
//					map.put("bank", "杭州银行");
//					map.put("bankNum", "123456");
//					map.put("valid", "1");
//					System.out.println(service.insertAccount(map));
					// 对象插入
					account = new Account();
					account.setName("cj");
					account.setBank("杭州");
					account.setValid(1);
					System.out.println(service.insertAccount(account));
					// 数组插入
//					System.out.println(service.insertAccount(new Object[]{"cj", "杭州", 123456, 1}));
//					// 多参插入
//					System.out.println(service.insertAccount("cj", "杭州", null, 1));
//					/* ****************** 更新 **********************/
//					System.out.println(service.updateAccount(245134606228661l));
//					System.out.println(service.updateAccount(809210962894005l, "张三"));
//					/* ****************** 删除 **********************/
//					System.out.println(service.deleteAccount(245134606228661l));
				}
			}).start();
		}
	}
	
}
