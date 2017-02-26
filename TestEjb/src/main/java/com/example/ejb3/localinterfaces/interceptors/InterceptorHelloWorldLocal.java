package com.example.ejb3.localinterfaces.interceptors;

import javax.ejb.Local;

@Local
public interface InterceptorHelloWorldLocal {

	public void helloWorldInterceptor();
	public void multipleInterceptorsAtMethodLevelDemo();
}
