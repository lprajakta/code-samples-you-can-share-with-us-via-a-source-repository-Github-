/***************for floor details on Project select**********************************/
function GetCategOnProjSelect(projID)
{	
	
	if (projID=="" || projID==null)
	{ 
		clearCatgDropdown();
		clearTaskDropdown();
		selectsite();
		//GetBuildOnSiteSelect(siteID);
		return false;
	}
	else
	{	
	//selectsite();
	//document.getElementById("SelectionFloor").innerHTML = document.getElementById("SelectionFloor").innerHTML ;
		
		selectBuild();	
//	$("#tabelFloorSelection").css("display", "none");
	/***********************clear dropdowns*************************/
	clearCatgDropdown();
	clearTaskDropdown();	
	var categoryCatg=$('.selectPickerFloor').selectpicker();	
	
	//displayBuildingDetails1();	
	callajaxFunction();
	var index=0;
	
	if (window.XMLHttpRequest)
	{	// code for IE7+, Firefox, Chrome, Opera, Safari
		xmlCatgNameID=new XMLHttpRequest();
	}
	else
	{	// code for IE6, IE5
		xmlCatgNameID=new ActiveXObject("Microsoft.XMLHTTP");
	}
	xmlCatgNameID.onreadystatechange=function()
	{
		
		if (xmlCatgNameID.readyState==4 && xmlCatgNameID.status==200)
		{ 
			var resultCatgList=xmlCatgNameID.responseText; 
			
			var catgObj= JSON.parse(resultCatgList); 
			
			if (catgObj.JsonCatgList.length <= 0)
			{
				clearCatgDropdown();
				return false;
			}
			else 
			{  
				
				categoryCatg.append('<option value="">-SELECT CATG-</option>');	
				
				for(var z=1;z<=catgObj.JsonCatgList.length;z++)
				{
					
					var categID=catgObj.JsonCatgList[index].catgId;
					
					var categName=catgObj.JsonCatgList[index].catgName;
					
					categoryCatg.append('<option value='+categID+'>'+categName+'</option>');	
					index = index+1;				
				}
				categoryCatg.selectpicker('refresh');
			 } 
			categoryCatg.selectpicker('refresh'); 				   
		}
	}; 
	}
	xmlCatgNameID.open('POST',"../AjaxUtilityController?req_type=GetCatgDetails&proj_Id="+projID,false);
	xmlCatgNameID.send();
	includeUserTaskJsp();
}
function includeUserTaskJsp(){
	
	$('#ProjTasks').load("../JSP/UserTaskDetail.jsp");
}
function callajaxFunction(){
	var projId=document.SelctDropdownUserForm.Select_Project.value;
	
	//var projId=1;
	
	if(projId=="" || projId==null)
	{
		
		return false;
	}
	var xmlHttpRequest = (window.XMLHttpRequest) ? new window.XMLHttpRequest() : new ActiveXObject('Microsoft.XMLHTTP');
	
	xmlHttpRequest.onreadystatechange=function()
	{
		
		if (xmlHttpRequest.readyState==4 && xmlHttpRequest.status==200)
		{ 		
			
			var resultProjectDetails=xmlHttpRequest.responseText; 
			//alert(resultProjectDetails);
			var ProjDetailsObj= JSON.parse(resultProjectDetails);  
	  		if (ProjDetailsObj.JsonProjectInfoList.length <= 0) 
	  		{
	  			return false;
	  		}
	  		else
	  		{    
	  			document.getElementById("iProj_ID").innerHTML=ProjDetailsObj.JsonProjectInfoList[0].projId;	  			
	  			document.getElementById("iProj_Name").innerHTML=ProjDetailsObj.JsonProjectInfoList[0].projname;
	  			
	  			var cretdDt=ProjDetailsObj.JsonProjectInfoList[0].startDt;
	  			
	  			document.getElementById("iProj_Cretd_Dt").innerHTML=cretdDt;
	  			
	  			var status=ProjDetailsObj.JsonProjectInfoList[0].status;
	  			
	  			document.getElementById("iProj_Status").innerHTML=status;  		  			
	  			document.getElementById("iProj_riewer").innerHTML=ProjDetailsObj.JsonProjectInfoList[0].rivewerName;
	  			document.getElementById("iProj_trust").innerHTML=ProjDetailsObj.JsonProjectInfoList[0].firmName;  	
	  			  	
	  		/*	document.getElementById("iProj_ID_Catg").innerHTML=ProjDetailsObj.JsonObjBuildingDetailsList[0].buildId;
	  			document.getElementById("iProj_Name_Catg").innerHTML=ProjDetailsObj.JsonObjBuildingDetailsList[0].buildName;
	  			document.getElementById("iProj_CretdDt_Catg").innerHTML=buildName;
	  			document.getElementById("iProj_Description_Catg").innerHTML=desc;*/
	  		}
		}
	}; 
	xmlHttpRequest.open('POST',"../AjaxUtilityController?req_type=GetProjectInfo&proj_Id="+projId,false);
	xmlHttpRequest.send(); 
}






