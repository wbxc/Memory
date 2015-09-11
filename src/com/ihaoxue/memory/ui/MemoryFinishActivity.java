package com.ihaoxue.memory.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ihaoxue.memory.MemoryApplication;
import com.ihaoxue.memory.R;
import com.ihaoxue.memory.adapter.MemoryPageAdapter;
import com.ihaoxue.memory.bean.MemoryQuestionPaper;
import com.ihaoxue.memory.net.MemoryICallBack;
import com.ihaoxue.memory.net.MemoryNetTaskManagement;

public class MemoryFinishActivity extends BaseActivity implements OnClickListener {

	private ImageView back ;
	private TextView topTextView ;
	private Button againTenQuesion ;
	private Button practiceOtherSubjects ;
	
	private String topSubjectName  ;
	
	@Override
	protected void initDataDataVariable() {
		// TODO Auto-generated method stub
		topSubjectName = getIntent().getExtras().getString("selectSubjectName") ;
	}

	@Override
	protected void setMainContentView() {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_memory_finish_ui) ;
		MemoryApplication.getInstance().addActivity(MemoryFinishActivity.this) ;
	}

	@Override
	protected void initComponentView() {
		// TODO Auto-generated method stub
		back = (ImageView)findViewById(R.id.back) ;
		topTextView = (TextView)findViewById(R.id.common_text) ;
		againTenQuesion = (Button)findViewById(R.id.againTenQuesion) ;
		practiceOtherSubjects = (Button)findViewById(R.id.practiceOtherSubjects) ;
	}

	@Override
	protected void initEvent() {
		topTextView.setText(topSubjectName) ;
		back.setOnClickListener(this) ;
		againTenQuesion.setOnClickListener(this) ;
		practiceOtherSubjects.setOnClickListener(this) ;
	}

	@Override
	protected void initNetDataRequest() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right) ;
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.back:
			MemoryFinishActivity.this.finish() ;
			overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right) ;
			break;
		case R.id.againTenQuesion:
			againTenQuestions() ;
//			Intent intentMemory = new Intent() ;
//			intentMemory.setClass(MemoryFinishActivity.this, ExamMemoryActivity.class) ;
//			Bundle mBundle = new Bundle() ;
//			mBundle.putString("selectSubjectName",topSubjectName) ;
//			intentMemory.putExtras(mBundle) ;
//			startActivity(intentMemory) ;
//			overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left) ;
//			MemoryFinishActivity.this.finish() ;
			break ;
		case R.id.practiceOtherSubjects:
			Intent intent = new Intent();
			intent.setClass(MemoryFinishActivity.this, MainHomeActivity.class) ;
			startActivity(intent) ;
			overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left) ;
			MemoryFinishActivity.this.finish() ;
			break ;
		default:
			break;
		}
	}
	
	private void againTenQuestions(){
		
		MemoryNetTaskManagement.getInstance(MemoryFinishActivity.this).createPaper(mMemoryICallBack, 1001) ;
		
	}
	private MemoryICallBack mMemoryICallBack = new MemoryICallBack() {
		
		@Override
		public void setProgress(int rate) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void setParams(Bundle bundle) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void setFinished(boolean isover) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void setError(String msg) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void setData(Message data) {
			// TODO Auto-generated method stub
			MemoryQuestionPaper mPaper = (MemoryQuestionPaper)data.obj ; 
			Intent intent = new Intent() ;
			Bundle mBundle = new Bundle() ;
			
			mBundle.putString("selectSubjectName", mPaper.getPaperName()) ;
			mBundle.putIntArray("questionAnswerNum", mPaper.getQuestionAnswerNum()) ;
			mBundle.putIntArray("questionArrFlag", mPaper.getQuestionArrFlag()) ;
			mBundle.putIntArray("questionIdArr", mPaper.getQuestionIdArr()) ;
			mBundle.putInt("globSumQuestions", mPaper.getQuestionAllNum()) ;
			
			intent.putExtras(mBundle) ;
			intent.setClass(MemoryFinishActivity.this, ExamMemoryActivity.class) ;
			startActivity(intent) ;
		    overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right) ;
		    MemoryFinishActivity.this.finish() ;
		}
	};
	
	
	
}
