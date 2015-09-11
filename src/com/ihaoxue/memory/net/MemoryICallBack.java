package com.ihaoxue.memory.net;

import android.os.Bundle;
import android.os.Message;

public interface MemoryICallBack {

	public void setProgress(int rate);

	public void setData(Message data);

	public void setError(String msg);

	public void setFinished(boolean isover);

	public void setParams(Bundle bundle);
}
