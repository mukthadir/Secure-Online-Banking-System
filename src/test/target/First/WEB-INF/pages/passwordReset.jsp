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
<form:form modelAttribute="personRequestingPasswordChange" action="${pageContext.request.contextPath}/passwordResetOTP">
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
Security Question 1
</td>
<td>
<form:select path="securityQuestion1">
<form:option value="placeOfBirth" selected="selected">Which city were you born in</form:option>
<form:option value="placeOfBirth">Which is your favorite color</form:option>
<form:option value="placeOfBirth">What was your grandfather's occupation</form:option>
</form:select>
</td>
</tr>


<tr>
<td>
Answer
</td>
<td><form:input path="securityAnswer1" class="form-control"/></td>
</tr>


<tr>
<td>
Security Question 2
</td>
<td>
<form:select path="securityQuestion2">
<form:option value="placeOfBirth" selected="selected">Which city were you born in</form:option>
<form:option value="placeOfBirth">Which is your favorite color</form:option>
<form:option value="placeOfBirth">What was your grandfather's occupation</form:option>
</form:select>
</td>
</tr>

<tr>
<td>
Answer
</td>
<td><form:input path="securityAnswer2" class="form-control"/></td>
</tr>

<tr>
<td><input type="submit" value="Submit"/></td>
</tr>
</table>
</form:form>
</div>