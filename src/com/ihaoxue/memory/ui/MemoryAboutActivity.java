package com.ihaoxue.memory.ui;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.ihaoxue.memory.MemoryApplication;
import com.ihaoxue.memory.R;

public class MemoryAboutActivity extends BaseActivity {

	private ImageView back ;
	private TextView commonTextView ;
	
	
	@Override
	protected void initDataDataVariable() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void setMainContentView() {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_about_memory) ;
		MemoryApplication.getInstance().addActivity(MemoryAboutActivity.this) ;
	}

	@Override
	protected void initComponentView() {
		// TODO Auto-generated method stub
		back = (ImageView)findViewById(R.id.back) ;
		commonTextView  = (TextView)findViewById(R.id.common_text) ;
	}

	@Override
	protected void initEvent() {
		// TODO Auto-generated method stub
		commonTextView.setText("关于") ;
		back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				MemoryAboutActivity.this.finish() ;
				overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right) ;
			}
		}) ;
		
	}
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right) ;
	}

	@Override
	protected void initNetDataRequest() {
		// TODO Auto-generated method stub

	}

}
