package com.example.ejb3.beans.dependencyInjection;

import javax.annotation.Resource;
import javax.ejb.Asynchronous;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
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
}
