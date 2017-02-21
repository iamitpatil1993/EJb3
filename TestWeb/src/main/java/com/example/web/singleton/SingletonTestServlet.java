package com.example.web.singleton;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.example.ejb3.localinterfaces.singleton.TestSingletonLocal;


public class SingletonTestServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER;
	
	static {
		LOGGER = Logger.getLogger(SingletonTestServlet.class);
	}
	
	@EJB
	public TestSingletonLocal singletonBeanLocal;		

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		LOGGER.info("Inside SingletonTestServlet ..");
		
		PrintWriter printWriter = response.getWriter();
	
		if(null != singletonBeanLocal) {
			printWriter.println("Singleton bean injected successfully ...!!!");
			singletonBeanLocal.sayHello();
			singletonBeanLocal.increamentCounter();
			printWriter.println("Singleton bean access couunt is : " + singletonBeanLocal.getCounter());
		}
		else {
			printWriter.println("Error while ijecting Singleton bean using dependency injection!");
		}
		
		printWriter.flush();
		response.flushBuffer();
	}
	
	

}
