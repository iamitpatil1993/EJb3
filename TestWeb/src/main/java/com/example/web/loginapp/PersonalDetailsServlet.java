package com.example.web.loginapp;

import java.io.IOException;
import java.util.Map;

import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.example.ejb3.localinterfaces.TestInf;
import com.example.ejb3.localinterfaces.login.PersonDetailsLocal;




/**
 * Servlet implementation class PersonalDetailsServlet
 */
public class PersonalDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger LOGGER;

	static{
		LOGGER = Logger.getLogger(PersonalDetailsServlet.class); 
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession httpSession = request.getSession(false);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/personalDetails.jsp");

		if(httpSession == null) {
			response.sendRedirect("login.jsp");
		}
		else {
			
			PersonDetailsLocal personDetailsLocal;
			try
			{
				personDetailsLocal = (PersonDetailsLocal) new InitialContext().lookup("java:global/TestEar-1.0/TestEjb/PersonDetailsBean!com.example.ejb3.localinterfaces.login.PersonDetailsLocal");

				String firstName = (String)request.getParameter("firstName");
				String lastName = (String)request.getParameter("lastName");
				String email = (String)request.getParameter("email");
				String username = (String)httpSession.getAttribute("username");

				if(null != personDetailsLocal) {

					httpSession.setAttribute("personBean", personDetailsLocal);
					LOGGER.info("PersonDetails Bean injected Successfully ...");
					personDetailsLocal.addOrUpdatePersonDetails(username, lastName, firstName, email);

					Map<String, String> personalDetails = personDetailsLocal.getPersonDetails(); 
					request.setAttribute("message", "Details Saved Successfully");
					request.setAttribute("detailsMap", personalDetails);
					dispatcher.forward(request, response);
				}
				else {
					LOGGER.info("Error while injecting stateful bean ...");
					request.setAttribute("message", "Internal Error while saving details");
					dispatcher.forward(request, response);
				}
			}
			catch (NamingException e)
			{
				LOGGER.info("Error while injecting stateful bean ...");
				request.setAttribute("message", "Internal Error while saving details");
				dispatcher.forward(request, response);
			}
		}

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession httpSession = request.getSession(false);
		if(httpSession.getAttribute("username") == null) {
			response.sendRedirect("login.jsp");
		}
		else {

			PersonDetailsLocal personDetailsLocal = (PersonDetailsLocal) httpSession.getAttribute("personBean");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/personalDetails.jsp");
			Map<String, String> personalDetails = personDetailsLocal.getPersonDetails(); 
			request.setAttribute("detailsMap", personalDetails);
			dispatcher.forward(request, response);
		}

	}

}
