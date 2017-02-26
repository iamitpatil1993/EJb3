package com.example.ejb3.beans.interceptors;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.interceptor.ExcludeClassInterceptors;
import javax.interceptor.ExcludeDefaultInterceptors;
import javax.interceptor.Interceptors;

import com.example.ejb3.interceptors.AutenticatorInterceptor;
import com.example.ejb3.interceptors.HelloWorldInterceptor;

//This bean has No-Interface view
@Stateless
@LocalBean
@TransactionManagement(TransactionManagementType.CONTAINER)
@Interceptors(value={AutenticatorInterceptor.class, HelloWorldInterceptor.class})
public class InterceptorClassLevelBean {

	//Nothing to do at method level, since class level interceptor is used.
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public void classLevelInterceptorDemo() {
		
		//Nothing to do here
	}
	
	
	//@ExcludeClassInterceptors can be used at method level to exclude triggering of class level interceptors, which get by default applied to all bean methods 
	//@@ExcludeDefaultInterceptors can be used at method level to exclude triggering of default interceptors defined in deployment-descriptor(ejb-jar.xml file),
	//which get by default applied to all bean methods of all beans in ejb jar
	@ExcludeClassInterceptors
	@ExcludeDefaultInterceptors
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public void excludeClassLevelAndDefaultInterceptor() {
		
		//Nothing to do here
		//No logger/authentication interceptors will intercept this method invocation. Call to this method will take place as normal bean method
	}
	
}
