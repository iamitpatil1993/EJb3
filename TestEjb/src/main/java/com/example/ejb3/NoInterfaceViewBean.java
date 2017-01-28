package com.example.ejb3;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import org.apache.log4j.Logger;


//No interface view beans do not has any businiss interfaces.
//We can declare bean as No-Interface View using @LocalBean or we can omit it as well. (But bean class must not implement any interface other than 
//Serirializalble and Externalizable)

@Stateless
@LocalBean //This annotation is optional, bean has no-inerface-view as long as it does not impletents any interface otehr that serializable and exteralizable.
public class NoInterfaceViewBean {

	public static Logger logger = Logger.getLogger(NoInterfaceViewBean.class);
	
	//By Default all public methods of bean class becomes visible as Business methods to client
	public void sayHello(){
		logger.info("Hello Workd!!! from NoInterfaceViewBean");
	}
}
