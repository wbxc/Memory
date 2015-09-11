package com.ihaoxue.memory.ui;

import com.ihaoxue.memory.R;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class FragmentThree extends Fragment {

	private Button enterApp ;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_three, null) ;
		enterApp = (Button)view.findViewById(R.id.enter_app) ;
		enterApp.setOnClickListener(mOnClickListener) ;
		return view ;
	}
	
	
	
	private OnClickListener mOnClickListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent = new Intent() ;
			intent.setClass(getActivity(), MainHomeActivity.class) ;
			getActivity().startActivity(intent) ;
			getActivity().finish() ;
		}
	};
}
