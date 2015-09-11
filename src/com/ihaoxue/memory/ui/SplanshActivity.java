package com.ihaoxue.memory.ui;

import android.content.Intent;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;

import com.ihaoxue.memory.MemoryApplication;
import com.ihaoxue.memory.R;


public class SplanshActivity extends BaseActivity {


	private AlphaAnimation mAlphaAnimation ;
	private ImageView mLoginImageView ;
	@Override
	protected void initDataDataVariable() {
		// TODO Auto-generated method stub
		mAlphaAnimation = new AlphaAnimation(0.3f, 1.0f) ;
		mAlphaAnimation.setDuration(500) ;
	}

	@Override
	protected void setMainContentView() {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_splansh_ui) ;
		MemoryApplication.getInstance().addActivity(SplanshActivity.this) ;
	}

	@Override
	protected void initComponentView() {
		// TODO Auto-generated method stub
		mLoginImageView = (ImageView) findViewById(R.id.splan_bg) ;
	}

	@Override
	protected void initEvent() {
		// TODO Auto-generated method stub
		mLoginImageView.setAnimation(mAlphaAnimation) ;
		mAlphaAnimation.setAnimationListener(new AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationEnd(Animation animation) {
				// TODO Auto-generated method stub
				Intent intent = new Intent() ;
				intent.setClass(SplanshActivity.this, LoginActivity.class) ;
				startActivity(intent) ;
				finish() ;
//				
//				Intent intent = new Intent() ;
//				intent.setClass(SplanshActivity.this, FirstLaunchActivity.class) ;
//				startActivity(intent) ;
//				finish() ;
			}
		}) ;
	}
	@Override
	protected void initNetDataRequest() {
		// TODO Auto-generated method stub
		
	}
	
}
