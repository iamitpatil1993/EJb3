package com.example.ejb3.beans.interceptors;

import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import com.example.ejb3.interceptors.AutenticatorInterceptor;
import com.example.ejb3.interceptors.HelloWorldInterceptor;
import com.example.ejb3.localinterfaces.interceptors.InterceptorHelloWorldLocal;


@Stateless
public class InterceptorHelloWorldBean implements InterceptorHelloWorldLocal {

	//we can define which interceptors to be triggered on method invocation at method level. The sequence in which we declare intereptors,
	//will determine order of interceptor invocation
	@Interceptors(HelloWorldInterceptor.class)
	@Override
	public void helloWorldInterceptor() {
		
		//Nothing to do here
		
	}

	//while calling interceptors get triggered in the same order in which they are declared in @Interceptors annotation, and when method returns, interceptors get 
	//invoked in exactly opposite order, they get invoked on revered order.
	@Interceptors(value={AutenticatorInterceptor.class, HelloWorldInterceptor.class})
	@Override
	public void multipleInterceptorsAtMethodLevelDemo() {
		//nothing to do here
		
	}

}
