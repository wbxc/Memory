package com.ihaoxue.memory.adapter;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import android.R.integer;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.ihaoxue.image.image.cache.ImageCacheManger;
import com.ihaoxue.image.image.cache.ImageCacheManger.BitmapImageEvent;
import com.ihaoxue.library.listener.OnDoubleClick;
import com.ihaoxue.library.listener.OnDoubleClick.OnTouchEventListener;
import com.ihaoxue.memory.MemoryGlobeManger;
import com.ihaoxue.memory.R;
import com.ihaoxue.memory.Utility;
import com.ihaoxue.memory.bean.QuestionModel;
import com.ihaoxue.memory.net.MemoryICallBack;
import com.ihaoxue.memory.net.MemoryNetTask;
import com.ihaoxue.memory.net.MemoryNetTaskManagement;
import com.ihaoxue.memory.ui.ExamNextMemoryActivity;
import com.ihaoxue.memory.ui.ExamNextMemoryActivity.MemoryViewHolder;
import com.ihaoxue.memory.widget.DemoSelectLinerLayoutView;
import com.ihaoxue.memory.widget.DemoSelectLinerLayoutView.DemoSelectEvent;
import com.ihaoxue.memory.widget.DemoSelectRelativeView;
import com.ihaoxue.memory.widget.DemoSelectRelativeView.DemoSelectRelative;
import com.ihaoxue.memory.widget.DemoSelectRemberOrNoView;
import com.ihaoxue.memory.widget.DemoSelectRemberOrNoView.DemoRemberOrNoRelative;
import com.ihaoxue.memory.widget.MemoryViewPager;

public class ExamNextPagerAdapter extends PagerAdapter {

	private Context mContext;
	private List<View> mWeViews;
	// private WebView mWebView ;
	private int[] questionIdArr;
	private int questionId;
	private boolean[] arr_loaded;
	private boolean questionFlag = false;
	public static final int EXAMPAGERADAPTER_SUCCESS = 1;
	public static final int EXAMPAGERADAPTER_FAIL = 2;
	public static final int EXAMPAGERADAOTER_NET_ERROR = 3;

