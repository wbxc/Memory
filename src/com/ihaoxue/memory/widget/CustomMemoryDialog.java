package com.ihaoxue.memory.widget;

import com.ihaoxue.memory.R;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CustomMemoryDialog extends Dialog  {

	private static CustomerInterface mCustomerInterface = null ;
	
	private static Button dissMissButton = null ;
	private static Button loginButton = null ;
	private static TextView top_title = null;
	public CustomMemoryDialog(Context context) {
		// TODO Auto-generated constructor stub
		super(context) ;
	}
	public CustomMemoryDialog(Context context, int theme) {
		super(context, theme);
		// TODO Auto-generated constructor stub
	}

	private static CustomMemoryDialog instance = null;
	
	public static CustomMemoryDialog getCustomMemoryDialog(Context context ,CustomerInterface mCustomer){
		   mCustomerInterface = mCustomer ;
			instance = new CustomMemoryDialog(context, R.style.customer_memory_dialog) ;
			View mView = LayoutInflater.from(context).inflate(R.layout.dialog_custom_memory, null) ;
			dissMissButton = (Button) mView.findViewById(R.id.fou_button) ;
			dissMissButton.setOnClickListener(new android.view.View.OnClickListener() {
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					mCustomerInterface.dimissButton() ;
				}
			}) ;
			
			loginButton = (Button)mView.findViewById(R.id.go_login) ;
			loginButton.setOnClickListener(new android.view.View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					mCustomerInterface.loginGoButton() ;
				}
			}) ;
			
			top_title = (TextView)mView.findViewById(R.id.top_title) ;
			instance.setContentView(mView) ;
		return instance ;
	}
	
	
	public void setMessage(String leftString , String centerMessage ,String rightString){
		dissMissButton.setText(leftString) ;
		loginButton.setText(rightString) ;
		top_title.setText(centerMessage) ;
	}
	
	public void createShowDialog(){
		instance.show() ;
	}
	public void dissMissDialog(){
		instance.dismiss() ;
	}
	
	
	
	public interface CustomerInterface{
		
		public void dimissButton() ;
		
		public void loginGoButton() ;
		
	}
	
	
}
