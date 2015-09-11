package com.ihaoxue.memory.model;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.SaveCallback;

public class LearnProgressModel {

	private String userId ;  // 用户的id
	private String userContent ;  // 用户的内容
	private String todayProgress ;  //用户今天的学习进度
	
	private AVObject mAvObject ;
	
	public LearnProgressModel() {
		// TODO Auto-generated constructor stub
		mAvObject = new AVObject() ;
	}
	public String getUserId() {
		return userId;
	}
	
	
	public void setUserId(String userId) {
		this.userId = userId;
		mAvObject.put("userId", userId) ;
	}
	public String getUserContent() {
		return userContent;
	}
	public void setUserContent(String userContent) {
		this.userContent = userContent;
		mAvObject.put("userContent", userContent) ;
	}
	public String getTodayProgress() {
		return todayProgress;
	}
	public void setTodayProgress(String todayProgress) {
		this.todayProgress = todayProgress;
		mAvObject.put("todayProgress", todayProgress) ;
	}
	public boolean saveTodayProgressModel(){
		try {
			mAvObject.save() ;
			return true ;
		} catch (AVException e) {
			// TODO Auto-generated catch block
			return false ;
		}
	}
	
}
