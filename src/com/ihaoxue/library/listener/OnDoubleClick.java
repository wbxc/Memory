package com.ihaoxue.library.listener;

import android.view.MotionEvent;
import android.view.View;

public class OnDoubleClick implements View.OnTouchListener {

	private OnTouchEventListener mOnTouchEventListener ;
	
	int count = 0;
	long firClick = 0;
	long secClick = 0;

	public OnDoubleClick() {
		// TODO Auto-generated constructor stub
	}
	public OnDoubleClick(OnTouchEventListener mOnTouchEventListener) {
		this.mOnTouchEventListener = mOnTouchEventListener ;
	}
	
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		if (MotionEvent.ACTION_DOWN == event.getAction()) {
			count++;
			if (count == 1) {
				firClick = System.currentTimeMillis();
			} else if (count == 2) {
				secClick = System.currentTimeMillis();
				if (secClick - firClick < 1000) {
					// 双击事件
					mOnTouchEventListener.switchActivity() ;
				}
				count = 0;
				firClick = 0;
				secClick = 0;
			}
		}
		return true;
	}

	public interface OnTouchEventListener{
		
		public void switchActivity() ;
	}
	
}