package com.itrix.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.itrix.model.ListBean;
import com.itrix.model.TaskBean;
import com.itrix.utility.DbUtil;

public class TaskDao {

	 
		private Connection conn=null;
		
		private ResourceBundle tblName = ResourceBundle.getBundle("dbTables");//load property file for table Name
		private ResourceBundle tblColumn=ResourceBundle.getBundle("taskmastc");//  load property file for table column
		private final String taskMast=tblName.getString("task");// get table name
	   
	   /*task mast table  column from task mast properties file*/
		 
		private final String taskId=tblColumn.getString("taskid");
		private final String taskName=tblColumn.getString("name");
		private final String taskDesc=tblColumn.getString("description");
		private final String catgId=tblColumn.getString("catgid");
		
	  public boolean addTaskDao(TaskBean taskBeanObj)
	              {
	         		 boolean status=false;											
		
		              try {
		        	        final String sql="insert into "+taskMast+" values(?,?,?,?,?)";
		        	        conn=DbUtil.getConnection();
						    PreparedStatement ps=conn.prepareStatement(sql);
						    ps.setInt(1,0);
						    ps.setInt(2,taskBeanObj.getCatgId());
						    ps.setString(3,taskBeanObj.getTaskName());
						    ps.setString(4,taskBeanObj.getTaskDesc());
						    ps.setString(5,taskBeanObj.getActiveStatus());
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
						// TODO: handle exception
					     }    	 
		             
		            return status;
		         }			   
						       
  public boolean deleteTaskDao(int delTaskId)
	               {
	        	  	boolean status=false;
	        	  	
	        	  	    try {
			 	        	   final String sql="delete from "+taskMast+ " where  "+taskId+"=?";
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

	 public List<TaskBean> getTaskDao(int startPageIndex, int numRecordsPerPage) 
			{	
				    List<TaskBean> taskList=new ArrayList<TaskBean>();
				    try {   
				    	    final String startPageIndexs=Integer.toString(startPageIndex);
				            final String PageSize=Integer.toString(numRecordsPerPage);
						    final String sql="select * from "+taskMast+" limit "+startPageIndexs+","+PageSize;
						   
						    conn=DbUtil.getConnection();
							PreparedStatement ps=conn.prepareStatement(sql);
							ResultSet rs=ps.executeQuery();
							
							while (rs.next()) { 
						     TaskBean taskBeanObj = new 	TaskBean();
							 taskBeanObj.setTaskId(rs.getInt(taskId));
							 taskBeanObj.setCatgId(rs.getInt(catgId));
							 taskBeanObj.setTaskName(rs.getString(taskName));
							 taskBeanObj.setTaskDesc(rs.getString(taskDesc));
							 taskList.add(taskBeanObj); 
							
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
	 public boolean updateTaskDao(TaskBean taskBeanObj)  
	      	     { 
	      		   boolean status=false;
	    		           
	      		            try {
	    		                    final String sql="update "+taskMast+" set "+taskName+"=?,"+catgId+"=?,"+taskDesc+"=? where "+taskId+"=?";
					        	    conn=DbUtil.getConnection();
									PreparedStatement ps=conn.prepareStatement(sql);
				    		        ps.setString(1, taskBeanObj.getTaskName());
				    			    ps.setInt(2, taskBeanObj.getCatgId());			
				    			    ps.setString(3,taskBeanObj.getTaskDesc());
				    			    ps.setInt(4, taskBeanObj.getTaskId());
				    			    ps.executeUpdate();
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

	     
public int getTaskCountDao() {
		int taskCount=0;
	    try {  
	 
	    final String sql="select count(*) as count from "+taskMast;
	    conn=DbUtil.getConnection();
		PreparedStatement ps=conn.prepareStatement(sql);
		ResultSet rs=ps.executeQuery();
		
		while (rs.next()) { 
	    
			taskCount=rs.getInt("count");
		
		 }		        
	  }  
	catch (SQLException e) { 
	e.printStackTrace();
	}       

	catch(Exception e) {
	e.getStackTrace();
	}	
	return taskCount;
	}


	public List<ListBean> getTaskListDao(int catgIds) 
	{	
		    List< ListBean> catgList=new ArrayList< ListBean>();
			
		    try {   
				    final String sql="select "+taskId+","+taskName+" from "+taskMast+" where "+catgId+"="+catgIds;
				    conn=DbUtil.getConnection();
					PreparedStatement ps=conn.prepareStatement(sql);
					ResultSet rs=ps.executeQuery();
					
					while (rs.next()) { 
				    
						 ListBean lTaskObj = new  ListBean();
					     Integer v=(rs.getInt(taskId));
					     String vv=v.toString();
			             lTaskObj.setValue(vv);
					     String ss=rs.getString(taskName);
			             lTaskObj.setDisplayText(ss);
					     catgList.add(lTaskObj); 
					 }		        
				}  
		   catch (SQLException e) { 
			   e.printStackTrace();
		       }       
		    
		       
		   catch(Exception e) {
				e.getStackTrace();
			 }	
		    return catgList;
		}

	
	
}