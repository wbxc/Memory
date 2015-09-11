package com.ihaoxue.memory.ui;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.ihaoxue.memory.MemoryApplication;
import com.ihaoxue.memory.MemoryGlobeManger;
import com.ihaoxue.memory.R;
import com.ihaoxue.memory.adapter.ExamNextTPagerAdapter;
import com.ihaoxue.memory.widget.DemoProgressView;
import com.ihaoxue.memory.widget.MemoryViewPager;

public class ExamNextTMemoryActivity extends BaseActivity implements OnClickListener {

	
	private int errorNumber = 0 ;
	private int rightNumber = 0 ;
	private int[] errorQuestionId ;
	private DemoProgressView mDemoProgressView ;
	private MemoryViewPager memoryViewPager ;
	private List<View> mViewPagersList ;
	private ExamNextTPagerAdapter mTestNextPagerAdapter ;
	private int[] questionAnswerN ;
	private int curretntIndex = 0 ;
	private ImageView back ;
	private TextView mToTextView ;
	private Handler mHandler = new Handler() ;
	
	private boolean[] questionFlag ;
	private int globSumQuestions =0 ;
	private int questionId ;
	private int[] questionArrquestionFlag ;
	
	private String selectSubjectName ;
	@Override
	protected void initDataDataVariable() {
		// TODO Auto-generated method stub
		errorNumber = getIntent().getExtras().getInt("errorNumber") ;
		selectSubjectName = getIntent().getExtras().getString("selectSubjectName") ;
		rightNumber = getIntent().getExtras().getInt("rightNumber") ;
		errorQuestionId = getIntent().getExtras().getIntArray("errorQuestionId") ;
		questionAnswerN = getIntent().getExtras().getIntArray("questionAnswerN") ;
		globSumQuestions = getIntent().getExtras().getInt("globSumQuestions") ;
		questionArrquestionFlag = getIntent().getExtras().getIntArray("questionArrquestionFlag") ;
		mViewPagersList = new ArrayList<View>() ;
		questionFlag = new boolean[errorQuestionId.length] ;
		for (int i = 0; i < errorQuestionId.length; i++) {
			questionFlag[i] = true ;
		}
		for (int i = 0; i < errorQuestionId.length; i++) {
			View view = createItemView(i) ;
			mViewPagersList.add(view) ;
		}
		questionId = errorQuestionId[0] ;
	}

