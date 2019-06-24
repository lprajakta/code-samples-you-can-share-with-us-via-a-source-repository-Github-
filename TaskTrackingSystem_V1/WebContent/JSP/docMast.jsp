<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Document Details</title>
<script type="text/javascript">
    $(document).ready(function () {
        $('#DocumentTableContainer').jtable({
            title: '<center>List Of Document</center>'+'<center><div class=""><form><input type="text" name="name" id="name" style="color: black;" /><button type="submit" id="LoadRecordsButton"><font color="blue">Search </font></button></form></div></center>',
            paging: true, //Enable paging
            /* selecting: true, //Enable selecting
            multiselect: true, //Allow multiple selecting
            selectingCheckboxes: true, //Show checkboxes on first column */
            actions: {
                listAction: '../DocMastController?action=list',
                createAction:'../DocMastController?action=create',
                updateAction: '../DocMastController?action=update',
                deleteAction: '../DocMastController?action=delete'
            },
            fields: {
                docId: {
                	title:'DocId',
                    key: true,
                    list: true,
                    create:false,
                },
                docName: {
                    title: 'Doc Name',
                    width: '30%',
                    edit:true,
                },
                details: {
                    title: 'Details',
                    width: '50%',
                    edit:true,
                },
                docStatus: {
                    title: 'Document',
                  //  edit: true,
                    type: 'radiobutton',
                    create:false,
                    edit: false,
                    list: false,
                    options: [
                        { Value: 'Y', DisplayText: 'Yes'},
                        { Value: 'N', DisplayText: 'No' }
                       ]
                },
                remark: {
                    title: 'Remark',
                    width: '20%',
                    edit: false,
                    create:false,
                    list: false,
                    type: 'textarea',
                }
            }
        });
        $('#LoadRecordsButton').click(function (e) {
        	e.preventDefault();
        	$('#DocumentTableContainer').jtable('load', {
        		name: $('#name').val(),
        	});
        });
        //Load all records when page is first shown
        $('#LoadRecordsButton').click();
       });
</script>
</head>
<body>
<div style="width:100%;text-align:center;">

<!-- <div class="filtering">
    <form>
        DocumentName: <input type="text" name="name" id="name" />
        <button type="submit" id="LoadRecordsButton">Search Document</button>
    </form>

 -->
 <div id="DocumentTableContainer"></div>
</div>
</body>
</html>