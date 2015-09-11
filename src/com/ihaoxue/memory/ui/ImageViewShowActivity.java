package com.ihaoxue.memory.ui;

import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.view.WindowManager;

import com.ihaoxue.image.image.cache.ImageCacheManger;
import com.ihaoxue.image.image.cache.ImageCacheManger.BitmapImageEvent;
import com.ihaoxue.memory.MemoryApplication;
import com.ihaoxue.memory.R;
import com.ihaoxue.memory.net.ConfigURL;
import com.ihaoxue.memory.widget.ZoomImageView;
import com.ihaoxue.memory.widget.ZoomImageView.ImageViewEvent;

public class ImageViewShowActivity extends BaseActivity {

	private WindowManager mWindowManager;
	private int displayWidth;
	private int displayHeight;
	private Matrix mMatrix = new Matrix();
	private ZoomImageView mZoomImageView;

	@Override
	protected void initDataDataVariable() {
		mWindowManager = this.getWindowManager();
	}

	@Override
	protected void setMainContentView() {
		// TODO Auto-generated method stub
		// setContentView(R.layout.activity_image_show);
		setContentView(R.layout.test);
		MemoryApplication.getInstance().addActivity(ImageViewShowActivity.this) ;
	}

	@Override
	protected void initComponentView() {
		mZoomImageView = (ZoomImageView) findViewById(R.id.zoom_image);
		mZoomImageView.setImageViewEvent(new ImageViewEvent() {

			@Override
			public void onImageViewListerner() {
				// TODO Auto-generated method stub
				ImageViewShowActivity.this.finish();
				overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right) ;
			}
		});
	}

	@Override
	protected void initEvent() {
		ImageCacheManger.loadImage2(
				ConfigURL.DEMO_TEST_IMAGE,
				mBitmapImageEvent,
				getBitmapFromResources(ImageViewShowActivity.this,
						R.drawable.ic_launcher),
				getBitmapFromResources(ImageViewShowActivity.this,
						R.drawable.ic_launcher));
	}

	@Override
	protected void initNetDataRequest() {
		// TODO Auto-generated method stub

	}

	public static Bitmap getBitmapFromResources(Activity act, int resId) {
		Resources res = act.getResources();
		return BitmapFactory.decodeResource(res, resId);
	}

	private BitmapImageEvent mBitmapImageEvent = new BitmapImageEvent() {

		@Override
		public void setBitmap(final Bitmap mBitmap) {
			// TODO Auto-generated method stub
			runOnUiThread(new Runnable() {
				public void run() {
					if (mBitmap != null) {
//						float heig = (float) (displayHeight - getStatusHeight(ImageViewShowActivity.this))
//								/ (float) mBitmap.getHeight();
//						float widthg = (float) displayWidth
//								/ (float) mBitmap.getWidth();
//
//						if (heig > widthg) {
//							mMatrix.setScale(widthg, widthg);
//							int wi = (int) ((displayWidth - (mBitmap.getWidth() * widthg)) / 2);
//							int hi = (int) ((displayHeight - (mBitmap
//									.getHeight() * widthg)) / 2);
//							mMatrix.postTranslate(wi, hi);
//						} else {
//							int wi = (int) ((displayWidth - (mBitmap.getWidth() * heig)) / 2);
//							int hi = (int) ((displayHeight - (mBitmap
//									.getHeight() * heig)) / 2);
//							mMatrix.setScale(heig, heig);
//							mMatrix.postTranslate(wi, hi);
//						}
						mZoomImageView.setImageBitmap(mBitmap);
					}else {
						mZoomImageView.setImageBitmap(getBitmap());
					}
				}
			});
		}
	};

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
	}

	public Bitmap getBitmap() {
		try {
			InputStream stream = getAssets().open("222.png");
			try {
				return BitmapFactory.decodeStream(stream);
			} finally {
				stream.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public int getStatusHeight(Activity activity) {
		int statusHeight = 0;
		Rect localRect = new Rect();
		activity.getWindow().getDecorView()
				.getWindowVisibleDisplayFrame(localRect);
		statusHeight = localRect.top;
		if (0 == statusHeight) {
			Class<?> localClass;
			try {
				localClass = Class.forName("com.android.internal.R$dimen");
				Object localObject = localClass.newInstance();
				int i5 = Integer.parseInt(localClass
						.getField("status_bar_height").get(localObject)
						.toString());
				statusHeight = activity.getResources()
						.getDimensionPixelSize(i5);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
			}
		}
		return statusHeight;
	}

}
