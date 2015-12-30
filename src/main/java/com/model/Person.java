package com.model;

import java.sql.Timestamp;

public class Person {
	private String userName;
	private String firstName;
	private String lastName;
	private String password;
	private String address;
	private String phoneNumber;
	private String emailAddress;
	private String ssn;
	private boolean gender;
	private String dob;
	private String bankStatus;
	private String securityQuestion1;
	private String securityAnswer1;
	private String securityQuestion2;
	private String securityAnswer2;
	private String OTP;
	private boolean currentlyLoggedIn;
	private Timestamp lastLoggedInTimeStamp;
	private Account savingsAccount;
	private Account checkingsAccount;
	private Person receiver;
	private boolean blockedStatus;
	private int numberOfAttempts;
	
	

	public boolean isBlockedStatus() {
		return blockedStatus;
	}
	public void setBlockedStatus(boolean blockedStatus) {
		this.blockedStatus = blockedStatus;
	}
	public int getNumberOfAttempts() {
		return numberOfAttempts;
	}
	public void setNumberOfAttempts(int numberOfAttempts) {
		this.numberOfAttempts = numberOfAttempts;
	}
	public Person getReceiver() {
		return receiver;
	}
	public void setReceiver(Person receiver) {
		this.receiver = receiver;
	}
	public Account getSavingsAccount() {
		return savingsAccount;
	}
	public void setSavingsAccount(Account savingsAccount) {
		this.savingsAccount = savingsAccount;
	}
	public Account getCheckingsAccount() {
		return checkingsAccount;
	}
	public void setCheckingsAccount(Account checkingsAccount) {
		this.checkingsAccount = checkingsAccount;
	}
	public Timestamp getLastLoggedInTimeStamp() {
		return lastLoggedInTimeStamp;
	}
	public void setLastLoggedInTimeStamp(Timestamp lastLoggedInTimeStamp) {
		this.lastLoggedInTimeStamp = lastLoggedInTimeStamp;
	}
	public boolean isCurrentlyLoggedIn() {
		return currentlyLoggedIn;
	}
	public void setCurrentlyLoggedIn(boolean currentlyLoggedIn) {
		this.currentlyLoggedIn = currentlyLoggedIn;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getSsn() {
		return ssn;
	}
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	public boolean isGender() {
		return gender;
	}
	public void setGender(boolean gender) {
		this.gender = gender;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getBankStatus() {
		return bankStatus;
	}
	public void setBankStatus(String bankStatus) {
		this.bankStatus = bankStatus;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getSecurityQuestion1() {
		return securityQuestion1;
	}
	public void setSecurityQuestion1(String securityQuestion1) {
		this.securityQuestion1 = securityQuestion1;
	}
	public String getSecurityAnswer1() {
		return securityAnswer1;
	}
	public void setSecurityAnswer1(String securityAnswer1) {
		this.securityAnswer1 = securityAnswer1;
	}
	public String getSecurityQuestion2() {
		return securityQuestion2;
	}
	public void setSecurityQuestion2(String securityQuestion2) {
		this.securityQuestion2 = securityQuestion2;
	}
	public String getSecurityAnswer2() {
		return securityAnswer2;
	}
	public void setSecurityAnswer2(String securityAnswer2) {
		this.securityAnswer2 = securityAnswer2;
	}
	public String getOTP() {
		return OTP;
	}
	public void setOTP(String oTP) {
		OTP = oTP;
	}

}
