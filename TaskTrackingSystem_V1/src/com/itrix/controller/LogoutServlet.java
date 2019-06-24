package com.itrix.controller;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 * Servlet implementation class LogoutUserServlet
 */
@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
private static final long serialVersionUID = 1L;       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogoutServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub	
	try{
		HttpSession session=request.getSession(false);		
		session.removeAttribute("LoggedUid");
		session.removeAttribute("LoggedUname");
		session.removeAttribute("LoggedUrole");
		session.invalidate();
		response.sendRedirect("index.jsp");
	}	
	catch( Exception e){
		
	}
	
	
	
		
	
	
    }
}
