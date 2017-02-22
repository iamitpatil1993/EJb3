package com.example.web.asynchronous;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.example.ejb3.localinterfaces.asynchronous.AsynchronousDemoBeanLocal;

/**
 * Servlet implementation class AsynchronousdemoServlet
 */
public class AsynchronousdemoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	private static final Logger LOGGER;
	
	@EJB
	AsynchronousDemoBeanLocal asynchronousDemoBean;
	
	static {
		LOGGER = Logger.getLogger(AsynchronousdemoServlet.class);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		LOGGER.info("Inside AsynchronousdemoServlet.doGet() ... entered");
		PrintWriter printWriter = response.getWriter();
		
		if(null != asynchronousDemoBean) {
			printWriter.println("AsynchronousDemoBean injected successfully ...");
			
			LOGGER.info("Request Thread : " + Thread.currentThread().getName());
			asynchronousDemoBean.processPayment(request.getParameter("debitCardNo"), request.getParameter("cvv"), request.getParameter("name"));
		}
		else {
			printWriter.println("AsynchronousDemoBean injection failed ...");
		}
				
		response.flushBuffer();
		LOGGER.info("Inside AsynchronousdemoServlet.doGet() ... leavig");
	}
}
