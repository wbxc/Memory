package com.ihaoxue.memory.model;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;

public class FeedBackModel{


	
	private String  content ;
	private String address ;
	private String userName ;
	
	
	private AVObject mAvObject ;
	
	public FeedBackModel() {
		// TODO Auto-generated constructor stub
		mAvObject = new AVObject("FeedBackModel") ;
	}
	
	public String getContent() {
		return content;
	}
	public String getAddress() {
		return address;
	}
	public String getUserName() {
		return userName;
	}
	
	
	public void setContent(String content) {
		this.content = content;
		mAvObject.put("content", content) ;
	}
	public void setAddress(String address) {
		this.address = address;
		mAvObject.put("address", address) ;
	}
	public void setUserName(String userName) {
		this.userName = userName;
		mAvObject.put("userName", userName) ;
	}
	
	public boolean saveFeedBack(){
		try {
			mAvObject.save() ;
			return true ;
		} catch (AVException e) {
			return false ;
		}
	}
	
	public AVObject getAvObject(){
		return mAvObject ;
	}
	
	
}
