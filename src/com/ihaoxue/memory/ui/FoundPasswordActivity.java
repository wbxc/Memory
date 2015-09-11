package com.ihaoxue.memory.ui;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.ihaoxue.memory.MemoryApplication;
import com.ihaoxue.memory.R;

public class FoundPasswordActivity extends BaseActivity implements OnClickListener {

	private TextView common_textView ;
	private ImageView back ;
	
	
	@Override
	protected void initDataDataVariable() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void setMainContentView() {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_password_found) ;
		MemoryApplication.getInstance().addActivity(FoundPasswordActivity.this) ;
	}

	@Override
	protected void initComponentView() {
		// TODO Auto-generated method stub
		back = (ImageView)findViewById(R.id.back) ;
		common_textView = (TextView)findViewById(R.id.common_text) ;
	}

	@Override
	protected void initEvent() {
		// TODO Auto-generated method stub
		common_textView.setText("找回密码") ;
		back.setOnClickListener(this) ;
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
			FoundPasswordActivity.this.finish() ;
			overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right) ;
			break;
		default:
			break;
		}
	}

}
