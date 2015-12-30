package com.controller;

import com.db.AccountSummary;
import com.db.Admininfo;
import com.db.Dummytransaction;
import com.db.Employee;
import com.db.Login;
import com.db.Merchantinfo;
import com.db.TransactionList;
import com.db.User;
import com.db.Usercritical;

import net.tanesha.recaptcha.ReCaptchaException;
import net.tanesha.recaptcha.ReCaptchaImpl;
import net.tanesha.recaptcha.ReCaptchaResponse;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dao.AccountSummaryDAO;
import com.dao.AdminDAO;
import com.dao.EmployeeDAO;
import com.dao.LoginDAO;
import com.dao.MerchantDAO;
import com.dao.DummyTransactionDAO;
import com.dao.TransactionWithbalance;
import com.dao.UserCriticalDAO;
import com.dao.UserDAO;
import com.utils.EmailSender;
import com.utils.OTPGenerator;
import com.utils.SessionUtils;

import java.security.InvalidParameterException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Controller
public class BankController {

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

	private static final String ROLE_ADMIN = "admin";
	private static final String ROLE_USER = "user";
	private static final String ROLE_MERCHANT = "merchant";
	private static final String ROLE_EMPLOYEE = "employee";
	private static void setUpEmailAccount(User user, Login login) {
		String emailReceiver = user.getEmailAddress();
		String otp = OTPGenerator.generate_Random_Password();
		login.setOtp(otp);
		EmailSender.sendMail("admgroup4CSE543@gmail.com", "bruteforce", otp, emailReceiver);
	}

	Random random = new Random();
	List<String> accounNumbers = new ArrayList<String>();

	int min = 10000;
	int max = 1000000;

	int inclusive = max - min + 1;
	int exclusive = max - min;

	@RequestMapping(value = "/")
	public String renderLoginPage(Model model, HttpServletRequest request) {
		request.getSession().invalidate();
		System.out.println("In login");
		model.addAttribute("login", new Login());
		return "loginPage";
	}

	@RequestMapping(value = "/homePage", params = { "login" })
	public String renderHomePage(@ModelAttribute("login") Login login,
			HttpServletRequest request, Model model) {
		System.out.println("Login ID:"+login.getLoginId()+"password"+login.getPassword());
		if (!login.getLoginId().isEmpty() && login.getLoginId() != null
				&& !login.getPassword().isEmpty()
				&& login.getPassword() != null) {
			String role = loginDAO.getRole(login.getLoginId(),
					login.getPassword());
			try {
				String remoteAddr = request.getRemoteAddr();
				ReCaptchaImpl reCaptcha = new ReCaptchaImpl();
				reCaptcha
						.setPrivateKey("6Ldw_PwSAAAAABXD4A_8dRl81MQOYf1aOvZSFSJ6");

				String challenge = request
						.getParameter("recaptcha_challenge_field");
				String uresponse = request
						.getParameter("recaptcha_response_field");
				ReCaptchaResponse reCaptchaResponse = reCaptcha.checkAnswer(
						remoteAddr, challenge, uresponse);

				if (reCaptchaResponse.isValid()) {
					request.getSession().setAttribute("login", login);
					if (role.equalsIgnoreCase(ROLE_MERCHANT)) {
						request.getSession().invalidate();
						Merchantinfo merchant = merchantDAO.getMerchant();
						AccountSummary summary = accountSummaryDAO
								.getSummary(merchant.getMerchantAccountNumber());
						model.addAttribute("merchant", merchant);
						model.addAttribute("balance", summary.getBalance());
						request.getSession().setAttribute("merchant", merchant);
						return "merchant";

					} else if (role.equalsIgnoreCase(ROLE_ADMIN)) {
						Admininfo admin = adminDAO.getAdmin();
						if(admin==null)
							System.out.println("not null jaffa");
						request.getSession().setAttribute("admin", admin);
						Admininfo ad=(Admininfo)request.getSession().getAttribute("admin");
						if(ad==null)
							System.out.println("here ra jaffa");
						String emailid=adminDAO.getAdminEmailId();
						System.out.println(emailid);
						List<Dummytransaction> transactionList=dummyTransactionDAO.getCriticalTransaction();
						for(int i=0;i<transactionList.size();i++)
						{
							System.out.println("Helo"+transactionList.get(i).getTransactionId());
						}
						model.addAttribute("criticallist",transactionList);
						
						return "admin";

					} else if (role.equalsIgnoreCase(ROLE_EMPLOYEE)) {
						Employee employee = employeeDAO.getEmployeeByNewId(login
								.getLoginId());
						String employeeid=employee.getEmployeeId();
						System.out.println(employeeid);
						List<Dummytransaction> transactionlist=dummyTransactionDAO.getEmployeeTransaction(employee);
					
						model.addAttribute("container",transactionlist);
						model.addAttribute("employee", employee);
						request.getSession().setAttribute("employee", employee);
						return "employee";

					} else if (role.equalsIgnoreCase(ROLE_USER)) {

						request.getSession().invalidate();
						User user = userDAO.getUserByIdOrEmail(login.getLoginId());
						model.addAttribute("user", user);
						request.getSession().setAttribute("user", user);
						System.out
								.println("Sending checkings accnt number to dao:"
										+ user.getCheckingsAccountNumber());
						AccountSummary summary = userDAO
								.getAccountSummaryByAccountNumber(user
										.getCheckingsAccountNumber());
						System.out.println("summary balance in user accnt:"
								+ summary.getBalance());
						List<Dummytransaction> transactionList = dummyTransactionDAO
								.pullMerchantPaymentRequests(user
										.getCheckingsAccountNumber());
						TransactionList list = new TransactionList();
						list.setTransactionList(transactionList);
						
						// Populating all transactions
						
						model.addAttribute("transactions", list);
						model.addAttribute("balance", summary.getBalance());
						return "user";
					}
					if (role.equalsIgnoreCase("error")) {
						model.addAttribute("failure",
								"The entered credentials are invalid");
					}
					return "loginPage";
				} else {
					model.addAttribute("failure",
							"The captcha entered is incorrect");
					return "loginPage";
				}
			} catch (ReCaptchaException exception) {
				System.out.println("Recaptcha service throwing exception");
				model.addAttribute("failure",
						"The captcha entered is incorrect");
				return "loginPage";
			}
		} else {
			return "redirect:/";
		}

	}

	
	@RequestMapping(value = "/checkingsAccount")
	public String renderCheckingsAccountPage(Model model,
			HttpServletRequest request) {
		if (!SessionUtils.isAdminLogin(request)
				&& !SessionUtils.isEmployeeLogin(request)
				&& !SessionUtils.isMerchantLogin(request)) {

			User user = (User) request.getSession().getAttribute("user");
			if(user != null) {
				model.addAttribute("user", user);
				AccountSummary summary = accountSummaryDAO.getSummary(user
						.getCheckingsAccountNumber());
				model.addAttribute("balance", summary.getBalance());
				List<Dummytransaction> transactionList = dummyTransactionDAO
						.pullMerchantPaymentRequests(user
								.getCheckingsAccountNumber());
				TransactionList list = new TransactionList();
				list.setTransactionList(transactionList);
				model.addAttribute("transactions", list);
				return "user";
			} else {
				return "redirect:/";
			}
		} else {
			return "noAccess";
		}
	}

	
	@RequestMapping(value="/userTransactions")
	public String getUserTransactions(HttpServletRequest request, Model model) {
		User user = (User)request.getSession().getAttribute("user");
		if (!SessionUtils.isAdminLogin(request)
				&& !SessionUtils.isEmployeeLogin(request)
				&& !SessionUtils.isMerchantLogin(request)) {

		if(user!=null) {
			
		
		AccountSummary summary = accountSummaryDAO.getSummary(user.getCheckingsAccountNumber());
		List<Dummytransaction> dummyTransactionsTable = dummyTransactionDAO
				.getAllTransactionsForUser(user
						.getCheckingsAccountNumber());
		if(dummyTransactionsTable!= null && dummyTransactionsTable.size() != 0) {
				List<TransactionWithbalance> listOfTransactionsWithbalance = new ArrayList<TransactionWithbalance>();
				int currentbalance = summary.getBalance();
				for(Dummytransaction dummy: dummyTransactionsTable) {
					TransactionWithbalance tWithbalance = new TransactionWithbalance();
						if (dummy.getAccountSummaryByUserReceiving() != null && dummy.getAccountSummaryByUserReceiving()
								.getAccountNumber() == user
								.getCheckingsAccountNumber()) {
							tWithbalance.setCredit(dummy.getAmount());
							tWithbalance.setBalance(currentbalance);
							currentbalance-=dummy.getAmount();
							tWithbalance.setDate(dummy.getTime());
							tWithbalance.setBeingReviewed(dummy.getReviewedByEmployee());
							
					} else if (dummy
							.getAccountSummaryByUserSending() != null
							&& dummy.getAccountSummaryByUserSending()
									.getAccountNumber() == user
									.getCheckingsAccountNumber()) {

							tWithbalance.setDebit(dummy.getAmount());
							tWithbalance.setBalance(currentbalance);
							currentbalance+=dummy.getAmount();
							tWithbalance.setDate(dummy.getTime());
							tWithbalance.setBeingReviewed(dummy.getReviewedByEmployee());
						}

					listOfTransactionsWithbalance.add(tWithbalance);
				}
				model.addAttribute("transactionsTable", listOfTransactionsWithbalance);							
				return "userTransactions";
			
		} else {
			model.addAttribute("noTransactions", "There are currently no transactions");
			return "userTransactions";
		}
		} 
		return "redirect:/";
		}
		return "noAccess";

	}
	
