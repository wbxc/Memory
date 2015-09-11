package com.ihaoxue.memory.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

import com.ihaoxue.memory.R;

public class DemoProgressView extends View {

	Paint mPaintRect;
	Paint mPaintRectF;
	Paint mPaintRectR;
	Paint mPaintRectCenter;

	private Rect mRect;
	private RectF mRectF;
	private Rect mRectR;
	private Rect mRectCenter;

	private int count;
	private int interval;
	private int sumInterval1;
	private int unrightInterval;
	private int sumCount;
	private int progressHight;

	private int textColor;

	private int sumInterval ;
	private int  left_ju ;
	private int  right_ju ;
	
	public DemoProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
	}

	public DemoProgressView(Context context, AttributeSet attrs) {
		super(context, attrs);
		WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		Display display = manager.getDefaultDisplay();
		sumInterval1 = display.getWidth();
		mPaintRect = new Paint(Paint.ANTI_ALIAS_FLAG);
		mPaintRectF = new Paint(Paint.ANTI_ALIAS_FLAG);
		mPaintRectR = new Paint(Paint.ANTI_ALIAS_FLAG);
		mPaintRectCenter = new Paint(Paint.ANTI_ALIAS_FLAG);

		mRect = new Rect();
		mRectF = new RectF();
		mRectR = new Rect();
		mRectCenter = new Rect();
		count = 0;
		unrightInterval = 0;
		sumCount = 0;
		// TypedArray是一个用来存放由context.obtainStyledAttributes获得的属性的数组
		// 在使用完成后，一定要调用recycle方法
		// 属性的名称是styleable中的名称+“_”+属性名称
		TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.MyView);
		textColor = array.getColor(R.styleable.MyView_textColor, 0XFF00FF00); // 提供默认值，放置未指定
		float textSize = array.getDimension(R.styleable.MyView_textSize, 36);
		progressHight = (int) (array.getDimension(R.styleable.MyView_demoprogresshight, 30));
		left_ju = (int)(array.getDimension(R.styleable.MyView_progress_left, 0));
		//progress_left
		right_ju = (int)(array.getDimension(R.styleable.MyView_progress_right, 0)) ;
		sumInterval = sumInterval1 - right_ju ;
		interval = (sumInterval -left_ju) /10 ;
		
		mPaintRect.setTextSize(textSize);
		mPaintRectCenter.setTextSize(textSize);
		mPaintRectR.setTextSize(textSize);
		array.recycle(); // 一定要调用，否则这次的设定会对下次的使用造成影响
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);

		mPaintRectF.setStyle(Style.FILL);
		mRectF.left = left_ju;
		mRectF.right = sumInterval;
		mRectF.top = 0;
		mRectF.bottom = progressHight;
		mPaintRectF.setColor(Color.YELLOW);
		canvas.drawRect(mRectF, mPaintRectF);

		// 中间
		mPaintRectCenter.setStyle(Style.FILL);
		mPaintRectCenter.setColor(Color.WHITE);
		canvas.drawRect(count * interval+left_ju, 0, sumInterval - unrightInterval * interval, progressHight, mPaintRectCenter);
		mPaintRectCenter.setColor(textColor);
		if (sumCount > 0) {
			String tempString = String.valueOf(sumCount);
			mPaintRectCenter.getTextBounds(tempString, 0, tempString.length(), mRectCenter);
			int centerY = (int) (mRectF.centerY() - mRectCenter.centerY());
			int centerX = (int) (sumInterval - unrightInterval * interval - 2 * mRectCenter.centerX());
			canvas.drawText(tempString, centerX, centerY, mPaintRectCenter);
		}

		// 左边
		mPaintRect.setStyle(Style.FILL); // 设置填充
		mPaintRect.setColor(Color.GREEN);
		canvas.drawRect(left_ju, 0, count * interval+left_ju, progressHight, mPaintRect); // 绘制矩形
		mPaintRect.setColor(textColor);
		if (count > 0) {
			String tempCount = String.valueOf(count);
			mPaintRect.getTextBounds(tempCount, 0, tempCount.length(), mRect);
			int leftX = (count * interval+left_ju) - 2 * mRect.centerX();
			int leftY = (int) (mRectF.centerY() - mRect.centerY());
			canvas.drawText(tempCount, leftX, leftY, mPaintRect);
		}

		// 右边
		mPaintRectR.setStyle(Style.FILL);
		mPaintRectR.setColor(Color.RED);
		canvas.drawRect(sumInterval - unrightInterval * interval, 0, sumInterval, progressHight, mPaintRectR); // 绘制矩形
		mPaintRectR.setColor(textColor);
		if (unrightInterval > 0) {
			String tempRString = String.valueOf(unrightInterval);
			mPaintRectR.getTextBounds(tempRString, 0, tempRString.length(), mRectR);
			int rightY = (int) (mRectF.centerY() - mRectR.centerY());
			int rightX = (int) (mRectF.right - 2 * mRectR.centerX());
			canvas.drawText(tempRString, rightX, rightY, mPaintRectR);
		}

	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		// TODO Auto-generated method stub
		super.onSizeChanged(w, h, oldw, oldh);
	}

	public void initRight() {
		sumCount--;
		count++;
		invalidate();
	}

	public void initUnRight() {
		sumCount--;
		unrightInterval++;
		invalidate();
	}

	
	public int getSumCount() {
		return sumCount;
	}

	
	public int getCount() {
		return count;
		
		
	}

	public int getUnrightInterval() {
		return unrightInterval;
	}
	
	
	
	
	
	public void setCount(int count ) {
		this.count = count;
		invalidate();
	}

	public void setCount2(int count ,int sumCount) {
		this.count = count;
		this.sumCount = sumCount;
		invalidate();
	}

	public void setUnrightInterval(int unrightInterval,int sumCount) {
		this.unrightInterval = unrightInterval;
		this.sumCount = sumCount;
		invalidate() ;
	}

	public void setSumCount(int sumCount) {
		this.sumCount = sumCount;
		invalidate() ;
	}
	
	public void setErrorOrRight(int count,int sumCount,int unrightInterval){
		this.count = count;
		this.sumCount = sumCount;
		this.unrightInterval = unrightInterval;
		invalidate() ;
	}
	
}
