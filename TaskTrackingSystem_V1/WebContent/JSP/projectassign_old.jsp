<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 <title>Project Assign</title>
 
<!-- Include one of jTable styles. -->
<link href="../CSS/metro/crimson/jtable.css" rel="stylesheet" type="text/css" />
<link href="../CSS/jquery-ui-1.10.3.custom.css" rel="stylesheet" type="text/css" />
<script src="../JS/jquery-1.8.2.js" type="text/javascript"></script>
<script src="../JS/jquery-ui-1.10.3.custom.js" type="text/javascript"></script>
<script src="../JS/jquery.jtable.js" type="text/javascript"></script>
<!-- Import CSS file for validation engine (in Head section of HTML) -->
<link href="../CSS/validationEngine.jquery.css" rel="stylesheet" type="text/css" />
 
<!-- Import Javascript files for validation engine (in Head section of HTML) -->
<script type="text/javascript" src="JS/jquery.validationEngine.js"></script>
<script type="text/javascript" src="JS/jquery.validationEngine-en.js"></script>


<script type="text/javascript">
 
    $(document).ready(function () {
 
        //Prepare jtable plugin
        $('#ProjectAssignTableContainer').jtable({
            title: 'Project Assign List',
            paging: true,
            sorting: true,
            actions: {
                        listAction: '../ProjectAssignController?action=list',
                        deleteAction: '../ProjectAssignController?action=delete',
                        updateAction: '../ProjectAssignController?action=update',
                        createAction: '../ProjectAssignController?action=create'
                    },
                    fields: {
                    	projassignId: {
                            key: true,
                            create: false,
                            edit: false,
                            list: false
                            
                        },
                        userId:{
                            title: 'User Name',
                            options: '../ProjectAssignController?action=getUserList',
                            list: true
                        },
                        
                        projId:{
                            title: 'Project Name',
                            options: '../ProjectAssignController?action=getProjectList',
                            list: true
                        }
                       
                    }
                });
         
                
                $('#ProjectAssignTableContainer').jtable('load');
            });
         
        </script>
          
            </head>
<body>
<div style="width:40%;margin-right:15%;margin-left:15%;text-align:center;">
<h4></h4>
<div id="ProjectAssignTableContainer"></div>
</div>
</body>
</html>