	@RequestMapping(value = "/homePage", params = { "newUser" })
	public String renderNewRegistration(HttpServletRequest request, Model model) {
		if (!SessionUtils.isAdminLogin(request)
				&& !SessionUtils.isEmployeeLogin(request)
				&& !SessionUtils.isMerchantLogin(request)
				&& !SessionUtils.isUserLogin(request)) {
			if (request.getSession().getAttribute("failure") != null) {
				model.addAttribute("failure", request.getSession()
						.getAttribute("failure"));
			}
			model.addAttribute("user", new User());
			return "newUser";
		}
		return "noAccess";
	}

	@RequestMapping(value = "/saveNewUserDetails")
	public String saveUserDetails(@ModelAttribute("user") User user,
			Model model, HttpServletRequest request) {
		if (!SessionUtils.isAdminLogin(request)
				&& !SessionUtils.isEmployeeLogin(request)
				&& !SessionUtils.isMerchantLogin(request)
				&& !SessionUtils.isUserLogin(request)) {

			if (user.getDob() != null && !user.getDob().isEmpty() && user.getFirstName() != null && !user.getFirstName().isEmpty()
					&& user.getLastName() != null &&  !user.getLastName().isEmpty()
					&& user.getEmailAddress() != null && !user.getEmailAddress().isEmpty()
					&& user.getGender() != null && !user.getGender().isEmpty() 
					&& user.getSecurityA1() != null && !user.getSecurityA1().isEmpty()
					&& user.getSecurityQ1() != null
					&& user.getSecurityQ2() != null
					&& user.getSecurityA2() != null && !user.getSecurityA2().isEmpty() 
					&& user.getSsn() != null && !user.getSsn().isEmpty()
					&& user.getUsername() != null && !user.getUsername().isEmpty()) {
				String userStatus = userDAO.isUserExists(user);
				boolean isExistingCredentials = loginDAO.isExistingUserId(user.getUsername());
				if(!userStatus.equalsIgnoreCase("unique")) {
				
					if(userStatus.equalsIgnoreCase("existingUserId")) {
						model.addAttribute("duplicateuserId", "This username has already been taken");
						
					} else if(userStatus.equalsIgnoreCase("existingEmailAddress")) {
						model.addAttribute("duplicateEmail", "We already have a user registered with this email ID");
					}
				
					return "newUser";
				} else if(isExistingCredentials) {
					model.addAttribute("duplicateuserId", "This username has already been taken");
					return "newUser";
				} 
				
				boolean isExistingEmployeeEmailId = employeeDAO.isExistingEmployeeEmail(user.getEmailAddress());
				boolean isExistingMerchantEmailId = merchantDAO.isExistingMerchantEmail(user.getEmailAddress());
				boolean isExistingAdminEmailId = adminDAO.isExistingAdminEmail(user.getEmailAddress());
				
				if(isExistingEmployeeEmailId || isExistingMerchantEmailId || isExistingAdminEmailId) {
					model.addAttribute("duplicateEmail", "We already have a user registered with this email ID");
					return "newUser";
				}
				System.out.println("In save new user details");
				request.getSession().setAttribute("newUser", user);

				model.addAttribute("login", new Login());
				return "setPassword";
			} else {
				model.addAttribute("failure", "All fields are required");
				return "newUser";
			}
		} else {
			return "noAccess";
		}

	}

