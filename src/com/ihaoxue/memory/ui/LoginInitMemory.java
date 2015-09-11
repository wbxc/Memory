package com.ihaoxue.memory.ui;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.FindCallback;
import com.avos.avoscloud.SaveCallback;
import com.ihaoxue.memory.MemoryApplication;
import com.ihaoxue.memory.R;
import com.ihaoxue.memory.adapter.InitMemoryAdapter;
import com.ihaoxue.memory.bean.SubjectModel;
import com.ihaoxue.memory.utils.ShareTools;

public class LoginInitMemory extends BaseActivity implements OnClickListener{

	private TextView common_text;
	private List<SubjectModel> mSubjectModels;

	private Button jianZhuButton ;
	private Button jidianButton ;
	private Button gongLuButton ;
	private Button shiZhengongYonngButton ;
	private Button tongxinButton ;
	private Button kuangyeButton ;
	private Button tieLuButton ;
	private Button minHangButton ;
	private Button gangKouhangDao  ;
	private Button shuiLiShuiDian ;
	
	private String selectSubjectName;
	private String selectSubjectId;

	@Override
	protected void initDataDataVariable() {
		// TODO Auto-generated method stub
		mSubjectModels = new ArrayList<SubjectModel>();

		SubjectModel m1 = new SubjectModel();
		m1.setSubjectName("建筑工程");
		m1.setSubjectId(1 + "");
		mSubjectModels.add(m1);

		SubjectModel m2 = new SubjectModel();
		m2.setSubjectId(2 + "");
		m2.setSubjectName("机电工程");
		mSubjectModels.add(m2);

		SubjectModel m3 = new SubjectModel();
		m3.setSubjectId(3 + "");
		m3.setSubjectName("市政公用工程");
		mSubjectModels.add(m3);

		SubjectModel m4 = new SubjectModel();
		m4.setSubjectId(4 + "");
		m4.setSubjectName("公路工程");
		mSubjectModels.add(m4);

		SubjectModel m5 = new SubjectModel();
		m5.setSubjectId(5 + "");
		m5.setSubjectName("通信与广电工程");
		mSubjectModels.add(m5);

		SubjectModel m6 = new SubjectModel();
		m6.setSubjectId(6 + "");
		m6.setSubjectName("矿业工程");
		mSubjectModels.add(m6);

		SubjectModel m7 = new SubjectModel();
		m7.setSubjectId(7 + "");
		m7.setSubjectName("铁路工程");
		mSubjectModels.add(m7);

		SubjectModel m8 = new SubjectModel();
		m8.setSubjectId(8 + "");
		m8.setSubjectName("水利水电工程");
		mSubjectModels.add(m8);

		SubjectModel m9 = new SubjectModel();
		m9.setSubjectId(9 + "");
		m9.setSubjectName("民航机场工程");
		mSubjectModels.add(m9);

		SubjectModel m10 = new SubjectModel();
		m10.setSubjectId(10 + "");
		m10.setSubjectName("港口与航道工程");
		mSubjectModels.add(m10);
	}

