package com.ihaoxue.memory.net;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUrlContent {

	
	public static String get(String url){
		URL  requestUrl = null;
		InputStreamReader mInputStreamReader = null;
		BufferedReader mBufferedReader = null;
		StringBuffer mStringBuffer = new StringBuffer() ;
		HttpURLConnection connection ;
		String tempLine = null; 
		
		try {
			requestUrl = new URL(url) ;
			connection = (HttpURLConnection) requestUrl.openConnection() ;
			connection.setConnectTimeout(200000) ;
			connection.setReadTimeout(200000) ;
			connection.setUseCaches(false) ;
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8") ;   //表单提交
//			OutputStream outputStream = connection.getOutputStream() ;
//			OutputStreamWriter  streamWriter = new OutputStreamWriter(outputStream) ;
//			String ssString ="" ;
//			streamWriter.write(ssString) ;
//			streamWriter.flush() ;
			connection.connect() ;
			mInputStreamReader = new InputStreamReader(connection.getInputStream(), "GBK") ;
			mBufferedReader = new BufferedReader(mInputStreamReader) ;
			while ((tempLine=mBufferedReader.readLine())!=null) {
				mStringBuffer.append(tempLine) ;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if (mBufferedReader !=null) {
				try {
					mBufferedReader.close() ;
					if (mInputStreamReader!=null) {
						mInputStreamReader.close() ;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return mStringBuffer.toString() ;
	}
}
