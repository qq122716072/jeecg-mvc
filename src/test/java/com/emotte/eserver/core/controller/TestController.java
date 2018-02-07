package com.emotte.eserver.core.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.emotte.eserver.core.annotation.Component;
import com.emotte.eserver.core.db.local.model.Account;
import com.emotte.eserver.core.db.local.service.TestService;

@Component(initMethod="runThread")
public class TestController {
	@Resource
	private TestService testService;
	
	public void doDisplay (Map<String, Object> map) {
		List<Map<String, Object>> list = testService.queryList(map);
		System.out.println(list);
	}
	
	public void doDisplay (Account account) {
		List<Account> list = testService.queryAccountList(account);
		System.out.println(list);
	}
	
	public void runThread() {
		System.out.println("HELLO WORLD!");
	}
}
