package com.ihaoxue.memory.net;

import android.content.Context;
import android.os.Bundle;

/**
 * @author weibin 管理请求网络数据
 */

public class MemoryNetTaskManagement {

	private static Context mContext;

	public static MemoryNetTaskManagement instance;

	private MemoryNetTaskManagement(Context mContext) {
		// TODO Auto-generated constructor stub
		MemoryNetTaskManagement.mContext = mContext;
	}

	public static MemoryNetTaskManagement getInstance(Context mContext) {
		if (MemoryNetTaskManagement.mContext == null) {
			instance = new MemoryNetTaskManagement(mContext);
		}
		return instance;
	}

	/**
	 * @param mICallBack 方法回调
	 * @param questionId 请求的问题Id
	 */
	public void downloadOneQuestionAll(MemoryICallBack mICallBack, long questionId) {
		MemoryNetTask mNetTask = new MemoryNetTask(MemoryNetTask.DOWNLOAD_ONE_QUESTION_ALL_TYPE);
		Bundle mBundle = new Bundle();
		mBundle.putInt(MemoryNetTask.RESPONSE_ANALYSE_QUESID, (int) questionId);
		if (mICallBack != null) {
			mICallBack.setParams(mBundle);
		}
		mNetTask.setmValue(String.valueOf(questionId)+"question.json") ;
		
		MemoryNetTaskModel mMemoryNetTaskModel = new MemoryNetTaskModel(mContext, mICallBack);
		Bundle bundleParams = new Bundle();
		bundleParams.putString(MemoryNetTask.CORRECTION_QUESTION_ID, String.valueOf(questionId));
		mNetTask.setUrlBundleParams(null);
		mNetTask.setmContext(mContext);
		mNetTask.setmMemoryNetListener(mMemoryNetTaskModel);
		mNetTask.start();
	}
	public void createPaper(MemoryICallBack mICallBack, int paperId) {
		MemoryNetTask mNetTask = new MemoryNetTask(MemoryNetTask.MEMORY_CRETAE_PAPER) ;
		mNetTask.setMemoryGetParam(String.valueOf(paperId)+"paper.json") ;
		MemoryNetTaskModel mNetTaskModel  = new MemoryNetTaskModel(mContext, mICallBack) ;
		mNetTask.setmContext(mContext) ;
		mNetTask.setmKeepAlive(30) ;
		mNetTask.setmMemoryNetListener(mNetTaskModel) ;
		mNetTask.start() ;
	}
	
	public void downloadTreePointer(MemoryICallBack mICallBack){
		MemoryNetTask mNetTask = new MemoryNetTask(MemoryNetTask.MEMORY_DOWNLOAD_TREE_POINTER) ;
		mNetTask.setmValue("") ;
		MemoryNetTaskModel mNetTaskModel = new MemoryNetTaskModel(mContext, mICallBack) ;
		mNetTask.setmContext(mContext) ;
		mNetTask.setmKeepAlive(30) ;
		mNetTask.setmMemoryNetListener(mNetTaskModel) ;
		mNetTask.start() ;
	}
}
