package com.emotte.eserver.core.security.sign;


/**
 * 签名
 * @author ChengJing
 * 2017年2月13日
 */
public interface ISign {
	/**
	 * 签名
	 * @param data
	 * @param privateKey
	 * @return
	 * 2017年3月23日
	 */
	public String sign(String data, String privateKey) throws Exception;
	
	/**
	 * 验证
	 * @param data
	 * @param publicKey
	 * @param sign
	 * @return
	 * 2017年3月23日
	 */
	public boolean verify (String data, String publicKey, String sign) throws Exception;
}
