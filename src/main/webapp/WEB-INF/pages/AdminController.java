package com.controller;

import java.security.InvalidParameterException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dao.AccountSummaryDAO;
import com.dao.AdminDAO;
import com.dao.DummyTransactionDAO;
import com.dao.EmployeeDAO;
import com.dao.LoginDAO;
import com.dao.MerchantDAO;
import com.dao.UserCriticalDAO;
import com.dao.UserDAO;
import com.db.AccountSummary;
import com.db.Admininfo;
import com.db.Dummytransaction;
import com.db.Employee;
import com.db.Login;
import com.db.User;
import com.db.Usercritical;
import com.model.Person;
import com.utils.OTPGenerator;
import com.utils.SessionUtils;
import com.utils.EmailSender;

@Controller
public class AdminController {
	
	@Autowired
	MerchantDAO merchantDAO;
	@Autowired
	LoginDAO loginDAO;
	@Autowired
	EmployeeDAO employeeDAO;
	@Autowired
	AdminDAO adminDAO;
	@Autowired
	UserDAO userDAO;
	@Autowired
	AccountSummaryDAO accountSummaryDAO;
	@Autowired
	DummyTransactionDAO dummyTransactionDAO;
	@Autowired
	UserCriticalDAO userCriticalDAO;
	
	public static ArrayList<Employee> emptable=new ArrayList<Employee>();
	
	@RequestMapping(value="/Critical",params={"authorize"})
	public String approveCriticalTransaction(HttpServletRequest request,Model model)
	{
		if (!SessionUtils.isEmployeeLogin(request)
				&& !SessionUtils.isMerchantLogin(request)
				&& !SessionUtils.isUserLogin(request)) 
		{
			
			Admininfo admin=(Admininfo) request.getSession().getAttribute("admin");
			if(admin==null)
				System.out.println("admin is null");
			model.addAttribute("email",admin.getAdminEmail());
			if(admin!=null)
			{
				String[] values=request.getParameterValues("values");
				if(values!=null)
				{
					System.out.println(values);
					List<Dummytransaction> transactionList=dummyTransactionDAO.getCriticalTransaction();
		
					if(values.length==transactionList.size())
					{
						for(int i=0;i<values.length;i++)
						{
			
							if(values[i].equalsIgnoreCase("Approve"))
							{
								try{
									Dummytransaction temp=transactionList.get(i);
									System.out.println(temp.getTransactionId());
									dummyTransactionDAO.criticalTransactionApproved(temp);
									}
								catch(Exception e)
								{
									
									return "requestfailureadmin";
								}
				
							}
							else if(values[i].equalsIgnoreCase("Reject"))
							{
								
								try{
								System.out.println("In reject");
								Dummytransaction temp=transactionList.get(i);
								AccountSummary receiver = accountSummaryDAO.getSummary(temp.getAccountSummaryByUserSending().getAccountNumber());
								AccountSummary sender=accountSummaryDAO.getSummary(temp.getAccountSummaryByUserReceiving().getAccountNumber());
				
								Dummytransaction update=new Dummytransaction();
				
								update.setAccountSummaryByUserReceiving(receiver);
								update.setAccountSummaryByUserSending(sender);
				
								update.setEmployee(null);
								update.setReviewedByEmployee(true);
								update.setAmount(temp.getAmount());
				
								sender.setBalance(sender.getBalance()-temp.getAmount());
								receiver.setBalance(receiver.getBalance()+temp.getAmount());
				
								dummyTransactionDAO.createTransaction(update);
				
								accountSummaryDAO.updateAccountSummary(sender);
								accountSummaryDAO.updateAccountSummary(receiver);
				
								System.out.println(temp.getTransactionId());
								dummyTransactionDAO.criticalTransactionRejected(temp);	
								}
								catch(Exception e)
								{
									
									return "requestfailureadmin";
								}
							}
						}
					}

					Admininfo admin1 = adminDAO.getAdmin();
					transactionList=dummyTransactionDAO.getCriticalTransaction();
					model.addAttribute("criticallist",transactionList);
					return "admin";
				}
				else
				{
					List<Dummytransaction> transactionList=dummyTransactionDAO.getCriticalTransaction();
					Admininfo admin2 = adminDAO.getAdmin();
					model.addAttribute("criticallist",transactionList);
					return "admin";
				}
			}
			else
				{
					return "redirect:/";
				}
		}
		else
		{
			return "noAccess";
		}

	}
	
