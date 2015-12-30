package com.dao;

import com.db.Employee;
import com.db.HibernateUtil;
import com.db.Merchantinfo;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository("employeeDAO")
public class EmployeeDAOImpl implements EmployeeDAO{
	 Session session = HibernateUtil.getSessionFactory().openSession();

		@Override
		public Employee getEmployeeByNewId(String employeename) {
			if(!session.isOpen()){
				 session = HibernateUtil.getSessionFactory().openSession();				
			}
			Query query = session.createQuery("FROM Employee WHERE employeeUsername = :employeename");
			query.setParameter("employeename", employeename);
			 List<Employee> list = query.list();
			 System.out.println(list.size());
				List<Employee> result=new ArrayList<Employee>();
				for(int i=0;i<list.size();i++)
				{
					Employee tempp=list.get(i);
					if(tempp.getEmployeeDeleteFlag()==null)
					{
						result.add(tempp);
					}
					else
					{
						
					}
				}
			 Employee employee;
			 if(result.size() != 0) {
				 employee = result.get(0);
				 System.out.println("name employee:"+employee.getEmployeeName());
				 session.close();
				 return employee;
			 }
			 System.out.println("Its null");
			 session.close();
			return null;
		}
		
		@Override
		public Employee getEmployeeById(String employeeId) {
			if(!session.isOpen()){
				 session = HibernateUtil.getSessionFactory().openSession();				
			}
			// Changes made by kushagra 11/6
			Query query = session.createQuery("FROM Employee WHERE employeeUsername = :employeeUsername ");
			query.setParameter("employeeUsername", employeeId);
			// Changes made by kushagra 11/6

			 List<Employee> list = query.list();
			 Employee employee;
			 if(list.size() != 0) {
				 employee = list.get(0);
				 System.out.println("name employee:"+employee.getEmployeeName());
				 session.close();
				 return employee;
			 }
			 System.out.println("Its null");
			 session.close();
			return null;
		}

		@Override
		public String[] getAllEmployeeIds() {
			if(!session.isOpen()){
				 session = HibernateUtil.getSessionFactory().openSession();				
			}
			try{
			Query query = session.createQuery("FROM Employee");
			List<Employee> list = query.list();
			
			List<Employee> result=new ArrayList<Employee>();
			for(int i=0;i<list.size();i++)
			{
				Employee tempp=list.get(i);
				if(tempp.getEmployeeDeleteFlag()==null)
				{
					result.add(tempp);
				}
				else
				{
					
				}
			}
			System.out.println("Size"+result.size());
			String[] listOfEmployeeIds = new String[result.size()];
			int i=0;
			if(result.size() != 0) {
				for(Employee employee: result) {
					listOfEmployeeIds[i]=employee.getEmployeeUsername();
					i++;
				}
			}
			//session.close();
			return listOfEmployeeIds;
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
		public void createEmployee(Employee emp) {
			if(!session.isOpen()) {
				session = HibernateUtil.getSessionFactory().openSession();
			}
			try {
				session.beginTransaction();
				try {
					Query query = session.createQuery("From Employee WHERE employeeUsername = :username");
					query.setParameter("username", emp.getEmployeeUsername());
			        	if(query.list().size() == 0) {
			                session.save(emp);
			            } else {
			            	 throw new InvalidParameterException("Please choose a diffrent username");
			            }
			        session.getTransaction().commit();
					} catch(Exception ex) {
					session.getTransaction().rollback();
					System.out.println("Error");
					throw new InvalidParameterException("Invalid");
				}
		    	
		    } finally {
		    	session.close();
		    }
		}
		
		@Override
		public List<Employee> getEmployeeTable()
		{
			if(!session.isOpen()){
				 session = HibernateUtil.getSessionFactory().openSession();				
			}
			try{
			Query query = session.createQuery("FROM Employee");
			List<Employee> list = query.list();
			List<Employee> result=new ArrayList<Employee>();
			for(int i=0;i<list.size();i++)
			{
				Employee tempp=list.get(i);
				if(tempp.getEmployeeDeleteFlag()==null)
				{
					result.add(tempp);
				}
				else
				{
					
				}
			}
			//session.close();
			return result;
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
		public void updateEmployee(Employee emp)
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
			session.update(emp);
			session.getTransaction().commit();
			System.out.println("emp updated");
			session.close();
		}
		
		@Override
		public void deleteEmployee(Employee emp)
		{
			if(!session.isOpen()){
				 session = HibernateUtil.getSessionFactory().openSession();				
			}
//			Query query = session.createQuery("FROM Employee WHERE employeeEmailId = :employeeid");
//			System.out.println(emp.getEmployeeId());
//			query.setParameter("employeeid", emp.getEmployeeId());
//			List<Employee> list = query.list();
//			for(int i=0;i<list.size();i++)
//			{
//				Employee tempp=list.get(i);
//				tempp.setEmployeeDeleteFlag(true);
//				session.update(tempp);
//				session.getTransaction().commit();
//			}
//			session.close();
			
			
			session.beginTransaction();
			Employee employee = (Employee)session.get(Employee.class, emp.getEmployeeId());
			employee.setEmployeeName(emp.getEmployeeName());
			employee.setEmployeeUsername(emp.getEmployeeUsername());
			employee.setEmployeeSalary(emp.getEmployeeSalary());
			employee.setEmployeeDesignation(emp.getEmployeeDesignation());
			employee.setEmployeeDeleteFlag(true);
			session.update(employee);
	        session.getTransaction().commit();
	        session.close();
		}
		
		@Override
		public boolean isExistingEmployeeEmail(String emailId) {
			if(!session.isOpen()) {
				session = HibernateUtil.getSessionFactory().openSession();
			}
			Query query = session.createQuery("FROM Employee WHERE employeeEmailId = :employeeEmailId ");
			 query.setParameter("employeeEmailId", emailId);
			 List<Employee> list = query.list();
			 if(list.size() != 0) {
					 session.close();
					 return true;
			 }
			 session.close();
			 return false;

		}
}
