package com.example.web.dependencyInjection;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

import com.example.ejb3.localinterfaces.dependencyInjection.DependencyInjectionDemoBeanLocal;

/**
 * Servlet implementation class DataSourceInjectionDemoServlet
 */
public class DataSourceInjectionDemoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER;

	@Resource(mappedName="java:jboss/datasources/MySqlDS")
	public DataSource dataSource;

	@EJB
	DependencyInjectionDemoBeanLocal dependencyInjectionDemoBeanLocal;

	static {
		LOGGER = Logger.getLogger(DependencyInjectionDemoServlet.class);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrintWriter printWriter = response.getWriter();
		if(null != dependencyInjectionDemoBeanLocal) {

			printWriter.println("DependencyInjectionDemoBean injected successully ....");
			dependencyInjectionDemoBeanLocal.dataSourceInjection();

			//We can inject datasource in servlets as well
			this.displayCareplanTemplateNames(printWriter);
		}
		else {
			printWriter.println("DependencyInjectionDemoBean injection falied ....");
		}
	}


	//We can inject and use datasource in servlets as well, this method will use datasource to 
	private void displayCareplanTemplateNames(final PrintWriter printWriter) {

		if(null != dataSource) {

			Connection con = null;
			LOGGER.info("DataSOurce injected successfully ... ... inside DataSourceInjectionDemoServlet");
			try {

				//Using JDBC to get data
				con = dataSource.getConnection();
				Statement st = con.createStatement();
				ResultSet resultSet = st.executeQuery("SELECT careplan_name FROM praxifydbUS.careplan_template");

				printWriter.println(" ---------------------------- Template Names from Servlet -----------------------");
				String templateName;
				while(resultSet.next()) {
					templateName = resultSet.getString(1); 
					if(templateName != null)
						printWriter.println(templateName);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally {

				if(null != con) {

					LOGGER.info("Closing dataSource connection .... ");
					try {
						con.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		}
		else {
			LOGGER.info("DataSOurce injection falied ... inside DataSourceInjectionDemoServlet");
		}
	}



}