	@Override
	protected void setMainContentView() {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_test_memory) ;
		MemoryApplication.getInstance().addActivity(ExamNextTMemoryActivity.this) ;
	}

	@Override
	protected void initComponentView() {
		mDemoProgressView = (DemoProgressView)findViewById(R.id.demo_ProgressView) ;
		mDemoProgressView.setErrorOrRight(rightNumber, 0, errorNumber) ; 
		memoryViewPager = (MemoryViewPager)findViewById(R.id.memory_viewpager) ;
		back = (ImageView)findViewById(R.id.back) ;
		mToTextView = (TextView)findViewById(R.id.common_text) ;
		
	}

	@Override
	protected void initEvent() {
		// TODO Auto-generated method stub
		mTestNextPagerAdapter = new ExamNextTPagerAdapter(ExamNextTMemoryActivity.this, mViewPagersList) ;
		mTestNextPagerAdapter.setQuestionArr(errorQuestionId) ;
		mTestNextPagerAdapter.setQuestionId(questionId) ;
		mTestNextPagerAdapter.setQuestionArrFlag(questionArrquestionFlag) ;
		memoryViewPager.setAdapter(mTestNextPagerAdapter) ;
		back.setOnClickListener(this) ;
		mToTextView.setText(selectSubjectName) ;
	}

	@Override
	protected void initNetDataRequest() {
		// TODO Auto-generated method stub

	}
	
	// 按钮 选择
	public void refreshLoadButton(int position){
		mTestNextPagerAdapter.refresh(mHandler, position, curretntIndex) ;
		questionFlag[curretntIndex] = false ;
	}
	
	public void reload(){
		mTestNextPagerAdapter.reLoad(mHandler, curretntIndex) ;
	}
	
	
	
	private void remmber_knowleage(){
		mTestNextPagerAdapter.loadQuestion(mHandler, 0) ;
	}
	public void nextQuestion(){
		int tempIndex = 0 ;
		tempIndex = curretntIndex ;
		curretntIndex++ ;
		if (curretntIndex<errorQuestionId.length) {
			if (questionFlag[tempIndex]) {
				errorNumber-- ;
				rightNumber++ ;
				mDemoProgressView.setErrorOrRight(rightNumber, 0, errorNumber) ;
			}
			memoryViewPager.setCurrentItem(curretntIndex) ;
			questionId = errorQuestionId[curretntIndex] ;
			mTestNextPagerAdapter.setQuestionId(questionId) ;
		}else {
			int temp = 0 ;
			for (int i = 0; i < errorQuestionId.length; i++) {
				if (!questionFlag[i]) {
					temp++ ;
				}
			}
			if (temp==0) {
				Intent intentSuccess = new Intent() ;
				intentSuccess.setClass(ExamNextTMemoryActivity.this, MemoryFinishActivity.class) ;
				Bundle mBundle = new Bundle() ;
				mBundle.putString("selectSubjectName", selectSubjectName) ;
				intentSuccess.putExtras(mBundle) ;
				startActivity(intentSuccess) ;
				overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left) ;
				ExamNextTMemoryActivity.this.finish() ;
			}else {
				int[] questionIdtemp = new int[temp] ;
				int[] questionAnseN = new int[temp] ;
				int[] questionArrfalg = new int[temp] ;
				int tem = 0 ;
				for (int i = 0; i < errorQuestionId.length; i++) {
					if (!questionFlag[i]) {
						questionIdtemp[tem] = errorQuestionId[i] ;
						questionAnseN[tem] = questionAnswerN[i] ;
						questionArrfalg[tem] = questionArrquestionFlag[i] ;
						tem++ ;
					}
				}
				Intent intent  = new Intent() ;
				intent.setClass(ExamNextTMemoryActivity.this, ExamNextMemoryActivity.class) ;
				Bundle errorBundle = new Bundle() ;
				errorBundle.putString("selectSubjectName", selectSubjectName) ;
				errorBundle.putInt("errorNumber", questionIdtemp.length) ;
				errorBundle.putInt("rightNumber", globSumQuestions-questionIdtemp.length) ;
				errorBundle.putIntArray("errorQuestionId", questionIdtemp) ;
				errorBundle.putIntArray("questionAnswerN", questionAnseN) ;
				errorBundle.putInt("globSumQuestions", globSumQuestions) ;
				errorBundle.putIntArray("questionArrquestionFlag", questionArrfalg) ;
				intent.putExtras(errorBundle) ;
				startActivity(intent) ;
				overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left) ;
				ExamNextTMemoryActivity.this.finish() ;
			}
		}
	}
	
	public void nextQuestion2(){
		int tempIndex = 0 ;
		tempIndex = curretntIndex ;
		questionFlag[tempIndex] =false ;
		curretntIndex++ ;
		if (curretntIndex<errorQuestionId.length) {
			if (questionFlag[tempIndex]) {
				errorNumber-- ;
				rightNumber++ ;
				mDemoProgressView.setErrorOrRight(rightNumber, 0, errorNumber) ;
			}
			memoryViewPager.setCurrentItem(curretntIndex) ;
			questionId = errorQuestionId[curretntIndex] ;
			mTestNextPagerAdapter.setQuestionId(questionId) ;
		}else {
			int temp = 0 ;
			for (int i = 0; i < errorQuestionId.length; i++) {
				if (!questionFlag[i]) {
					temp++ ;
				}
			}
			if (temp==0) {
				Intent intentSuccess = new Intent() ;
				intentSuccess.setClass(ExamNextTMemoryActivity.this, MemoryFinishActivity.class) ;
				Bundle mBundle = new Bundle() ;
				mBundle.putString("selectSubjectName", selectSubjectName) ;
				intentSuccess.putExtras(mBundle) ;
				startActivity(intentSuccess) ;
				overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left) ;
				ExamNextTMemoryActivity.this.finish() ;
			}else {
				int[] questionIdtemp = new int[temp] ;
				int[] questionAnseN = new int[temp] ;
				int[] questionArrfalg = new int[temp] ;
				int tem = 0 ;
				for (int i = 0; i < errorQuestionId.length; i++) {
					if (!questionFlag[i]) {
						questionIdtemp[tem] = errorQuestionId[i] ;
						questionAnseN[tem] = questionAnswerN[i] ;
						questionArrfalg[tem] = questionArrquestionFlag[i] ;
						tem++ ;
					}
				}
				Intent intent  = new Intent() ;
				intent.setClass(ExamNextTMemoryActivity.this, ExamNextMemoryActivity.class) ;
				Bundle errorBundle = new Bundle() ;
				errorBundle.putString("selectSubjectName", selectSubjectName) ;
				errorBundle.putInt("errorNumber", questionIdtemp.length) ;
				errorBundle.putInt("rightNumber", globSumQuestions-questionIdtemp.length) ;
				errorBundle.putIntArray("errorQuestionId", questionIdtemp) ;
				errorBundle.putIntArray("questionAnswerN", questionAnseN) ;
				errorBundle.putInt("globSumQuestions", globSumQuestions) ;
				errorBundle.putIntArray("questionArrquestionFlag", questionArrfalg) ;
				intent.putExtras(errorBundle) ;
				startActivity(intent) ;
				overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left) ;
				ExamNextTMemoryActivity.this.finish() ;
			}
		}
	}
	
	
	
	public void remmber_again_button(){
		mTestNextPagerAdapter.reLoad(mHandler, curretntIndex) ;
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.back:
			ExamNextTMemoryActivity.this.finish() ;
			overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right) ; 
			break;
		default:
			break;
		}
		
	}
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right) ;
	}
	
	
	public View createItemView(int index) {
		if (index<questionArrquestionFlag.length) {
			if (questionArrquestionFlag[index] ==0) {
				View mView = LayoutInflater.from(ExamNextTMemoryActivity.this).inflate(
						R.layout.view_item_memory, null);
				if (mView != null) {
					WebView mWebView = (WebView) mView
							.findViewById(R.id.webview_item_content);
					mWebView.getSettings().setJavaScriptEnabled(true);
					mWebView.getSettings().setBuiltInZoomControls(false);
					MemoryViewHolder mMemoryViewHolder = new MemoryViewHolder();
					mMemoryViewHolder.questionAnswerNum = questionAnswerN[index] ;
					mMemoryViewHolder.questionId = errorQuestionId[index];
					mMemoryViewHolder.index = index + 1;
					mWebView.setTag(mMemoryViewHolder);
					mWebView.setWebChromeClient(new WebChromeClient());
					mWebView.setWebViewClient(new WebViewClient() {
						@Override
						public boolean shouldOverrideUrlLoading(WebView view, String url) {
							// TODO Auto-generated method stub
							return super.shouldOverrideUrlLoading(view, url);
						}

						@Override
						public void onReceivedError(WebView view, int errorCode,
								String description, String failingUrl) {
							// TODO Auto-generated method stub
							super.onReceivedError(view, errorCode, description,
									failingUrl);
						}

						@Override
						public void onPageFinished(WebView view, String url) {
							// TODO Auto-generated method stub
							MemoryViewHolder mMemoryViewHolder = (MemoryViewHolder) view
									.getTag();
							view.loadUrl("javascript:showContent('"
									+ mMemoryViewHolder.questionContent + "')");
						}
					});
				}
				return mView;
			}else if (questionArrquestionFlag[index]==1) {
				View mView = LayoutInflater.from(ExamNextTMemoryActivity.this).inflate(R.layout.view_item_memory_test, null) ;
				ScrollView mScrollView = (ScrollView) mView.findViewById(R.id.scrollview_id) ;
				MemoryViewHolder memoryViewHolder = new MemoryViewHolder() ;
				memoryViewHolder.index = index+1 ;
				memoryViewHolder.questionId = errorQuestionId[index] ;
				memoryViewHolder.questionAnswerNum = questionAnswerN[index] ;
				mScrollView.setTag(memoryViewHolder) ;
				return mView ;
			}
		}
		return null;
	}
	
	
	public void switchActivity(){
		
		Intent intent = new Intent() ;
		intent.setClass(ExamNextTMemoryActivity.this, ImageViewShowActivity.class) ;
		startActivity(intent) ;
	}
	public void JumShowImage(){
		Intent intent = new Intent() ;
		intent.setClass(ExamNextTMemoryActivity.this, ImageViewShowActivity.class) ;
		startActivity(intent) ;
		
	}
	
	public class MemoryViewHolder {
		public int questionId;
		public String questionContent;
		public int index;
		public int questionAnswerNum  ;
		public String smallImageUrl  ;
		public String largeImageUrl ;
		
		
		public MemoryViewHolder() {
			// TODO Auto-generated constructor stub
			questionId = 0;
			questionContent = "";
			index = 0;
			questionAnswerNum = 0 ;
			
			
			smallImageUrl = "" ;
			largeImageUrl = "" ;
		}
	}
}
