<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="com.itrix.service.ProjetcMastService"%>
<%@page import="com.itrix.service.UtilityService"%>
<%@page import="com.itrix.model.ProjectMastBean"%>
<%@page import="com.itrix.model.FirmModel"%>
<%@page import="java.util.ArrayList, java.text.DateFormat,java.text.SimpleDateFormat,java.sql.Date"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<link href="../CSS/Combined.Style.css"type="text/css" rel="stylesheet"  />
<!-- <link href="../CSS/dataTables.bootstrap.css" type="text/css" rel="stylesheet" />
<link href="../CSS/bootstrap-dialog.css" type="text/css" rel="stylesheet" /> -->


<title>Smart- home</title>
<%
String Username="";
Character UserRole=' ';
String UserEmailID="";
try{	
	if(session.getAttribute("LoggedUid")==null || session.getAttribute("LoggedUname")==null || session.getAttribute("LoggedUrole")==null)
	{	
	   response.sendRedirect("../index.jsp");	 
	}
	else
	{
		Username=(String)session.getAttribute("LoggedUname");	
		UserRole=(Character)session.getAttribute("LoggedUrole");
		UserEmailID=(String)session.getAttribute("LoggedEmailID");
	}
}catch(Exception e){
	e.printStackTrace();
}
%>   
<%
	//String projID="17";
	//String keyValue[]= saleID_orderStatus.split(":");
	/* String saleID = projID[0].trim();
	String orderStatus = keyValue[1]; */
	
	/*  if(projID==null)
	{
		response.sendRedirect("../JSP/UserHome.jsp");
	}  */
	%>
	
<script type="text/javascript">
var projID=document.SelctDropdownUserForm.Select_Project.value;	
if(projID==""){
	projID="0";
}
//alert("Usr task Jsp"+projID );
document.UserEditProjectForm.hiddenIDForUserUpdt.value = projID;
</script>

</head>
<body onload="">


<div class="container" style="margin-top: 5px;">		    
  <div class="row">
	  <div class="container" style="margin-top: 5px;">
 				         
		<div id="iHeadUserDropdown">				
	  <div class="col-md-12">   
		  <form name="UserEditProjectForm" action="../UserEditProjectController" id="iUserEditProjectForm" method="post" >					 
		     
	      <div class="col-md-9">
			    <div class="alert alert-info clsPageHeadingPanel">
			             <div class="clsHeading">		               
			              Task Details		             
			             </div>  			                                  
	            </div>
	            
	          <table class="table table-bordered table-striped" id="iProjectUpdtNewTbl">
						<tr>
						<td align="center" ><label class="clsFormLbl">ID</label></td>
		  				<td align="center" ><label class="clsFormLbl">Category</label></td>
		  				<td align="center" ><label class="clsFormLbl">Task</label></td>
		  				<td align="center" ><label class="clsFormLbl">Amount</label></td>		  				
		  				<td align="center" ><label class="clsFormLbl">User</label></td>
		  				<td align="center" ><label class="clsFormLbl">Remark</label></td>
		  				<td align="center" ><label class="clsFormLbl">Work Status</label></td>
		  			</tr> 
		  			</table>
	          
	           <div class="col-md-9" align="center">
	           <table>
	         			<tr>
							<td align="center" colspan="2">
								<input type="hidden"  name="hiddenIDForUserUpdt" id="proId">
								<div class="ClsPdHideForUpdt">
									<input type="submit" value="Update" class="btn btn-sm btn-primary" id="iProjectUpdt">
									<a class="btn btn-sm btn-primary" href="#">Refresh</a>
								</div>
							</td>
					    </tr>
	        	</table>
	           </div>
	           </div>
	   </form>
	</div>	
	
			</div>
		</div>	
	</div>
</div>


<!-- 
<script type="text/javascript" src="../JS/tts/utility.js"></script>
<script type="text/javascript" src="../JS/tts/ajaxUtility.js"></script> -->

