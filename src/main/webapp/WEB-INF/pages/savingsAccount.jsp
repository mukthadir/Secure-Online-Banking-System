<%@include file="tagImports.jsp" %>  
<html>
<head>	
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap.css"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" />
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap-theme.css" />
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap-theme.min.css" />
</head>	
<body>
<%@include file="userNavBar.jsp" %>

<div class="container">
<form:form modelAttribute="person" action="${pageContext.request.contextPath}/saveNewUserDetails">
<table class="table-condensed">
<tr>
<th><h1>Savings Account</h1></th>
</tr>
<tr>
<td>
<b>Account Number</b>
</td>
<td>${person.savingsAccount.accountNumber}</td>
</tr>

<tr>
<td>
<b>Available Balance</b>
</td>
<td>${person.savingsAccount.balance}</td>
</tr>

</table>

</form:form>
</div>
</body>

</html>