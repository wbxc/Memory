package com.ihaoxue.memory.net;

import org.json.JSONObject;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

public class MemoryNetTask extends Thread {

	public static final String POST = "POST";
	public static final String GET = "GET";
	// 创建任务
	public final static int DOWNLOAD_ONE_QUESTION_ALL_TYPE = 1;
	public final static int MEMORY_CRETAE_PAPER = 2 ;
	public final static int MEMORY_DOWNLOAD_TREE_POINTER = 3 ;
	
	
	
	private int mNetTaskType ; // 任务的类型
	private String mNetRequestUrl ; // 请求的url
	private Bundle urlBundleParams ;  // 请求头携带的数据
	private MemoryNetListener mMemoryNetListener ;  //回调
	private int mKeepAlive;  
	private Context mContext;
	private String mStrPost;
	private String mValue ;
	
	public void setMemoryGetParam(String value){
		
		this.mValue = value ;
	}


	public void setmKeepAlive(int mKeepAlive) {
		this.mKeepAlive = mKeepAlive;
	}


	public void setmContext(Context mContext) {
		this.mContext = mContext;
	}


	public void setmStrPost(String mStrPost) {
		this.mStrPost = mStrPost;
	}


	public void setmValue(String mValue) {
		this.mValue = mValue;
	}

	public Bundle getUrlBundleParams() {
		return urlBundleParams;
	}

	public void setUrlBundleParams(Bundle urlBundleParams) {
		this.urlBundleParams = urlBundleParams;
	}


	public MemoryNetListener getmMemoryNetListener() {
		return mMemoryNetListener;
	}


	public void setmMemoryNetListener(MemoryNetListener mMemoryNetListener) {
		this.mMemoryNetListener = mMemoryNetListener;
	}
	// 请求的参数
	public MemoryNetTask(int netType) {
		// TODO Auto-generated constructor stub
		this.mNetTaskType = netType ;
	}
	

	protected void makeRequest(int netType) {
		 switch (netType) {
		case DOWNLOAD_ONE_QUESTION_ALL_TYPE:
			mNetRequestUrl = ConfigURL.URL_DOWNLOAD_QUESTION ;
			mStrPost = MemoryNetTask.GET ;
			break;
		case MEMORY_CRETAE_PAPER:
			mNetRequestUrl = ConfigURL.URL_CREATE_PAPER ;
			mStrPost = MemoryNetTask.GET ;
			break ;
		case MEMORY_DOWNLOAD_TREE_POINTER:
			mNetRequestUrl = ConfigURL.DEMO_POINT_TREE ;
			mStrPost = MemoryNetTask.GET ;
			break ;
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		makeRequest(mNetTaskType) ;
		
		JSONObject oResult = null ;
		
		try {
			if (mStrPost.equals(MemoryNetTask.POST)) {
				//oResult = HttpUtils.getMemoryJSOnByPost(mNetRequestUrl, m, urlBundleParams, conf, m_nKeepAlive) ;
			}else if (mStrPost.equals(MemoryNetTask.GET)) {
				oResult = HttpUtils.getMemoryJSONByGet(mContext,mNetRequestUrl+mValue, mKeepAlive) ;
			}
			onFinished(oResult) ;
		} catch (Exception e) {
			 onError(e) ;
		}
	}

	/**
	 * 解析成功
	 */
	protected void onFinished(JSONObject oResult) {
		mMemoryNetListener.recvDataFromConn(oResult, mNetTaskType) ;
	}

	/**
	 * 解析失败
	 */
	protected void onError(Exception e ) {
		
		mMemoryNetListener.onConnectionError(e) ;
	}

	
	
	public static final String CORRECTION_QUESTION_ID = "questionid";
	public static final String RESPONSE_ANALYSE_QUESID = "qid";
}