	@RequestMapping(value="/addemp")
	public String addEmployee(HttpServletRequest request,Model model)
	{
		if (!SessionUtils.isEmployeeLogin(request)
				&& !SessionUtils.isMerchantLogin(request)
				&& !SessionUtils.isUserLogin(request)) 
		{
			Admininfo admin=(Admininfo) request.getSession().getAttribute("admin");
			if(admin==null)
				System.out.println("admin is null");
			model.addAttribute("email",admin.getAdminEmail());
			if(admin!=null)
			{
				Employee emp=new Employee();
				model.addAttribute("employee",emp);
				return "addemp";
			}
			else
			{
				return "redirect:/";
			}
		}
		else
		{
			return "noAccess";
		}

	}
	
	@RequestMapping(value="/newemp",params={"Save"})
	public String approveCriticalTransaction(@ModelAttribute("employee") Employee employee,HttpServletRequest request,Model model)
	{
		if (!SessionUtils.isEmployeeLogin(request)
				&& !SessionUtils.isMerchantLogin(request)
				&& !SessionUtils.isUserLogin(request)) 
		{
			Admininfo admin=(Admininfo) request.getSession().getAttribute("admin");
			model.addAttribute("email",admin.getAdminEmail());
			if(admin!=null)
			{
		
				if(employee.getEmployeeName()!=null && employee.getEmployeeUsername()!=null && employee.getEmployeeDesignation()!=null && employee.getEmployeeSalary()!=null && employee.getEmployeeEmailId()!=null)
				{
					String[] numberofemployees=employeeDAO.getAllEmployeeIds();
					int empcount=numberofemployees.length+1;
					employee.setEmployeeId("employee"+Integer.toString(empcount));
			
					try{
						employee.setEmployeeName(employee.getEmployeeName().toString());
						employee.setEmployeeUsername(employee.getEmployeeUsername().toString());
						employee.setEmployeeDesignation(employee.getEmployeeDesignation().toString());
						employee.setEmployeeSalary(employee.getEmployeeSalary().toString());
						employee.setEmployeeEmailId(employee.getEmployeeEmailId().toString());
						employee.setSecurityA1("hi");
						employee.setSecurityA2("hi");
						employeeDAO.createEmployee(employee);
					}
					catch(Exception e){
						return "requestfailureadmin";
					}
		
//					String[] numberofemployees=employeeDAO.getAllEmployeeIds();
//					int empcount=numberofemployees.length;
//					employee.setEmployeeId("employee"+Integer.toString(empcount));
//					try{
//						employeeDAO.createEmployee(employee);
//					}
//					catch(Exception e){
//						System.out.println(e);
//					}
					
					String emailReceiver = employee.getEmployeeEmailId();
					String otp = OTPGenerator.generate_Random_Password();
					Login login=new Login();
					login.setOtp(otp);
					
					String link=".       Please go to this link->";
					EmailSender.sendMail("admgroup4CSE543@gmail.com", "bruteforce", otp+link, emailReceiver);
			
		
					//Entry in Login table
					login.setRole("employee");
					login.setLoginId(employee.getEmployeeUsername());
					login.setPassword("TonyIronStark");
					loginDAO.saveLoginInformation(login);
					model.addAttribute("employee",new Employee());
					model.addAttribute("success", "Details successfully saved");
					return "addemp";
				}
				else
				{
					model.addAttribute("employee",new Employee());
					return "addemp";
				}
			}
			else
			{
				return "redirect:/";
			}
		}
		else
		{
			return "noAccess";
		}
		

	}
	
