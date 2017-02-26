package com.example.ejb3.interceptors;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import org.apache.log4j.Logger;

//This is defalt inteceptors, which will get invoked before execution of all methods of all beans packaged in this ejb jar
//We must configure this default interceptors in ejb-jar.xml file. This file is in resources/META-INF/ejb.jar.xml location of this ejb module
public class DefaultInterceptor {
	
	private static final Logger LOGGER;
	
	static {
		LOGGER = Logger.getLogger(DefaultInterceptor.class);
	}
	
	
	@AroundInvoke
	public Object defaultInterceptor(InvocationContext context) throws Exception {
		
		LOGGER.info("Inside " + context.getMethod().getDeclaringClass().getName() + "." + context.getMethod().getName() + " Entering");
		return context.proceed();
	}

}
