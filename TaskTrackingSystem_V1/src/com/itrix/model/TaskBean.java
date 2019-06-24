package com.itrix.model;

public class TaskBean {
	private int catgId;
	private int taskId;
	private String taskName;
	private String taskDesc;
	private String activeStatus;
	
	public int getCatgId() {
		return catgId;
	}
	public void setCatgId(int catgId) {
		this.catgId = catgId;
	}
	
	public int getTaskId() {
		return taskId;
	}
	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public String getTaskDesc() {
		return taskDesc;
	}
	public void setTaskDesc(String taskDesc) {
		this.taskDesc = taskDesc;
	}
	public String getActiveStatus() {
		return activeStatus;
	}
	public void setActiveStatus(String activeStatus) {
		this.activeStatus = activeStatus;
	}	

	
}
