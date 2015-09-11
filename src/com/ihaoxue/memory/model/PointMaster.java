package com.ihaoxue.memory.model;

import org.json.JSONException;
import org.json.JSONObject;

public class PointMaster {

	private int masterdegree;
	private long donequestion;
	private String accuracy;
	private String grade;
	private int node_id;
	private String node_name;
	private int node_level;
	private int parentid;
	private int[] childs;
	private long totalquestion;

	public int getMasterdegree() {
		return masterdegree;
	}

	public void setMasterdegree(int masterdegree) {
		this.masterdegree = masterdegree;
	}

	public long getDonequestion() {
		return donequestion;
	}

	public void setDonequestion(long donequestion) {
		this.donequestion = donequestion;
	}

	public String getAccuracy() {
		return accuracy;
	}

	public void setAccuracy(String accuracy) {
		this.accuracy = accuracy;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public int getNode_id() {
		return node_id;
	}

	public void setNode_id(int node_id) {
		this.node_id = node_id;
	}

	public String getNode_name() {
		return node_name;
	}

	public void setNode_name(String node_name) {
		this.node_name = node_name;
	}

	public int getNode_level() {
		return node_level;
	}

	public void setNode_level(int node_level) {
		this.node_level = node_level;
	}

	public int getParentid() {
		return parentid;
	}

	public void setParentid(int parentid) {
		this.parentid = parentid;
	}

	public int[] getChilds() {
		return childs;
	}

	public void setChilds(int[] childs) {
		this.childs = childs;
	}

	public long getTotalquestion() {
		return totalquestion;
	}

	public void setTotalquestion(long totalquestion) {
		this.totalquestion = totalquestion;
	}

	
	
	public boolean loadFromJson(JSONObject json) {

		try {
			setMasterdegree(json.getInt("masterdegree"));
			setNode_id(json.getInt("id"));
			setNode_name(json.getString("name"));
			setNode_level(json.getInt("level"));
			setParentid(json.getInt("parentid"));
			setDonequestion(json.getLong("donequestion"));
			setAccuracy(json.getString("accuracy"));
			setGrade(json.getString("grade"));
			setTotalquestion(json.getLong("totalquestion"));

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			return false;
		}

		return true;
	}

}
