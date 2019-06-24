package com.itrix.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.itrix.model.ListBean;
import com.itrix.model.ProjectAssignBean;
import com.itrix.model.ProjectMastBean;
import com.itrix.model.ProjectSubTaskAssignBean;
import com.itrix.model.SubTaskBean;
import com.itrix.utility.DbUtil;

import java.sql.Statement;

public class ProjectMastDao {

	private  Connection conn=null;
		
		private ResourceBundle tblName = ResourceBundle.getBundle("dbTables");//load property file for table Name
		private ResourceBundle tblColumn=ResourceBundle.getBundle("projectmastc");//  load property file for table column
		private final String proMast=tblName.getString("projectmast");// get table name
	   
	  		
		private final String projIdc=tblColumn.getString("projid");
		private final String firmIdc=tblColumn.getString("firmid");
		private final String projnamec=tblColumn.getString("projname");
		private final String total_amt=tblColumn.getString("total_amt");
		private final String revieweridc=tblColumn.getString("reviewerid");
		private final String opening_bal=tblColumn.getString("opening_bal");
		private final String statusc=tblColumn.getString("status");
		private final String created_dt=tblColumn.getString("created_dt");
		private final String exp_enddt=tblColumn.getString("exp_enddt");
		private final String end_dt=tblColumn.getString("end_dt");
		private final String remarkc=tblColumn.getString("remark");
		private final String activestatus=tblColumn.getString("activestatus");
		public static void main(String[] args) {
			ProjectMastDao pdo=new ProjectMastDao();
			pdo.getProjectsMastDao(0,1);
		}
		public boolean addProjMastDao(ProjectMastBean proBeanObj)
	              {  
			         int currProjID=0;
			         boolean status=false;
		
		              try {
		        	        final String sql="insert into "+proMast+" (`projid`, `firmid`, `projname`, `reviewerid`, `total_amt`, `opening_bal`, `status`, `created_dt`,  `remark`, `activestatus`) values(?,?,?,?,?,?,?,?,?,?)";
		        	     //   INSERT INTO `projectmast`(`projid`, `firmid`, `projname`, `reviewerid`, `total_amt`, `opening_bal`, `status`, `created_dt`, `exp_enddt`, `end_dt`, `remark`, `activestatus`) VALUES ([value-1],[value-2],[value-3],[value-4],[value-5],[value-6],[value-7],[value-8],[value-9],[value-10],[value-11],[value-12])
		        	        
		        	        conn=DbUtil.getConnection();
						    PreparedStatement ps=conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
						    ps.setInt(1,0);
						    ps.setInt(2,proBeanObj.getFirmId());
						    ps.setString(3,proBeanObj.getprojname());
						    ps.setInt(4,proBeanObj.getReviewerid()); 
						    ps.setDouble(5,proBeanObj.getFees());
						    ps.setDouble(6,proBeanObj.getOpeningFees());
		    		        ps.setString(7,String.valueOf(proBeanObj.getStatus()));
						    ps.setDate(8,proBeanObj.getStartDt());
						  //  ps.setString(9,proBeanObj.getExpectedEnddt());
						 //   ps.setString(10,proBeanObj.getEndDt());
						    ps.setString(9,proBeanObj.getRemark());
						    ps.setString(10, proBeanObj.getActivestatus());
						    System.out.println("query="+ps);
						    int i=ps.executeUpdate();
						    
					        if(i==1) {
					       
					        ResultSet rsForProj=ps.getGeneratedKeys();
							
							if (rsForProj.next())
							{
								boolean result = false;
								currProjID = rsForProj.getInt(1);
								System.out.println("Generated Proj Id "+currProjID);
								ProjectAssignDao assignObj = new ProjectAssignDao();
								result = assignObj.addProjectAssignDao(proBeanObj,currProjID);
								if(result){
									 status=true;
								}
								
							}
					         }
					        else
					         {
					        	status=false;
					        	System.out.println("not inserted");
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
					   
					       
  public boolean deleteProjectMastDao(int deletId)
	               {
	        	  	boolean status=false;
	        	  	
	        	  	    try {
			 	        	   final String sql="delete from "+proMast+ " where  "+projIdc+"=?";
			 	        	   conn=DbUtil.getConnection();
			 				   PreparedStatement ps=conn.prepareStatement(sql);
			 				   ps.setInt(1,deletId);
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
  

	 public List<ProjectMastBean> getProjectsMastDao(int startPageIndex, int numRecordsPerPage) 
			{	
				    List<ProjectMastBean> proList=new ArrayList<ProjectMastBean>();//projIdc firmIdc fees catgIdc openFeec statusc creatDtc endDt expEndDtc remarkc
					
				    try {   final String startPageIndexs=Integer.toString(startPageIndex);
				            final String PageSize=Integer.toString(numRecordsPerPage);
				            final String sql="select * from "+proMast+" limit "+startPageIndexs+","+PageSize;
				            conn=DbUtil.getConnection();
							PreparedStatement ps=conn.prepareStatement(sql);
										ResultSet rs=ps.executeQuery();
							
							while (rs.next()) { 
						 
							 SimpleDateFormat sdf = new SimpleDateFormat();
							 ProjectMastBean proBeanObj = new ProjectMastBean();
							 proBeanObj.setProjId(rs.getInt(projIdc));
							 proBeanObj.setFirmId(rs.getInt(firmIdc)); 
							 proBeanObj.setprojname(rs.getString(projnamec));
							 proBeanObj.setReviewerid(rs.getInt(revieweridc));
							 proBeanObj.setFees(rs.getDouble(total_amt));
							 proBeanObj.setOpeningFees(rs.getDouble(opening_bal)); 
						     
						     proBeanObj.setStatus(rs.getString(statusc));
							 proBeanObj.setOpeningFees(rs.getDouble(opening_bal));
							 proBeanObj.setCreatedDt(rs.getString(created_dt));
					    	 proBeanObj.setExpectedEnddt(rs.getString(exp_enddt));
					    	 proBeanObj.setEndDt(rs.getString(end_dt));
					    	 
					    	 proBeanObj.setRemark(rs.getString(remarkc));
					    	 proBeanObj.setActivestatus(rs.getString(activestatus));
					    	 proList.add(proBeanObj); 
							
							}		        
						}  
				   catch (SQLException e) { 
					   e.printStackTrace();
				       }       
				       
				   catch(Exception e) {
						e.getStackTrace();
					 }	
					
				  return proList;
				}
	

		public int getProMastCountDao() {
						int categoryCount=0;
					    try {  
					 
					final String sql="select count(*) as count from "+proMast;
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
		public boolean updateProjMastDao(ProjectMastBean proBeanObj)  
		      	     {   
					   boolean status=false;
		    		   int i=0;        
		      		            try {  
		      		            	    
		    		                    final String sql="update "+proMast+" set "+projnamec+"=?,"+revieweridc+"=?,"+total_amt+"=?,"+statusc+"=?,"+created_dt+"=?,"+remarkc+"=? where "+projIdc+"=?";
						        	    conn=DbUtil.getConnection();
										PreparedStatement ps=conn.prepareStatement(sql);
									   // ps.setInt(1,proBeanObj.getFirmId());
					    		        ps.setString(1,proBeanObj.getprojname());
					    		        ps.setInt(2,proBeanObj.getReviewerid());
					    		        ps.setDouble(3, proBeanObj.getFees());			
					    		      //  ps.setDouble(5, proBeanObj.getOpeningFees());
					    			    ps.setString(4,String.valueOf(proBeanObj.getStatus()));
									    ps.setDate(5, proBeanObj.getStartDt());  
									  //  ps.setString(8, proBeanObj.getExpectedEnddt());  
									  //  ps.setString(9, proBeanObj.getEndDt());  
									    ps.setString(6,proBeanObj.getRemark());
									   // ps.setString(7, proBeanObj.getActivestatus());
									    ps.setInt(7,proBeanObj.getProjId());									    
					                    i = ps.executeUpdate();
									   
					                    if(i!=0){
					                    ProjectAssignDao assignObj = new ProjectAssignDao();
										boolean result = assignObj.updtProjectAssignDao(proBeanObj);
										if(result){
											 status=true;
										}
					                    }
					               } 
					    		catch (SQLException e) 
					    		     {
					    			  e.printStackTrace();
					    		     }
					    		catch(Exception e){
					    			  e.printStackTrace();
					    		
									}
		    	       return status;
		      	    }
					 
					

		 public List<ListBean> getProjectListDao() 
					 {	
					 	    List< ListBean> projectList=new ArrayList< ListBean>();
					 		
					 	    try {   
					 			    final String sql="select * from "+proMast;
					 			    conn=DbUtil.getConnection();
					 				PreparedStatement ps=conn.prepareStatement(sql);
					 				ResultSet rs=ps.executeQuery();
					 				
					 				while (rs.next()) { 
					 			    
					 					 ListBean projectBeanObj = new  ListBean();
					 					  
					 				     Integer proid=(rs.getInt(projIdc));
					 				     String proidStr=proid.toString();;
					 				     projectBeanObj.setValue(proidStr);
					 				     					 				     
					 				     String projname=rs.getString(projnamec);
					 				     projectBeanObj.setDisplayText(projname);
					 				     
					 		             projectList.add(projectBeanObj); 
					 				     
					 				     
					 		          }		        
					 			}  
					 	   catch (SQLException e) { 
					 		   e.printStackTrace();
					 	       }       
					 	    
					 	       
					 	   catch(Exception e) {
					 			e.getStackTrace();
					 		 }	
					 	   					  
					     return projectList;
					 	}
					public ArrayList<ProjectMastBean> getAllProjectsMastDao() {	
						ArrayList<ProjectMastBean> proList=new ArrayList<ProjectMastBean>();//projIdc firmIdc fees catgIdc openFeec statusc creatDtc endDt expEndDtc remarkc
						
					    try {  
					            final String sql="SELECT pm.projid,pm.firmid,fm.firmname,pm.projname,pm.reviewerid,um.fname,pm.total_amt,pm.status,pm.created_dt,pm.remark FROM projectmast as pm inner join firmmast as fm on fm.firmid=pm.firmid inner join usermast as um on um.userid=pm.reviewerid order by pm.projid asc";
					            
					            conn=DbUtil.getConnection();
								PreparedStatement ps=conn.prepareStatement(sql);
											ResultSet rs=ps.executeQuery();
								
								while (rs.next()) { 
							 
								 SimpleDateFormat sdf = new SimpleDateFormat();
								 ProjectMastBean proBeanObj = new ProjectMastBean();
								 proBeanObj.setProjId(rs.getInt("projid"));
								 proBeanObj.setFirmId(rs.getInt("firmid")); 
								 proBeanObj.setFirmName(rs.getString("firmname"));
								 proBeanObj.setprojname(rs.getString("projname"));
								 proBeanObj.setReviewerid(rs.getInt("reviewerid"));
								 proBeanObj.setRivewerName(rs.getString("fname"));
								 proBeanObj.setFees(rs.getDouble("total_amt"));															     
							     proBeanObj.setStatus(rs.getString("status"));								
								 proBeanObj.setStartDt(rs.getDate("created_dt"));
						    	 proBeanObj.setRemark(rs.getString("remark"));
						    	
						    	 proList.add(proBeanObj); 
								
								}		        
							}  
					   catch (SQLException e) { 
						   e.printStackTrace();
					       }       
					       
					   catch(Exception e) {
							e.getStackTrace();
						 }	
						
					  return proList;
					}
		public boolean UserUpdateProjMastDao(
							ProjectMastBean proBeanObj) {   
						   boolean status=false;
			    		       
			      		            try {  
			      		            	                   
						                    ProjectAssignDao assignObj = new ProjectAssignDao();
											boolean result = assignObj.UserUpdtProjectAssignDao(proBeanObj);
											if(result){
												 status=true;
											}
						                    
						               } 
						    		
						    		catch(Exception e){
						    			  e.printStackTrace();
						    		
										}
			    	       return status;
			      	    }
		public boolean UserUpdtProjSubTaskMastDao(
							ArrayList<ProjectSubTaskAssignBean> projSubTaskAssignList) { 
			   boolean status=false;
	           
	           try {  
	           	    final String sql="UPDATE projsubtaskdet SET status=?,remark=?,work_status=? WHERE projsubtaskdetid=?";
	           	    
	           	    conn=DbUtil.getConnection();
	           
					PreparedStatement ps=conn.prepareStatement(sql);									
					for (int i = 0; i < projSubTaskAssignList.size(); i++) {
						ProjectSubTaskAssignBean projectAssignObj = projSubTaskAssignList.get(i);
											    
						  //  ps.setInt(1,projectAssignObj.getTaskId());						   
						    ps.setString(1, projectAssignObj.getStatus());
						    ps.setString(2, projectAssignObj.getRemark());
						    ps.setString(3, projectAssignObj.getWorkStatus());
						    ps.setInt(4,projectAssignObj.getProjsubtaskdetid());
				
						    System.out.println("subtask updt "+ps);
						    int result=ps.executeUpdate();
						   
					        if(result==1) {
					        	status=true;
					        	
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
	    				
						
	    	
	    
	   

	