	@RequestMapping(value="/modifyemp")
	public String modifyEmployee(HttpServletRequest request,Model model)
	{
		if (!SessionUtils.isEmployeeLogin(request)
				&& !SessionUtils.isMerchantLogin(request)
				&& !SessionUtils.isUserLogin(request)) 
		{
			Admininfo admin=(Admininfo) request.getSession().getAttribute("admin");
			model.addAttribute("email",admin.getAdminEmail());
			if(admin!=null)
			{
				model.addAttribute("emptable",employeeDAO.getEmployeeTable());
				model.addAttribute("Employee",new Employee());
				return "Modifyemp";
			}
			else
			{
				return "redirect:/";
			}
		}
		else
		{
			return "noAccess";
		}
	}
	
	@RequestMapping(value="/modifyemp/submit")
	public String modifyEmployeeSubmit(@ModelAttribute("Employee") Employee emp,HttpServletRequest request,Model model)
	{
		if (!SessionUtils.isEmployeeLogin(request)
				&& !SessionUtils.isMerchantLogin(request)
				&& !SessionUtils.isUserLogin(request)) 
		{
			Admininfo admin=(Admininfo) request.getSession().getAttribute("admin");
			model.addAttribute("email",admin.getAdminEmail());
			if(admin!=null)
			{
				String[] firstName=emp.getEmployeeName().split(",");
				String[] username=emp.getEmployeeUsername().split(",");
				String[] level=emp.getEmployeeDesignation().split(",");
				String[] salary=emp.getEmployeeSalary().split(",");
				String[] email=emp.getEmployeeEmailId().split(",");
				String[] options=request.getParameterValues("values");
				if(options!=null)
				{
					List<Employee> list=employeeDAO.getEmployeeTable();
					if(options.length==list.size())
					{
						for(int i=0;i<options.length;i++)
						{
							System.out.println(options[i]);
							if(options[i].equals("Yes"))
							{
								try{
								Employee temp=list.get(i);
								if(!(firstName[i].equals(temp.getEmployeeName())))
								{
									temp.setEmployeeName(firstName[i]);
								}	
								if(!(username[i].equals(temp.getEmployeeUsername())))
								{
									temp.setEmployeeUsername(username[i]);
								}	
								if(!(level[i].equals(temp.getEmployeeDesignation())))
								{
									temp.setEmployeeDesignation(level[i]);
								}
								if(!(salary[i].equals(temp.getEmployeeSalary())))
								{
									temp.setEmployeeSalary(salary[i]);
								}
								if(!(email[i].equals(temp.getEmployeeEmailId())))
								{
									temp.setEmployeeEmailId(email[i]);
								}	
								
									employeeDAO.updateEmployee(temp);
								}
								catch(Exception e)
								{
									return "requestfailureadmin";
								}
							}	
						}
					}
					else
					{
						model.addAttribute("emptable",employeeDAO.getEmployeeTable());
						model.addAttribute("Employee",new Employee());
						return "Modifyemp";
					}
				model.addAttribute("emptable",employeeDAO.getEmployeeTable());
				model.addAttribute("Employee",new Employee());
				return "Modifyemp";
				}
				else
				{
					model.addAttribute("emptable",employeeDAO.getEmployeeTable());
					model.addAttribute("Employee",new Employee());
					return "Modifyemp";
				}
			}
			else
			{
				return "redirect:/";
			}
		}
		else
		{
			return "noAccess";
		}
	}
	
	
	@RequestMapping(value="/delemp")
	public String deleteEmployee(HttpServletRequest request,Model model)
	{
		if (!SessionUtils.isEmployeeLogin(request)
				&& !SessionUtils.isMerchantLogin(request)
				&& !SessionUtils.isUserLogin(request)) 
		{
			Admininfo admin=(Admininfo) request.getSession().getAttribute("admin");
			model.addAttribute("email",admin.getAdminEmail());
			if(admin!=null)
			{
				List<Employee> result=new ArrayList<Employee>();
				try
				{
				List<Employee> list=employeeDAO.getEmployeeTable();
				for(int i=0;i<list.size();i++)
				{	
					Employee emp=list.get(i);
					String temp=emp.getEmployeeId();
					System.out.println(temp);
					if(dummyTransactionDAO.isEmployeeFree(emp))
					{
						result.add(emp);
					}
					else
					{
						continue;
					}
			
				}
				}
				catch(Exception e)
				{
					return "requestfailureadmin";
				}
				model.addAttribute("emptable",result);
				model.addAttribute("Employee",new Employee());
				return "deleteemployee";
			}
			else
			{
				return "redirect:/";
			}
		}
		else
		{
			return "noAccess";
		}
	}
	
