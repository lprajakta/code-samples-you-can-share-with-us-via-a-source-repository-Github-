package com.itrix.service;

import java.util.List;



import com.itrix.dao.UserMastDao;
import com.itrix.model.UserBean;

public class UserMastService {
	public boolean addUserSer(UserBean userBeanObj){	
	      boolean status=false;
	      UserMastDao UserDaoObj=new UserMastDao();
	      status=UserDaoObj.addUserDao(userBeanObj);
	      return status;
	   }
	 
	public List<UserBean> getUserSer(int startPageIndex, int numRecordsPerPage)
	   {

		UserMastDao UserDaoObj=new UserMastDao();
		List <UserBean>list=UserDaoObj.getUserDao(startPageIndex, numRecordsPerPage);	
	    return list; 
	}
	public boolean updateUserSer(UserBean userBeanObj) {
		boolean status=false;
		// TODO Auto-generated method stub
		UserMastDao UserDaoObj=new UserMastDao();
		UserDaoObj.updateUserDao(userBeanObj);
	    return status;
	 }

	public boolean deleteUserSer(int empId) {
	   boolean status=false;
	  UserMastDao UserDaoObj=new UserMastDao();   
	   status=UserDaoObj.deleteUserDao(empId);	
	   return status;
	  }

	public int getUserCountSer() {
		// TODO Auto-generated method stub
		UserMastDao UserDaoObj=new UserMastDao();
		int userCount=UserDaoObj.getUserCountDao();
		return userCount;
	}    

	   

}
