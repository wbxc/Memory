package com.ihaoxue.memory;

import java.util.ArrayList;
import java.util.List;

import com.avos.avoscloud.AVOSCloud;

import android.app.Activity;
import android.app.Application;
import android.content.pm.PackageManager.NameNotFoundException;
import android.view.WindowManager;

public class MemoryApplication extends Application {

	private List<Activity> mListActivities=new ArrayList<Activity>() ;
	private static MemoryApplication instance= null;
	
	private final static String APPLICATION_ID = "wvrpbexfzix7829ir5ede2ldhr6j4aaxfmeif5siblqwdyjj" ; 
	private final static String APPLICATION_KEY = "hqhynd1i5sitnpgg45ozr1xk3qt965er2pxbpen5oy0qhm9n" ;
	
	public static MemoryApplication memoryApplication ;
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		memoryApplication = this ;
		AVOSCloud.initialize(this, APPLICATION_ID, APPLICATION_KEY)  ;
	}
	
	
	public static MemoryApplication getMemoryApplication(){
		
		return memoryApplication ;
		
	}
	
	public int getDefaultWidth(){
		
	  WindowManager mWindowManager = (WindowManager) getApplicationContext().getSystemService(WINDOW_SERVICE) ;
	  
	  return mWindowManager.getDefaultDisplay().getWidth() ;
	  
	}
	public int getDefaultHeight(){
		
		WindowManager mWindowManager = (WindowManager) getApplicationContext().getSystemService(WINDOW_SERVICE) ;
		  
		  return mWindowManager.getDefaultDisplay().getHeight() ;
	}
	public static MemoryApplication getInstance(){
		if (instance == null) {
			instance = new MemoryApplication() ;
		}
		return instance ;
	}
	
	public void addActivity(Activity activity){
		mListActivities.add(activity) ;
	}
	public void exitApplication(){
		for (int i = 0; i < mListActivities.size(); i++) {
			mListActivities.get(i).finish() ;
		}
		System.exit(0) ;
	}
	/**
	 * 获取本地应用的版本号
	 * @return
	 */
	public int getCurrentVersionNumber(){
		int versionCode = -1 ;
		try {
			versionCode = getPackageManager().getPackageInfo(getPackageName(), 0).versionCode ;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return versionCode ;
	}
	
}
