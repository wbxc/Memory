package com.ihaoxue.memory.adapter;

import java.util.List;

import com.ihaoxue.memory.R;
import com.ihaoxue.memory.bean.SubjectModel;

import android.content.Context;
import android.opengl.Visibility;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class InitMemoryAdapter extends BaseAdapter {

	private Context mContext ;
	private List<SubjectModel> mSubjectModels ;
	
	public InitMemoryAdapter(Context mContext , List<SubjectModel> mSubjectModels) {
		// TODO Auto-generated constructor stub
		this.mContext = mContext ;
		this.mSubjectModels = mSubjectModels ;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		if (mSubjectModels!=null && !mSubjectModels.isEmpty()) {
			
			return mSubjectModels.size() ;
		}
		return 0;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mSubjectModels.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder  viewHolder ;
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(R.layout.adapter_init_memory_grid_ui, null) ;
			viewHolder = new ViewHolder() ;
			viewHolder.subjectName = (TextView)convertView.findViewById(R.id.subject_name) ;
			convertView.setTag(viewHolder) ;
		}
		viewHolder = (ViewHolder) convertView.getTag() ;
		viewHolder.subjectName.setText(mSubjectModels.get(position).getSubjectName()) ;
		return convertView;
	}

	class ViewHolder{
		TextView subjectName ;
	}
}
