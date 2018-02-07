package com.emotte.eserver.core.bean;

import org.junit.Test;

import com.emotte.eserver.core.context.Context;
import com.emotte.eserver.core.helper.ServerHelper;

public class BeanManagerTest {

	@Test
	public void test() {
		try {
			BeanManager.readBeans(ServerHelper.getConfPath() + "context1.xml");
			System.out.println(Context.getBean("sqlSessionFactory"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
