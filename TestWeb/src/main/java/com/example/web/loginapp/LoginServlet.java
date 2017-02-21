package com.example.web.loginapp;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER;
	
	static{
		LOGGER = Logger.getLogger(LoginServlet.class);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		LOGGER.info("username : " + username + " password : " +password);
		
		if(authenticateUser(username, password)) {
			
			HttpSession httpSession = request.getSession();
			httpSession.setAttribute("username", username);
			response.sendRedirect("loginSuccess.jsp");
		}
		else
		{
			request.setAttribute("message", "Invalid Username or Password");
			RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	
	private boolean authenticateUser(String username, String password) {
		
		boolean returnValue = false;
		
		if(username != null && password != null) {
			
			if((username.equals("iamitpatil1993") && password.equals("password")) || (username.equals("iamitpatil") && password.equals("asdf"))) {
				returnValue = true;
			}
		}
		return returnValue;
	}

}
