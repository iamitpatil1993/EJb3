package com.example.ejb3.localinterfaces.asynchronous;

import javax.ejb.Local;

@Local
public interface AsynchronousDemoBeanLocal {

	//This method is used to Demo 'Only async'
	//this method will work asynchronously, this method will either return void or Future<> interface.
	//If method returns void it must not throw application exception. It can throw application
	//exception only if it returns Future<> interface instance(like AsyncResult<>)
	void processPayment(String debitCardNo, String cvv, String cardholderName);
	
	
	//This second method is used to Demo 'Mixing sync and async'
	void processPayment2(String debitCardNo, String cvv, String cardholderName);
}
