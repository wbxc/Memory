package com.ihaoxue.security;

import android.util.Log;

/**
 * BASE64�Ĺ�����
 * 
 * @author kevinxu
 * 
 */
public class BASE64Coding {
	public BASE64Coding() {
	}

	/**
	 * ��ϵͳĬ�ϱ���encode���ַ�
	 * 
	 * @param s
	 * @return String
	 */
	public static String encode(String s) {
		return encode(s.getBytes());
	}

	/**
	 * ���ֽ��������encode
	 * 
	 * @param bytes
	 * @return String
	 */
	public static String encode(byte[] bytes) {
		return Base64.encode(bytes);
	}

	/**
	 * ��BASE64���ַ����decode����decodeʧ�ܣ��򷵻�null
	 * 
	 * @param str
	 * @return byte[]
	 */
	public static byte[] decode(String str) {
		try {
			return new BASE64Decoder().decodeBuffer(str);
		} catch (Exception e) {
			Log.e("BASE64Coding", "decode error:", e);
			return null;
		}
	}

}