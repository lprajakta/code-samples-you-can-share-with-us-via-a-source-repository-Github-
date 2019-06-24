package com.itrix.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.itrix.model.FirmDoc;
import com.itrix.model.FirmModel;
import com.itrix.utility.DbUtil;

public class FirmDocDao 
{
private Connection conn=null;
	
	private ResourceBundle tblName = ResourceBundle.getBundle("dbTables");//load property file for table Name
	private ResourceBundle tblColumn=ResourceBundle.getBundle("firmDoc");//  load property file for table column
	private final String firmDoc=tblName.getString("firmDoc");// get table name
   
   /*firmmast table  column from firmmast properties file*/
    
	private final String firmid=tblColumn.getString("firmid");
	private final String docid=tblColumn.getString("docid");
	private final String status=tblColumn.getString("status");
	private final String details=tblColumn.getString("details");
	private final String docname=tblColumn.getString("docname");
	private final String remark=tblColumn.getString("remark");
	
	
public List<FirmDoc> getFirmDocDao(int firmid1) {
		
		List<FirmDoc> firmDocList=new ArrayList<FirmDoc>();
		
	    try {     
			    final String sql="SELECT f.firmid, d.docid, d.docname, d.details, f.status, f.remark FROM firmdoc f RIGHT OUTER JOIN docmast d ON d.docid = f.docid AND firmid ="+firmid1;
			    
			    conn=DbUtil.getConnection();
				PreparedStatement ps=conn.prepareStatement(sql);
				ResultSet rs=ps.executeQuery();
				while (rs.next()) { 
				  FirmDoc firmDocObj = new FirmDoc();
				  firmDocObj.setFirmid(rs.getInt(firmid));
				  firmDocObj.setDocId(rs.getInt(docid));
				  firmDocObj.setDetails(rs.getString(details));
				  firmDocObj.setDocName(rs.getString(docname));
				  if (rs.getString(status)==null) {
					  firmDocObj.setStatus("N");
				} else {
					firmDocObj.setStatus(rs.getString(status));
				}
				  
				  firmDocObj.setRemark(rs.getString(remark));
				  firmDocList.add(firmDocObj); 
				  
				}		        
			}  
	   catch (SQLException e) { 
		   e.printStackTrace();
	       }       
	       
	   catch(Exception e) {
			e.getStackTrace();
		 }	
	   System.err.println(firmDocList);
	  return firmDocList;
	}


public boolean updateFirmDocDao(FirmDoc firmDocObj) {
	 boolean status=false;
   
     try {
   	  
   	  String stats=firmDocObj.getStatus();
   	  int docIds=firmDocObj.getDocid();
   	  int firmIds=firmDocObj.getFirmid();
   	  System.out.println("status is "+stats);
   	  System.out.println("doc id is "+docIds);
   	  System.out.println("firm id is "+firmIds);
   	 if (firmDocObj.getRemark()!=null) {
			
		}
        final String sql="insert into firmdoc(docid, firmid, remark, status) values(?,?,?,?)";
      
       final String sql2="DELETE FROM firmdoc WHERE docid ="+docIds+" AND firmid ="+firmIds; 
       	conn=DbUtil.getConnection();
		    PreparedStatement pst=conn.prepareStatement(sql);
		    PreparedStatement pst1=conn.prepareStatement(sql2);
          pst.setInt(1, firmDocObj.getDocid());
		   pst.setInt(2, firmDocObj.getFirmid());
		   pst.setString(3, firmDocObj.getRemark());
		   pst.setString(4, firmDocObj.getStatus());
		   int i=0;
             if(stats.equals("Y")){
	              try{
	            	  i=pst1.executeUpdate();  
	                }
	              catch(SQLException e)
	                {
	            	 
	            	  
	                }
           	  i=pst.executeUpdate();
             
             }else if(stats.equals("N")) {
            	 i=pst1.executeUpdate();  
			}
	     
			if(i==1)
			{
				status = true;
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


	
}
