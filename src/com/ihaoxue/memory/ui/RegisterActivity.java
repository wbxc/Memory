package com.ihaoxue.memory.ui;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ihaoxue.memory.MemoryApplication;
import com.ihaoxue.memory.R;
import com.ihaoxue.memory.widget.CustomMemoryDialog;
import com.ihaoxue.memory.widget.CustomMemoryDialog.CustomerInterface;

public class RegisterActivity extends BaseActivity implements OnClickListener {

	private  ImageView backImage ;
	private  TextView commonTextView ;
	private  Button mailRegister ;
	private  Button phone_register ;
	private CustomMemoryDialog mCustomMemoryDialog ;
	@Override
	protected void initDataDataVariable() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void setMainContentView() {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_register_ui) ;
		mCustomMemoryDialog = CustomMemoryDialog.getCustomMemoryDialog(RegisterActivity.this,mCustomerInterface ) ;
		mCustomMemoryDialog.setMessage("取消", "手机注册暂不可用请选择邮箱注册", "go邮箱注册")  ;
		
		MemoryApplication.getInstance().addActivity(RegisterActivity.this) ;
	}

	private CustomerInterface mCustomerInterface = new CustomerInterface() {
		
		@Override
		public void loginGoButton() {
			// TODO Auto-generated method stub
			mCustomMemoryDialog.dismiss() ;
			Intent intent = new Intent() ;
			intent.setClass(RegisterActivity.this, RegisterMailActivity.class) ;
			startActivity(intent) ;
			overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left) ;
		}
		
		@Override
		public void dimissButton() {
			// TODO Auto-generated method stub
			mCustomMemoryDialog.dismiss() ;
		}
	};
	@Override
	protected void initComponentView() {
		// TODO Auto-generated method stub
		backImage = (ImageView)findViewById(R.id.back) ;
		commonTextView = (TextView)findViewById(R.id.common_text) ; 
		mailRegister = (Button)findViewById(R.id.mail_register) ;
		phone_register = (Button)findViewById(R.id.phone_register) ;
	}

	@Override
	protected void initEvent() {
		// TODO Auto-generated method stub
		commonTextView.setText("手机号注册") ;
		mailRegister.setOnClickListener(this) ;
		phone_register.setOnClickListener(this) ;
		backImage.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				RegisterActivity.this.finish() ;
				overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right) ;
			}
		}) ;
	}

	@Override
	protected void initNetDataRequest() {
		// TODO Auto-generated method stub
		
	}

	
	
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.mail_register:
			Intent intentMailRegister = new Intent() ;
			intentMailRegister.setClass(RegisterActivity.this, RegisterMailActivity.class) ;
			startActivity(intentMailRegister) ;
			overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left) ;
			RegisterActivity.this.finish() ;
			break;
		case R.id.phone_register:
			mCustomMemoryDialog.show() ;
			break ;
		default:
			break;
		}
	}

}