	@RequestMapping(value = "/saveUser")
	public String saveUser(@ModelAttribute("login") Login login,
			HttpServletRequest request, Model model) {
		if (!SessionUtils.isAdminLogin(request)
				&& !SessionUtils.isEmployeeLogin(request)
				&& !SessionUtils.isMerchantLogin(request)
				&& !SessionUtils.isUserLogin(request)) {
			// Generating Account number and checking if it is present in DB
			User user = (User) request.getSession().getAttribute("newUser");			
				if(user != null) {
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
						userDAO.createUser(user);
					} catch (InvalidParameterException invException) {
						System.out.println("Transaction failure");
						request.getSession().setAttribute("failure",
								"Please enter a different username");
						return "redirect:homePage?newUser";
					} catch (Exception ex) {
						ex.printStackTrace();
						System.out.println("Session closed");
						request.getSession().setAttribute("failure",
								"Please enter a different username");
						return "redirect:homePage?newUser";
					}

					// Entry in Login table, Setting userName from User
					login.setRole("user");
					login.setLoginId(user.getUsername());
					System.out.println("Login id:" + login.getLoginId());
					System.out.println("Login password:" + login.getPassword());
					loginDAO.saveLoginInformation(login);
					model.addAttribute("success", "Details successfully saved");

					// Entry in AccountSummary table
					AccountSummary accountSummary = new AccountSummary();
					accountSummary.setAccountNumber(user.getCheckingsAccountNumber());
					accountSummary.setBalance(120);
					Dummytransaction dummy = new Dummytransaction();
					dummy.setAccountSummaryByUserReceiving(accountSummary);
					dummy.setAmount(120);
					Calendar calendar = Calendar.getInstance();
					DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					System.out.println("Date:"+format.format(calendar.getTime()));
					dummy.setTime(format.format(calendar.getTime()));
					
					String[] listOfEmployees = employeeDAO.getAllEmployeeIds();
					if(listOfEmployees.length != 0) {
						int randomNumber = new Random().nextInt(listOfEmployees.length);

					Employee employee = employeeDAO
							.getEmployeeById(listOfEmployees[randomNumber]);
					
					System.out.println("employee email:"
							+ employee.getEmployeeEmailId());
					dummy.setEmployee(employee);
					dummy.setReviewedByEmployee(false);
					} else {
						dummy.setAdminReviewing(true);
					}
					accountSummaryDAO.createAccount(accountSummary);
					dummyTransactionDAO.createTransaction(dummy);
					return "acccountCreationConfirmation";
					
				} else {
					return "redirect:/";
				}
				
		} else {
			return "noAccess";
		}

	}

	@RequestMapping(value = "userCredit")
	public String renderUserCredit(HttpServletRequest request, Model model) {
		if (!SessionUtils.isAdminLogin(request)
				&& !SessionUtils.isEmployeeLogin(request)
				&& !SessionUtils.isMerchantLogin(request)) {
			User user = (User)request.getSession()
					.getAttribute("user");
			if(user != null) {
				model.addAttribute("dummyTransaction", new Dummytransaction());
				model.addAttribute("user", user);
				return "userCredit";
				
			} else {
				return "redirect:/";
			}
		} else {
			return "noAccess";
		}
	}

	@RequestMapping(value = "/updateBalance")
	public String creditBalance(
			@ModelAttribute("dummyTransaction") Dummytransaction dummyTransaction,
			Model model, HttpServletRequest request) {
		if (!SessionUtils.isAdminLogin(request)
				&& !SessionUtils.isEmployeeLogin(request)
				&& !SessionUtils.isMerchantLogin(request)) {

			User user = (User) request.getSession().getAttribute("user");
			if(user != null) {
				if(dummyTransaction.getAmount() <= 0) {
					model.addAttribute("failure", "Please enter a non zero non negative amount");
					model.addAttribute("dummyTransaction", new Dummytransaction());
					model.addAttribute("user", user);
					return "userCredit";					
				}
				AccountSummary	summary = accountSummaryDAO.getSummary(user
							.getCheckingsAccountNumber());
					
				summary.setBalance(summary.getBalance()
						+ dummyTransaction.getAmount());
				System.out.println("accnt number and balance"
						+ summary.getAccountNumber() + " " + summary.getBalance());
				dummyTransaction.setAccountSummaryByUserReceiving(summary);
//				dummyTransaction.setAccountSummaryByUserSending(summary);
				String[] listOfEmployees = employeeDAO.getAllEmployeeIds();
				if(listOfEmployees.length != 0) {
					int randomNumber = new Random().nextInt(listOfEmployees.length);
					System.out.println("Random empl:" + listOfEmployees[randomNumber]);
					Employee employee = employeeDAO
							.getEmployeeById(listOfEmployees[randomNumber]);
					System.out.println("employee email:"
							+ employee.getEmployeeEmailId());
					dummyTransaction.setEmployee(employee);
				} else {
					dummyTransaction.setAdminReviewing(true);
				}
				dummyTransaction.setReviewedByEmployee(false);
				if (dummyTransaction.getAmount() >= 1000) {
					dummyTransaction.setCritical(true);
				}

				// Setting date of transaction
				Calendar calendar = Calendar.getInstance();
				DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				System.out.println("Date:"+format.format(calendar.getTime()));
				dummyTransaction.setTime(format.format(calendar.getTime()));
				dummyTransactionDAO.createTransaction(dummyTransaction);
				accountSummaryDAO.updateAccountSummary(summary);
				model.addAttribute("user", user);
				return "successfulPayment";
				
			} else {
				return "redirect:/";
			}
		} else {
			return "noAccess";
		}
	}

	@RequestMapping(value = "userDebit")
	public String renderUserDebit(HttpServletRequest request, Model model) {
		if (!SessionUtils.isAdminLogin(request)
				&& !SessionUtils.isEmployeeLogin(request)
				&& !SessionUtils.isMerchantLogin(request)) {
			User user =(User)request.getSession()
					.getAttribute("user");
			if(user != null) {
				model.addAttribute("dummyTransaction", new Dummytransaction());
				model.addAttribute("user", user);
				return "userDebit";				
			} else {
				return "redirect:/";
			}
		} else {
			return "noAccess";
		}
	}

	@RequestMapping(value = "/updateBalanceAfterDebit")
	public String debitBalance(
			@ModelAttribute("dummyTransaction") Dummytransaction dummyTransaction,
			Model model, HttpServletRequest request) {
		if (!SessionUtils.isAdminLogin(request)
				&& !SessionUtils.isEmployeeLogin(request)
				&& !SessionUtils.isMerchantLogin(request)) {
			User user = (User) request.getSession().getAttribute("user");
			if(user != null) {
				if(dummyTransaction.getAmount() <= 0) {
					model.addAttribute("failure", "Please enter a non zero non negative amount");
					model.addAttribute("dummyTransaction", new Dummytransaction());
					model.addAttribute("user", user);
					return "userDebit";					
				}				
				AccountSummary summary = accountSummaryDAO.getSummary(user
						.getCheckingsAccountNumber());
				if (summary.getBalance() < dummyTransaction.getAmount()) {
					model.addAttribute("failure",
							"Please enter an amount less than your account balance");
					return "userDebit";
				} 
				summary.setBalance(summary.getBalance()
						- dummyTransaction.getAmount());
				System.out.println("accnt number and balance"
						+ summary.getAccountNumber() + " " + summary.getBalance());
				dummyTransaction.setAccountSummaryByUserSending(summary);
				String[] listOfEmployees = employeeDAO.getAllEmployeeIds();
				if(listOfEmployees.length != 0) {

				int randomNumber = new Random().nextInt(listOfEmployees.length);
				System.out.println("Random empl:" + listOfEmployees[randomNumber]);
				Employee employee = employeeDAO
						.getEmployeeById(listOfEmployees[randomNumber]);
				System.out.println("employee email:"
						+ employee.getEmployeeEmailId());
				dummyTransaction.setEmployee(employee);
				} else {
					dummyTransaction.setAdminReviewing(true);
				}
				dummyTransaction.setReviewedByEmployee(false);
				if (dummyTransaction.getAmount() >= 1000) {
					dummyTransaction.setCritical(true);
				}

				Calendar calendar = Calendar.getInstance();
				DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				System.out.println("Date:"+format.format(calendar.getTime()));
				dummyTransaction.setTime(format.format(calendar.getTime()));
				dummyTransactionDAO.createTransaction(dummyTransaction);
				accountSummaryDAO.updateAccountSummary(summary);
				model.addAttribute("user", user);
				return "successfulPayment";
			} else {
				return "redirect:/";
			}
		} else {
			return "noAccess";
		}
	}


	@RequestMapping(value = "/editProfile")
	public String editPersonalProfile(HttpServletRequest request, Model model)
			throws ParseException {
		if (!SessionUtils.isAdminLogin(request)
				&& !SessionUtils.isEmployeeLogin(request)
				&& !SessionUtils.isMerchantLogin(request)) {
			User userFromSession = (User) request.getSession().getAttribute("user");
			User user = userDAO.getUserByIdOrEmail(userFromSession.getUsername());
			if(user != null && userFromSession!= null) {
				model.addAttribute("user", user);
				return "editProfile";				
			} else {
				return "redirect:/";
			}
		} else {
			return "noAccess";
		}
	}

	@RequestMapping(value = "/saveProfileChanges")
	public String saveEditPersonalProfile(@ModelAttribute("user") User user,
			HttpServletRequest request, Model model) {
		if (!SessionUtils.isAdminLogin(request)
				&& !SessionUtils.isEmployeeLogin(request)
				&& !SessionUtils.isMerchantLogin(request)) {

			System.out.println("In save edit profile");
			User userFromSession = (User) request.getSession().getAttribute(
					"user");
			Usercritical userCritical = new Usercritical();
			if(userFromSession != null) {
				if (user.getDob() != null) {
					userFromSession.setDob(user.getDob());
				}
				String emailId = user.getEmailAddress();
				if((merchantDAO.isExistingMerchantEmail(emailId) ||
						adminDAO.isExistingAdminEmail(emailId) ||
						employeeDAO.isExistingEmployeeEmail(emailId) ||
						userDAO.isUserExists(userFromSession).equalsIgnoreCase("existingEmailAddress"))
						&& !userFromSession.getEmailAddress().equalsIgnoreCase(user.getEmailAddress())) {
					System.out.println("Duplicate email address user");
					model.addAttribute("failure","We already have a registered user with this email ID");
					model.addAttribute("user",userFromSession);
					return "editProfile";
				}
				userFromSession.setUsername(user.getUsername());
				if (user.getEmailAddress() != null && !user.getEmailAddress().isEmpty()
						&& !user.getSsn().isEmpty() && user.getSsn()!=null) {
					userCritical.setUsername(userFromSession.getUsername());
					String[] listOfEmployees = employeeDAO.getAllEmployeeIds();
					String employeeOrAdminUserName= "";
					if(listOfEmployees.length != 0) {
					employeeOrAdminUserName = listOfEmployees[new Random()
							.nextInt(listOfEmployees.length)];
					userCritical.setEmployeeIdRequested(employeeOrAdminUserName);
					} else {
						// TO set transaction reviewed by admin. Make empployeid nullable in dummy and trnasaciotn table.
					}
					if (!user.getSsn().equalsIgnoreCase(userFromSession.getSsn())) {
						
						userCritical.setSsn(user.getSsn());
					}
					if(!user.getEmailAddress().equalsIgnoreCase(userFromSession.getEmailAddress())) {
						userCritical.setEmailId(user.getEmailAddress());
					}
					model.addAttribute("employeeMsg",
							"Your request for change has been forwarded to "
									+ employeeOrAdminUserName);
					userCriticalDAO.saveOrUpdate(userCritical);

//					userFromSession.setEmailAddress(user.getEmailAddress());
				} else {
					model.addAttribute("failure", "No field can be left empty");
					model.addAttribute("user",userFromSession);
					return "editProfile";
				}
				
				System.out.println("Updating user");
				userDAO.updateUser(userFromSession);
				model.addAttribute("success", "Profile Updated");
				model.addAttribute("user", userFromSession);
				model.addAttribute(
						"balance",
						accountSummaryDAO.getSummary(
								userFromSession.getCheckingsAccountNumber())
								.getBalance());
				List<Dummytransaction> transactionList = dummyTransactionDAO
						.pullMerchantPaymentRequests(userFromSession
								.getCheckingsAccountNumber());
				model.addAttribute("transactions", transactionList);
				return "user";
				
			} else {
				return "redirect:/";
			}
		} else {
			return "noAccess";
		}
	}

	@RequestMapping(value = "/transfer")
	public String navigateToTransferPage(HttpServletRequest request, Model model) {
		if (!SessionUtils.isAdminLogin(request)
				&& !SessionUtils.isEmployeeLogin(request)
				&& !SessionUtils.isMerchantLogin(request)) {
			User user =(User)request.getSession()
					.getAttribute("user");
			if(user != null) {
				model.addAttribute("dummyTransaction", new Dummytransaction());
				model.addAttribute("user", user);
				return "transfers";
			} else {
				return "redirect:/";
			}
		} else {
			return "noAccess";
		}
	}

	@RequestMapping(value = "/settleBalanceAfterTransfer")
	public String settleBlanaceAfterTransfer(
			HttpServletRequest request,
			Model model,
			@ModelAttribute("dummyTransaction") Dummytransaction dummyTransaction) {
		if (!SessionUtils.isAdminLogin(request)
				&& !SessionUtils.isEmployeeLogin(request)
				&& !SessionUtils.isMerchantLogin(request)) {

			User user = (User) request.getSession().getAttribute("user");
			if(user != null) {
				if(dummyTransaction.getAmount() <= 0) {
					model.addAttribute("failure", "Please enter a non zero non negative amount");
					model.addAttribute("dummyTransaction", new Dummytransaction());
					model.addAttribute("user", user);
					return "transfers";					
				}
				if(user.getCheckingsAccountNumber() == dummyTransaction.getAccountSummaryByUserReceiving().getAccountNumber()) {
					model.addAttribute("failure", "Please use the credit functionality to make transfer to your own account");
					model.addAttribute("dummyTransaction", new Dummytransaction());
					model.addAttribute("user", user);
					return "transfers";					
				}
				AccountSummary senderSummary = accountSummaryDAO.getSummary(user
						.getCheckingsAccountNumber());
				if (senderSummary.getBalance() < dummyTransaction.getAmount()) {
					model.addAttribute("failure",
							"Please enter an amount less than your account balance");
					return "transfers";
				}
				senderSummary.setBalance(senderSummary.getBalance()
						- dummyTransaction.getAmount());
				System.out.println("accnt number and balance"
						+ senderSummary.getAccountNumber() + " "
						+ senderSummary.getBalance());
				AccountSummary receiverSummary = null;
				try {
					receiverSummary = accountSummaryDAO
							.getSummary(dummyTransaction
									.getAccountSummaryByUserReceiving()
									.getAccountNumber());
					
				} catch(InvalidParameterException invalidEx) {
					model.addAttribute("failure", "Please enter a valid account number");
					model.addAttribute("dummyTransaction", new Dummytransaction());
					model.addAttribute("user", user);
					return "transfers";										
				}
				receiverSummary.setBalance(receiverSummary.getBalance()
						+ dummyTransaction.getAmount());

				dummyTransaction.setAccountSummaryByUserReceiving(receiverSummary);
				dummyTransaction.setAccountSummaryByUserSending(senderSummary);
				String[] listOfEmployees = employeeDAO.getAllEmployeeIds();
				if(listOfEmployees.length != 0) {

				int randomNumber = new Random().nextInt(listOfEmployees.length);
				System.out.println("Random empl:" + listOfEmployees[randomNumber]);
				Employee employee = employeeDAO
						.getEmployeeById(listOfEmployees[randomNumber]);
				System.out.println("employee email:"
						+ employee.getEmployeeEmailId());
				dummyTransaction.setEmployee(employee);
				} else {
					dummyTransaction.setAdminReviewing(true);
				}
				dummyTransaction.setReviewedByEmployee(false);
				if (dummyTransaction.getAmount() >= 1000) {
					dummyTransaction.setCritical(true);
				}

				// Setting date of transaction
				Calendar calendar = Calendar.getInstance();
				DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				System.out.println("Date:"+format.format(calendar.getTime()));
				dummyTransaction.setTime(format.format(calendar.getTime()));

				dummyTransactionDAO.createTransaction(dummyTransaction);
				accountSummaryDAO.updateAccountSummary(senderSummary);
				accountSummaryDAO.updateAccountSummary(receiverSummary);
				model.addAttribute("user", user);
				return "successfulPayment";
				
			} else {
				return "redirect:/";
			}
		} else {
			return "noAccess";
		}
	}

	@RequestMapping(value = "/approveMerchantTransaction")
	public String approveMerchantTransactions(HttpServletRequest request,
			@ModelAttribute("transactions") TransactionList transactionList,
			Model model) {

		if (!SessionUtils.isAdminLogin(request)
				&& !SessionUtils.isEmployeeLogin(request)
				&& !SessionUtils.isMerchantLogin(request)) {
		User user = (User) request.getSession().getAttribute("user");
		if(user != null) {
			List<Dummytransaction> listOfTransactions = transactionList
					.getTransactionList();
			System.out.println("In approve merchant transaction");
			
			for (Dummytransaction transaction : listOfTransactions) {
				System.out.println("Account:"
						+ transaction.getAccountSummaryByUserReceiving()
								.getAccountNumber());
				if(transaction.getIsRejectedByUser()) {
					System.out.println("Rejected");
					Dummytransaction dummy = new Dummytransaction();
					dummy.setAccountSummaryByUserReceiving(transaction.getAccountSummaryByUserReceiving());
					dummy.setTransactionId(transaction.getTransactionId());
					dummy.setAccountSummaryByUserSending(transaction.getAccountSummaryByUserSending());
					dummy.setAmount(transaction.getAmount());
					dummy.setIsRejectedByUser(true);
					dummy.setIsApprovedByUser(false);
					System.out.println("Tid:"+transaction.getTransactionId());
					dummyTransactionDAO.updateTransaction(dummy);					
				}

				
				else if (transaction.getIsApprovedByUser()!=null && transaction.getIsApprovedByUser()) {
					System.out.println(transaction
							.getAccountSummaryByUserReceiving().getAccountNumber()
							+ "Approved");
					AccountSummary summarySender = accountSummaryDAO
							.getSummary(transaction
									.getAccountSummaryByUserSending()
									.getAccountNumber());
					AccountSummary summaryReceiver = accountSummaryDAO
							.getSummary(transaction
									.getAccountSummaryByUserReceiving()
									.getAccountNumber());
					if (summarySender.getBalance() > transaction.getAmount()) {
						System.out.println("Amount being transferred");
						summarySender.setBalance(summarySender.getBalance()
								- transaction.getAmount());
						accountSummaryDAO.updateAccountSummary(summarySender);

						summaryReceiver.setBalance(summaryReceiver.getBalance()
								+ transaction.getAmount());
						accountSummaryDAO.updateAccountSummary(summaryReceiver);

						Dummytransaction dummyTransaction = new Dummytransaction();
						dummyTransaction
								.setAccountSummaryByUserReceiving(summaryReceiver);
						dummyTransaction
								.setAccountSummaryByUserSending(summarySender);
						dummyTransaction.setAmount(transaction.getAmount());
						dummyTransaction.setIsApprovedByUser(true);
						dummyTransaction.setTransactionId(transaction
								.getTransactionId());
						dummyTransaction.setIsRejectedByUser(false);
						dummyTransactionDAO.updateTransaction(dummyTransaction);
					} else {
						AccountSummary summary = userDAO.getAccountSummaryByAccountNumber(user
								.getCheckingsAccountNumber());

						List<Dummytransaction> transactionFromDB = dummyTransactionDAO
								.pullMerchantPaymentRequests(user.getCheckingsAccountNumber());
						
						TransactionList list = new TransactionList();
						list.setTransactionList(transactionFromDB);
						
						model.addAttribute("transactions", list);
						model.addAttribute("balance", summary.getBalance());
						model.addAttribute("failure", "You cannot pay more than your account balance");
						return "user";
						
					}
					
				} 
			}
			AccountSummary summary = userDAO.getAccountSummaryByAccountNumber(user
					.getCheckingsAccountNumber());
			
			List<Dummytransaction> transaction = dummyTransactionDAO
					.pullMerchantPaymentRequests(user.getCheckingsAccountNumber());
			
			TransactionList list = new TransactionList();
			list.setTransactionList(transaction);
			
			model.addAttribute("transactions", list);
			model.addAttribute("balance", summary.getBalance());
			return "user";
			
		} else {
			return "redirect:/";
		}
		}
		return "noAccess";
	}

