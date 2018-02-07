package com.emotte.eserver.core.proxy;

import java.util.HashMap;
import java.util.Map;

import com.emotte.BaseTestService;
import com.emotte.eserver.core.context.Context;
import com.emotte.eserver.core.controller.TestController;
import com.emotte.eserver.core.db.local.model.Account;
import com.emotte.eserver.core.db.local.service.TestService;
import com.emotte.eserver.interf.EAccountService;

public class ControllerTest extends BaseTestService {
	
	public static void main(String[] args) throws Exception {
		init();
		test();
	}

	public static void test() throws Exception {
		Account account = new Account();
		account.setName("北京易盟天地信息技术有限公司包头分公司");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", "616297093509556");
		TestService service = (TestService) Context.getBean("testService" + "-0.9");
		System.out.println(service.queryAccount(map));
	}
	
}
