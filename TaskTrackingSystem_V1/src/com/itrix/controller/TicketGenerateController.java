package com.itrix.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itrix.model.TicketModel;
import com.itrix.service.TicketService;

/**
 * Servlet implementation class TicketGenerateController
 */
@WebServlet("/TicketGenerateController")
public class TicketGenerateController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TicketGenerateController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		ResourceBundle rbMsg=ResourceBundle.getBundle("ApplicationMessages");
		HttpSession session=request.getSession(false);
		TicketService ticketService = new TicketService();
		if (action.equals("create")){
			boolean result = false;
            int firmID=Integer.parseInt(request.getParameter("firmID"));
            int catId=Integer.parseInt(request.getParameter("catId"));
            int taskId=Integer.parseInt(request.getParameter("taskId"));
            int projectId=Integer.parseInt(request.getParameter("projectId"));
            String ticketSubject = request.getParameter("tSubject");
            String ticketMsg = request.getParameter("tMessage");
			TicketModel ticketModel = new TicketModel();
			
			ticketModel.setFirmID(firmID);
			ticketModel.setCatId(catId);
			ticketModel.setProjectId(projectId);
			ticketModel.setTaskId(taskId);
			ticketModel.setSubject(ticketSubject);
			ticketModel.setMessage(ticketMsg);
			String priority = request.getParameter("tPriority");
			if (priority.equals("h")) {
				ticketModel.setPriority('H');
			}
			if (priority.equals("m")) {
				ticketModel.setPriority('M');
			}
			if (priority.equals("l")) {
				ticketModel.setPriority('H');
			}
			
			result = ticketService.createTicketService(ticketModel);
			PrintWriter out = response.getWriter();
			if (result) {
				out.println("Ticket Generated Sucessfully");

			} else {
				out.println("Problem Occured while Ticket Generating");
			}
			 /* if (result)
   		 {					
				String AddSuccessMSG=rbMsg.getString("TicketMastAddSuccessMsg");
			 	session.setAttribute("SuccessTktMSG", AddSuccessMSG);
			 //	session.setAttribute("TabIndex", "usermast");
			  	response.sendRedirect("JSP/UserHome.jsp");
			    }
			   else 
			   {					   
				String AddFailureMSG=rbMsg.getString("TicketMastAddFailureMsg");
			 	session.setAttribute("ErrorTktMSG", AddFailureMSG);
			 //	session.setAttribute("TabIndex", "usermast");
			 	response.sendRedirect("JSP/UserHome.jsp");
			  }*/

		}

		
		if (action.equals("delete")) {
			boolean result=false;
			
			final int id = Integer.parseInt(request.getParameter("ticketId"));
			result=ticketService.deleteTicketSrvice(id);
			PrintWriter out=response.getWriter();
			if(result==true){
				out.println("Ticket Deleted Succusessfully");
			}else{
				out.println("Ticket Not Deleted ");
			}
			
		}
		
/*		if (action.equals("update")||action=="update") {
			boolean result = false;
			TicketModel ticketModel = new TicketModel();
			int id=Integer.parseInt(request.getParameter("ticketId"));
			ticketModel.setId(id);
			ticketModel.setSubject(request.getParameter("subject"));
			ticketModel.setMessage(request.getParameter("message"));
			String priority = request.getParameter("priority");
			if (priority.equals("h")) {
				ticketModel.setPriority('H');
			}
			else if (priority.equals("m")) {
				ticketModel.setPriority('M');
			}
			else {
				ticketModel.setPriority('L');
			}
			String status = request.getParameter("status");
			if (status.equals("o")) {
				ticketModel.setStatus('O');
			}
			else if (status.equals("s")) {
				ticketModel.setStatus('S');
			}
			else  {
				ticketModel.setStatus('C');
			}
			result = ticketService.updateTicketService(ticketModel);
			PrintWriter out = response.getWriter();
			if (result == true) {
				out.println("Ticket Updated Sucessfully");

			} else {
				out.println("Problem Occured while Ticket Updating");
			}

		}*/
	}

}
