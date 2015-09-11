package com.ihaoxue.memory.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.ihaoxue.memory.ConstValue;

public class NetListenerService extends Service {

	
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		
		Log.e("BroadcastReceiver", "action") ;
		
		IntentFilter mIntentFilter = new IntentFilter() ;
		mIntentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION) ; // 增加标签
		registerReceiver(netListenerReceiver, mIntentFilter) ;
	}
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		unregisterReceiver(netListenerReceiver) ;
	}
	
	private NetworkInfo mNetworkInfo ;
	private ConnectivityManager connectivityManager ;
	
	
	private BroadcastReceiver netListenerReceiver = new BroadcastReceiver() {
		
		@Override
		public void onReceive(Context context, Intent data) {
			// TODO Auto-generated method stub
			String action = data.getAction() ;
			Log.e("BroadcastReceiver", action) ;
			if (action.equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
				
				connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE) ;
				mNetworkInfo = connectivityManager.getActiveNetworkInfo() ;
				if (mNetworkInfo!=null && mNetworkInfo.isAvailable()) {
					ConstValue.NETWORKSTATE = true ;
				}else {
					 Toast.makeText(context, "网络连接失败，请设置网络",Toast.LENGTH_SHORT).show();
					ConstValue.NETWORKSTATE = false ;
				}
			}
		}
	};
}
