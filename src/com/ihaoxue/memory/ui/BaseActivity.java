package com.ihaoxue.memory.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Window;

public abstract class BaseActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE) ;
		initDataDataVariable() ;
		setMainContentView() ;
		initComponentView() ;
		initEvent() ;
		initNetDataRequest() ;
	}
	/**
	 * 变量初始化
	 */
	protected abstract void initDataDataVariable() ;
	/**
	 * 主UI界面加载
	 */
	protected abstract void setMainContentView() ;
	
	/**
	 * 主界面 控件加载
	 */
	protected abstract void initComponentView() ;
	
	/**
	 * 控件监听事件 初始化
	 */
	protected abstract void initEvent() ;
	
	/**
	 * 网络数据访问
	 */
	protected abstract void initNetDataRequest() ;
}
