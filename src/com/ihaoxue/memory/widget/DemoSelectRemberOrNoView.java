package com.ihaoxue.memory.widget;

import com.ihaoxue.memory.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

public class DemoSelectRemberOrNoView extends RelativeLayout {

	private DemoRemberOrNoRelative mDemoSelectRelative ;
	private static DemoSelectRemberOrNoView instance ;
	private LayoutInflater mLayoutInflater ;
	private Button next_question ;
	private  Button remmber_again_button ;
	public DemoSelectRemberOrNoView(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}
	public DemoSelectRemberOrNoView(Context context, AttributeSet attrs ) {
		super(context, attrs);
	}
	public DemoSelectRemberOrNoView(Context context ,DemoRemberOrNoRelative mDemoSelectRelative) {
		super(context);
		this.mDemoSelectRelative = mDemoSelectRelative ;
		mLayoutInflater = LayoutInflater.from(context) ;
		LayoutParams mLayoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT) ;
		View view = initView() ;
		this.addView(view,mLayoutParams) ;
	}
	
	public static DemoSelectRemberOrNoView getInstance(Context mContext ,DemoRemberOrNoRelative mDemoSelectRelative){
		instance = new DemoSelectRemberOrNoView(mContext,mDemoSelectRelative) ;
		return instance ;
	}
	private View initView(){
		View mView = mLayoutInflater.inflate(R.layout.bootom_view_pager_two, null) ;
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
				mDemoSelectRelative.remmber() ;
				break;
			case R.id.remmber_again_button :
				mDemoSelectRelative.unRemmber() ;
				break ;
			}
		}
	};
	
	
	public interface DemoRemberOrNoRelative{
		
		public void remmber() ;
		public void unRemmber() ;
	}
}
