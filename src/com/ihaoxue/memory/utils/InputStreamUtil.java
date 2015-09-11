package com.ihaoxue.memory.utils;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class InputStreamUtil {
	
	
	public static String inputStreamToBufferString(InputStream is,String charset){
		
		String result = "" ;
		if (charset.endsWith("") || charset==null) {
			charset = "UTF-8" ;
		}
		StringBuffer  sbBuffer = new StringBuffer() ;
		BufferedReader bReader = null;
		try {
			bReader = new BufferedReader(new InputStreamReader(is,charset)) ;
			while((result=bReader.readLine())!=null){   //readLine()表示一行结束
				sbBuffer.append(result) ;
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if (bReader !=null) {
				try {
					bReader.close() ;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return sbBuffer.toString() ;
	}
	
	  /** 
	    * 利用byte数组转换InputStream------->String <功能详细描述> 
	    *  
	    * @param is
	    * @return 
	    * @see [类、类#方法、类#成员] 
	    */  
	
	public static String inputStreamToBytes(InputStream is,String charset) throws Exception{
		String result = "" ;
		StringBuffer sbBuffer = new StringBuffer() ;
		if (charset.equals("") || charset == null) {
			charset = "UTF-8" ;
		}
		byte[] by = new byte[2048] ;
		int len = 0 ;
		while((len=is.read(by))!=-1){
			sbBuffer.append(new String(by, 0, len, charset)) ;
		}
		result = sbBuffer.toString() ;
		return result ;
	}
	  /**
	    * 利用ByteArrayOutputStream：Inputstream------------>String <功能详细描述> 
	    *  
	    * @param in 
	    * @return 
	    * @see [类、类#方法、类#成员] 
	    */  
	public static String inputStreamByArrayOutStream(InputStream is,String charset) throws Exception{
		String result = "" ;
		ByteArrayOutputStream  out = new ByteArrayOutputStream() ;
		byte[] by = new byte[2048] ;
		if (charset.endsWith("") || charset == null) {
			charset = "UTF-8" ;
		}
		int len = 0 ;
		while((len = is.read(by))>0){
			out.write(by, 0, len) ;
		}
		result =  out.toString(charset) ;
		return result ;
	}
}
