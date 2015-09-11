package com.ihaoxue.memory.model;

public class UpdateModel {

	private int versionCode;
    private String versionName;
    private String downloadUrl;
    private String updateLog;
	public int getVersionCode() {
		return versionCode;
	}
	public String getVersionName() {
		return versionName;
	}
	public String getDownloadUrl() {
		return downloadUrl;
	}
	public String getUpdateLog() {
		return updateLog;
	}
	public void setVersionCode(int versionCode) {
		this.versionCode = versionCode;
	}
	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}
	public void setDownloadUrl(String downloadUrl) {
		this.downloadUrl = downloadUrl;
	}
	public void setUpdateLog(String updateLog) {
		this.updateLog = updateLog;
	}
    
    
}
