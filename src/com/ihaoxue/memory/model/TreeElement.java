package com.ihaoxue.memory.model;

import java.util.ArrayList;
import java.util.List;

import com.ihaoxue.memory.R;


public class TreeElement {

	private int img_tree_space_n = R.drawable.tree_space_n;
	private int img_tree_space_y = R.drawable.tree_space_y;

	private int id;
	private String caption;
	private String value;
	private int level;
	private TreeElement parent;
	private boolean isHasChild;
	private boolean isExpanded;
	private ArrayList<TreeElement> childList = new ArrayList<TreeElement>();
	private boolean isLastSibling;
	private ArrayList<Integer> spaceList;
	private int position;

	public TreeElement() {
		super();
	}

	public TreeElement(int id, String caption) {
		this.id = id;
		this.caption = caption;
		this.parent = null;
		this.level = 0;
		this.isExpanded = false;
		this.isHasChild = false;
		this.isLastSibling = false;
		this.setSpaceList(new ArrayList<Integer>());
		this.position = 0;
	}

	public TreeElement(int id, String caption, String status) {
		this.id = id;
		this.caption = caption;
		this.parent = null;
		this.level = 0;
		this.isExpanded = false;
		this.isHasChild = false;
		this.isLastSibling = false;
		this.setSpaceList(new ArrayList<Integer>());
		this.position = 0;
		this.status = status;
	}

	public void addChild(TreeElement treeElement) {
		treeElement.parent = this;
		if (treeElement.getParent() != null
				&& treeElement.getParent().getChildList().size() > 0) {
			List<TreeElement> siblingList = treeElement.getParent()
					.getChildList();
			treeElement.getParent().getChildList().get(siblingList.size() - 1)
					.setLastSibling(false);
		}

		if (this.childList.size() == 0)
			this.childList.add(treeElement);
		else {
			int i = 0;
			boolean bRepeat = false;
			for (i = this.childList.size() - 1; i >= 0; i--) {
				if (this.childList.get(i).getId() < treeElement.getId()) {
					this.childList.add(i + 1, treeElement);
					break;
				} else if (this.childList.get(i).getId() == treeElement.getId()) {
					bRepeat = true;
					break;
				}
			}

			if (i < 0 && !bRepeat)
				this.childList.add(0, treeElement);
		}

		this.isHasChild = true;
		treeElement.level = this.level + 1;
		treeElement.isLastSibling = true;
		if (this.level > 0) {
			treeElement.getSpaceList().addAll(this.getSpaceList());
			if (this.isLastSibling()) {
				treeElement.getSpaceList().add(img_tree_space_y);
			} else {
				treeElement.getSpaceList().add(img_tree_space_n);
			}
		}
	}

	String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public TreeElement getParent() {
		return parent;
	}

	public void setParent(TreeElement parent) {
		this.parent = parent;
	}

	public boolean isHasChild() {
		return isHasChild;
	}

	public void setHasChild(boolean isHasChild) {
		this.isHasChild = isHasChild;
	}

	public boolean isExpanded() {
		return isExpanded;
	}

	public void setExpanded(boolean isExpanded) {
		this.isExpanded = isExpanded;
	}

	public ArrayList<TreeElement> getChildList() {
		return childList;
	}

	public void setChildList(ArrayList<TreeElement> childList) {
		this.childList = childList;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public boolean isLastSibling() {
		return isLastSibling;
	}

	public void setLastSibling(boolean isLastSibling) {
		this.isLastSibling = isLastSibling;
	}

	public ArrayList<Integer> getSpaceList() {
		return spaceList;
	}

	public void setSpaceList(ArrayList<Integer> spaceList) {
		this.spaceList = spaceList;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}
}