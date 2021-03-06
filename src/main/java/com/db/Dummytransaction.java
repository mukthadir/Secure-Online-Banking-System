package com.db;
// default package
// Generated Nov 6, 2014 12:59:42 AM by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Dummytransaction generated by hbm2java
 */
@Entity
@Table(name = "dummytransaction", catalog = "cse545group4bank")
public class Dummytransaction implements java.io.Serializable {

	private Integer transactionId;
	private AccountSummary accountSummaryByUserReceiving;
	private AccountSummary accountSummaryByUserSending;
	private Employee employee;
	private int amount;
	private String time;
	private Boolean critical;
	private Boolean reviewedByEmployee;
	private Boolean isMerchantTransaction;
	private Boolean isApprovedByUser;
	private Boolean isRejectedByUser;
	private Boolean isRejectedByEmployee;
	private Boolean adminReviewing;
	private String message;
	private Boolean modifyFlag;
	private Boolean modifyTransactionFlag;
	private String transactionMessage;

	public Dummytransaction() {
	}

	public Dummytransaction(int amount) {
		this.amount = amount;
	}

	public Dummytransaction(AccountSummary accountSummaryByUserReceiving,
			AccountSummary accountSummaryByUserSending, Employee employee,
			int amount, String time, Boolean critical,
			Boolean reviewedByEmployee, Boolean isMerchantTransaction,
			Boolean isApprovedByUser, Boolean isRejectedByUser,
			Boolean isRejectedByEmployee, Boolean adminReviewing,
			String message, Boolean modifyFlag, Boolean modifyTransactionFlag,
			String transactionMessage) {
		this.accountSummaryByUserReceiving = accountSummaryByUserReceiving;
		this.accountSummaryByUserSending = accountSummaryByUserSending;
		this.employee = employee;
		this.amount = amount;
		this.time = time;
		this.critical = critical;
		this.reviewedByEmployee = reviewedByEmployee;
		this.isMerchantTransaction = isMerchantTransaction;
		this.isApprovedByUser = isApprovedByUser;
		this.isRejectedByUser = isRejectedByUser;
		this.isRejectedByEmployee = isRejectedByEmployee;
		this.adminReviewing = adminReviewing;
		this.message = message;
		this.modifyFlag = modifyFlag;
		this.modifyTransactionFlag = modifyTransactionFlag;
		this.transactionMessage = transactionMessage;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "TRANSACTION_ID", unique = true, nullable = false)
	public Integer getTransactionId() {
		return this.transactionId;
	}

	public void setTransactionId(Integer transactionId) {
		this.transactionId = transactionId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_RECEIVING")
	public AccountSummary getAccountSummaryByUserReceiving() {
		return this.accountSummaryByUserReceiving;
	}

	public void setAccountSummaryByUserReceiving(
			AccountSummary accountSummaryByUserReceiving) {
		this.accountSummaryByUserReceiving = accountSummaryByUserReceiving;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_SENDING")
	public AccountSummary getAccountSummaryByUserSending() {
		return this.accountSummaryByUserSending;
	}

	public void setAccountSummaryByUserSending(
			AccountSummary accountSummaryByUserSending) {
		this.accountSummaryByUserSending = accountSummaryByUserSending;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "EMPLOYEE_ID_REVIEWING")
	public Employee getEmployee() {
		return this.employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@Column(name = "AMOUNT", nullable = false)
	public int getAmount() {
		return this.amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	@Column(name = "TIME", length = 40)
	public String getTime() {
		return this.time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	@Column(name = "CRITICAL")
	public Boolean getCritical() {
		return this.critical;
	}

	public void setCritical(Boolean critical) {
		this.critical = critical;
	}

	@Column(name = "REVIEWED_BY_EMPLOYEE")
	public Boolean getReviewedByEmployee() {
		return this.reviewedByEmployee;
	}

	public void setReviewedByEmployee(Boolean reviewedByEmployee) {
		this.reviewedByEmployee = reviewedByEmployee;
	}

	@Column(name = "IS_MERCHANT_TRANSACTION")
	public Boolean getIsMerchantTransaction() {
		return this.isMerchantTransaction;
	}

	public void setIsMerchantTransaction(Boolean isMerchantTransaction) {
		this.isMerchantTransaction = isMerchantTransaction;
	}

	@Column(name = "IS_APPROVED_BY_USER")
	public Boolean getIsApprovedByUser() {
		return this.isApprovedByUser;
	}

	public void setIsApprovedByUser(Boolean isApprovedByUser) {
		this.isApprovedByUser = isApprovedByUser;
	}

	@Column(name = "IS_REJECTED_BY_USER")
	public Boolean getIsRejectedByUser() {
		return this.isRejectedByUser;
	}

	public void setIsRejectedByUser(Boolean isRejectedByUser) {
		this.isRejectedByUser = isRejectedByUser;
	}

	@Column(name = "IS_REJECTED_BY_EMPLOYEE")
	public Boolean getIsRejectedByEmployee() {
		return this.isRejectedByEmployee;
	}

	public void setIsRejectedByEmployee(Boolean isRejectedByEmployee) {
		this.isRejectedByEmployee = isRejectedByEmployee;
	}

	@Column(name = "ADMIN_REVIEWING")
	public Boolean getAdminReviewing() {
		return this.adminReviewing;
	}

	public void setAdminReviewing(Boolean adminReviewing) {
		this.adminReviewing = adminReviewing;
	}

	@Column(name = "MESSAGE", length = 45)
	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Column(name = "MODIFY_FLAG")
	public Boolean getModifyFlag() {
		return this.modifyFlag;
	}

	public void setModifyFlag(Boolean modifyFlag) {
		this.modifyFlag = modifyFlag;
	}

	@Column(name = "MODIFY_TRANSACTION_FLAG")
	public Boolean getModifyTransactionFlag() {
		return this.modifyTransactionFlag;
	}

	public void setModifyTransactionFlag(Boolean modifyTransactionFlag) {
		this.modifyTransactionFlag = modifyTransactionFlag;
	}

	@Column(name = "TRANSACTION_MESSAGE", length = 45)
	public String getTransactionMessage() {
		return this.transactionMessage;
	}

	public void setTransactionMessage(String transactionMessage) {
		this.transactionMessage = transactionMessage;
	}

}
