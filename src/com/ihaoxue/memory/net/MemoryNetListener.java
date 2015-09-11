package com.ihaoxue.memory.net;

import org.json.JSONObject;

import android.content.Context;

public interface MemoryNetListener {

	/**
	 * @param json
	 * @param type
	 */
	public void recvDataFromConn(JSONObject json, int type);

	/**
	 * @param e
	 */
	public void onConnectionError(Exception e);

	/**
	 * @return
	 */
	public Context getContext();
	
	
}
