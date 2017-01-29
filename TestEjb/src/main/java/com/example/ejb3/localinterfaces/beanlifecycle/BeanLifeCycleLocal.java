package com.example.ejb3.localinterfaces.beanlifecycle;

import javax.ejb.Local;

//we are not declaring bean life cyles methods in BI interface. Since we don't want to 
//expose them to client
@Local
public interface BeanLifeCycleLocal {

	public void sayHello();
	
}
