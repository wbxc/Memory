package com.ihaoxue.memory.widget;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.TextView;

import com.ihaoxue.memory.R;

public class LoginLoadProgress extends Dialog {
    private static LoginLoadProgress customProgressDialog = null;
     
    public LoginLoadProgress(Context context){
        super(context);
    }
     
    public LoginLoadProgress(Context context, int theme) {
        super(context, theme);
    }
     
    public static LoginLoadProgress createDialog(Context context){
        customProgressDialog = new LoginLoadProgress(context,R.style.customer_memory_dialog);
        customProgressDialog.setContentView(R.layout.login_load_bar);
        customProgressDialog.getWindow().getAttributes().gravity = Gravity.CENTER;
         
        return customProgressDialog;
    }
  
    public void onWindowFocusChanged(boolean hasFocus){
         
        if (customProgressDialog == null){
            return;
        }
         
        ImageView imageView = (ImageView) customProgressDialog.findViewById(R.id.loadingImageView);
        AnimationDrawable animationDrawable = (AnimationDrawable) imageView.getBackground();
        animationDrawable.start();
    }
  
    /**
     *
     * [Summary]
     *       setTitile 标题
     * @param strTitle
     * @return
     *
     */
    public LoginLoadProgress setTitile(String strTitle){
        return customProgressDialog;
    }
     
    /**
     *
     * [Summary]
     *       setMessage 提示内容
     * @param strMessage
     * @return
     *
     */
    public LoginLoadProgress setMessage(String strMessage){
        TextView tvMsg = (TextView)customProgressDialog.findViewById(R.id.id_tv_loadingmsg);
         
        if (tvMsg != null){
            tvMsg.setText(strMessage);
        }
         
        return customProgressDialog;
    }
}