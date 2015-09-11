package com.ihaoxue.memory.net;

import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 网络头请求参数配置
 * @author weibin
 */
public class NetConfiguration {

	public Map<String, String> m_mapHeader;

	public NetConfiguration() {
		m_mapHeader = new HashMap<String, String>();
	}

	public void addHeaderKey(String[] arrKey) {
		if (arrKey == null || m_mapHeader == null)
			return;

		for (int i = 0; i < arrKey.length; i++) {
			if (arrKey[i] != null)
				m_mapHeader.put(arrKey[i], "");
		}
	}

	public void setHeaderValue(String strKey, String strVal) {
		if (m_mapHeader != null) {
			if (m_mapHeader.containsKey(strKey))
				m_mapHeader.put(strKey, strVal);
		}
	}

	public void setHeaderProperty(HttpURLConnection connection) {
		Iterator<Map.Entry<String, String>> iter = m_mapHeader.entrySet()
				.iterator();
		while (iter.hasNext()) {
			Map.Entry<String, String> entry = (Map.Entry<String, String>) iter
					.next();
			String strKey = entry.getKey();
			String strVal = entry.getValue();
			try {
				connection.setRequestProperty(strKey,
						new String(strVal.getBytes(), "UTF-8"));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static final String url_prefix = "" ;
	public static final String HTTP_HEADER_PACKETVERSION = "curversion";
}
