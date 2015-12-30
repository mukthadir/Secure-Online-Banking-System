package com.dao;

import java.util.List;

import com.db.AccountSummary;
import com.db.Admininfo;
import com.db.Dummytransaction;
import com.db.Employee;
import com.db.User;

public interface UserDAO {

	public User getUserById(String userId);
	
	public AccountSummary getAccountSummaryByAccountNumber(int accountNumber);
	
	public void createUser(User user);

	public void updateUser(User user);
	
	public List<User> getUserTable();
	
	public boolean deleteUser(User user);
	
	public boolean isExistingUserEmailId(String emailId);
	
	public String isUserExists(User user);
	
	public User getUserByIdOrEmail(String userIdOrEmail); 
}


