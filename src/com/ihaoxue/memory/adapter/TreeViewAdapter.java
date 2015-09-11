package com.ihaoxue.memory.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.ihaoxue.memory.model.TreeElement;

public class TreeViewAdapter extends BaseAdapter {

	private List<TreeElement> m_parentTreelist;

	public TreeViewAdapter(Context context, List<TreeElement> parentListTreeview) {
		m_parentTreelist = parentListTreeview;
	}

	@Override
	public int getCount() {
		return m_parentTreelist.size();
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		return null;
	}

	public void onClickExpanded(int position) {
		if (!m_parentTreelist.get(position).isHasChild()) {
			return;
		}

		if (m_parentTreelist.get(position).isExpanded()) {
			m_parentTreelist.get(position).setExpanded(false);
			TreeElement element = m_parentTreelist.get(position);
			ArrayList<TreeElement> temp = new ArrayList<TreeElement>();

			for (int i = position + 1; i < m_parentTreelist.size(); i++) {
				if (element.getLevel() >= m_parentTreelist.get(i).getLevel()) {
					break;
				}
				temp.add(m_parentTreelist.get(i));
			}

			m_parentTreelist.removeAll(temp);
			for (int i = position + 1; i < m_parentTreelist.size(); i++) {
				m_parentTreelist.get(i).setPosition(i);
			}

			notifyDataSetChanged();
		} else {
			TreeElement obj = m_parentTreelist.get(position);
			obj.setExpanded(true);
			int level = obj.getLevel();
			int nextLevel = level + 1;

			ArrayList<TreeElement> tempList = obj.getChildList();

			for (int i = 0; i < tempList.size(); i++) {
				TreeElement element = tempList.get(i);
				element.setLevel(nextLevel);
				element.setExpanded(false);
				m_parentTreelist.add(position + i + 1, element);
			}
			for (int i = position + 1; i < m_parentTreelist.size(); i++) {
				m_parentTreelist.get(i).setPosition(i);
			}
			notifyDataSetChanged();
		}

	}

}
