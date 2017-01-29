package com.example.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.example.ejb3.beans.nointerfaceview.NoInterfaceViewBean;


/**
 * Servlet implementation class NoInterfaceViewServlet
 */
public class NoInterfaceViewServlet extends HttpServlet {


	private static final long serialVersionUID = 1L;

	//To inject No interface bean, we must use bean class reference variable instead of Interface, rather there is not business interface in case of
	//No-Interface-View beans
	@EJB
	private NoInterfaceViewBean noInterfaceViewBean;

	//Restriction of No-Interface View is that, Clinet of No-Interface-view bean must be part of same application as that of bean. I.e CLient must be packaged
	//into .ear in which bean is packaged.
	//In our case, since this servlet is part of same application is being packaged with same ear, our servlet can easily access bean

	public static Logger logger = Logger.getLogger(NoInterfaceViewServlet.class);
	
	public NoInterfaceViewServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrintWriter writer = response.getWriter();
		writer.println("Served at: " + request.getContextPath());

		if(null != noInterfaceViewBean) {

			writer.println("NoInterfaceViewBean Injected Succcessfully");
			noInterfaceViewBean.sayHello();
		}
		else
		{
			writer.println("Error While Injecting NoInterfaceView Bean");
		}
	}

}
