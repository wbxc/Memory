package com.ihaoxue.memory.ui;

import java.util.HashMap;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.LogInCallback;
import com.ihaoxue.memory.MemoryApplication;
import com.ihaoxue.memory.R;
import com.ihaoxue.memory.utils.ShareTools;
import com.ihaoxue.memory.utils.StringUtil;
import com.ihaoxue.memory.widget.CustomMemoryDialog;
import com.ihaoxue.memory.widget.CustomMemoryDialog.CustomerInterface;
import com.ihaoxue.memory.widget.LoginLoadProgress;

public class LoginActivity extends BaseActivity implements OnClickListener {

	private Button loginButton;
	private TextView registerTextView;
	private TextView forgect_password;
	private CustomMemoryDialog mCustomMemoryDialog;
	private HashMap<String, String> mHashMap;
	private EditText userName;
	private EditText userPassword;
	private LoginLoadProgress mLoadProgress ;
	
	@Override
	protected void initDataDataVariable() {
		// TODO Auto-generated method stub
		mHashMap = ShareTools.getInstance().readUserInfo(LoginActivity.this);
		if (StringUtil.isNotStringEmpty(mHashMap.get(ShareTools.USER_ID))) {
			Intent intent = new Intent();
			intent.setClass(LoginActivity.this, MainHomeActivity.class);
			startActivity(intent);
			LoginActivity.this.finish();
			return;
		}
	}

	@Override
	protected void setMainContentView() {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_login_ui);
		mLoadProgress = LoginLoadProgress.createDialog(LoginActivity.this) ;
		MemoryApplication.getInstance().addActivity(LoginActivity.this);
	}

	@Override
	protected void initComponentView() {
		// TODO Auto-generated method stub
		loginButton = (Button) findViewById(R.id.login_button);
		registerTextView = (TextView) findViewById(R.id.register_user);
		forgect_password = (TextView) findViewById(R.id.forgect_password);
		userName = (EditText) findViewById(R.id.user_name);
		userPassword = (EditText) findViewById(R.id.user_password);
	}

	@Override
	protected void initEvent() {
		// TODO Auto-generated method stub
		loginButton.setOnClickListener(this);
		registerTextView.setOnClickListener(this);
		forgect_password.setOnClickListener(this);
		mCustomMemoryDialog = CustomMemoryDialog.getCustomMemoryDialog(LoginActivity.this, mCustomerInterface);
		mCustomMemoryDialog.setMessage("否", "你要退出记忆宝？", "要跳转");
	}

	@Override
	protected void initNetDataRequest() {
		// TODO Auto-generated method stub
		try {
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private CustomerInterface mCustomerInterface = new CustomerInterface() {

		@Override
		public void loginGoButton() {
			// TODO Auto-generated method stub
			mCustomMemoryDialog.dissMissDialog();
			Intent intent = new Intent();
			intent.setClass(LoginActivity.this, RegisterActivity.class);
			startActivity(intent);
		
		}

		@Override
		public void dimissButton() {
			// TODO Auto-generated method stub
			mCustomMemoryDialog.dissMissDialog();
		}
	};

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.login_button:
			mLoadProgress.show() ;
			jumpInterface();
			break;
		case R.id.register_user:
			jumpLoginUI();
			break;
		case R.id.forgect_password:
			goToFoundPassword() ;
			break;
		default:
			break;
		}
	}
	
	private void goToFoundPassword(){
		Intent mIntent = new Intent() ;
		mIntent.setClass(LoginActivity.this, FoundPasswordActivity.class) ;
		startActivity(mIntent) ;
		overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left) ;
		
 	}

	private void jumpLoginUI() {
		Intent intent = new Intent();
		intent.setClass(LoginActivity.this, RegisterActivity.class);
		startActivity(intent);
		overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
	}

	private void jumpInterface() {

		if (StringUtil.isNotStringEmpty(userName.getText().toString().trim())
				&& StringUtil.isNotStringEmpty(userPassword.getText().toString().trim())) {
			AVUser.logInInBackground(userName.getText().toString().trim(), userPassword.getText().toString().trim(),
					new LogInCallback<AVUser>() {

						@Override
						public void done(AVUser arg0, AVException arg1) {
							// TODO Auto-generated method stub
							if (arg0 != null) {
								mLoadProgress.dismiss() ;
								ShareTools.getInstance().saveUserInfo(LoginActivity.this, arg0.getUsername(), "",
										arg0.getString("userId"));
								if (StringUtil.isNotStringEmpty(arg0.getString("selectSubjectName"))
										&& StringUtil.isNotStringEmpty(arg0.getString("selectSubjectId"))) {
									ShareTools.getInstance().saveInitMemory(LoginActivity.this,
											arg0.getString("selectSubjectId"), arg0.getString("selectSubjectName"));
									Intent intent = new Intent();
									intent.setClass(LoginActivity.this, MainHomeActivity.class);
									startActivity(intent);
									overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
									LoginActivity.this.finish();
								} else {
									mLoadProgress.dismiss() ;
									Intent intent = new Intent();
									intent.setClass(LoginActivity.this, LoginInitMemory.class);
									startActivity(intent);
									overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
									LoginActivity.this.finish();
								}
							} else {
								mLoadProgress.dismiss() ;
								Toast.makeText(LoginActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
							}
						}
					});
		} else {
			mLoadProgress.dismiss() ;
			Toast.makeText(LoginActivity.this, "用户名或密码不能为空", Toast.LENGTH_SHORT).show();
		}
	}
}
