package com.ihaoxue.memory.ui;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.SaveCallback;
import com.ihaoxue.memory.MemoryApplication;
import com.ihaoxue.memory.R;
import com.ihaoxue.memory.model.FeedBackModel;
import com.ihaoxue.memory.utils.ShareTools;
import com.ihaoxue.memory.utils.StringUtil;
import com.ihaoxue.memory.widget.DemoImageToast;
import com.ihaoxue.memory.widget.DemoToast;

public class FeekBackActivity extends BaseActivity implements OnClickListener {

	private ImageView back ;
	private TextView commonText ;
	
	private EditText feedBackContent  ;
	private EditText feedBackPhone ;
	private Button submitButton ;
	
	
	@Override
	protected void initDataDataVariable() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void setMainContentView() {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_feed_back_ui) ;
		MemoryApplication.getInstance().addActivity(FeekBackActivity.this) ;
	}

	@Override
	protected void initComponentView() {
		// TODO Auto-generated method stub
		back = (ImageView)findViewById(R.id.back) ;
		commonText = (TextView)findViewById(R.id.common_text) ;
		feedBackContent = (EditText)findViewById(R.id.feedback_content) ;
		feedBackPhone = (EditText)findViewById(R.id.feedback_phone) ;
		submitButton = (Button)findViewById(R.id.submit_button) ;
	}

	@Override
	protected void initEvent() {
		// TODO Auto-generated method stub
		commonText.setText("意见反馈") ;
		back.setOnClickListener(this) ;
		submitButton.setOnClickListener(this) ;
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
	
	private void saveUserOpinion(){
		
		if (StringUtil.isNotStringEmpty(feedBackContent.getText().toString().trim())
				&&StringUtil.isNotStringEmpty(feedBackPhone.getText().toString().trim())) {
			FeedBackModel mFeedBackModel = new FeedBackModel() ;
			mFeedBackModel.setContent(feedBackContent.getText().toString().trim()) ;
			mFeedBackModel.setAddress(feedBackPhone.getText().toString().trim()) ;
			mFeedBackModel.setUserName(ShareTools.getInstance().readUserInfo(FeekBackActivity.this).get(ShareTools.USER_NAME)) ;
			mFeedBackModel.getAvObject().saveInBackground(new SaveCallback() {
				
				@Override
				public void done(AVException arg0) {
					// TODO Auto-generated method stub
					if (arg0==null) {
						//DemoToast.getInstance(FeekBackActiivity.this).show("提交成功", R.drawable.ic_launcher) ;
						DemoImageToast.getInstance(FeekBackActivity.this).show(R.drawable.toast_feed_back) ;
						overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right) ;
						FeekBackActivity.this.finish() ;
					}else {
						DemoToast.getInstance(FeekBackActivity.this).show("服务器异常或网络有问题重新提交", 0) ;
					}
				}
			}) ;
		}else {
			DemoToast.getInstance(FeekBackActivity.this).show("请您留下对我们的意见，我们会努力改进我们的产品", 0) ;
		}
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.back:
			FeekBackActivity.this.finish() ;
			overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right) ;
			break;
		case R.id.submit_button:
			saveUserOpinion() ;
			break ;
		default:
			break;
		}
	}
	
	

}
