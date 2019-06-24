package com.itrix.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.itrix.model.ListBean;
import com.itrix.model.ProjectAssignBean;
import com.itrix.model.ProjectMastBean;
import com.itrix.model.ProjectSubTaskAssignBean;
import com.itrix.model.SubTaskBean;
import com.itrix.utility.Conversion;
import com.itrix.utility.DbUtil;

public class ProjectAssignDao {

	 
		private Connection conn=null;
		
		private ResourceBundle tblName = ResourceBundle.getBundle("dbTables");//load property file for table Name
		private ResourceBundle tblColumn=ResourceBundle.getBundle("projectassign");//  load property file for table column
		private final String projectAssign=tblName.getString("projectassign");// get table name	   
		private final String projassignId=tblColumn.getString("projassignid");
		private final String projId=tblColumn.getString("projid");
		private final String userId=tblColumn.getString("userid");		
		
	  public boolean addProjectAssignDao(ProjectAssignBean projectassignBeanObj)
	              {
	         		 boolean status=false;											
		
		              try {
		        	     //   final String sql="insert into "+projectAssign+" values(?,?,?)";
		            	  
		            	  final String sql = "INSERT INTO `projtaskdet`(`projtaskdetid`, `projid`, `catgid`, `taskid`, `userid`, `taskamt`, `activestatus`, `status`, `work_status`, `reviewerremark`, `created_dt`, `exp_enddt`, `end_dt`) VALUES "+projectAssign+" values(?,?,?)";
		        	        conn=DbUtil.getConnection();
						    PreparedStatement ps=conn.prepareStatement(sql);
						    ps.setInt(1,0);						    
						    ps.setInt(2,projectassignBeanObj.getProjId());						    
						    ps.setInt(3,projectassignBeanObj.getUserId());						    
						    
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
						       
  public boolean deleteProjectAssignDao(int delProjectAssignId)
	               {
	        	  	boolean status=false;
	        	  	
	        	  	    try {
	        	  	    	   final String sql="delete from "+projectAssign+ " where  "+projassignId+"=?";
			 	        	   conn=DbUtil.getConnection();
			 				   PreparedStatement ps=conn.prepareStatement(sql);
			 				   ps.setInt(1,delProjectAssignId);
			 				  System.out.println("delete into table=11====>"+ps);
			 				   int i=ps.executeUpdate();
			 				  System.out.println("delete into table=22====>"+ps);
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

	 public List<ProjectAssignBean> getProjectAssignDao(int startPageIndex, int numRecordsPerPage) 
			{	
				    List<ProjectAssignBean> projectassignList=new ArrayList<ProjectAssignBean>();
				    try {   
				    	    final String startPageIndexs=Integer.toString(startPageIndex);
				            final String PageSize=Integer.toString(numRecordsPerPage);
						    final String sql="select * from "+projectAssign+" limit "+startPageIndexs+","+PageSize;
						   
						    conn=DbUtil.getConnection();
							PreparedStatement ps=conn.prepareStatement(sql);
							ResultSet rs=ps.executeQuery();
							
							 while (rs.next()) { 
							 ProjectAssignBean projectassignBeanObj = new ProjectAssignBean();
							 projectassignBeanObj.setProjassignId(rs.getInt(projassignId));
							 //System.out.println("projassignId=C=ssssssListssssssss======>"+rs.getInt(projassignId));
						       
							 
							 
							 projectassignBeanObj.setUserId(rs.getInt(userId));
							 projectassignBeanObj.setProjId(rs.getInt(projId));
							 
							 projectassignList.add(projectassignBeanObj); 
							
							}		        
						}  
				   catch (SQLException e) { 
					   e.printStackTrace();
				       }       
				       
				   catch(Exception e) {
						e.getStackTrace();
					 }	
					return projectassignList;
				}
	 public boolean updateProjectAssignDao(ProjectAssignBean projectassignBeanObj)  
	      	     { 
	      		   boolean status=false;
	    		           
	      		            try {  
	      		            	    final String sql="update "+projectAssign+" set "+projId+"=?,"+userId+"=? where "+projassignId+"=?";
	      		            	   
	      		            	    //final String sql="update "+taskMast+" set "+taskName+"=?,"+catgId+"=?,"+taskDesc+"=? where "+taskId+"=?";
					        	   
	      		            	    
	      		            	    conn=DbUtil.getConnection();
									PreparedStatement ps=conn.prepareStatement(sql);									
				    		        ps.setInt(1, projectassignBeanObj.getProjId());			    		        
				    		        ps.setInt(2, projectassignBeanObj.getUserId()); 		        
				    		        ps.setInt(3, projectassignBeanObj.getProjassignId());    
				    		       
				    		        //ps.setInt(2, 1);
				    		       
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

	     
public int getProjectAssignCountDao() {
		int taskCount=0;
	    try {  
	 
	    final String sql="select count(*) as count from "+projectAssign;
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


	public List<ListBean> getProjectAssignListDao(int userIds) 
	{	
		    List< ListBean> userList=new ArrayList< ListBean>();
			
		    try {   
				    final String sql="select "+projassignId+" from "+projectAssign+" where "+userId+"="+userIds;
				    conn=DbUtil.getConnection();
					PreparedStatement ps=conn.prepareStatement(sql);
					ResultSet rs=ps.executeQuery();
					
					while (rs.next()) { 
				    
						 ListBean lProjectAssignObj = new  ListBean();
					     Integer v=(rs.getInt(projassignId));
					     String vv=v.toString();
			             lProjectAssignObj.setValue(vv);
			             
					    // String ss=rs.getString(taskName);
			             //lTaskObj.setDisplayText(ss);
					     userList.add(lProjectAssignObj); 
					 }		        
				}  
		   catch (SQLException e) { 
			   e.printStackTrace();
		       }       
		    
		       
		   catch(Exception e) {
				e.getStackTrace();
			 }	
		    return userList;
		}

	public boolean addProjectAssignDao(ProjectMastBean proBeanObj,
			int currProjID) {
		 boolean status=false;											
		 int currProjAssignID=0;
         try {
   	     //   final String sql="insert into "+projectAssign+" values(?,?,?)";
       	  
       	  final String sql = "INSERT INTO `projtaskdet`(`projtaskdetid`, `projid`, `catgid`, `taskid`, `userid`, `taskamt`, `activestatus`, `status`, `work_status`, `reviewerremark`) VALUES (?,?,?,?,?,?,?,?,?,?)";
   	        conn=DbUtil.getConnection();
   	     ArrayList<ProjectAssignBean> projectAssignList = proBeanObj.getProjectAssignList();
   	     PreparedStatement ps=conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS); 
   	   
   	     for (int i = 0; i < projectAssignList.size(); i++) {
   	    	ProjectAssignBean projectAssignObj = projectAssignList.get(i);
			    ps.setInt(1,0);						    
			    ps.setInt(2,currProjID);						    
			    ps.setInt(3,projectAssignObj.getCatgId());						    
			    ps.setInt(4,projectAssignObj.getTaskId());
			    ps.setInt(5,projectAssignObj.getUserId());
			    ps.setDouble(6, projectAssignObj.getAmount());
			    ps.setString(7, projectAssignObj.getActivestatus());
			    ps.setString(8, projectAssignObj.getStatus());
			    ps.setString(9, projectAssignObj.getWorkStatus());
			    ps.setString(10, projectAssignObj.getRemark());
			  //  ps.setDate(11, strDateDt);
			    System.out.println("ps Task "+ps);
			    int result=ps.executeUpdate();
			   
		        if(result==1) {
		        	 ResultSet rsForProj=ps.getGeneratedKeys();
						
						if (rsForProj.next())
						{
							boolean res = false;
							currProjAssignID = rsForProj.getInt(1);
							System.out.println("Generated task Id "+currProjAssignID);
							//ProjectAssignBean assignObj = new ProjectAssignBean();
							res = addProjSubTaskDao(projectAssignObj,currProjAssignID);
							if(res){
								 status=true;
							}
							
						}
		          
		         } 	      
		}
			   
            }	 
        catch(SQLException ee) {
       	    ee.printStackTrace();
            }
        catch (Exception e) {
       	   e.printStackTrace();
			// TODO: handle exception
		     }    	 
        
       return status;
    }
	public boolean addProjSubTaskDao(ProjectAssignBean assignTaskBeanObj, int curProAssinTaskId)
    {
		 boolean status=false;											
		 int result=0;
        try {
  	        final String sql="insert into projsubtaskdet (`projsubtaskdetid`, `projtaskdetid`, `subtaskid`) values(?,?,?)";
  	        
  	        	conn=DbUtil.getConnection();
			    PreparedStatement ps=conn.prepareStatement(sql);
			    ArrayList<ProjectSubTaskAssignBean> projSubTaskAssignList = assignTaskBeanObj.getProjSubTaskAssignList();
			    for (int i = 0; i < projSubTaskAssignList.size(); i++) {
			    	ProjectSubTaskAssignBean bean = projSubTaskAssignList.get(i);
			    	 	ps.setInt(1,0);
					    ps.setInt(2,curProAssinTaskId);
					    ps.setInt(3,bean.getSubtaskid());
					    System.out.println("Generated subtask  "+ps);
					     result=ps.executeUpdate();
				}
			   
		        if(result==1) {
		          status=true;
		         } 	      
           }	 
       catch(SQLException ee) {
        	    ee.printStackTrace();
             }
         catch (Exception e) {
        	   e.printStackTrace();
			 }    	 
      

      return status;
   }
	/*
	public List<ListBean> getProjectAssignListDao(int projIds) 
	{	
		    List< ListBean> userList=new ArrayList< ListBean>();
			
		    try {   
				    final String sql="select "+projassignId+" from "+projectAssign+" where "+projId+"="+projIds;
				    conn=DbUtil.getConnection();
					PreparedStatement ps=conn.prepareStatement(sql);
					ResultSet rs=ps.executeQuery();
					
					while (rs.next()) { 
				    
						 ListBean lProjectAssignObj = new  ListBean();
					     Integer v=(rs.getInt(projassignId));
					     String vv=v.toString();
			             lProjectAssignObj.setValue(vv);
			             
					    // String ss=rs.getString(taskName);
			             //lTaskObj.setDisplayText(ss);
					     userList.add(lProjectAssignObj); 
					 }		        
				}  
		   catch (SQLException e) { 
			   e.printStackTrace();
		       }       
		    
		       
		   catch(Exception e) {
				e.getStackTrace();
			 }	
		    return userList;
		}
	  java.util.Date cretddate = new java.sql.Timestamp(new java.util.Date().getTime());
   	//  Calendar calendar = Calendar.getInstance();
    //  java.sql.Date ourJavaDateObject = new java.sql.Date(calendar.getTime().getTime());
   //	  Date date = new Date();  
      SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");  
      String strDate= formatter.format(cretddate);
      Conversion conv = new Conversion();
      Date strDateDt = conv.convertStrToDate(strDate);
	*/

	public boolean updtProjectAssignDao(ProjectMastBean proBeanObj) { 
		   boolean status=false;
           
	            try {  
	            	    final String sql="update projtaskdet set catgid=?, taskid=?, userid=?, taskamt=?, reviewerremark=? where projtaskdetid=?";
	            	   
	            	    //final String sql="update "+taskMast+" set "+taskName+"=?,"+catgId+"=?,"+taskDesc+"=? where "+taskId+"=?";
	        	   
	            	    
	            	conn=DbUtil.getConnection();
	            	 ArrayList<ProjectAssignBean> projectAssignList = proBeanObj.getProjectAssignList();
					PreparedStatement ps=conn.prepareStatement(sql);									
					for (int i = 0; i < projectAssignList.size(); i++) {
			   	    	ProjectAssignBean projectAssignObj = projectAssignList.get(i);
						    //ps.setInt(1,0);						    
						 //   ps.setInt(2,currProjID);						    
						    ps.setInt(1,projectAssignObj.getCatgId());						    
						    ps.setInt(2,projectAssignObj.getTaskId());
						    ps.setInt(3,projectAssignObj.getUserId());
						    ps.setDouble(4, projectAssignObj.getAmount());
						  //  ps.setString(7, projectAssignObj.getActivestatus());
						  //  ps.setString(8, projectAssignObj.getStatus());
						  //  ps.setString(9, projectAssignObj.getWorkStatus());
						    ps.setString(5, projectAssignObj.getRemark());
						    ps.setInt(6,projectAssignObj.getProjassignId());
						  //  ps.setDate(11, strDateDt);
						    System.out.println("ps updt Task "+ps);
						    int result=ps.executeUpdate();
						   
					        if(result==1) {
					        	status=true;
					        	// ResultSet rsForProj=ps.getGeneratedKeys();
									
									/*if (rsForProj.next())
									{
										boolean res = false;
										currProjAssignID = rsForProj.getInt(1);
										System.out.println("Generated task Id "+currProjAssignID);
										//ProjectAssignBean assignObj = new ProjectAssignBean();
										res = addProjSubTaskDao(projectAssignObj,currProjAssignID);
										if(res){
											 status=true;
										}
										
									}*/
					          
					         } 	      
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

	public boolean UserUpdtProjectAssignDao(ProjectMastBean proBeanObj) { 
		   boolean status=false;
           
           try {  
           	    final String sql="update projtaskdet set catgid=?, taskid=?, userid=?, taskamt=?, reviewerremark=?, work_status=? where projtaskdetid=?";
           	   
           	    //final String sql="update "+taskMast+" set "+taskName+"=?,"+catgId+"=?,"+taskDesc+"=? where "+taskId+"=?";
       	   
           	    
           	conn=DbUtil.getConnection();
           	 ArrayList<ProjectAssignBean> projectAssignList = proBeanObj.getProjectAssignList();
				PreparedStatement ps=conn.prepareStatement(sql);									
				for (int i = 0; i < projectAssignList.size(); i++) {
		   	    	ProjectAssignBean projectAssignObj = projectAssignList.get(i);
					    //ps.setInt(1,0);						    
					 //   ps.setInt(2,currProjID);						    
					    ps.setInt(1,projectAssignObj.getCatgId());						    
					    ps.setInt(2,projectAssignObj.getTaskId());
					    ps.setInt(3,projectAssignObj.getUserId());
					    ps.setDouble(4, projectAssignObj.getAmount());
					    ps.setString(5, projectAssignObj.getRemark());
					    ps.setString(6, projectAssignObj.getWorkStatus());
					    ps.setInt(7,projectAssignObj.getProjassignId());
					  //  ps.setDate(11, strDateDt);
					    System.out.println("ps updt Task "+ps);
					    int result=ps.executeUpdate();
					   
				        if(result==1) {
				        	status=true;
				        	// ResultSet rsForProj=ps.getGeneratedKeys();
								
								/*if (rsForProj.next())
								{
									boolean res = false;
									currProjAssignID = rsForProj.getInt(1);
									System.out.println("Generated task Id "+currProjAssignID);
									//ProjectAssignBean assignObj = new ProjectAssignBean();
									res = addProjSubTaskDao(projectAssignObj,currProjAssignID);
									if(res){
										 status=true;
									}
									
								}*/
				          
				         } 	      
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