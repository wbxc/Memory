package com.ihaoxue.memory.widget;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.ihaoxue.memory.R;

public class DemoImageToast extends Toast {

	private static DemoImageToast instance = null;
	
	private ImageView mImageView ;
	public DemoImageToast(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		View mView = initView(context) ;
		this.setView(mView) ;
	}
	
	private View initView(Context mContext){
		View mView = LayoutInflater.from(mContext).inflate(R.layout.toast_message_image, null) ;
		mImageView = (ImageView) mView.findViewById(R.id.message_image) ;
		return mView ;
	}
	public static DemoImageToast getInstance(Context mContext){
		
		if (instance == null) {
			instance = new DemoImageToast(mContext) ;
		}
		return instance;
	}
	public void show(int rId){
		mImageView.setBackgroundResource(rId) ;
		instance.setDuration(Toast.LENGTH_SHORT) ;
		instance.setGravity(Gravity.CENTER, 0, 0) ;
		instance.show() ;
	}

}
