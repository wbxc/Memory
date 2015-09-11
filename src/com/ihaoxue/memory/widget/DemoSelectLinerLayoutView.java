package com.ihaoxue.memory.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

import com.ihaoxue.memory.R;

public class DemoSelectLinerLayoutView extends RelativeLayout{

	private static DemoSelectLinerLayoutView instance = null; 
	
	private Context mContext ;
	
	private LayoutInflater mLayoutInflater ;
	private DemoSelectEvent mDemoSelectEvent ;
	private Button remmber_all_knowleage ;
	private Button kaodian1 ;
	private Button kaodian2 ;
	private Button kaodian3 ;
	private Button kaodian4 ;
	private Button kaodian5 ;
	private Button kaodian6 ;
	private Button kaodian7 ;
	private Button kaodian8 ;
	
	
	
	public DemoSelectLinerLayoutView(Context context, AttributeSet attrs,int select) {
		super(context, attrs);
	}
	public DemoSelectLinerLayoutView(Context context,int select,DemoSelectEvent mdeDemoSelectEvent ) {
		// TODO Auto-generated constructor stub
		super(context) ;
		this.mContext = context ;
		this.mDemoSelectEvent = mdeDemoSelectEvent ;
		mLayoutInflater = LayoutInflater.from(context) ;
		LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT) ;
		View view =  initView(select) ;
		this.addView(view,layoutParams) ;
	}
	
	public static DemoSelectLinerLayoutView getInstance(Context context , int selectLayout,DemoSelectEvent mdeDemoSelectEvent){
		instance = new DemoSelectLinerLayoutView(context, selectLayout,mdeDemoSelectEvent) ;
		return instance ;
	}
	
	
	
	
	
	
	
	
	public View initView(int selectView){
		
		View view = null ;
		switch (selectView) {
		case 1:
			view = mLayoutInflater.inflate(R.layout.bootom_item1, null) ;
			kaodian1 = (Button) view.findViewById(R.id.kaodian1) ;
			kaodian1.setOnTouchListener(mOnTouchListener) ;
			remmber_all_knowleage = (Button)view.findViewById(R.id.remmber_all_knowleage) ;
			remmber_all_knowleage.setOnClickListener(mOnClickListener) ;
			break;
		case 2:
			view = mLayoutInflater.inflate(R.layout.bootom_item2, null) ;
			kaodian1 = (Button)view.findViewById(R.id.kaodian1) ;
			kaodian1.setOnTouchListener(mOnTouchListener) ;
			kaodian2 = (Button)view.findViewById(R.id.kaodian2) ;
			kaodian2.setOnTouchListener(mOnTouchListener) ;
			remmber_all_knowleage = (Button)view.findViewById(R.id.remmber_all_knowleage) ;
			remmber_all_knowleage.setOnClickListener(mOnClickListener) ;
			break ;
		case 3:
			view = mLayoutInflater.inflate(R.layout.bootom_item3, null) ;
			kaodian1 = (Button)view.findViewById(R.id.kaodian1) ;
			kaodian1.setOnTouchListener(mOnTouchListener) ;
			kaodian2 = (Button)view.findViewById(R.id.kaodian2) ;
			kaodian2.setOnTouchListener(mOnTouchListener) ;
			kaodian3 = (Button)view.findViewById(R.id.kaodian3) ;
			kaodian3.setOnTouchListener(mOnTouchListener) ;
			remmber_all_knowleage = (Button)view.findViewById(R.id.remmber_all_knowleage) ;
			remmber_all_knowleage.setOnClickListener(mOnClickListener) ;
			break ;
		case 4:
			view = mLayoutInflater.inflate(R.layout.bootom_item4, null) ;
			kaodian1 = (Button)view.findViewById(R.id.kaodian1) ;
			kaodian1.setOnTouchListener(mOnTouchListener) ;
			kaodian2 = (Button)view.findViewById(R.id.kaodian2) ;
			kaodian2.setOnTouchListener(mOnTouchListener) ;
			kaodian3 = (Button)view.findViewById(R.id.kaodian3) ;
			kaodian3.setOnTouchListener(mOnTouchListener) ;
			kaodian4 = (Button)view.findViewById(R.id.kaodian4) ;
			kaodian4.setOnTouchListener(mOnTouchListener) ;
			remmber_all_knowleage = (Button)view.findViewById(R.id.remmber_all_knowleage) ;
			remmber_all_knowleage.setOnClickListener(mOnClickListener) ;
			break ;
		case 5 :
			view = mLayoutInflater.inflate(R.layout.bootom_item5, null) ;
			kaodian1 = (Button)view.findViewById(R.id.kaodian1) ;
			kaodian1.setOnTouchListener(mOnTouchListener) ;
			kaodian2 = (Button)view.findViewById(R.id.kaodian2) ;
			kaodian2.setOnTouchListener(mOnTouchListener) ;
			kaodian3 = (Button)view.findViewById(R.id.kaodian3) ;
			kaodian3.setOnTouchListener(mOnTouchListener) ;
			kaodian4 = (Button)view.findViewById(R.id.kaodian4) ;
			kaodian4.setOnTouchListener(mOnTouchListener) ;
			kaodian5 = (Button)view.findViewById(R.id.kaodian5) ;
			kaodian5.setOnTouchListener(mOnTouchListener) ;
			remmber_all_knowleage = (Button)view.findViewById(R.id.remmber_all_knowleage) ;
			remmber_all_knowleage.setOnClickListener(mOnClickListener) ;
			break ;
		case 6:
			view = mLayoutInflater.inflate(R.layout.bootom_item6, null) ;
			kaodian1 = (Button)view.findViewById(R.id.kaodian1) ;
			kaodian1.setOnTouchListener(mOnTouchListener) ;
			kaodian2 = (Button)view.findViewById(R.id.kaodian2) ;
			kaodian2.setOnTouchListener(mOnTouchListener) ;
			kaodian3 = (Button)view.findViewById(R.id.kaodian3) ;
			kaodian3.setOnTouchListener(mOnTouchListener) ;
			kaodian4 = (Button)view.findViewById(R.id.kaodian4) ;
			kaodian4.setOnTouchListener(mOnTouchListener) ;
			kaodian5 = (Button)view.findViewById(R.id.kaodian5) ;
			kaodian5.setOnTouchListener(mOnTouchListener) ;
			kaodian6 = (Button)view.findViewById(R.id.kaodian6) ;
			kaodian6.setOnTouchListener(mOnTouchListener) ;
			remmber_all_knowleage = (Button)view.findViewById(R.id.remmber_all_knowleage) ;
			remmber_all_knowleage.setOnClickListener(mOnClickListener) ;
			break ;
		case 7:
			view = mLayoutInflater.inflate(R.layout.bootom_item7, null) ;
			kaodian1 = (Button)view.findViewById(R.id.kaodian1) ;
			kaodian1.setOnTouchListener(mOnTouchListener) ;
			kaodian2 = (Button)view.findViewById(R.id.kaodian2) ;
			kaodian2.setOnTouchListener(mOnTouchListener) ;
			kaodian3 = (Button)view.findViewById(R.id.kaodian3) ;
			kaodian3.setOnTouchListener(mOnTouchListener) ;
			kaodian4 = (Button)view.findViewById(R.id.kaodian4) ;
			kaodian4.setOnTouchListener(mOnTouchListener) ;
			kaodian5 = (Button)view.findViewById(R.id.kaodian5) ;
			kaodian5.setOnTouchListener(mOnTouchListener) ;
			kaodian6 = (Button)view.findViewById(R.id.kaodian6) ;
			kaodian6.setOnTouchListener(mOnTouchListener) ;
			kaodian7 = (Button)view.findViewById(R.id.kaodian7) ;
			kaodian7.setOnTouchListener(mOnTouchListener) ;
			remmber_all_knowleage = (Button)view.findViewById(R.id.remmber_all_knowleage) ;
			remmber_all_knowleage.setOnClickListener(mOnClickListener) ;
			break ;
		case 8:
			view = mLayoutInflater.inflate(R.layout.bootom_item8, null) ;
			kaodian1 = (Button)view.findViewById(R.id.kaodian1) ;
			kaodian1.setOnTouchListener(mOnTouchListener) ;
			kaodian2 = (Button)view.findViewById(R.id.kaodian2) ;
			kaodian2.setOnTouchListener(mOnTouchListener) ;
			kaodian3 = (Button)view.findViewById(R.id.kaodian3) ;
			kaodian3.setOnTouchListener(mOnTouchListener) ;
			kaodian4 = (Button)view.findViewById(R.id.kaodian4) ;
			kaodian4.setOnTouchListener(mOnTouchListener) ;
			kaodian5 = (Button)view.findViewById(R.id.kaodian5) ;
			kaodian5.setOnTouchListener(mOnTouchListener) ;
			kaodian6 = (Button)view.findViewById(R.id.kaodian6) ;
			kaodian6.setOnTouchListener(mOnTouchListener) ;
			kaodian7 = (Button)view.findViewById(R.id.kaodian7) ;
			kaodian7.setOnTouchListener(mOnTouchListener) ;
			kaodian8 = (Button)view.findViewById(R.id.kaodian8) ;
			kaodian8.setOnTouchListener(mOnTouchListener) ;
			remmber_all_knowleage = (Button)view.findViewById(R.id.remmber_all_knowleage) ;
			remmber_all_knowleage.setOnClickListener(mOnClickListener) ;
			break ;
		}
		return view ;
	}
	
	public interface DemoSelectEvent{
		
		public void selectButton(int select) ;
		public void reloadButton() ;
		public void remmberAllKnowleage() ;
	}

	private OnClickListener mOnClickListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			mDemoSelectEvent.remmberAllKnowleage() ;
		}
	};
	private OnTouchListener mOnTouchListener = new OnTouchListener() {
		
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.kaodian1:
				if (event.getAction()==MotionEvent.ACTION_UP) {
					mDemoSelectEvent.reloadButton() ;
					kaodian1.setBackground(mContext.getResources().getDrawable(R.drawable.corner_bg_item_button)) ;
				}else if (event.getAction() == MotionEvent.ACTION_DOWN) {
					kaodian1.setBackground(mContext.getResources().getDrawable(R.drawable.corner_bg_item_button_two)) ;
					mDemoSelectEvent.selectButton(0) ;
				} 
				break;
			case R.id.kaodian2:
				if (event.getAction()==MotionEvent.ACTION_UP) {
					mDemoSelectEvent.reloadButton() ;
					kaodian2.setBackground(mContext.getResources().getDrawable(R.drawable.corner_bg_item_button)) ;
				}else if (event.getAction() == MotionEvent.ACTION_DOWN) {
					kaodian2.setBackground(mContext.getResources().getDrawable(R.drawable.corner_bg_item_button_two)) ;
					mDemoSelectEvent.selectButton(1) ;
				} 
				break ;
			case R.id.kaodian3:
				if (event.getAction()==MotionEvent.ACTION_UP) {
					mDemoSelectEvent.reloadButton() ;
					kaodian3.setBackground(mContext.getResources().getDrawable(R.drawable.corner_bg_item_button)) ;
				}else if (event.getAction() == MotionEvent.ACTION_DOWN) {
					kaodian3.setBackground(mContext.getResources().getDrawable(R.drawable.corner_bg_item_button_two)) ;
					mDemoSelectEvent.selectButton(2) ;
				} 
				break ;
			case R.id.kaodian4:
				if (event.getAction()==MotionEvent.ACTION_UP) {
					mDemoSelectEvent.reloadButton() ;
					kaodian4.setBackground(mContext.getResources().getDrawable(R.drawable.corner_bg_item_button)) ;
				}else if (event.getAction() == MotionEvent.ACTION_DOWN) {
					kaodian4.setBackground(mContext.getResources().getDrawable(R.drawable.corner_bg_item_button_two)) ;
					mDemoSelectEvent.selectButton(3) ;
				} 
				break ;
			case R.id.kaodian5:
				if (event.getAction()==MotionEvent.ACTION_UP) {
					mDemoSelectEvent.reloadButton() ;
					kaodian5.setBackground(mContext.getResources().getDrawable(R.drawable.corner_bg_item_button)) ;
				}else if (event.getAction() == MotionEvent.ACTION_DOWN) {
					kaodian5.setBackground(mContext.getResources().getDrawable(R.drawable.corner_bg_item_button_two)) ;
					mDemoSelectEvent.selectButton(4) ;
				} 
				break ;
			case R.id.kaodian6:
				if (event.getAction()==MotionEvent.ACTION_UP) {
					mDemoSelectEvent.reloadButton() ;
					kaodian6.setBackground(mContext.getResources().getDrawable(R.drawable.corner_bg_item_button)) ;
				}else if (event.getAction() == MotionEvent.ACTION_DOWN) {
					kaodian6.setBackground(mContext.getResources().getDrawable(R.drawable.corner_bg_item_button_two)) ;
					mDemoSelectEvent.selectButton(5) ;
				} 
				break ;
			case R.id.kaodian7:
				if (event.getAction()==MotionEvent.ACTION_UP) {
					mDemoSelectEvent.reloadButton() ;
					kaodian7.setBackground(mContext.getResources().getDrawable(R.drawable.corner_bg_item_button)) ;
				}else if (event.getAction() == MotionEvent.ACTION_DOWN) {
					kaodian7.setBackground(mContext.getResources().getDrawable(R.drawable.corner_bg_item_button_two)) ;
					mDemoSelectEvent.selectButton(6) ;
				} 
				break ;
			case R.id.kaodian8:
				if (event.getAction()==MotionEvent.ACTION_UP) {
					mDemoSelectEvent.reloadButton() ;
					kaodian8.setBackground(mContext.getResources().getDrawable(R.drawable.corner_bg_item_button)) ;
				}else if (event.getAction() == MotionEvent.ACTION_DOWN) {
					kaodian8.setBackground(mContext.getResources().getDrawable(R.drawable.corner_bg_item_button_two)) ;
					mDemoSelectEvent.selectButton(7) ;
				} 
				break ;
			}
			return true;
		}
	};
}
