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
import com.itrix.model.ListBean;
import com.itrix.model.TaskBean;
import com.itrix.service.TaskService;

/**
 * Servlet implementation class TaskController
 */
@WebServlet("/TaskController")
public class TaskController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private TaskService taskSerObj;   
    public TaskController() {
    taskSerObj=new TaskService(); 
  
          }

protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
                    if(request.getParameter("action")!=null){
				    List<TaskBean> ltaskBeanObj=new ArrayList<TaskBean>();
				    String action=(String)request.getParameter("action");				
					Gson gson = new Gson();
					response.setContentType("application/json");
			        if(action.equals("list")){
					     	
						
					try {
					    	    int startPageIndex=Integer.parseInt(request.getParameter("jtStartIndex"));
					            int numRecordsPerPage=Integer.parseInt(request.getParameter("jtPageSize"));
					        	int categoryCount=taskSerObj.getTasksCountSer();
					        	
					        	//Fetch Data from CategotyMast Table
					        	ltaskBeanObj=taskSerObj.getTaskSer(startPageIndex, numRecordsPerPage);
							    
					        	//Convert Java Object to Json
						        JsonElement element = gson.toJsonTree(	ltaskBeanObj, new TypeToken<List<TaskBean>>() {}.getType());
							    JsonArray jsonArray = element.getAsJsonArray();
							    String listData=jsonArray.toString();				
							     
							     //Return Json in the format required by jTable plugin
			                     listData= "{\"Result\":\"OK\",\"Records\":"+listData+",\"TotalRecordCount\":"+categoryCount+"}";
							     response.getWriter().print(listData);
							   }
						  catch(Exception ex) {
								  String error="{\"Result\":\"ERROR\",\"Message\":"+ex.getMessage()+"}";
								  response.getWriter().print(error);
							      ex.printStackTrace();
						     }	
			            }
			
					 else if(action.equals("create") || action.equals("update")||action.equals("getCatgList")){
						          
								  TaskBean taskBeanObj=new TaskBean();
						          if(request.getParameter("taskId")!=null){				   
							          int taskId=Integer.parseInt(request.getParameter("taskId"));
							          taskBeanObj.setTaskId(taskId);
							        }
						          if(request.getParameter("taskName")!=null){
									  String taskName=(String)request.getParameter("taskName");
									  taskBeanObj.setTaskName(taskName);
								   }
						          if(request.getParameter("taskDesc")!=null) { 
						               String taskDesc=(String)request.getParameter("taskDesc");
						               taskBeanObj.setTaskDesc(taskDesc);
								    } 
						          if(request.getParameter("catgId")!=null) { 
						               int  catgId=Integer.parseInt(request.getParameter("catgId"));
						                taskBeanObj.setCatgId(catgId);;
								    }
						          String actStatus="A";
					               taskBeanObj.setActiveStatus(actStatus);
					   try  {
						 		  if(action.equals("create")) {//Create new record
						 			   taskSerObj.addTaskSer(taskBeanObj);					
								       ltaskBeanObj.add(taskBeanObj);
								       //Convert Java Object to Json				
								       String json=gson.toJson(taskBeanObj);					
								      //Return Json in the format required by jTable plugin
								       String listData="{\"Result\":\"OK\",\"Record\":"+json+"}";											
								       response.getWriter().print(listData);
							      }
							   else if(action.equals("update")){//Update existing record
								       taskSerObj.updateTaskSer(taskBeanObj);
								       String listData="{\"Result\":\"OK\"}";									
								       response.getWriter().print(listData);
							     }
						        else if(action.equals("getCatgList"))
						           {
						              List<ListBean> lCatgBeanObj=new ArrayList<ListBean>();  
								      lCatgBeanObj= taskSerObj.getCategoryListSer();
				                      JsonElement element = gson.toJsonTree(lCatgBeanObj, new TypeToken<List<ListBean>>() {}.getType());
                                      JsonArray jsonArray = element.getAsJsonArray();
								     // System.out.println("json array"+jsonArray);
                                      String catglistData=jsonArray.toString();				
									  catglistData="{\"Result\":\"OK\",\"Options\":"+catglistData+"}";   
									  response.getWriter().print(catglistData);
								  } 
						}
					  catch(Exception ex) {
							   String error="{\"Result\":\"ERROR\",\"Message\":"+ex.getStackTrace().toString()+"}";
							   response.getWriter().print(error);
					        }
					}
					
			     else if(action.equals("delete")){//Delete record
						 try {
							    if(request.getParameter("taskId")!=null){
								  String taskId=(String)request.getParameter("taskId");
								  taskSerObj.deleteTaskSer(Integer.parseInt(taskId));//
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


