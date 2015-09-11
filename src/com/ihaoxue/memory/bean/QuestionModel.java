package com.ihaoxue.memory.bean;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import android.util.Log;


public class QuestionModel {

	// 问题的id
	private String questionId ;
	// 问题所属的章
	private String questionChapter ; 
	// 问题所属的节
	private String questionSection ;
	 // 问题的标题
	private String questionTopic ;
	 // 问题的名称
	private String questionName ;
	
	private String questionImageUrl ;
	private String questionLargeImageUrl ;
	private String tempQuestion ;
	
	private List<String> questionArrStrings ;
	
	
	private String questionContent ;
	private int answerSum ;
	private ArrayList<String> questionArr ;
	
	
	public List<String> getQuestionArrStrings() {
		return questionArrStrings;
	}
	public void setQuestionArrStrings(List<String> questionArrStrings) {
		this.questionArrStrings = questionArrStrings;
	}
	public String getTempQuestion() {
		return tempQuestion;
	}
	public void setTempQuestion(String tempQuestion) {
		this.tempQuestion = tempQuestion;
	}
	public ArrayList<String> getQuestionArr() {
		return questionArr;
	}
	public void setQuestionArr(ArrayList<String> questionArr) {
		this.questionArr = questionArr;
	}
	
	public int getAnswerSum() {
		return answerSum;
	}
	public void setAnswerSum(int answerSum) {
		this.answerSum = answerSum;
	}
	public String getQuestionContent() {
		return questionContent;
	}
	public void setQuestionContent(String questionContent) {
		this.questionContent = questionContent;
	}
	public String getQuestionImageUrl() {
		return questionImageUrl;
	}
	public void setQuestionImageUrl(String questionImageUrl) {
		this.questionImageUrl = questionImageUrl;
	}
	public String getQuestionLargeImageUrl() {
		return questionLargeImageUrl;
	}
	public void setQuestionLargeImageUrl(String questionLargeImageUrl) {
		this.questionLargeImageUrl = questionLargeImageUrl;
	}
	public String getQuestionId() {
		return questionId;
	}
	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}
	public String getQuestionChapter() {
		return questionChapter;
	}
	public void setQuestionChapter(String questionChapter) {
		this.questionChapter = questionChapter;
	}
	public String getQuestionSection() {
		return questionSection;
	}
	public void setQuestionSection(String questionSection) {
		this.questionSection = questionSection;
	}
	public String getQuestionTopic() {
		return questionTopic;
	}
	public void setQuestionTopic(String questionTopic) {
		this.questionTopic = questionTopic;
	}
	public String getQuestionName() {
		return questionName;
	}
	public void setQuestionName(String questionName) {
		this.questionName = questionName;
	}
	
	public QuestionModel parseQuestionModel(QuestionModel mQuestionModel,JSONObject oResult) throws Exception{
		
		
			mQuestionModel.setQuestionId(oResult.getString("questionId")) ;
			Log.e("setQuestionId", oResult.getString("questionId")) ;
			mQuestionModel.setQuestionChapter(oResult.getString("questionChapter")) ;
			mQuestionModel.setQuestionSection(oResult.getString("questionSection")) ;
			mQuestionModel.setQuestionTopic(oResult.getString("questionTopic")) ;
			
			
			JSONArray mData = oResult.getJSONArray("data") ;
			if (mData!=null&&mData.length()>0) {
				JSONObject questionJSON = mData.getJSONObject(0) ;
				mQuestionModel.setTempQuestion(questionJSON.getString("questionContent")) ;
				mQuestionModel.setQuestionContent(questionJSON.toString()) ;
				JSONArray mJsonArray = questionJSON.getJSONArray("questionArr") ;
				if (mJsonArray!=null) {
					List<String> mList = new ArrayList<String>() ;
					for (int i = 0; i < mJsonArray.length(); i++) {
						mList.add(mJsonArray.getString(i)) ;
					}
					mQuestionModel.setQuestionArrStrings(mList) ;
				}
				mQuestionModel.setAnswerSum(Integer.parseInt(questionJSON.getString("answerSum"))) ;
			}
			
			
			JSONArray mImageData = oResult.getJSONArray("imageData") ;
			if (mImageData!=null&&mImageData.length()>0) {
				JSONObject mImage = mImageData.getJSONObject(0) ;
				mQuestionModel.setQuestionImageUrl(mImage.getString("questionImageUrl")) ;
				mQuestionModel.setQuestionLargeImageUrl(mImage.getString("questionLargeImageUrl")) ;
			}
			return mQuestionModel ;
	}
	
}
