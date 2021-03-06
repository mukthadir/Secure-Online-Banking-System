package com.db;
// default package
// Generated Nov 6, 2014 12:59:42 AM by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Login generated by hbm2java
 */
@Entity
@Table(name = "login", catalog = "cse545group4bank")
public class Login implements java.io.Serializable {

	private String loginId;
	private String password;
	private String role;
	private String otp;
	private int failedAttempts;
	private Boolean blockedStatus;
	private Boolean deleteFlag;
	private Boolean loginStatus;

	public Login() {
	}

	public Login(String loginId, String password, String role,
			int failedAttempts) {
		this.loginId = loginId;
		this.password = password;
		this.role = role;
		this.failedAttempts = failedAttempts;
	}

	public Login(String loginId, String password, String role, String otp,
			int failedAttempts, Boolean blockedStatus, Boolean deleteFlag,
			Boolean loginStatus) {
		this.loginId = loginId;
		this.password = password;
		this.role = role;
		this.otp = otp;
		this.failedAttempts = failedAttempts;
		this.blockedStatus = blockedStatus;
		this.deleteFlag = deleteFlag;
		this.loginStatus = loginStatus;
	}

	@Id
	@Column(name = "LOGIN_ID", unique = true, nullable = false, length = 40)
	public String getLoginId() {
		return this.loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	@Column(name = "PASSWORD", nullable = false, length = 45)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "ROLE", nullable = false, length = 45)
	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Column(name = "OTP", length = 45)
	public String getOtp() {
		return this.otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	@Column(name = "FAILED_ATTEMPTS", nullable = false)
	public int getFailedAttempts() {
		return this.failedAttempts;
	}

	public void setFailedAttempts(int failedAttempts) {
		this.failedAttempts = failedAttempts;
	}

	@Column(name = "BLOCKED_STATUS")
	public Boolean getBlockedStatus() {
		return this.blockedStatus;
	}

	public void setBlockedStatus(Boolean blockedStatus) {
		this.blockedStatus = blockedStatus;
	}

	@Column(name = "DELETE_FLAG")
	public Boolean getDeleteFlag() {
		return this.deleteFlag;
	}

	public void setDeleteFlag(Boolean deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	@Column(name = "LOGIN_STATUS")
	public Boolean getLoginStatus() {
		return this.loginStatus;
	}

	public void setLoginStatus(Boolean loginStatus) {
		this.loginStatus = loginStatus;
	}

}
