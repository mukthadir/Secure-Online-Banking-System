<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="tagImports.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Delete Internal User</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap.css"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" />
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap-theme.css" />
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap-theme.min.css" />
	<style>
tr {
display: table-row;
vertical-align: inherit;
border-color: inherit;
}
th {
ont-size: 1.4em;
text-align: center;
padding:3px;
background-color: #555555;
border:1px solid #555555;
color: white;}
td {
font-size: 1.2em;
border: 1px solid #d4d4d4;
padding: 5px;
text-align:center;
}</style>
</head>
<body>
	<div class="navbar navbar-default">
  <div class="container">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">Admin</a>
    </div>
       <ul class="nav navbar-nav">
      <li class="active"><a href="${pageContext.request.contextPath}/admin/home">Home</a></li>
      <li><a href="#" data-hover="dropdown" class="dropdown-toggle">Internal Users<b class="caret"></b></a>
            <ul class="dropdown-menu">
            	<li><a href="${pageContext.request.contextPath}/addemp">Add</a></li>
            	<li><a href="${pageContext.request.contextPath}/modifyemp">Modify</a></li>
            	<li><a href="${pageContext.request.contextPath}/delemp">Delete</a></li>
            </ul>
      </li>
      <li><a href="#" data-hover="dropdown" class="dropdown-toggle">External Users<b class="caret"></b></a>
            <ul class="dropdown-menu">
            	<li><a href="${pageContext.request.contextPath}/adduser">Add</a></li>
            	<li><a href="${pageContext.request.contextPath}/modifyuser">Modify</a></li>
            	<li><a href="${pageContext.request.contextPath}/deluser">Delete</a></li>
            </ul>
      </li>
      <li><a id="PII" href="${pageContext.request.contextPath}/pii">PII Access</a></li>
      <li><a id="Log" href="${pageContext.request.contextPath}/syslog">System Log</a></li>
    </ul>
    <p class="navbar-text pull-right">${email} | <a href="${pageContext.request.contextPath}/">Log Out</a></p>
  </div>
</div>

	<img src="${pageContext.request.contextPath}/resources/images/bankLogo.png"/>
	<h3 style="text-align:center;">Transactions</h3>
	<table style="width:80%;display: table-row-group;
vertical-align: middle;
border-color: inherit;
border-collapse: collapse;
margin-left:10%">
 <tr>
    		<th>Transaction ID</th>
    		<th>Sender</th>		
    		<th>Receiver</th>
    		<th>Amount</th>
    		<th>Date</th>
  		</tr>
			<%-- <c:forEach items="${transactions}" var="transaction"> --%>
			<c:if test="${not empty criticallist}">
			<c:forEach items="${criticallist}" var="a">
 		
 		  		<tr>
    		<td>${a.getTransactionId()}</td>
    		<td>${a.getAccountSummaryByUserSending().accountNumber}</td>		
			<td>${a.getAccountSummaryByUserReceiving().accountNumber}</td>
    		<td>${a.getAmount()}</td>
    		<td>${a.getTime()}</td>
 		</tr>
			</c:forEach>
			</c:if>
	</table>
			<h3 style="text-align:center;">Profile Updations</h3>
	<table style="width:80%;display: table-row-group;
vertical-align: middle;
border-color: inherit;
border-collapse: collapse;
margin-left:10%">
  		<tr>
    		<th>User Name</th>
    		<th>SSN Updated</th>
    		<th>Email Update</th>
  		</tr>
  				<c:if test="${not empty updations}">
			<c:forEach items="${updations}" var="a">
 		
 		  		<tr>
    		<td>${a.getUsername()}</td>
    		<c:if test="${not empty a.getSsn()}">
    		<td>Yes</td>	
    		</c:if>	
    		<c:if test="${not empty a.getSsn()}">
			<td>No</td>
			</c:if>
 		</tr>
			</c:forEach>
			</c:if>

	</table>
		<h3 style="text-align:center;">System Access Information</h3>
	<table style="width:80%;display: table-row-group;
vertical-align: middle;
border-color: inherit;
border-collapse: collapse;
margin-left:10%">
  		<tr>
    		<th>Account Number</th>
    		<th>Status</th>
  		</tr>
		  	<c:if test="${not empty login}">
  			<c:set var="count" value="0" scope="page" />
			<c:forEach items="${login}" var="a">
			<c:set var="count" value="${count + 1}" scope="page"/>
  		<tr>
    		<td>${a.getLoginId()}</td>
    		<td>${a.getLoginStatus()}</td>
 		</tr>
 		</c:forEach>
 		</c:if>
	</table>
	<h3 style="text-align:center;">Internal Users</h3>
	<table style="width:80%;margin-left:10%;display: table-row-group;
vertical-align: middle;
border-color: inherit;
border-collapse: collapse;">
  		<tr>
    		<th>Name</th>
    		<th>Username</th>	
    		<th>Email Address</th>	
    		<th>Designation</th>
    		<th>Salary</th>
  		</tr>
  		<c:if test="${not empty emptable}">
  			<c:set var="count" value="0" scope="page" />
			<c:forEach items="${emptable}" var="a">
			<c:set var="count" value="${count + 1}" scope="page"/>
  		<tr>
    		<td>${a.getEmployeeName()}</td>
    		<td>${a.getEmployeeUsername()}</td>
    		<td>${a.getEmployeeEmailId()}</td>
    		<td>${a.getEmployeeDesignation()}</td>				
    		<td>${a.getEmployeeSalary()}</td>
 		</tr>
			</c:forEach>
			</c:if>
	</table>
<h3 style="text-align:center;">External Users</h3>
	<table style="width:80%;margin-left:10%;display: table-row-group;
vertical-align: middle;
border-color: inherit;
border-collapse: collapse;">
<tr>
    		<th>Account Number</th>
    		<th>User Name</th>
    		<th>First Name</th>
    		<th>Last Name</th>	
  		</tr>
  		<c:if test="${not empty acctable}">
			<c:forEach items="${acctable}" var="a">
  		<tr>
  			<td>${a.getCheckingsAccountNumber()}</td>
    		<td>${a.getUsername()}</td>
    		<td>${a.getFirstName()}</td>
    		<td>${a.getLastName()}</td>
 		</tr>
 		</c:forEach>
 		</c:if>
	</table>
</body>
</html>