<script type="text/javascript">
 $(function()
		{
	  		var getCategoryName;
	  		if(window.XMLHttpRequest)
			{
	  			getCategoryName = new XMLHttpRequest();
			}
			else
			{
				getCategoryName = new ActiveXObject("Microsoft.XMLHttp");
			}
	  		getCategoryName.onreadystatechange=function()
			{
	  			if(getCategoryName.readyState==4 && getCategoryName.status==200)
				{
	  				var result=getCategoryName.responseText;
	  				//alert(result);
	  				var getcategory=JSON.parse(result);
					if(getcategory.JsonCatgList.length>0)
					{
						for(var i=0;getcategory.JsonCatgList.length;i++)
						{	
							
							$('#iSelectAllCategories').append($('<option>').text(getcategory.JsonCatgList[i].catgName).attr('value',getcategory.JsonCatgList[i].catgId));
						}
					} 
		 		}
			};
			getCategoryName.open('POST','../AjaxUtilityController?&req_type=GetAllCatgNameID',false);
			getCategoryName.send();
	  	});	


$(function()
		{
	  		var xmlUserHttpObj;
	  		if(window.XMLHttpRequest)
			{
	  			xmlUserHttpObj = new XMLHttpRequest();
			}
			else
			{
				xmlUserHttpObj = new ActiveXObject("Microsoft.XMLHttp");
			}
	  		xmlUserHttpObj.onreadystatechange=function()
			{
	  			if(xmlUserHttpObj.readyState==4 && xmlUserHttpObj.status==200)
				{
	  				var result=xmlUserHttpObj.responseText;
	  				//alert(result);
	  				var getcategory=JSON.parse(result);
					if(getcategory.JsonUserList.length>0)
					{
						for(var i=0;getcategory.JsonUserList.length;i++)
						{	
							var fName = getcategory.JsonUserList[i].fname;
							var lName  =getcategory.JsonUserList[i].lname
							//$('#iUserList').append($('<option>').text(fName+" "+lName).attr('value',getcategory.JsonUserList[i].userId));
							$('#iEditReviwer').append($('<option>').text(fName+" "+lName).attr('value',getcategory.JsonUserList[i].userId));
							
						}
					} 
		 		}
			};
			xmlUserHttpObj.open('POST','../AjaxUtilityController?&req_type=getUserInfo',false);
			xmlUserHttpObj.send();
			
			loadProjectData(projID);
	  	});

function loadProjectData(projID){
	var xmlHttp;
	if(window.XMLHttpRequest)
	{
		xmlHttp =new XMLHttpRequest(); 
	}
	else
	{
		xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
	}
	xmlHttp.onreadystatechange=function()
	{
		if(xmlHttp.readyState==4 && xmlHttp.status==200)
		{
			
			var resultProjectDetails=xmlHttp.responseText; 
		//	alert("loadProjectData "+resultProjectDetails);
			 var ProjDetailsObj= JSON.parse(resultProjectDetails);  
		  		if (ProjDetailsObj.JsonProjectInfoEditList.length <= 0) 
		  		{
		  			return false;
		  		}
		  		else
		  		{    
		  			$("#iEditFirm").val(ProjDetailsObj.JsonProjectInfoEditList[0].firmName);		  			
		  			$("#iEditProjectName").val(ProjDetailsObj.JsonProjectInfoEditList[0].projname);
		  			//alert(ProjDetailsObj.JsonProjectInfoEditList[0].rivewerName+":"+ProjDetailsObj.JsonProjectInfoEditList[0].reviewerid);
		  			$("#iEditReviwer").val(ProjDetailsObj.JsonProjectInfoEditList[0].reviewerid);
		  			$("#iEditRemark").val(ProjDetailsObj.JsonProjectInfoEditList[0].remark);
		  			var startDt=ProjDetailsObj.JsonProjectInfoEditList[0].startDt;								
					$("#iEditStartDate").val(moment(startDt).format("DD-MMM-YYYY"));
		  			
		  		}
			
		} 
	};
	xmlHttp.open('POST','../AjaxUtilityController?&req_type=GetProjectInfoForEdit&proj_Id='+projID,false);
	xmlHttp.send();
	getProjTaskDetailsUpd(projID);
		}
		
