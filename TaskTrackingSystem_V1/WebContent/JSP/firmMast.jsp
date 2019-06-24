<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 <title>Firm Master</title>
<!-- Include one of jTable styles. -->
<!-- <link href="../CSS/metro/crimson/jtable.css" rel="stylesheet" type="text/css" />
<link href="../CSS/jquery-ui-1.10.3.custom.css" rel="stylesheet" type="text/css" /> -->
<!-- Import CSS file for validation engine (in Head section of HTML) -->
<!-- <link href="../CSS/validationEngine.jquery.css" rel="stylesheet" type="text/css" /> -->
 

 <script type="text/javascript">
    $(document).ready(function () {
               $('#PersonTableContainerFM').jtable({
               title: '<center><form>Trust Name: <input type="text" name="sname" id="SearchFM" style="color: black;" /><button type="submit" id="LoadRecordsButtonPF"><font color="blue">Search </font></button> </center></form> Table of Trust',
               openChildAsAccordion: true, 
               paging: true, //Enable paging
               pageSize: 10, //Set page size (default: 10)
               sorting: true, //Enable sorting
               defaultSorting: 'Name ASC', //Set default sorting
                actions: {
                listAction: '../FirmController?action=list',
                createAction:'../FirmController?action=create',
                updateAction: '../FirmController?action=update',
                deleteAction: '../FirmController?action=delete'
            },
            fields: {
            	 Phones: {
                     title: '',
                     width: '5%',
                     sorting: false,
                     edit: false,
                     create: false,
                     display: function (firmData) {
                         //Create an image that will be used to open child table
                         var $img = $('<img src="../IMAGES/image/icon.png" title="Edit phone numbers" />');
                         //Open child table when user clicks the image
                         $img.click(function () {
                        	
                             $('#PersonTableContainerFM').jtable('openChildTable',
                                     $img.closest('tr'),
                                     {
                            	 title:  firmData.record.firmname +' Table of Contact',
                                 
                                 actions: {
                                 listAction: '../ContactController?action=phonelist&firmid='+firmData.record.firmid,
                                 createAction:'../ContactController?action=phonecreate',
                                 updateAction: '../ContactController?action=phoneupdate',
                                 deleteAction: '../ContactController?action=phonedelete'
                             },
                             fields: {
                       
                             	                
                             	contactid: {
                                 	title:'S.NO',
                                     key: true,
                                     list: true,       
                                 },
                                 firmid: {
                                
                                	 type: 'hidden',
                                     defaultValue:firmData.record.firmid
                                 },
                                 
                                 contactname: {
                                     title: 'Name',
                                     width: '30%',
                                     edit:true,
                                     list:true
                                     //type:'text'
                                 },
                                 emailid: {
                                     title: 'EmailId',
                                     width: '20%',
                                     list: true,
                                     inputClass: 'validate[required,custom[email]]',
                                     edit:true
                                  
                                 },
                                 mobileno: {
                                     title: 'MobileNo',
                                     width: '20%',
                                     list: true,
                                     edit:true
                                 },
                                 
                                 designation: {
                                     title: 'Designation',
                                     width: '30%',
                                     edit:true
                                 },
                                
                                 remark: {
                                     title: 'Description',
                                     type:'textarea',
                                     list: true,
                                     edit:true
                                 }
                                              
                             },
                             
                        
                             //Initialize validation logic when a form is created
                             formCreated: function (event, data) {
                               
                                 data.form.find('input[name="name"]').addClass('validate[required]');
                                 data.form.find('input[name="designation"]').addClass('validate[required]');
                                 data.form.find('input[name="mobileno"]').addClass('validate[required]');
                                 data.form.find('input[name="remark"]').addClass('validate[required]');
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
                            }, function (data) { //opened handler
                                         data.childTable.jtable('load');
                                     });
                         });
                         //Return image to show on the person row
                         return $img;
                     }
                 },
                 
                 LOD: {
                	 title: '',
                     width: '5%',
                     sorting: false,
                     edit: false,
                     create: false,
                    display: function (LodData) {
                        //Create an image that will be used to open child table
                        var $img = $('<img src="../IMAGES/image/Doc.png" title="List of Document"/>');
                        
                        $img.click(function() {
                             
                                	

                                    $('#PersonTableContainerFM').jtable('openChildTable',
                                            $img.closest('tr'),{
                                        title: LodData.record.firmname+' Table of people'+LodData.record.firmid,
                                        //paging: true, //Enable paging
                                        actions: {
                                        	 listAction: '../FirmDocController?action=doclist&firmid='+LodData.record.firmid,
                                            //createAction:'DocMastController?action=create',
                                            updateAction: '../FirmDocController?action=update',
                                            //deleteAction: 'DocMastController?action=delete'
                                        },
                                        fields: {
                                        	
                                           
                                            
                                            docName: {
                                                title: 'Doc Name',
                                                width: '50%',
                                                edit:false,
                                                //inputClass: 'validate[required]'
                                            },
                                            details: {
                                                title: 'Details',
                                                width: '30%',
                                                edit:false,
             
                                                //inputClass: 'validate[required]'
                                            },
                                            docId: {
                                            	title:'DocId',
                                                key: true,
                                                list: false,
                                                create:true,
                                                edit:true,
                                                readOnly:true
                                            },
                                            firmid: {
                                            	title:'Firm Id',
                                                key: true,
                                                edit: true ,
                                                list:false,
                                                defaultValue:LodData.record.firmid
                                               
                                            },
                                     
                                            status:{
                                            	title: 'Status',   list: false,
                                                type: 'radiobutton',
                                                list: true,
                                                options: [
                                                    { Value: 'Y', DisplayText: 'YES' },
                                                    { Value: 'N', DisplayText: 'NO' }
                                                
                                                ]
                                            },
                                            
                                            remark: {
                                                title: 'Remark',
                                                width: '20%',
                                                edit: true,
                                                list:true,
                                                //inputClass: 'validate[required,custom[email]]'
                                            }
                                        }

                                    },
                                 
                                  
                                function (data) { //opened handler
                                    data.childTable.jtable('load');
                                });
                   
                                
                               
                            });
                    
         
                        //Open child table when us
                        return $img;
                    }
                },
            	                
                firmid: {
                	title:'Trust Id',
                    key: true,
                    list: true,
                    create:false,
                    width: '15%'
                },
                
                
                firmname: {
                    title: 'Trust Name',
                    width: '30%',
                    edit:true,
                    //type:'text'
                },
                regid: {
                	title:'RegId',
                    width: '20%',
                    edit:true
                },
                address: {
                    title: 'Address',
                    width: '30%'
                },
                emailid: {
                    title: 'EmailId',
                    width: '20%',
                    //list: true,
                    inputClass: 'validate[required,custom[email]]',
                    edit:true
                 
                },
                contactno: {
                    title: 'ContactNo',
                    width: '20%',
                    edit:true
                },
                mobileno: {
                    title: 'MobileNo',
                    width: '20%',
                    //inputClass: 'validate[required,custom[mobileno]]',
                     edit:true
                },
                remark: {
                    title: 'Description',
                    type:'textarea',
                    list: false,
                    edit:true
                }
            },
            //Initialize validation logic when a form is created
            formCreated: function (event, data) {
              
                data.form.find('input[name="firmname"]').addClass('validate[required]');
                data.form.find('input[name="regid"]').addClass('validate[required]');
                data.form.find('input[name="address"]').addClass('validate[required]');
                data.form.find('input[name="contactno"]').addClass('validate[required]');
                data.form.find('input[name="mobileno"]').addClass('validate[required]');
                data.form.find('input[name="remark"]').addClass('validate[required]');
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
               $('#LoadRecordsButtonPF').click(function (e) {
               e.preventDefault();
               $('#PersonTableContainerFM').jtable('load', {
                   sname: $('#SearchFM').val()
                   
               });
           });
    
           //Load all records when page is first shown
       $('#PersonTableContainerFM').jtable('load', {
                   sname: $('#SearchFM').val()
                   
               });
          // $('#PersonTableContainerFM').click();
    });
</script></head>
<body>

<div style="width:100%;text-align:center;">

<div id="PersonTableContainerFM">

</div>

</div>
<div id="PersonTableContainerFM" style="display:none"></div>
<!-- <script src="../JS/jquery-1.8.2.js" type="text/javascript"></script>
<script src="../JS/jquery-ui-1.10.3.custom.js" type="text/javascript"></script>
<script src="../JS/jquery.jtable.js" type="text/javascript"></script> -->
<!-- Import Javascript files for validation engine (in Head section of HTML) -->
<!-- <script type="text/javascript" src="../JS/jquery.validationEngine.js"></script>
<script type="text/javascript" src="../JS/jquery.validationEngine-en.js"></script> -->
</body>
</html>