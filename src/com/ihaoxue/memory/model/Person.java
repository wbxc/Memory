package com.ihaoxue.memory.model;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;

public class Person  {
	private String personId ;
	
	private String personName ;

	private AVObject mAvObject ;
	
	public Person() {
		// TODO Auto-generated constructor stub
		mAvObject = new AVObject("Person") ;
	}
	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
		mAvObject.put("personId", personId) ;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
		mAvObject.put("personName", personName) ;
	}
	
	public void savePerson(){
		try {
			mAvObject.save() ;
		} catch (AVException e) {
			e.printStackTrace();
		}
	}
	
	
}