function getProjTaskDetailsUpd(projID)
{
	if(projID=="")
	{
		alert("Something goes wrong");
		return false;
	}
	else
	{
		if(window.XMLHttpRequest)
		{
			xmlTaskHttp =new XMLHttpRequest();
		}
		else
		{
			xmlTaskHttp =new ActiveXObject("Microsoft.XMLHttp");
		}
		xmlTaskHttp.onreadystatechange=function()
		{
			if(xmlTaskHttp.readyState==4 && xmlTaskHttp.status==200)
				{	
					var result=xmlTaskHttp.responseText;
					//alert(result);
					var getProTaskDetail=JSON.parse(result);
					if(getProTaskDetail.JsonProTaskList.length>0)
					{
						   for(var i=0;i<getProTaskDetail.JsonProTaskList.length;i++)
							{
								var j=i+1;
								
								//$('#iProjectUpdtNewTbl tr:last').after('<tr><td>'+j+'</td><td><select name="category[]" class="input-sm form-control" id="iCatgList'+j+'" ><option value="'+getProTaskDetail.JsonProTaskList[i].catgId+'">'+getProTaskDetail.JsonProTaskList[i].catgId+'</option></select></td ></tr>');
								$('#iProjectUpdtNewTbl tr:last').after('<tr><td align="right" style="width: 5%"><input id="iAssignID'+j+'" type="text" name="assignID[]" readonly   class="input-sm form-control" value="'+getProTaskDetail.JsonProTaskList[i].projassignId+'"/></td><td style="width: 20%"><select name="Category[]" readonly class="input-sm form-control" id="iCatgList'+j+'" ><option value="'+getProTaskDetail.JsonProTaskList[i].catgId+'">'+getProTaskDetail.JsonProTaskList[i].catgName+'</option></select></td ><td style="width: 20%"><select name="taskname[]" readonly class="input-sm form-control" id="iTaskNewList'+j+'"></select></td><td style="width: 10%"><input id="iTotal'+j+'" type="text" style="text-align:right;" maxlength="10" name="Amount[]" readonly class="input-sm form-control" value="'+getProTaskDetail.JsonProTaskList[i].amount+'"/></td><td><select name="username[]" readonly class="input-sm form-control" id="iUserList'+j+'"></select></td><td><input id="iRemark'+j+'" name="remark[]" class="input-sm form-control pqrs"  type="text" value="'+getProTaskDetail.JsonProTaskList[i].remark+'" /></td><td style="width: 10%"><select name="workstatus[]"  class="input-sm form-control" id="iWrkStatus'+j+'" ><option value="N">NO</option><option value="Y">YES</option></select></td ></tr>');
						
								DrawAllItemValueIntoSelect(getProTaskDetail.JsonProTaskList[i].catgId,j);
								$("#iTaskNewList"+j).val(getProTaskDetail.JsonProTaskList[i].taskId); 
								DrawAllUserValuesIntoSelect(j);
								$("#iUserList"+j).val(getProTaskDetail.JsonProTaskList[i].userId); 
								
								$("#iWrkStatus"+j).val(getProTaskDetail.JsonProTaskList[i].workStatus);
						} 
						}
						else{
							$("#iProjectUpdt").attr('disabled',true);						
			  				$('#iProjectUpdtNewTbl tr:last').after('<tr><td colspan="6">No Task items to display.</td></tr>');
						}
					
					}
				};
		};
	
		xmlTaskHttp.open('POST','../AjaxUtilityController?&req_type=GetProjTaskDetails&projID='+projID,false);
		xmlTaskHttp.send();
}
function DrawAllItemValueIntoSelect(catgId,SelectIndex)
{
	  if(window.XMLHttpRequest)
		{
			getCatgItemXML=new XMLHttpRequest();
		}
		else
		{
			getCatgItemXML=new ActiveXObject("Microsoft.XMLHttp");
		}
	
	  getCatgItemXML.onreadystatechange=function ()
		{	  				
			if(getCatgItemXML.readyState==4 && getCatgItemXML.status==200)
			{	
				var result=getCatgItemXML.responseText;
				var getAllCatgItem=JSON.parse(result);
				if(getAllCatgItem.JsonTaskList.length>0)
				{  						
					$('#iTaskNewList'+SelectIndex+" option").remove();
					for(var i=0;i<getAllCatgItem.JsonTaskList.length;i++)
					{  
						$("#iTaskNewList"+SelectIndex).append($('<option>').text(getAllCatgItem.JsonTaskList[i].taskName).attr('value', getAllCatgItem.JsonTaskList[i].taskId));
					}
					
				}
			}
		};
		getCatgItemXML.open('POST','../AjaxUtilityController?req_type=GetCatgTask&catgid='+catgId,false);
		getCatgItemXML.send();
}		
function DrawAllUserValuesIntoSelect(SelectIndex){
	var xmlUserHttpObj;
		if(window.XMLHttpRequest)
	{
			xmlUserHttpObj = new XMLHttpRequest();
	}
	else
	{
		xmlUserHttpObj = new ActiveXObject("Microsoft.XMLHttp");
	}
		xmlUserHttpObj.onreadystatechange=function()
	{
			if(xmlUserHttpObj.readyState==4 && xmlUserHttpObj.status==200)
		{
				var result=xmlUserHttpObj.responseText;
				//alert(result);
				var getcategory=JSON.parse(result);
			if(getcategory.JsonUserList.length>0)
			{
				$('#iUserList'+SelectIndex+" option").remove();
				for(var i=0;getcategory.JsonUserList.length;i++)
				{	
					var fName = getcategory.JsonUserList[i].fname;
					var lName  =getcategory.JsonUserList[i].lname
					$("#iUserList"+SelectIndex).append($('<option>').text(fName+" "+lName).attr('value',getcategory.JsonUserList[i].userId));
					
					
				}
			} 
 		}
	};
	xmlUserHttpObj.open('POST','../AjaxUtilityController?&req_type=getUserInfo',false);
	xmlUserHttpObj.send();
	
}	
$(document).ready(function(){
	
	$('.clsMessageContainer').delay(500).fadeOut(function(){	        
        ResponseComplete();
    
    });

});

function ResponseWaitingForSuccess() 
{	
		$('<div id="iWaitResponse" class="modal-backdrop clsResponseWaiting"><div class="alert alert-success clsMessageContainer"><div class="clsSuccessMsg"><%=session.getAttribute("successMsg")%></div> </div></div>').appendTo(document.body);
		
}
function ResponseWaitingForFailure() 
{	
	$('<div id="iWaitResponse" class="modal-backdrop clsResponseWaiting"><div class="alert alert-danger clsMessageContainer"><div class="clsFailureMsg"><%=session.getAttribute("failureMsg")%></div> </div></div>').appendTo(document.body);
}
function ResponseComplete() {	
		$('#iWaitResponse').remove();
	}	
</script>
	<%	
if(session.getAttribute("successMsg")!=null){
%>        
		<script type="text/javascript">ResponseWaitingForSuccess();</script>
<%	
       session.removeAttribute("successMsg");
	}if(session.getAttribute("failureMsg")!=null){
%>
		<script type="text/javascript">ResponseWaitingForFailure();</script>
<%    
     session.removeAttribute("failureMsg"); 
	}
%>
</body>
</html>