package com.ihaoxue.memory.leancloud;

public class UserService {

	private static UserService instance = null; 
	
	public static UserService getInstance(){
		if (instance == null) {
			instance = new UserService() ;
		}
		return instance ;
	}
	
	public void registerUser(){
		
		
	}
	
	
	
	
	
	
	
	
	
}
