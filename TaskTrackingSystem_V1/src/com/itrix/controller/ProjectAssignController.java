/*package com.itrix.controller;

public class ProjectAssignController {

}
*/

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
import com.itrix.model.ProjectAssignBean;
import com.itrix.service.ProjectAssignService;

/**
 * Servlet implementation class ProjectAssignController
 */
@WebServlet("/ProjectAssignController")
public class ProjectAssignController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ProjectAssignService projectassignSerObj;   
    public ProjectAssignController() {
    	projectassignSerObj=new ProjectAssignService(); 
  
          }

protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
                    if(request.getParameter("action")!=null){
				    List<ProjectAssignBean> lprojectassignBeanObj=new ArrayList<ProjectAssignBean>();
				    String action=(String)request.getParameter("action");				
					Gson gson = new Gson();
					response.setContentType("application/json");
			        if(action.equals("list")){
					     	
						
					try {
					    	    int startPageIndex=Integer.parseInt(request.getParameter("jtStartIndex"));
					            int numRecordsPerPage=Integer.parseInt(request.getParameter("jtPageSize"));
					        	int userCount=projectassignSerObj.getProjectAssignsCountSer();
					        	
					        	//Fetch Data from  Table
					        	lprojectassignBeanObj=projectassignSerObj.getProjectAssignSer(startPageIndex, numRecordsPerPage);
							    
					        	//Convert Java Object to Json
						        JsonElement element = gson.toJsonTree(	lprojectassignBeanObj, new TypeToken<List<ProjectAssignBean>>() {}.getType());
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
			
					 else if(action.equals("create") || action.equals("update")||action.equals("getUserList")||action.equals("getProjectList")){
						          
						 ProjectAssignBean projectassignBeanObj=new ProjectAssignBean();
						          if(request.getParameter("projassignId")!=null){				   
							          int projassignId=Integer.parseInt(request.getParameter("projassignId"));//	
							          projectassignBeanObj.setProjassignId(projassignId);
							          //System.out.println("projassignId=C=sssssssssssssssssssssssssssssssssssss======>"+projassignId);
							        }
						          
						          
						          if(request.getParameter("projId")!=null) { 
						               int  projId=Integer.parseInt(request.getParameter("projId"));
						               projectassignBeanObj.setProjId(projId);
						               System.out.println("projId=C=======>"+projId);
								    }
						         
						          if(request.getParameter("userId")!=null) { 
						               int  userId=Integer.parseInt(request.getParameter("userId"));
						               projectassignBeanObj.setUserId(userId);;
						               System.out.println("userId=C=======>"+userId);
								    }
						          
						          
					   try  {
						 		  if(action.equals("create")) {//Create new record
						 			 projectassignSerObj.addProjectAssignSer(projectassignBeanObj);					
								       lprojectassignBeanObj.add(projectassignBeanObj);
								       //Convert Java Object to Json				
								       String json=gson.toJson(projectassignBeanObj);					
								      //Return Json in the format required by jTable plugin
								       String listData="{\"Result\":\"OK\",\"Record\":"+json+"}";											
								       response.getWriter().print(listData);
							      }
							   else if(action.equals("update")){//Update existing record
								   
								       //System.out.println("===========PAC==11===============>");
								       projectassignSerObj.updateProjectAssignSer(projectassignBeanObj);
								       //System.out.println("===========PAC==22===============>");
								       String listData="{\"Result\":\"OK\"}";									
								       response.getWriter().print(listData);
								       //System.out.println("===========PAC==22===============>"+listData);
							     }
						        else if(action.equals("getUserList"))
						           {
						              List<ListBean> lUserBeanObj=new ArrayList<ListBean>();  
								      lUserBeanObj= projectassignSerObj.getUserListSer();
				                      JsonElement element = gson.toJsonTree(lUserBeanObj, new TypeToken<List<ListBean>>() {}.getType());
                                      JsonArray jsonArray = element.getAsJsonArray();
								     // System.out.println("json array"+jsonArray);
                                      String userlistData=jsonArray.toString();				
									  userlistData="{\"Result\":\"OK\",\"Options\":"+userlistData+"}";   
									  response.getWriter().print(userlistData);
								  } 
						        else if(action.equals("getProjectList"))
						           {
						              List<ListBean> lProjectBeanObj=new ArrayList<ListBean>();  
						              lProjectBeanObj= projectassignSerObj.getProjectListSer();
				                      JsonElement element = gson.toJsonTree(lProjectBeanObj, new TypeToken<List<ListBean>>() {}.getType());
                                      JsonArray jsonArray = element.getAsJsonArray();
								     // System.out.println("json array"+jsonArray);
                                      String projectlistData=jsonArray.toString();				
                                      projectlistData="{\"Result\":\"OK\",\"Options\":"+projectlistData+"}";   
									  response.getWriter().print(projectlistData);
								  }
						 		  
						 		  
						 		  
						}
					  catch(Exception ex) {
							   String error="{\"Result\":\"ERROR\",\"Message\":"+ex.getStackTrace().toString()+"}";
							   response.getWriter().print(error);
					        }
					}
					
			     else if(action.equals("delete")){//Delete record
						 try {
							    if(request.getParameter("projassignId")!=null){
								  String projassignId=(String)request.getParameter("projassignId");
								  projectassignSerObj.deleteProjectAssignSer(Integer.parseInt(projassignId));//
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


