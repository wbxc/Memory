package com.ihaoxue.memory.model;


public class StudyModel {

	private String courseName ;
	private int errorNumber ;
	private int rightNumber ;
	private int[] errorQuestionId ;
	
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public int getErrorNumber() {
		return errorNumber;
	}
	public void setErrorNumber(int errorNumber) {
		this.errorNumber = errorNumber;
	}
	public int getRightNumber() {
		return rightNumber;
	}
	public void setRightNumber(int rightNumber) {
		this.rightNumber = rightNumber;
	}
	public int[] getErrorQuestionId() {
		return errorQuestionId;
	}
	public void setErrorQuestionId(int[] errorQuestionId) {
		this.errorQuestionId = errorQuestionId;
	}
	
	
}
