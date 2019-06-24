package com.itrix.dao;

//import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.itrix.model.FirmModel;
import com.itrix.model.ListBean;
import com.itrix.utility.DbUtil;

public class FirmDao {
   
private Connection conn=null;
	
	private ResourceBundle tblName = ResourceBundle.getBundle("dbTables");//load property file for table Name
	private ResourceBundle tblColumn=ResourceBundle.getBundle("firmMast");//  load property file for table column
	private final String firmMast=tblName.getString("firmMast");// get table name
   
   /*firmmast table  column from firmmast properties file*/
    
	private final String firmid=tblColumn.getString("firmid");
	private final String firmname=tblColumn.getString("firmname");
	private final String regid=tblColumn.getString("regid");
	private final String address=tblColumn.getString("address");
	private final String emailid=tblColumn.getString("emailid");
	private final String contactno=tblColumn.getString("contactno");
	private final String mobileno=tblColumn.getString("mobileno");
	private final String remark=tblColumn.getString("remark");

	
	public boolean addFirmDao(FirmModel firmModelObj) {
		boolean status=false;
		
        try {
  	        final String sql="insert into "+firmMast+" values(?,?,?,?,?,?,?,?,?)";
  	        conn=DbUtil.getConnection();
			    PreparedStatement ps=conn.prepareStatement(sql);
			    ps.setInt(1,0);
			    ps.setString(2,firmModelObj.getFirmname());
		   		ps.setString(3, firmModelObj.getRegid());
		   		ps.setString(4,firmModelObj.getAddress());
		   		ps.setString(5, firmModelObj.getEmailid());
		   		ps.setLong(6, firmModelObj.getContactno());
		   		ps.setLong(7,firmModelObj.getMobileno());
		   		ps.setString(8, firmModelObj.getRemark());
		   		ps.setString(9, firmModelObj.getActiveStatus());
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


	public List<FirmModel> getFirmDao(int startPageIndex, int numRecordsPerPage, String sname) {
		
		List<FirmModel> firmList=new ArrayList<FirmModel>();
		
	    try {     
	    	    final String startPageIndexs=Integer.toString(startPageIndex);
	            final String PageSize=Integer.toString(numRecordsPerPage);
	            
			    final String sql="select * from "+firmMast+" where firmname LIKE'"+sname+"%' limit "+startPageIndexs+","+PageSize;
			   
			    conn=DbUtil.getConnection();
				PreparedStatement ps=conn.prepareStatement(sql);
				ResultSet rs=ps.executeQuery();
				
				while (rs.next()) { 
			    
				  FirmModel firmModelObj = new FirmModel();
				  firmModelObj.setFirmid(rs.getInt(firmid));
				  firmModelObj.setFirmname(rs.getString(firmname));
				  firmModelObj.setRegid(rs.getString(regid));
				  firmModelObj.setEmailid(rs.getString(emailid));
				  firmModelObj.setAddress(rs.getString(address));
				  firmModelObj.setMobileno(rs.getLong(mobileno));
				  firmModelObj.setContactno(rs.getLong(contactno));
				  firmModelObj.setRemark(rs.getString(remark));
				  firmList.add(firmModelObj); 
				
				}		        
			}  
	   catch (SQLException e) { 
		   e.printStackTrace();
	       }       
	       
	   catch(Exception e) {
			e.getStackTrace();
		 }	
		  	  

	  return firmList;

	}


	public void updateFirmDao(FirmModel firmModelObj) {
		
         
           try {
                final String sql="update "+firmMast+" set "+firmname+"=?,"+regid+"=?,"+address+"=?,"+emailid+"=?,"+contactno+"=?,"+mobileno+"=?,"+remark+"=? where "+firmid+"=?";
	        	   
	        	conn=DbUtil.getConnection();
			    PreparedStatement pst=conn.prepareStatement(sql);
			    pst.setString(1, firmModelObj.getFirmname());
				pst.setString(2,firmModelObj.getRegid());
				pst.setString(3,firmModelObj.getAddress());
				pst.setString(4,firmModelObj.getEmailid());
				pst.setLong(5,firmModelObj.getContactno());
				pst.setLong(6,firmModelObj.getMobileno());
				pst.setString(7,firmModelObj.getRemark());
				pst.setInt(8,firmModelObj.getFirmid());
				
 		        pst.executeUpdate();
            } 
 		catch (SQLException e) 
 		     {
 			  e.printStackTrace();
 		     }
 		catch(Exception e){
 			  e.getStackTrace();
 		   }


		
	}


	public boolean deleteFirmDao(int firmId) {
		boolean status=false;
	  	
  	    try {
	        	   final String sql="delete from "+firmMast+ " where  "+firmid+"=?";
	        	   conn=DbUtil.getConnection();
				   PreparedStatement ps=conn.prepareStatement(sql);
				   ps.setInt(1, firmId);
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


	public int getFirmCountDao() {
		int firmCount=0;
	    try {  
	 
	    final String sql="select count(*) as count from "+firmMast;
	  
	    conn=DbUtil.getConnection();
		PreparedStatement ps=conn.prepareStatement(sql);
		ResultSet rs=ps.executeQuery();
		
		while (rs.next()) { 
	    
			firmCount=rs.getInt("count");
		
		}		        
	}  
	catch (SQLException e) { 
	e.printStackTrace();
	}       

	catch(Exception e) {
	e.getStackTrace();
	}	
		
	    return firmCount;
	}
	
	//nbs
	public List<ListBean> getFirmListDao() 
	{	
		    List< ListBean> firmList=new ArrayList< ListBean>();
			
		    try {   
				    final String sql="select "+firmid+","+firmname+" from "+firmMast;
				    conn=DbUtil.getConnection();
					PreparedStatement ps=conn.prepareStatement(sql);
					ResultSet rs=ps.executeQuery();
					
					while (rs.next()) { 
				    
						 ListBean firmBeanObj= new  ListBean();
					     //Integer v=(rs.getInt(firmIdc));
					     Integer v=(rs.getInt(firmid));
					     String vv=v.toString();
			             firmBeanObj.setValue(vv);
					     //String ss=rs.getString(firmNamec);
					     String ss=rs.getString(firmname);
			             firmBeanObj.setDisplayText(ss);
					     firmList.add(firmBeanObj); 
					    //System.out.println(rs.getString(catgName));
			          }		        
				}  
		   catch (SQLException e) { 
			   e.printStackTrace();
		       }       
		    
		       
		   catch(Exception e) {
				e.getStackTrace();
			 }
         return   firmList;
       }
	
 //nbs    
		
	}


