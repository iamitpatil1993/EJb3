package com.example.ejb3.beans.asynchronous;

import javax.annotation.Resource;
import javax.ejb.Asynchronous;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import org.apache.log4j.Logger;

import com.example.ejb3.localinterfaces.asynchronous.AsynchronousDemoBeanLocal;

@Stateless
public class AsynchronousDemoBean implements AsynchronousDemoBeanLocal {

	private static final Logger LOGGER;
	
	//In EJB, there is one interface 'EjbContext' which provided access to container services. But this interface is generic interface, therefore there are 
	//other three interfaces that are use to access container services  BUT specific to ejb bean type. 'SessionContext', 'MessageDrivenContecxt' and 'EntityContect'
	//are three interfaces that extends 'EjbContext' interface and provide additional methods or restrict methods specific to bean type. (Session bean, MDB and entity resp.)
	//Here we are injecting 'SessionContext' because this is session bean and we want additional services from container. We use @Resource annotation to inject
	//these object references.
	@Resource
	public SessionContext context;
	
	static {
		LOGGER = Logger.getLogger(AsynchronousDemoBean.class);
	}
	
	//Since method returning void, method must not throw application exception, since this method returns to application container, how
	//container will come to know that how to handle thrown application exception. therefore async method with void return type must not throw
	//application exception. If it returns Future<> interface concrete implementation class, it can throw application exception outside exception
	//because Future<> interface has call back methods to handle these exceptions.
	@Asynchronous
	@Override
	public void processPayment(String debitCardNo, String cvv,
			String cardholderName) {
		
		LOGGER.info("Inside AsynchronousDemoBean.processPayment() ...entered");
		LOGGER.info("ThreadName : " + Thread.currentThread().getName());
		
		StringBuffer buffer = new StringBuffer();
		buffer.append("debitCardNo : ").append(debitCardNo).append("cvv : ").append(cvv).append("cardholderName : ").append(cardholderName);
		LOGGER.info("details : " + buffer);
		
		//slepping thread for 20 seconds intentionally, to test async behavior
		try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			
			//InterruptedException is an application exception, it is subclass of Exception class and not RuntimeException or RemoteExcecption, therefore
			//need to handle this exception here itself, can't thow this excecption outside this method
			e.printStackTrace();
		}
		
		LOGGER.info("Inside AsynchronousDemoBean.processPayment() ...leaving");
	}

	//Note this method is not async, this method will execute synchronously
	@Override
	public void processPayment2(String debitCardNo, String cvv,
			String cardholderName) {
		
		LOGGER.info("Inside AsynchronousDemoBean.processPayment2() ...entered");
		LOGGER.info("ThreadName validatePaymentOrder.processPayment2() : " + Thread.currentThread().getName());
		
		if(validatePaymentOrder(debitCardNo, cvv, cardholderName)) {
			
			//this method call will take place synchronously, since we are not using sessioncontext to proxy this call
			//processPayment(debitCardNo, cvv, cardholderName);
			
			//this method call will take place Asynchronously, since we are using sessioncontext to proxy this call
			//using context object to get EjbObject with provided business interfaceview so that we can call method on that bean. 
			//we need to create new bean instance, becasse we want to delegate this task to new bean. If we don't do this, and call
			//processPayment method on current bean instance, that call will take place synchronously.
			AsynchronousDemoBeanLocal asynchronousDemoBean = context.getBusinessObject(AsynchronousDemoBeanLocal.class);
			if(asynchronousDemoBean != null)
				asynchronousDemoBean.processPayment(debitCardNo, cvv, cardholderName);
			else
				LOGGER.info("asynchronousDemoBean injected null");
		}
		else
		{
			LOGGER.info("Invalid data exception");
		}
			
		LOGGER.info("Inside AsynchronousDemoBean.processPayment2() ...leaving");
	}
	
	
	//Validation code will also work synchronously
	private boolean validatePaymentOrder(String debitCardNo, String cvv,
			String cardholderName) {
		
		LOGGER.info("Inside AsynchronousDemoBean.validatePaymentOrder() ...entered");
		LOGGER.info("ThreadName validatePaymentOrder.validatePaymentOrder() : " + Thread.currentThread().getName());
		if(null == debitCardNo || null == cvv || null == cardholderName)
			return false;
		
		LOGGER.info("Inside AsynchronousDemoBean.validatePaymentOrder() ...leaving");
		return true;
	}
}
