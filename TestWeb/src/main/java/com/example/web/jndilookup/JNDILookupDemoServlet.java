package com.example.web.jndilookup;

import java.io.IOException;
import java.io.PrintWriter;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.example.ejb3.localinterfaces.dependencyInjection.DependencyInjectionDemoBeanLocal;

/**
 * Servlet implementation class JNDILookupDemoServlet
 */
public class JNDILookupDemoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER;
	public static final String LOOKUP_NAME = "java:global/TestEar-1.0/TestEjb/DependencyInjectionDemoBean!com.example.ejb3.localinterfaces.dependencyInjection.DependencyInjectionDemoBeanLocal";

	static {
		LOGGER = Logger.getLogger(JNDILookupDemoServlet.class);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrintWriter printWriter = response.getWriter();

		try {

			//We must use initial context object to inject resources out side ejbs.
			//Inside ejbs we can use ejbCOntext/sessionContext/messageDrivenContext lookup() method to look up resources
			//but outside ejb like in pojos we don't have any option other than JNDI look up
			InitialContext context = new InitialContext();

			DependencyInjectionDemoBeanLocal beanLocal =  (DependencyInjectionDemoBeanLocal) context.lookup(LOOKUP_NAME);
			if(null != beanLocal) {

				response.setContentType("text/html");
				printWriter.println("DependencyInjectionDemoBean injected Successfully using clumpsy JNDI lookup<br>");
				printWriter.println("<p>We must use initial context object to inject resources out side ejbs</p>");
				printWriter.println("<p>Inside ejbs we can use ejbCOntext/sessionContext/messageDrivenContext lookup() method to look up resources</p>");
				printWriter.append(", but outside ejb like in pojos we don't have any option other than JNDI look up.");
				response.flushBuffer();
			}
			else {
				printWriter.println("DependencyInjectionDemoBean injection failed using clumpsy JNDI lookup");
			}

		} catch (NamingException e) {

			e.printStackTrace();
		}


	}

	
}
