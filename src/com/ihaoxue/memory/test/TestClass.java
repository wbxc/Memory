package com.ihaoxue.memory.test;

import java.util.Date;

import org.json.JSONObject;

import android.os.Bundle;
import android.os.Message;
import android.test.AndroidTestCase;
import android.util.DisplayMetrics;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.LogUtil.log;
import com.avos.avoscloud.SignUpCallback;
import com.ihaoxue.memory.model.Person;
import com.ihaoxue.memory.model.UserInfo;
import com.ihaoxue.memory.net.ConfigURL;
import com.ihaoxue.memory.net.HttpUrlContent;
import com.ihaoxue.memory.net.HttpUtils;
import com.ihaoxue.memory.net.MemoryICallBack;
import com.ihaoxue.memory.net.MemoryNetTaskManagement;
import com.ihaoxue.memory.ui.ExamMemoryActivity;
import com.ihaoxue.memory.ui.ExamNextMemoryActivity;
import com.ihaoxue.memory.utils.StringUtil;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

public class TestClass extends AndroidTestCase {

	public void testSavePersion(){
		
		try {
			Person person = new Person() ;
			person.setPersonId("12") ;
			person.setPersonName("你好") ;
			person.savePerson() ;
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Log.e("ssssss",e.getMessage()) ;
		}
	}
	
	public void testUser(){
		UserInfo mUserInfo = UserInfo.getInstance() ;
		mUserInfo.setMemoryMail("1475389047@qq.com") ;
		mUserInfo.setPassword("123456") ;
		mUserInfo.setMemoryUserName("5haoxue") ;
		mUserInfo.setUserId("133") ;
		
		mUserInfo.signUpInBackground(new SignUpCallback() {
			
			@Override
			public void done(AVException arg0) {
				Log.e("ssssss",arg0.getMessage()) ;
			}
		}) ;
	}
	
	public void testFindUser(){
		
		AVQuery<AVUser> mAvQuery = AVUser.getQuery() ;
		
		AVUser avUser = null;
		try {
			 
			 Log.e("tempString", "tempString:"+mAvQuery.count()) ;
			 
			 
			avUser = mAvQuery.getFirst();
		} catch (AVException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String tempString = avUser.getString("userId") ;
		Log.e("tempString", "tempString:"+tempString) ;
	}
	
	public void testEmail(){
		
		String email = "1475389047@qq.com" ;
		String emails = "wwwwww@163.com" ;
		
		Log.e("StringUtil", StringUtil.isEmailAddress(email)+"uu") ;
		
		Log.e("StringUtil", StringUtil.isEmailAddress(emails)+"ss") ;
		
		
		
	}
	
	public void testUrl(){
		
		String  tempString = HttpUrlContent.get(ConfigURL.KNOWLEDGE_POINT_TREE) ;
		
		Log.e("tempString", tempString) ;
		
	}
	
	public void testData(){
		Date date = new Date() ;
		Log.e("tempString", date.toString()) ;
	}
	
	public void testPrint(){
		DisplayMetrics mDisplayMetrics = getContext().getResources().getDisplayMetrics() ;
		float mFlot = mDisplayMetrics.density ;
		int mIn = mDisplayMetrics.densityDpi ;
		
		Log.e("mDisplayMetrics", "mDisplayMetrics:"+mFlot+":"+mIn+":::"+mDisplayMetrics.xdpi) ;
	}
	
	public void getTest(){
		
		MemoryNetTaskManagement.getInstance(getContext()).createPaper(new MemoryICallBack() {
			
			@Override
			public void setProgress(int rate) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void setParams(Bundle bundle) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void setFinished(boolean isover) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void setError(String msg) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void setData(Message data) {
				// TODO Auto-generated method stub
				Log.e("Message", "kskskk") ;
				JSONObject mJsonObject = (JSONObject) data.obj ;
				Log.e("Message", mJsonObject.toString()) ;
			}
		}, 1001) ;
	}
	
	
	public void getHttp(){
		Log.e("Message",ExamMemoryActivity.questionAll) ;
		
		String ssString = "\'<div style=\"width: 40px; height: auto;\"><img src=\"jiahao.png\" /></div>\'" ;
		
	}
	
	public void getHttpRequest(){
		
		com.lidroid.xutils.HttpUtils httpUtils = new com.lidroid.xutils.HttpUtils() ;
		httpUtils.send(HttpMethod.GET, "http://hxtest.umage.cn/memory/question/1001question.json", 
				new RequestCallBack<String>() {

					@Override
					public void onFailure(HttpException arg0, String arg1) {
						// TODO Auto-generated method stub
						Log.e("ResponseInfo", "arg0"+arg1) ;
					}

					@Override
					public void onSuccess(ResponseInfo<String> arg0) {
						// TODO Auto-generated method stub
						Log.e("ResponseInfo", "arg0"+arg0) ;
					}
				}) ;
	}
	
	
	
	
	
	
	
}
