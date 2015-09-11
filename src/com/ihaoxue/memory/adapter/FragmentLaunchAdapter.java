package com.ihaoxue.memory.adapter;

import java.util.List;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class FragmentLaunchAdapter extends FragmentPagerAdapter {

	private Context mContext ;
	private List<Fragment> mFragments ;

	public FragmentLaunchAdapter(FragmentManager fm) {
		super(fm);
	}
	public void setmFragments(List<Fragment> mFragments){
		this.mFragments = mFragments ;
	}
	public void setCoutext(Context mContext){
		this.mContext = mContext ;
	}
	@Override
	public Fragment getItem(int position) {
		// TODO Auto-generated method stub
		return mFragments.get(position);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mFragments.size();
	}

}
