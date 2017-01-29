package com.example.ejb3.beans;

import javax.ejb.Stateless;

import org.apache.log4j.Logger;

import com.example.ejb3.localinterfaces.TestInf2;

//This is Local-Client-View
@Stateless
public class TestBean2 implements TestInf2{

	public static Logger logger = Logger.getLogger(TestBean2.class);

	@Override
	public void sayGoodMorning() {
		// TODO Auto-generated method stub
		logger.info("Good Morning !!!");
	}

}
