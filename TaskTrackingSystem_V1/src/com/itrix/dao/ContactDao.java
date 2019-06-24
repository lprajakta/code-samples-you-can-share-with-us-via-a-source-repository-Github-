package com.itrix.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.itrix.model.ContactModel;
import com.itrix.utility.DbUtil;

public class ContactDao {
   
private Connection conn=null;
	
	private ResourceBundle tblName = ResourceBundle.getBundle("dbTables");//load property file for table Name
	private ResourceBundle tblColumn=ResourceBundle.getBundle("contactMast");//  load property file for table column
	private final String contactMast=tblName.getString("contactMast");// get table name
   
   /*contactmmast table  column from contactmast properties file*/
    
	private final String contactid=tblColumn.getString("contactid");
	private final String firmid=tblColumn.getString("firmid");
	private final String contactname=tblColumn.getString("contactname");
	private final String emailid=tblColumn.getString("emailid");
	private final String mobileno=tblColumn.getString("mobileno");
	private final String designation=tblColumn.getString("designation");
	private final String remark=tblColumn.getString("remark");

	
	public boolean addContactDao(ContactModel contactModelobj) {
		boolean status=false;
		
        try {
  	        final String sql="insert into "+contactMast+" values(?,?,?,?,?,?,?,?)";
  	        conn=DbUtil.getConnection();
			    PreparedStatement ps=conn.prepareStatement(sql);
			    ps.setInt(1,0);
			    ps.setInt(2, contactModelobj.getFirmid());
			    ps.setString(3,contactModelobj.getContactname());
		   		ps.setString(4, contactModelobj.getEmailid());
		   		ps.setLong(5,contactModelobj.getMobileno());
		   		ps.setString(6, contactModelobj.getDesignation());
		   		ps.setString(7, contactModelobj.getRemark());
		   		ps.setString(8, contactModelobj.getActiveStatus());
		        int i=ps.executeUpdate();
		        if(i==1) {
		        status=true;
		         } 	      
           }	 
       catch(SQLException ee) {
      	    ee.getStackTrace();
           }
       catch (Exception e) {
      	   e.printStackTrace();
		     }    	 
      return status;

	}


	public List<ContactModel> getContactDao(int firmid1) {
		
		List<ContactModel> contactList=new ArrayList<ContactModel>();
		
	    try {     
	    	   
			    final String sql="select * from "+contactMast+" where firmid="+firmid1;

			    conn=DbUtil.getConnection();
				PreparedStatement ps=conn.prepareStatement(sql);
				ResultSet rs=ps.executeQuery();
				
				while (rs.next()) { 
			    
				  ContactModel contactModelobj = new ContactModel();
				  contactModelobj.setContactid(rs.getInt(contactid));
				  contactModelobj.setFirmid(rs.getInt(firmid));
				  contactModelobj.setContactname(rs.getString(contactname));
				  contactModelobj.setEmailid(rs.getString(emailid));
				  contactModelobj.setMobileno(rs.getLong(mobileno));
				  contactModelobj.setDesignation(rs.getString(designation));
				  contactModelobj.setRemark(rs.getString(remark));
				  contactList.add(contactModelobj);  
				}		        
			}  
	   catch (SQLException e) { 
		   e.printStackTrace();
	       }       
	       
	   catch(Exception e) {
			e.getStackTrace();
		 }	
		  	  

	  return contactList;

	}


	 public boolean updateContactDao(ContactModel contactModelobj) {

		 boolean status=false;
         
           try {
                final String sql="update "+contactMast+" set "+contactname+"=?,"+emailid+"=?,"+mobileno+"=?,"+designation+"=?,"+remark+"=? where "+contactid+"=?";
	        	
	        	conn=DbUtil.getConnection();
			    PreparedStatement pst=conn.prepareStatement(sql);
			   
			    pst.setString(1,contactModelobj.getContactname());
		   		pst.setString(2, contactModelobj.getEmailid());
		   		pst.setLong(3,contactModelobj.getMobileno());
		   		pst.setString(4, contactModelobj.getDesignation());
		   		pst.setString(5, contactModelobj.getRemark());
		   		pst.setLong(6,contactModelobj.getContactid());

		   		int i=pst.executeUpdate();
		   	    
 		       if(i==1) {
				   status=true;
				   } 
 		        
            } 
 		catch (SQLException e) 
 		     {
 			  e.printStackTrace();
 		     }
 		catch(Exception e){
 			  e.getStackTrace();
 		   }

           return status;	
		
	}
	public boolean deleteContactDao(int contactid1) {
		boolean status=false;
	  	
  	    try {
	        	   final String sql="delete from "+contactMast+ " where  "+contactid+"=?";
	        	  
	        	   conn=DbUtil.getConnection();
				   PreparedStatement ps=conn.prepareStatement(sql);
				   ps.setInt(1, contactid1);
				   int i=ps.executeUpdate();
				   
				   if(i==1) {
				   status=true;
				   } 
  	       } 	 
  	   catch(SQLException e){
	       e.getStackTrace();
           }      
      catch (Exception e) {
	       e.getStackTrace();
           }  	 
       return status;	
	}


	public int getContactCountDao() {
		int contactCount=0;
	    try {  
	 
	    final String sql="select count(*) as count from "+contactMast;
	   
	    conn=DbUtil.getConnection();
		PreparedStatement ps=conn.prepareStatement(sql);
		ResultSet rs=ps.executeQuery();
		
		while (rs.next()) { 
	    
			contactCount=rs.getInt("count");
		
		}		        
	}  
	catch (SQLException e) { 
	e.printStackTrace();
	}       

	catch(Exception e) {
	e.getStackTrace();
	}	
		
	    return contactCount;
	}
	

	}


