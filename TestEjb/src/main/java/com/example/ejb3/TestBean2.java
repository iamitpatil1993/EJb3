package com.example.ejb3;

import javax.ejb.Stateless;

@Stateless
public class TestBean2 implements TestInf2{

	@Override
	public void sayGoodMorning() {
		// TODO Auto-generated method stub
		System.out.println("Good Morning !!!");
	}

}
