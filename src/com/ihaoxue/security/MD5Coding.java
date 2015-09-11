package com.ihaoxue.security;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import android.util.Log;

public class MD5Coding {

	public static String md5(byte[] bytes) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(bytes);

			byte[] b = md.digest();
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < b.length; i++) {
				int v = (int) b[i];
				v = v < 0 ? 0x100 + v : v;
				String cc = Integer.toHexString(v);
				if (cc.length() == 1)
					sb.append('0');
				sb.append(cc);
			}

			return sb.toString();
		} catch (Exception e) {
		}
		return "";
	}

	
	public static byte[] encode(byte[] bytes) {
		try {
			java.security.MessageDigest digest = java.security.MessageDigest
					.getInstance("MD5");
			digest.update(bytes);
			byte[] digesta = digest.digest();
			return digesta;
		} catch (Exception e) {
			return null;
		}
	}

	
	public static String encode2HexStr(byte[] bytes) {
		return HexUtil.bytes2HexStr(encode(bytes));
	}

	
	public static String encode2Base64(byte[] bytes) {
		return BASE64Coding.encode(encode(bytes));
	}

	
	public static byte[] encodeFile(String filePath) {
		try {
			java.security.MessageDigest digest = java.security.MessageDigest
					.getInstance("MD5");
			FileInputStream fis = new FileInputStream(filePath);
			byte[] buffer = new byte[1024];
			byte[] digesta = null;
			int readed = -1;
			try {
				while ((readed = fis.read(buffer)) != -1) {
					digest.update(buffer, 0, readed);
				}
				digesta = digest.digest();
			} catch (IOException e) {
				Log.e("encodeFile", "IOException:" + filePath, e);
			} finally {
				try {
					fis.close();
				} catch (IOException e) {
					Log.e("encodeFile", "IOException:" + filePath, e);
				}
			}
			return digesta;
		} catch (FileNotFoundException e) {
			Log.e("encodeFile", "IOException:" + filePath, e);
			return null;
		} catch (NoSuchAlgorithmException e) {
			Log.e("encodeFile", "IOException:" + filePath, e);
			return null;
		}
	}

	
	public static String encodeFile2HexStr(String filePath) {
		return HexUtil.bytes2HexStr(encodeFile(filePath));
	}

	
	public static String encodeFile2HexStrLocas(String filePath) {
		return HexUtil.bytes2HexStrxiao(encodeFile(filePath));
	}

	public static String encodeFile2Base64(String filePath) {
		byte[] bytes = encodeFile(filePath);
		if (bytes == null) {
			return null;
		}
		return BASE64Coding.encode(bytes);
	}
}