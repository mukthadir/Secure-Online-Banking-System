<%@include file="tagImports.jsp" %>
<%@include file="imageLogo.jsp" %>
<html>
<head>	
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap.css"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" />
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap-theme.css" />
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap-theme.min.css" />
</head>	
<body>
<div class="container">
<c:if test="${not empty success}">
   <h4>${success}</h4>   
</c:if>
<c:if test="${not empty failure}">
   <h4>${failure}</h4>   
</c:if>
<form:form modelAttribute="userRequestingPasswordReset" action="${pageContext.request.contextPath}/sendOTP">
<table class="table-condensed">

<tr>
<th>
<h2>Password Reset</h2>
</th>
</tr>

<tr>
<td>Please answer any two security questions to regain access to your account</td>
</tr>

<tr>
<td>
Enter registered email address
</td>
<td><form:input path="emailAddress" class="form-control"/></td>
</tr>

<tr>
<td>
<b>What is your grandfather's occupation?(Security Question)</b>
</td>
<td><form:hidden path="securityQ1" value="What is your grandfather's occupation?"/>
<form:input class= "form-control" path="securityA1"/>
</td>
</tr>

<tr>
<td>
<b>What is your mother's maiden name?(Security Question)</b>
</td>
<td><form:hidden path="securityQ2" value="What is your mother's maiden name?"/>
<form:input class= "form-control" path="securityA2"/>
</td>
</tr>

<tr>
<td><input type="submit" value="Submit"/></td>
<td><a href = "${pageContext.request.contextPath}/">Proceed to Login Page</a></td>
</tr>
</table>
</form:form>
</div>