package com.example.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.example.ejb3.localinterfaces.TestInf;
import com.example.ejb3.localinterfaces.TestInf2;


public class TestServ extends HttpServlet {

	/**
	 * 
	 *
	 */
	private static final long serialVersionUID = -1080093111079027447L;

	@EJB
	private TestInf testBean;
	
	@EJB(beanName="TestBeanOther")
	public TestInf2 testOtehrBean;
	
	@EJB(beanName="TestBean2")
	public TestInf2 testBean2;
	
	public static Logger logger = Logger.getLogger(TestServ.class);
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		PrintWriter printWriter = resp.getWriter();
		printWriter.println("Hello world from Serlet");


		if(null == testBean){
			
			logger.info("Injecting bean via JNDI.... Dependency Injection didn't worked");
			try {
				testBean = (TestInf) new InitialContext().lookup("java:global/TestEar-1.0/TestEjb/TestBean!com.example.ejb3.TestInf");
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				printWriter.println("Error while injecting bean From catch");
				e.printStackTrace();
			}	
		}
		else {
			//logger.info("Bean Injected using DI Successfully !!!.... Dependency Injection worked");
			logger.info("Bean Injected using DI Successfully !!!.... Dependency Injection worked");
		}

		if(null != testBean){

			testBean.sayHello();
			printWriter.println(testBean.getTestString());
		}
		else
		{
			printWriter.println("Error while injecting bean");
		}
		
		if(null != testOtehrBean){

			logger.info("TEstBeanOther Injected Successfully in Servlet");
			testOtehrBean.sayGoodMorning();
		}
		else
		{
			printWriter.println("Error while injecting TestOtherBean bean");
		}
		
		if(null != testBean2){

			logger.info("testBean2 Injected Successfully in Servlet");
			testBean2.sayGoodMorning();
		}
		else
		{
			printWriter.println("Error while injecting testBean2 bean");
		}
				

		printWriter.flush();
		resp.flushBuffer();
	}
}
