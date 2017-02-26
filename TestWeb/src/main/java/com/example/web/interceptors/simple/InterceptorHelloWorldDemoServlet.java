package com.example.web.interceptors.simple;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.example.ejb3.beans.interceptors.InterceptorClassLevelBean;
import com.example.ejb3.localinterfaces.interceptors.InterceptorHelloWorldLocal;

/**
 * Servlet implementation class InterceptorHelloWorldDemoServlet
 */
public class InterceptorHelloWorldDemoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Logger LOGGER = Logger.getLogger(InterceptorHelloWorldDemoServlet.class);

	@EJB
	private InterceptorHelloWorldLocal interceptorHelloWorldLocal;
	
	@EJB
	private InterceptorClassLevelBean interceptorClassLevelBean;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter printWriter = response.getWriter();

		if(null != interceptorHelloWorldLocal) {

			printWriter.println("InterceptorHelloWorldLocal injected successfully ...");
			interceptorHelloWorldLocal.helloWorldInterceptor();
			interceptorHelloWorldLocal.multipleInterceptorsAtMethodLevelDemo();
		}
		else {
			printWriter.println("InterceptorHelloWorldLocal injection failed ...");
		}
		
		if(null != interceptorClassLevelBean) {

			printWriter.println("interceptorClassLevelBean injected successfully ...");
			interceptorClassLevelBean.classLevelInterceptorDemo();
			interceptorClassLevelBean.excludeClassLevelInterceptor();
		}
		else {
			printWriter.println("interceptorClassLevelBean injection failed ...");
		}
		
				
	}




}
