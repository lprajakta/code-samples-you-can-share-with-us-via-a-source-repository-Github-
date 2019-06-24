package com.itrix.service;

import java.util.ArrayList;
import java.util.List;

import com.itrix.dao.CategoryMastDao;
import com.itrix.dao.FirmDao;
import com.itrix.dao.ProjectMastDao;
import com.itrix.dao.UserMastDao;
import com.itrix.model.ListBean;
import com.itrix.model.ProjectMastBean;
import com.itrix.model.ProjectSubTaskAssignBean;

public class ProjetcMastService {
	
	
	public List<ProjectMastBean> getProjectsMastSer(int startPage,int RecordperPage)
	   {
		 ProjectMastDao promDaoObj=new ProjectMastDao();	
	     List<ProjectMastBean>prolist=promDaoObj.getProjectsMastDao(startPage, RecordperPage);
	     return prolist;
	    }
	public int getProMastCountSer()
	  {		 
		ProjectMastDao promDaoObj=new ProjectMastDao();	
       int count= promDaoObj.getProMastCountDao();
	   return count;	
	  }
	public List<ListBean> getFirmListSer()
	  { 
		 FirmDao frmDaoObj=new FirmDao(); 
		 List<ListBean>list=frmDaoObj.getFirmListDao(); 
		return list;
	  }
	public boolean updateProjMastSer(ProjectMastBean proObj)
	   {  ProjectMastDao promDaoObj=new ProjectMastDao();
		boolean status=false;
		status=promDaoObj.updateProjMastDao(proObj);
		return status;
	   }
	
	public List<ListBean> getCategoryListSer()
	  {
		
		CategoryMastDao ctgMObj=new CategoryMastDao();
		List<ListBean>list=ctgMObj.getCategoryListDao();
		
		return list;
	  }
	
	
	public boolean deleteProjectMastSer(int deletId)
	  {
	boolean status=false;
	ProjectMastDao promDaoObj=new ProjectMastDao();
	status=promDaoObj.deleteProjectMastDao(deletId);
	return status;
	  }
	
	public boolean addProjMastSer(ProjectMastBean proBeanObj)
	    {  
		 ProjectMastDao promDaoObj=new ProjectMastDao();
		 boolean status=promDaoObj.addProjMastDao(proBeanObj);
		 return status;
	   }
	 public List<ListBean> getUserListSer() 
	    {
	     UserMastDao   userBeanDao=new UserMastDao();
				 List<ListBean> list=userBeanDao.getUserListDao();
		 return list; 
	    }
	 public ArrayList<ProjectMastBean> getAllProjectsMastSer()
	   {
		 ProjectMastDao promDaoObj=new ProjectMastDao();	
		 ArrayList<ProjectMastBean>prolist=promDaoObj.getAllProjectsMastDao();
	     return prolist;
	    }
	public boolean updtProjMastSer(ProjectMastBean proBeanObj) {  
		 ProjectMastDao updtProjMastObj=new ProjectMastDao();
		 boolean status=updtProjMastObj.updateProjMastDao(proBeanObj);
		 return status;
	   }
	public boolean UserUpdtProjMastSer(ProjectMastBean proBeanObj) {  
		 ProjectMastDao updtProjMastObj=new ProjectMastDao();
		 boolean status=updtProjMastObj.UserUpdateProjMastDao(proBeanObj);
		 return status;
	   }
	public boolean UserUpdtProjSubTaskMastSer(
			ArrayList<ProjectSubTaskAssignBean> projSubTaskAssignList) {  
		 ProjectMastDao updtProjMastObj=new ProjectMastDao();
		 boolean status=updtProjMastObj.UserUpdtProjSubTaskMastDao(projSubTaskAssignList);
		 return status;
	   }
}
