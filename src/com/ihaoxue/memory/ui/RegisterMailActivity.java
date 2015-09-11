package com.ihaoxue.memory.ui;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.SignUpCallback;
import com.ihaoxue.memory.MemoryApplication;
import com.ihaoxue.memory.R;
import com.ihaoxue.memory.model.UserInfo;
import com.ihaoxue.memory.utils.ShareTools;
import com.ihaoxue.memory.utils.StringUtil;

public class RegisterMailActivity extends BaseActivity implements OnClickListener {

	private ImageView back ;
	private TextView commonTitle ;
	
	private EditText userName ;
	private EditText mailName ;
	private EditText settingPassword ;
	private EditText confirmPassword ;
	private Button registerButton ;
	private int temp = 0  ;
	
	@Override
	protected void initDataDataVariable() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void setMainContentView() {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_register_mail_ui) ;
		MemoryApplication.getInstance().addActivity(RegisterMailActivity.this) ;
	}

	@Override
	protected void initComponentView() {
		// TODO Auto-generated method stub
		userName  = (EditText)findViewById(R.id.user_name) ;
		mailName = (EditText)findViewById(R.id.mail_name) ;
		settingPassword = (EditText)findViewById(R.id.setting_password) ;
		confirmPassword = (EditText)findViewById(R.id.confirm_password) ;
		registerButton = (Button)findViewById(R.id.submit_register) ;
		commonTitle = (TextView)findViewById(R.id.common_text) ;
		back = (ImageView)findViewById(R.id.back) ;
	}

	@Override
	protected void initEvent() {
		// TODO Auto-generated method stub
		registerButton.setOnClickListener(this) ;
		back.setOnClickListener(this) ;
		commonTitle.setText(getResources().getString(R.string.common_mail_register)) ;
	}

	@Override
	protected void initNetDataRequest() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.submit_register:
			submitRegister() ;
			break;
		case R.id.back:
			RegisterMailActivity.this.finish() ;
			overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right) ;
			break ;
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
	
	public void submitRegister(){
		if (StringUtil.isNotStringEmpty(userName.getText().toString().trim())
				&&StringUtil.isNotStringEmpty(mailName.getText().toString())
				&&StringUtil.isNotStringEmpty(settingPassword.getText().toString().trim())
				&&StringUtil.isNotStringEmpty(confirmPassword.getText().toString().trim())) {
			if (StringUtil.isEmailAddress(mailName.getText().toString().trim())) {
				
				try {
					if (StringUtil.isStringEquals(settingPassword.getText().toString().trim(), confirmPassword.getText().toString().trim())) {
		
						new Thread(mRunnable).start() ;
					}else {
						settingPassword.setText("") ;
						confirmPassword.setText("") ;
						Toast.makeText(RegisterMailActivity.this, "密码不一致", Toast.LENGTH_SHORT).show() ;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else {
				mailName.setText("") ;
				Toast.makeText(RegisterMailActivity.this, "邮箱格式不正确", Toast.LENGTH_SHORT).show() ;
			}
		}else {
			Toast.makeText(RegisterMailActivity.this, "用户,密码,邮箱都不能为空", Toast.LENGTH_SHORT).show() ;
		}
		
		
	}

	private Runnable  mRunnable = new Runnable() {
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			AVQuery<AVUser> mAvQuery = AVUser.getQuery() ;
			int temp = 0;
			try {
				temp = mAvQuery.count()+1 ;
				Message message = Message.obtain() ;
				message.what  = 100 ;
				message.obj = temp ;
				mHandler.sendMessage(message) ;
			} catch (AVException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	};
	
	private Handler mHandler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 100:
				temp = (Integer) msg.obj ;
				UserInfo userInfo = UserInfo.getInstance() ;
				userInfo.setMemoryMail(mailName.getText().toString().trim()) ;
				userInfo.setMemoryPassword(confirmPassword.getText().toString().trim()) ;
				userInfo.setMemoryUserName(userName.getText().toString().trim()) ;
				userInfo.setUserId(String.valueOf(temp)) ;
				
				userInfo.signUpInBackground(new SignUpCallback() {
					
					@Override
					public void done(AVException arg0) {
						if (arg0 == null) {
							
							ShareTools.getInstance().saveUserInfo(RegisterMailActivity.this, 
									userName.getText().toString().trim(),
									confirmPassword.getText().toString().trim(), 
									String.valueOf(temp)) ; 
							Intent intent = new Intent() ;
							intent.setClass(RegisterMailActivity.this, LoginInitMemory.class) ;
							startActivity(intent) ;
							overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left) ;
							RegisterMailActivity.this.finish() ;
						}else {
							Toast.makeText(RegisterMailActivity.this, "注册失败"+arg0.getMessage(), Toast.LENGTH_SHORT).show() ;
						}
					}
				}) ;
				break;
			case 101:
				
				break ;

			default:
				break;
			}
		};
	} ;
	
}
