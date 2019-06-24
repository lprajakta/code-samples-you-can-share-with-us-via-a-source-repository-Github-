<%@page import="javax.print.DocFlavor.STRING"%>

<%@page import="com.itrix.model.*"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.itrix.service.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link rel="shortcut icon" href="../Images/favicon.ico">
<link rel="stylesheet" type="text/css" href="../CSS/bootstrap.css">
<link rel="stylesheet" type="text/css" href="../CSS/dataTables.bootstrap.css">
<link rel="stylesheet" type="text/css" href="../CSS/bootstrap-dialog.css">
<link rel="stylesheet" type="text/css" href="../CSS/Combined.css">
<title>SmartCSM-Users</title>
<%
Character UserRole=null;
try{
	if(session.getAttribute("LoggedUid")==null || session.getAttribute("LoggedUname")==null || session.getAttribute("LoggedUrole")==null)
	{	
	   response.sendRedirect("../index.jsp");	 
	}
	else
	{	
		UserRole=(Character)session.getAttribute("LoggedUrole");
		if(UserRole=='A'){
			response.sendRedirect("../JSP/Home.jsp");
		}else{
		    response.sendRedirect("../JSP/UserHome.jsp");
		}
	}
}catch(Exception e){
	e.printStackTrace();
}
%>   
<script type="text/javascript">
function getUserDetails()
{	
	<%
	TicketService ticketService = new TicketService();
	ArrayList<TicketModel> list = ticketService.getAllTicketService();
	int ticketId = 0;
	String ticketSubject = null;
	%>		
	$('.selectpickerRoleAdd').selectpicker(); 
}
</script>
</head>
<body>
<!--********************************Create new user button************************************* -->
<table class="table table-striped" id="iTblAddBtn">
  <tr>
   <td>
       <button data-toggle="modal" type="button" data-target="#AddUser" id="iAddUser" title="create new user" class="btn btn-success btn-xs AlignRight">+Create New User</button>
   </td>
  </tr>
</table>

<!--********************************Pagination table************************************* -->
<table class="table table-striped table-bordered" id="iUserMastPageTbl">
		<thead>
			<tr>
				<th class="TblHeadContent">ID</th>
				<th class="TblHeadContent">Subject</th>
				<th class="TblHeadContent">Created Date</th>
				<th class="TblHeadContent">Priority</th>			
				<th class="TblHeadContent">Status</th>
				<th class="TblHeadContent">Action</th>
			</tr>
		</thead>
	 <tbody>
		<%
					if (list != null) {
						for (int i = 0; i < list.size(); i++) {
							TicketModel ticketModel = list.get(i);
							ticketId = ticketModel.getId();
							ticketSubject = ticketModel.getSubject();
				%>

				<tr id="tableRow">
					<td><%=ticketId%></td>
					<td><%=ticketModel.getSubject()%></td>
					<%-- <td><%=ticketModel.getMessage()%></td> --%>
					
					<td><%=ticketModel.getCreatedDate()%></td>
					<td><%=ticketModel.getPriorityStr()%></td>
					<td><%=ticketModel.getStatusStr()%></td>
					<%-- <td><%=ticketModel.getFirmName()%></td>
					<td><%=ticketModel.getProjectName()%></td> --%>
					<%-- <td><%=ticketModel.getCatName()%></td>
					<td><%=ticketModel.getTaskName()%></td> --%>
					<td>
						<button class="btn btn-danger btn-sm"
							onclick="deleteTicket(<%=ticketId%>)">Delete</button>
						<button class="btn btn-primary btn-sm" class="btn btn-info btn-sm"
							data-toggle="modal" onclick="setTicketId(<%=ticketId%>)">View</button>
					</td>
				</tr>
				<%
					}
					}
				%>			
   </tbody>
