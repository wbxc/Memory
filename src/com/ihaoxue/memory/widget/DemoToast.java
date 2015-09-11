package com.ihaoxue.memory.widget;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ihaoxue.memory.R;

public class DemoToast extends Toast {

	private static DemoToast instance =null;
	private ImageView mImageView ;
	private TextView messageTextView ;
	
	public DemoToast(Context context) {
		super(context);
		View view = initView(context) ;
		this.setView(view) ;
	}
	
	public static DemoToast getInstance(Context mContext) {
		if (instance == null) {
			instance = new DemoToast(mContext) ;
		}
		return instance ;
	}
	
	private View initView(Context mContext){
		View view = LayoutInflater.from(mContext).inflate(R.layout.toast_message_item, null) ;
		mImageView = (ImageView)view.findViewById(R.id.log) ;
		messageTextView = (TextView)view.findViewById(R.id.message) ;
		return view ;
	}
	
	public void show(String message , int rId){
		messageTextView.setText(message) ;
		if (rId==0) {
			mImageView.setVisibility(View.INVISIBLE) ;
		}else {
			mImageView.setVisibility(View.VISIBLE) ;
			mImageView.setImageResource(rId) ;
		}
		instance.setDuration(Toast.LENGTH_SHORT) ;
		instance.setGravity(Gravity.CENTER, 0, 0) ;
		instance.show() ;
	}
}
