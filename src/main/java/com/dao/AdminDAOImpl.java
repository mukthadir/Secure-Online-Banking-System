package com.dao;

import com.db.Admininfo;
import com.db.HibernateUtil;
import java.security.InvalidParameterException;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository("adminDAO")
public class AdminDAOImpl implements AdminDAO{

    Session session = HibernateUtil.getSessionFactory().openSession();

	@Override
	public Admininfo getAdmin() {
		if (!session.isOpen()) {
			session = HibernateUtil.getSessionFactory().openSession();
		}
		try{
		 Query query = session.createQuery("FROM Admininfo WHERE adminId = :adminId ");
		 query.setParameter("adminId", "admin");
		// session.beginTransaction();
		 List<Admininfo> list = query.list();
		 Admininfo adminInfo;
		 if(list.size() != 0) {
			 adminInfo = list.get(0);
			 System.out.println("Email id admin:"+adminInfo.getAdminEmail());
			// session.close();
			 return adminInfo;
		 }
		 System.out.println("Its null");
		//session.close();
		 return null;
		}
		catch(Exception e)
		{
			throw new InvalidParameterException("Invalid");
		}
		finally
		{
			session.close();
		}
	}
	
	@Override
	public boolean isExistingAdminEmail(String emailId) {
		if(!session.isOpen()) {
			session = HibernateUtil.getSessionFactory().openSession();
		}
		Query query = session.createQuery("FROM Admininfo WHERE adminEmail = :adminEmail ");
		 query.setParameter("adminEmail", emailId);
//		 session.beginTransaction();
		 List<Admininfo> list = query.list();
		 if(list.size() != 0) {
				 session.close();
				 return true;
		 }
		 session.close();
		 return false;
	}
	
	public String getAdminEmailId()
	{
		if (!session.isOpen()) {
			session = HibernateUtil.getSessionFactory().openSession();
		}
		try{
		Query query=session.createQuery("FROM Admininfo WHERE adminId = :adminId");
		query.setParameter("adminId","admin");
		List<Admininfo> list=query.list();
		Admininfo adminInfo;
		if(list.size()!=0){
			adminInfo=list.get(0);
			//session.close();
			return adminInfo.getAdminEmail();
		}
		//session.close();
		return null;
		}
		catch(Exception e)
		{
			throw new InvalidParameterException("Invalid");
		}
		finally
		{
			session.close();
		}
	}

}
