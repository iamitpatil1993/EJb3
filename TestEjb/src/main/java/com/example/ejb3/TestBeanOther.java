package com.example.ejb3;

import javax.ejb.Stateless;

import org.apache.log4j.Logger;

/**
 * Session Bean implementation class TestBeanOther
 */
//This is Local-Client-View
@Stateless
public class TestBeanOther implements TestInf2 {

	/**
	 * Default constructor. 
	 */

	public static Logger logger = Logger.getLogger(TestBeanOther.class);

	public TestBeanOther() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void sayGoodMorning() {

		logger.info("Say Hello From TestOtherBean");
	}
}
