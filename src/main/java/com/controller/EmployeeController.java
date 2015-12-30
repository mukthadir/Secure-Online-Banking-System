package com.controller;



import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import com.db.Dummytransaction;
import com.db.Employee;
import com.db.Usercritical;
import com.utils.SessionUtils;



@Controller
public class EmployeeController {
	
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
	
	@RequestMapping(value="/Processed",params={"authorize"})
	public String processedTransaction(HttpServletRequest request,Model model)
	{
		if (!SessionUtils.isAdminLogin(request)
				&& !SessionUtils.isMerchantLogin(request)
				&& !SessionUtils.isUserLogin(request)) 
		{
			Employee empinfo=(Employee)request.getSession().getAttribute("employee");
			System.out.println(empinfo.getEmployeeUsername());
			model.addAttribute("email", empinfo.getEmployeeEmailId());
			if(empinfo!=null)
			{
				String[] values=request.getParameterValues("values");
				if(values!=null){
				Employee emp=(Employee)request.getSession().getAttribute("employee");
				String empid=emp.getEmployeeId();
				List<Dummytransaction> transactionList=dummyTransactionDAO.getEmployeeTransaction(emp);
				
				if(values.length==transactionList.size())
				{
					for(int i=0;i<values.length;i++)
					{
						if(values[i].equalsIgnoreCase("Approve"))
						{
							try
							{
							Dummytransaction temp=transactionList.get(i);
							dummyTransactionDAO.criticalTransactionApproved(temp);			
							}
							catch(Exception e)
							{
								return "requestfailure";
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
								return "requestfailure";
							}
						}
					}
				}
				
				List<Dummytransaction> transactionlist=dummyTransactionDAO.getEmployeeTransaction(emp);
				model.addAttribute("container",transactionlist);
				model.addAttribute("employee", emp);
				request.getSession().setAttribute("employee", emp);
				return "employee";
			}
			else
			{
				List<Dummytransaction> transactionlist=dummyTransactionDAO.getEmployeeTransaction(empinfo);
				model.addAttribute("container",transactionlist);
				model.addAttribute("employee", empinfo);
				request.getSession().setAttribute("employee", empinfo);
				return "employee";
			}}
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
	
	@RequestMapping(value="/emp/home")
	public String redirectToHomePage(HttpServletRequest request,Model model)
	{
		if (!SessionUtils.isAdminLogin(request)
				&& !SessionUtils.isMerchantLogin(request)
				&& !SessionUtils.isUserLogin(request)) 
		{
			Employee empinfo=(Employee)request.getSession().getAttribute("employee");
			model.addAttribute("email", empinfo.getEmployeeEmailId());
			if(empinfo!=null)
			{
				Employee emp=(Employee)request.getSession().getAttribute("employee");
				String empid=emp.getEmployeeId();
				List<Dummytransaction> transactionlist=dummyTransactionDAO.getEmployeeTransaction(emp);
				model.addAttribute("container",transactionlist);
				model.addAttribute("employee", emp);
				request.getSession().setAttribute("employee", emp);
				return "employee";
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
	
	
	@RequestMapping(value="/transaction/create")
	public String createTransaction(HttpServletRequest request,Model model)
	{
		if (!SessionUtils.isAdminLogin(request)
				&& !SessionUtils.isMerchantLogin(request)
				&& !SessionUtils.isUserLogin(request)) 
		{
			Employee empinfo=(Employee)request.getSession().getAttribute("employee");
			model.addAttribute("email", empinfo.getEmployeeEmailId());
			if(empinfo!=null)
			{
				model.addAttribute("container", new Dummytransaction());
				return "create";
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
	
	@RequestMapping(value="/transaction/create/submit")
	public String createTransactionSubmit(@ModelAttribute("container") Dummytransaction transact,HttpServletRequest request,Model model)
	{
		if (!SessionUtils.isAdminLogin(request)
				&& !SessionUtils.isMerchantLogin(request)
				&& !SessionUtils.isUserLogin(request)) 
		{
			Employee empinfo=(Employee)request.getSession().getAttribute("employee");
			model.addAttribute("email", empinfo.getEmployeeEmailId());
			if(empinfo!=null)
			{
				try{
				AccountSummary senderSummary = accountSummaryDAO.getSummary(transact.getAccountSummaryByUserSending().getAccountNumber());
				AccountSummary receiverSummary = accountSummaryDAO.getSummary(transact.getAccountSummaryByUserReceiving().getAccountNumber());
		
				Dummytransaction update=new Dummytransaction();
		
				update.setAccountSummaryByUserReceiving(receiverSummary);
				update.setAccountSummaryByUserSending(senderSummary);
		
				update.setEmployee(null);
				update.setReviewedByEmployee(true);
				update.setAmount(transact.getAmount());
		
				Date date = new Date();
				DateFormat format = new SimpleDateFormat("yyyy-dd-mm");
				update.setTime(format.format(date));
		
				senderSummary.setBalance(senderSummary.getBalance()-transact.getAmount());
				receiverSummary.setBalance(receiverSummary.getBalance()+transact.getAmount());
		
				dummyTransactionDAO.createTransaction(update);
			
				accountSummaryDAO.updateAccountSummary(senderSummary);
				accountSummaryDAO.updateAccountSummary(receiverSummary);
		
				}
				
				catch(Exception e)
				{
					return "requestfailure";
				}
				
				model.addAttribute("container", new Dummytransaction());
				return "create";
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
	
//	
//	@RequestMapping(value="/transaction/modify")
//	public String modifyTransaction(Model model)
//	{
//		if (!SessionUtils.isAdminLogin(request)
//			&& !SessionUtils.isEmployeeLogin(request)
//			&& !SessionUtils.isMerchantLogin(request)
//			&& !SessionUtils.isUserLogin(request)) 
//		{
//			Employee empinfo=(Employee)request.getSession().getAttribute("employee");
//		
//			if(empinfo!=null)
//			{
//			
//			}
//		}
//
//		model.addAttribute("transactions",BankController.transactions);
//		model.addAttribute("container", new Transaction());
//		return "modifytransaction";
//	}
//	
//	@RequestMapping(value="/transaction/modify/submit")
//	public String modifyTransactionSubmit(@ModelAttribute("container") Transaction transact,HttpServletRequest request,Model model)
//	{
//		if (!SessionUtils.isAdminLogin(request)
//			&& !SessionUtils.isEmployeeLogin(request)
//			&& !SessionUtils.isMerchantLogin(request)
//			&& !SessionUtils.isUserLogin(request)) 
//		{
//			Employee empinfo=(Employee)request.getSession().getAttribute("employee");
//		
//			if(empinfo!=null)
//			{
//		
//			}
//		}
//		String[] VTID=transact.getTID().split(",");
//		String[] VSender=transact.getSender().split(",");
//		String[] VReceiver=transact.getReceiver().split(",");
//		String[] VAmount=transact.getAmount().split(",");
//		String[] VDate=transact.getDate().split(",");
//		String[] path=transact.getFlag().split(",");
//		for(int i=0;i<path.length;i++)
//		{
//			if(path[i].equals("Yes"))
//			{
//				Transaction temp=BankController.transactions.get(i);
//				temp.setSender(VSender[i]);
//				temp.setTID(VTID[i]);
//				temp.setAmount(VAmount[i]);
//				temp.setReceiver(VReceiver[i]);
//				temp.setDate(VDate[i]);
//			}
//		}
//		model.addAttribute("transactions",BankController.transactions);
//		model.addAttribute("container", new Transaction());
//		return "modifytransaction";
//	}
//	
	@RequestMapping(value="/transaction/delete")
	public String deleteTransaction(HttpServletRequest request,Model model)
	{
		
		if (!SessionUtils.isAdminLogin(request)
				&& !SessionUtils.isMerchantLogin(request)
				&& !SessionUtils.isUserLogin(request)) 
		{
			Employee empinfo=(Employee)request.getSession().getAttribute("employee");
			model.addAttribute("email", empinfo.getEmployeeEmailId());
			if(empinfo!=null)
			{
				Employee emp=employeeDAO.getEmployeeById(empinfo.getEmployeeId());
				model.addAttribute("transactions",dummyTransactionDAO.getModifyTransaction(emp));
				return "Deletetransaction";
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
	
	@RequestMapping(value="/transaction/delete/submit")
	public String deleteTransactionSubmit(HttpServletRequest request,Model model)
	{
		if (!SessionUtils.isAdminLogin(request)
				&& !SessionUtils.isMerchantLogin(request)
				&& !SessionUtils.isUserLogin(request)) 
		{
			Employee empinfo=(Employee)request.getSession().getAttribute("employee");
			model.addAttribute("email", empinfo.getEmployeeEmailId());
			if(empinfo!=null)
			{
				String[] values=request.getParameterValues("values");
				
				Employee emp=employeeDAO.getEmployeeById(empinfo.getEmployeeId());
				
				if(values!=null)
				{
					List<Dummytransaction> list=dummyTransactionDAO.getModifyTransaction(emp);
					if(values.length==list.size())
					{
				
						for(int i=0;i<values.length;i++)
						{
							Dummytransaction temp=list.get(i);
							if(values[i].equalsIgnoreCase("Delete"))
							{
								try{
									System.out.println("In delete");
									//effect both the parties
									AccountSummary receiver = accountSummaryDAO.getSummary(temp.getAccountSummaryByUserSending().getAccountNumber());
									AccountSummary sender=accountSummaryDAO.getSummary(temp.getAccountSummaryByUserReceiving().getAccountNumber());
				
									Dummytransaction update=new Dummytransaction();
				
									update.setAccountSummaryByUserReceiving(receiver);
									update.setAccountSummaryByUserSending(sender);
				
									//set employee to the current eemployee
									update.setEmployee(null);
									update.setReviewedByEmployee(true);
									update.setAmount(temp.getAmount());
				
									sender.setBalance(sender.getBalance()-temp.getAmount());
									receiver.setBalance(receiver.getBalance()+temp.getAmount());
				
									Date date = new Date();
									DateFormat format = new SimpleDateFormat("yyyy-dd-mm");
									update.setTime(format.format(date));
				
									dummyTransactionDAO.createTransaction(update);
				
									accountSummaryDAO.updateAccountSummary(sender);
									accountSummaryDAO.updateAccountSummary(receiver);
				
									dummyTransactionDAO.criticalTransactionApproved(temp);
								}
								catch(Exception e)
								{
									return "requestfailure";
								}
							}
							else if(values[i].equalsIgnoreCase("Reject"))
							{
								dummyTransactionDAO.criticalTransactionRejected(temp);
							}
						}
					}
					
					model.addAttribute("transactions",dummyTransactionDAO.getModifyTransaction(emp));
					return "Deletetransaction";
				}
				model.addAttribute("transactions",dummyTransactionDAO.getModifyTransaction(emp));
				return "Deletetransaction";
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
	
	
	@RequestMapping(value="/approvereq")
	public String approverequests(HttpServletRequest request,Model model)
	{
		if (!SessionUtils.isAdminLogin(request)
				&& !SessionUtils.isMerchantLogin(request)
				&& !SessionUtils.isUserLogin(request)) 
		{
			Employee empinfo=(Employee)request.getSession().getAttribute("employee");
			model.addAttribute("email", empinfo.getEmployeeEmailId());
			if(empinfo!=null)
			{
				List<Usercritical> critical=userCriticalDAO.getCriticalRequests();
				model.addAttribute("critical",critical);
				return "ApproveRequest";
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

}

