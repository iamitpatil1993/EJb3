package com.example.ejb3;

import javax.ejb.EJB;
import javax.ejb.Stateless;

//This is Local-Client-View 	
@Stateless
public class TestBean implements TestInf {

	/*
	 * Bean/resources references can not be static. And Obliviously, they are get injected runtime, and container will assign value to them at 
	run-time we can not mark then as 'final'. Because final fields are not supposed to change at runtime. And container will change it's value at run-time. i.e will
	assign value to it.*/
	//http://stackoverflow.com/questions/3195365/injecting-a-static-ejb-nonsense
	@EJB(beanName="TestBeanOther")
	public TestInf2 testBean2;

	@Override
	public void sayHello() {
		// TODO Auto-generated method stub
		System.out.println("Hello World!");

		if(testBean2 != null)
			testBean2.sayGoodMorning();
		else
			System.err.println("TestOtherBean Injection failed");
	}

	@Override
	public String getTestString() {
		// TODO Auto-generated method stub
		return "Amit Rocks";
	}
}