package com.example.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.example.ejb3.localinterfaces.beanlifecycle.BeanLifeCycleLocal;

public class BeanLifeCycleServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6688888752835756980L;

	@EJB(beanName="BeanLifeCycleBean")
	private BeanLifeCycleLocal beanLifeCycleLocal;
	
	Logger logger = Logger.getLogger(BeanLifeCycleServlet.class);
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		PrintWriter writer = response.getWriter();
		
		if(null == beanLifeCycleLocal){
			
			writer.println("Error while injeting BeanLifeCyclebean");
			logger.info("Error while injeting BeanLifeCyclebean");
		}
		else
		{
			writer.println("BeanLifeCyclebean injected successfully");
			logger.info("BeanLifeCyclebean injected successfully");
			
			beanLifeCycleLocal.sayHello();
		}
		
		writer.flush();
		writer.close();
		
		response.flushBuffer();
	}
}
