package com.dao;

import java.util.List;

import com.db.Usercritical;

public interface UserCriticalDAO {

	public void saveOrUpdate(Usercritical userCritical);
	
	public String[] getSsnRequests();
	
	public List<Usercritical> getCriticalRequests();
	
	public List<Usercritical> getCriticalTable();
}
