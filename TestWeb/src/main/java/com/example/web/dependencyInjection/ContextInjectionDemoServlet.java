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
 * Servlet implementation class ContextInjectionDemoServlet
 */
public class ContextInjectionDemoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER;

	static {
		LOGGER = Logger.getLogger(ContextInjectionDemoServlet.class);
	}
	@EJB
	DependencyInjectionDemoBeanLocal dependencyInjectionDemoBeanLocal;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrintWriter printWriter = response.getWriter();
		if(null != dependencyInjectionDemoBeanLocal) {

			printWriter.println("DependencyInjectionDemoBean injected successully ....");
			dependencyInjectionDemoBeanLocal.ejbContextEnjection();
		}
		else {
			printWriter.println("DependencyInjectionDemoBean injection falied ....");
		}
	}

}
