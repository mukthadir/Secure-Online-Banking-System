package com.dao;

import java.security.InvalidParameterException;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.db.AccountSummary;
import com.db.HibernateUtil;
import com.db.User;

@Transactional
@Repository("accountSummaryDAO")
public class AccountSummaryDAOImpl implements AccountSummaryDAO {

	Session session = HibernateUtil.getSessionFactory().openSession();

	@Override
	public void createAccount(AccountSummary accountSummary) {
		if (!session.isOpen()) {
			session = HibernateUtil.getSessionFactory().openSession();
		}
		try {
			session.beginTransaction();
			try {
				Query query = session
						.createQuery("From AccountSummary WHERE accountNumber = :accountNumber");
				query.setParameter("accountNumber",
						accountSummary.getAccountNumber());
				if (query.list().size() == 0) {
					session.save(accountSummary);
				} else {
					throw new InvalidParameterException(
							"Account already exists");
				}
				session.getTransaction().commit();

			} catch (Exception ex) {
				ex.printStackTrace();
				session.getTransaction().rollback();
				throw new InvalidParameterException("Invalid");
			}

		} finally {
			session.close();
		}

	}

	@Override
	public void updateAccountSummary(AccountSummary accountSummary) {
		if (!session.isOpen()) {
			session = HibernateUtil.getSessionFactory().openSession();
		}
		try {
			session.beginTransaction();
			try {
				session.update(accountSummary);
				session.getTransaction().commit();

			} catch (Exception ex) {
				ex.printStackTrace();
				session.getTransaction().rollback();
				throw new InvalidParameterException("Invalid");
			}

		} finally {
			session.close();
		}
		
	}
	@Override
	public boolean isAccountNumberExists(int accountNumber) {
		if (!session.isOpen()) {
			session = HibernateUtil.getSessionFactory().openSession();
		}
		try{
			Query query = session
				.createQuery("From AccountSummary WHERE accountNumber = :accountNumber");
			query.setParameter("accountNumber", accountNumber);
			if (query.list().size() == 0) {
				//session.close();
				return false;
			} else {
				//session.close();
				return true;
			}
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
	public AccountSummary getSummary(int checkingsAccountNumber) {
		if (!session.isOpen()) {
			session = HibernateUtil.getSessionFactory().openSession();
		}
		try{
		Query query = session
				.createQuery("From AccountSummary WHERE accountNumber = :accountNumber");
		query.setParameter("accountNumber",checkingsAccountNumber);
		List<AccountSummary> list = query.list();
		//session.close();
		return list.get(0);
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
