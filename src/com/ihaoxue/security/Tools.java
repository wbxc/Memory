package com.ihaoxue.security;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import android.os.Bundle;
import android.util.Log;

import com.ihaoxue.memory.Utility;

public class Tools {

	public Tools() {
		// TODO Auto-generated constructor stub
		super();
	}

	public static long CurrentTime() {
		return new Date().getTime();
	}

	public static String encrypt(String data) {

		String strMD5 = "";
		try {
			strMD5 = MD5Coding.md5(data.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			strMD5 = MD5Coding.md5(data.getBytes());
		}
		return strMD5;
	}

	@SuppressWarnings("rawtypes")
	public static String getUrlandHeaders(String url, Bundle header,
			Bundle m_bundleParams, Map<String, String> getHeaderMap) {
		if (m_bundleParams != null) {
			url = url + "?";
			Set<String> keySet = m_bundleParams.keySet();
			int nInt = 0;
			for (String key : keySet) {
				if (nInt == 0) {
					url += key + "=" + m_bundleParams.getString(key);
				} else {
					url += "&" + key + "=" + m_bundleParams.getString(key);
				}
				nInt++;
			}
		}
		if (header != null) {
			Set<String> keySetheader = header.keySet();
			for (String key : keySetheader) {
				Log.i("header", header.toString());
				url += "&" + key + "=" + header.getString(key);
			}
		}
		Map<String, String> headerMap = getHeaderMap;
		Iterator iterator = headerMap.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry entry = (Map.Entry) iterator.next();
			url += "&" + entry.getKey() + "=" + entry.getValue();
		}
		return url;
	}

	public static String getResponse(Bundle bundle) {
		Set<String> respSet = bundle.keySet();
		int i = 0;
		String resp = "";
		for (String key : respSet) {
			if (i == 0) {
				resp += key + "=" + bundle.getString(key);
			} else {
				resp += "&" + key + "=" + bundle.getString(key);
			}
			i++;
		}
		return resp;
	}
}
