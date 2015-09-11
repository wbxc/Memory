package com.ihaoxue.memory.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class TripleDES {

	/** 加密方式 */
	private final static String DES = "DESede";

	private final static String KEY_NUVA_KEY = "0123456789QWEQWEEWQQ1234";

	/**
	 * 密码加密
	 * 
	 * @param data
	 * 
	 * @return
	 * @throws Exception
	 */
	public final static String encrypt(String data, String key) {
		try {
			return byte2hex(encrypt(data.getBytes(), key.getBytes()));
		} catch (Exception e) {
		}
		return null;
	}

	/**
	 * 加密
	 * 
	 * @param src
	 *            数据源
	 * @param key
	 *            密钥，长度必须是24的倍数
	 * @return 返回加密后的数据
	 * @throws Exception
	 */
	public static byte[] encrypt(byte[] src, byte[] key) throws Exception {
		SecretKeySpec securekey = new SecretKeySpec(key, DES);
		// Cipher对象实际完成加密操作
		Cipher cipher = Cipher.getInstance(DES);
		// 用密匙初始化Cipher对象
		cipher.init(Cipher.ENCRYPT_MODE, securekey);
		// 现在，获取数据并加密
		// 正式执行加密操作
		return cipher.doFinal(src);
	}

	/**
	 * 解密
	 * 
	 * @param src
	 *            数据源
	 * @param key
	 *            密钥，长度必须是24的倍数
	 * @return 返回解密后的原始数据
	 * @throws Exception
	 */
	public static byte[] decrypt(byte[] src, byte[] key) throws Exception {
		SecretKeySpec securekey = new SecretKeySpec(key, DES);
		// Cipher对象实际完成解密操作
		Cipher cipher = Cipher.getInstance(DES);
		// 用密匙初始化Cipher对象
		cipher.init(Cipher.DECRYPT_MODE, securekey);
		// 现在，获取数据并解密
		// 正式执行解密操作
		byte[] a = cipher.doFinal(src);
		return a;
	}

	/**
	 * 二行制转字符串
	 * 
	 * @param b
	 * @return
	 */
	public static String byte2hex(byte[] b) {
		StringBuilder hs = new StringBuilder();
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1)
				hs.append("0" + stmp);
			else
				hs.append(stmp);
		}
		return hs.toString().toUpperCase();
	}

	public static byte[] hex2byte(String hex) throws IllegalArgumentException {
		if (hex.length() % 2 != 0) {
			throw new IllegalArgumentException("长度不是偶数");
		}
		char[] arr = hex.toCharArray();
		byte[] b = new byte[hex.length() / 2];
		for (int i = 0, j = 0, l = hex.length(); i < l; i++, j++) {
			String swap = "" + arr[i++] + arr[i];
			int byteint = Integer.parseInt(swap, 16) & 0xFF;
			b[j] = new Integer(byteint).byteValue();
		}
		return b;
	}

	/**
	 * key加密
	 * 
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public final static String keyEncrypt(String password) {
		try {
			return byte2hex(encrypt(password.getBytes(),
					KEY_NUVA_KEY.getBytes()));
		} catch (Exception e) {
		}
		return null;
	}

	/**
	 * key解密
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public final static String keyDecrypt(String data) {
		try {
			return new String(decrypt(hex2byte(data), KEY_NUVA_KEY.getBytes()));
		} catch (Exception e) {
		}
		return null;
	}

	/**
	 * key加密
	 * 
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public static String keyMd5(String sourceStr) {
		String result = "";
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(sourceStr.getBytes());
			byte b[] = md.digest();
			int i;
			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			result = buf.toString();
			//System.out.println("result: " + result);// 32位的加密
			// System.out.println("result: " +
			// buf.toString().substring(8,24));//16位的加密
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block e.printStackTrace();
		}
		return result;
	}
	
	
	public static String TestMd5Key(String str){
		MessageDigest mdDigest = null;
		StringBuffer sBuffer = new StringBuffer() ;
		try {
			mdDigest = MessageDigest.getInstance("MD5") ;
			char[] charArray = str.toCharArray() ;
			byte[] byteArray = new byte[charArray.length] ;
			for(int i=0 ; i < charArray.length ; i++){
				byteArray[i] = (byte) charArray[i] ;
			}
			byte[] md5Byte = mdDigest.digest(byteArray) ;
			for (int i = 0; i < md5Byte.length; i++) {
				int val = ((int)md5Byte[i])&0xff ;
				
				if (val<16) {
					sBuffer.append("0") ;
				}
				sBuffer.append(Integer.toHexString(val)) ;
			}
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sBuffer.toString() ;
	}
	 /**
	　　* 加密解密算法 执行一次加密，两次解密
	　　*/
	public static String converMD5(String md5String){
		char[] charArray = md5String.toCharArray() ;
		for (int i = 0; i < charArray.length; i++) {
			charArray[i] = (char)(charArray[i]^'t') ;
		}
		String str = new String(charArray) ;
		return str ;
	}
	
	public static String decryptMD5(String str){
		
		return converMD5(converMD5(str)) ;
	}
	
}
