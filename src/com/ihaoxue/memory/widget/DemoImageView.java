package com.ihaoxue.memory.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector.OnDoubleTapListener;
import android.view.MotionEvent;
import android.widget.ImageView;

import com.ihaoxue.memory.inter.ImageViewDoubleEvent;

public class DemoImageView extends ImageView implements OnDoubleTapListener{

	private ImageViewDoubleEvent mImageViewDoubleEvent ;
	
	
	public DemoImageView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}
	public DemoImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	public DemoImageView(Context context) {
		super(context);
	}
	
	public void setDoubleEvent(ImageViewDoubleEvent mImageViewDoubleEvent){
		this.mImageViewDoubleEvent = mImageViewDoubleEvent ;
	}

	@Override
	public boolean onSingleTapConfirmed(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onDoubleTap(MotionEvent e) {
		// TODO Auto-generated method stub
		mImageViewDoubleEvent.enlargePicture() ;
		return true;
	}

	@Override
	public boolean onDoubleTapEvent(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		return this.onTouchEvent(event) ;
	}
}
