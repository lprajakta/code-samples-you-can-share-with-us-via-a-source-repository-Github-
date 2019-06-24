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
import com.itrix.model.DocBean;
import com.itrix.model.FirmDoc;
import com.itrix.model.FirmModel;
import com.itrix.service.FirmDocService;
import com.itrix.service.FirmService;


@WebServlet("/FirmDocController")
public class FirmDocController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private FirmDocService firmDocSerObj;
	
	 public FirmDocController()
     {
   	firmDocSerObj=new FirmDocService();  
     }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		if(request.getParameter("action")!=null){
			List<FirmDoc> docBeansObj=new ArrayList<FirmDoc>();
			String action=(String)request.getParameter("action");
			Gson gson = new Gson();
			response.setContentType("application/json");
		
			if(action.equals("doclist")){
				 System.out.println("Insert");
			try{					        	
	       	docBeansObj = firmDocSerObj.getFirmDocSer(Integer.parseInt(request.getParameter("firmid")));
			//Convert Java Object to Json				
			JsonElement element = gson.toJsonTree(docBeansObj, new TypeToken<List<DocBean>>() {}.getType());
			JsonArray jsonArray = element.getAsJsonArray();
			String listData=jsonArray.toString();				
			//Return Json in the format required by jTable plugin
			listData="{\"Result\":\"OK\",\"Records\":"+listData+"}";			
			response.getWriter().print(listData);
			}catch(Exception ex){
				String error="{\"Result\":\"ERROR\",\"Message\":"+ex.getMessage()+"}";
				response.getWriter().print(error);
				ex.printStackTrace();
			}				
		}	
			 	
		else if(action.equals("update")){
			System.out.println("Create");
			FirmDoc firmDocobj=new FirmDoc();
			if(request.getParameter("docId")!=null){				   
			   int docid=Integer.parseInt(request.getParameter("docId"));
			  firmDocobj.setDocId(docid);
			}
			if(request.getParameter("firmid")!=null){				   
				   int firmid1=Integer.parseInt(request.getParameter("firmid"));
				  System.out.println("firm controller "+firmid1);
				   firmDocobj.setFirmid(firmid1);
				}
			if(request.getParameter("status")!=null){
				String status=request.getParameter("status");
				System.out.println(status);
				firmDocobj.setStatus(status);
			}
			
			
			if(request.getParameter("remark")!=null){
			   String remark=(String)request.getParameter("remark");
			   firmDocobj.setRemark(remark);
			}
			try{											
				if(action.equals("update")){//Update existing record
					firmDocSerObj.updateFirmDocSer(firmDocobj);
					String listData="{\"Result\":\"OK\"}";									
					response.getWriter().print(listData);
				}
			}catch(Exception ex){
					String error="{\"Result\":\"ERROR\",\"Message\":"+ex.getStackTrace().toString()+"}";
					response.getWriter().print(error);
			}
			}
			}
		}
	}
