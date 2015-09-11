package com.ihaoxue.memory.adapter;

import java.util.List;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ihaoxue.memory.R;
import com.ihaoxue.memory.ui.MemoryActivity;
import com.ihaoxue.memory.ui.MemoryActivity.DataHolder;

public class MemoryPageAdapter extends PagerAdapter {

	private Context mContext ;
	private List<View> memoryViews ;
	
	
	public MemoryPageAdapter() {
		// TODO Auto-generated constructor stub
	}
	public MemoryPageAdapter(Context mContext , List<View> memoryPagers ) {
		// TODO Auto-generated constructor stub
		this.mContext = mContext ;
		this.memoryViews = memoryPagers ;
	}
	
	@Override
	public Object instantiateItem(View container, int position) {
		// TODO Auto-generated method stub
		View childView = memoryViews.get(position) ;
		if (childView==null) {
			childView = ((MemoryActivity)mContext).createQuestionView(position) ;
		}
		childView.setTag(position) ;
		((ViewPager) container).addView(childView);  
		
		RelativeLayout content = (RelativeLayout) childView.findViewById(R.id.pager_content) ;
 		DataHolder dataHolder = (DataHolder) content.getTag() ;
		TextView zhangName = (TextView) content.findViewById(R.id.zhang_name) ;
		TextView jieName = (TextView)content.findViewById(R.id.jie_name) ;
		TextView questionName = (TextView)content.findViewById(R.id.question_name) ;
		TextView question = (TextView)content.findViewById(R.id.question) ;
		// 问题答案re
		TextView answer = (TextView)content.findViewById(R.id.answer) ;
		
		
		
		zhangName.setText(dataHolder.mQuestionModel.getQuestionChapter()) ;
		jieName.setText(dataHolder.mQuestionModel.getQuestionSection()) ;
		questionName.setText(dataHolder.mQuestionModel.getQuestionTopic()) ;
		question.setText(dataHolder.mQuestionModel.getQuestionName()) ;
	//	answer.setText(dataHolder.mQuestionModel.getQuestionAnswer()) ;
		return childView;
	}
	
	
	
	
	
	
	
	@Override
	public void notifyDataSetChanged() {
		// TODO Auto-generated method stub
		super.notifyDataSetChanged();
	}
	
	
	@Override
	public int getItemPosition(Object object) {
		// TODO Auto-generated method stub
		return super.getItemPosition(object);
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return memoryViews.size();
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		// TODO Auto-generated method stub
		return arg0 == arg1;
	}

	@Override
	public void destroyItem(View container, int position, Object object) {
		// TODO Auto-generated method stub
		((ViewPager)container).removeView(memoryViews.get(position)) ;
	}
	
	
	public void setDataViews(List<View> dataViews){
		
		this.memoryViews = dataViews ;
		notifyDataSetChanged() ;
	}
	
	public View getDataView(int index){
		return memoryViews.get(index) ;
	}
	
	
	
}
