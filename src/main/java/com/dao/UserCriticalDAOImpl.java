package com.dao;

import java.security.InvalidParameterException;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.db.HibernateUtil;
import com.db.Merchantinfo;
import com.db.Usercritical;

@Transactional
@Repository("userCriticalDAO")
public class UserCriticalDAOImpl implements UserCriticalDAO {

	Session session = HibernateUtil.getSessionFactory().openSession();

	@Override
	public void saveOrUpdate(Usercritical userCritical) {
		if (!session.isOpen()) {
			session = HibernateUtil.getSessionFactory().openSession();
		}
		try {
			session.beginTransaction();
			try {

				session.saveOrUpdate(userCritical);
				session.getTransaction().commit();
			} catch (Exception ex) {
				session.getTransaction().rollback();
				throw new InvalidParameterException("Invalid");
			}

		} finally {
			session.close();
		}
	}
	
	public String[] getSsnRequests()
	{
		if (!session.isOpen()) {
			session = HibernateUtil.getSessionFactory().openSession();
		}
		 Query query = session.createQuery("FROM Usercritical WHERE isAdminApproved = :approve");
		 query.setParameter("approve",false);

		 List<Usercritical> list = query.list();
		 String[] temp=new String[list.size()];
		 for(int i=0;i<list.size();i++){
			 temp[i]=list.get(i).getUsername();
			 return temp;
		 }
		 session.close();
		 return null;
	}
	
	public List<Usercritical> getCriticalRequests()
	{
		if (!session.isOpen()) {
			session = HibernateUtil.getSessionFactory().openSession();
		}
		 Query query = session.createQuery("FROM Usercritical WHERE isAdminApproved = :approve");
		 query.setParameter("approve",false);

		 List<Usercritical> list = query.list();
		 session.close();
		return list;
	}
	
	public List<Usercritical> getCriticalTable()
	{
		if (!session.isOpen()) {
			session = HibernateUtil.getSessionFactory().openSession();
		}
		 Query query = session.createQuery("FROM Usercritical");

		 List<Usercritical> list = query.list();
		 session.close();
		return list;
	}

}
