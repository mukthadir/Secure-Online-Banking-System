package com.dao;

import com.db.Admininfo;

public interface AdminDAO {

	public Admininfo getAdmin();
	
	public String getAdminEmailId();
	
	public boolean isExistingAdminEmail(String emailId);
}
