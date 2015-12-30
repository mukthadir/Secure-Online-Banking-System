package com.dao;

import com.db.Employee;

import java.util.*;

public interface EmployeeDAO {

	public Employee getEmployeeByNewId(String employeeId);
	public Employee getEmployeeById(String employeeId);
	public String[] getAllEmployeeIds();
	public void createEmployee(Employee emp);
	public List<Employee> getEmployeeTable();
	public void updateEmployee(Employee emp);
	public void deleteEmployee(Employee emp);
	public boolean isExistingEmployeeEmail(String emailId);
}
