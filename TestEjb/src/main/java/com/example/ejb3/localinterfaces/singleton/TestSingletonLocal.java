package com.example.ejb3.localinterfaces.singleton;

import javax.ejb.Local;

@Local
public interface TestSingletonLocal {

	public void sayHello();
	public Integer getCounter();
	public void increamentCounter();
	
}
