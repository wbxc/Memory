package com.ihaoxue.memory.model;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;

public class SaveUserSelectData {

	private String userId ;
	private String selectSubjectName ;
	private String selectSubjectId ;
	
	private AVObject avObject ;
	
	public SaveUserSelectData() {
		// TODO Auto-generated constructor stub
		avObject = new AVObject("SaveUserSelectData") ;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
		avObject.put("userId", userId) ;
	}
	public String getSelectSubjectName() {
		return selectSubjectName;
	}
	public void setSelectSubjectName(String selectSubjectName) {
		this.selectSubjectName = selectSubjectName;
		avObject.put("selectSubjectName", selectSubjectName) ;
	}
	public String getSelectSubjectId() {
		return selectSubjectId;
	}
	public void setSelectSubjectId(String selectSubjectId) {
		this.selectSubjectId = selectSubjectId;
		avObject.put("selectSubjectId", selectSubjectId) ;
	}
	
	public boolean saveUserSelectData() {
		try {
			avObject.save() ;
			return true ;
		} catch (AVException e) {
			// TODO Auto-generated catch block
			return false ;
		}
	}
	
	
}
