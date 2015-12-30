package com.dao;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.db.AccountSummary;
import com.db.Dummytransaction;
import com.db.Employee;
import com.db.HibernateUtil;

@Transactional
@Repository("dummyTransactionDAO")
public class DummyTransactionDAOImpl implements DummyTransactionDAO{

    Session session = HibernateUtil.getSessionFactory().openSession();

	@Override
	public void createTransaction(Dummytransaction dummyTransaction) {
		if(!session.isOpen()) {
			session = HibernateUtil.getSessionFactory().openSession();
		}
		try {
			session.beginTransaction();
		
				Query query = session.createQuery("From Dummytransaction");
		        session.save(dummyTransaction);
		        session.getTransaction().commit();
		       // session.close();
			} catch(Exception ex) {
				session.getTransaction().rollback();
				throw new InvalidParameterException("Invalid");
			}
			finally
			{
				session.close();
			}
		}
	@Override
	public List<Dummytransaction> getCriticalTransaction()
	{
		if (!session.isOpen()) {
			session = HibernateUtil.getSessionFactory().openSession();
		}
		try{
		Query query=session.createQuery("FROM Dummytransaction WHERE critical = :critical AND reviewedByEmployee = :reviewedByEmployee");
		query.setParameter("critical", true);
		query.setParameter("reviewedByEmployee", false);

		List<Dummytransaction> list=query.list();
		
		if(list!=null){
			System.out.println("Found them!");
			//session.close();
			return list;}
		else{
			//session.close();
			return list;}
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
	public List<Dummytransaction> pullMerchantPaymentRequests(
			int accountNumberOfSender) {
		if (!session.isOpen()) {
			session = HibernateUtil.getSessionFactory().openSession();
		}
		try{
			Query query = session
					.createQuery("From Dummytransaction WHERE accountSummaryByUserSending.accountNumber = :accountNumber AND isMerchantTransaction = :merchantFlag"
							+ " AND isApprovedByUser = :isApprovedByUser AND isRejectedByUser = :rejectFlag)");
			query.setParameter("accountNumber",accountNumberOfSender);
			query.setParameter("merchantFlag", true);
			query.setParameter("isApprovedByUser", false);
			query.setParameter("rejectFlag", false);
		List<Dummytransaction> list = query.list();
		//session.close();
		return list;
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
	public void updateTransaction(Dummytransaction dummyTransaction) {
		
		if(!session.isOpen()) {
			session = HibernateUtil.getSessionFactory().openSession();
		}
		try {
			session.beginTransaction();
			try {
				Dummytransaction dummyTransactionObj = (Dummytransaction)session.get(Dummytransaction.class, dummyTransaction.getTransactionId());
				dummyTransactionObj.setIsApprovedByUser(dummyTransaction.getIsApprovedByUser());
				dummyTransactionObj.setIsRejectedByUser(dummyTransaction.getIsRejectedByUser());
				// Changes made by kushagra 11-6
				session.update(dummyTransactionObj);
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
	public void criticalTransactionApproved(Dummytransaction dummyTransaction) {
		
		if(!session.isOpen()) {
			session = HibernateUtil.getSessionFactory().openSession();
		}
		try {
			session.beginTransaction();
			try {
				Dummytransaction dummyTransactionObj = (Dummytransaction)session.get(Dummytransaction.class, dummyTransaction.getTransactionId());
				dummyTransactionObj.setReviewedByEmployee(true);
				dummyTransactionObj.setIsRejectedByEmployee(false);
				session.update(dummyTransactionObj);
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
	public void criticalTransactionRejected(Dummytransaction dummyTransaction) {
		
		if(!session.isOpen()) {
			session = HibernateUtil.getSessionFactory().openSession();
		}
		try {
			session.beginTransaction();
			try {
				Dummytransaction dummyTransactionObj = (Dummytransaction)session.get(Dummytransaction.class, dummyTransaction.getTransactionId());
				dummyTransactionObj.setIsRejectedByEmployee(true);
				dummyTransactionObj.setReviewedByEmployee(true);
				session.update(dummyTransactionObj);
		        session.getTransaction().commit();

			} catch(Exception ex) {
				session.getTransaction().rollback();
				throw new InvalidParameterException("Invalid");
			}
	    	
	    } finally {
	    	session.close();
	    }
		
	}
	
	
	public boolean isEmployeeFree(Employee employee)
	{
		if (!session.isOpen()) {
			session = HibernateUtil.getSessionFactory().openSession();
		}
		try{
		Query query=session.createQuery("FROM Dummytransaction WHERE employee = :employee");
		query.setParameter("employee", employee);
		List<Dummytransaction> list=query.list();
		int count=0;
		for(int i=0;i<list.size();i++)
		{
			Dummytransaction d=list.get(i);
			if(d.getReviewedByEmployee()!=null)
			{
				if(d.getReviewedByEmployee())
				{
					count++;
				}
				System.out.println(list.get(i).getTransactionId());
			}
		}
		if(count==list.size() || list==null)
		{
			System.out.println("True");
			//session.close();
			return true;
		}
		else
		{
			System.out.println("False");
			//session.close();
			return false;
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
	
	public List<Dummytransaction> getTable()
	{
		if(!session.isOpen()) {
			session = HibernateUtil.getSessionFactory().openSession();
		}
		try {
			session.beginTransaction();
			try {
				Query query = session.createQuery("From Dummytransaction");
				List<Dummytransaction> result=query.list();
		        //session.close();
		        return result;
			} catch(Exception ex) {
				
				throw new InvalidParameterException("Invalid");
			}}catch(Exception ex1) {
				
				throw new InvalidParameterException("Invalid");
			}
			finally
			{
				session.close();
			}
	}
	
	public List<Dummytransaction> getEmployeeTransaction(Employee empid)
	{
		if (!session.isOpen()) {
			session = HibernateUtil.getSessionFactory().openSession();
		}
		try{
		Query query=session.createQuery("FROM Dummytransaction WHERE employee = :employee AND reviewedByEmployee = :reviewedByEmployee");
		query.setParameter("employee", empid);
		query.setParameter("reviewedByEmployee", false);
		
		
		@SuppressWarnings("unchecked")
		List<Dummytransaction> list=(List<Dummytransaction>)query.list();
		List<Dummytransaction> result=new ArrayList<Dummytransaction>();
		
		System.out.println(list.size());
		
		for(int i=0;i<list.size();i++)
		{
			if(list.get(i).getIsRejectedByUser()==null)
			{
				result.add(list.get(i));
			}
		}

		if(result!=null){
			//session.close();
			return result;
			}
		else{
			//session.close();
			return result;
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
	
	public List<Dummytransaction> getModifyTransaction(Employee empid)
	{
		if (!session.isOpen()) {
			session = HibernateUtil.getSessionFactory().openSession();
		}
		try{
		Query query=session.createQuery("FROM Dummytransaction WHERE employee = :employee AND reviewedByEmployee = :reviewedByEmployee AND isRejectedByUser = :rejectedbyuser");
		query.setParameter("employee", empid);
		query.setParameter("reviewedByEmployee", false);
		query.setParameter("rejectedbyuser",true);
		
		
		@SuppressWarnings("unchecked")
		List<Dummytransaction> list=(List<Dummytransaction>)query.list();
		List<Dummytransaction> result=new ArrayList<Dummytransaction>();
		
		System.out.println("Here"+list.size());
		
		for(int i=0;i<list.size();i++)
		{
			Dummytransaction temp=list.get(i);
			if(temp.getIsMerchantTransaction()==null)
			{
				result.add(temp);
			}
			System.out.println(result.size());
		}

		if(list!=null){
			//session.close();
			return result;
			}
		else{
			//session.close();
			return result;
		}}
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
	public List<Dummytransaction> getAllTransactionsForUser(int checkingsAccountNumber) {
		if(!session.isOpen()) {
			session = HibernateUtil.getSessionFactory().openSession();
		}
		Query query = session
				.createQuery("From Dummytransaction WHERE accountSummaryByUserSending.accountNumber = :accountNumber OR "
						+ "accountSummaryByUserReceiving.accountNumber = :accountNumber OR isRejectedByEmployee = :rejectedFlag");
		query.setParameter("accountNumber", checkingsAccountNumber);
		query.setParameter("rejectedFlag", false);
		if(query.list().size() > 0) {
			List<Dummytransaction> list = query.list();
			Collections.reverse(list);
			session.close();
			return list;
		}
		session.close();
		return null;
	}

}
