package com.ihaoxue.memory.widget;

import com.ihaoxue.memory.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

public class DemoSelectRelativeView extends RelativeLayout {

	private DemoSelectRelative mDemoSelectRelative ;
	private static DemoSelectRelativeView instance ;
	private LayoutInflater mLayoutInflater ;
	private Button next_question ;
	private  Button remmber_again_button ;
	public DemoSelectRelativeView(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}
	public DemoSelectRelativeView(Context context, AttributeSet attrs ) {
		super(context, attrs);
	}
	public DemoSelectRelativeView(Context context ,DemoSelectRelative mDemoSelectRelative) {
		super(context);
		this.mDemoSelectRelative = mDemoSelectRelative ;
		mLayoutInflater = LayoutInflater.from(context) ;
		View view = initView() ;
		LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT) ;
		this.addView(view,layoutParams) ;
	}
	
	public static DemoSelectRelativeView getInstance(Context mContext ,DemoSelectRelative mDemoSelectRelative){
		instance = new DemoSelectRelativeView(mContext,mDemoSelectRelative) ;
		return instance ;
	}
	private View initView(){
		View mView = mLayoutInflater.inflate(R.layout.bootom_view_pager, null) ;
		next_question = (Button)mView.findViewById(R.id.next_question) ;
		next_question.setOnClickListener(mOnClickListener) ;
		remmber_again_button = (Button)mView.findViewById(R.id.remmber_again_button) ;
		remmber_again_button.setOnClickListener(mOnClickListener) ;
		return mView ;
	}
	
	private OnClickListener mOnClickListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.next_question:
				mDemoSelectRelative.selectNextPager() ;
				break;
			case R.id.remmber_again_button :
				mDemoSelectRelative.selectAgainRemmber() ;
				break ;
			}
		}
	};
	
	
	public interface DemoSelectRelative{
		public void selectNextPager() ;
		public void selectAgainRemmber() ;
	}
}
