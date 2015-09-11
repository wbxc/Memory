package com.ihaoxue.memory.bean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


	/**
	 * @author weibin
	 * 试卷信息
	 */
public class MemoryQuestionPaper {

	private String paperName;		// 试卷的名称
	private int[] questionArrFlag;  // 0表示文本 1表示图片 试题的标示
	private int questionAllNum;		// 试卷创建试题的总数量
	private int[] questionIdArr;     // 试题Id 数组
	private int[] questionAnswerNum;  // 试题对应的空的个数

	public String getPaperName() {
		return paperName;
	}

	public void setPaperName(String paperName) {
		this.paperName = paperName;
	}

	public int[] getQuestionArrFlag() {
		return questionArrFlag;
	}

	public void setQuestionArrFlag(int[] questionArrFlag) {
		this.questionArrFlag = questionArrFlag;
	}

	public int getQuestionAllNum() {
		return questionAllNum;
	}

	public void setQuestionAllNum(int questionAllNum) {
		this.questionAllNum = questionAllNum;
	}

	public int[] getQuestionIdArr() {
		return questionIdArr;
	}

	public void setQuestionIdArr(int[] questionIdArr) {
		this.questionIdArr = questionIdArr;
	}

	public int[] getQuestionAnswerNum() {
		return questionAnswerNum;
	}

	public void setQuestionAnswerNum(int[] questionAnswerNum) {
		this.questionAnswerNum = questionAnswerNum;
	}

	public MemoryQuestionPaper() {

	}
	
	public MemoryQuestionPaper paresJson(MemoryQuestionPaper memoryQuestionPaper ,JSONObject oResult){
	//	{"questionAllNum":10,"questionAnswerNum":[2,3,4,2,5,4,2,4,5,6],
    //  "paperName":"项目法规","curversion":"","questionArrFlag":[0,1,1,1,0,1,1,1,1,0],
    //"questionIdArr":[1001,1002,1003,1004,1005,1006,1007,1008,1009,1010]}
		try {
			memoryQuestionPaper.setPaperName(oResult.getString("paperName")) ;
			memoryQuestionPaper.setQuestionAllNum(oResult.getInt("questionAllNum")) ;
			JSONArray mJsonArray = oResult.getJSONArray("questionAnswerNum") ;
			if (mJsonArray!=null && mJsonArray.length()>0) {
				int[] questionAnswerNum1 = new int[mJsonArray.length()] ;
				for (int i = 0; i < mJsonArray.length(); i++) {
					questionAnswerNum1[i] = mJsonArray.getInt(i) ;
				}
				memoryQuestionPaper.setQuestionAnswerNum(questionAnswerNum1) ;
			}
			
			JSONArray questionArrFlag = oResult.getJSONArray("questionArrFlag") ;
			if (questionArrFlag!=null && questionArrFlag.length()>0) {
				int[] questionArrFlag1 = new int[questionArrFlag.length()] ;
				for (int i = 0; i < questionArrFlag.length(); i++) {
					questionArrFlag1[i] = questionArrFlag.getInt(i) ;
				}
				memoryQuestionPaper.setQuestionArrFlag(questionArrFlag1) ;
			}
			
			JSONArray questionIdArr = oResult.getJSONArray("questionIdArr") ;
			if (questionIdArr!=null && questionIdArr.length()>0) {
				int[] questionIdArr1 = new int[questionIdArr.length()] ;
				for (int i = 0; i < questionIdArr.length(); i++) {
					questionIdArr1[i] = questionIdArr.getInt(i) ;
				}
				memoryQuestionPaper.setQuestionIdArr(questionIdArr1) ;
			}
			
			return memoryQuestionPaper ;
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null ;
	}
}
