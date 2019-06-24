<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 <title>project Mast</title>
<!-- Include one of jTable styles. -->
 <link href="../CSS/metro/crimson/jtable.css" rel="stylesheet" type="text/css" />
<link href="../CSS/jquery-ui-1.10.3.custom.css" rel="stylesheet" type="text/css" />
<script src="../JS/jquery-1.8.2.js" type="text/javascript"></script>
<script src="../JS/jquery-ui-1.10.3.custom.js" type="text/javascript"></script>
<script src="../JS/jquery.jtable.js" type="text/javascript"></script>

<link href="../CSS/validationEngine.jquery.css" rel="stylesheet" type="text/css" />
 

<script type="text/javascript" src="../JS/jquery.validationEngine.js"></script>
<script type="text/javascript" src="../JS/jquery.validationEngine-en.js"></script> 

<script type="text/javascript">

$(document).ready(function () {
 
        //Prepare jtable plugin
        $('#ProjectTableContainer').jtable({
            title: 'Project Mast',
            paging: true, //Enable paging
            pageSize: 5,
            searching:true, 
            sorting: true,
            defaultSorting: 'Name ASC',
            //selecting: true, 
            //Enable selecting
            //multiselect: true, //Allow multiple selecting
            //selectingCheckboxes: true, //Show checkboxes on first column
            //selectOnRowClick: false, //Enable this to only select using checkboxes
            
             actions: {
                        listAction: '../ProjectMastControllers?action=list',
                        deleteAction: '../ProjectMastControllers?action=delete',
                        updateAction: '../ProjectMastControllers?action=update',
                        createAction: '../ProjectMastControllers?action=create'
                    },
                    fields: {
                    	projId: {
                            key: true,
                            create: false,
                            edit: false,
                            list: false
                       },
          
                        firmId: {
                            title: 'Firm Name ',
                            options: '../ProjectMastControllers?action=getFirmList',
                            list:false
                        }, 
                        
                        
                      
                        projname: {
                            title: 'Poject Name',
                            width: '20%'
                        },
                        fees: {
                            title: 'Fees ',
                            width: '10%'
                        },
                        openingFees: {
                            title: 'Open Fees ',
                            width: '10%'
                        },
                        createdDt: {
                            title: 'Create Date',
                            type: 'date',
                            displayFormat: 'yy-mm-dd',
                            list: true
                        },
                        
                        expectedEnddt: {
                            title: 'Expected End Date',
                            type: 'date',
                            displayFormat: 'yy-mm-dd',
                            width: '15%',
                            list: true
                        },
                        endDt: {
                              title: 'End Date',
                              type: 'date',
                              displayFormat: 'yy-mm-dd',
                              list: true
                           },
                            
                             
                             status: {
                            	 title: 'status',
                                 options: { 'N': 'New', 'O': 'Open','H': 'Hold','C': 'Completed' },
                                 list: true
                                 
                            },
                             
                           remark: {
                            title: ' Remark',
                            type:'textarea',
                            edit:true
                        },
                        reviewerid: {
                                title: 'Reviewer Name ',
                                options: '../ProjectMastControllers?action=getReviewerList',
                                list:false
                          },
                          activestatus: {
                         	 title: 'Activestatus',
                             options: { 'A': 'Active', 'I': 'Inactive' },
                             list: true
                             
                        },
                        
                       
                  }
                  
                  
                 
        });
         
                
                $('#ProjectTableContainer').jtable('load');
                
            });
         
        </script>
         
            </head>
<body>
  <jsp:include page="../JSP/Home.jsp"/>
<div style="width:100%;text-align:center;">
<div id="ProjectTableContainer"></div>
</div>
</body>
</html>
