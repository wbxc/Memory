package com.ihaoxue.memory.ui;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewDebug.FlagToString;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ihaoxue.memory.MemoryApplication;
import com.ihaoxue.memory.R;
import com.ihaoxue.memory.R.menu;
import com.ihaoxue.memory.adapter.MemoryPageAdapter;
import com.ihaoxue.memory.bean.QuestionModel;
import com.ihaoxue.memory.widget.DemoProgressView;

public class MemoryActivity extends BaseActivity implements OnClickListener {

	private ViewPager mViewPager;

	private int sumQuestions;
	private MemoryPageAdapter mMemoryPageAdapter;
	private List<View> mMemoryPagerViews;
	private List<QuestionModel> mQuestionModels;
	private ImageView back;
	private TextView topTextView;
	private String selectSubjectCourse;
	private DemoProgressView mDemoProgressView;
	private int currentIndex = 0;

	// 当前界面显示
	private RelativeLayout study_re;
	private Button rememberButton;
	private Button unrememberButton;

	// 下一个
	private RelativeLayout study_next;
	private Button study_next_button;

	// 再次记忆
	private RelativeLayout again_study;
	private Button again_study_next;
	private Button again_study_again;
	private int sumAllQuestions;

	private List<QuestionModel> mMQuestionModels;
	
	@Override
	protected void initDataDataVariable() {
		// TODO Auto-generated method stub
		sumAllQuestions = 10;

		sumQuestions = sumAllQuestions;
		mMemoryPagerViews = new ArrayList<View>();
		mQuestionModels = new ArrayList<QuestionModel>();
		mMQuestionModels = new ArrayList<QuestionModel>() ;
		
		for (int i = 0; i < sumQuestions; i++) {
			QuestionModel questionModel = new QuestionModel();
			questionModel.setQuestionId(i+"") ;
			questionModel.setQuestionChapter("1Z101000工程经济");
			questionModel.setQuestionSection("1Z101010资金时间价值的计算及应用");
			questionModel.setQuestionTopic("资金时间价值");
			questionModel.setQuestionName("资金时间价值概念");
			mQuestionModels.add(questionModel);
		}
		
		
		
		for (int i = 0; i < sumQuestions; i++) {
			View view = createQuestionView(i);
			mMemoryPagerViews.add(view);
		}
		selectSubjectCourse = getIntent().getExtras().getString("selectSubjectName");
		sumCount = sumQuestions;

	}

