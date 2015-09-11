package com.ihaoxue.memory.model;

import com.avos.avoscloud.AVUser;

public class UserInfo extends AVUser {

	private String userId ;

	private String memoryUserName ;
	private String memoryMail ;
	private String memoryPassword ;
	private static UserInfo instance = null;
	
	public static UserInfo getInstance(){
		if (instance == null) {
			instance = new UserInfo() ;
		}
		return instance ;
	}
	
	
	public String getMemoryUserName() {
		return memoryUserName;
	}

	public void setMemoryUserName(String memoryUserName) {
		this.memoryUserName = memoryUserName;
		instance.setUsername(memoryUserName) ;
	}

	public String getMemoryMail() {
		return memoryMail;
	}

	public void setMemoryMail(String memoryMail) {
		this.memoryMail = memoryMail;
		instance.setEmail(memoryMail) ;
	}

	public String getMemoryPassword() {
		return memoryPassword;
	}

	public void setMemoryPassword(String memoryPassword) {
		this.memoryPassword = memoryPassword;
		instance.setPassword(memoryPassword) ;
	}



	
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
		instance.put("userId", userId) ;
	}
	
	
}
