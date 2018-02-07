package com.emotte.eserver.core.security.sign;

import org.junit.Test;

import com.emotte.eserver.core.exception.BaseException;
import com.emotte.eserver.core.security.sign.SignFactory.SIGNTYPE;

public class SignFactoryTest {

	@Test
	public void test() {
		try {
			final String params = "cj";
			TextBuilder2<String> textBuilder = new TextBuilder2<String>() {
				@Override
				public String build(String t) {
					return t;
				}
			};
			SignFactory<String> signFactory = new SignFactory<String>(SIGNTYPE.valueOf("md5"));
			String sign = signFactory.sign(textBuilder, params, "emotte");
			System.out.println(sign);
			if (!signFactory.verify(textBuilder, params, "emotte", sign)) {
				throw new BaseException("验证失败");
			}
			System.out.println("成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
