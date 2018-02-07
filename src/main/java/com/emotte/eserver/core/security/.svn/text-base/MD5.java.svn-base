package com.emotte.eserver.core.security;

import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;

import com.emotte.eserver.core.helper.LogHelper;

/**
 * MD5加密工具
 * @author ChengJing
 * 2016年4月12日
 */
public class MD5 {
	
	private static final String CHARSET = "UTF-8";
	
	public static String encrypt(String text) {
		try {
			byte[] bytes = null;
			if (StringUtils.isEmpty(CHARSET)) {
				bytes = text.getBytes();
			} else {
				bytes = text.getBytes(CHARSET);
			}
			return DigestUtils.md5Hex(bytes);
		} catch (UnsupportedEncodingException e) {
			LogHelper.error(MD5.class, "MD5签名过程中出现错误,指定的编码集不对,您目前指定的编码集是:" + CHARSET, e);
			return null;
		}
	}
	
	public static void main(String[] args) {
		System.out.println(MD5.encrypt("powergrass"));
	}

}