	@Override
	protected void setMainContentView() {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_memory_ui);
		MemoryApplication.getInstance().addActivity(MemoryActivity.this);
	}

	@Override
	protected void initComponentView() {
		// TODO Auto-generated method stub
		mViewPager = (ViewPager) findViewById(R.id.content_viewpager);
		back = (ImageView) findViewById(R.id.back);
		topTextView = (TextView) findViewById(R.id.common_text);
		mDemoProgressView = (DemoProgressView) findViewById(R.id.demo_ProgressView);
		mDemoProgressView.setSumCount(sumQuestions);

		study_re = (RelativeLayout) findViewById(R.id.study_re);
		unrememberButton = (Button) findViewById(R.id.unremember);
		rememberButton = (Button) findViewById(R.id.remember);

		study_next = (RelativeLayout) findViewById(R.id.study_next);
		study_next_button = (Button) findViewById(R.id.study_next_button);

		again_study = (RelativeLayout) findViewById(R.id.again_study);
		again_study_next = (Button) findViewById(R.id.again_study_next);
		again_study_again = (Button) findViewById(R.id.again_study_again);

	}

	@Override
	protected void initEvent() {
		// TODO Auto-generated method stub
		rememberButton.setOnClickListener(this);
		unrememberButton.setOnClickListener(this);
		study_next_button.setOnClickListener(this);
		again_study_next.setOnClickListener(this);
		again_study_again.setOnClickListener(this);

		mMemoryPageAdapter = new MemoryPageAdapter(MemoryActivity.this, mMemoryPagerViews);
		mViewPager.setAdapter(mMemoryPageAdapter);
	//	mViewPager.setOffscreenPageLimit(2);
		back.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						MemoryActivity.this.finish();
						MemoryActivity.this.overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
					}
				});
			}
		});
		topTextView.setText(selectSubjectCourse);
	}

	@Override
	protected void initNetDataRequest() {
		// TODO Auto-generated method stub

	}

	public void setSelectCurrent() {
		currentIndex++;
		if (currentIndex < sumQuestions) {
			mViewPager.setCurrentItem(currentIndex);
		} else {

		}

	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
	}

	private int mRight = 0;
	private int mError = 0;
	private int sumCount = 0;

	public void leftCount(int mRight) {
		mDemoProgressView.setCount(mRight);
		// mDemoProgressView.initRight();
	}

	public void rightCount(int mError,int sum) {
		mDemoProgressView.setUnrightInterval(mError,sum);
	}

	public void centerCount(int sumCount) {
		mDemoProgressView.setSumCount(sumCount);
	}

	// 创建View
	public View createQuestionView(int index) {
		View contentView = LayoutInflater.from(MemoryActivity.this).inflate(R.layout.pager_main_memory_ui, null);
		RelativeLayout content = (RelativeLayout) contentView.findViewById(R.id.pager_content);
		DataHolder mDataHolder = new DataHolder();
		mDataHolder.mQuestionModel = mQuestionModels.get(index);
		mDataHolder.index = index + 1;
		content.setTag(mDataHolder);
		return contentView;
	}

	// 创建View
	public View createQuestionViewmm(int index) {
		View contentView = LayoutInflater.from(MemoryActivity.this).inflate(R.layout.pager_main_memory_ui, null);
		RelativeLayout content = (RelativeLayout) contentView.findViewById(R.id.pager_content);
		DataHolder mDataHolder = new DataHolder();
		mDataHolder.mQuestionModel = mMQuestionModels.get(index);
		mDataHolder.index = index + 1;
		content.setTag(mDataHolder);
		return contentView;
	}
	
	
	/**
	 * 记得
	 * 
	 * @param index
	 */
	private void rememberEvent(int index) {
		Log.e("mMemoryPageAdapter", "mMemoryPageAdapter"+index) ;
		View view = mMemoryPageAdapter.getDataView(index);
		RelativeLayout content = (RelativeLayout) view.findViewById(R.id.pager_content);
		DataHolder mDataHolder = (DataHolder) content.getTag() ;
		Log.e("mDataHolder", mDataHolder.mQuestionModel.getQuestionId()+"") ;
		RelativeLayout relativeLayout = (RelativeLayout) content.findViewById(R.id.answer_re);
		relativeLayout.setVisibility(View.VISIBLE);
		study_re.setVisibility(View.INVISIBLE);
		again_study.setVisibility(View.VISIBLE);
	}

	/**
	 * 不记得
	 * 
	 * @param index
	 */
	private void unRemberEvent(int index) {
		View view = mMemoryPageAdapter.getDataView(index);
		RelativeLayout content = (RelativeLayout) view.findViewById(R.id.pager_content);
		RelativeLayout relativeLayout = (RelativeLayout) content.findViewById(R.id.answer_re);
		relativeLayout.setVisibility(View.VISIBLE);
		study_re.setVisibility(View.INVISIBLE);
		study_next.setVisibility(View.VISIBLE);
	}

	private boolean isFirst = true;

	/**
	 * 再次记忆 中的下一个 --- 表示自己记得此题
	 */
	private void againStudyNext() { //
		if (isFirst) { // 表示是第一轮的浏览
			mRight++;
			leftCount(mRight);
			sumCount--;
			centerCount(sumCount);
		} else { // 非第一轮的浏览
			mRight++;
			leftCount(mRight);
			mError = mError - 1;
			rightCount(mError,0);
		}

		// 判断是否是第一轮的
		if (sumCount <= 0) {
			isFirst = false;
		} else {
			isFirst = true;
		}

		if (mError == 0 && mRight == sumAllQuestions) {
			// 表示已经完成记忆练习
			Intent intent = new Intent();
			intent.setClass(MemoryActivity.this, MemoryFinishActivity.class);
			Bundle mBundle = new Bundle();
			mBundle.putString("selectSubjectName", selectSubjectCourse);
			intent.putExtras(mBundle);
			startActivity(intent);
			overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
			MemoryActivity.this.finish();
		} else {
			currentIndex++;
			if (currentIndex < sumQuestions) {
				mViewPager.setCurrentItem(currentIndex);
				again_study.setVisibility(View.INVISIBLE);
				study_re.setVisibility(View.VISIBLE);
			} else {
				again_study.setVisibility(View.INVISIBLE);
				study_re.setVisibility(View.VISIBLE);
				sumQuestions = mError;
				createViewPagerViews();
				currentIndex = 0;
			}
		}
	}

	/**
	 * 不记得---的下一个
	 */
	private void studyNextEvent() {

		if (isFirst) {
			sumCount--;
			centerCount(sumCount);
			mError++;
			rightCount(mError,sumCount);
			study_next.setVisibility(View.INVISIBLE);
			study_re.setVisibility(View.VISIBLE);
		} else {
			study_next.setVisibility(View.INVISIBLE);
			study_re.setVisibility(View.VISIBLE);
		}
		if (sumCount <= 0) {
			isFirst = false;
		} else {
			isFirst = true;
		}

		if (mError == 0 && mRight == sumAllQuestions) {

		} else {
			currentIndex++;
			if (currentIndex < sumQuestions) {
				mViewPager.setCurrentItem(currentIndex);
				study_next.setVisibility(View.INVISIBLE);
				study_re.setVisibility(View.VISIBLE);
			} else {
				sumQuestions = mError;
				createViewPagerViews();
				currentIndex = 0;
				study_next.setVisibility(View.INVISIBLE);
				study_re.setVisibility(View.VISIBLE);
			}
		}
	}

	public void createViewPagerViews() {
		mMemoryPagerViews.clear();
		
		for (int i = 0; i < sumQuestions; i++) {
			QuestionModel questionModel = new QuestionModel();
			questionModel.setQuestionId(i+"") ;
			questionModel.setQuestionChapter("1Z101000工程经济_计算机");
			questionModel.setQuestionSection("1Z101010资金时间价值的计算及应用——简史");
			questionModel.setQuestionTopic("资金时间价值");
			questionModel.setQuestionName("资金时间价值概念");
			mMQuestionModels.add(questionModel);
		}
		
		
		
		
		
		
		for (int i = 0; i < sumQuestions; i++) {
			View view = createQuestionViewmm(i);
			mMemoryPagerViews.add(view);
		}
		mMemoryPageAdapter.setDataViews(mMemoryPagerViews);
	}

	private void againStudyAgain(int index) {
		again_study.setVisibility(View.INVISIBLE);
		study_re.setVisibility(View.VISIBLE);
		View view = mMemoryPageAdapter.getDataView(index);
		RelativeLayout content = (RelativeLayout) view.findViewById(R.id.pager_content);
		RelativeLayout relativeLayout = (RelativeLayout) content.findViewById(R.id.answer_re);
		relativeLayout.setVisibility(View.INVISIBLE);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.remember:
			rememberEvent(currentIndex);
			break;
		case R.id.unremember:
			unRemberEvent(currentIndex);
			break;
		case R.id.study_next_button:
			studyNextEvent();
			break;
		case R.id.again_study_next:
			againStudyNext();
			break;
		case R.id.again_study_again:
			againStudyAgain(currentIndex);
			break;
		}
	}

	public class DataHolder {
		public QuestionModel mQuestionModel;
		public int index;

		public DataHolder() {
			// TODO Auto-generated constructor stub
			index = 0;
			mQuestionModel = new QuestionModel();
		}
	}

}
