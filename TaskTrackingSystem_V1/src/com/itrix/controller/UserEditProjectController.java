package com.itrix.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itrix.model.ProjectAssignBean;
import com.itrix.model.ProjectMastBean;
import com.itrix.model.ProjectSubTaskAssignBean;
import com.itrix.model.SubTaskBean;
import com.itrix.service.ProjetcMastService;
import com.itrix.service.SubTaskService;
import com.itrix.utility.Conversion;

/**
 * Servlet implementation class UserEditProjectController
 */
@WebServlet("/UserEditProjectController")
public class UserEditProjectController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 private ProjetcMastService proMastSerObj=null;     
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserEditProjectController() {
    	 proMastSerObj=new ProjetcMastService(); 
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		Conversion convert=new Conversion();
		String projID=request.getParameter("hiddenIDForUserUpdt");
		System.out.println("Pro ID Controller "+projID);
		int UpdtProjID = convert.convertStrToInt(projID);
		ProjectMastBean proBeanObj=new ProjectMastBean();
		
		/*int firmidInt=0;
		String firmName="";
		int reviewIDInt=0;
		String reviewName="";
		
		String projName = request.getParameter("editProjectName").trim();
		
		String startdate=request.getParameter("editstartdate");
		java.sql.Date startDt=convert.convertStrToDate(startdate);
		
		String remark = request.getParameter("editremark").trim();
		String reviewID = request.getParameter("editreviewer");
		
		reviewIDInt=convert.convertStrToInt(reviewID);
		// proBeanObj.setFirmId(firmidInt);
		 proBeanObj.setProjId(UpdtProjID);
		 proBeanObj.setprojname(projName);
		 proBeanObj.setStartDt(startDt);
		 proBeanObj.setRemark(remark);
		 proBeanObj.setReviewerid(reviewIDInt);
		 proBeanObj.setActivestatus("A");
		 proBeanObj.setOpeningFees(0.0);
		 proBeanObj.setFees(0.0);
		 proBeanObj.setStatus("N");*/
		 proBeanObj.setProjId(UpdtProjID);
		 String[] catgValue;
		 String[] itemValue;
		 String[] userValue;	
		 String[] assignIDValue;
		 String[] workStatusValue;
		 
		 	 assignIDValue=request.getParameterValues("assignID[]");
		 	 catgValue=request.getParameterValues("Category[]");
			 itemValue=request.getParameterValues("taskname[]");
			 userValue=request.getParameterValues("username[]");			 
			 String[] amount=request.getParameterValues("Amount[]");
			 String[] description=request.getParameterValues("remark[]");
			 String[] workStatus = request.getParameterValues("workstatus[]");
		ArrayList<ProjectAssignBean> projAssignList = new ArrayList<ProjectAssignBean>();
		for(int i=0;i< catgValue.length;i++)
		{
			ProjectAssignBean assignBean =  new ProjectAssignBean();
			String assignIdValueTxt=assignIDValue[i];
			String catgValueTxt=catgValue[i];
			String itemValueTxt=itemValue[i];
			String usrValueTxt=userValue[i];
			String amtValueTxt=amount[i];
			String descValueTxt=description[i].trim();
			String wrkStatusValueTxt=workStatus[i];
			
			String AssignId[]=assignIdValueTxt.split(":");
			String CatId[]=catgValueTxt.split(":");
			String itemid[]=itemValueTxt.split(":");
			String usr[]=usrValueTxt.split(":");
			String amt[]=amtValueTxt.split(":");
			String desc[]=descValueTxt.split(":");
			String wrkStatus[] = wrkStatusValueTxt.split(":");
			int assignid=convert.convertStrToInt(AssignId[0]);	
			int catid=convert.convertStrToInt(CatId[0]);				
			int taskId=convert.convertStrToInt(itemid[0]);
			int usrId=convert.convertStrToInt(usr[0]);
			double amtVal = convert.convertStrToDouble(amt[0]);
			
			assignBean.setProjassignId(assignid);
			assignBean.setCatgId(catid);
			assignBean.setTaskId(taskId);
			assignBean.setUserId(usrId);
			assignBean.setAmount(amtVal);
			assignBean.setRemark(desc[0]);
			//assignBean.setActivestatus("A");
			assignBean.setStatus("N");
			assignBean.setWorkStatus(wrkStatus[0]);
			
			//Check Sub Task available for the particular task ?
			SubTaskService service  = new SubTaskService();
			ArrayList<SubTaskBean> subTaskList = service.getSubTaskSer(catid);
			if(subTaskList.size()!= 0){
				
			ArrayList<ProjectSubTaskAssignBean> projSubTaskAssignList = new ArrayList<ProjectSubTaskAssignBean>();
			for (int j = 0; j < subTaskList.size(); j++) {
				SubTaskBean subTaskBean = subTaskList.get(j);
				int subTaskId = subTaskBean.getSubTaskId();
				ProjectSubTaskAssignBean subTaskObj = new ProjectSubTaskAssignBean();
				subTaskObj.setSubtaskid(subTaskId);
				projSubTaskAssignList.add(subTaskObj);
			}	
			//Set the subtask to task bean
			assignBean.setProjSubTaskAssignList(projSubTaskAssignList);
			}
			//add task bean into list
			projAssignList.add(assignBean);
	
		}
		 proBeanObj.setProjectAssignList(projAssignList);
		 boolean result = proMastSerObj.UserUpdtProjMastSer(proBeanObj);	
		 System.out.println("output >> "+result);
		 HttpSession htp = request.getSession();
		 ResourceBundle rb = ResourceBundle.getBundle("ApplicationMessages");
		 if(result){
		
		 String Msg =rb.getString("PorojectMastAddSuccessMsg");
			htp.setAttribute("successMsg",Msg);
			response.sendRedirect("JSP/UserHome.jsp");
		 }else
			{
				String Msg=rb.getString("ProjectMastAddFailureMsg");
				htp.setAttribute("failureMsg",Msg);
				
				response.sendRedirect("JSP/UserHome.jsp");
					
			}
			
		 
	
	
	}

}
