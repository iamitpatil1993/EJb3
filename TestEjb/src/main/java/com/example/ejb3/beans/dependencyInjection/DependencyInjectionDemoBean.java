package com.example.ejb3.beans.dependencyInjection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.annotation.Resource;
import javax.ejb.Asynchronous;
import javax.ejb.EJBContext;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TimerService;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

import com.example.ejb3.localinterfaces.dependencyInjection.DependencyInjectionDemoBeanLocal;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class DependencyInjectionDemoBean implements DependencyInjectionDemoBeanLocal{

	private static final Logger LOGGER;


	//Inject javamail resource, configured in standalon.xml configuration file
	@Resource(mappedName="java:jboss/mail/test")
	public Session mailSession;

	@Resource
	public SessionContext context;

	@Resource
	TimerService timerService;

	//1.Download jboss as 7
	//Goto modules directory
	// Create new folder mysql/main inside modules directory
	//download mysql connector jar
	//create module.xml file in modules/mysql/main folder
	//give mysqlconnector name as root path, give name to module related to mysql
	//Now go to standalon.xml
	//go to datasources subsystem
	//add new datasource with url, security(username, password), pool details
	//add new driver tag under drivers tag and specify module name that we sent in module.xml file
	//refer Module.xml and Standalone.xml files uploaded in github
	//e.g
	/*	

	//e.g
/*	

</datasources>
	 <datasource jndi-name="java:jboss/datasources/MySqlDS" pool-name="MySqlDS">
     <connection-url>jdbc:mysql://localhost:3306/praxifydbUS</connection-url>
     <driver>com.mysql</driver>
     <transaction-isolation>TRANSACTION_READ_COMMITTED</transaction-isolation>
     <pool>
         <min-pool-size>10</min-pool-size>
         <max-pool-size>100</max-pool-size>
         <prefill>true</prefill>
     </pool>
     <security>
         <user-name>root</user-name>
         <password>asdf1234</password>
     </security>
 	</datasource>
 	<drivers>
          <driver name="h2" module="com.h2database.h2">
                    <xa-datasource-class>org.h2.jdbcx.JdbcDataSource</xa-datasource-class>
          </driver>
           <driver name="com.mysql" module="com.mysql">
                   <xa-datasource-class>com.mysql.jdbc.jdbc2.optional.MysqlXADataSource</xa-datasource-class>
           </driver>
      </drivers>
</datasources>*/

	@Resource
	EJBContext ejbContext;

	@Resource(mappedName="java:jboss/datasources/MySqlDS")
	public DataSource dataSource;

	static {
		LOGGER = Logger.getLogger(DependencyInjectionDemoBean.class);
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public void emailHandler(String subject, String to, String message) {

		LOGGER.info("Inside DependencyInjectionDemoBean.emailHandler() ... entered");

		if(null != mailSession) {
			LOGGER.info("MailSession injected successfully ...");

			//Send email asynchronously
			context.getBusinessObject(DependencyInjectionDemoBeanLocal.class).sendMail(subject, to, message);
		}
		else {
			LOGGER.info("MailSession injection failed ...");
		}
		LOGGER.info("Inside DependencyInjectionDemoBean.emailHandler() ... leaving");
	}

	@Asynchronous
	@Override
	public void sendMail(String subject, String toEmail, String message) {

		LOGGER.info("Inside DependencyInjectionDemoBean.sendMail() ... entered");
		try {
			MimeMessage m = new MimeMessage(mailSession);
			Address[] to = new InternetAddress[] { new InternetAddress(
					toEmail) };
			m.setRecipients(Message.RecipientType.TO, to);
			m.setSubject(subject);
			m.setContent(message, "text/plain");
			Transport.send(m);

			LOGGER.info("Mail Sent Successfully.");
		} catch (javax.mail.MessagingException e) {
			e.printStackTrace();
		}
		LOGGER.info("Inside DependencyInjectionDemoBean.sendMail() ... leaving");
	}

	@Override
	public void dataSourceInjection() {

		if(null != dataSource) {

			LOGGER.info("DataSOurce injected successfully ...");
			if(null != dataSource) {

				Connection con = null;
				LOGGER.info("DataSOurce injected successfully ...");
				try {

					//Using JDBC to get data
					con = dataSource.getConnection();
					Statement st = con.createStatement();
					ResultSet resultSet = st.executeQuery("SELECT careplan_name FROM praxifydbUS.careplan_template");

					String templateName;
					while(resultSet.next()) {
						templateName = resultSet.getString(1); 
						if(templateName != null)
							System.out.println(templateName);
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
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}

			}
			else {
				LOGGER.info("DataSOurce injection falied ...");
			}
		}
	}

	@Override
	public void ejbContextEnjection() {

		if(null != ejbContext) {

			LOGGER.info("ejbContetx injected succeessully inside session bean...");
		}
		else {
			LOGGER.info("ejbContetx injection failed");
		}

		if(null != context) {

			LOGGER.info("Sessioncontext injected succeessully inside session bean...");
		}
		else {
			LOGGER.info("Sessioncontext injection failed");
		}
	}

	@Override
	public void timerServiceInjection() {

		if(null != timerService) {
			LOGGER.info("Timer Service Injected Successfullt... ready to use");
		}
		else {
			LOGGER.info("Timer Service Injection failed... NOT ready to use");
		}
	}
}
