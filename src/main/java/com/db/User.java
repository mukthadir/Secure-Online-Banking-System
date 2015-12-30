package com.db;
// default package
// Generated Nov 6, 2014 12:59:42 AM by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * User generated by hbm2java
 */
@Entity
@Table(name = "user", catalog = "cse545group4bank")
public class User implements java.io.Serializable {

	private String username;
	private String firstName;
	private String lastName;
	private String emailAddress;
	private String ssn;
	private String gender;
	private String dob;
	private String securityQ1;
	private String securityQ2;
	private String securityA1;
	private String securityA2;
	private int checkingsAccountNumber;
	private String publicKey;

	public User() {
	}

	public User(String username, String firstName, String lastName,
			String emailAddress, String ssn, String gender, String dob,
			String securityQ1, String securityQ2, String securityA1,
			String securityA2, int checkingsAccountNumber, String publicKey) {
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAddress = emailAddress;
		this.ssn = ssn;
		this.gender = gender;
		this.dob = dob;
		this.securityQ1 = securityQ1;
		this.securityQ2 = securityQ2;
		this.securityA1 = securityA1;
		this.securityA2 = securityA2;
		this.checkingsAccountNumber = checkingsAccountNumber;
		this.publicKey = publicKey;
	}

	@Id
	@Column(name = "USERNAME", unique = true, nullable = false, length = 45)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "FIRST_NAME", nullable = false, length = 200)
	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "LAST_NAME", nullable = false, length = 50)
	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(name = "EMAIL_ADDRESS", nullable = false, length = 45)
	public String getEmailAddress() {
		return this.emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	@Column(name = "SSN", nullable = false, length = 45)
	public String getSsn() {
		return this.ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	@Column(name = "GENDER", nullable = false, length = 45)
	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Column(name = "DOB", nullable = false, length = 20)
	public String getDob() {
		return this.dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	@Column(name = "SECURITY_Q1", nullable = false, length = 45)
	public String getSecurityQ1() {
		return this.securityQ1;
	}

	public void setSecurityQ1(String securityQ1) {
		this.securityQ1 = securityQ1;
	}

	@Column(name = "SECURITY_Q2", nullable = false, length = 45)
	public String getSecurityQ2() {
		return this.securityQ2;
	}

	public void setSecurityQ2(String securityQ2) {
		this.securityQ2 = securityQ2;
	}

	@Column(name = "SECURITY_A1", nullable = false, length = 45)
	public String getSecurityA1() {
		return this.securityA1;
	}

	public void setSecurityA1(String securityA1) {
		this.securityA1 = securityA1;
	}

	@Column(name = "SECURITY_A2", nullable = false, length = 45)
	public String getSecurityA2() {
		return this.securityA2;
	}

	public void setSecurityA2(String securityA2) {
		this.securityA2 = securityA2;
	}

	@Column(name = "CHECKINGS_ACCOUNT_NUMBER", nullable = false)
	public int getCheckingsAccountNumber() {
		return this.checkingsAccountNumber;
	}

	public void setCheckingsAccountNumber(int checkingsAccountNumber) {
		this.checkingsAccountNumber = checkingsAccountNumber;
	}

	@Column(name = "PUBLIC_KEY", nullable = false, length = 90)
	public String getPublicKey() {
		return this.publicKey;
	}

	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}

}