/*
 ******************************************* Merchant Module ****************************************************************
 */
	@RequestMapping(value = "/merchantCheckings")
	public String renderMerchantCheckings(HttpServletRequest request,
			Model model) {
		
		if (!SessionUtils.isAdminLogin(request)
				&& !SessionUtils.isEmployeeLogin(request)
				&& !SessionUtils.isUserLogin(request)) {

			Merchantinfo merchant = (Merchantinfo) request.getSession()
					.getAttribute("merchant");
			if(merchant != null) {
				model.addAttribute(
						"balance",
						accountSummaryDAO.getSummary(
								merchant.getMerchantAccountNumber()).getBalance());
		
				model.addAttribute("merchant", merchant);
				return "merchant";
				
			} else {
				return "redirect:/";
			}
		} else {
			return "noAccess";
		}
	}

	@RequestMapping(value = "/renderRequestPayment")
	public String renderRequestPayment(HttpServletRequest request, Model model) {
	
		if (!SessionUtils.isAdminLogin(request)
				&& !SessionUtils.isEmployeeLogin(request)
				&& !SessionUtils.isUserLogin(request)) {
			Merchantinfo merchant = (Merchantinfo)request.getSession().getAttribute("merchant");
			if(merchant != null) {
				model.addAttribute("dummyTransaction", new Dummytransaction());
				return "requestPayment";
			} else {
				return "redirect:/";
			}
		} else {
			return "noAccess";
		}
	}

	@RequestMapping(value = "/settleBalanceAfterMerchantPayment")
	public String settleBalanceAfterMerchantPayment(
			@ModelAttribute("dummyTransaction") Dummytransaction dummyTransaction,
			HttpServletRequest request, Model model) {

		if (!SessionUtils.isAdminLogin(request)
				&& !SessionUtils.isEmployeeLogin(request)
				&& !SessionUtils.isUserLogin(request)) {

			Merchantinfo merchant = (Merchantinfo) request.getSession()
					.getAttribute("merchant");
			if(merchant != null) {
				AccountSummary receiver = accountSummaryDAO.getSummary(merchant
						.getMerchantAccountNumber());
				dummyTransaction.setAccountSummaryByUserReceiving(receiver);
				// CHanges made by kushagra 11-6
				AccountSummary sender = null;
				try {
					sender = accountSummaryDAO
							.getSummary(dummyTransaction
									.getAccountSummaryByUserSending()
									.getAccountNumber());
						
				} catch(Exception ex) {
					model.addAttribute("failure", "Please enter a valid account number");
					model.addAttribute("dummyTransaction", new Dummytransaction());
					return "requestPayment";					
				}
				dummyTransaction.setAccountSummaryByUserSending(sender);
				String[] listOfEmployees = employeeDAO.getAllEmployeeIds();
				if(listOfEmployees.length != 0) {

				int randomNumber = new Random().nextInt(listOfEmployees.length);
				Employee employee = employeeDAO
						.getEmployeeById(listOfEmployees[randomNumber]);
				dummyTransaction.setEmployee(employee);
				} else {
					dummyTransaction.setAdminReviewing(true);
				}
				Calendar calendar = Calendar.getInstance();
				DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				System.out.println("Date:"+format.format(calendar.getTime()));
				dummyTransaction.setTime(format.format(calendar.getTime()));

				dummyTransaction.setIsMerchantTransaction(true);
				dummyTransaction.setIsApprovedByUser(false);
				dummyTransaction.setReviewedByEmployee(false);
				dummyTransaction.setIsRejectedByUser(false);
				dummyTransactionDAO.createTransaction(dummyTransaction);

				return "notifyUser";
				
			} else {
				return "redirect:/";
			}
		} else {
			return "noAccess";
		}

	}

	@RequestMapping(value = "/updateMerchantProfile")
	public String updateMerchantProfile(HttpServletRequest request, Model model) {
		
		if (!SessionUtils.isAdminLogin(request)
				&& !SessionUtils.isEmployeeLogin(request)
				&& !SessionUtils.isUserLogin(request)) {
		    
			Merchantinfo merchant = merchantDAO.getMerchant();
			if(merchant != null && request.getSession().getAttribute("merchant")!=null) {
				model.addAttribute("merchant", merchant);
				
				return "editMerchantProfile";
				
			} else {
				return "redirect:/";
			}
		} else {
			return "noAccess";
		}
	}

	@RequestMapping(value = "/saveMerchantProfile")
	public String saveMerchantProfile(
			@ModelAttribute("merchant") Merchantinfo merchant,
			HttpServletRequest request, Model model) {
		
		if (!SessionUtils.isAdminLogin(request)
				&& !SessionUtils.isEmployeeLogin(request)
				&& !SessionUtils.isUserLogin(request)) {
			
			Merchantinfo merchantFromSession = (Merchantinfo) request
					.getSession().getAttribute("merchant");
			if(merchant != null && merchantFromSession != null) {
				if(!merchant.getMerchantName().isEmpty() && merchant.getMerchantName()!= null
						&& !merchant.getMerchantIncomeTaxNumber().isEmpty() && merchant.getMerchantIncomeTaxNumber()!= null
						&& !merchant.getMerchantAddress().isEmpty() && merchant.getMerchantAddress()!= null
						&& !merchant.getMerchantEmail().isEmpty() && merchant.getMerchantEmail()!=null) {
					merchantFromSession.setMerchantAddress(merchant.getMerchantAddress());					
					merchantFromSession.setMerchantIncomeTaxNumber(merchant.getMerchantIncomeTaxNumber());
					merchantFromSession.setMerchantName(merchant.getMerchantName());
					merchantFromSession.setMerchantName(merchant.getMerchantName());
				} else {
					model.addAttribute("failure","No Field can be left empty");
					model.addAttribute("merchant",merchantFromSession);
					return "editMerchantProfile";
				}
				String emailID = merchant.getMerchantEmail();
				if((merchantDAO.isExistingMerchantEmail(emailID) || 
						adminDAO.isExistingAdminEmail(emailID) ||
						employeeDAO.isExistingEmployeeEmail(emailID) ||
						userDAO.isExistingUserEmailId(emailID))
						&& !merchantFromSession.getMerchantEmail().equalsIgnoreCase(merchant.getMerchantEmail())) {
					model.addAttribute("merchant",merchantFromSession);
					System.out.println("Duplicate email add of merchant");
					model.addAttribute("failure","We already have a registered user with this Email ID");
					return "editMerchantProfile";
				}
				merchantFromSession.setMerchantEmail(merchant.getMerchantEmail());

				
				merchantDAO.updateProfile(merchantFromSession);
				
				AccountSummary summary = accountSummaryDAO
						.getSummary(merchantFromSession.getMerchantAccountNumber());
				model.addAttribute("merchant", merchantFromSession);
				model.addAttribute("balance", summary.getBalance());
				model.addAttribute("success", "Profile Updated");
				
				return "merchant";
				
			} else {
				return "redirect:/";
			}
		} else {
			return "noAccess";
		}
	}

	@RequestMapping(value="merchantSelfDebit")
	public String  merchantSelfDebit(HttpServletRequest request, Model model) {
		if (!SessionUtils.isAdminLogin(request)
				&& !SessionUtils.isEmployeeLogin(request)
				&& !SessionUtils.isUserLogin(request)) {
			Merchantinfo merchant = (Merchantinfo)request.getSession()
					.getAttribute("merchant");
			if(merchant != null) {
				model.addAttribute("dummyTransaction", new Dummytransaction());
				model.addAttribute("merchant", merchant);
				return "merchantSelfDebit";
				
			} else {
				return "redirect:/";
			}
		} else {
			return "noAccess";
		}
	}
	
	
	@RequestMapping(value = "/merchantSelfDebitUpdateSummary")
	public String debitBalanceFromMerchant(
			@ModelAttribute("dummyTransaction") Dummytransaction dummyTransaction,
			Model model, HttpServletRequest request) {
		if (!SessionUtils.isAdminLogin(request)
				&& !SessionUtils.isEmployeeLogin(request)
				&& !SessionUtils.isUserLogin(request)) {
			Merchantinfo merchant = (Merchantinfo) request.getSession().getAttribute("merchant");
			if(merchant != null) {
				if(dummyTransaction.getAmount() <= 0) {
					model.addAttribute("failure", "Please enter a non zero non negative amount");
					model.addAttribute("dummyTransaction", new Dummytransaction());
					model.addAttribute("merchant", merchant);
					return "merchantSelfDebit";					
				}

				AccountSummary summary = accountSummaryDAO.getSummary(merchant.getMerchantAccountNumber());
				if (summary.getBalance() < dummyTransaction.getAmount()) {
					model.addAttribute("failure",
							"Please enter an amount less than your account balance");
					return "merchantSelfDebit";
				}
				summary.setBalance(summary.getBalance()
						- dummyTransaction.getAmount());
				System.out.println("accnt number and balance"
						+ summary.getAccountNumber() + " " + summary.getBalance());
//				dummyTransaction.setAccountSummaryByUserReceiving(summary);
				dummyTransaction.setAccountSummaryByUserSending(summary);
				String[] listOfEmployees = employeeDAO.getAllEmployeeIds();
				if(listOfEmployees.length != 0) {

				int randomNumber = new Random().nextInt(listOfEmployees.length);
				System.out.println("Random empl:" + listOfEmployees[randomNumber]);
				Employee employee = employeeDAO
						.getEmployeeById(listOfEmployees[randomNumber]);
				System.out.println("employee email:"
						+ employee.getEmployeeEmailId());
				dummyTransaction.setEmployee(employee);
				} else {
					dummyTransaction.setAdminReviewing(true);
				}
				dummyTransaction.setReviewedByEmployee(false);
				if (dummyTransaction.getAmount() >= 1000) {
					dummyTransaction.setCritical(true);
				}

				// Setting date of transaction
				Calendar calendar = Calendar.getInstance();
				DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				System.out.println("Date:"+format.format(calendar.getTime()));
				dummyTransaction.setTime(format.format(calendar.getTime()));
				dummyTransaction.setReviewedByEmployee(false);
				dummyTransactionDAO.createTransaction(dummyTransaction);
				accountSummaryDAO.updateAccountSummary(summary);
				model.addAttribute("merchant", merchant);
				return "successfulMerchantPayment";
				
			} else {
				return "redirect:/";
			}
		} else {
			return "noAccess";
		}
	}

	
	@RequestMapping(value="merchantSelfCredit")
	public String  merchantSelfCredit(HttpServletRequest request, Model model) {
		if (!SessionUtils.isAdminLogin(request)
				&& !SessionUtils.isEmployeeLogin(request)
				&& !SessionUtils.isUserLogin(request)) {
			Merchantinfo merchant = (Merchantinfo)request.getSession()
					.getAttribute("merchant");
			if(merchant != null) {
				model.addAttribute("dummyTransaction", new Dummytransaction());
				model.addAttribute("merchant", merchant);
				return "merchantSelfCredit";				
			} else {
				return "redirect:/";
			}
		} else {
			return "noAccess";
		}
	}

	
	@RequestMapping(value = "/merchantSelfCreditUpdateSummary")
	public String merchantSelfCreditUpdateSummary(
			@ModelAttribute("dummyTransaction") Dummytransaction dummyTransaction,
			Model model, HttpServletRequest request) {
		if (!SessionUtils.isAdminLogin(request)
				&& !SessionUtils.isEmployeeLogin(request)
				&& !SessionUtils.isUserLogin(request)) {

			Merchantinfo merchant = (Merchantinfo) request.getSession().getAttribute("merchant");
			if(merchant != null) {
				if(dummyTransaction.getAmount() <= 0) {
				
				model.addAttribute("failure", "Please enter a non zero non negative amount");
				model.addAttribute("dummyTransaction", new Dummytransaction());
				model.addAttribute("merchant", merchant);
				return "merchantSelfCredit";					
			}
				
				AccountSummary summary = accountSummaryDAO.getSummary(merchant.getMerchantAccountNumber());
				summary.setBalance(summary.getBalance()
						+ dummyTransaction.getAmount());
				System.out.println("accnt number and balance"
						+ summary.getAccountNumber() + " " + summary.getBalance());
				dummyTransaction.setAccountSummaryByUserReceiving(summary);
//				dummyTransaction.setAccountSummaryByUserSending(summary);
				String[] listOfEmployees = employeeDAO.getAllEmployeeIds();
				if(listOfEmployees.length != 0) {			
				int randomNumber = new Random().nextInt(listOfEmployees.length);
				System.out.println("Random empl:" + listOfEmployees[randomNumber]);
				Employee employee = employeeDAO
						.getEmployeeById(listOfEmployees[randomNumber]);
				System.out.println("employee email:"
						+ employee.getEmployeeEmailId());
				dummyTransaction.setEmployee(employee);
				} else {
					dummyTransaction.setAdminReviewing(true);
				}
				dummyTransaction.setReviewedByEmployee(false);
				if (dummyTransaction.getAmount() >= 1000) {
					dummyTransaction.setCritical(true);
				}

				// Setting date of transaction
				Calendar calendar = Calendar.getInstance();
				DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				System.out.println("Date:"+format.format(calendar.getTime()));
				dummyTransaction.setTime(format.format(calendar.getTime()));
				dummyTransactionDAO.createTransaction(dummyTransaction);
				accountSummaryDAO.updateAccountSummary(summary);
				model.addAttribute("merchant", merchant);
				return "successfulMerchantPayment";
				
			} else {
				return "redirect:/";
			}
		} else {
			return "noAccess";
		}
	}

	@RequestMapping(value = "/merchantFundsTransfer")
	public String navigateToMerchantTransferPage(HttpServletRequest request, Model model) {
		if (!SessionUtils.isAdminLogin(request)
				&& !SessionUtils.isEmployeeLogin(request)
				&& !SessionUtils.isUserLogin(request)) {
			Merchantinfo merchant = (Merchantinfo)request.getSession()
					.getAttribute("merchant");
			if(merchant != null) {
				model.addAttribute("dummyTransaction", new Dummytransaction());
				model.addAttribute("merchant", merchant);
				return "merchantFundsTransfer";				
			} else {
				return "redirect:/";
			}
		} else {
			return "noAccess";
		}
	}

	
	@RequestMapping(value = "/settleBalanceAfterMerchantTransfer")
	public String settleBalanceAfterMerchantTransfer(
			HttpServletRequest request,
			Model model,
			
			@ModelAttribute("dummyTransaction") Dummytransaction dummyTransaction) {
		if (!SessionUtils.isAdminLogin(request)
				&& !SessionUtils.isEmployeeLogin(request)
				&& !SessionUtils.isUserLogin(request)) {
			Merchantinfo merchant = (Merchantinfo) request.getSession().getAttribute("merchant");
			if(merchant != null) {
				if(dummyTransaction.getAmount() <= 0) {
					model.addAttribute("failure", "Please enter a non zero non negative amount");
					model.addAttribute("dummyTransaction", new Dummytransaction());
					model.addAttribute("merchant", merchant);
					return "merchantFundsTransfer";					
				}
				if(merchant.getMerchantAccountNumber() == dummyTransaction.getAccountSummaryByUserReceiving().getAccountNumber()) {
					model.addAttribute("failure", "Please use the credit functionality to make transfer to your own account");
					model.addAttribute("dummyTransaction", new Dummytransaction());
					model.addAttribute("merchant", merchant);
					return "merchantFundsTransfer";					
				}
				AccountSummary senderSummary = accountSummaryDAO.getSummary(merchant.getMerchantAccountNumber());
				if (senderSummary.getBalance() < dummyTransaction.getAmount()) {
					model.addAttribute("failure",
							"Please enter an amount less than your account balance");
					return "merchantFundsTransfer";
				}
				senderSummary.setBalance(senderSummary.getBalance()
						- dummyTransaction.getAmount());
				System.out.println("accnt number and balance"
						+ senderSummary.getAccountNumber() + " "
						+ senderSummary.getBalance());
				
				AccountSummary receiverSummary = null;
				try {
					 receiverSummary = accountSummaryDAO
							.getSummary(dummyTransaction
									.getAccountSummaryByUserReceiving()
									.getAccountNumber());					
				} catch(InvalidParameterException invalidEx) {
					model.addAttribute("failure", "Please enter a valid account number");
					model.addAttribute("dummyTransaction", new Dummytransaction());
					model.addAttribute("merchant", merchant);
					return "merchantFundsTransfer";										

				}
				receiverSummary.setBalance(receiverSummary.getBalance()
						+ dummyTransaction.getAmount());

				dummyTransaction.setAccountSummaryByUserReceiving(receiverSummary);
				dummyTransaction.setAccountSummaryByUserSending(senderSummary);
				String[] listOfEmployees = employeeDAO.getAllEmployeeIds();
				if(listOfEmployees.length != 0) {
				
				int randomNumber = new Random().nextInt(listOfEmployees.length);
				System.out.println("Random empl:" + listOfEmployees[randomNumber]);
				Employee employee = employeeDAO
						.getEmployeeById(listOfEmployees[randomNumber]);
				System.out.println("employee email:"
						+ employee.getEmployeeEmailId());
				dummyTransaction.setEmployee(employee);
				} else {
					dummyTransaction.setAdminReviewing(true);
				}
				dummyTransaction.setReviewedByEmployee(false);
				if (dummyTransaction.getAmount() >= 1000) {
					dummyTransaction.setCritical(true);
				}

				// Setting date of transaction
				Calendar calendar = Calendar.getInstance();
				DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				System.out.println("Date:"+format.format(calendar.getTime()));
				dummyTransaction.setTime(format.format(calendar.getTime()));
				dummyTransactionDAO.createTransaction(dummyTransaction);
				accountSummaryDAO.updateAccountSummary(senderSummary);
				accountSummaryDAO.updateAccountSummary(receiverSummary);
				model.addAttribute("merchant", merchant);
				return "successfulMerchantPayment";
	
			} else {
				return "redirect:/";
			}
		} else {
			return "noAccess";
		}
	}
	
	
	
	@RequestMapping(value="/merchantTransactions")
	public String getMerchantTransactions(HttpServletRequest request, Model model) {
		Merchantinfo merchant = (Merchantinfo)request.getSession().getAttribute("merchant");
		if (!SessionUtils.isAdminLogin(request)
				&& !SessionUtils.isEmployeeLogin(request)
				&& !SessionUtils.isUserLogin(request)) {

		if(merchant!=null) {
			
		
		AccountSummary summary = accountSummaryDAO.getSummary(merchant.getMerchantAccountNumber());
		List<Dummytransaction> dummyTransactionsTable = dummyTransactionDAO
				.getAllTransactionsForUser(merchant
						.getMerchantAccountNumber());
		if(dummyTransactionsTable!= null && dummyTransactionsTable.size() != 0) {
				List<TransactionWithbalance> listOfTransactionsWithbalance = new ArrayList<TransactionWithbalance>();
				int currentbalance = summary.getBalance();
				for(Dummytransaction dummy: dummyTransactionsTable) {
					TransactionWithbalance tWithbalance = new TransactionWithbalance();
						if (dummy.getAccountSummaryByUserReceiving() != null && dummy.getAccountSummaryByUserReceiving()
								.getAccountNumber() == merchant
								.getMerchantAccountNumber()) {
							tWithbalance.setCredit(dummy.getAmount());
							tWithbalance.setBalance(currentbalance);
							currentbalance-=dummy.getAmount();
							tWithbalance.setDate(dummy.getTime());
							System.out.println("Is reviewed:"+dummy.getReviewedByEmployee());
							tWithbalance.setBeingReviewed(dummy.getReviewedByEmployee());
							
					} else if (dummy
							.getAccountSummaryByUserSending() != null
							&& dummy.getAccountSummaryByUserSending()
									.getAccountNumber() == merchant
									.getMerchantAccountNumber()) {

							tWithbalance.setDebit(dummy.getAmount());
							tWithbalance.setBalance(currentbalance);
							currentbalance+=dummy.getAmount();
							tWithbalance.setDate(dummy.getTime());
							tWithbalance.setBeingReviewed(dummy.getReviewedByEmployee());
						}

					listOfTransactionsWithbalance.add(tWithbalance);
				}
				model.addAttribute("transactionsTable", listOfTransactionsWithbalance);							
				return "merchantTransactions";
			
		} else {
			model.addAttribute("noTransactions", "There are currently no transactions");
			return "merchantTransactions";
		}
		} 
		return "redirect:/";
		}
		return "noAccess";

	}

	
	
	
