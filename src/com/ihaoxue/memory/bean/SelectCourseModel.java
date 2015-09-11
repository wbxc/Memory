package com.ihaoxue.memory.bean;

/**
 * 科目选择的模型
 * @author weibin
 *
 */
public class SelectCourseModel {

	private String courseName ;
	private int sumQuestions ;
	private int compteQuestion ;
	private int status ;
	private boolean isPublic ;
	
	private String questionId ;
	
	public String getQuestionId() {
		return questionId;
	}
	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}
	public boolean isPublic() {
		return isPublic;
	}
	public void setPublic(boolean isPublic) {
		this.isPublic = isPublic;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public int getSumQuestions() {
		return sumQuestions;
	}
	public void setSumQuestions(int sumQuestions) {
		this.sumQuestions = sumQuestions;
	}
	public int getCompteQuestion() {
		return compteQuestion;
	}
	public void setCompteQuestion(int compteQuestion) {
		this.compteQuestion = compteQuestion;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	
}
