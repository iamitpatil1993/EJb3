package com.example.ejb3.localinterfaces.login;

import java.util.Map;

import javax.ejb.Local;

@Local
public interface PersonDetailsLocal {


	public void addOrUpdatePersonDetails(String username, String lastName, String firstName, String email);
	public Map<String, String> getPersonDetails();
	public void invalidate();

}
