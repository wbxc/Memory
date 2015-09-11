package com.ihaoxue.memory.test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.ihaoxue.memory.utils.TripleDES;

import android.test.AndroidTestCase;
import android.util.Log;

public class MapTest extends AndroidTestCase {

	
	public void testMap() throws Exception{
		
		Map<String, Object> map = new HashMap<String, Object>() ;
		
		map.put("A", "nihao")  ;
		map.put("B", "world") ;
		map.put("A", "hello") ;
		
		
		Iterator<Entry<String, Object>> mIterator = map.entrySet().iterator() ;
		while(mIterator.hasNext()) {
			Map.Entry<String,Object> entry = (Map.Entry<String,Object>)mIterator.next() ;
			Log.e("AndroidTestCase", entry.getKey()+"::::"+entry.getValue()) ;
		}
	}
	
	
	public void testMd5() throws Exception{
		
		String url = "http://loaclhost:8080/nihao/你是是" ;
		
		if (TripleDES.keyMd5(url).equals(TripleDES.TestMd5Key(url))) {
			Log.e("TripleDES", "demo1"+true) ;
		}
		String logString = TripleDES.TestMd5Key(url) ;
		Log.e("TripleDES",logString ) ;
		Log.e("TripleDES",TripleDES.converMD5(logString) ) ;
		
		
		
	}
	
	
}
