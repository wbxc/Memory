package com.ihaoxue.memory.ui;

import java.util.HashMap;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.avos.avoscloud.AVUser;
import com.ihaoxue.memory.MemoryApplication;
import com.ihaoxue.memory.MemoryGlobeManger;
import com.ihaoxue.memory.R;
import com.ihaoxue.memory.service.UpdateManager;
import com.ihaoxue.memory.service.UpdateService;
import com.ihaoxue.memory.utils.ShareTools;
import com.ihaoxue.memory.utils.StringUtil;
import com.ihaoxue.memory.widget.CustomMemoryDialog;
import com.ihaoxue.memory.widget.CustomMemoryDialog.CustomerInterface;
import com.ihaoxue.memory.widget.DemoToast;

public class SettingActivity extends BaseActivity implements OnClickListener {

	private ImageView back;
	private TextView commTopTextView;

	private RelativeLayout kaoshileixing_re;
	private TextView username_info; // 账号信息
	private RelativeLayout modyPassword_re;
	private RelativeLayout yijian_re; // 意见反馈
	private RelativeLayout banben_re; // 版本检测
	private RelativeLayout about_re; // 关于
	private RelativeLayout bootom_re; // 底部
	private HashMap<String, String> mHashMap ;
	private CustomMemoryDialog mCustomMemoryDialog ;
	private Button login_out ;
	
	@Override
	protected void initDataDataVariable() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void setMainContentView() {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_setting_ui);
		mCustomMemoryDialog = CustomMemoryDialog.getCustomMemoryDialog(SettingActivity.this, mCustomerInterface) ;
		mCustomMemoryDialog.setMessage("否", "你确定要退出？", "是") ;
		MemoryApplication.getInstance().addActivity(SettingActivity.this);
	}
	
	private CustomerInterface mCustomerInterface = new CustomerInterface() {
		
		@Override
		public void loginGoButton() {
			// TODO Auto-generated method stub
			loginOut() ;
			runOnUiThread(new Runnable() {
				
				@Override
				public void run() {
					Intent intent = new Intent() ;
					intent.setClass(SettingActivity.this, LoginActivity.class) ;
					startActivity(intent) ;
					overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left) ;
					SettingActivity.this.finish() ;
				}
			}) ;
			mCustomMemoryDialog.dismiss() ;
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
		back = (ImageView) findViewById(R.id.back);
		commTopTextView = (TextView) findViewById(R.id.common_text);
		kaoshileixing_re = (RelativeLayout)findViewById(R.id.kaoshileixing_re) ;
		modyPassword_re = (RelativeLayout)findViewById(R.id.password_re) ;
		yijian_re = (RelativeLayout)findViewById(R.id.yijian_re) ;
		about_re = (RelativeLayout)findViewById(R.id.about_re) ;
		username_info = (TextView)findViewById(R.id.username_info) ;
		bootom_re = (RelativeLayout)findViewById(R.id.bootom_re) ;
		login_out = (Button)findViewById(R.id.login_out) ;
		banben_re = (RelativeLayout)findViewById(R.id.banben_re) ;
	}

	@Override
	protected void initEvent() {
		// TODO Auto-generated method stub
		commTopTextView.setText("设置");
		back.setOnClickListener(this) ;
		kaoshileixing_re.setOnClickListener(this) ;
		modyPassword_re.setOnClickListener(this) ;
		yijian_re.setOnClickListener(this) ;
		about_re.setOnClickListener(this) ;
		mHashMap = ShareTools.getInstance().readUserInfo(SettingActivity.this) ;
		username_info.setText(mHashMap.get(ShareTools.USER_NAME)) ;
		bootom_re.setOnClickListener(this) ;
		login_out.setOnClickListener(this) ;
		banben_re.setOnClickListener(this) ;
		if (isLoginAlready()) {
			bootom_re.setVisibility(View.VISIBLE) ;
		}else {
			bootom_re.setVisibility(View.INVISIBLE) ;
		}
	}

	@Override
	protected void initNetDataRequest() {
		// TODO Auto-generated method stub

	}

	
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.back:
			SettingActivity.this.finish();
			overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
			break;
		case R.id.kaoshileixing_re:
			Intent intent = new Intent() ;
			if (isLoginAlready()) {
				intent.setClass(SettingActivity.this, LoginInitMemory.class) ;
			}else {
				intent.setClass(SettingActivity.this, LoginActivity.class) ;
			}
			startActivity(intent) ;
			overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
			SettingActivity.this.finish() ;
			break ;
		case R.id.password_re:
			Intent intentModefyPassword = new Intent() ;
			if (!isLoginAlready()) {
				intentModefyPassword.setClass(SettingActivity.this, LoginActivity.class) ;
			}else {
				intentModefyPassword.setClass(SettingActivity.this, ModefyPasswordActivity.class) ;
			}
			startActivity(intentModefyPassword) ;
			overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left) ;
			break ;
		case R.id.yijian_re:
			Intent intentFeedBack = new Intent() ;
			if (!isLoginAlready()) {
				intentFeedBack.setClass(SettingActivity.this, LoginActivity.class) ;
			}else {
				intentFeedBack.setClass(SettingActivity.this, FeekBackActivity.class) ;
			}
			startActivity(intentFeedBack) ;
			overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left) ;
			break ;
		case R.id.about_re:
			Intent intentAbout = new Intent() ;
			intentAbout.setClass(SettingActivity.this, MemoryAboutActivity.class) ;
			startActivity(intentAbout) ;
			overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left) ;
			break ;
		case R.id.bootom_re:
			mCustomMemoryDialog.show() ;
			break ;
		case R.id.login_out:
			mCustomMemoryDialog.show() ;
			break ;
		case R.id.banben_re:
			UpdateManager.getUpdateManager().checkAppUpdate(SettingActivity.this, true);
			break ;
		default:
			break;
		}
	}
	
	
	
	private void loginOut(){
		ShareTools.getInstance().saveInitMemory(SettingActivity.this, "", "") ;
		ShareTools.getInstance().saveUserInfo(SettingActivity.this, "", "", "") ;
		ShareTools.getInstance().setInitSelectClass(SettingActivity.this, false, "", "") ;
		AVUser.logOut() ;
	}

	public boolean isLoginAlready(){
		if (StringUtil.isNotStringEmpty(ShareTools.getInstance().readUserInfo(SettingActivity.this).get(ShareTools.USER_NAME))
				&&StringUtil.isNotStringEmpty(ShareTools.getInstance().readUserInfo(SettingActivity.this).get(ShareTools.USER_ID))) {
			return true ;
		}
		return false ;
	}
	
	
	
	/**
	 * 检查更新版本
	 */
	public void checkVersion() {

		if (MemoryGlobeManger.localVersion < MemoryGlobeManger.serverVersion) {
			// 发现新版本，提示用户更新
			AlertDialog.Builder alert = new AlertDialog.Builder(this);
			alert.setTitle("软件升级").setMessage("发现新版本,建议立即更新使用.")
					.setPositiveButton("更新", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							// 开启更新服务UpdateService
							// 这里为了把update更好模块化，可以传一些updateService依赖的值
							// 如布局ID，资源ID，动态获取的标题,这里以app_name为例
							Intent updateIntent = new Intent(SettingActivity.this, UpdateService.class);
							updateIntent.putExtra("titleId", R.string.app_name);
							startService(updateIntent);
						}
					}).setNegativeButton("取消", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							dialog.dismiss();
						}
					});
			alert.create().show();
		} else {
			// 清理工作，略去
			// cheanUpdateFile()
		}
	}

}
