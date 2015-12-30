package com.model;

import java.util.Date;

public class AmountTranfer {

	private Double amountDebited;
	private Double amountCredited;
	private Date dateOfTransaction;

	
	public Double getAmountDebited() {
		return amountDebited;
	}
	public void setAmountDebited(Double amountDebited) {
		this.amountDebited = amountDebited;
	}
	public Double getAmountCredited() {
		return amountCredited;
	}
	public void setAmountCredited(Double amountCredited) {
		this.amountCredited = amountCredited;
	}
	public Date getDateOfTransaction() {
		return dateOfTransaction;
	}
	public void setDateOfTransaction(Date dateOfTransaction) {
		this.dateOfTransaction = dateOfTransaction;
	}
	
	
}
