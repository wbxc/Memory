package com.ihaoxue.memory.ui;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.ihaoxue.image.image.cache.ImageCacheManger;
import com.ihaoxue.memory.MemoryApplication;
import com.ihaoxue.memory.R;
import com.ihaoxue.memory.adapter.AnmaPageTransformer;
import com.ihaoxue.memory.adapter.ExamPagerAdapter;
import com.ihaoxue.memory.widget.DemoProgressView;
import com.ihaoxue.memory.widget.MemoryViewPager;

public class ExamMemoryActivity extends BaseActivity implements OnClickListener {

	String question = "{" + "\"questionId\": \"123\", \"questionContent\": "
			+ "\"你好____你是____，请问你是____?\",\"answerSum\": \"3\",\"questionArr\":"
			+ " [\"你好世界你是____，请问你是____?\", \"你好____你是中国，请问你是____?\", " + " \"你好____你是____，请问你是哪里?\" ]" + "}";

	public static String content = "<p>1.资金的 ____(1)____ 。在单位时间的资金增值率一定的条件下，资金使用时间越长，资金的时间价值越大；使用时间越短，则资金的时间价值越小。</P>"
			+ "<p>2.资金___(2)_____。在其他条件不变的情况下，资金数量越多，资金的时间价值就越多；反之，资金的时间价值则越少。</p>"
			+ "<p>3.资金____(3)____。在总资金一定的情况下，前期投入的资金越多，资金的朿效益越大；反之，后期投入的资金越多，"
			+ "资金的负效益越小。而在资金回收额一定的情况下，离现在越近的时间回收的资金越多，资金的时间价值就越多；反之，离现在越远的时间回收的资金越多，资金的时间价值就越少。</P>";

	public static String question1 = "<p>1.资金的 _使用时间_ 。在单位时间的资金增值率一定的条件下，资金使用时间越长，资金的时间价值越大；使用时间越短，则资金的时间价值越小。</p>"
			+ "<p>2.资金___(2)_____。在其他条件不变的情况下，资金数量越多，资金的时间价值就越多；反之，资金的时间价值则越少。</p>"
			+ "<p>3.资金____(3)____。在总资金一定的情况下，前期投入的资金越多，资金的朿效益越大；反之，后期投入的资金越多，"
			+ "资金的负效益越小。而在资金回收额一定的情况下，离现在越近的时间回收的资金越多，资金的时间价值就越多；反之，离现在越远的时间回收的资金越多，资金的时间价值就越少。</p>";

	public static String question2 = "<p>1.资金的 ____(1)____ 。在单位时间的资金增值率一定的条件下，资金使用时间越长，资金的时间价值越大；使用时间越短，则资金的时间价值越小。</p>"
			+ "<p>2.资金_使用时间_。在其他条件不变的情况下，资金数量越多，资金的时间价值就越多；反之，资金的时间价值则越少。</p>"
			+ "<p>3.资金____(3)____。在总资金一定的情况下，前期投入的资金越多，资金的朿效益越大；反之，后期投入的资金越多，"
			+ "资金的负效益越小。而在资金回收额一定的情况下，离现在越近的时间回收的资金越多，资金的时间价值就越多；反之，离现在越远的时间回收的资金越多，资金的时间价值就越少。</p>";

	public static String question3 = "<p>1.资金的 ____(1)____ 。在单位时间的资金增值率一定的条件下，资金使用时间越长，资金的时间价值越大；使用时间越短，则资金的时间价值越小。</p>"
			+ "<p>2.资金___(2)_____。在其他条件不变的情况下，资金数量越多，资金的时间价值就越多；反之，资金的时间价值则越少。</p>"
			+ "<p>3.资金_使用时间_。在总资金一定的情况下，前期投入的资金越多，资金的朿效益越大；反之，后期投入的资金越多，"
			+ "资金的负效益越小。而在资金回收额一定的情况下，离现在越近的时间回收的资金越多，资金的时间价值就越多；反之，离现在越远的时间回收的资金越多，资金的时间价值就越少。</p>";

	public static String questionAnswer = "<p>1.资金的 _使用时间_ 。在单位时间的资金增值率一定的条件下，资金使用时间越长，资金的时间价值越大；使用时间越短，则资金的时间价值越小。</p>"
			+ "<p>2.资金_使用时间_。在其他条件不变的情况下，资金数量越多，资金的时间价值就越多；反之，资金的时间价值则越少。</p>"
			+ "<p>3.资金_使用时间_。在总资金一定的情况下，前期投入的资金越多，资金的朿效益越大；反之，后期投入的资金越多，"
			+ "资金的负效益越小。而在资金回收额一定的情况下，离现在越近的时间回收的资金越多，资金的时间价值就越多；反之，离现在越远的时间回收的资金越多，资金的时间价值就越少。</p>";

