package com.ihaoxue.memory.net;

import java.util.ArrayList;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.ihaoxue.memory.bean.MemoryQuestionPaper;
import com.ihaoxue.memory.bean.QuestionModel;
import com.ihaoxue.memory.model.PointMaster;
import com.ihaoxue.memory.model.TreeElement;

import android.content.Context;
import android.os.Message;


public class MemoryNetTaskModel implements MemoryNetListener {

	private Context mContext ;
	private MemoryICallBack mICallBack ;
	private int mFrom ;
	
	
	public MemoryNetTaskModel(Context mContext , MemoryICallBack mICallBack) {
		// TODO Auto-generated constructor stub
		this.mContext = mContext ;
		this.mICallBack = mICallBack ;
		this.mFrom = 0 ;
	}
	
	public MemoryNetTaskModel(Context mContext , MemoryICallBack mICallBack ,int mFrom) {
		// TODO Auto-generated constructor stub
		this.mContext = mContext ;
		this.mICallBack = mICallBack ;
		this.mFrom = mFrom ;
	}
	
	public Context getmContext() {
		return mContext;
	}
	public void setmContext(Context mContext) {
		this.mContext = mContext;
	}
	
	public void clear(){
		mContext = null;
		mICallBack = null;
	}

	
	@Override
	public void recvDataFromConn(JSONObject json, int type) {
		doResponeData(json, type) ;
		clear() ;
	}

	private void doResponeData(JSONObject json,int type){
		switch (type) {
		case MemoryNetTask.MEMORY_CRETAE_PAPER:
			handlerCreatePaperData(json) ;
			break;
		case MemoryNetTask.DOWNLOAD_ONE_QUESTION_ALL_TYPE:
			handlerDownloadQuestion(json) ;
			break ;
		case MemoryNetTask.MEMORY_DOWNLOAD_TREE_POINTER:
			handlerSendTreePointer(json) ;
			break ;
		}
	}
	
	private void handlerSendTreePointer(JSONObject jsonObject){
		
		try {
			JSONObject jsonData = jsonObject.getJSONObject("data");
			Iterator<?> iterator = jsonData.keys();
			ArrayList<PointMaster> arrFatherTree = new ArrayList<PointMaster>();
			while (iterator.hasNext()) {
				String key = (String) iterator.next();
				JSONArray arrjson = jsonData.getJSONArray(key);
				PointMaster pointmaster;
				JSONObject jsonChid;
				for (int i = 0; i < arrjson.length(); i++) {
					jsonChid = arrjson.getJSONObject(i);
					pointmaster = new PointMaster();
					if (pointmaster.loadFromJson(jsonChid)) {
						arrFatherTree.add(pointmaster);
					}
				}
			}
			Message message = Message.obtain() ;
			message.obj = arrFatherTree ;
			if (mICallBack!=null) {
				mICallBack.setData(message) ;
				mICallBack.setFinished(true) ;
			}
		} catch (JSONException e) {
			if (mICallBack!=null) {
				mICallBack.setError(e.getMessage()) ;
				mICallBack.setFinished(true) ;
			}
		}
		
	}
	private void handlerDownloadQuestion(JSONObject jsonObject){
		
		QuestionModel questionModel = new QuestionModel() ;
		try {
			questionModel.parseQuestionModel(questionModel, jsonObject) ;
			Message message = new Message() ;
			message.obj = questionModel ;
			if (mICallBack!=null) {
				mICallBack.setData(message) ;
				mICallBack.setFinished(true) ;
			}
		} catch (Exception e) {
			if (mICallBack!=null) {
				mICallBack.setError(e.getMessage()) ;
				mICallBack.setFinished(true) ;
			}
		}
		
	}
	
	
	// 创建试卷
	private void handlerCreatePaperData(JSONObject jsonObject){
		
		MemoryQuestionPaper memoryQuestionPaper = new MemoryQuestionPaper() ;
		
		try {
			memoryQuestionPaper.paresJson(memoryQuestionPaper ,jsonObject) ;
			Message message = new Message() ;
			message.obj = memoryQuestionPaper ;
			if (mICallBack!=null) {
				mICallBack.setData(message) ;
				mICallBack.setFinished(true) ;
			}
		} catch (Exception e) {
			if (mICallBack!=null) {
				mICallBack.setError(e.getMessage()) ;
				mICallBack.setFinished(true) ;
			}
		}
		
	}
	
	
	
	@Override
	public void onConnectionError(Exception e) {
		// TODO Auto-generated method stub
		if (mICallBack !=null) {
			mICallBack.setFinished(false) ;
			clear() ;
		}
	}

	@Override
	public Context getContext() {
		// TODO Auto-generated method stub
		return mContext;
	}

}
