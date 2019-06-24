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
import com.itrix.model.SubTaskBean;
import com.itrix.model.TaskBean;
import com.itrix.service.SubTaskService;

/**
 * Servlet implementation class SubTaskController
 */
@WebServlet("/SubTaskController")
public class SubTaskController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private SubTaskService sbTaskSerObj;   
    public SubTaskController() {
    sbTaskSerObj=new SubTaskService(); 
  
  }

protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
                    if(request.getParameter("action")!=null){
				    List<SubTaskBean> lSubTaskBeanObj=new ArrayList<SubTaskBean>();
				    String action=(String)request.getParameter("action");				
					Gson gson = new Gson();
					response.setContentType("application/json");
			        if(action.equals("list")){
					     	
						
					try {
					    	    int startPageIndex=Integer.parseInt(request.getParameter("jtStartIndex"));
					            int numRecordsPerPage=Integer.parseInt(request.getParameter("jtPageSize"));
					        	int categoryCount=sbTaskSerObj.getSubTasksCountSer();
					        	
					        	
					        	lSubTaskBeanObj=sbTaskSerObj.getSubTaskSer(startPageIndex, numRecordsPerPage);
							    
					        	//Convert Java Object to Json
						        JsonElement element = gson.toJsonTree(	lSubTaskBeanObj, new TypeToken<List<TaskBean>>() {}.getType());
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
			
					 else if(action.equals("create") || action.equals("update")||action.equals("getCatgList")||action.equals("getTaskList")){
						          
								  SubTaskBean sbTaskBeanObj=new SubTaskBean();
						          if(request.getParameter("subTaskId")!=null){				   
							          int subTaskId=Integer.parseInt(request.getParameter("subTaskId"));
							          sbTaskBeanObj.setTaskId(subTaskId);
							        }
						          if(request.getParameter("subTaskName")!=null){
									  String subTaskName=(String)request.getParameter("subTaskName");//taskId subTaskId subTaskName subDesc
									  sbTaskBeanObj.setSubTaskName(subTaskName);
								   }
						          if(request.getParameter("subDesc")!=null) { 
						               String subDesc=(String)request.getParameter("subDesc");
						               sbTaskBeanObj.setSubDesc(subDesc);
								    } 
						          
						          if(request.getParameter("taskId")!=null) { 
						               int  taskId=Integer.parseInt(request.getParameter("taskId"));
						               sbTaskBeanObj.setTaskId(taskId);
								    }
						          String activeStatus="A";
					               sbTaskBeanObj.setActiveStatus(activeStatus);;
					   try  {
						 		  if(action.equals("create")) {//Create new record
						 			   sbTaskSerObj.addSubTaskSer(sbTaskBeanObj);					
								       lSubTaskBeanObj.add(sbTaskBeanObj);
								       //Convert Java Object to Json				
								       String json=gson.toJson(sbTaskBeanObj);					
								      //Return Json in the format required by jTable plugin
								       String listData="{\"Result\":\"OK\",\"Record\":"+json+"}";											
								       response.getWriter().print(listData);
							      } 
							   else if(action.equals("update")){//Update existing record
								       sbTaskSerObj.addSubTaskSer(sbTaskBeanObj);
								       String listData="{\"Result\":\"OK\"}";									
								       response.getWriter().print(listData);
							     }
						        else if(action.equals("getCatgList"))
						           { 
						        	
						         
							
							          List<ListBean> lCatgBeanObj=new ArrayList<ListBean>();  
								      lCatgBeanObj= sbTaskSerObj.getCategoryListSer();
				                      JsonElement element = gson.toJsonTree(lCatgBeanObj, new TypeToken<List<ListBean>>() {}.getType());
                                      JsonArray jsonArray = element.getAsJsonArray();
								     // System.out.println("json array"+jsonArray);
                                      String catglistData=jsonArray.toString();				
									  catglistData="{\"Result\":\"OK\",\"Options\":"+catglistData+"}";   
									  response.getWriter().print(catglistData);
								  }
						 		  
						        else if(action.equals("getTaskList"))
						           {
						        	
						        	  if(request.getParameter("catgId")!=null) { 
							           int catgIds=Integer.parseInt(request.getParameter("catgId"));
							           List<ListBean> lCatgBeanObj=new ArrayList<ListBean>();  
								       lCatgBeanObj= sbTaskSerObj.getTaskListSer(catgIds);
				                       JsonElement element = gson.toJsonTree(lCatgBeanObj, new TypeToken<List<ListBean>>() {}.getType());
                                       JsonArray jsonArray = element.getAsJsonArray();
								       String catglistData=jsonArray.toString();				
									   catglistData="{\"Result\":\"OK\",\"Options\":"+catglistData+"}";   
									   response.getWriter().print(catglistData);
								  }
						       } 
						}
				  catch(Exception ex) {
							   String error="{\"Result\":\"ERROR\",\"Message\":"+ex.getStackTrace().toString()+"}";
							   response.getWriter().print(error);
					        }
					}
					
			     else if(action.equals("delete")){//Delete record
						 try {
							 
							 if(request.getParameter("subTaskId")!=null){				   
						          int subTaskId=Integer.parseInt(request.getParameter("subTaskId"));
						          sbTaskSerObj.deleteSubTaskSer(subTaskId);//
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