	public static String questionAll = "{" + "\"questionId\": \"123\", \"questionContent\": " + "\"" + content
			+ "\",\"answerSum\": \"3\",\"questionArr\":" + " [\"" + question1 + "\", \"" + question2 + "\", " + " \"" + question3
			+ "\" ]," + "\"" + "questionAnswer" + "\":\"" + questionAnswer + "\"" + "}";

	private ViewPager mMemoryViewPager;
	private ExamPagerAdapter mTestPagerAdapter;
	private List<View> mWebViews;
	private int[] questionIdArr;
	private DemoProgressView mDemoProgressView;
	private ImageView back;

	private int currentIndex = 0;
	private int questionAnswerNum[];
	private boolean[] questionStatus;
	private int globSumQuestions = 0;
	private int currentQuestionId = 0;
	private int[] questionArrFlag;
	private String selectSubjectName;
	private TextView commonTextView;
	private Handler mHandler = new Handler();

	@Override
	protected void initDataDataVariable() {
		// TODO Auto-generated method stub

		mWebViews = new ArrayList<View>();
		Bundle mBundle = getIntent().getExtras();
		selectSubjectName = mBundle.getString("selectSubjectName");
		globSumQuestions = mBundle.getInt("globSumQuestions");
		questionIdArr = mBundle.getIntArray("questionIdArr");
		questionAnswerNum = mBundle.getIntArray("questionAnswerNum");
		questionArrFlag = mBundle.getIntArray("questionArrFlag");
		sumCount = globSumQuestions;
		questionStatus = new boolean[globSumQuestions];

		for (int i = 0; i < globSumQuestions; i++) {
			// questionIdArr[i] = i;
			questionStatus[i] = true;
			View view = createItemView(i);
			mWebViews.add(view);
		}
		currentQuestionId = questionIdArr[0];
	}

