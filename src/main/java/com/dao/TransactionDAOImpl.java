package com.dao;

import java.security.InvalidParameterException;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.db.HibernateUtil;
import com.db.Transaction;

@Transactional
@Repository("transactionDAO")
public class TransactionDAOImpl implements TransactionDAO{

	Session session = HibernateUtil.getSessionFactory().openSession();
	@Override
	public void createTransaction(Transaction transaction) {
		if (!session.isOpen()) {
			session = HibernateUtil.getSessionFactory().openSession();
		}
		try {
			session.beginTransaction();
			try {
				Query query = session
						.createQuery("From Trnasaction WHERE transactionId = :transactionId");
				query.setParameter("transactionId",
						transaction.getTransactionId());
				if (query.list().size() == 0) {
					session.save(transaction);
				} else {
					throw new InvalidParameterException(
							"Transaction already exists");
				}
				session.getTransaction().commit();

			} catch (Exception ex) {
				ex.printStackTrace();
				session.getTransaction().rollback();
				throw new InvalidParameterException("Invalid transaction");
			}

		} finally {
			session.close();
		}

	}

}
