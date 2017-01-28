package com.example.ejb3;

import javax.ejb.Local;

@Local
public interface TestInf {

	public void sayHello();
	public String getTestString();
}
