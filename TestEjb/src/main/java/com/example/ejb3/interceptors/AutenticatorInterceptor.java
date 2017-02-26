package com.example.ejb3.interceptors;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import org.apache.log4j.Logger;

//This interceptor will be used fro authentiacation
public class AutenticatorInterceptor {
	
	private static Logger LOGGER;
	
	static {
		LOGGER = Logger.getLogger(AutenticatorInterceptor.class);
	}
	
	@AroundInvoke
	public Object authenticateUser(InvocationContext context) throws Exception {
		
		Object result = null;
		
		LOGGER.info("Authenticating: " + context.getMethod().getDeclaringClass().getName() + "." + context.getMethod().getName() + "() ... entering");
		result = context.proceed();
		LOGGER.info("Authenticating: " + context.getMethod().getDeclaringClass().getName() + "." + context.getMethod().getName() + "() ... leaving");
				
		return result;
	}
	
}
