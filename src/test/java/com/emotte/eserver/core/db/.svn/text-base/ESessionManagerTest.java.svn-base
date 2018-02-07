package com.emotte.eserver.core.db;

import org.junit.Test;

import com.emotte.eserver.core.db.local.mapper.TestMapper;

public class ESessionManagerTest {

	@Test
	public void test() {
		ESessionFactory factory = new ESessionFactory();
		factory.setScanPackage("com.emotte.**.service");
		ESessionFactory factory2 = new ESessionFactory();
		factory2.setScanPackage("com.emotte.**.mapper");
		ESessionManager.addFactory(factory);
		ESessionManager.addFactory(factory2);
		ESessionFactory factory_ = ESessionManager.getFactory(TestMapper.class.getName());
		System.out.println(factory_);
	}

}