	public ExamNextPagerAdapter(Context mContext, List<View> mWebViews) {
		// TODO Auto-generated constructor stub
		this.mContext = mContext;
		this.mWeViews = mWebViews;
		if (mWebViews != null) {
			arr_loaded = new boolean[mWebViews.size()];
			for (int i = 0; i < mWebViews.size(); i++) {
				arr_loaded[i] = false;
			}
		}
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		if (mWeViews != null && !mWeViews.isEmpty()) {
			return mWeViews.size();
		}
		return 0;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	@Override
	public Object instantiateItem(View container, int position) {
		// TODO Auto-generated method stub
		View childView = mWeViews.get(position);
		if (childView == null) {
			childView = ((ExamNextMemoryActivity) mContext).createItemView(position);
			mWeViews.set(position, childView);
		}
		((MemoryViewPager) container).addView(childView);
		childView.setTag(position);
		if (!arr_loaded[position]) {
			if (questionFlag) {
				if (questionArrFlag[position] == 0) {
					WebView mWebView = (WebView) childView.findViewById(R.id.webview_item_content);
					MemoryViewHolder memoryViewHolder = (MemoryViewHolder) mWebView.getTag();
					
					LinearLayout mLinearLayout = (LinearLayout) childView.findViewById(R.id.select_question);
					mLinearLayout.addView(DemoSelectLinerLayoutView.getInstance(mContext, memoryViewHolder.questionAnswerNumber,
							mdeDemoSelectEvent));
					RelativeLayout mRelativeLayout = (RelativeLayout) childView.findViewById(R.id.remmber_again_next);
					mRelativeLayout.addView(DemoSelectRelativeView.getInstance(mContext, mDemoSelectRelative));
					mWebView.loadUrl(MemoryGlobeManger.DEMO_URL_PATH);
				} else if (questionArrFlag[position] == 1) {
					ScrollView mScrollView = (ScrollView) childView.findViewById(R.id.scrollview_id);
					RelativeLayout mRelativeLayout = (RelativeLayout) childView.findViewById(R.id.remmber_again_next);
					mRelativeLayout.addView(DemoSelectRemberOrNoView.getInstance(mContext, mDemoRemberOrNoRelative));
					ImageView mImageView = (ImageView) childView.findViewById(R.id.image_item_content);
					mImageView.setOnTouchListener(new OnDoubleClick(mOnTouchEventListener));
				}
			} else {
				MemoryNetTaskManagement.getInstance(mContext).downloadOneQuestionAll(memoryICallBack, questionIdArr[position]);
			}
			arr_loaded[position] = true;
		}
		return childView;
	}

	private Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case ExamPagerAdapter.EXAMPAGERADAPTER_SUCCESS:
				if (msg.obj != null && msg.obj instanceof integer) {

				} else if (msg.obj != null && msg.obj instanceof QuestionModel) {
					showContent((QuestionModel) msg.obj);
				}
				break;
			case ExamPagerAdapter.EXAMPAGERADAPTER_FAIL:
				Toast.makeText(mContext, "出现错误:" + (String) msg.obj, Toast.LENGTH_SHORT).show();
				break;
			}
		};
	};

	private void showContent(QuestionModel oResult) {

		int questionId = Integer.parseInt(oResult.getQuestionId());

		for (int position = 0; position < questionIdArr.length; position++) {

			if (questionId == questionIdArr[position]) {

				if (mWeViews.get(position) != null) {
					if (questionArrFlag[position] == 0) {
						WebView mWebView = (WebView) mWeViews.get(position).findViewById(R.id.webview_item_content);
						MemoryViewHolder memoryViewHolder = (MemoryViewHolder) mWebView.getTag();
						if (memoryViewHolder != null)
							memoryViewHolder.questionContent = oResult.getQuestionContent();
						LinearLayout mLinearLayout = (LinearLayout) mWeViews.get(position).findViewById(R.id.select_question);
						RelativeLayout mRelativeLayout = (RelativeLayout) mWeViews.get(position).findViewById(
								R.id.remmber_again_next);
						if (mLinearLayout != null && mRelativeLayout != null) {
							mRelativeLayout.addView(DemoSelectRelativeView.getInstance(mContext, mDemoSelectRelative));
							mLinearLayout.addView(new DemoSelectLinerLayoutView(mContext, memoryViewHolder.questionAnswerNumber,
									mdeDemoSelectEvent));
						}
						mWebView.loadUrl(MemoryGlobeManger.DEMO_URL_PATH);
					} else if (questionArrFlag[position] == 1) {
						ScrollView mScrollView = (ScrollView) mWeViews.get(position).findViewById(R.id.scrollview_id);
						MemoryViewHolder memoryViewHolder = (MemoryViewHolder) mScrollView.getTag();
						memoryViewHolder.largeImageUrl = oResult.getQuestionLargeImageUrl();
						memoryViewHolder.smallImageUrl = oResult.getQuestionImageUrl();
						final ImageView imageView = (ImageView) mWeViews.get(position).findViewById(R.id.image_item_content);
						ImageCacheManger.loadImage2(memoryViewHolder.smallImageUrl, new BitmapImageEvent() {
							@Override
							public void setBitmap(Bitmap mBitmap) {
								imageView.setImageBitmap(mBitmap);
							}
						}, getBitmap(), getBitmap());
						imageView.setOnClickListener(mOnClickListener);
						RelativeLayout mRelativeLayout = (RelativeLayout) mWeViews.get(position).findViewById(
								R.id.remmber_again_next);
						mRelativeLayout.addView(DemoSelectRemberOrNoView.getInstance(mContext, mDemoRemberOrNoRelative));
					}
					arr_loaded[position] = false;
				}
				break;
			}
		}

	}

	private OnClickListener mOnClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if (Utility.isFastDoubleClick()) {
				return;
			}
			((ExamNextMemoryActivity) mContext).JumShowImage();
		}
	};

	private MemoryICallBack memoryICallBack = new MemoryICallBack() {

		private int questionId;

		@Override
		public void setProgress(int rate) {
			// TODO Auto-generated method stub

		}

		@Override
		public void setParams(Bundle bundle) {
			// TODO Auto-generated method stub
			this.questionId = bundle.getInt(MemoryNetTask.CORRECTION_QUESTION_ID);
		}

		@Override
		public void setFinished(boolean isover) {
			// TODO Auto-generated method stub
			// isover false 表示网络访问出现问题
			if (!isover) {
				Message message = Message.obtain();
				message.obj = questionId;
				message.what = EXAMPAGERADAPTER_SUCCESS;
				mHandler.sendMessage(message);
			}
		}

		@Override
		public void setError(String msg) {
			// TODO Auto-generated method stub
			Message message = new Message();
			message.obj = msg;
			message.what = ExamPagerAdapter.EXAMPAGERADAPTER_FAIL;
			mHandler.sendMessage(message);
		}

		@Override
		public void setData(Message data) {
			// TODO Auto-generated method stub
			Message message = new Message();
			message.obj = data.obj;
			message.what = ExamPagerAdapter.EXAMPAGERADAPTER_SUCCESS;
			mHandler.sendMessage(message);
		}
	};

	private OnTouchEventListener mOnTouchEventListener = new OnTouchEventListener() {

		@Override
		public void switchActivity() {
			// TODO Auto-generated method stub
			((ExamNextMemoryActivity) mContext).switchActivity();
		}
	};

	public Bitmap getBitmap() {
		try {
			InputStream stream = mContext.getAssets().open("222.png");
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

	private DemoRemberOrNoRelative mDemoRemberOrNoRelative = new DemoRemberOrNoRelative() {

		@Override
		public void unRemmber() {
			// TODO Auto-generated method stub
			((ExamNextMemoryActivity) mContext).nextQuestion2();
			((ExamNextMemoryActivity) mContext).runOnUiThread(new Runnable() {
				@Override
				public void run() {
					remmberFunction();
				}
			});
			
		}

		@Override
		public void remmber() {
			// TODO Auto-generated method stub
			((ExamNextMemoryActivity) mContext).nextQuestion();
			((ExamNextMemoryActivity) mContext).runOnUiThread(new Runnable() {
				@Override
				public void run() {
					remmberFunction();
				}
			});
		}
	};

	private void remmberFunction() {
		for (int i = 0; i < questionIdArr.length; i++) {
			if (questionArrFlag[i] == 0) {
				final WebView webView = (WebView) mWeViews.get(i).findViewById(R.id.webview_item_content);
				final MemoryViewHolder memoryViewHolder = (MemoryViewHolder) webView.getTag();
				LinearLayout mLinearLayout = (LinearLayout) mWeViews.get(i).findViewById(R.id.select_question);
				RelativeLayout mRelativeLayout = (RelativeLayout) mWeViews.get(i).findViewById(R.id.remmber_again_next);
				if (questionId == memoryViewHolder.questionId) {
					mRelativeLayout.setVisibility(View.GONE);
					mLinearLayout.setVisibility(View.VISIBLE);
					webView.loadUrl("javascript:showContent('" + memoryViewHolder.questionContent + "')");
				}
			} else if (questionArrFlag[i] == 1) {
				ScrollView mScrollView = (ScrollView) mWeViews.get(i).findViewById(R.id.scrollview_id);
				MemoryViewHolder memoryViewHolder = (MemoryViewHolder) mScrollView.getTag();
				RelativeLayout mRelativeLayout = (RelativeLayout) mWeViews.get(i).findViewById(R.id.remmber_again_next);
				if (memoryViewHolder.questionId == questionId) {
					mRelativeLayout.setVisibility(View.VISIBLE);
				}
			}
		}
	}

	private int[] questionArrFlag;

	public void setQuestionArrFlag(int[] questionArrFlag) {
		this.questionArrFlag = questionArrFlag;

	}

	private DemoSelectRelative mDemoSelectRelative = new DemoSelectRelative() {

		@Override
		public void selectNextPager() {
			// TODO Auto-generated method stub
			((ExamNextMemoryActivity) mContext).nextQuestion();
			((ExamNextMemoryActivity) mContext).runOnUiThread(new Runnable() {
				@Override
				public void run() {
					for (int i = 0; i < questionIdArr.length; i++) {
						if (questionArrFlag[i] == 0) {
							final WebView webView = (WebView) mWeViews.get(i).findViewById(R.id.webview_item_content);
							final MemoryViewHolder memoryViewHolder = (MemoryViewHolder) webView.getTag();
							LinearLayout mLinearLayout = (LinearLayout) mWeViews.get(i).findViewById(R.id.select_question);
							RelativeLayout mRelativeLayout = (RelativeLayout) mWeViews.get(i).findViewById(
									R.id.remmber_again_next);
							if (questionId == memoryViewHolder.questionId) {
								mRelativeLayout.setVisibility(View.GONE);
								mLinearLayout.setVisibility(View.VISIBLE);
								webView.loadUrl("javascript:showContent('" + memoryViewHolder.questionContent + "')");
							}
						} else if (questionArrFlag[i] == 1) {

						}

					}
				}
			});
		}

		@Override
		public void selectAgainRemmber() {

			((ExamNextMemoryActivity) mContext).runOnUiThread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					for (int i = 0; i < questionIdArr.length; i++) {
						if (questionArrFlag[i] == 1) {

						} else if (questionArrFlag[i] == 0) {
							View view = mWeViews.get(i);
							WebView mWebView = (WebView) view.findViewById(R.id.webview_item_content);
							MemoryViewHolder memoryViewHolder = (MemoryViewHolder) mWebView.getTag();
							RelativeLayout mRelativeLayout = (RelativeLayout) view.findViewById(R.id.remmber_again_next);
							LinearLayout mLinearLayout = (LinearLayout) view.findViewById(R.id.select_question);
							if (memoryViewHolder.questionId == questionId) {
								mRelativeLayout.setVisibility(View.GONE);
								mLinearLayout.setVisibility(View.VISIBLE);
								mWebView.loadUrl("javascript:showContent('" + memoryViewHolder.questionContent + "')");
							}
						}
					}
				}
			});
		}
	};

	private DemoSelectEvent mdeDemoSelectEvent = new DemoSelectEvent() {

		@Override
		public void selectButton(int select) {
			// TODO Auto-generated method stub
			Log.e("mdeDemoSelectEvent", "next:" + select);
			((ExamNextMemoryActivity) mContext).selectButton(select);
		}

		@Override
		public void remmberAllKnowleage() {
			// TODO Auto-generated method stub

			((ExamNextMemoryActivity) mContext).runOnUiThread(new Runnable() {
				@Override
				public void run() {
					for (int i = 0; i < questionIdArr.length; i++) {
						if (questionArrFlag[i] == 1) {

						} else {
							final WebView webView = (WebView) mWeViews.get(i).findViewById(R.id.webview_item_content);
							final MemoryViewHolder memoryViewHolder = (MemoryViewHolder) webView.getTag();
							LinearLayout mLinearLayout = (LinearLayout) mWeViews.get(i).findViewById(R.id.select_question);
							RelativeLayout mRelativeLayout = (RelativeLayout) mWeViews.get(i).findViewById(
									R.id.remmber_again_next);
							if (questionId == memoryViewHolder.questionId) {
								mLinearLayout.setVisibility(View.GONE);
								mRelativeLayout.setVisibility(View.VISIBLE);
								webView.loadUrl("javascript:showContentAnswer('" + memoryViewHolder.questionContent + "')");
							}
						}
					}
				}
			});
		}

		@Override
		public void reloadButton() {
			// TODO Auto-generated method stub
			((ExamNextMemoryActivity) mContext).reloadButton();
		}
	};

	@Override
	public int getItemPosition(Object object) {
		// TODO Auto-generated method stub
		return super.getItemPosition(object);
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		// TODO Auto-generated method stub
		return arg0 == arg1;
	}

	@Override
	public void destroyItem(View container, int position, Object object) {
		// TODO Auto-generated method stub
		((MemoryViewPager) container).removeView(mWeViews.get(position));
	}

	public void setQuestionArr(int[] questionArr) {
		this.questionIdArr = questionArr;
	}

	public void reLoad(Handler mHandler, int currentIndex) {
		for (int i = 0; i < questionIdArr.length; i++) {
			if (questionArrFlag[i] == 1) {
				return;
			} else {
				final WebView webView = (WebView) mWeViews.get(i).findViewById(R.id.webview_item_content);
				final MemoryViewHolder memoryViewHolder = (MemoryViewHolder) webView.getTag();
				if (questionId == memoryViewHolder.questionId) {
					mHandler.post(new Runnable() {
						@Override
						public void run() {
							// TODO Auto-generated method stub
							webView.loadUrl("javascript:showContent('" + memoryViewHolder.questionContent + "')");
						}
					});
				}
			}

		}
	}

	public void refresh(Handler mHandler, final int index) {
		for (int i = 0; i < questionIdArr.length; i++) {
			if (questionArrFlag[i] == 1) {
				return;
			} else if (questionArrFlag[i] == 0) {
				final WebView webView = (WebView) mWeViews.get(i).findViewById(R.id.webview_item_content);
				final MemoryViewHolder memoryViewHolder = (MemoryViewHolder) webView.getTag();
				if (questionId == memoryViewHolder.questionId) {
					mHandler.post(new Runnable() {
						@Override
						public void run() {
							// TODO Auto-generated method stub
							webView.loadUrl("javascript:showMsg('" + memoryViewHolder.questionContent + "'" + ",'" + index + "')");
						}
					});
				}
			}
		}
	}

	public void loadQuestion(Handler mHandler, int index) {
		for (int i = 0; i < questionIdArr.length; i++) {
			if (questionArrFlag[i] == 1) {
				
				return;
			} else if (questionArrFlag[i] == 0) {
				final WebView webView = (WebView) mWeViews.get(i).findViewById(R.id.webview_item_content);
				final MemoryViewHolder memoryViewHolder = (MemoryViewHolder) webView.getTag();
				if (questionId == memoryViewHolder.questionId) {
					mHandler.post(new Runnable() {
						@Override
						public void run() {
							// TODO Auto-generated method stub
							webView.loadUrl("javascript:showContentAnswer('" + memoryViewHolder.questionContent + "')");
						}
					});
				}
			}
		}
	}
}
