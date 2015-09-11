package com.ihaoxue.memory;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Utility {

	public static boolean MOCK_RESULT_DEBUG = false;

	public static Date getCurrentDate() {
		return new java.util.Date();
	}

	public static boolean wifiAlive(Context mContext) {
		ConnectivityManager cm = (ConnectivityManager) mContext
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		return cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED ? true
				: false;
	}

	private static long lastClickTime;
	private static long clickgap = 1000;

	public static void setClickGap(long gap) {
		clickgap = gap;
	}

	public static void resetClickGap() {
		clickgap = 500;
	}

	public static boolean isFastDoubleClick() {
		long time = System.currentTimeMillis();
		long timeD = time - lastClickTime;
		if (0 < timeD && timeD < clickgap) {
			return true;
		}
		lastClickTime = time;
		return false;
	}

	public static int dip2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

	public static int px2dip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}

	public static int px2sp(float pxValue, float fontScale) {
		return (int) (pxValue / fontScale + 0.5f);
	}

	public static int sp2px(float spValue, float fontScale) {
		return (int) (spValue * fontScale + 0.5f);
	}

	// 字符串不为空也不为0
	public static boolean isAvailable(String str) {
		return ((str != null) && !str.equals("") && !str.equals("0"));
	}

	public static int getActualLength(String str) {
		int nIndex = str.getBytes().length;
		try {
			nIndex = (str.getBytes("gbk").length);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return nIndex;
	}
}