	@RequestMapping(value="/delete/submit")
	public String deleteEmployeeSubmit(@ModelAttribute("Employee") Employee emp,HttpServletRequest request,Model model)
	{
		if (!SessionUtils.isEmployeeLogin(request)
				&& !SessionUtils.isMerchantLogin(request)
				&& !SessionUtils.isUserLogin(request)) 
		{
			Admininfo admin=(Admininfo) request.getSession().getAttribute("admin");
			model.addAttribute("email",admin.getAdminEmail());
			if(admin!=null)
			{
				String[] options=request.getParameterValues("values");
				List<Employee> result=new ArrayList<Employee>();
				
				try{
				List<Employee> list=employeeDAO.getEmployeeTable();
				int count=0;
				for(int i=0;i<list.size();i++)
				{
					Employee emp1=list.get(i);
					String temp=emp1.getEmployeeId();
					System.out.println(temp);
					if(dummyTransactionDAO.isEmployeeFree(emp1))
					{
				
						String op=options[count];
						System.out.println(op);
						if(op.equalsIgnoreCase("Delete"))
						{
							System.out.println("In here");
							employeeDAO.deleteEmployee(emp1);
						}
						count++;
				
					}
					else
					{
						continue;
					}	
				}
				}
				catch(Exception e)
				{
					return "requestfailureadmin";
				}
				
				try{
				List<Employee> list=employeeDAO.getEmployeeTable();
				for(int i=0;i<list.size();i++)
				{
					Employee emp1=list.get(i);
					String temp=emp1.getEmployeeId();
					System.out.println(temp);
					if(dummyTransactionDAO.isEmployeeFree(emp1))
					{
						result.add(emp1);
					}
					else
					{
						continue;
					}
			
				}
				}
				catch(Exception e)
				{
					return "requestfailureadmin";
				}
				model.addAttribute("emptable",result);
				model.addAttribute("Employee",new Employee());
				return "deleteemployee";
			}
			else
			{
				return "redirect:/";
			}
		}
		else
		{
			return "noAccess";
		}
	}
	
	@RequestMapping(value="/adduser")
	public String addUser(HttpServletRequest request,Model model)
	{
		if (!SessionUtils.isEmployeeLogin(request)
				&& !SessionUtils.isMerchantLogin(request)
				&& !SessionUtils.isUserLogin(request)) 
		{
			Admininfo admin=(Admininfo) request.getSession().getAttribute("admin");
			model.addAttribute("email",admin.getAdminEmail());
			if(admin!=null)
			{
				model.addAttribute("person",new User());
				return "adduser";
			}
			else
			{
				return "redirect:/";
			}
		}
		else
		{
			return "noAccess";
		}
	}
	
