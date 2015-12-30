<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="tagImports.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Delete External User</title>
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
	<h3 style="text-align:center;"> Delete External User</h3>
	<form:form modelAttribute="account" action="${pageContext.request.contextPath}/delete/usr/submit">
	<table style="width:100%;display: table-row-group;
vertical-align: middle;
border-color: inherit;
border-collapse: collapse;">
  		<tr>
    		<th>Account Number</th>
    		<th>User Name</th>
    		<th>First Name</th>
    		<th>Last Name</th>	
    		<th>Delete?</th>
  		</tr>
  		<c:if test="${not empty acctable}">
			<c:forEach items="${acctable}" var="a">
  		<tr>
  			<td><form:input  value="${a.getCheckingsAccountNumber()}" readonly="true" path="checkingsAccountNumber" class="input"/></td>
    		<td><form:input  value="${a.getUsername()}" readonly="true" path="username" class="input"/></td>
    		<td><form:input  value="${a.getFirstName()}" readonly="true" path="firstName" class="input"/></td>
    		<td><form:input  value="${a.getLastName()}" readonly="true" path="lastName" class="input"/></td>
    		<td><select name="values">
  					<option value="No">No</option>
  					<option value="Delete">Delete</option>
				<select></td>
 		</tr>
 			</c:forEach>
			</c:if>
	</table>
				<input id="Submit" type="submit" value="Submit" class="btn btn-default" name="submit"/>
	</form:form>
</body>
</html>