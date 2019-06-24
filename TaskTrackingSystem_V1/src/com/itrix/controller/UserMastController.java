package com.itrix.controller;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import com.itrix.model.UserBean;
import com.itrix.service.UserMastService;


/**
 * Servlet implementation class UserMastController
 */
@WebServlet("/UserMastController")
public class UserMastController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserMastService userSerObj;   
    /**
     * @see HttpServlet#HttpServlet()2
     */
    public UserMastController() {
    	userSerObj=new UserMastService();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  if(request.getParameter("action")!=null){
		   //System.out.println("Servlet");
			    List<UserBean> lUserBeanObj=new ArrayList<UserBean>();
				String action=(String)request.getParameter("action");				
				Gson gson = new Gson();
				response.setContentType("application/json");
	             
				 if(action.equals("list")){
				     System.out.println("List");
				      try {
				        	int startPageIndex=Integer.parseInt(request.getParameter("jtStartIndex"));
				        	int numRecordsPerPage=Integer.parseInt(request.getParameter("jtPageSize"));
				        	int userCount=userSerObj.getUserCountSer();
				        	//System.out.println("start index--"+startPageIndex+"jtPageSize--"+numRecordsPerPage);
				    	    //Fetch Data from CategotyMast Table
						    lUserBeanObj=userSerObj.getUserSer(startPageIndex, numRecordsPerPage);			
					        //Convert Java Object to Json
					         JsonElement element = gson.toJsonTree(	lUserBeanObj, new TypeToken<List<UserBean>>() {}.getType());
						     JsonArray jsonArray = element.getAsJsonArray();
						     String listData=jsonArray.toString();				
						     //Return Json in the format required by jTable plugin
						     
	                         listData= "{\"Result\":\"OK\",\"Records\":"+listData+",\"TotalRecordCount\":"+userCount+"}";
						     response.getWriter().print(listData);
						   }
					  catch(Exception ex) {
							  String error="{\"Result\":\"ERROR\",\"Message\":"+ex.getMessage()+"}";
							  response.getWriter().print(error);
						      ex.printStackTrace();
					     }	
		            }

				 else if(action.equals("create") || action.equals("update")){
					 System.out.println("Create OR Update");
					          UserBean userBeanObj=new UserBean();
					          if(request.getParameter("userId")!=null){				   
						          int userId=Integer.parseInt(request.getParameter("userId"));
						          userBeanObj.setUserId(userId);
						        }
					          
					          if(request.getParameter("empId")!=null){				   
						          int empId=Integer.parseInt(request.getParameter("empId"));
						          userBeanObj.setEmpId(empId);
						        }
					          
					          if(request.getParameter("fname")!=null){
								  String fname=(String)request.getParameter("fname");
						          userBeanObj.setFname(fname);
							   }
					          if(request.getParameter("lname")!=null){
								  String lname=(String)request.getParameter("lname");
						          userBeanObj.setLname(lname);
							   }
					          if(request.getParameter("emailid")!=null){
								  String emailid=(String)request.getParameter("emailid");
						          userBeanObj.setEmailid(emailid);
							   }
					          if(request.getParameter("mobileno")!=null){
								  Long mobileno=Long.parseLong(request.getParameter("mobileno"));
						          userBeanObj.setMobileno(mobileno);
							   }
					          if(request.getParameter("username")!=null){
								  String username=(String)request.getParameter("username");
						          userBeanObj.setUsername(username);
							   }
					          if(request.getParameter("password")!=null) { 
					               String password=(String)request.getParameter("password");
								   userBeanObj.setPassword(password);
							   }
					          if(request.getParameter("userrole")!=null) { 
					               String userrole=(String)request.getParameter("userrole");
					               userBeanObj.setUserrole(userrole);
					              		   
							   }
					          if(request.getParameter("status")!=null) { 
					               String status=(String)request.getParameter("status");
					               userBeanObj.setStatus(status);
					               
					               
							   }
					          
				   try  {
					 		  if(action.equals("create")) {//Create new record
							      userSerObj.addUserSer(userBeanObj);					
							       lUserBeanObj.add(userBeanObj);
							       //Convert Java Object to Json				
							       String json=gson.toJson(userBeanObj);					
							      //Return Json in the format required by jTable plugin
							       String listData="{\"Result\":\"OK\",\"Record\":"+json+"}";											
							       response.getWriter().print(listData);
						      }
						   else if(action.equals("update")){//Update existing record
							      userSerObj.updateUserSer(userBeanObj);
							       String listData="{\"Result\":\"OK\"}";									
							       response.getWriter().print(listData);
						     }
					}
				  catch(Exception ex) {
						   String error="{\"Result\":\"ERROR\",\"Message\":"+ex.getStackTrace().toString()+"}";
						   response.getWriter().print(error);
				     }
				}
				
	          else if(action.equals("delete")){//Delete record
					 try {
						    if(request.getParameter("userId")!=null){
							  String userid=(String)request.getParameter("userId");
							  //System.out.println("userId: "+userid);
							 userSerObj.deleteUserSer(Integer.parseInt(userid));//
							  String listData="{\"Result\":\"OK\"}";								
							  response.getWriter().print(listData);
						    }
					    }
				    catch(Exception ex) {
					        String error="{\"Result\":\"ERROR\",\"Message\":"+ex.getStackTrace().toString()+"}";
					        response.getWriter().print(error);
					    }				

			 
		  }
	  	  
	   }
	}
}
