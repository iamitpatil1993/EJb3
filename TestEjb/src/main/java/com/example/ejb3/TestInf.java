package com.example.ejb3;

import javax.ejb.Local;

@Local
//This @Local annotation is optional, Bean business interfaces are by default Loal and hence beans has Local-Client-View
public interface TestInf {

	public void sayHello();
	public String getTestString();
}