//***************************************End of Merchant**************************************************	
	@RequestMapping(value = "/passwordReset")
	public String renderPasswordResetPage(Model model, HttpServletRequest request) {
		if (!SessionUtils.isAdminLogin(request)
				&& !SessionUtils.isEmployeeLogin(request)
				&& !SessionUtils.isUserLogin(request) && !SessionUtils.isMerchantLogin(request)) {

		model.addAttribute("userRequestingPasswordReset", new User());
		return "passwordReset";
		} else {
			return "redirect:/";
		}
	}

	@RequestMapping(value = "/sendOTP")
	public String sendOTP(@ModelAttribute("userRequestingPasswordReset") User user, Model model, HttpServletRequest request) {
		if (!SessionUtils.isAdminLogin(request)
				&& !SessionUtils.isEmployeeLogin(request)
				&& !SessionUtils.isUserLogin(request) && !SessionUtils.isMerchantLogin(request)) {

		String existingUser = userDAO.isUserExists(user);
		if(!existingUser.equalsIgnoreCase("existingEmailAddress")) {
			model.addAttribute("failure", "The user is not present in our records");
			return "passwordReset";
		} else {
			User userFromDB = userDAO.getUserByIdOrEmail(user.getEmailAddress());
			if(user.getSecurityA1().equalsIgnoreCase(userFromDB.getSecurityA1())
					&& user.getSecurityA2().equalsIgnoreCase(userFromDB.getSecurityA2())) {
				Login login = loginDAO.getLoginInformation(userFromDB.getUsername());
				setUpEmailAccount(user,login);
				loginDAO.updateLoginInformation(login);
				request.getSession().setAttribute("login", login);
				model.addAttribute("userForEnteringOTP",login);
				return "reEnterOTP";				
			} else {
				model.addAttribute("failure", "The user is not present in our records");
				return "passwordReset";
			}
		}
		} else {
			return "redirect:/";
		}
	}
	
	@RequestMapping(value="/OTPConfirmation")
	public String confirmOTP(@ModelAttribute("userForEnteringOTP") Login login, HttpServletRequest request, Model model) {
		if (!SessionUtils.isAdminLogin(request)
				&& !SessionUtils.isEmployeeLogin(request)
				&& !SessionUtils.isUserLogin(request) && !SessionUtils.isMerchantLogin(request)) {

		Login loginFromSession = (Login) request.getSession().getAttribute("login");
		Login loginFromDB = loginDAO.getLoginInformation(loginFromSession.getLoginId());
		if(loginFromDB!=null)
		{
			if(loginFromDB.getOtp().equalsIgnoreCase(login.getOtp())) {
				model.addAttribute("newLoginPassword", new Login());
				return "enterNewPassword";			
			} else {
				model.addAttribute("failure","The entered one time password is incorrect");
				return "reEnterOTP";
			}
		}
		}
		return "redirect:/";
	}
	
	@RequestMapping(value="/saveNewPassword") 
		public String setNewPassword(@ModelAttribute("newLoginPassword") Login login, HttpServletRequest request, Model model) {
		if (!SessionUtils.isAdminLogin(request)
				&& !SessionUtils.isEmployeeLogin(request)
				&& !SessionUtils.isUserLogin(request) && !SessionUtils.isMerchantLogin(request)) {

		Login loginFromSession = (Login) request.getSession().getAttribute("login");
			Login loginFromDB = loginDAO.getLoginInformation(loginFromSession.getLoginId());
			System.out.println("The username:"+loginFromSession.getLoginId());
			System.out.println("The password as entered:"+login.getPassword());
			System.out.println("From DB:"+loginFromDB.getLoginId());
			loginFromDB.setPassword(login.getPassword());
			loginDAO.updateLoginInformation(loginFromDB);
			model.addAttribute("success","Password successfully updated");
			return "passwordSuccessfullyUpdated";
		}
		return "redirect:/";
	}

}
