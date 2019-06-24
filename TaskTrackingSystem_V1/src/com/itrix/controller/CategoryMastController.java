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
import com.itrix.model.CategoryBean;
import com.itrix.service.CategoryMastService;

/**
 * Servlet implementation class CategoryMastController
 */
@WebServlet("/CategoryMastController")
public class CategoryMastController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    private CategoryMastService catgSerObj;   
    /**
     * @see HttpServlet#HttpServlet()
     */
		    public CategoryMastController()
		      {
		    	catgSerObj=new CategoryMastService();  
		      }
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	        if(request.getParameter("action")!=null){
		    List<CategoryBean> lCatgBeanObj=new ArrayList<CategoryBean>();
			String action=(String)request.getParameter("action");				
			Gson gson = new Gson();
			response.setContentType("application/json");
             
			 if(action.equals("list")){
			     	
			      try {
			        	int startPageIndex=Integer.parseInt(request.getParameter("jtStartIndex"));
			        	int numRecordsPerPage=Integer.parseInt(request.getParameter("jtPageSize"));
			        	int categoryCount=catgSerObj.getCategoryCountSer();
			        	//System.out.println("start index--"+startPageIndex+"jtPageSize--"+numRecordsPerPage);
			    	    //Fetch Data from CategotyMast Table
					    lCatgBeanObj=catgSerObj.getCategorySer(startPageIndex, numRecordsPerPage);			
				        //Convert Java Object to Json
				         JsonElement element = gson.toJsonTree(	lCatgBeanObj, new TypeToken<List<CategoryBean>>() {}.getType());
					     JsonArray jsonArray = element.getAsJsonArray();
					     String listData=jsonArray.toString();				
					     //Return Json in the format required by jTable plugin
					     //listData="{\"Result\":\"OK\",\"Records\":"+listData+",\totalRecordCount\":"+categoryCount+"}";			

                         listData= "{\"Result\":\"OK\",\"Records\":"+listData+",\"TotalRecordCount\":"+categoryCount+"}";
					     response.getWriter().print(listData);
					   }
				  catch(Exception ex) {
						  String error="{\"Result\":\"ERROR\",\"Message\":"+ex.getMessage()+"}";
						  response.getWriter().print(error);
					      ex.printStackTrace();
				     }	
	            }

			 else if(action.equals("create") || action.equals("update")){
				          CategoryBean catgBeanObj=new CategoryBean();
				          if(request.getParameter("catgId")!=null){				   
					          int catgId=Integer.parseInt(request.getParameter("catgId"));
					          catgBeanObj.setCatgId(catgId);
					        }
				          if(request.getParameter("catgName")!=null){
							  String catgName=(String)request.getParameter("catgName");//catgName catgId catgDesc
					          catgBeanObj.setCatgName(catgName);
						   }
				          if(request.getParameter("catgDesc")!=null) { 
				               String catgDesc=(String)request.getParameter("catgDesc");
							   catgBeanObj.setCatgDesc(catgDesc);
						   }
				          String actstatus="A";
						   catgBeanObj.setActiveStatus(actstatus);
			   try  {
				 		  if(action.equals("create")) {//Create new record
						       catgSerObj.addCategorySer(catgBeanObj);					
						       lCatgBeanObj.add(catgBeanObj);
						       //Convert Java Object to Json				
						       String json=gson.toJson(catgBeanObj);					
						      //Return Json in the format required by jTable plugin
						       String listData="{\"Result\":\"OK\",\"Record\":"+json+"}";											
						       response.getWriter().print(listData);
					      }
					   else if(action.equals("update")){//Update existing record
						       catgSerObj.updateCategorySer(catgBeanObj);
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
					    if(request.getParameter("catgId")!=null){
						  String catgId=(String)request.getParameter("catgId");
						  catgSerObj.deleteCategorySer(Integer.parseInt(catgId));//
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
