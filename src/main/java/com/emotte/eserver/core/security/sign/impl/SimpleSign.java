package com.emotte.eserver.core.security.sign.impl;

import com.emotte.eserver.core.security.MD5;
import com.emotte.eserver.core.security.sign.ISign;

public class SimpleSign implements ISign {

	@Override
	public String sign(String data, String privateKey) throws Exception {
		return MD5.encrypt(data + privateKey);
	}

	@Override
	public boolean verify(String data, String publicKey, String sign) throws Exception {
		return sign.equals(MD5.encrypt(data + publicKey));
	}

}
