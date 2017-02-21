package com.example.web.loginapp;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.ejb3.localinterfaces.login.PersonDetailsLocal;

/**
 * Servlet implementation class LogoutServlet
 */
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession httpSession = request.getSession(false);
		PersonDetailsLocal detailsLocal = (PersonDetailsLocal) httpSession.getAttribute("personBean");
		httpSession.invalidate();
		if(detailsLocal != null)
			detailsLocal.invalidate();
		response.sendRedirect("login.jsp");
	}

}
