package com.ihaoxue.memory.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ihaoxue.memory.R;

public class GridViewAdapter extends BaseAdapter {

	private Context mContext;
	private List<String> mList;

	public GridViewAdapter(Context mContext, List<String> mList) {
		// TODO Auto-generated constructor stub
		this.mContext = mContext;
		this.mList = mList;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		if (mList != null && !mList.isEmpty()) {
			return mList.size();
		}
		return 0;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	public void refreshData(List<String> mList) {
		this.mList = mList;
		notifyDataSetChanged();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder mViewHolder;
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.item_button, null);
			mViewHolder = new ViewHolder();
			mViewHolder.selectButton = (TextView) convertView
					.findViewById(R.id.item_button);
			convertView.setTag(mViewHolder);
		}
		mViewHolder = (ViewHolder) convertView.getTag();
		mViewHolder.selectButton.setText(mList.get(position));
		return convertView;
	}
	public class ViewHolder {
		TextView selectButton;
	}
}
