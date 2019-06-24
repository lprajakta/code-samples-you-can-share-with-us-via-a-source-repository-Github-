package com.itrix.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.itrix.model.CategoryBean;
import com.itrix.model.FirmModel;
import com.itrix.model.ProjectAssignBean;
import com.itrix.model.ProjectMastBean;
import com.itrix.model.ProjectSubTaskAssignBean;
import com.itrix.model.SubTaskBean;
import com.itrix.model.TaskBean;
import com.itrix.model.UserBean;
import com.itrix.service.UtilityService;
import com.itrix.utility.Conversion;

/**
 * Servlet implementation class AjaxUtilityController
 */
@WebServlet("/AjaxUtilityController")
public class AjaxUtilityController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxUtilityController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Conversion conversion=new Conversion();
		String reqType=request.getParameter("req_type");
		
		/************************get project id name in dropdown******************************/
		if (reqType.equals("GetFirmInfo") || reqType=="GetFirmInfo")
		{
			ArrayList<FirmModel> lfirmBeanObjList=new ArrayList<FirmModel>();
		
			try
			{
				int firmID=Integer.parseInt(request.getParameter("firm_Id"));
				UtilityService serviceObj = new UtilityService();
				lfirmBeanObjList =serviceObj.getFirmInfoSer(firmID);
				
						
				/******create json object and convert it into a string*******************/		    
				Gson gsonObject=new Gson();
				JsonElement jElement=gsonObject.toJsonTree(lfirmBeanObjList);
				JsonArray jArray=jElement.getAsJsonArray();
				
				JsonObject jObj=new JsonObject();
				jObj.add("JsonObjSiteDetailsList", jArray);				    
				String strObject=gsonObject.toJson(jObj);
				/******write json object string to response text*******************/
				response.getWriter().write(strObject);		
			}	
			catch (Exception e)
			{
				e.printStackTrace();
			} 		
		 }
		
		/************************get ******************************/
		if (reqType.equals("GetProjectInfo") || reqType=="GetProjectInfo")
		{
			ArrayList<ProjectMastBean> lProjBeanObjList=new ArrayList<ProjectMastBean>();
		
			try
			{
				int projID=Integer.parseInt(request.getParameter("proj_Id"));
				UtilityService serviceObj = new UtilityService();
				//lproBeanObjList =serviceObj.getProjectsMastSer(projID);
			
				lProjBeanObjList=serviceObj.getProjectsMastInfoSer(projID);			
						
				/******create json object and convert it into a string*******************/		    
				Gson gsonObject=new Gson();
				JsonElement jElement=gsonObject.toJsonTree(lProjBeanObjList);
				JsonArray jArray=jElement.getAsJsonArray();
				
				JsonObject jObj=new JsonObject();
				jObj.add("JsonProjectInfoList", jArray);				    
				String strObject=gsonObject.toJson(jObj);
				/******write json object string to response text*******************/
				response.getWriter().write(strObject);	
			}	
			catch (Exception e)
			{
				e.printStackTrace();
			} 		
		 }
		/************************get ******************************/
		if (reqType.equals("GetProjectInfoForEdit") || reqType=="GetProjectInfoForEdit")
		{
			ArrayList<ProjectMastBean> lProjBeanObjList=new ArrayList<ProjectMastBean>();
		
			try
			{
				int projID=Integer.parseInt(request.getParameter("proj_Id"));
				UtilityService serviceObj = new UtilityService();
				//lproBeanObjList =serviceObj.getProjectsMastSer(projID);
			
				lProjBeanObjList=serviceObj.getProjectsMastInfoForEditSer(projID);			
						
				/******create json object and convert it into a string*******************/		    
				Gson gsonObject=new Gson();
				JsonElement jElement=gsonObject.toJsonTree(lProjBeanObjList);
				JsonArray jArray=jElement.getAsJsonArray();
				
				JsonObject jObj=new JsonObject();
				jObj.add("JsonProjectInfoEditList", jArray);				    
				String strObject=gsonObject.toJson(jObj);
				/******write json object string to response text*******************/
				response.getWriter().write(strObject);	
			}	
			catch (Exception e)
			{
				e.printStackTrace();
			} 		
		 }
    		/************************get project id name in dropdown******************************/
    		if (reqType.equals("GetProjDetails") || reqType=="GetProjDetails")
    		{
    			ArrayList<ProjectMastBean> lproBeanObjList=new ArrayList<ProjectMastBean>();
    		
    			try
    			{
    				int trustID=Integer.parseInt(request.getParameter("trust_ID"));
    				UtilityService serviceObj = new UtilityService();
    				lproBeanObjList =serviceObj.getProjectsMastSer(trustID);
    				
    						
    				/******create json object and convert it into a string*******************/		    
    				Gson gsonObject=new Gson();
    				JsonElement jElement=gsonObject.toJsonTree(lproBeanObjList);
    				JsonArray jArray=jElement.getAsJsonArray();
    				
    				JsonObject jObj=new JsonObject();
    				jObj.add("JsonObjProjectList", jArray);				    
    				String strObject=gsonObject.toJson(jObj);
    				/******write json object string to response text*******************/
    				response.getWriter().write(strObject);		
    			}	
    			catch (Exception e)
    			{
    				e.printStackTrace();
    			} 		
    		 }
    		
    		/************************get categ id name in dropdown******************************/
    		if (reqType.equals("GetCatgDetails") || reqType=="GetCatgDetails")
    		{
    			ArrayList<CategoryBean> lCatgBeanObjList=new ArrayList<CategoryBean>();
    		
    			try
    			{
    				int projID=Integer.parseInt(request.getParameter("proj_Id"));
    				UtilityService serviceObj = new UtilityService();
    				//lproBeanObjList =serviceObj.getProjectsMastSer(projID);
    			
    				lCatgBeanObjList=serviceObj.getCategorySer(projID);			
    						
    				/******create json object and convert it into a string*******************/		    
    				Gson gsonObject=new Gson();
    				JsonElement jElement=gsonObject.toJsonTree(lCatgBeanObjList);
    				JsonArray jArray=jElement.getAsJsonArray();
    				
    				JsonObject jObj=new JsonObject();
    				jObj.add("JsonCatgList", jArray);				    
    				String strObject=gsonObject.toJson(jObj);
    				/******write json object string to response text*******************/
    				response.getWriter().write(strObject);	
    			}	
    			catch (Exception e)
    			{
    				e.printStackTrace();
    			} 		
    		 }
    		
    		/************************get task id name in dropdown******************************/
    		if (reqType.equals("GetTaskDetails") || reqType=="GetTaskDetails")
    		{
    			ArrayList<TaskBean> ltaskBeanObjList=new ArrayList<TaskBean>();
    		
    			try
    			{
    				int catgID=Integer.parseInt(request.getParameter("catg_Id"));
    				UtilityService serviceObj = new UtilityService();
    				  			
    				//Fetch Data from CategotyMast Table
		        	ltaskBeanObjList=serviceObj.getTaskSer(catgID);
    				/******create json object and convert it into a string*******************/		    
    				Gson gsonObject=new Gson();
    				JsonElement jElement=gsonObject.toJsonTree(ltaskBeanObjList);
    				JsonArray jArray=jElement.getAsJsonArray();
    				
    				JsonObject jObj=new JsonObject();
    				jObj.add("JsonTaskNameIdList", jArray);				    
    				String strObject=gsonObject.toJson(jObj);
    				/******write json object string to response text*******************/
    				response.getWriter().write(strObject);	
    			}	
    			catch (Exception e)
    			{
    				e.printStackTrace();
    			} 		
    		 }
    		/************************get Panel id name in dropdown******************************/
    		if (reqType.equals("getSubTaskDetails") || reqType=="getSubTaskDetails")
    		{
    			ArrayList<SubTaskBean> lsubtaskBeanObjList=new ArrayList<SubTaskBean>();
        		
    			try
    			{			
    				int taskID=Integer.parseInt(request.getParameter("task_Id"));
    				UtilityService serviceObj = new UtilityService();
    				  			
    				//Fetch Data from CategotyMast Table
    				lsubtaskBeanObjList=serviceObj.getSubTaskSer(taskID); 				
    				/******create json object and convert it into a string*******************/		    
    				Gson gsonObject=new Gson();
    				JsonElement jElement=gsonObject.toJsonTree(lsubtaskBeanObjList);
    				JsonArray jArray=jElement.getAsJsonArray();
    				
    				JsonObject jObj=new JsonObject();
    				jObj.add("JsonsubtaskList", jArray);				    
    				String strObject=gsonObject.toJson(jObj);
    				/******write json object string to response text*******************/
    				response.getWriter().write(strObject);		
    			}	
    			catch (Exception e)
    			{
    				e.printStackTrace();
    			}
    		}
    		
    		/************************get categ id name in dropdown******************************/
    		if (reqType.equals("GetAllCatgNameID") || reqType=="GetAllCatgNameID")
    		{
    			ArrayList<CategoryBean> lCatgBeanObjList=new ArrayList<CategoryBean>();
    		
    			try
    			{
    				
    				UtilityService serviceObj = new UtilityService();
    				//lproBeanObjList =serviceObj.getProjectsMastSer(projID);
    			
    				lCatgBeanObjList=serviceObj.getCategorySer();			
    						
    				/******create json object and convert it into a string*******************/		    
    				Gson gsonObject=new Gson();
    				JsonElement jElement=gsonObject.toJsonTree(lCatgBeanObjList);
    				JsonArray jArray=jElement.getAsJsonArray();
    				
    				JsonObject jObj=new JsonObject();
    				jObj.add("JsonCatgList", jArray);				    
    				String strObject=gsonObject.toJson(jObj);
    				/******write json object string to response text*******************/
    				response.getWriter().write(strObject);	
    			}	
    			catch (Exception e)
    			{
    				e.printStackTrace();
    			} 		
    		 }
    		
    		
    		/************************get user dropdown******************************/
    		if (reqType.equals("getUserInfo") || reqType=="getUserInfo")
    		{
    			ArrayList<UserBean> lUserBeanObjList=new ArrayList<UserBean>();
    		
    			try
    			{
    				
    				UtilityService serviceObj = new UtilityService();    				    			
    				lUserBeanObjList=serviceObj.getUserListSer();			
    						
    				/******create json object and convert it into a string*******************/		    
    				Gson gsonObject=new Gson();
    				JsonElement jElement=gsonObject.toJsonTree(lUserBeanObjList);
    				JsonArray jArray=jElement.getAsJsonArray();
    				
    				JsonObject jObj=new JsonObject();
    				jObj.add("JsonUserList", jArray);				    
    				String strObject=gsonObject.toJson(jObj);
    				/******write json object string to response text*******************/
    				response.getWriter().write(strObject);	
    			}	
    			catch (Exception e)
    			{
    				e.printStackTrace();
    			} 		
    		 }
    		
    		/************************get task id name in dropdown******************************/
    		if (reqType.equals("GetProjTaskDetails") || reqType=="GetProjTaskDetails")
    		{
    			ArrayList<ProjectAssignBean> ltaskBeanObjList=new ArrayList<ProjectAssignBean>();
    		
    			try
    			{
    				int projID=Integer.parseInt(request.getParameter("projID"));
    				UtilityService serviceObj = new UtilityService();
    				  			
    				//Fetch Data from CategotyMast Table
		        	ltaskBeanObjList=serviceObj.getProjTaskDetails(projID);
    				/******create json object and convert it into a string*******************/		    
    				Gson gsonObject=new Gson();
    				JsonElement jElement=gsonObject.toJsonTree(ltaskBeanObjList);
    				JsonArray jArray=jElement.getAsJsonArray();
    				
    				JsonObject jObj=new JsonObject();
    				jObj.add("JsonProTaskList", jArray);				    
    				String strObject=gsonObject.toJson(jObj);
    				/******write json object string to response text*******************/
    				response.getWriter().write(strObject);	
    			}	
    			catch (Exception e)
    			{
    				e.printStackTrace();
    			} 		
    		 }
    		/************************get task id name in dropdown******************************/
    		if (reqType.equals("GetProjSubTaskDetails") || reqType=="GetProjSubTaskDetails")
    		{
    			System.out.println("GetProjSubTaskDetails >>>>");
    			ArrayList<ProjectSubTaskAssignBean> ltaskBeanObjList=new ArrayList<ProjectSubTaskAssignBean>();
    		
    			try
    			{
    				int projID=Integer.parseInt(request.getParameter("projID"));
    				int catgID=Integer.parseInt(request.getParameter("catgID"));
    				int taskID=Integer.parseInt(request.getParameter("taskID"));
    				UtilityService serviceObj = new UtilityService();
    				  			
    				//Fetch Data from CategotyMast Table
		        	ltaskBeanObjList=serviceObj.getProjSubTaskDetails(projID,catgID,taskID);
    				/******create json object and convert it into a string*******************/		    
    				Gson gsonObject=new Gson();
    				JsonElement jElement=gsonObject.toJsonTree(ltaskBeanObjList);
    				JsonArray jArray=jElement.getAsJsonArray();
    				
    				JsonObject jObj=new JsonObject();
    				jObj.add("JsonProSubTaskList", jArray);				    
    				String strObject=gsonObject.toJson(jObj);
    				/******write json object string to response text*******************/
    				response.getWriter().write(strObject);	
    			}	
    			catch (Exception e)
    			{
    				e.printStackTrace();
    			} 		
    		 }
    		/************************load task based on category in edit project dropdown******************************/
    		if (reqType.equals("GetCatgTask") || reqType=="GetCatgTask")
    		{
    			//ArrayList<ProjectAssignBean> ltaskBeanObjList=new ArrayList<ProjectAssignBean>();
    			ArrayList<TaskBean> ltaskBeanObjList=new ArrayList<TaskBean>();
    			try
    			{
    				int catgid=Integer.parseInt(request.getParameter("catgid"));
    				UtilityService serviceObj = new UtilityService();
    				  			
    				//Fetch Data from CategotyMast Table
    				ltaskBeanObjList=serviceObj.getCatgTask(catgid);
    				/******create json object and convert it into a string*******************/		    
    				Gson gsonObject=new Gson();
    				JsonElement jElement=gsonObject.toJsonTree(ltaskBeanObjList);
    				JsonArray jArray=jElement.getAsJsonArray();
    				
    				JsonObject jObj=new JsonObject();
    				jObj.add("JsonTaskList", jArray);				    
    				String strObject=gsonObject.toJson(jObj);
    				/******write json object string to response text*******************/
    				response.getWriter().write(strObject);	
    			}	
    			catch (Exception e)
    			{
    				e.printStackTrace();
    			} 		
    		 }
	}

}
