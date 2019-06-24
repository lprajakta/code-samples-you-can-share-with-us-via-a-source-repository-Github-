package com.itrix.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FirmController
 */


import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import com.itrix.model.ContactModel;
import com.itrix.model.FirmModel;
import com.itrix.service.ContactService;


/**
 * Servlet implementation class CategoryMastController
 */
@WebServlet("/ContactController")
public class ContactController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    private ContactService contactSerObj;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ContactController()
      {
    	contactSerObj=new ContactService();  
      }
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	        if(request.getParameter("action")!=null){
		    List<ContactModel> lContactModelObj=new ArrayList<ContactModel>();
			String action=(String)request.getParameter("action");				
			Gson gson = new Gson();
			response.setContentType("application/json");
             
			 if(action.equals("phonelist")){
				 
			      try {
			    	 
			        	int contactCount=contactSerObj.getContactCountSer();
			        	 //Fetch Data from CategotyMast Table
			        	lContactModelObj=contactSerObj.getContactSer(Integer.parseInt(request.getParameter("firmid")));			
				        //Convert Java Object to Json
				         JsonElement element = gson.toJsonTree(	lContactModelObj, new TypeToken<List<FirmModel>>() {}.getType());
					     JsonArray jsonArray = element.getAsJsonArray();
					     String listData=jsonArray.toString();				
                         listData= "{\"Result\":\"OK\",\"Records\":"+listData+",\"TotalRecordCount\":"+contactCount+"}";
					     response.getWriter().print(listData);
					   }
				  catch(Exception ex) {
						  String error="{\"Result\":\"ERROR\",\"Message\":"+ex.getMessage()+"}";
						  response.getWriter().print(error);
					      ex.printStackTrace();
				     }	
	            }

			 else if(action.equals("phonecreate") || action.equals("phoneupdate")){
				          ContactModel contactModelObj=new ContactModel();
				          if(request.getParameter("contactid")!=null){				   
					          int contactid=Integer.parseInt(request.getParameter("contactid"));
					          contactModelObj.setContactid(contactid);
					        
					        }
				          if(request.getParameter("firmid")!=null){				   
					          int firmid=Integer.parseInt(request.getParameter("firmid"));
					          contactModelObj.setFirmid(firmid);
					         
					        }
				          if(request.getParameter("contactname")!=null){
							  String contactname=(String)request.getParameter("contactname");
							  contactModelObj.setContactname(contactname);
				          }
				          if(request.getParameter("emailid")!=null) { 
				               String emailId=(String)request.getParameter("emailid");
				               contactModelObj.setEmailid(emailId);
				          }
				          if(request.getParameter("mobileno")!=null) { 
				              
				               long mobileNo=Long.parseLong(request.getParameter("mobileno"));
				               contactModelObj.setMobileno(mobileNo);
						   }
				          if (request.getParameter("designation")!=null) {
							String designation=(String)request.getParameter("designation");
							contactModelObj.setDesignation(designation);
						}
				          if(request.getParameter("remark")!=null) { 
				               String remark=request.getParameter("remark");
				               contactModelObj.setRemark(remark);
						   }
				          String actStatus="A";
			               contactModelObj.setActiveStatus(actStatus);
			   try  {
				 		  if(action.equals("phonecreate")) {//Create new record
						       contactSerObj.addContactSer(contactModelObj);					
						       lContactModelObj.add(contactModelObj);
						       //Convert Java Object to Json				
						       String json=gson.toJson(contactModelObj);					
						      //Return Json in the format required by jTable plugin
						       String listData="{\"Result\":\"OK\",\"Record\":"+json+"}";											
						       response.getWriter().print(listData);
					      }
					   else if(action.equals("phoneupdate")){//Update existing record
						       contactSerObj.updateConatactSer(contactModelObj);
						       String listData="{\"Result\":\"OK\"}";									
						       response.getWriter().print(listData);
					     }
				}
			  catch(Exception ex) {
					   String error="{\"Result\":\"ERROR\",\"Message\":"+ex.getStackTrace().toString()+"}";
					   response.getWriter().print(error);
			     }
			}
			 
			
          else if(action.equals("phonedelete")){//Delete record
				 try {
					    if(request.getParameter("contactid")!=null){
						  String contactId=(String)request.getParameter("contactid");
						  contactSerObj.deleteCaontactSer(Integer.parseInt(contactId));//
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