	@SuppressWarnings("deprecation")
	@RequestMapping(value="/add/newuser")
	public String saveNewUser(@ModelAttribute("person") User user,HttpServletRequest request,Model model)
	{
		if (!SessionUtils.isEmployeeLogin(request)
				&& !SessionUtils.isMerchantLogin(request)
				&& !SessionUtils.isUserLogin(request)) 
		{
			Admininfo admin=(Admininfo) request.getSession().getAttribute("admin");
			model.addAttribute("email",admin.getAdminEmail());
			if(admin!=null)
			{
				Random random=new Random();
				int min = 10000;
				int max = 1000000;
				int inclusive = max - min + 1;
				int exclusive = max - min;
				if(user != null) 
				{
					int checkingsAccountNumber = (Math.abs(random.nextInt()) % inclusive)
					+ min;
					while (accountSummaryDAO
					.isAccountNumberExists(checkingsAccountNumber)) {
						checkingsAccountNumber = (Math.abs(random.nextInt()) % inclusive)
						+ min;
					}
					user.setCheckingsAccountNumber(checkingsAccountNumber);

					// Entry in User Table
					try {
				
						//Must generate an OTP and send it to the user's email address. This OTP is stored in password field.
				
						//For now filling up the temporary details.
						user.setLastName("xxx");
						user.setSsn("1234567879");
						user.setGender("M");
						user.setSecurityQ1("What is your grandfather's occupation?");
						user.setSecurityA1("xxx");
						user.setSecurityQ2("What is you mother's maiden name?");
						user.setSecurityA2("xxx");
						user.setDob("2014-12-12");
						user.setPublicKey("123");
				
						userDAO.createUser(user);
					} 
					catch (InvalidParameterException invException) {
						System.out.println("Transaction failure");
						model.addAttribute("success",
						"Failed! Please enter a different username");
						return "adduser";
					} 
					catch (Exception ex) {
						ex.printStackTrace();
						System.out.println("Session closed");
						request.getSession().setAttribute("success",
						"Failed! Please enter a different username");
						return "adduser";
					}
					
					String emailReceiver = user.getEmailAddress();
					String otp = OTPGenerator.generate_Random_Password();
					Login login=new Login();
					login.setOtp(otp);
					String link="              Please follow this link";
					EmailSender.sendMail("admgroup4CSE543@gmail.com", "bruteforce", otp+link, emailReceiver);
			
					// Entry in Login table, Setting userName from User
			
					login.setRole("user");
					login.setLoginId(user.getUsername());
			
			
					//Temporary password
					login.setPassword("IamBatman");
			
					System.out.println("Login id:" + login.getLoginId());
					System.out.println("Login password:" + login.getPassword());
					loginDAO.saveLoginInformation(login);
					model.addAttribute("success", "Details successfully saved");

					// Entry in AccountSummary table
					AccountSummary accountSummary = new AccountSummary();
					accountSummary.setAccountNumber(user.getCheckingsAccountNumber());
					accountSummary.setBalance(120);
					accountSummaryDAO.createAccount(accountSummary);

					return "adduser";
			
				} 
				else 
				{
					return "adduser";
				}
			}
			else
			{
				return "redirect:/";
			}
		}
		else
		{
			return "noAccess";
		}

	}
	
	@RequestMapping(value="/modifyuser")
	public String modifyUser(HttpServletRequest request,Model model)
	{
		if (!SessionUtils.isEmployeeLogin(request)
				&& !SessionUtils.isMerchantLogin(request)
				&& !SessionUtils.isUserLogin(request)) 
		{
			Admininfo admin=(Admininfo) request.getSession().getAttribute("admin");
			model.addAttribute("email",admin.getAdminEmail());
			if(admin!=null)
			{
				//Go to UserCritical DAO and get all the usernames in which only SSN field is set and email id is set as null.
				//Send this list to the view

				List<Usercritical> critical=userCriticalDAO.getCriticalRequests();
				model.addAttribute("critical",critical);
				return "modifyuser";
			}
			else
			{
				return "redirect:/";
			}
		}
		else
		{
			return "noAccess";
		}
	}
	
	@RequestMapping(value="/modifyuser/submit")
	public String submitModifyUser(HttpServletRequest request,Model model)
	{
		if (!SessionUtils.isEmployeeLogin(request)
				&& !SessionUtils.isMerchantLogin(request)
				&& !SessionUtils.isUserLogin(request)) 
		{
			Admininfo admin=(Admininfo) request.getSession().getAttribute("admin");
			model.addAttribute("email",admin.getAdminEmail());
			if(admin!=null)
			{
				List<Usercritical> critical=userCriticalDAO.getCriticalRequests();
				for(int i=0;i<critical.size();i++)
				{
					//First modify the table to set admin approved as true
					critical.get(i).setIsAdminApproved(true);
					userCriticalDAO.saveOrUpdate(critical.get(i));
			
					//Now modify the values in user table
					String username=critical.get(i).getUsername();
			
					//Use this username for user table
					User user=userDAO.getUserById(username);
					user.setSsn(critical.get(i).getSsn());
					userDAO.updateUser(user);
				}
		
				List<Usercritical> critical1=userCriticalDAO.getCriticalRequests();
				model.addAttribute("critical",critical1);
				return "modifyuser";
			}
			else
			{
				return "redirect:/";
			}
		}
		else
		{
			return "noAccess";
		}
	}
	
