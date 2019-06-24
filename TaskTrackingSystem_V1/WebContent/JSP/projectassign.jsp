<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%-- <%@page import="com.itrix.model.ClientMastBean"%> --%>
<%@page import="com.itrix.model.UserMastBean"%>
<%@page import="com.itrix.model.FirmModel"%>
<%@page import="com.itrix.service.UtilityService"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

 
<title>Smart-Project Assign</title>
  
<script type="text/javascript">
/*****************************On load site function to get sites********************************/
function onLoadSelectTrust()
{	alert("load trust");
	//var SelectSite=$('.selectPickerTrust').selectpicker();	
		
	/* SelectSite.selectpicker('refresh');	
	var categoryBuild=$('.selectPickerBuilding').selectpicker();	
	categoryBuild.selectpicker('refresh');	
	var categoryFloor=$('.selectPickerFloor').selectpicker();	
	categoryFloor.selectpicker('refresh');	
	var categoryPanel=$('.selectPickerPanel').selectpicker();	
	categoryPanel.selectpicker('refresh');	 */
	
}

</script>
</head>
<body onload="onLoadSelectTrust();">

<div class="container" style="margin-top: 5px;">		    
  <div class="row">
	 
 			       
		         
		<div id="iHeadUserDropdown">				
	    <!-- <form name="SelctDropdown">
	        <table class="table table-striped" id="iHomeSearchTbl">			    
	        	<tbody>		   		
			        <tr>   	
		    	        <td width="24%">
					        <div class="form-group">
					         <label class="FieldLabel">Select Trust</label>
						     <select name="Select_Firm" data-width="100%" onchange="GetProjOnTrustSelect(this.value)"  class="selectPickerTrust show-tick show-menu-arrow" data-live-search="true" style="text-transform:uppercase">
						     </select>
					       </div>
				       </td>	
		    	     <td width="24%">
				           	<div class="form-group">
				           	    <label class="FieldLabel">Select Project</label>	
					            <select name="Select_Project" data-width="100%" onchange="GetCategOnProjSelect(this.value)" class="selectPickerBuilding show-tick show-menu-arrow" data-live-search="true" style="text-transform:uppercase">
								</select>
					       	</div>
		        	   </td>
		        	    <td width="24%">
				           	<div class="form-group">
				           	    <label class="FieldLabel">Select Category</label>	
					             <select name="Select_Catg" data-width="100%" onchange="GetTaskOnCatgSelect(this.value)"  class="selectPickerFloor show-tick show-menu-arrow" data-live-search="true" style="text-transform:uppercase">
								</select>
					       	</div>
				        </td>
				        <td width="24%">
				           	<div class="form-group">
				           	<label class="FieldLabel">Select Task</label>
					            <select name="Select_Task" data-width="100%"  onchange="displaysubTaksDetails(this.value)" class="selectPickerPanel show-tick show-menu-arrow" data-live-search="true" style="text-transform:uppercase">
								</select>
					       	</div>
				        </td>
				       <td width="4%">
				           <div class="form-group">			           	
					            <button type="reset" title="Refresh" class="refButn glyphicon glyphicon-refresh" onclick=""></button>
					       	</div>
				           
				       </td>
				 </tr>
		    </tbody>
	      </table> 	
	   </form> -->
	</div>		
	</div>
</div>



</body>
</html>