package com.emotte.eserver.core.security.sign.impl;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import com.emotte.eserver.core.security.sign.ISign;

public class RSASign implements ISign {
	
	/**
	 * 数字签名
	 * 密钥算法
	 * 2017年2月13日
	 */
	private static final String KEY_ALGORITHM = "RSA";
	/**
	 * 数字签名
	 * 签名/验证算法
	 * 2017年2月13日
	 */
	private static final String SIGNATURE_ALGORITHM = "MD5withRSA";
	/**
	 * 公钥
	 * 2017年2月13日
	 */
	private static final String PUBLIC_KEY = "RSAPublicKey";
	/**
	 * 私钥
	 * 2017年2月13日
	 */
	private static final String PRIVATE_KEY = "RSAPrivateKey";
	/**
	 * RSA密钥长度 默认1024
	 * 长度必须是64的倍数
	 * 范围在512~65536之间
	 * 2017年2月13日
	 */
	private static final int KEY_SIZE = 512;
	
	public String sign(String data, String privateKey) throws Exception {
		byte[] sign = sign(data.getBytes(), decryptBASE64(privateKey));
		return encryptBASE64(sign);
	}

	public boolean verify(String data, String publicKey, String sign) throws Exception {
		return verify(data.getBytes(), decryptBASE64(publicKey), decryptBASE64(sign));
	}
	
	private byte[] sign(byte[] data, byte[] privateKey) {
		byte[] sign = null;
		try {
			// 转换私钥材料
			PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(privateKey);
			// 实例化密钥工厂
			KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
			// 取私钥对象
			PrivateKey priKey = keyFactory.generatePrivate(pkcs8KeySpec);
			// 实例化Signature
			Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
			// 初始化Signature
			signature.initSign(priKey);
			signature.update(data);
			sign = signature.sign();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (SignatureException e) {
			e.printStackTrace();
		}
		return sign;
	}

	private boolean verify(byte[] data, byte[] publicKey, byte[] sign) {
		boolean res = false;
		try {
			// 转换公钥材料
			X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKey);
			// 实例化密钥工厂
			KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
			// 生成公钥
			PublicKey pubKey = keyFactory.generatePublic(keySpec);
			// 实例化Signature
			Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
			// 初始化Signature
			signature.initVerify(pubKey);
			// 更新
			signature.update(data);
			// 验证
			res = signature.verify(sign);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (SignatureException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	//解码返回byte
    public byte[] decryptBASE64(String key) throws Exception {
        return (new BASE64Decoder()).decodeBuffer(key);
    }

    //编码返回字符串
    public String encryptBASE64(byte[] key) throws Exception {
        return (new BASE64Encoder()).encodeBuffer(key);
    }
    
	class KeyGenerator {
		private Map<String, Object> keyMap;
		public String getPrivateKey () throws Exception {
			Key key = (Key) keyMap.get(PRIVATE_KEY);
			return encryptBASE64(key.getEncoded());
		}
		
		public String getPublicKey () throws Exception {
			Key key = (Key) keyMap.get(PUBLIC_KEY);
			return encryptBASE64(key.getEncoded());
		}
		
		public void initKey () {
			try {
				// 实例化密钥对生成器
				KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
				// 初始化密钥对生成器
				keyPairGen.initialize(KEY_SIZE);
				// 生成密钥对
				KeyPair keyPair = keyPairGen.generateKeyPair();
				// 公钥
				RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
				// 私钥
				RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
				// 封装密钥对
				Map<String, Object> keyMap = new HashMap<String, Object>();
				keyMap.put(PUBLIC_KEY, publicKey);
				keyMap.put(PRIVATE_KEY, privateKey);
				this.keyMap = keyMap;
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		RSASign signService = new RSASign();
		KeyGenerator generator = signService.new KeyGenerator();
		generator.initKey();
		String publicKey = generator.getPublicKey();
		String privateKey = generator.getPrivateKey();
		System.out.println("publicKey: " + publicKey);
		System.out.println("privateKey: " + privateKey);
	}

}
