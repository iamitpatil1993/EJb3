package com.example.ejb3;

import javax.ejb.Stateless;

/**
 * Session Bean implementation class TestBeanOther
 */
//This is Local-Client-View
@Stateless
public class TestBeanOther implements TestInf2 {

	/**
	 * Default constructor. 
	 */
	public TestBeanOther() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void sayGoodMorning() {
	
		System.out.println("Say Hello From TestOtherBean");
	}



}
