package com.ihaoxue.memory.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import android.R.integer;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.ihaoxue.memory.ConstValue;
import com.ihaoxue.memory.MemoryApplication;
import com.ihaoxue.memory.R;
import com.ihaoxue.memory.Utility;
import com.ihaoxue.memory.adapter.KnowledgeTreeViewAdapter;
import com.ihaoxue.memory.bean.MemoryQuestionPaper;
import com.ihaoxue.memory.model.PointMaster;
import com.ihaoxue.memory.model.TreeElement;
import com.ihaoxue.memory.net.MemoryICallBack;
import com.ihaoxue.memory.net.MemoryNetTaskManagement;
import com.ihaoxue.memory.widget.CustomLoadProgress;


public class MainHomeActivity extends BaseActivity implements OnClickListener {
	private ListView contentListView ;
	private ImageView settingImage ;
	private List<TreeElement> m_parentTreelist;
	private KnowledgeTreeViewAdapter treeViewAdapter = null;
	private HashMap<PointMaster, TreeElement> m_mapPoint2TreeElement;
	private HashMap<Integer, TreeElement> m_mapID2TreeElement;
	private static final int SUCCESS_REQUEST_DATA = 10001 ;
	private static final int FAILE_REQUEST_DATA = 10002 ;
	private static final int ERROR_REQUEST_DATA = 10003 ;
	private static final int SUCCESS_GOTO_EXAMMEMORY = 10004 ;
	private static boolean isQuit = false ;
	private Timer timer = new Timer() ;
	// 选择节点
	private final String[] m_arrStatus = { ConstValue.STATUS_TRAINING, ConstValue.STATUS_STRENGTHERN,
			ConstValue.STATUS_REFINE };
	private CustomLoadProgress mCustomLoadProgress ;
	private Button continueLastProgress ;
	/**
	 * 初始化树
	 * @param arrFatherTree
	 */
	private void initTreeViewData(ArrayList<PointMaster> arrFatherTree) {
		if (arrFatherTree != null && arrFatherTree.size() > 0) {
			if (m_parentTreelist != null)
				m_parentTreelist.clear();
			for (int i = 0; i < arrFatherTree.size(); i++) {
				PointMaster curPoint = arrFatherTree.get(i);
				if (curPoint.getNode_level() == 0) {
					TreeElement pdfOutlineElementParent = new TreeElement(curPoint.getNode_id(),
							curPoint.getNode_name(),
							// 显示 当前的状态
							m_arrStatus[curPoint.getMasterdegree() > 0 && curPoint.getMasterdegree() < 4 ? curPoint
									.getMasterdegree() - 1 : 0]);
					m_mapPoint2TreeElement.put(curPoint, pdfOutlineElementParent);
					m_mapID2TreeElement.put(curPoint.getNode_id(), pdfOutlineElementParent);
					boolean bFind = false;
					for (int j = 0; j < m_parentTreelist.size(); j++) {
						if (m_parentTreelist.get(j).getId() > pdfOutlineElementParent.getId()) {
							m_parentTreelist.add(j, pdfOutlineElementParent);
							bFind = true;
							break;
						}
					}
					if (!bFind)
						m_parentTreelist.add(pdfOutlineElementParent);
				} else {
					TreeElement pdfOutlineElementChild = new TreeElement(curPoint.getNode_id(),
							curPoint.getNode_name(), m_arrStatus[curPoint.getMasterdegree() > 0
									&& curPoint.getMasterdegree() < 4 ? curPoint.getMasterdegree() - 1 : 0]);
					m_mapPoint2TreeElement.put(curPoint, pdfOutlineElementChild);
					m_mapID2TreeElement.put(curPoint.getNode_id(), pdfOutlineElementChild);
				}
			}
			if (m_parentTreelist.size() > 0) {
				m_parentTreelist.get(m_parentTreelist.size() - 1).setLastSibling(true);
			}
			Set<PointMaster> key = m_mapPoint2TreeElement.keySet();
			for (Iterator<PointMaster> it = key.iterator(); it.hasNext();) {
				PointMaster point = it.next();
				TreeElement element = m_mapPoint2TreeElement.get(point);
				if (point.getNode_level() != 0) {// && point.getNode_level() < 3
					TreeElement parent = m_mapID2TreeElement.get(point.getParentid());
					if (parent != null && element != null) {
						parent.addChild(element);
					}
				}
			}
		}
	}

