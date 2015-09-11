package com.ihaoxue.memory.utils;

import java.util.HashMap;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class ShareTools {

	private static ShareTools instance  = null;
	
	public static ShareTools getInstance(){
		if (instance == null) {
			instance = new ShareTools() ;
		}
		return instance ;
	}
	
	public void saveInitMemory(Context mContext , String subject_id , String subject_name){
		SharedPreferences spf = mContext.getSharedPreferences("init_memory", Context.MODE_PRIVATE) ;
		Editor mEditor = spf.edit() ;
		mEditor.clear() ;
		mEditor.putString("subject_name", subject_name) ;
		mEditor.putString("subject_id", subject_id) ;
		mEditor.commit() ;
	}
	public String[] readInitMemory(Context mContext){
		SharedPreferences spf = mContext.getSharedPreferences("init_memory", Context.MODE_PRIVATE) ;
		String[]  stringArr = new String[2] ;
		stringArr[0] = spf.getString("subject_name", "") ;
		stringArr[1] = spf.getString("subject_id", "0") ;
		return stringArr ;
	}
	
	public void saveUserInfo(Context mContext ,String userName , String password, String userId){
		SharedPreferences spf = mContext.getSharedPreferences("user_info", Context.MODE_PRIVATE) ;
		Editor mEditor = spf.edit() ;
		mEditor.clear() ;
		mEditor.putString("user_name", userName) ;
		mEditor.putString("user_password", password) ;
		mEditor.putString("user_id", userId) ;
		mEditor.commit() ;
	}
	
	public static final String USER_NAME = "user_name" ;
	public static final String USER_PASSWORD = "user_password" ;
	public static final String USER_ID = "user_id" ;
	
	public HashMap<String, String> readUserInfo(Context mContext){
		HashMap<String, String> hashMap = new HashMap<String, String>() ;
		SharedPreferences spf = mContext.getSharedPreferences("user_info", Context.MODE_PRIVATE) ;
		hashMap.put(ShareTools.USER_NAME, spf.getString(ShareTools.USER_NAME, "")) ;
		hashMap.put(ShareTools.USER_PASSWORD, spf.getString(ShareTools.USER_PASSWORD, "")) ;
		hashMap.put(ShareTools.USER_ID, spf.getString(ShareTools.USER_ID, "")) ;
		return hashMap ;
	}
	
	public static final String IS_INIT_MEMORY = "isInitMemory" ;
	public static final String SELECT_SUBJECT_NAME = "selectSubjectName" ;
	public static final String SELECT_SUBJECT_ID = "selectSubjectId" ;
	
	public void setInitSelectClass(Context mContext,boolean isInitMemory,String selectSubjectName,String selectSubjectId){
		SharedPreferences spf = mContext.getSharedPreferences("select_init_memory", Context.MODE_PRIVATE) ;
		Editor mEditor = spf.edit() ;
		mEditor.putBoolean(ShareTools.IS_INIT_MEMORY, false) ;
		mEditor.putString(ShareTools.SELECT_SUBJECT_NAME, selectSubjectName) ;
		mEditor.putString(ShareTools.SELECT_SUBJECT_ID, selectSubjectId)  ;
		mEditor.commit() ;
	}
	public HashMap<String, Object> readInitSelectClassInfo(Context mContext){
		HashMap<String, Object> hashMap = new HashMap<String, Object>() ;
		SharedPreferences spf = mContext.getSharedPreferences("select_init_memory", Context.MODE_PRIVATE) ;
		hashMap.put(ShareTools.IS_INIT_MEMORY, spf.getBoolean(ShareTools.IS_INIT_MEMORY, false)) ;
		hashMap.put(ShareTools.SELECT_SUBJECT_NAME, spf.getString(ShareTools.SELECT_SUBJECT_NAME, "")) ;
		hashMap.put(ShareTools.SELECT_SUBJECT_ID, spf.getString(ShareTools.SELECT_SUBJECT_ID, "")) ;
		return hashMap ;
	}
	
	public void setIsHastLogin(Context mContext ,boolean flag){
		SharedPreferences spf = mContext.getSharedPreferences("is_first_login", Context.MODE_PRIVATE) ;
		Editor mEditor = spf.edit() ;
		mEditor.clear() ;
		mEditor.putBoolean("isnot_login_first", true) ;
		mEditor.commit() ;
	}
	public boolean readIsHastLogin(Context mContext){
		SharedPreferences spf = mContext.getSharedPreferences("is_first_login", Context.MODE_PRIVATE) ;
		return spf.getBoolean("isnot_login_first", false) ;
	}
	
	//安装完成后第一次启动
	public boolean isFirstRunApp(Context mContext){
		SharedPreferences spf = mContext.getSharedPreferences("is_first_run_app", Context.MODE_PRIVATE) ;
		return spf.getBoolean("is_first_run_app", false) ;
	}
	//安装完成第一次启动标示
	public void setFirstRunApp(Context mContext){
		SharedPreferences spf = mContext.getSharedPreferences("is_first_run_app", Context.MODE_PRIVATE) ;
		Editor mEditor = spf.edit() ;
		mEditor.putBoolean("is_first_run_app", true) ;
		mEditor.commit() ;
	}
	
	
	
}
