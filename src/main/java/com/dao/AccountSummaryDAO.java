package com.dao;

import com.db.AccountSummary;
import com.db.User;

public interface AccountSummaryDAO {

	void createAccount(AccountSummary accountSummary);
	boolean isAccountNumberExists(int accountNumber);
	AccountSummary getSummary(int checkingsAccountNumber);
	void updateAccountSummary(AccountSummary accountSummary);
}
