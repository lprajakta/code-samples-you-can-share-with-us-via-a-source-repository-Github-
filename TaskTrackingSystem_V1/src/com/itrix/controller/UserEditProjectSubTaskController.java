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
import com.itrix.model.ProjectSubTaskAssignBean;
import com.itrix.model.SubTaskBean;
import com.itrix.service.ProjetcMastService;
import com.itrix.utility.Conversion;

/**
 * Servlet implementation class UserEditProjectSubTaskController
 */
@WebServlet("/UserEditProjectSubTaskController")
public class UserEditProjectSubTaskController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 private ProjetcMastService proMastSerObj=null;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserEditProjectSubTaskController() {
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
		String projID=request.getParameter("hiddenIDForUserProIdUpdt");
		String catgID=request.getParameter("hiddenIDForUserCatgIdUpdt");
		String taskID=request.getParameter("hiddenIDForUserTaskIdUpdt");
		System.out.println("Pro ID Controller "+projID+" "+catgID+" "+taskID);
		int UpdtProjID = convert.convertStrToInt(projID);
		int UpdtCatgID = convert.convertStrToInt(catgID);
		int UpdtTaskID = convert.convertStrToInt(taskID);
	
		 String[] assignIDValue; 
		 String[] catgValue;
		 String[] taskValue;
		 String[] userValue;		 
		
		 
	 	 assignIDValue=request.getParameterValues("assignID[]");
	 	 catgValue=request.getParameterValues("category[]");
	 	 taskValue=request.getParameterValues("taskname[]");
		/// userValue=request.getParameterValues("username[]");			 
		 String[] subtaskname=request.getParameterValues("Subtaskname[]");
		 String[] description=request.getParameterValues("remark[]");
		 String[] workStatus = request.getParameterValues("workstatus[]");
		 
		 ArrayList<ProjectSubTaskAssignBean> projSubTaskAssignList = new ArrayList<ProjectSubTaskAssignBean>();
			for (int i = 0; i < assignIDValue.length; i++) {
				
				ProjectSubTaskAssignBean staskBean = new ProjectSubTaskAssignBean();
				String assignIdValueTxt=assignIDValue[i];
			//	String catgValueTxt=catgValue[i];
			//	String taskValueTxt=taskValue[i];
			//	String usrValueTxt=userValue[i];
			//	String subtasknameValueTxt=subtaskname[i];
				String descValueTxt=description[i].trim();
				String wrkStatusValueTxt=workStatus[i];
				
				String AssignId[]=assignIdValueTxt.split(":");
			//	String CatId[]=catgValueTxt.split(":");
			//	String itemid[]=taskValueTxt.split(":");
			//	String usr[]=usrValueTxt.split(":");
			//	String subTaskName[]=subtasknameValueTxt.split(":");
				String desc[]=descValueTxt.split(":");
				String wrkStatus[] = wrkStatusValueTxt.split(":");
				
				int assignid=convert.convertStrToInt(AssignId[0]);	
		//		int catid=convert.convertStrToInt(CatId[0]);				
		//		int taskId=convert.convertStrToInt(itemid[0]);
			//	int usrId=convert.convertStrToInt(usr[0]);
						
				staskBean.setProjsubtaskdetid(assignid);
			//	staskBean.setCatgId(catid);
			//	staskBean.setTaskId(taskId);
				//staskBean.setUserId(usrId);
			//	staskBean.setSubtaskName(subTaskName[0]);
				staskBean.setRemark(desc[0]);
				staskBean.setStatus("N");
				staskBean.setWorkStatus(wrkStatus[0]);				
				projSubTaskAssignList.add(staskBean);
			}
			 boolean result = proMastSerObj.UserUpdtProjSubTaskMastSer(projSubTaskAssignList);	
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
