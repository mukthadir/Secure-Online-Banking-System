package com.dao;

public class TransactionWithbalance {
	private String date;
	private int balance;
	private int debit;
	private int credit;
	private boolean isBeingReviewed;
	private int transactionId;
	private boolean modify;
	private boolean delete;
	
	
	public int getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}
	public boolean isModify() {
		return modify;
	}
	public void setModify(boolean modify) {
		this.modify = modify;
	}
	public boolean isDelete() {
		return delete;
	}
	public void setDelete(boolean delete) {
		this.delete = delete;
	}
	public boolean isBeingReviewed() {
		return isBeingReviewed;
	}
	public void setBeingReviewed(boolean isBeingReviewed) {
		this.isBeingReviewed = isBeingReviewed;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public int getDebit() {
		return debit;
	}
	public void setDebit(int debit) {
		this.debit = debit;
	}
	public int getCredit() {
		return credit;
	}
	public void setCredit(int credit) {
		this.credit = credit;
	}
	
	
}
