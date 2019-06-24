<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 <title>Category Master</title>
<!-- Include one of jTable styles. -->
<link href="../CSS/metro/crimson/jtable.css" rel="stylesheet" type="text/css" />
<link href="../CSS/jquery-ui-1.10.3.custom.css" rel="stylesheet" type="text/css" />
<script src="../JS/jquery-1.8.2.js" type="text/javascript"></script>
<script src="../JS/jquery-ui-1.10.3.custom.js" type="text/javascript"></script>
<script src="../JS/jquery.jtable.js" type="text/javascript"></script>
<!-- Import CSS file for validation engine (in Head section of HTML) -->
<link href="../CSS/validationEngine.jquery.css" rel="stylesheet" type="text/css" />
 
<!-- Import Javascript files for validation engine (in Head section of HTML) -->
<script type="text/javascript" src="../JS/jquery.validationEngine.js"></script>
<script type="text/javascript" src="../JS/jquery.validationEngine-en.js"></script>
 <script type="text/javascript">
 
    $(document).ready(function () {
 
        $('#CategoryTableContainer').jtable({
            title: 'Category List',
            paging: true, //Enable paging
            pageSize: 5,
            searching:true, 
            sorting: true, //Enable sorting
            defaultSorting: 'catgName ASC',
            actions: {
                listAction: '../CategoryMastController?action=list',
                createAction:'../CategoryMastController?action=create',
                updateAction: '../CategoryMastController?action=update',
                deleteAction: '../CategoryMastController?action=delete'
            },
            fields: {
            	catgId: {
                	title:'category id',
                    key: true,
                    list: true,
                    create:false,
                },
                catgName: {
                    title: 'Category Name',
                    width: '30%',
                    edit:true
                },
                catgDesc: {
                    title: 'Description',
                    type:'textarea',
                    edit:true
                }
             
            },
            //Initialize validation logic when a form is created
            formCreated: function (event, data) {
                data.form.find('input[name="catgName"]').addClass('validate[required]');
                data.form.find('input[name="catgDesc"]').addClass('validate[required]');
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
 
        //Load student list from server
        $('#CategoryTableContainer').jtable('load');
    });
 
</script></head>
<body>
<div style="width:100%;margin-right:0 auto%;margin-left:0 auto%;text-align:0 auto;">
<h4>Task Categories</h4>
<div id="CategoryTableContainer"></div>
</div>
</body>
</html>