	@RequestMapping(value="/deluser")
	public String deleteUser(HttpServletRequest request,Model model)
	{
		if (!SessionUtils.isEmployeeLogin(request)
				&& !SessionUtils.isMerchantLogin(request)
				&& !SessionUtils.isUserLogin(request)) 
		{
			Admininfo admin=(Admininfo) request.getSession().getAttribute("admin");
			model.addAttribute("email",admin.getAdminEmail());
			if(admin!=null)
			{
				model.addAttribute("acctable",userDAO.getUserTable());
				model.addAttribute("account",new User());
				return "deleteuser";
			}
			else
			{
				return "redirect:/";
			}
		}
		else
		{
			return "noAccess";
		}
	}
	
	@RequestMapping(value="/delete/usr/submit")
	public String submitDeleteUser(@ModelAttribute("account") User use,HttpServletRequest request,Model model)
	{
		if (!SessionUtils.isEmployeeLogin(request)
				&& !SessionUtils.isMerchantLogin(request)
				&& !SessionUtils.isUserLogin(request)) 
		{
			Admininfo admin=(Admininfo) request.getSession().getAttribute("admin");
			model.addAttribute("email",admin.getAdminEmail());
			if(admin!=null)
			{
				String[] options=request.getParameterValues("values");
				if(options!=null)
				{
					List<User> list=userDAO.getUserTable();
					if(options.length==list.size())
					{
						for(int i=0;i<options.length;i++)
						{
							if(options[i].equals("Delete"))
							{
								try{
								User temp=list.get(i);
								int accnumber=temp.getCheckingsAccountNumber();
								AccountSummary acc=accountSummaryDAO.getSummary(accnumber);
								acc.setDeleteFlag(true);
								accountSummaryDAO.updateAccountSummary(acc);
								userDAO.deleteUser(temp);}
								catch(Exception e)
								{
									return "requestfailureadmin";
								}
							}
						}
					}
					model.addAttribute("acctable",userDAO.getUserTable());
					model.addAttribute("account",new User());
					return "deleteuser";
				}
				else
				{
					model.addAttribute("acctable",userDAO.getUserTable());
					model.addAttribute("account",new User());
					return "deleteuser";
				}
			}
			else
			{
				return "redirect:/";
			}
		}
		else
		{
			return "noAccess";
		}
	}
	
	
	@RequestMapping(value="/syslog")
	public String accessSystemLog(HttpServletRequest request,Model model)
	{
		if (!SessionUtils.isEmployeeLogin(request)
			&& !SessionUtils.isMerchantLogin(request)
			&& !SessionUtils.isUserLogin(request)) 
		{
			Admininfo admin=(Admininfo) request.getSession().getAttribute("admin");
			model.addAttribute("email",admin.getAdminEmail());
			if(admin!=null)
			{
				model.addAttribute("criticallist",dummyTransactionDAO.getTable());
				model.addAttribute("emptable",employeeDAO.getEmployeeTable());
				model.addAttribute("acctable",userDAO.getUserTable());
				model.addAttribute("login",loginDAO.getLoginTable());
				model.addAttribute("updations",userCriticalDAO.getCriticalTable());
				return "systemlog";
			}
			else
			{
				return "redirect:/";
			}
		}
		else
		{
			return "noAccess";
		}
	}
	
	
	@RequestMapping(value="/pii")
	public String PII(HttpServletRequest request,Model model)
	{
		if (!SessionUtils.isEmployeeLogin(request)
			&& !SessionUtils.isMerchantLogin(request)
			&& !SessionUtils.isUserLogin(request)) 
		{
			Admininfo admin=(Admininfo) request.getSession().getAttribute("admin");
			model.addAttribute("email",admin.getAdminEmail());
			if(admin!=null)
			{
				return "pii";
			}
			else
			{
				return "redirect:/";
			}
		}
		else
		{
			return "noAccess";
		}
	}
	
