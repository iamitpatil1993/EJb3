package com.example.ejb3.localinterfaces.dependencyInjection;

import javax.ejb.Local;

@Local
public interface DependencyInjectionDemoBeanLocal {

	void emailHandler(String subject, String to, String message);
	void sendMail(String subject, String toEmail, String message);
	void dataSourceInjection();
	void ejbContextEnjection();
	void timerServiceInjection();
}
