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
import com.itrix.service.DocMastService;


@WebServlet("/DocMastController")
public class DocMastController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DocMastService docServObj;
	
	public DocMastController()
	{
		docServObj = new DocMastService();
	}
     @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
    }  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		if(request.getParameter("action")!=null){
		List<DocBean> docBeansObj=new ArrayList<DocBean>();
		String action=(String)request.getParameter("action");
		Gson gson = new Gson();
		response.setContentType("application/json");
	
		if(action.equals("list")){
			 System.out.println("Insert");
		try{
			int startPageIndex=Integer.parseInt(request.getParameter("jtStartIndex"));
        	int numRecordsPerPage=Integer.parseInt(request.getParameter("jtPageSize"));
        	docBeansObj = docServObj.getDocSer(startPageIndex, numRecordsPerPage);
        	String name = request.getParameter("name");
        	if(!name.isEmpty())
        	{
        		System.out.println("df");
        		docBeansObj = docServObj.getFilterSer(name);
        	}
		//Fetch Data from User Table
		//Convert Java Object to Json				
		JsonElement element = gson.toJsonTree(docBeansObj, new TypeToken<List<DocBean>>() {}.getType());
		JsonArray jsonArray = element.getAsJsonArray();
		System.out.println("sdhashd"+jsonArray);
		String listData=jsonArray.toString();				
		//Return Json in the format required by jTable plugin
		listData="{\"Result\":\"OK\",\"Records\":"+listData+"}";			
		response.getWriter().print(listData);
		System.out.println(listData);
		}catch(Exception ex){
			String error="{\"Result\":\"ERROR\",\"Message\":"+ex.getMessage()+"}";
			response.getWriter().print(error);
			ex.printStackTrace();
		}				
	}
	else if(action.equals("create") || action.equals("update")){
		System.out.println("Create");
		DocBean docBean=new DocBean();
		if(request.getParameter("docId")!=null){				   
		   int docid=Integer.parseInt(request.getParameter("docId"));
		  
		   docBean.setDocId(docid);
		}
		if(request.getParameter("docName")!=null){
			String docname=(String)request.getParameter("docName");
		
			docBean.setDocName(docname);
		}
		if(request.getParameter("details")!=null){
		   String details=(String)request.getParameter("details");
		  
		   docBean.setDetails(details);
		}
		 docBean.setDocStatus("A");
		/*if(request.getParameter("remark")!=null){
		   String remark=(String)request.getParameter("remark");
		 
		   docBean.setRemark(remark);
		}
		if(request.getParameter("docStatus")!=null){
			   String document=(String)request.getParameter("docStatus");
			   if(document.equals("Y"))
			   {
				   docBean.setDocStatus("Y");
			   }
			   if(document.equals("N"))
			   {
				   docBean.setDocStatus("N");
			   }
			   System.out.println(document);
			   
			}*/
		try{											
			if(action.equals("create")){//Create new record
				docServObj.addDocSer(docBean);
				docBeansObj.add(docBean);
				//Convert Java Object to Json				
				String json=gson.toJson(docBean);					
				//Return Json in the format required by jTable plugin
				String listData="{\"Result\":\"OK\",\"Record\":"+json+"}";											
				response.getWriter().print(listData);
			}else if(action.equals("update")){//Update existing record
				docServObj.updateDocSer(docBean);
				String listData="{\"Result\":\"OK\"}";									
				response.getWriter().print(listData);
			}
		}catch(Exception ex){
				String error="{\"Result\":\"ERROR\",\"Message\":"+ex.getStackTrace().toString()+"}";
				response.getWriter().print(error);
		}
	}else if(action.equals("delete")){//Delete record
		try{
			if(request.getParameter("docId")!=null){
				String docid=(String)request.getParameter("docId");
				docServObj.deleteDocServ(Integer.parseInt(docid));
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

