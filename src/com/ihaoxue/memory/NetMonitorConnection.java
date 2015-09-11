package com.ihaoxue.memory;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetMonitorConnection {

	public static boolean netConnection(Context mContext){
		ConnectivityManager mConnectivityManager =(ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE) ;
		// 获取手机所有连接管理对象（包括对wi-fi,net等连接的管理） 
		try {
			if (mConnectivityManager!=null) {
				NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo() ;
				if (mNetworkInfo!=null && mNetworkInfo.isConnected()) {
					if (mNetworkInfo.getState() == NetworkInfo.State.CONNECTED) { // 判断当前网络是否连接了
						return true ;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false ;
	}
	/**
	 * 判断网络连接的类型
	 * @param mContext
	 * @return
	 */
	public static int netCoonectionType(Context mContext){
		ConnectivityManager mConnManger = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE) ;
		NetworkInfo mNetworkInfo = null;
		try {
			mNetworkInfo = mConnManger.getActiveNetworkInfo() ;
			if (mNetworkInfo!=null && mNetworkInfo.isAvailable()) {
				return mNetworkInfo.getType() ;
			}
		} catch (Exception e) {
			e.printStackTrace() ;
		}
		return -1 ;
	}
}