</table><!--pagination table closed  -->
<!--********************************Create New User modal************************************* -->  
 <div class="modal fade" id="AddUser" data-keyboard="false" data-backdrop="static">
  <!--  modal-sm,modal-midle,modal-lg -->
	  <div class="modal-dialog modal-midle">
	    <div class="modal-content">
		    <div class="modal-header success">
		        <button type="button" class="close" title="Close Window" data-dismiss="modal">×</button>
		        <label class="ClsModalHeadContent"><img alt="UserImage" src="../Images/ModalAddUser.png"><br />ADD NEW USER</label>
		    </div>
	          <div class="modal-body">      
	            <form name="AddUserForm" id="iCreateNewUserForm" action="../AddUserServlet" method="post" >
	               <table class="table table-striped" id="iNewUserTbl">			    
			             <tbody>
			                 <tr>	               
					              <td>
					                <label class="FieldLabel required">Name</label>
					              </td>				                  
					              <td>
					                   <div class="form-group input-group-sm">
							              <input type="text" placeholder="First name" name="FirstName" class="form-control input-sm" style="text-transform:uppercase" maxlength="25" autocomplete="off">
							          </div>
							      </td>
					              <td>
					                    <div class="form-group input-group-sm">
							             <input type="text" placeholder="Last name" name="LastName" class="form-control input-sm" style="text-transform:uppercase" maxlength="25" autocomplete="off">
							            </div>
							     </td>							         
						      </tr>
						      <tr>			                
					              <td>
					             <label class="FieldLabel required">Email ID</label>
					             </td>				                  
					              <td>
					                   <div class="form-group input-group-sm">
							            <input type="text" placeholder="EMAIL ID" name="EmailId" class="form-control input-sm" maxlength="40" autocomplete="off">
							           </div>
							     </td>					                						         
						    </tr>	
						     <tr>			                
					             <td>
					              <label class="FieldLabel required">User Name</label>
					             </td>				                  
					              <td>
					                  <div class="form-group input-group-sm">
							            <input type="text" placeholder="USERNAME" name="UserName" class="form-control input-sm" maxlength="25" autocomplete="off">
							           </div>
							     </td>					                						         
						    </tr>					    
						     <tr>   	
						         <td>
						          <label class="FieldLabel required">Role</label>
						        </td>
				                 <td>
				                      <div class="form-group input-group-sm">
							            <select name="SelectRole" class="selectpickerRoleAdd show-tick show-menu-arrow input-sm" data-width="100%">
							                <option value="">-SELECT ROLE-</option>
											<option value="U">USER</option>	
											<option value="A">ADMIN</option>
					                    </select>
							         </div>
						        </td>	
						    </tr>             
			                 <tr>
			                    <td>
			                   </td>
			                    <td>
			                        <div class="form-group">			                              		                              
						               <button type="submit" class="btn btn-success btn-sm">Save</button>
						               <button type="reset" title="Reset"  class="btn btn-success btn-sm resetForm">Cancel</button>						                    
							       </div>
						       </td>								      
			               </tr>                       
			            </tbody>
			         </table><!-- end table-->
			       </form><!-- end  form -->
	          </div><!-- modal-body closed --> 	      
	      </div> <!-- modal-content closed -->      
	 </div><!-- modal-dialog closed  -->     
</div><!-- modal fade closed --> 
<!--**********************************update modal************************************* -->
 <div class="modal fade" id="EditUser" data-keyboard="false" data-backdrop="static">
  <!--  modal-sm,modal-midle,modal-lg -->
	  <div class="modal-dialog modal-midle">
	    <div class="modal-content">
		    <div class="modal-header success">
		        <button type="button" title="Close Window" class="close closeUpdateUser" data-dismiss="modal">×</button>
		        <label class="ClsModalHeadContent"><img alt="UserImage" src="../Images/ModalEditUser.png"><br />EDIT USER</label>
		    </div>
	        <div class="modal-body">        
	          <form name="EditUserForm" action="../UpdateUserServlet" method="post" id="iEditUserForm">
	              <table class="table table-striped" id="iUpdateUserTbl">			         
			             <tbody>			             
			                   <tr>			                
					              <td>
					                  <label class="FieldLabel required">Name</label>
					              </td>				                  
				                   <td>
					                   <div class="form-group">
							              <input type="text" placeholder="First Name" name="First_Name" class="form-control input-sm " style="text-transform:uppercase" maxlength="25" autocomplete="off">
							           </div>
							       </td>
					               <td>
					                 <div class="form-group">
							            <input type="text" placeholder="Last Name" name="Last_Name" class="form-control input-sm " style="text-transform:uppercase" maxlength="25" autocomplete="off">
							         </div>
							       </td>							         
						      </tr>
						       <tr>			                
					               <td>
					                  <label class="FieldLabel required">Email ID</label>
					              </td>				                  
					               <td>
					                  <div class="form-group">
							            <input type="text" placeholder="Email Id" name="Email_Id" class="form-control input-sm" maxlength="40" autocomplete="off">
							          </div>
							      </td>					                						         
						       </tr>	
						        <tr>			                
					                <td>
					                <label class="FieldLabel required">User Name</label>
					                </td>				                  
					                <td>
					                     <div class="form-group">
							                <input type="text" placeholder="UserName" name="User_Name" class="form-control input-sm " maxlength="25" autocomplete="off">
							             </div>
							        </td>					                						         
						       </tr>					    
						        <tr>   	
						            <td>
						             <label class="FieldLabel required">Role</label>
						           </td>
				                    <td>
				                          <div class="form-group">
							                <select name="Select_Role"  data-live-search="true" class="selectpickerRoleUpdate show-tick show-menu-arrow input-sm" data-width="100%">
								                <option value="">SELECT ROLE</option>
												<option value="U">USER</option>	
												<option value="A">ADMIN</option>
					                       </select>
							            </div>
						            </td>	
						       </tr>             
			                    <tr>
			                        <td>
			                        </td>
			                        <td>
			                              <div class="form-group">	
			                               <input type="hidden" name="HideUserID">		                               
						                   <button type="submit" class="btn btn-success btn-sm">Update</button>
						                   <button type="button" title="Close Window"  data-dismiss="modal" class="btn btn-success btn-sm closeUpdateUser">Cancel</button>
							              </div>
								   </td>								      
			                   </tr>                       
			            </tbody><!-- end tbody -->
			       </table><!-- end table -->
			       </form><!-- end  form -->
	          </div><!-- modal-body closed -->
	        </div><!-- modal-content closed -->
	      </div><!-- modal-dialog closed -->
	  </div><!-- modal fade closed -->	 

</body>
</html>