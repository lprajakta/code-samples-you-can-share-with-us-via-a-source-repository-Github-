package com.itrix.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import com.itrix.model.SubTaskBean;
import com.itrix.utility.DbUtil;

public class SubTaskDao {

	private Connection conn=null;
	
	private ResourceBundle tblName = ResourceBundle.getBundle("dbTables");//load property file for table Name
	private ResourceBundle tblColumn=ResourceBundle.getBundle("subtaskc");//  load property file for table column
	private final String subTaskMast=tblName.getString("subtask");// get table name
   
   /*task mast table  column from task mast properties file*/
	 
	private final String subTaskIdc=tblColumn.getString("subtaskid");//subTaskIdc,sbTaskNamec,taskDesc,taskIdc subTaskMast
	private final String sbTaskNamec=tblColumn.getString("name");
	private final String taskDesc=tblColumn.getString("description");
	private final String taskIdc=tblColumn.getString("taskid");
	

	  public boolean addSubTaskDao(SubTaskBean sbTaskBeanObj)
	              {
	         		 boolean status=false;											
		
		              try {
		        	        final String sql="insert into "+subTaskMast+" values(?,?,?,?,?)";
		        	        
		        	        conn=DbUtil.getConnection();
						    PreparedStatement ps=conn.prepareStatement(sql);
						    ps.setInt(1,0);
						    ps.setInt(2,sbTaskBeanObj.getTaskId());
						    ps.setString(3,sbTaskBeanObj.getSubTaskName());
						    ps.setString(4,sbTaskBeanObj.getSubDesc());
						    ps.setString(5,sbTaskBeanObj.getActiveStatus());
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

	
	public List<SubTaskBean> getSubTaskDao(int startPageIndex, int numRecordsPerPage) {
	

	    List<SubTaskBean> taskList=new ArrayList<SubTaskBean>();
	    try {   
	    	    final String startPageIndexs=Integer.toString(startPageIndex);
	            final String PageSize=Integer.toString(numRecordsPerPage);
			    final String sql="select * from "+subTaskMast+" limit "+startPageIndexs+","+PageSize;//subTaskIdc,sbTaskNamec,taskDesc,taskIdc subTaskMast
			  
			    conn=DbUtil.getConnection();
				PreparedStatement ps=conn.prepareStatement(sql);
				ResultSet rs=ps.executeQuery();
				
				while (rs.next()) { 
			     SubTaskBean sbTaskBeanObj = new    SubTaskBean();
				 sbTaskBeanObj.setSubTaskId(rs.getInt(subTaskIdc));
				 sbTaskBeanObj.setSubTaskName(rs.getString(sbTaskNamec));
				 sbTaskBeanObj.setTaskId(rs.getInt(taskIdc));
				 sbTaskBeanObj.setSubDesc(rs.getString(taskDesc));
				 taskList.add(sbTaskBeanObj); 
				}		        
		  }  
	         catch (SQLException e) { 
		     e.printStackTrace();
	         }       
	       
	        catch(Exception e) {
			e.getStackTrace();
		    }	
	
	   return taskList;
	}
	
	
	public int getSubTaskCountDao() {
	   int categoryCount=0;
	   try {  
	 
	          final String sql="select count(*) as count from "+subTaskMast;
	          conn=DbUtil.getConnection();
	          PreparedStatement ps=conn.prepareStatement(sql);
	          ResultSet rs=ps.executeQuery();
	
	          while (rs.next()) { 
		       categoryCount=rs.getInt("count");
		      }		        
	       }  
	      catch (SQLException e) { 
	          e.printStackTrace();
	       }       
	
	      catch(Exception e) {
	          e.getStackTrace();
	       }	
		
	    return categoryCount;
	 }
	
	
	
	  public boolean deleteSubTaskDao(int delTaskId)
      {
	  	boolean status=false;
	  	
	  	    try {
	        	   final String sql="delete from "+subTaskMast+ " where  "+subTaskIdc+"=?";
	        	   conn=DbUtil.getConnection();
				   PreparedStatement ps=conn.prepareStatement(sql);
				   ps.setInt(1,delTaskId);
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


	public ArrayList<SubTaskBean> getSubTaskDao(int taskID) {
	

		ArrayList<SubTaskBean> taskList=new ArrayList<SubTaskBean>();
	    try {   
	    	   
			    final String sql="select * from "+subTaskMast+" where "+taskIdc+"="+taskID; //subTaskIdc,sbTaskNamec,taskDesc,taskIdc subTaskMast
			  
			    conn=DbUtil.getConnection();
				PreparedStatement ps=conn.prepareStatement(sql);
				ResultSet rs=ps.executeQuery();
				
				while (rs.next()) { 
			     SubTaskBean sbTaskBeanObj = new    SubTaskBean();
				 sbTaskBeanObj.setSubTaskId(rs.getInt(subTaskIdc));
				 sbTaskBeanObj.setSubTaskName(rs.getString(sbTaskNamec));
				 sbTaskBeanObj.setTaskId(rs.getInt(taskIdc));
				 sbTaskBeanObj.setSubDesc(rs.getString(taskDesc));
				 taskList.add(sbTaskBeanObj); 
				}		        
		  }  
	         catch (SQLException e) { 
		     e.printStackTrace();
	         }       
	       
	        catch(Exception e) {
			e.getStackTrace();
		    }	
	
	   return taskList;
	}

 }