	private void initLayoutParams() {
		if (treeViewAdapter == null) {
			contentListView = (ListView) findViewById(R.id.content_listview);
			// treeViewAdapter = new
			// KnowledgeTreeViewAdapter(this,m_parentTreelist);
			treeViewAdapter = new KnowledgeTreeViewAdapter(MainHomeActivity.this, m_parentTreelist);
			contentListView.setAdapter(treeViewAdapter);
			contentListView.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
					treeViewAdapter.onClickExpanded(position);
				}
			});
		} else {
			treeViewAdapter.notifyDataSetChanged();
		}
	}
	@Override
	protected void initDataDataVariable() {
		m_parentTreelist = new ArrayList<TreeElement>();
		m_mapPoint2TreeElement = new HashMap<PointMaster, TreeElement>();
		m_mapID2TreeElement = new HashMap<Integer, TreeElement>();
	}

	@Override
	protected void setMainContentView() {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_mainhome_ui) ;
		mCustomLoadProgress = CustomLoadProgress.createDialog(MainHomeActivity.this) ;
		MemoryApplication.getInstance().addActivity(MainHomeActivity.this) ;
	}

	@Override
	protected void initComponentView() {
		
		
		settingImage = (ImageView)findViewById(R.id.setting_image) ;
		continueLastProgress = (Button)findViewById(R.id.continue_last_progress) ;
	}

	@Override
	protected void initEvent() {
		settingImage.setOnClickListener(this);
		continueLastProgress.setOnClickListener(this) ;
		Intent mIntent = new Intent() ;
		mIntent.setAction("com.ihaoxue.memory.service.NetListenerService") ;
		startService(mIntent) ;
	}
	
	
	public void goStartStudy(String className){
		mCustomLoadProgress.show() ; 
		MemoryNetTaskManagement.getInstance(MainHomeActivity.this).createPaper(memoryICallBack, 1001) ;
	}
	
	
	private MemoryICallBack memoryICallBack = new MemoryICallBack() {
		
		@Override
		public void setProgress(int rate) {
			// TODO Auto-generated method stub
		}
		
		@Override
		public void setParams(Bundle bundle) {
			// TODO Auto-generated method stub
		}
		
		@Override
		public void setFinished(boolean isover) {
			// TODO Auto-generated method stub
			if (!isover) {
				Message message = Message.obtain() ;
				message.what = FAILE_REQUEST_DATA ;
				message.obj = "未知的错误" ;
				mHandler.sendMessage(message) ;
			}
		}
		
		@Override
		public void setError(String msg) {
			// TODO Auto-generated method stub
			// 访问出现异常
			Message message = Message.obtain() ;
			message.what = FAILE_REQUEST_DATA ;
			message.obj = msg ;
			mHandler.sendMessage(message) ;
		}
		@Override
		public void setData(Message data) {
			// TODO Auto-generated method stub
			Message message = Message.obtain() ;
			message.what = SUCCESS_GOTO_EXAMMEMORY ;
			message.obj = data.obj ;
			mHandler.sendMessage(message) ;
		}
	};
	
	
	
	
	public void startActivity(int index){
		Intent intent = new Intent() ;
		Bundle mBundle = new Bundle() ;
		//mBundle.putString("selectSubjectName", mSelectCourseModels.get(index).getCourseName()) ;
		intent.putExtras(mBundle) ;
		intent.setClass(MainHomeActivity.this, MemoryActivity.class) ;
		startActivity(intent) ;
	    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left) ;
	}

	
	
	
	@Override
	protected void initNetDataRequest() {
		// TODO Auto-generated method stub
		mCustomLoadProgress.setTitile("加载中。。") ;
		mCustomLoadProgress.show() ;
		MemoryNetTaskManagement.getInstance(MainHomeActivity.this).downloadTreePointer(mTreePointerICallBack) ;

	}
	
	private MemoryICallBack mTreePointerICallBack = new MemoryICallBack() {
		
		@Override
		public void setProgress(int rate) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void setParams(Bundle bundle) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void setFinished(boolean isover) {
			// TODO Auto-generated method stub
			if (!isover) {
				Message message = new Message() ;
				message.what = ERROR_REQUEST_DATA ;
				message.obj = "未知错误" ;
				mHandler.sendMessage(message) ;
			}
		}
		
		@Override
		public void setError(String msg) {
			// TODO Auto-generated method stub
			Message message = new Message() ;
			message.what = FAILE_REQUEST_DATA ;
			message.obj = msg ;
			mHandler.sendMessage(message) ;
		}
		
		@Override
		public void setData(Message data) {
			// TODO Auto-generated method stub
			Message message = Message.obtain() ;
			message.obj = data.obj ;
			message.what = SUCCESS_REQUEST_DATA ;
			mHandler.sendMessage(message) ;
		}
	};
	
	
	private Handler mHandler = new Handler(){
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case SUCCESS_REQUEST_DATA:
				mCustomLoadProgress.dismiss() ;
				ArrayList<PointMaster> mPointMasters = (ArrayList<PointMaster>) msg.obj ;
				initTreeViewData(mPointMasters) ;
				initLayoutParams() ;
				break;
			case FAILE_REQUEST_DATA:
				mCustomLoadProgress.dismiss() ;
				break ;
			case ERROR_REQUEST_DATA:
				mCustomLoadProgress.dismiss() ;
				break ;
			case SUCCESS_GOTO_EXAMMEMORY:
				mCustomLoadProgress.dismiss() ;
				MemoryQuestionPaper mPaper = (MemoryQuestionPaper)msg.obj ; 
				Intent intent = new Intent() ;
				Bundle mBundle = new Bundle() ;
				mBundle.putString("selectSubjectName", mPaper.getPaperName()) ;
				mBundle.putIntArray("questionAnswerNum", mPaper.getQuestionAnswerNum()) ;
				mBundle.putIntArray("questionArrFlag", mPaper.getQuestionArrFlag()) ;
				mBundle.putIntArray("questionIdArr", mPaper.getQuestionIdArr()) ;
				mBundle.putInt("globSumQuestions", mPaper.getQuestionAllNum()) ;
				intent.putExtras(mBundle) ;
				intent.setClass(MainHomeActivity.this, ExamMemoryActivity.class) ;
				startActivity(intent) ;
			    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left) ;
				break ;
			}
		};
	} ;

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode==KeyEvent.KEYCODE_BACK) {
			if (!isQuit) {
				isQuit =true ;
				Toast.makeText(this, "再按一次返回键退出记忆宝", Toast.LENGTH_SHORT).show();
				TimerTask mTimerTask = new TimerTask() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						isQuit =false ;
					}
				};
				timer.schedule(mTimerTask, 3000) ;
				return true ;
			}else {
				Intent mIntent = new Intent("com.ihaoxue.memory.service.NetListenerService") ;
				stopService(mIntent) ;
				
				MemoryApplication.getInstance().exitApplication() ;
				return false ;
			}
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.setting_image:
			Intent intent = new Intent() ;
			intent.setClass(MainHomeActivity.this, SettingActivity.class) ;
			startActivity(intent) ;
			overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left) ;
			break;
		case R.id.continue_last_progress:
			if (Utility.isFastDoubleClick()) 
				return ;
			goStartStudy("") ;
			break ;
		default:
			break;
		}
	}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		mHandler.removeMessages(SUCCESS_REQUEST_DATA) ;
		mHandler.removeMessages(FAILE_REQUEST_DATA) ;
		mHandler.removeMessages(ERROR_REQUEST_DATA) ;
		mHandler.removeMessages(SUCCESS_GOTO_EXAMMEMORY) ;
	}
}
