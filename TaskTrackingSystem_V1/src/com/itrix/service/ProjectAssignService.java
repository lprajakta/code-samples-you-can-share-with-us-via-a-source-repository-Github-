package com.itrix.service;

import java.util.List;
import com.itrix.dao.ProjectAssignDao;
import com.itrix.dao.UserMastDao;
import com.itrix.dao.ProjectMastDao;
import com.itrix.dao.TaskDao;
import com.itrix.model.ListBean;
import com.itrix.model.ProjectAssignBean;
import com.itrix.model.TaskBean;


public class ProjectAssignService {

	public boolean addProjectAssignSer(ProjectAssignBean projectassignBeanObj){	
	      boolean status=false;
	      ProjectAssignDao projectassignDaoObj=new ProjectAssignDao();
	      status=projectassignDaoObj.addProjectAssignDao(projectassignBeanObj);
	      return status;
	   }
	
	public List<ProjectAssignBean> getProjectAssignSer(int startPageIndex, int numRecordsPerPage)
	   {

		ProjectAssignDao projectassignDaoObj=new ProjectAssignDao();
		List <ProjectAssignBean>list=projectassignDaoObj.getProjectAssignDao(startPageIndex, numRecordsPerPage);	
		
	    return list; 
	}
	
	public int getProjectAssignsCountSer() {
		
		ProjectAssignDao projectassignDaoObj=new ProjectAssignDao();
		int projectassignCount=projectassignDaoObj.getProjectAssignCountDao();
		return projectassignCount;
	}  
	
	public boolean deleteProjectAssignSer(int delProjectAssignId)
	     {  boolean status=false;
	     ProjectAssignDao projectassignDaoObj=new ProjectAssignDao();
		    status=projectassignDaoObj.deleteProjectAssignDao(delProjectAssignId);
		    return status;
		
	     }
	
  public List<ListBean> getUserListSer() 
	    {
	     UserMastDao   userBeanDao=new UserMastDao();
		
		 List<ListBean> list=userBeanDao.getUserListDao();
		 return list; 
	    }
  
  
  public List<ListBean> getProjectListSer() 
  {
    ProjectMastDao   projectBeanDao=new ProjectMastDao();
	
	 List<ListBean> list=projectBeanDao.getProjectListDao();
	 return list; 
  }
  
   

public boolean updateProjectAssignSer(ProjectAssignBean projectassignBeanObj)
    {
	boolean status=false;
	ProjectAssignDao projectassignDaoObj=new ProjectAssignDao();
    status=projectassignDaoObj.updateProjectAssignDao(projectassignBeanObj);
    return status;
    }	
}


