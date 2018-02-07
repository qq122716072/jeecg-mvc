package com.emotte.eserver.core.db.finance.service.impl;

import javax.annotation.Resource;

import com.emotte.eserver.core.annotation.Service;
import com.emotte.eserver.core.db.finance.mapper.TestMapper2;
import com.emotte.eserver.core.db.finance.model.Comfirm;
import com.emotte.eserver.core.db.finance.service.TestService2;
@Service(value="serviceB")
public class TestService2Impl implements TestService2 {
	@Resource
	private TestMapper2 test;
	@Override
	public String getAccount () {
		System.out.println("Congratulations");
		Comfirm account = test.queryAccount();
//		test.query();
		return account.toString();
	}
}
