package com.ihaoxue.memory.adapter;

import java.util.List;

import com.ihaoxue.memory.R;
import com.ihaoxue.memory.bean.SelectCourseModel;
import com.ihaoxue.memory.ui.MainHomeActivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainHomeAdapter extends BaseAdapter {

	private Context mContext ;
	private List<SelectCourseModel> mSelectCourseModels ;
	
	private int temp = 0 ;
	public MainHomeAdapter(Context mContext , List<SelectCourseModel> selectCourseModels) {
		// TODO Auto-generated constructor stub
		this.mContext = mContext ;
		this.mSelectCourseModels = selectCourseModels ;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		if (mSelectCourseModels!=null && !mSelectCourseModels.isEmpty()) {
			
			return mSelectCourseModels.size() ;
		}
		return 0;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mSelectCourseModels;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder viewHolder ;
		ViewHolderTwo viewHolderTwo ;
		temp  = position ;
		if (mSelectCourseModels!=null && !mSelectCourseModels.isEmpty()) {
			SelectCourseModel selectCourseModel = mSelectCourseModels.get(position) ;
			if (selectCourseModel.isPublic()) {
				if (convertView==null) {
					convertView = LayoutInflater.from(mContext).inflate(R.layout.adapter_main_home_ui, null) ;
					viewHolder = new ViewHolder() ;
					viewHolder.courseName = (TextView)convertView.findViewById(R.id.subjectName) ;
					viewHolder.count_sum = (TextView)convertView.findViewById(R.id.count_sum) ;
					viewHolder.flagImage = (Button)convertView.findViewById(R.id.flag_image) ;
					convertView.setTag(viewHolder) ;
				}
				viewHolder = (ViewHolder) convertView.getTag() ;
				viewHolder.courseName.setText(selectCourseModel.getCourseName()) ;
				viewHolder.count_sum.setText(selectCourseModel.getCompteQuestion()+"/"+selectCourseModel.getSumQuestions()) ;
				if (selectCourseModel.getCompteQuestion()==0) {
					viewHolder.flagImage.setText("开始")  ;
				}
				if (selectCourseModel.getCompteQuestion()==selectCourseModel.getSumQuestions()) {
					viewHolder.flagImage.setText("再来十个") ;
				}
				if (selectCourseModel.getCompteQuestion()!=0 && selectCourseModel.getCompteQuestion()<selectCourseModel.getSumQuestions()) {
					viewHolder.flagImage.setText("继续") ;
				}
				viewHolder.flagImage.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						((MainHomeActivity)mContext).startActivity(temp) ;
					}
				}) ;
				return convertView ;
			}else {
				if (convertView == null) {
					convertView = LayoutInflater.from(mContext).inflate(R.layout.adapter_mainhome_two_ui, null) ;
					viewHolderTwo = new ViewHolderTwo() ;
					viewHolderTwo.courseName = (TextView)convertView.findViewById(R.id.course_name) ;
					convertView.setTag(viewHolderTwo) ;
				}
				viewHolderTwo = (ViewHolderTwo) convertView.getTag() ;
				viewHolderTwo.courseName.setText(selectCourseModel.getCourseName()) ;
				return convertView ;
			}
		}
		return null;
	}
	
	class ViewHolderTwo{
		TextView courseName ;
		TextView courseInfo ;
	}
	
	class ViewHolder{
		TextView courseName ;
		TextView count_sum ;
		Button flagImage ;
		
	}
	

}
