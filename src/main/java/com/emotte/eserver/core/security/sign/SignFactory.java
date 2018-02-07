package com.emotte.eserver.core.security.sign;

import com.emotte.eserver.core.exception.SignException;
import com.emotte.eserver.core.security.sign.impl.RSASign;
import com.emotte.eserver.core.security.sign.impl.SimpleSign;

public class SignFactory<T> {
	protected ISign signService;
	
	public SignFactory(SIGNTYPE signType) {
		switch (signType) {
		case rsa : signService = new RSASign(); break;
		case md5 : signService = new SimpleSign(); break;
		}
	}
	public String sign(TextBuilder2<T> builder, T data, String privateKey) throws SignException {
		try {
			return signService.sign(builder.build(data), privateKey);
		} catch (Exception e) {
			throw new SignException("签名错误");
		}
	}
	
	public boolean verify(TextBuilder2<T> builder, T data, String publicKey, String sign) throws SignException {
		try {
			return signService.verify(builder.build(data), publicKey, sign);
		} catch (Exception e) {
			throw new SignException("验证签名错误");
		}
	}
	
	public enum SIGNTYPE {
		rsa,md5
	}
}
