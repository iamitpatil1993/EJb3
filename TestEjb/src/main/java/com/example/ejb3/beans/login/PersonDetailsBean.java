package com.example.ejb3.beans.login;

import java.util.Map;
import java.util.TreeMap;

import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.apache.log4j.Logger;

import com.example.ejb3.localinterfaces.login.PersonDetailsLocal;



@Stateful
@TransactionManagement(TransactionManagementType.CONTAINER)
public class PersonDetailsBean implements PersonDetailsLocal{

	private static Logger LOGGER;
	private String username;
	private String firstName;
	private String lastName;
	private String email;
	
	static {
		LOGGER = Logger.getLogger(PersonDetailsBean.class);
	}
	
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public void addOrUpdatePersonDetails(String username, String lastName,
			String firstName, String email) {
		
		LOGGER.info("INside addOrUpdatePersonDetails");
		this.firstName = firstName;
		this.username = username;
		this.lastName = lastName;
		this.email = email;
		
		LOGGER.info("Personal Details = : " +this);
	}

	@Override
	public String toString() {
		return "PersonDetailsBean [username=" + username + ", firstName="
				+ firstName + ", lastName=" + lastName + ", email=" + email
				+ "]";
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Map<String, String> getPersonDetails() {
		
		 Map<String, String> map = null;
		if(null != username) {
			map = new TreeMap<String, String>();
			map.put("username", username);
			map.put("firstName", firstName);
			map.put("lastName", lastName);
			map.put("email", email);
		}
		return map;
	}

	@Override
	@Remove
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public void invalidate() {
		LOGGER.info("Inside invalidate bean for : " + username);
		
	}
}
