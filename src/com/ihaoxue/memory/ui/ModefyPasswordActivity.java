package com.ihaoxue.memory.ui;

import com.ihaoxue.memory.MemoryApplication;
import com.ihaoxue.memory.R;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ModefyPasswordActivity extends BaseActivity implements OnClickListener {

	
	private ImageView back ;
	private TextView commonText ;
	
	private Button modefyPassword ;
  	@Override
	protected void initDataDataVariable() {
		// TODO Auto-generated method stub
	}

	@Override
	protected void setMainContentView() {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_modify_password) ;
		MemoryApplication.getInstance().addActivity(ModefyPasswordActivity.this) ;
	}

	@Override
	protected void initComponentView() {
		// TODO Auto-generated method stub
		back = (ImageView)findViewById(R.id.back) ;
		commonText = (TextView)findViewById(R.id.common_text) ;
		modefyPassword  = (Button)findViewById(R.id.modefy_password) ;
	}

	@Override
	protected void initEvent() {
		// TODO Auto-generated method stub
		commonText.setText("修改密码") ;
		modefyPassword.setOnClickListener(this) ;
		back.setOnClickListener(this) ;
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

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.modefy_password:
			Toast.makeText(ModefyPasswordActivity.this, "修改密码功能暂未开通", Toast.LENGTH_SHORT).show() ;
			break;
		case R.id.back:
			ModefyPasswordActivity.this.finish() ;
			overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right) ;
			break ;
		default:
			break;
		}
	}

}
