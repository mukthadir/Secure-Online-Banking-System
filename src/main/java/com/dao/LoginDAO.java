package com.dao;

import java.util.List;

import com.db.Login;
import com.db.User;

public interface LoginDAO {

	public String getRole(String username, String password);
	
	public void saveLoginInformation(Login login);
	
	public List<Login> getLoginTable();

	public Login getUsername(String username);
	
	public void userregistration(Login log);
	
	public Login getLoginInformation(String username);
	
	public void updateLoginInformation(Login login);
	
	public boolean isExistingUserId(String userId);
}
