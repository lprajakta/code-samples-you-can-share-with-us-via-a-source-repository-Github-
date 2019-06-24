package com.itrix.controller;

import com.itrix.model.UserMastBean;
import com.itrix.service.EncryptService;
import com.itrix.service.LoginUserService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
    	try {
    		/**************************Get text field names from jsp************************************/
    		   String username=request.getParameter("username");
    		   String password=request.getParameter("password");
    		  /*********************************password encrption****************************************/
			   EncryptService encryptService=new EncryptService();
			   String key="itrix"; 
			   String encrytPassword = encryptService.encrypt(key, password);
			   System.out.println("Encrypted pwd "+encrytPassword);
	    	/*********************************set into bean object**************************************/
	    	    UserMastBean  userBean=new UserMastBean();
	    	    userBean.setUsername(username);
	    	    userBean.setPassword(encrytPassword);
	    	    boolean resultFlag=false;
	    	    ArrayList<UserMastBean> userlist=new ArrayList<UserMastBean>();
	    	    LoginUserService loginService=new LoginUserService();
	    	    resultFlag=loginService.checkUserPass(userBean);
	    	    HttpSession session=request.getSession();
	    	    session.setMaxInactiveInterval(20*60);
	    	    ResourceBundle rbMSG=ResourceBundle.getBundle("ApplicationMessages");
	    	    if(resultFlag==true)
	    	    {
	    	    	userlist=loginService.getUserDetails(userBean);
	    	    	if (userlist!=null)
	    	    	{
	    	    		if(userlist.size()==1){
	    	    			UserMastBean ubean=userlist.get(0);
		    	    		int user_id=ubean.getUserId();
		    	    		String User_name=ubean.getUsername();
		    	    		String EmailID=ubean.getEmailId();
		    	    		char userRole=ubean.getRoleFlag(); 	    		
		    	    		session.setAttribute("LoggedUid", user_id);
		    	    		session.setAttribute("LoggedUname", User_name);
		    	    		session.setAttribute("LoggedUrole", userRole);
		    	    		session.setAttribute("LoggedEmailID", EmailID);
		    	    		if(userRole=='A'){
								response.sendRedirect("JSP/Home.jsp");
							}else{
								response.sendRedirect("JSP/UserHome.jsp");
							}		    			 	
	    	    		}else{
	    	    			System.out.println("Multiple user found in database");
	    	    		}	    	    		
					}	    	    				 	
				 }
	    		else
	    		{
	    			String error=rbMSG.getString("LoginFailedMsg");				 	
				 	session.setAttribute("LoginErrorMSG", error);
				 	response.sendRedirect("index.jsp");
	    		}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
