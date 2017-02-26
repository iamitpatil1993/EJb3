package com.example.ejb3.interceptors;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import org.apache.log4j.Logger;

//Interceptor is just POJO, application server determines which method to invoke based on @AroundInvoke annotation on method
public class HelloWorldInterceptor {

	private static Logger LOGGER;
	
	static {
		LOGGER = Logger.getLogger(HelloWorldInterceptor.class);
	}
	
	
	//Interceptor method signature must be like below
	//public Object <methodName> (InvocationContext context) thows Exception
	//there must be only one method annoted with @AroundInvoke inside interceoptor class
	@AroundInvoke
	public Object helloWorldInterceptor(InvocationContext context) throws Exception {
		
		Object result = null;
		
		LOGGER.info(context.getMethod().getDeclaringClass().getName() + "." + context.getMethod().getName() + "() ... entering");
		result = context.proceed();
		LOGGER.info(context.getMethod().getDeclaringClass().getName() + "." + context.getMethod().getName() + "() ... leaving");
				
		return result;
	}
	
}
