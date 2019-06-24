package com.itrix.service;

import java.util.List;
import com.itrix.dao.CategoryMastDao;
import com.itrix.dao.TaskDao;
import com.itrix.model.ListBean;
import com.itrix.model.TaskBean;


public class TaskService {

	public boolean addTaskSer(TaskBean taskBeanObj){	
	      boolean status=false;
	      TaskDao taskDaoObj=new TaskDao();
	      status=taskDaoObj.addTaskDao(taskBeanObj);
	      return status;
	   }
	
	public List<TaskBean> getTaskSer(int startPageIndex, int numRecordsPerPage)
	   {

		TaskDao taskDaoObj=new TaskDao();
		List <TaskBean>list=taskDaoObj.getTaskDao(startPageIndex, numRecordsPerPage);	
		
	    return list; 
	}
	
	public int getTasksCountSer() {
		
		TaskDao taskDaoObj=new TaskDao();
		int taskCount=taskDaoObj.getTaskCountDao();
		return taskCount;
	}  
	
	public boolean deleteTaskSer(int delTaskId)
	     {  boolean status=false;
		    TaskDao taskDaoObj=new TaskDao();
		    status=taskDaoObj.deleteTaskDao(delTaskId);
		    return status;
		
	     }
	
  public List<ListBean> getCategoryListSer()
	    {
	     CategoryMastDao   catgBeanDao=new CategoryMastDao();
		
		 List<ListBean> list=catgBeanDao.getCategoryListDao();
		 return list; 
	    }

public boolean updateTaskSer(TaskBean taskBeanObj)
    {
	boolean status=false;
    TaskDao taskDaoObj=new TaskDao();
    status=taskDaoObj.updateTaskDao(taskBeanObj);
    return status;
    }	
}


