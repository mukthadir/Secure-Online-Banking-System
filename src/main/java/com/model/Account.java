package com.model;

public class Account {

	private String accountNumber;
	private Double balance;
	private AmountTranfer amountTransfer;
	
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	public AmountTranfer getAmountTransfer() {
		return amountTransfer;
	}
	public void setAmountTransfer(AmountTranfer amountTransfer) {
		this.amountTransfer = amountTransfer;
	}
	
}
