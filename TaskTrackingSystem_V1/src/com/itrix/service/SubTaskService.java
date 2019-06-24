package com.itrix.service;

import java.util.ArrayList;
import java.util.List;

import com.itrix.dao.CategoryMastDao;
import com.itrix.dao.SubTaskDao;
import com.itrix.dao.TaskDao;
import com.itrix.model.ListBean;
import com.itrix.model.SubTaskBean;

public class SubTaskService {
	private SubTaskDao sbTaskDaoObj;
	
	public SubTaskService()
	  {
		sbTaskDaoObj=new SubTaskDao();
		
	  }
	
	
	public int getSubTasksCountSer() {
		
		int count=sbTaskDaoObj.getSubTaskCountDao();
	    // TODO Auto-generated method stub
		return count;
	}

	
	
	public List<SubTaskBean> getSubTaskSer(int startPageIndex, int numRecordsPerPage) {
		// TODO Auto-generated method stub
	List<SubTaskBean>slist=sbTaskDaoObj.getSubTaskDao(startPageIndex,numRecordsPerPage);
		return slist;
	}
	
	public ArrayList<SubTaskBean> getSubTaskSer(int taskID) {
		// TODO Auto-generated method stub
		ArrayList<SubTaskBean> slist=sbTaskDaoObj.getSubTaskDao(taskID);
		return slist;
	}
	

	public boolean addSubTaskSer(SubTaskBean sbTaskBeanObj) {
		// TODO Auto-generated method stub
	boolean status=false;	
		status=sbTaskDaoObj.addSubTaskDao(sbTaskBeanObj);
		return status;
	}
	
	

	public List<ListBean> getCategoryListSer() {
	    
		CategoryMastDao   catgBeanDao=new CategoryMastDao();
	    List<ListBean> list=catgBeanDao.getCategoryListDao();
		return list; 
	}
	
			
	
	public boolean deleteSubTaskSer(int delId) {
        boolean status=false;
		status=sbTaskDaoObj.deleteSubTaskDao(delId);
	    return status;
	}
	

	public List<ListBean> getTaskListSer(int catgIds)
	    {
		
		TaskDao taskObj=new TaskDao(); 
		List<ListBean> listTask=taskObj.getTaskListDao(catgIds);
		return listTask; 
	   }
	
	
 }
