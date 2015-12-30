package com.dao;

import com.db.Dummytransaction;
import com.db.HibernateUtil;
import com.db.Merchantinfo;

import java.security.InvalidParameterException;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository("merchantDAO")
public class MerchantDAOImpl implements MerchantDAO{

    Session session = HibernateUtil.getSessionFactory().openSession();

	@Override
	public Merchantinfo getMerchant() {
		if (!session.isOpen()) {
			session = HibernateUtil.getSessionFactory().openSession();
		}
		Query query = session.createQuery("FROM Merchantinfo WHERE merchantId = :merchantId ");
		 query.setParameter("merchantId", "amz");
//		 session.beginTransaction();
		 List<Merchantinfo> list = query.list();
		 Merchantinfo merchantInfo;
		 if(list.size() != 0) {
			 merchantInfo = list.get(0);
			 System.out.println("Email id merchant:"+merchantInfo.getMerchantEmail());
			 session.close();
			 return merchantInfo;
		 }
		 session.close();
		return null;
	}

	@Override
	public void updateProfile(Merchantinfo merchant) {
		if(!session.isOpen()) {
			session = HibernateUtil.getSessionFactory().openSession();
		}
			session.beginTransaction();
				Merchantinfo merchantInfo = (Merchantinfo)session.get(Merchantinfo.class, merchant.getMerchantId());
				merchantInfo.setMerchantAddress(merchant.getMerchantAddress());
				merchantInfo.setMerchantEmail(merchant.getMerchantEmail());
				merchantInfo.setMerchantIncomeTaxNumber(merchant.getMerchantIncomeTaxNumber());
				merchantInfo.setMerchantName(merchant.getMerchantName());
				session.update(merchantInfo);
		        session.getTransaction().commit();
		        session.close();
	}

	@Override
	public boolean isExistingMerchantEmail(String emailId) {
		if(!session.isOpen()) {
			session = HibernateUtil.getSessionFactory().openSession();
		}
		Query query = session.createQuery("FROM Merchantinfo WHERE merchantEmail = :merchantEmail ");
		 query.setParameter("merchantEmail", emailId);
//		 session.beginTransaction();
		 List<Merchantinfo> list = query.list();
		 Merchantinfo merchant;
		 if(list.size() != 0) {
				 session.close();
				 return true;
		}
		 session.close();
		 return false;

	}

}
