package com.dao;

import com.db.Employee;
import com.db.HibernateUtil;
import com.db.Login;
import com.db.User;

import java.security.InvalidParameterException;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository("loginDAO")
public class LoginDAOImpl implements LoginDAO{

    Session session = HibernateUtil.getSessionFactory().openSession();

	@Override
	public String getRole(String username, String password) {
		if (!session.isOpen()) {
			session = HibernateUtil.getSessionFactory().openSession();
		}
		 Query query = session.createQuery("FROM Login WHERE loginId = :username AND password = :password");
		 query.setParameter("username", username);
		 query.setParameter("password", password);
		 List<Login> list = query.list();
		 if(list.size() != 0) {
			 Login login = list.get(0);
			 session.close();
			 return login.getRole();
		 } else {
			 session.close();
			 return "error";
		 }
	}

	@Override
	public void saveLoginInformation(Login login) {
		System.out.println("Login id and password in dao impl"+login.getLoginId()+login.getPassword());
		if(!session.isOpen()) {
			session = HibernateUtil.getSessionFactory().openSession();
		}
//		try {
			session.beginTransaction();
//			try {
				Query query = session.createQuery("From Login WHERE loginId = :loginId");
				query.setParameter("loginId", login.getLoginId());
		        	if(query.list().size() == 0) {
		                session.save(login);
		            } else {
		            	throw new InvalidParameterException("User already exists");
		            }
		        session.getTransaction().commit();
		        session.close();
	//		} catch(Exception ex) {
	//			ex.printStackTrace();
	//			session.getTransaction().rollback();
	//			throw new InvalidParameterException("Invalid");
	//		}
	    	
	//    } finally {
	 //   	session.close();
	  //  }

	}
	
	public List<Login> getLoginTable()
	{
		if(!session.isOpen()) {
			session = HibernateUtil.getSessionFactory().openSession();
		}
		Query query = session.createQuery("FROM Login");
		 List<Login> list = query.list();
		 return list;
	}
	
	public Login getUsername(String username)
	{
		if(!session.isOpen()) {
			session = HibernateUtil.getSessionFactory().openSession();
		}
		Query query = session.createQuery("FROM Login WHERE loginId = :username");
		query.setParameter("username", username);
		 List<Login> list = query.list();
		 if(list.size()==1)
		 {
		 return list.get(0);
		 }
		 else
			return new Login();
	}
	
	public void userregistration(Login login)
	{
		if(session.isOpen())
		{
			session.close();
			session = HibernateUtil.getSessionFactory().openSession();
		}
		if(!session.isOpen()) {
			session = HibernateUtil.getSessionFactory().openSession();
		}

		session.beginTransaction();
		session.update(login);
		session.getTransaction().commit();
		System.out.println("login updated");
		session.close();
	}
	
	@Override
	public Login getLoginInformation(String username) {
		if (!session.isOpen()) {
			session = HibernateUtil.getSessionFactory().openSession();
		}
		 Query query = session.createQuery("FROM Login WHERE loginId = :username");
		 System.out.println("Loogin for:"+username);
		 query.setParameter("username", username);
		 List<Login> list = query.list();
		 System.out.println("Siuze of list"+list.size());
		 if(list.size() != 0) {
			 Login login = list.get(0);
			 session.close();
			 return login;
		 } else {
			 session.close();
			 return null;
		 }
	}

	@Override
	public void updateLoginInformation(Login login) {
		if(!session.isOpen()) {
			session = HibernateUtil.getSessionFactory().openSession();
		}
		session.beginTransaction();
		session.update(login);
		session.getTransaction().commit();
		System.out.println("Login details updated");
		session.close();

	}

	@Override
	public boolean isExistingUserId(String userId) {
		if (!session.isOpen()) {
			session = HibernateUtil.getSessionFactory().openSession();
		}
		 Query query = session.createQuery("FROM Login WHERE loginId = :userId");
		 query.setParameter("userId", userId);
		 List<Login> list = query.list();
		 if(list.size() != 0) {
			 session.close();
			 return true;
		 } 
		 session.close();
		 return false;
	}

}
