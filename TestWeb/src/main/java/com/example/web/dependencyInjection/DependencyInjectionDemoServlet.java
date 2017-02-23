package com.example.web.dependencyInjection;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.example.ejb3.localinterfaces.dependencyInjection.DependencyInjectionDemoBeanLocal;

/**
 * Servlet implementation class DependencyInjectionDemoServlet
 */
public class DependencyInjectionDemoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER;

	@EJB
	DependencyInjectionDemoBeanLocal dependencyInjectionDemoBeanLocal;
	
	static {
		LOGGER = Logger.getLogger(DependencyInjectionDemoServlet.class);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String subject = request.getParameter("subject");
		String toEmail = request.getParameter("toEmail");
		String message = request.getParameter("messgae");
		
		PrintWriter printWriter = response.getWriter();
		if(null != dependencyInjectionDemoBeanLocal) {
			
			printWriter.println("DependencyInjectionDemoBean injected successully ....");
			printWriter.println("Email is sent to : " + toEmail);
			dependencyInjectionDemoBeanLocal.emailHandler(subject, toEmail, message);
		}
		else {
			printWriter.println("DependencyInjectionDemoBean injection falied ....");
		}
	}

}
