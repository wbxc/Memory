package com.ihaoxue.memory;

import android.widget.Toast;

import com.ihaoxue.memory.ui.ExamMemoryActivity;

public class JSKit {
	private ExamMemoryActivity ma;
	
	public JSKit(ExamMemoryActivity context) {
		this.ma = context;
	}
	
	public void showMsg(String msg) {
		Toast.makeText(ma, msg, Toast.LENGTH_SHORT).show();
	}
}
