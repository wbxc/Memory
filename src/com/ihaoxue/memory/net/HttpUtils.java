package com.ihaoxue.memory.net;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

import org.json.JSONObject;

import android.content.Context;
import android.os.Bundle;

import com.ihaoxue.memory.db.CacheDBService;
import com.ihaoxue.memory.db.DBOpenHelperSQlite;
import com.ihaoxue.security.Tools;


public class HttpUtils {

	private static int BUFFER_SIZE = 8192;
	private static  Bundle RespHeaderBundle ;
	
	public static final String PACKET_DATA = "data";   //数据包
	public static final String PACKET_HEADER = "header";  //数据头
	public static final String PACKET_RESPONSE_HEADER = "response_header";  // 请求的返回头
	
	
	public static String POST = "POST";
	public static String GET = "GET";
	public static final String POST_ECODEING = "UTF-8" ;
	public static final String GBK_ECODEING = "GBK" ;
	
	/**
	 * 将输入流转变成为字符串
	 * @param in  
	 * @param encoding
	 * @return
	 * @throws Exception
	 */
	private static String InputStreamToString(InputStream in, String encoding)
			throws Exception {
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] data = new byte[BUFFER_SIZE];
		int count = -1;
		while ((count = in.read(data, 0, BUFFER_SIZE)) != -1)
			outStream.write(data, 0, count);
		data = null;
		return new String(outStream.toByteArray(), encoding);
	}
	
	
	/**
	 * 
	 * @param in 传递来的输入流
	 * @param version 当前版本
	 * @return
	 * @throws Exception 
	 */
	private static JSONObject streamToJSON(InputStream in, String version, String ecoding)
			throws Exception {
		String content = InputStreamToString(in, ecoding);
		if (content == null) {
			return null;
		} else if (content.equals("")) {
			JSONObject jo = new JSONObject();
			jo.put(NetConfiguration.HTTP_HEADER_PACKETVERSION, version);
			return jo;
		}
		JSONObject json = new JSONObject(content);
		json.put(NetConfiguration.HTTP_HEADER_PACKETVERSION, version);
		return json;
	}
	
	 // 6227 0001 6738 0074427
	/**
	 * @param context  上下文对象
	 * @param url  请求的URL 最原始的连接
	 * @param headers 请求头文件参数
	 * @param params post请求参数
	 * @param conf  请求配置
	 * @param requesttype 请求类型post与get
	 * @param keepalive  
	 * @param mapHeader 头Map
	 * @return  返回json数据
	 * @throws Exception
	 */
	public static JSONObject getJSON(Context context, String url,
			Bundle headers, Bundle params, NetConfiguration conf,
			String requesttype, int keepAlive, Map<String, String> mapHeader,String ecoding)
			throws Exception {
		JSONObject oResult = null;
		// 判断数据缓存是否有
		if (POST.equals(requesttype)) {
			oResult = getJSONByPost(url, headers, params, false, conf, keepAlive,ecoding) ;
		}else if(GET.equals(requesttype)) {
			oResult = getJSONByGet(url, headers, conf, keepAlive,ecoding) ;
		}
		return oResult;
	}
	
	/**
	 * POST 请求获取数据
	 * @param url
	 * @param headers
	 * @param params
	 * @param withResponseHeader   判断是否返回有返回头
	 * @param conf   网络请求的配置
	 * @param keepAlive
	 * @return
	 * @throws Exception
	 */
	public static JSONObject getJSONByPost(String url, Bundle headers,
			Bundle params, Boolean withResponseHeader, NetConfiguration conf,
			int keepAlive,String ecodeing) throws Exception {
		URL urlTemp;
		
		urlTemp = new URL(NetConfiguration.url_prefix + url);
		HttpURLConnection connection = (HttpURLConnection) urlTemp.openConnection();
		InputStream in;
		String ver = "";
		try {
			in = internalPost(connection, headers, params, conf, keepAlive);
			
//			ver = connection.getHeaderField(NetConfiguration.HTTP_HEADER_PACKETVERSION);
//			if (ver == null) {
//				if (headers != null) {
//					if (headers.containsKey(NetConfiguration.HTTP_HEADER_PACKETVERSION)) {
//						ver = headers.getString(NetConfiguration.HTTP_HEADER_PACKETVERSION);
//					} else {
//						ver = "";
//					}
//				}
//			}
			JSONObject json = streamToJSON(in, ver ,ecodeing);
			
			
			
			if (withResponseHeader) {
				JSONObject header = new JSONObject();
				Map<String, List<String>> rhead = connection.getHeaderFields();
				for (Entry<String, List<String>> entry : rhead.entrySet()) {
					if (entry.getKey() != null) {
						if (entry.getValue().get(0) == null
								|| entry.getValue().get(0).equals("null")) {
							header.put(entry.getKey().toLowerCase(Locale.US),
									"0");
						} else {
							header.put(entry.getKey().toLowerCase(Locale.US),
									entry.getValue().get(0));
						}
						// entry.getKey()+":"+entry.getValue());
					}
				}
				json.put(PACKET_RESPONSE_HEADER, header);
			}
			return json;
		} finally {
			// connection.disconnect();
		}
	}
	/**
	 * 
	 * @param url
	 * @param headers
	 * @param conf
	 * @param keepAlive
	 * @return 
	 * @throws Exception
	 */
	public static JSONObject getJSONByGet(String url, Bundle headers,
			NetConfiguration conf, int keepAlive,String ecodeing) throws Exception {
		return getJSONByPost(url, headers, null, false, conf, keepAlive,ecodeing);
	}
	/**
	 * 
	 * @param connection   HttpUrlConnection
	 * @param headers      请求头bundle
	 * @param params	        请求参数 post请求参数
	 * @param con          请求连接测试
	 * @param keepAlive
	 * @return   返回是输入流
	 * @throws Exception
	 */
	private static InputStream internalPost(HttpURLConnection connection,
			Bundle headers, Bundle params, NetConfiguration con, int keepAlive)
			throws Exception {
		connection.setConnectTimeout(10000);
		connection.setReadTimeout(20000);
		if (keepAlive > 0) {
			connection.setRequestProperty("Connection", "Keep-Alive");
			connection.setRequestProperty("Keep-Alive",
					String.valueOf(keepAlive));
		}
		connection.setRequestProperty("Content-Type",
				"application/x-www-form-urlencoded;charset=UTF-8");
//		con.setHeaderProperty(connection);
//		Map<String, List<String>> req = connection.getRequestProperties();
//		for (String key : req.keySet()) {
//		}
		if (headers != null) {
			for (String key : headers.keySet()) {
				connection.addRequestProperty(key, headers.getString(key));
			}
		}

		if (params != null) {
			connection.setRequestMethod("POST");
			connection.setDoOutput(true);
			StringBuilder body = new StringBuilder();
			for (String key : params.keySet()) {
				body.append(key).append("=").append(params.getString(key))
						.append("&");
			}
			byte[] bypes = body.toString().getBytes();
			connection.getOutputStream().write(bypes);
		}
		connection.connect();
		InputStream is = connection.getInputStream();
		
//		Map<String, List<String>> hea = connection.getHeaderFields();
//		RespHeaderBundle = new Bundle();
//		for (String key : hea.keySet()) {
//			RespHeaderBundle.putString(key, hea.get(key).get(0));
//		}
		return is;
	}
	
	private static InputStream internalPostMemory(HttpURLConnection connection,int keepAlive)
			throws Exception {
		connection.setConnectTimeout(10000);
		connection.setReadTimeout(20000);
		if (keepAlive > 0) {
			connection.setRequestProperty("Connection", "Keep-Alive");
			connection.setRequestProperty("Keep-Alive",
					String.valueOf(keepAlive));
		}
		connection.setRequestProperty("Content-Type",
				"application/x-www-form-urlencoded;charset=UTF-8");
		connection.connect();
		InputStream is = connection.getInputStream();
		return is;
	}
	
	
	/**
	 * 
	 * @param connection  HttpUrlConnnection
	 * @param headers    请求头
	 * @param params     请求参数
	 * @param con		  请求参数设置 
	 * @param keepAlive  请求连接
	 * @return           返回超时连接的返回的输入流
	 * @throws Exception
	 */
	private static InputStream internalPostTimeOut(HttpURLConnection connection,
			Bundle headers, Bundle params, NetConfiguration con, int keepAlive)
			throws Exception {
		connection.setConnectTimeout(2000);
		connection.setReadTimeout(2000);
		if (keepAlive > 0) {
			connection.setRequestProperty("Connection", "Keep-Alive");
			connection.setRequestProperty("Keep-Alive",
					String.valueOf(keepAlive));
		}
		connection.setRequestProperty("Content-Type",
				"application/x-www-form-urlencoded;charset=UTF-8");
		if (con !=null) {  // 当con == null ; 没有请求头
			con.setHeaderProperty(connection);
			if (headers != null) {
				for (String key : headers.keySet()) {
					connection.addRequestProperty(key, headers.getString(key));
				}
			}
		}
		if (params != null) {   // post请求携带请求参数及规定携带参数的规则
			connection.setRequestMethod("POST");
			connection.setDoOutput(true);
			StringBuilder body = new StringBuilder();
			
			for (String key : params.keySet()) {
				body.append(key).append("=").append(params.getString(key))
						.append("&");
			}

			byte[] bypes = body.toString().getBytes();
			connection.getOutputStream().write(bypes);
		}
		connection.connect();
		InputStream is = connection.getInputStream();
		Map<String, List<String>> hea = connection.getHeaderFields();
		RespHeaderBundle = new Bundle();
		for (String key : hea.keySet()) {
			RespHeaderBundle.putString(key, hea.get(key).get(0));
		}
		return is;
	}
	
	public static JSONObject getJSONByPostTimeOut(String url, Bundle headers,
			Bundle params, Boolean withResponseHeader, NetConfiguration conf,
			int keepAlive,String ecoding) throws Exception {
		URL urlTemp;
		urlTemp = new URL(NetConfiguration.url_prefix + url);
		HttpURLConnection connection = (HttpURLConnection) urlTemp
				.openConnection();
		InputStream in;
		String ver;
		try {
			in = internalPostTimeOut(connection, headers, params, conf, keepAlive);
			ver = connection
					.getHeaderField(NetConfiguration.HTTP_HEADER_PACKETVERSION);
			if (ver == null) {
				if (headers != null) {
					if (headers
							.containsKey(NetConfiguration.HTTP_HEADER_PACKETVERSION)) {
						ver = headers
								.getString(NetConfiguration.HTTP_HEADER_PACKETVERSION);
					} else {
						ver = "";
					}
				}
			}
			JSONObject json = streamToJSON(in, ver,ecoding);
			if (withResponseHeader) {
				JSONObject header = new JSONObject();
				Map<String, List<String>> rhead = connection.getHeaderFields();
				for (Entry<String, List<String>> entry : rhead.entrySet()) {
					if (entry.getKey() != null) {
						if (entry.getValue().get(0) == null
								|| entry.getValue().get(0).equals("null")) {
							header.put(entry.getKey().toLowerCase(Locale.US),
									"0");
						} else {
							header.put(entry.getKey().toLowerCase(Locale.US),
									entry.getValue().get(0));
						}
					}
				}
				json.put(PACKET_RESPONSE_HEADER, header);
			}
			return json;
		} finally {
			// connection.disconnect();
		}
	}
	
	// 获取时间超时
	public static JSONObject getJSONTimeOut(Context context, String url,
			Bundle headers, Bundle params, NetConfiguration conf,
			String requesttype, int keepAlive, Map<String, String> mapHeader , String ecoding)
			throws Exception {
		return getJSONByPostTimeOut(url, headers, params, false, conf, keepAlive,ecoding);
	}
	
	/**
	 *  Memory Post请求
	 * @param url
	 * @param headers
	 * @param params
	 * @param conf
	 * @param keepAlive
	 * @return
	 */
	public static JSONObject getMemoryJSOnByPost(Context mContext ,String url,Bundle headers,
			Bundle params,NetConfiguration conf,int keepAlive) throws Exception{
			return getJSONByPost(url, headers, params, false, conf, keepAlive, GBK_ECODEING) ;
	}
	/**
	 * MemoryGet请求
	 * @param url
	 * @param keepAlive
	 * @return 返回
	 */
	public static JSONObject getMemoryJSONByGet(Context mContext ,String url,int keepAlive) throws Exception{
		JSONObject jsonObject = null;
//		if (!CacheDBService.getInsance(mContext).exist(Tools.encrypt(url))) {
//			jsonObject = getJSONByGet(url, null, null, keepAlive, GBK_ECODEING) ;
//			insertDataInDatabase(mContext, url, jsonObject) ;
//			DatedManagement.onReset(url) ;
//		}else {
////			long timestamp = CacheDBService.getInsance(mContext).getDate(Tools.encrypt(url));
////			if (DatedManagement.isDated(url, timestamp)) {
////				jsonObject = getJSONByGet(url, null, null, keepAlive, GBK_ECODEING) ;
////				updateDataInDatabase(mContext, url, jsonObject) ;
////				DatedManagement.onReset(url) ;
////			}else {
////				String strJson = CacheDBService.getInsance(mContext).getData(Tools.encrypt(url));
////				JSONObject jsonObject2 = new JSONObject(strJson);
////				jsonObject = jsonObject2 ;
////			}
//			String strJson = CacheDBService.getInsance(mContext).getData(Tools.encrypt(url));
//			JSONObject jsonObject2 = new JSONObject(strJson);
//			jsonObject = jsonObject2 ;
//		}
		jsonObject = getJSONByGet(url, null, null, keepAlive, GBK_ECODEING) ; 
			
		return jsonObject;
	}
	
	private static void insertDataInDatabase(Context context, String strUrl,
			JSONObject oResult) {
		Bundle bundle = new Bundle();
		bundle.putString(DBOpenHelperSQlite.CACHEDB_URL, strUrl);
		bundle.putString(DBOpenHelperSQlite.CACHEDB_MD5, Tools.encrypt(strUrl));
		
		bundle.putLong(DBOpenHelperSQlite.CACHEDB_DATE, Tools.CurrentTime());
		bundle.putString(DBOpenHelperSQlite.CACHEDB_DATA, oResult.toString());
		try {
			CacheDBService.getInsance(context).insert(bundle);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	private static void updateDataInDatabase(Context context, String strUrl,
			JSONObject oResult) {
		Bundle bundle = new Bundle();
		bundle.putString(DBOpenHelperSQlite.CACHEDB_URL, strUrl);
		bundle.putString(DBOpenHelperSQlite.CACHEDB_MD5, Tools.encrypt(strUrl));
		
		bundle.putLong(DBOpenHelperSQlite.CACHEDB_DATE, Tools.CurrentTime());
		bundle.putString(DBOpenHelperSQlite.CACHEDB_DATA, oResult.toString());
		try {
			CacheDBService.getInsance(context).update(bundle);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
