package com.example.ejb3.beans.singleton;

import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.AccessTimeout;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.DependsOn;
import javax.ejb.EJB;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import org.apache.log4j.Logger;

import com.example.ejb3.localinterfaces.singleton.SIngletonTestBeanLocal2;
import com.example.ejb3.localinterfaces.singleton.TestSingletonLocal;

/*Singleton is one of the EJB bean time like Stateless and Stateful and MDBs
 * There will be only one instance of this bean, no pooling and all clients will share this object
 * For each client there will be new thread, but all threads will share this instance.
 * 
 * Bean life cycle methods can be used to simulate application startup and application shut down call back methods , only 
 * if it is annoted with @Startup annotation at class level 
*/

@Singleton
//Startup annottion will force application server to create single insatcne for this bean at application startup
//Application server will inject this bean regardless of weather this bean is injected by client or not
//if we do not give this annotation, application server will be free to determine when to instantiate this class (mostly when user will inject it first time)
@Startup
@DependsOn(value={"SingletonTestBean2"})//This will force container to instantiate 'SingletonTestBean2' bean before this one regardless of @Startup used or not at 'SingletonTestBean2' bean 
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
@AccessTimeout(unit=TimeUnit.SECONDS, value=30) //Used to control access timeout
public class TestSingletonBean implements TestSingletonLocal {

	private static final Logger LOGGER;
	private Integer count = 0;
	
	
	@EJB
	public SIngletonTestBeanLocal2  singletonTestBeanLocal2;
	
	static {
		LOGGER = Logger.getLogger(TestSingletonBean.class);
	}
	
	//by default all methods of singleton bean has @Lock type WRITE, we need to use @Lock annotation to override this behavior
	//this method will act as application start up call back
	//this method will be used to load data at application start up or other application start up tasks.
	@PostConstruct
	@Lock(LockType.READ)
	private void init() {
		
		LOGGER.info("Inside SingletonBean Init() ... Application Start up callback");
	}
	
	//this method will act as application shut down call back handler
	@Lock(LockType.READ)
	@PreDestroy
	private void destroy() {
		
		LOGGER.info("Inside SingletonBean destroy() ... Application shut down callback");
	}

	//Need to override this, othersie application server take lock on entire object un-necessarily
	@Lock(LockType.READ)
	@Override
	public void sayHello() {
		// TODO Auto-generated method stub
		LOGGER.info("Saying Hello from TestSingletonBean ..");
		singletonTestBeanLocal2.sayHello();
	}

	@Override
	public Integer getCounter() {
		// TODO Auto-generated method stub
		return count;
	}

	@Override
	public void increamentCounter() {
		count++;
	}
	
	
	
	
}
