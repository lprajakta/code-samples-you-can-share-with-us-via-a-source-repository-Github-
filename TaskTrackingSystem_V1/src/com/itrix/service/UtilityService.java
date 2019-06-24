package com.itrix.service;

import java.util.ArrayList;

import com.itrix.dao.UtilityDao;
import com.itrix.model.CategoryBean;
import com.itrix.model.FirmModel;
import com.itrix.model.ProjectAssignBean;
import com.itrix.model.ProjectMastBean;
import com.itrix.model.ProjectSubTaskAssignBean;
import com.itrix.model.SubTaskBean;
import com.itrix.model.TaskBean;
import com.itrix.model.UserBean;

public class UtilityService {
	
	public ArrayList<FirmModel> getFirmDetailSer()
	   {

		UtilityDao utilDaoObj=new UtilityDao();
		ArrayList <FirmModel>list=utilDaoObj.getFirmDeatilsDao();	
	    return list; 
	}
	public ArrayList<FirmModel> getFirmInfoSer(int firmID)
	   {

		UtilityDao utilDaoObj=new UtilityDao();
		ArrayList <FirmModel>list=utilDaoObj.getFirmInfoSer(firmID);	
	    return list; 
	}
	public ArrayList<ProjectMastBean> getProjectsMastSer(int trustID)
	   {
		ArrayList<ProjectMastBean> prolist=null;
		try {
			UtilityDao promDaoObj=new UtilityDao();	
			 prolist = promDaoObj.getProjectsMastDao(trustID);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     return prolist;
	    }
	public ArrayList<ProjectMastBean> getProjectsMastInfoSer(int projID)
	   {
		ArrayList<ProjectMastBean> prolist=null;
		try {
			UtilityDao promDaoObj=new UtilityDao();	
			 prolist = promDaoObj.getProjectsMastInfoDao(projID);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     return prolist;
	    }
	public ArrayList<CategoryBean> getCategorySer(int projID) {
		UtilityDao CatgDaoObj=new UtilityDao();
		ArrayList <CategoryBean>list=CatgDaoObj.getCategoriesDao(projID);	
	    return list; 
	}
	public ArrayList<CategoryBean> getCategorySer() {
		UtilityDao CatgDaoObj=new UtilityDao();
		ArrayList <CategoryBean>list=CatgDaoObj.getCategoriesDao();	
	    return list; 
	}
	public ArrayList<TaskBean> getTaskSer(int catgID) {

	
		UtilityDao taskDaoObj=new UtilityDao();
		ArrayList <TaskBean>list=taskDaoObj.getTaskDao(catgID);	
		
	    return list; 
	}
	public ArrayList<SubTaskBean> getSubTaskSer(int taskID) {
		UtilityDao subtaskDaoObj=new UtilityDao();
		ArrayList<SubTaskBean>slist=subtaskDaoObj.getSubTaskDao(taskID);
		return slist;
	}
	public ArrayList<UserBean> getUserListSer() 
	    {
		UtilityDao  userBeanDao=new UtilityDao();
		
	     ArrayList<UserBean> list=userBeanDao.getUserListDao();
		 return list; 
	    }
	public ArrayList<ProjectMastBean> getProjectsMastInfoForEditSer(int projID) {
		ArrayList<ProjectMastBean> prolist=null;
		try {
			UtilityDao promDaoObj=new UtilityDao();	
			 prolist = promDaoObj.getProjectsMastInfoForEditDao(projID);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     return prolist;
	    }
	public ArrayList<ProjectAssignBean> getProjTaskDetails(int projID) {
		ArrayList<ProjectAssignBean> prolist=null;
		try {
			UtilityDao promDaoObj=new UtilityDao();	
			 prolist = promDaoObj.getProjTaskDetailsDao(projID);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     return prolist;
	    }
	public ArrayList<ProjectSubTaskAssignBean> getProjSubTaskDetails(int projID,int catgID ,int taskID) {
		ArrayList<ProjectSubTaskAssignBean> prolist=null;
		try {
			UtilityDao promDaoObj=new UtilityDao();	
			 prolist = promDaoObj.getProjSubTaskDetailsDao(projID,catgID , taskID);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     return prolist;
	    }
	public ArrayList<TaskBean> getCatgTask(int catgid) {
		ArrayList<TaskBean> tasklist=null;
		try {
			UtilityDao taskDaoObj=new UtilityDao();	
			tasklist = taskDaoObj.getCatgTask(catgid);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     return tasklist;
	    }
}
