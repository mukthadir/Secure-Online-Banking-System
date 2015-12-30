package com.dao;

import com.db.AccountSummary;
import com.db.HibernateUtil;
import com.db.User;

import java.security.InvalidParameterException;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository("userDAO")
public class UserDAOImpl implements UserDAO{

    Session session = HibernateUtil.getSessionFactory().openSession();

	@Override
	public User getUserById(String userId) {
		if(!session.isOpen()) {
			session = HibernateUtil.getSessionFactory().openSession();
		}
		Query query = session.createQuery("FROM User WHERE username = :username ");
		query.setParameter("username", userId);
			List<User> list = query.list();
			 User user;
			 if(list.size() != 0) {
				 user = list.get(0);
				 System.out.println("Email id user:"+user.getEmailAddress());
				 session.close();
				 return user;
			 }
		
		 System.out.println("No user exists");
		 session.close();
		return null;
	}
	
	@Override
	public User getUserByIdOrEmail(String userIdOrEmail) {
		if(!session.isOpen()) {
			session = HibernateUtil.getSessionFactory().openSession();
		}
		Query query = session.createQuery("FROM User WHERE username = :username OR emailAddress = :emailAddress");
		query.setParameter("username", userIdOrEmail);
		query.setParameter("emailAddress", userIdOrEmail);
			List<User> list = query.list();
			 User user;
			 if(list.size() != 0) {
				 user = list.get(0);
				 System.out.println("Email id user:"+user.getEmailAddress());
				 session.close();
				 return user;
			 }
		
		 session.close();
		return null;
	}

	@Override
	public AccountSummary getAccountSummaryByAccountNumber(int accountNumber) {
		if(!session.isOpen()) {
			session = HibernateUtil.getSessionFactory().openSession();
		}
		Query query = session.createQuery("From AccountSummary WHERE accountNumber = :accountNumber");
		query.setParameter("accountNumber", accountNumber);
			List<AccountSummary> list = query.list();
			AccountSummary summary;
			if(list.size() != 0) {
				summary = list.get(0);
				System.out.println("The balance is:"+summary.getBalance());
				session.close();
				return summary;
			}
		session.close();
		return null;
	}

	@Override
	public void createUser(User user) {
		if(!session.isOpen()) {
			session = HibernateUtil.getSessionFactory().openSession();
		}
		try {
			session.beginTransaction();
			try {
				Query query = session.createQuery("From User WHERE username = :username");
				query.setParameter("username", user.getUsername());
		        	if(query.list().size() == 0) {
		                session.save(user);
		            } else {
		            	throw new InvalidParameterException("Please choose a diffrent username");
		            }
		        session.getTransaction().commit();
			} catch(Exception ex) {
				session.getTransaction().rollback();
				throw new InvalidParameterException("Invalid");
			}
	    	
	    } finally {
	    	session.close();
	    }
	}
	
	@Override
	public void updateUser(User user) {
		if(!session.isOpen()) {
			session = HibernateUtil.getSessionFactory().openSession();
		}
		session.beginTransaction();
		session.update(user);
		session.getTransaction().commit();
		System.out.println("user updated");
		session.close();
	}
	
	public List<User> getUserTable()
	{
		if(!session.isOpen()) {
			session = HibernateUtil.getSessionFactory().openSession();
		}
		Query query = session.createQuery("FROM User");
			List<User> list = query.list();

			 if(list.size() != 0) {
				
				 return list;
			 }
		
		 System.out.println("No user exists");
		 session.close();
		return list;
	}
	
	public boolean deleteUser(User user)
	{
		if(!session.isOpen()) {
			session = HibernateUtil.getSessionFactory().openSession();
		}
		session.beginTransaction();
		session.delete(user);
		session.getTransaction().commit();
		System.out.println("user updated");
		session.close();
		return true;
	}
	
	@Override
	public String isUserExists(User user) {
		if(!session.isOpen()) {
			session = HibernateUtil.getSessionFactory().openSession();
		}
		Query query = session.createQuery("FROM User WHERE username = :username");
		Query query1 = session.createQuery("FROM User WHERE emailAddress = :emailAddress");
		query.setParameter("username", user.getUsername());
		query1.setParameter("emailAddress", user.getEmailAddress());		
			List<User> list = query.list();
			 if(list.size() != 0) {
				 session.close();
				 return "existingUserId";
			 }
			 List<User> list1 =query1.list();
			 if(list1.size() != 0) {
				 session.close();
				 return "existingEmailAddress";
			 }
		session.close();
		return "unique";
	}

	@Override
	public boolean isExistingUserEmailId(String emailId) {
		if(!session.isOpen()) {
			session = HibernateUtil.getSessionFactory().openSession();
		}
		Query query = session.createQuery("FROM User WHERE emailAddress = :emailAddress ");
		 query.setParameter("emailAddress", emailId);
		 List<User> list = query.list();
		 if(list.size() != 0) {
				 session.close();
				 return true;
		 }
		 session.close();
		 return false;
	}
	

}