	@Override
	protected void setMainContentView() {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_test_memory);
		MemoryApplication.getInstance().addActivity(ExamMemoryActivity.this);
	}

	@Override
	protected void initComponentView() {
		// mWebView = (WebView) findViewById(R.id.web_content);
		mMemoryViewPager = (MemoryViewPager) findViewById(R.id.memory_viewpager);
		mDemoProgressView = (DemoProgressView) findViewById(R.id.demo_ProgressView);
		back = (ImageView) findViewById(R.id.back);
		commonTextView = (TextView) findViewById(R.id.common_text);
	}

	@Override
	protected void initEvent() {
		mDemoProgressView.setSumCount(questionIdArr.length);
		mTestPagerAdapter = new ExamPagerAdapter(ExamMemoryActivity.this, mWebViews);
		mTestPagerAdapter.setQuestionArr(questionIdArr);
		mMemoryViewPager.setOffscreenPageLimit(2);
		mTestPagerAdapter.setQuestionId(currentQuestionId);
		mTestPagerAdapter.setQuestionArrFlag(questionArrFlag);
		mMemoryViewPager.setAdapter(mTestPagerAdapter);
		mMemoryViewPager.setPageTransformer(true, new AnmaPageTransformer());
		mMemoryViewPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int arg0) {
				// TODO Auto-generated method stub

				currentIndex = arg0;

			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
				// mTestPagerAdapter.loadQuestion(mHandler, currentIndex) ;
			}
		});
		back.setOnClickListener(this);
		commonTextView.setText(selectSubjectName);
	}

	@Override
	protected void initNetDataRequest() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.back:
			ExamMemoryActivity.this.finish();
			overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
			break;
		default:
			break;
		}

	}

	public void refreshTestMemoryQuestion(int position) {
		mTestPagerAdapter.refresh(mHandler, position);
		questionStatus[currentIndex] = false;
	}

	public void leftCount(int mRight, int sum) {
		mDemoProgressView.setCount2(mRight, sum);
		// mDemoProgressView.initRight();
	}

	public void rightCount(int mError, int sum) {
		mDemoProgressView.setUnrightInterval(mError, sum);
	}

	public void centerCount(int sumCount) {
		mDemoProgressView.setSumCount(sumCount);
	}

	private int mRight = 0;
	private int mError = 0;
	private int sumCount = 0;
	private int temp = 0;

	public void nextQuestion() {
		temp = currentIndex;
		currentIndex++;
		if (currentIndex < questionIdArr.length) {
			if (questionStatus[temp]) {
				mRight++;
				sumCount--;
				leftCount(mRight, sumCount);
			} else {
				mError++;
				sumCount--;
				rightCount(mError, sumCount);
			}
			currentQuestionId = questionIdArr[currentIndex];
			mTestPagerAdapter.setQuestionId(currentQuestionId);
			mMemoryViewPager.setCurrentItem(currentIndex);
		} else {
			int flagSum = 0;
			for (int i = 0; i < 10; i++) {
				if (!questionStatus[i]) {
					flagSum++;
				}
			}

			int[] questionIdtemp = new int[flagSum];
			int[] questionAnswerN = new int[flagSum];
			int[] questionArrquestionFlag = new int[flagSum];

			int temp = 0;
			for (int i = 0; i < globSumQuestions; i++) {
				if (!questionStatus[i]) {
					questionIdtemp[temp] = questionIdArr[i];
					questionAnswerN[temp] = questionAnswerNum[i];
					questionArrquestionFlag[temp] = questionArrFlag[i];
					temp++;
				}
			}

			if (flagSum == 0) { // 表示所有的都学会了
				// topSubjectName =
				// getIntent().getExtras().getString("selectSubjectName") ;
				Intent intentSuccess = new Intent();
				Bundle mBundle = new Bundle();
				mBundle.putString("selectSubjectName", selectSubjectName);
				intentSuccess.putExtras(mBundle);
				intentSuccess.setClass(ExamMemoryActivity.this, MemoryFinishActivity.class);
				startActivity(intentSuccess);
				overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
				ExamMemoryActivity.this.finish();
			} else { // 跳转到下个界面继续学习
				Intent intent = new Intent();
				intent.setClass(ExamMemoryActivity.this, ExamNextMemoryActivity.class);
				Bundle errorBundle = new Bundle();
				errorBundle.putString("selectSubjectName", selectSubjectName);
				errorBundle.putInt("globSumQuestions", globSumQuestions);
				errorBundle.putInt("errorNumber", flagSum);
				errorBundle.putInt("rightNumber", globSumQuestions - flagSum);
				errorBundle.putIntArray("errorQuestionId", questionIdtemp);
				errorBundle.putIntArray("questionAnswerN", questionAnswerN);
				errorBundle.putIntArray("questionArrquestionFlag", questionArrquestionFlag);
				intent.putExtras(errorBundle);
				startActivity(intent);
				overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
				ExamMemoryActivity.this.finish();
			}
		}
	}

	public void nextQuestion2() {
		temp = currentIndex;
		questionStatus[temp] = false;
		currentIndex++;
		if (currentIndex < questionIdArr.length) {
			if (questionStatus[temp]) {
				mRight++;
				sumCount--;
				leftCount(mRight, sumCount);
			} else {
				mError++;
				sumCount--;
				rightCount(mError, sumCount);
			}
			mMemoryViewPager.setCurrentItem(currentIndex);
			currentQuestionId = questionIdArr[currentIndex];
			mTestPagerAdapter.setQuestionId(currentQuestionId);
		} else {
			int flagSum = 0;
			for (int i = 0; i < 10; i++) {
				if (!questionStatus[i]) {
					flagSum++;
				}
			}

			int[] questionIdtemp = new int[flagSum];
			int[] questionAnswerN = new int[flagSum];
			int[] questionArrquestionFlag = new int[flagSum];

			int temp = 0;
			for (int i = 0; i < globSumQuestions; i++) {
				if (!questionStatus[i]) {
					questionIdtemp[temp] = questionIdArr[i];
					questionAnswerN[temp] = questionAnswerNum[i];
					questionArrquestionFlag[temp] = questionArrFlag[i];
					temp++;
				}
			}

			if (flagSum == 0) { // 表示所有的都学会了
				// topSubjectName =
				// getIntent().getExtras().getString("selectSubjectName") ;
				Intent intentSuccess = new Intent();
				Bundle mBundle = new Bundle();
				mBundle.putString("selectSubjectName", selectSubjectName);
				intentSuccess.putExtras(mBundle);
				intentSuccess.setClass(ExamMemoryActivity.this, MemoryFinishActivity.class);
				startActivity(intentSuccess);
				overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
				ExamMemoryActivity.this.finish();
			} else { // 跳转到下个界面继续学习
				Intent intent = new Intent();
				intent.setClass(ExamMemoryActivity.this, ExamNextMemoryActivity.class);
				Bundle errorBundle = new Bundle();
				errorBundle.putString("selectSubjectName", selectSubjectName);
				errorBundle.putInt("globSumQuestions", globSumQuestions);
				errorBundle.putInt("errorNumber", flagSum);
				errorBundle.putInt("rightNumber", globSumQuestions - flagSum);
				errorBundle.putIntArray("errorQuestionId", questionIdtemp);
				errorBundle.putIntArray("questionAnswerN", questionAnswerN);
				errorBundle.putIntArray("questionArrquestionFlag", questionArrquestionFlag);
				intent.putExtras(errorBundle);
				startActivity(intent);
				overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
				ExamMemoryActivity.this.finish();
			}
		}
	}

	public void remmber_again_button() {
		mTestPagerAdapter.reLoad(mHandler, currentIndex);
	}

	public void reloadButton() {
		mTestPagerAdapter.reLoad(mHandler, currentIndex);
	}

	public void remmberKnowleage() {
		if (currentIndex < questionIdArr.length) {
			mTestPagerAdapter.loadQuestion(mHandler, currentIndex);
		}
	}

	@SuppressLint("SetJavaScriptEnabled")
	public View createItemView(int index) {
		if (index < questionArrFlag.length) {
			if (questionArrFlag[index] == 0) {
				View mView = LayoutInflater.from(ExamMemoryActivity.this).inflate(R.layout.view_item_memory, null);
				if (mView != null) {
					WebView mWebView = (WebView) mView.findViewById(R.id.webview_item_content);
					mWebView.getSettings().setJavaScriptEnabled(true);
					mWebView.getSettings().setBuiltInZoomControls(false);
					MemoryViewHolder mMemoryViewHolder = new MemoryViewHolder();
					mMemoryViewHolder.questionId = questionIdArr[index];
					mMemoryViewHolder.questionAnswerNumber = questionAnswerNum[index];
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
						public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
							// TODO Auto-generated method stub
							super.onReceivedError(view, errorCode, description, failingUrl);
						}

						@Override
						public void onPageFinished(WebView view, String url) {
							// TODO Auto-generated method stub
							MemoryViewHolder mMemoryViewHolder = (MemoryViewHolder) view.getTag();
							//view.loadUrl("javascript:showContentAnswer('" + mMemoryViewHolder.questionContent + "')");
							view.loadUrl("javascript:showContent('" + mMemoryViewHolder.questionContent + "')");
						}
					});
				}
				return mView;
			} else if (questionArrFlag[index] == 1) {

				View mView = LayoutInflater.from(ExamMemoryActivity.this).inflate(R.layout.view_item_memory_test, null);

				if (mView != null) {
					ScrollView mScrollView = (ScrollView) mView.findViewById(R.id.scrollview_id);
					MemoryViewHolder memoryViewHolder = new MemoryViewHolder();
					memoryViewHolder.questionId = questionIdArr[index];
					memoryViewHolder.index = index + 1;
					memoryViewHolder.questionAnswerNumber = questionAnswerNum[index];
					mScrollView.setTag(memoryViewHolder);
				}
				return mView;
			}
		}
		return null;
	}

	public class MemoryViewHolder {
		public int questionId;
		public String questionContent;
		public int index;
		public int questionAnswerNumber;

		public String smallImageUrl;
		public String largeImageUrl;
		public String tempQuestionContent ;
		
		public MemoryViewHolder() {
			// TODO Auto-generated constructor stub
			questionId = 0;
			questionContent = "";
			index = 0;
			questionAnswerNumber = 0;
			smallImageUrl = "";
			largeImageUrl = "";
			tempQuestionContent = "" ;
		}
	}

	public void JumShowImage() {

		Intent intent = new Intent();
		intent.setClass(ExamMemoryActivity.this, ImageViewShowActivity.class);
		startActivity(intent);

	}

	public void imageShow(ImageView imageView) {
		ImageCacheManger.loadImage("http://a3.att.hudong.com/01/61/01200000032142134377613701370_s.jpg", imageView,
				getBitmapFromResources(ExamMemoryActivity.this, R.drawable.ic_launcher),
				getBitmapFromResources(ExamMemoryActivity.this, R.drawable.ic_launcher));
	}

	public static Bitmap getBitmapFromResources(Activity act, int resId) {
		Resources res = act.getResources();
		return BitmapFactory.decodeResource(res, resId);
	}

}
