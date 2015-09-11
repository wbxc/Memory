package com.ihaoxue.memory.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ihaoxue.memory.ConstValue;
import com.ihaoxue.memory.R;
import com.ihaoxue.memory.Utility;
import com.ihaoxue.memory.model.TreeElement;
import com.ihaoxue.memory.ui.BaseActivity;
import com.ihaoxue.memory.ui.MainHomeActivity;

public class KnowledgeTreeViewAdapter extends TreeViewAdapter {

	Context contenxt;

	public KnowledgeTreeViewAdapter(Context context,
			List<TreeElement> parentListTreeview) {
		super(context, parentListTreeview);
		this.contenxt = context;
		m_inflater = LayoutInflater.from(context);
		m_parentTreelist = parentListTreeview;
	}

	private LayoutInflater m_inflater;
	private List<TreeElement> m_parentTreelist;
	private TreeElement m_curTreeElement;

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return super.getCount();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return super.getItem(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return super.getItemId(position);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		ViewHolder holder = null;
		m_curTreeElement = m_parentTreelist.get(position);

		if (convertView == null) {
			convertView = m_inflater.inflate(R.layout.customview_item_treeview,null);

			holder = new ViewHolder();
			holder.text = (TextView) convertView
					.findViewById(R.id.textview_treeview);
			holder.space = (LinearLayout) convertView.findViewById(R.id.space);
			holder.connection = (RelativeLayout) convertView
					.findViewById(R.id.layout_treeview_connection);
			holder.icon = (ImageView) convertView
					.findViewById(R.id.img_treeview_ico);
			holder.status = (Button) convertView
					.findViewById(R.id.button_treeview);
			holder.status.setTag(m_curTreeElement);

			holder.status.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					if (Utility.isFastDoubleClick())
						return;
					((MainHomeActivity) contenxt).goStartStudy(((TreeElement)arg0.getTag()).getCaption());
				}
			});

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
			holder.status.setTag(m_curTreeElement);
		}
		int level = m_curTreeElement.getLevel();
		holder.space.removeAllViews();
		if (level != 0) {
			TextView textEmpty = new TextView(parent.getContext());
			textEmpty.setWidth(17 * level);
			holder.space.addView(textEmpty);
		}

		if (m_curTreeElement.isHasChild()) {
			if (m_curTreeElement.isExpanded()) {
				if (m_curTreeElement.getLevel() == 0){
					holder.icon.setImageResource(R.drawable.knowledgetree_rootexpanded);
				}else
					holder.icon.setImageResource(R.drawable.knowledgetree_expanded);
			} else {
				if (m_curTreeElement.getLevel() == 0)
					holder.icon.setImageResource(R.drawable.knowledgetree_rootunexpanded);
				else
					holder.icon
							.setImageResource(R.drawable.knowledgetree_unexpanded);
			}
		} else
			holder.icon.setImageResource(R.drawable.knowledgetree_leaf);

		if (m_curTreeElement.getLevel() == 0) {
			if (position + 1 < getCount()&& m_parentTreelist.get(position + 1).getLevel() == 0) {
				holder.connection.setBackgroundResource(0);
			} else if (position + 1 == getCount())
				holder.connection.setBackgroundResource(0);
			else
				holder.connection.setBackgroundResource(R.drawable.knowledgetree_halfconnection_root);
		} else {
			if (position + 1 < getCount()&& m_parentTreelist.get(position + 1).getLevel() == 0) {
				holder.connection.setBackgroundResource(R.drawable.knowledgetree_halfconnection_leaf);
			} else if (position == getCount() - 1)
				holder.connection.setBackgroundResource(R.drawable.knowledgetree_halfconnection_leaf);
			else
				holder.connection.setBackgroundResource(R.drawable.knowledgetree_connection);
		}

		if (m_curTreeElement.getStatus() != null&& !(m_curTreeElement.getStatus().equals(""))) {
			
		} else {
			holder.status.setVisibility(View.GONE);
		}

		String strMaster = m_curTreeElement.getStatus();
		if (strMaster != null && strMaster.equals(ConstValue.STATUS_REFINE)) {
			
			//holder.text.setBackgroundResource(R.layout.text_roundcorner_refine);
			
		} else if (strMaster != null
				&& strMaster.equals(ConstValue.STATUS_STRENGTHERN)) {
			
			//holder.text.setBackgroundResource(R.layout.text_roundcorner_strengthen);
			
		} else if (strMaster != null
				&& strMaster.equals(ConstValue.STATUS_TRAINING)) {
			
			//holder.text.setBackgroundResource(R.layout.text_roundcorner_training);
			
		} else
			holder.status.setVisibility(View.GONE);
		
		holder.text.setText(m_curTreeElement.getCaption());
		
		return convertView;
	}

	private class ViewHolder {
		LinearLayout space;
		TextView text;
		Button status;
		ImageView icon;
		RelativeLayout connection;
	}

	@Override
	public void onClickExpanded(int position) {
		super.onClickExpanded(position);
	}

}
