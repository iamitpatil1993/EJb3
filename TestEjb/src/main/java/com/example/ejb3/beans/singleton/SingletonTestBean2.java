package com.example.ejb3.beans.singleton;

import javax.annotation.PostConstruct;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;

import org.apache.log4j.Logger;

import com.example.ejb3.localinterfaces.singleton.SIngletonTestBeanLocal2;

@Singleton
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
public class SingletonTestBean2 implements SIngletonTestBeanLocal2{

	private static final Logger LOGGER;
	
	static {
		LOGGER = Logger.getLogger(SingletonTestBean2.class);
	}
	
	
	@Lock(LockType.READ)
	@PostConstruct
	private void init() {
		LOGGER.info("Inside SingletonTestBean2 Init() ...");
	}
	
	@Lock(LockType.READ)
	@PostConstruct
	private void destroy() {
		LOGGER.info("Inside SingletonTestBean2 destroy() ...");
	}

	@Lock(LockType.READ)
	@Override
	public void sayHello() {
		
		LOGGER.info("Saying hello from SingletonTestBean2 ...");
	}
}

