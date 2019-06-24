<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 <title>task Master</title>
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
 
        //Prepare jtable plugin
        $('#TaskTableContainer').jtable({
            title: 'Task List',
            paging: true,
            sorting: true,
            defaultSorting: 'Name ASC',
            //selecting: true, 
            //Enable selecting
            //multiselect: true, //Allow multiple selecting
            selectingCheckboxes: true, //Show checkboxes on first column
            //selectOnRowClick: false, //Enable this to only select using checkboxes
            
             actions: {
                        listAction: '../TaskController?action=list',
                        deleteAction: '../TaskController?action=delete',
                        updateAction: '../TaskController?action=update',
                        createAction: '../TaskController?action=create'
                     },
                    fields: {
                    	taskId: {
                            key: true,
                            create: false,
                            edit: false,
                            list: false
                        },
                        catgId:{
                            title: 'Category',
                            options: '../TaskController?action=getCatgList',
                            list: false
                        },
                      
                        taskName: {
                            title: 'Task Name',
                            width: '20%'
                        },
                        
                        taskDesc: {
                            title: 'Description',
                            type:'textarea',
                            edit:true
                        }
                       
                       
                        /* ,
                        CountryId: {
                            title: 'Country',
                            dependsOn: 'ContinentalId', //Countries depends on continentals. Thus, jTable builds cascade dropdowns!
                            options: function (data) {
                                if (data.source == 'list') {
                                    //Return url of all countries for optimization. 
                                    //This method is called for each row on the table and jTable caches options based on this url.
                                    return '/Demo/GetCountryOptions?continentalId=0';
                                }
         
                                //This code runs when user opens edit/create form or changes continental combobox on an edit/create form.
                                //data.source == 'edit' || data.source == 'create'
                                return '/Demo/GetCountryOptions?continentalId=' + data.dependedValues.ContinentalId;
                            },
                            list: false
                        },
                        CityId: {
                            title: 'City',
                            width: '30%',
                            dependsOn: 'CountryId', //Cities depends on countries. Thus, jTable builds cascade dropdowns!
                            options: function (data) {
                                if (data.source == 'list') {
                                    //Return url of all cities for optimization. 
                                    //This method is called for each row on the table and jTable caches options based on this url.
                                    return '/Demo/GetCityOptions?countryId=0';
                                }
         
                                //This code runs when user opens edit/create form or changes country combobox on an edit/create form.
                                //data.source == 'edit' || data.source == 'create'
                                return '/Demo/GetCityOptions?countryId=' + data.dependedValues.CountryId;
                            }
                        },
                        BirthDate: {
                            title: 'Birth date',
                            type: 'date',
                            displayFormat: 'yy-mm-dd',
                            list: false
                        },
                        Education: {
                            title: 'Education',
                            list: false,
                            type: 'radiobutton',
                            options: [
                                { Value: '1', DisplayText: 'Primary school' },
                                { Value: '2', DisplayText: 'High school' },
                                { Value: '3', DisplayText: 'University' }
                            ]
                        },
                        About: {
                            title: 'About this person',
                            type: 'textarea',
                            list: false
                        },
                        IsActive: {
                            title: 'Status',
                            width: '15%',
                            type: 'checkbox',
                            values: { 'false': 'Passive', 'true': 'Active' },
                            defaultValue: 'true'
                        },
                        RecordDate: {
                            title: 'Record date',
                            width: '25%',
                            type: 'date',
                            displayFormat: 'yy-mm-dd',
                            create: false,
                            edit: false,
                            sorting: false //This column is not sortable!
                        } */
                    },
                    formCreated: function (event, data) {
                        data.form.find('input[name="taskName"]').addClass('validate[required]');
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
                $('#TaskTableContainer').jtable('load');
            });
         
        </script>
          
            </head>
<body>
<div style="width:100%;margin-right:0 auto%;margin-left:0 auto%;text-align:0 auto;">
<h4>Task</h4>
<div id="TaskTableContainer"></div>
</div>
</body>
</html>