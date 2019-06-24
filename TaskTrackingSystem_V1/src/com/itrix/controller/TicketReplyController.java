package com.itrix.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.itrix.model.TicketModel;
import com.itrix.model.TicketReplyModel;
import com.itrix.service.TicketReplyService;
import com.itrix.service.TicketService;

/**
 * Servlet implementation class TicketReplyController
 */
@WebServlet("/TicketReplyController")
public class TicketReplyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TicketReplyController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		TicketReplyService ticketReplyService=new TicketReplyService();
		if (action.equals("create")) {
			boolean result = false;
			TicketReplyModel ticketReplyModel = new TicketReplyModel();
			ticketReplyModel.setReply(request.getParameter("reply"));
			ticketReplyModel.setReplyBy("User");
			ticketReplyModel.setTicketId(Integer.parseInt(request.getParameter("ticketId")));
			result = ticketReplyService.createTicketReplyService(ticketReplyModel);
			PrintWriter out = response.getWriter();
			if (result == true) {
				out.println("Reply Send  Sucessfully");

			} else {
				out.println("Problem Occured while Reply Sending");
			}

		}
		if (action.equals("fetchReply")) {
			ArrayList<TicketReplyModel> list =new ArrayList<>();
			int id = Integer.parseInt(request.getParameter("ticketId"));
			list=ticketReplyService.getAllReplyService(id);
			
			Gson gson=new Gson();
			JsonElement jelement=gson.toJsonTree(list);
			JsonObject object=new JsonObject();
			object.add("ticket", jelement);
			String strJson=gson.toJson(object);
			response.getWriter().write(strJson);
			
		}
	}

}
