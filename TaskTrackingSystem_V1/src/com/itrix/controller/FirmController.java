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

//import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
//import com.itrix.model.ContactModel;
import com.itrix.model.FirmModel;
//import com.itrix.service.ContactService;
import com.itrix.service.FirmService;

/**
 * Servlet implementation class CategoryMastController
 */
@WebServlet("/FirmController")
public class FirmController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    private FirmService firmSerObj; 
  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FirmController()
      {
    	firmSerObj=new FirmService();  
    
      }
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	        if(request.getParameter("action")!=null){
		    List<FirmModel> lFirmModelObj=new ArrayList<FirmModel>();
			String action=(String)request.getParameter("action");				
			Gson gson = new Gson();
			response.setContentType("application/json");
             
			 if(action.equals("list")){
			     	System.out.println("hiheloo");
			      try {
			        	int startPageIndex=Integer.parseInt(request.getParameter("jtStartIndex"));
			        	int numRecordsPerPage=Integer.parseInt(request.getParameter("jtPageSize"));
			        	int firmCount=firmSerObj.getFirmCountSer();
			        	String sname=request.getParameter("sname");
			        	//System.out.println("start index--"+startPageIndex+"jtPageSize--"+numRecordsPerPage);
			    	    //Fetch Data from CategotyMast Table
					    lFirmModelObj=firmSerObj.getFirmSer(startPageIndex, numRecordsPerPage,sname);			
				        //Convert Java Object to Json
				         JsonElement element = gson.toJsonTree(	lFirmModelObj, new TypeToken<List<FirmModel>>() {}.getType());
					     JsonArray jsonArray = element.getAsJsonArray();
					     String listData=jsonArray.toString();				
					     //Return Json in the format required by jTable plugin
					     //listData="{\"Result\":\"OK\",\"Records\":"+listData+",\totalRecordCount\":"+categoryCount+"}";			

                         listData= "{\"Result\":\"OK\",\"Records\":"+listData+",\"TotalRecordCount\":"+firmCount+"}";
					     response.getWriter().print(listData);
					   }
				  catch(Exception ex) {
						  String error="{\"Result\":\"ERROR\",\"Message\":"+ex.getMessage()+"}";
						  response.getWriter().print(error);
					      ex.printStackTrace();
				     }	
	            }

			 else if(action.equals("create") || action.equals("update")){
				 System.out.println("Create Firm >>>>> ");
				          FirmModel firmModelObj=new FirmModel();
				          if(request.getParameter("firmid")!=null){				   
					          int firmId=Integer.parseInt(request.getParameter("firmid"));
					          firmModelObj.setFirmid(firmId);;
					          System.out.println("F ID "+firmId);
					        }
				          if(request.getParameter("firmname")!=null){
							  String firmName=(String)request.getParameter("firmname");//catgName catgId catgDesc
							  firmModelObj.setFirmname(firmName);
						   }
				          if(request.getParameter("regid")!=null){				   
					          String regId=request.getParameter("regid");
					          firmModelObj.setRegid(regId);;
					        }
				          if(request.getParameter("address")!=null) { 
				               String address=(String)request.getParameter("address");
				               firmModelObj.setAddress(address);
						   }
				          if(request.getParameter("emailid")!=null) { 
				               String emailId=(String)request.getParameter("emailid");
				               firmModelObj.setEmailid(emailId);
						   }
				          if(request.getParameter("contactno")!=null) { 
				               long contactNo=Long.parseLong(request.getParameter("contactno"));
				              
				               firmModelObj.setContactno(contactNo);
						   }
				          if(request.getParameter("mobileno")!=null) { 
				              
				               long mobileNo=Long.parseLong((request.getParameter("mobileno")));
				               firmModelObj.setMobileno(mobileNo);
						   }
				          if(request.getParameter("remark")!=null) { 
				               String remark=request.getParameter("remark");
				               firmModelObj.setRemark(remark);
						   }
				         // if(request.getParameter("activestatus")!=null) { 
				               String actstatus="A";
				               firmModelObj.setActiveStatus(actstatus);
						  // }
			   try  {
				 		  if(action.equals("create")) {//Create new record
				 			 
						       firmSerObj.addFirmSer(firmModelObj);					
						       lFirmModelObj.add(firmModelObj);
						       //Convert Java Object to Json				
						       String json=gson.toJson(firmModelObj);					
						      //Return Json in the format required by jTable plugin
						       String listData="{\"Result\":\"OK\",\"Record\":"+json+"}";											
						       response.getWriter().print(listData);
					      }
					   else if(action.equals("update")){//Update existing record
						       firmSerObj.updateFirmSer(firmModelObj);
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
					    if(request.getParameter("firmid")!=null){
						  String firmId=(String)request.getParameter("firmid");
						  System.out.println(firmId);
						  firmSerObj.deleteFirmSer(Integer.parseInt(firmId));//
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