	@RequestMapping(value="pii/redirect")
	public String PIIsubmit(HttpServletRequest request,Model model)
	{
		if (!SessionUtils.isEmployeeLogin(request)
				&& !SessionUtils.isMerchantLogin(request)
				&& !SessionUtils.isUserLogin(request)) 
		{
			Admininfo admin=(Admininfo) request.getSession().getAttribute("admin");
			model.addAttribute("email",admin.getAdminEmail());
			if(admin!=null)
			{
				String piiapproved="yes";
				model.addAttribute(piiapproved, "yes");
				List<Usercritical> critical=userCriticalDAO.getCriticalRequests();
				model.addAttribute("critical",critical);
				return "piiapproved-redirect-modify";
			}
			else
			{
				return "redirect:/";
			}
		}
		else
		{
			return "noAccess";
		}
	}
	
	@RequestMapping(value="/admin/home")
	public String redirectToHomePage(HttpServletRequest request,Model model)
	{
		if (!SessionUtils.isEmployeeLogin(request)
				&& !SessionUtils.isMerchantLogin(request)
				&& !SessionUtils.isUserLogin(request)) 
		{
			Admininfo admin1=(Admininfo) request.getSession().getAttribute("admin");
			model.addAttribute("email",admin1.getAdminEmail());
			if(admin1!=null)
			{
				List<Dummytransaction> transactionList=dummyTransactionDAO.getCriticalTransaction();
				Admininfo admin = adminDAO.getAdmin();
		
				for(int i=0;i<transactionList.size();i++)
				{
					System.out.println(transactionList.get(i).getTransactionId());
				}
				model.addAttribute("criticallist",transactionList);
				request.getSession().setAttribute("admin",admin);
				return "admin";
			}
			else
			{
				return "redirect:/";
			}
		}
		else
		{
			return "noAccess";
		}
	}
	
	
	@RequestMapping(value="/empregistration")
	public String employeeregistration(HttpServletRequest request,Model model)
	{
		String username=request.getParameter("username");
		String email=request.getParameter("email");
		String OTP=request.getParameter("OTP");
		String Password=request.getParameter("password");
		String sec1=request.getParameter("securityA1");
		String sec2=request.getParameter("securityA2");
		
		Employee emp=employeeDAO.getEmployeeByNewId(username);
		Login log=loginDAO.getUsername(username);
		if(emp!=null && emp.getEmployeeUsername().equalsIgnoreCase(username) && emp.getEmployeeEmailId().equalsIgnoreCase(email) && log.getOtp().equals(OTP))
		{
			emp.setSecurityA1(sec1);
			emp.setSecurityA2(sec2);
			employeeDAO.updateEmployee(emp);
			log.setPassword(Password);
			log.setOtp(null);
			loginDAO.userregistration(log);
		}
		return "redirect:/";
	}
	
	@RequestMapping(value="/empreg")
	public String employeeregistrationHandler(HttpServletRequest request,Model model)
	{
		
		return "employeereg";
	}
	
	
	@RequestMapping(value="/userreg")
	public String userregistrationHandler()
	{
		return "userreg";
	}
	
	@RequestMapping(value="/userregistration")
	public String userregistration(HttpServletRequest request,Model model)
	{
		String firstname=request.getParameter("first_name");
		String lastname=request.getParameter("last_name");
		String username=request.getParameter("username");
		String email=request.getParameter("email");
		String ssn=request.getParameter("ssn");
		String dob=request.getParameter("dob");
		String OTP=request.getParameter("OTP");
		String gender=request.getParameter("gender");
		String Password=request.getParameter("password");
		String sec1=request.getParameter("securityA1");
		String sec2=request.getParameter("securityA2");
		User user=userDAO.getUserById(username);
		Login log=loginDAO.getUsername(username);
		if(user!=null && user.getUsername().equalsIgnoreCase(username) && user.getEmailAddress().equalsIgnoreCase(email))
		{
			user.setFirstName(firstname);
			user.setLastName(lastname);
			user.setEmailAddress(email);
			user.setSsn(ssn);
			user.setSecurityA1(sec1);
			user.setSecurityA2(sec2);
			user.setGender(gender);
			user.setDob(dob);
			log.setPassword(Password);
			loginDAO.userregistration(log);
			userDAO.updateUser(user);
			
		}
			return "redirect:/";
	}
	
	


}
