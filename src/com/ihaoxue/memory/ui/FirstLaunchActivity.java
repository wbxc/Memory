package com.ihaoxue.memory.ui;

import java.util.ArrayList;
import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.ihaoxue.memory.R;
import com.ihaoxue.memory.adapter.FragmentLaunchAdapter;

public class FirstLaunchActivity extends BaseActivity {

	private ViewPager mViewPager ;
	private List<Fragment> mList ;
	private FragmentLaunchAdapter mFragmentLaunchAdapter ;
	
	@Override
	protected void initDataDataVariable() {
		// TODO Auto-generated method stub
		mList = new ArrayList<Fragment>() ;
		mList.add(new FragmentFirst()) ;
		mList.add(new FragmentSecond()) ;
		mList.add(new FragmentThree()) ;
	}

	@Override
	protected void setMainContentView() {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_first_launch_ui) ;
	}

	@Override
	protected void initComponentView() {
		// TODO Auto-generated method stub
		mViewPager = (ViewPager)findViewById(R.id.viewpager) ;
		mFragmentLaunchAdapter = new FragmentLaunchAdapter(getSupportFragmentManager()) ;
		mFragmentLaunchAdapter.setCoutext(FirstLaunchActivity.this) ;
		mFragmentLaunchAdapter.setmFragments(mList) ;
		mViewPager.setAdapter(mFragmentLaunchAdapter) ;
	}

	@Override
	protected void initEvent() {
		// TODO Auto-generated method stub
	}
	@Override
	protected void initNetDataRequest() {
		// TODO Auto-generated method stub
	}
}
