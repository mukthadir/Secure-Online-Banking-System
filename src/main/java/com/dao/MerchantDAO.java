package com.dao;

import com.db.Merchantinfo;

public interface MerchantDAO {

	public Merchantinfo getMerchant();
	
	public void updateProfile(Merchantinfo merchant);
	
	public boolean isExistingMerchantEmail(String emailId);
}
