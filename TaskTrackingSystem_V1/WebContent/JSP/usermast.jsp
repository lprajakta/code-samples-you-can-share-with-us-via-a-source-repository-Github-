<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
 <title>User Master</title>
 
<!-- Include one of jTable styles. -->
<!-- <link href="../CSS/metro/crimson/jtable.css" rel="stylesheet" type="text/css" />
<link href="../CSS/jquery-ui-1.10.3.custom.css" rel="stylesheet" type="text/css" /> -->

<!-- Include jTable script file. -->
<!-- <script src="../JS/jquery-1.8.2.js" type="text/javascript"></script>
<script src="../JS/jquery-ui-1.10.3.custom.js" type="text/javascript"></script>
<script src="../JS/jquery.jtable.js" type="text/javascript"></script> -->

<!-- Import CSS file for validation engine (in Head section of HTML) -->
<!-- <link href="../CSS/validationEngine.jquery.css" rel="stylesheet" type="text/css" /> -->
 
<!-- Import Javascript files for validation engine (in Head section of HTML) -->
<!-- <script type="text/javascript" src="../JS/jquery.validationEngine.js"></script>
<script type="text/javascript" src="../JS/jquery.validationEngine-en.js"></script> -->
 
<script type="text/javascript">
    $(document).ready(function () {
               $('#UserTableContainer').jtable({
               title: 'Table of User',
               
               paging: true, //Enable paging
               pageSize: 10, //Set page size (default: 10)
               sorting: true, //Enable sorting
               
                actions: {
                listAction: '../UserMastController?action=list',
                createAction:'../UserMastController?action=create',
                updateAction: '../UserMastController?action=update',
                deleteAction: '../UserMastController?action=delete'
            },
            fields: {
            	
            	 
            	userId: {
                	title:'S. No.',
                    key: true,
                    list: false,
                    create:false
                },
            	            	
                empId: {
                	title:'Emp Id',
                	width: '5%',
                    edit:true
                },
                fname: {
                    title: 'First Name',
                    edit:true
                },
                lname: {
                    title: 'Last Name',
                    edit:true
                },
                
                emailid: {
                    title: 'Email Id',
                    inputClass: 'validate[required,custom[email]]',
                    edit:true
                },
                mobileno: {
                    title: 'MobileNo',
                    edit:true
                },
               username: {
                    title: 'User Name',
                    edit:true
                },
                password: {
                    title: 'Password',
                    type:'password',
                    list: false,
                    edit:true
                },
                userrole: {
                    title: 'UserRole',
                    options: { 'A': 'Admin', 'S': 'Staff','U': 'User' },
                    list: false
      
                },
                status: {
                	 title: 'status',
                     options: { 'A': 'Active', 'I': 'Inactive' },
                     list: false
                     
                }
                
                                
            },
       
            //Initialize validation logic when a form is created
            formCreated: function (event, data) {
            	data.form.find('input[name="empId"]').addClass('validate[required]');
            	data.form.find('input[name="fname"]').addClass('validate[required]');
            	data.form.find('input[name="lname"]').addClass('validate[required]');
            	data.form.find('input[name="emailId"]').addClass('validate[required]');
            	data.form.find('input[name="mobileno"]').addClass('validate[required]');
                data.form.find('input[name="username"]').addClass('validate[required]');
                data.form.find('input[name="password"]').addClass('validate[required]');
                data.form.find('input[name="userrole"]').addClass('validate[required]');
                data.form.find('input[name="status"]').addClass('validate[required]');
                data.form.validationEngine();
             },
            //Validate form when it is being submitted
            formSubmitting: function (event, data) {
                return data.form.validationEngine('validate');
            },
            //Dispose validation logic when form is closed
            formClosed: function (event, data) {
                data.form.validationEngine('hide');
                data.form.validationEngine('detach');
            }
           });
               
        $('#UserTableContainer').jtable('load');
    });
</script>
</head>
<body>
<div style="width:100%;text-align:center;">

<div id="UserTableContainer"></div>
</div>

</body>
</html>