	@Override
	protected void setMainContentView() {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_init_memory);
		MemoryApplication.getInstance().addActivity(LoginInitMemory.this);
	}

	@Override
	protected void initComponentView() {
		// TODO Auto-generated method stub
		common_text = (TextView) findViewById(R.id.common_text);
		jianZhuButton = (Button)findViewById(R.id.jianzhugcngheng) ;
		jidianButton = (Button)findViewById(R.id.jidiangcheng) ;
		shiZhengongYonngButton = (Button)findViewById(R.id.shizhenggcheng) ;
		gongLuButton = (Button)findViewById(R.id.gonglugcheng) ;
		tongxinButton = (Button)findViewById(R.id.tongxingdian) ;
		kuangyeButton = (Button)findViewById(R.id.kuangyegcheng) ;
		tieLuButton = (Button)findViewById(R.id.tielugcheng) ;
		minHangButton = (Button)findViewById(R.id.mihangjichang) ;
		gangKouhangDao = (Button)findViewById(R.id.gangkou_hangdao) ;
		shuiLiShuiDian = (Button)findViewById(R.id.shuilishuidian) ;
	}
	@Override
	protected void initEvent() {
		// TODO Auto-generated method stub
		common_text.setText(getResources().getString(R.string.common_login_init_title));
		jianZhuButton.setOnClickListener(this) ;
		jidianButton.setOnClickListener(this) ;
		shiZhengongYonngButton.setOnClickListener(this) ;
		gongLuButton.setOnClickListener(this) ;
		tongxinButton.setOnClickListener(this) ;
		kuangyeButton.setOnClickListener(this) ;
		tieLuButton.setOnClickListener(this) ;
		minHangButton.setOnClickListener(this) ;
		gangKouhangDao.setOnClickListener(this) ;
		shuiLiShuiDian.setOnClickListener(this) ;
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right) ;
	}
	
	
	
	
	public void saveData(AVUser mAvUser) {
		mAvUser.put("selectSubjectName", selectSubjectName);
		mAvUser.put("selectSubjectId", selectSubjectId);
		mAvUser.saveInBackground(new SaveCallback() {
			@Override
			public void done(AVException arg0) {
				// TODO Auto-generated method stub
				if (arg0 == null) {
					ShareTools.getInstance().saveInitMemory(LoginInitMemory.this, selectSubjectId, selectSubjectName) ;
					Intent intent = new Intent();
					intent.setClass(LoginInitMemory.this, MainHomeActivity.class);
					Bundle mBundle = new Bundle();
					mBundle.putString("subject_id", selectSubjectId);
					mBundle.putString("subject_name", selectSubjectName);
					intent.putExtras(mBundle);
					startActivity(intent);
					overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
					LoginInitMemory.this.finish();
				} else {
					Toast.makeText(LoginInitMemory.this, "初始化失败，请重试"+arg0.getMessage(), Toast.LENGTH_SHORT).show();
				}
			}
		});
	}

	private Handler mHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 1000:
				AVUser mAvUser = (AVUser) msg.obj ;
				saveData(mAvUser) ;
				break;
			default:
				break;
			}
		};
	};

	@Override
	protected void initNetDataRequest() {
		// TODO Auto-generated method stub

	}

	private void setSelectCourse(int position){
		selectSubjectName = mSubjectModels.get(position).getSubjectName();
		selectSubjectId = mSubjectModels.get(position).getSubjectId();
		AVQuery<AVUser> mAvQuery = AVUser.getQuery();
		mAvQuery.whereEqualTo("username",ShareTools.getInstance().readUserInfo(LoginInitMemory.this).get(ShareTools.USER_NAME));
		mAvQuery.findInBackground(new FindCallback<AVUser>() {
			@Override
			public void done(List<AVUser> arg0, AVException arg1) {
				// TODO Auto-generated method stub
				if (arg1==null) {
					if (arg0 != null && !arg0.isEmpty()) {
						Message message = Message.obtain();
						message.what = 1000;
						message.obj = arg0.get(0);
						mHandler.sendMessage(message);
					}else {
						Toast.makeText(LoginInitMemory.this, "用户为空", Toast.LENGTH_SHORT).show() ;
					}
				}else {
					Log.e("ShareTools", arg1.getMessage() ) ;
				}
			}
		});
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.jianzhugcngheng:
			setSelectCourse(0) ;
			break;
		case R.id.jidiangcheng:
			setSelectCourse(1) ;
			break ;
		case R.id.shizhenggcheng:
			setSelectCourse(2) ;
			break ;
		case R.id.gonglugcheng:
			setSelectCourse(3) ;
			break ;
		case R.id.tongxingdian:
			setSelectCourse(4) ;
			break ;
		case R.id.kuangyegcheng:
			setSelectCourse(5) ;
			break ;
		case R.id.tielugcheng:
			setSelectCourse(6) ;
			break ;
		case R.id.mihangjichang:
			setSelectCourse(7) ;
			break ;
		case R.id.gangkou_hangdao:
			setSelectCourse(8) ;
			break ;
		case R.id.shuilishuidian:
			setSelectCourse(9) ;
			break;
		default:
			break;
		}
	}

}
