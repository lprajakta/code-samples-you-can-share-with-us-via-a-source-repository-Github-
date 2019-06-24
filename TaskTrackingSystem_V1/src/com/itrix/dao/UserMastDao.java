package com.itrix.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.itrix.model.ListBean;
import com.itrix.model.UserBean;
import com.itrix.utility.DbUtil;

public class UserMastDao {
private Connection conn=null;
	
	private ResourceBundle tblName = ResourceBundle.getBundle("dbTables");//load property file for table Name
	private ResourceBundle tblColumn=ResourceBundle.getBundle("usermast");//  load property file for table column
	private final String usermast=tblName.getString("usermast");// get table name
   
   /*usermast table  column from usermast properties file*/
	
	private final String userId=tblColumn.getString("userId");
	private final String empId=tblColumn.getString("empId");
	private final String fname=tblColumn.getString("fname");
	private final String lname=tblColumn.getString("lname");
	private final String emailid=tblColumn.getString("emailid");
	private final String mobileno=tblColumn.getString("mobileno");
	private final String username=tblColumn.getString("username");
	private final String password=tblColumn.getString("password");
	private final String userrole=tblColumn.getString("userrole");
	private final String status=tblColumn.getString("status");
    public boolean addUserDao(UserBean userBeanObj)
              {
         		 boolean status=false;
	
	              try {
	        	        final String sql="insert into "+usermast+" values(?,?,?,?,?,?,?,?,?,?)";
	        	        conn=DbUtil.getConnection();
					    PreparedStatement ps=conn.prepareStatement(sql);
					    ps.setInt(1,0);
					    ps.setInt(2,userBeanObj.getEmpId());
					    ps.setString(3,userBeanObj.getFname());
					    ps.setString(4,userBeanObj.getLname());
					    ps.setString(5,userBeanObj.getEmailid());
					    ps.setLong(6,Long.valueOf(userBeanObj.getMobileno()));
					    ps.setString(7,userBeanObj.getUsername());
					    ps.setString(8,userBeanObj.getPassword());
					    ps.setString(9,String.valueOf(userBeanObj.getUserrole()));
					    ps.setString(10,String.valueOf(userBeanObj.getStatus()));
					    System.out.println("Insert: "+ps);
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
					       
  public boolean deleteUserDao(int userId)
               {
        	  	boolean status=false;
        	  	
        	  	    try {
		 	        	   String sql="delete from usermast where userId=?";
		 	        	   conn=DbUtil.getConnection();
		 				   PreparedStatement ps=conn.prepareStatement(sql);
		 				   ps.setInt(1,userId);
		 				   //System.out.println("ps: "+ps);
		 				   int i=ps.executeUpdate();
		 				   //System.out.println("Delete: "+ps);
		 				   if(i==1) 
		 				   {
		 				   status=true;
		 				   }
        	  	       } 	  
	              catch (Exception e) {
				       e.printStackTrace();
			           }  	 
 				     
 	              return status;	
              }

 public List<UserBean> getUserDao(int startPageIndex, int numRecordsPerPage) 
		{	
			    List<UserBean> userList=new ArrayList<UserBean>();
				
			    try { 			    	
			    	    String sql = "select * from usermast";    
			    	   //System.out.println("select--"+sql);
					    conn=DbUtil.getConnection();
						PreparedStatement ps=conn.prepareStatement(sql);
						//System.out.println("ps: "+ps);
						ResultSet rs=ps.executeQuery();
						//System.out.println("result"+rs);
						while (rs.next()) { 
					    
						UserBean userBeanObj = new UserBean();
						userBeanObj.setUserId(rs.getInt(userId));
						userBeanObj.setEmpId(rs.getInt(empId));
						userBeanObj.setFname(rs.getString(fname));
						userBeanObj.setLname(rs.getString(lname));
						userBeanObj.setEmailid(rs.getString(emailid));
						userBeanObj.setMobileno(rs.getLong(mobileno));
						userBeanObj.setUsername(rs.getString(username));
						userBeanObj.setPassword(rs.getString(password));
						userBeanObj.setUserrole(rs.getString(userrole));
						userBeanObj.setStatus(rs.getString(status));
						userList.add(userBeanObj); 
						
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
 public boolean updateUserDao(UserBean userBeanObj)  
      	     { 
      		   boolean status=false;
    		           
      		            try {
    		                   final String sql="update usermast  set empId=?,fname=?,lname=?,emailid=?,mobileno=?,username=?,password=?,userrole=?,activestatus=? where userId=?";
      		            	   conn=DbUtil.getConnection();
								PreparedStatement ps=conn.prepareStatement(sql);
								
								ps.setInt(1, userBeanObj.getEmpId());
								ps.setString(2, userBeanObj.getFname());
								ps.setString(3, userBeanObj.getLname());
								ps.setString(4, userBeanObj.getEmailid());
								ps.setLong(5, userBeanObj.getMobileno());
			    		        ps.setString(6, userBeanObj.getUsername());
			    			    ps.setString(7, userBeanObj.getPassword());		
			    			   
			    			    ps.setString(8, String.valueOf(userBeanObj.getUserrole()));
			    			    ps.setString(9, String.valueOf(userBeanObj.getStatus()));
			    			    ps.setInt(10, userBeanObj.getUserId());
			    			    System.out.println("Update: "+ps);
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
 public UserBean getUserByIdDao(int userId) 
            {
      		 UserBean userBeanObj = new UserBean();
    		
                try {
		        	   final String sql="select * from "+usermast+"where "+userId+"=?";
		        	   conn=DbUtil.getConnection();
					   PreparedStatement ps=conn.prepareStatement(sql);
					   ps.setInt(1, userId);
					   ResultSet rs=ps.executeQuery();
					   //System.out.println("Reyrieve: "+ps);
					   if (rs.next()) { 
    				 
					   userBeanObj.setUserId(rs.getInt(userId));	   
					   userBeanObj.setEmpId(rs.getInt(empId));
					   userBeanObj.setFname(rs.getString(fname));
					   userBeanObj.setLname(rs.getString(lname));
					   userBeanObj.setEmailid(rs.getString(emailid));
					   userBeanObj.setMobileno(rs.getLong(mobileno));
 					   userBeanObj.setUsername(rs.getString(username));
 					   userBeanObj.setPassword(rs.getString(password));
 					   userBeanObj.setUserrole(String.valueOf(rs.getString(userrole)));
					   userBeanObj.setStatus(String.valueOf(rs.getString(status)));
		     		  }		
           	      }
              catch (SQLException e) {
      			       e.printStackTrace();
      		      }
              catch(Exception e) {
            	       e.getStackTrace();
                  } 
      		return userBeanObj;
     }

public int getUserCountDao() {
	int userCount=0;
    try {  
 
    final String sql="select count(*) as count from "+usermast;
    	
    conn=DbUtil.getConnection();
	PreparedStatement ps=conn.prepareStatement(sql);
	//System.out.println("UserCount: "+ps);
	ResultSet rs=ps.executeQuery();
	
	while (rs.next()) { 
    
		userCount=rs.getInt("count");
	
	}		        
}  
catch (SQLException e) { 
e.printStackTrace();
}       

catch(Exception e) {
e.getStackTrace();
}	
	//System.out.println("-count--"+userCount);
    return userCount;   
}


///nbs

public List<ListBean> getProjectListDao() 
{	
	    List< ListBean> userList=new ArrayList< ListBean>();
		
	    try {   
			    final String sql="select * from "+usermast;
			    conn=DbUtil.getConnection();
				PreparedStatement ps=conn.prepareStatement(sql);
				ResultSet rs=ps.executeQuery();
				
				while (rs.next()) { 
			    
					 ListBean userBeanObj = new  ListBean();
				     Integer v=(rs.getInt(userId));
				     String vv=v.toString();;
		             userBeanObj.setValue(vv);
				     String ss=rs.getString(username);
		             userBeanObj.setDisplayText(ss);
				     userList.add(userBeanObj); 
				     
				     
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

//nbs
///nbs

public List<ListBean> getUserListDao() 
{	
	    List< ListBean> userList=new ArrayList< ListBean>();
		
	    try {   
			    final String sql="select * from "+usermast;
			    conn=DbUtil.getConnection();
				PreparedStatement ps=conn.prepareStatement(sql);
				ResultSet rs=ps.executeQuery();
				
				while (rs.next()) { 
			    
					 ListBean userBeanObj = new  ListBean();
				     Integer v=(rs.getInt(userId));
				     String vv=v.toString();;
		             userBeanObj.setValue(vv);
				     String ss=rs.getString(username);
		             userBeanObj.setDisplayText(ss);
				     userList.add(userBeanObj); 
				     
				     
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

}
