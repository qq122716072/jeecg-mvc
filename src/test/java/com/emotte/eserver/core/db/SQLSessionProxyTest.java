package com.emotte.eserver.core.db;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.emotte.BaseTestService;
import com.emotte.eserver.core.db.finance.mapper.TestMapper2;
import com.emotte.eserver.core.db.finance.service.TestService2;
import com.emotte.eserver.core.db.local.mapper.CommentMapper;
import com.emotte.eserver.core.db.proxy.SQLSessionProxy;

public class SQLSessionProxyTest extends BaseTestService {
	@Test
	public void test() throws Exception {
		init();
		ESessionFactory factory = ESessionManager.getFactory(TestService2.class.getName());
		ConnectionManager.createConnection(factory);
		ConnectionManager.beginTransaction();
		try {
			process ();
			ConnectionManager.commit();
		} catch (Exception e) {
			e.printStackTrace();
			ConnectionManager.rollBack();
		} finally {
			ConnectionManager.closeConnection();
		}
	}

	public void process () throws Exception {
		TestMapper2 mapper = (TestMapper2) SQLSessionProxy.getInstance(TestMapper2.class);
		Map<String, Object> map = new HashMap<String, Object> ();
		map.put("start", 10);
		map.put("end", 0);
		System.out.println(mapper.query());
	}

}
