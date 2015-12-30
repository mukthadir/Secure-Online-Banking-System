package com.db;
// default package
// Generated Nov 6, 2014 12:59:42 AM by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Merchantinfo generated by hbm2java
 */
@Entity
@Table(name = "merchantinfo", catalog = "cse545group4bank")
public class Merchantinfo implements java.io.Serializable {

	private String merchantId;
	private String merchantName;
	private String merchantEmail;
	private String merchantAddress;
	private String merchantIncomeTaxNumber;
	private Boolean merchantDeleteFlag;
	private int merchantAccountNumber;
	private String merchantA1;
	private String merchantA2;
	private String publicKey;

	public Merchantinfo() {
	}

	public Merchantinfo(String merchantId, String merchantName,
			String merchantEmail, String merchantAddress,
			String merchantIncomeTaxNumber, int merchantAccountNumber) {
		this.merchantId = merchantId;
		this.merchantName = merchantName;
		this.merchantEmail = merchantEmail;
		this.merchantAddress = merchantAddress;
		this.merchantIncomeTaxNumber = merchantIncomeTaxNumber;
		this.merchantAccountNumber = merchantAccountNumber;
	}

	public Merchantinfo(String merchantId, String merchantName,
			String merchantEmail, String merchantAddress,
			String merchantIncomeTaxNumber, Boolean merchantDeleteFlag,
			int merchantAccountNumber, String merchantA1, String merchantA2,
			String publicKey) {
		this.merchantId = merchantId;
		this.merchantName = merchantName;
		this.merchantEmail = merchantEmail;
		this.merchantAddress = merchantAddress;
		this.merchantIncomeTaxNumber = merchantIncomeTaxNumber;
		this.merchantDeleteFlag = merchantDeleteFlag;
		this.merchantAccountNumber = merchantAccountNumber;
		this.merchantA1 = merchantA1;
		this.merchantA2 = merchantA2;
		this.publicKey = publicKey;
	}

	@Id
	@Column(name = "MERCHANT_ID", unique = true, nullable = false, length = 40)
	public String getMerchantId() {
		return this.merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	@Column(name = "MERCHANT_NAME", nullable = false, length = 45)
	public String getMerchantName() {
		return this.merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	@Column(name = "MERCHANT_EMAIL", nullable = false, length = 45)
	public String getMerchantEmail() {
		return this.merchantEmail;
	}

	public void setMerchantEmail(String merchantEmail) {
		this.merchantEmail = merchantEmail;
	}

	@Column(name = "MERCHANT_ADDRESS", nullable = false, length = 45)
	public String getMerchantAddress() {
		return this.merchantAddress;
	}

	public void setMerchantAddress(String merchantAddress) {
		this.merchantAddress = merchantAddress;
	}

	@Column(name = "MERCHANT_INCOME_TAX_NUMBER", nullable = false, length = 45)
	public String getMerchantIncomeTaxNumber() {
		return this.merchantIncomeTaxNumber;
	}

	public void setMerchantIncomeTaxNumber(String merchantIncomeTaxNumber) {
		this.merchantIncomeTaxNumber = merchantIncomeTaxNumber;
	}

	@Column(name = "MERCHANT_DELETE_FLAG")
	public Boolean getMerchantDeleteFlag() {
		return this.merchantDeleteFlag;
	}

	public void setMerchantDeleteFlag(Boolean merchantDeleteFlag) {
		this.merchantDeleteFlag = merchantDeleteFlag;
	}

	@Column(name = "MERCHANT_ACCOUNT_NUMBER", nullable = false)
	public int getMerchantAccountNumber() {
		return this.merchantAccountNumber;
	}

	public void setMerchantAccountNumber(int merchantAccountNumber) {
		this.merchantAccountNumber = merchantAccountNumber;
	}

	@Column(name = "MERCHANT_A1", length = 40)
	public String getMerchantA1() {
		return this.merchantA1;
	}

	public void setMerchantA1(String merchantA1) {
		this.merchantA1 = merchantA1;
	}

	@Column(name = "MERCHANT_A2", length = 45)
	public String getMerchantA2() {
		return this.merchantA2;
	}

	public void setMerchantA2(String merchantA2) {
		this.merchantA2 = merchantA2;
	}

	@Column(name = "PUBLIC_KEY", length = 90)
	public String getPublicKey() {
		return this.publicKey;
	}

	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}

}