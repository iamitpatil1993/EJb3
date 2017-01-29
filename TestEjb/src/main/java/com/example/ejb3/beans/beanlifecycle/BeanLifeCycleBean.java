package com.example.ejb3.beans.beanlifecycle;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.log4j.Logger;

import com.example.ejb3.beans.nointerfaceview.NoInterfaceViewBean;
import com.example.ejb3.localinterfaces.beanlifecycle.BeanLifeCycleLocal;

@Stateless
public class BeanLifeCycleBean implements BeanLifeCycleLocal{

	Logger logger = Logger.getLogger(BeanLifeCycleBean.class);
	
	@EJB
	private NoInterfaceViewBean bean;
	
	@Override
	public void sayHello() {
	
		logger.info("Hello World!!! from BeanLifeCycleBean");
	}

	@PostConstruct
	private void postConstructCallback() throws IOException{
		
		logger.info("Inside @postConstruct Callback");
		if(null != bean){
			bean.sayHello();
		}
	}
	
	@PreDestroy
	private void preDestroyCallback(){
		
		logger.info("Inside @preDestroy Callback");
	}
	
	/*NOte:
	 * 1.Bean class can have multiple methods with same life cycle annotation, it does not gives any error while deploying or while executing, but container choose
	 * random methods among multiple.
	 * 2.If bean life cycle method has return type other that void it gives deploy time error.
	 * 3.EJB3 says call back method signature must be like this void METHOD_NAme () : If method takes ant any argument, then that method is not get recognised y container
	 * as life cycle call back and container does not call that method when that life cycle event occurs.
	 * 4, If life cycle method throws catched excetption, container calls method successfully, and executes methods as well, but if exception is actually get thrown
	 * from method then it gives reuntime exception, because container does not know how to handle that thrown exception. So in conclusion, life cycle method suould not
	 * have throws clause with checked exceptions.
	 * 5.@PostConstruct life cycle method gets called after all instance variables injected using DI, so we can use injected resources in PostConstruct life
	 * cycle call back
	 * 
	 * 
	 * 
	 * */
}
