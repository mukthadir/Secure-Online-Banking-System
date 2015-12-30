package com.dao;

import java.util.List;

import com.db.Dummytransaction;
import com.db.Employee;

public interface DummyTransactionDAO {

	public void createTransaction(Dummytransaction dummyTransaction);
	
	public List<Dummytransaction> pullMerchantPaymentRequests(int accountNumberOfSender);
	
	public void updateTransaction(Dummytransaction dummyTransaction);
	
	public List<Dummytransaction> getCriticalTransaction();
	
	public void criticalTransactionApproved(Dummytransaction dummyTransaction);
	
	public void criticalTransactionRejected(Dummytransaction dummyTransaction);
	
	public boolean isEmployeeFree(Employee emp);
	
	public List<Dummytransaction> getTable();
	
	public List<Dummytransaction> getEmployeeTransaction(Employee empid);
	
	public List<Dummytransaction> getModifyTransaction(Employee empid);
	
	public List<Dummytransaction> getAllTransactionsForUser(int checkingsAccountNumber);
}
