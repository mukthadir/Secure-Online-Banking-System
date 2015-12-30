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

<form:form modelAttribute="newPerson" action="${pageContext.request.contextPath}/saveNewUserDetails">
<table class="table-condensed">
<tr>
<th><h1>New User Registration</h1></th>
</tr>
<tr>
<td>
<b>First name</b>
</td>
<td><form:input class= "form-control" path="firstName"/></td>
</tr>

<tr>
<td>
<b>Last name</b>
</td>
<td><form:input class= "form-control" path="lastName"/></td>
</tr>

<tr>
<td>
<b>Email</b>
</td>
<td><form:input class= "form-control" path="emailAddress"/></td>
</tr>

<tr>
<td>
<b>Address</b>
</td>
<td><form:input class= "form-control" path="address"/></td>
</tr>


<tr>
<td>
<b>SSN</b>
</td>
<td><form:input class= "form-control" path="ssn"/></td>
</tr>

<tr>
<td>
<b>Phone Number</b>
</td>
<td><form:input class= "form-control" path="phoneNumber"/></td>
</tr>

<tr>
<td>
<b>Gender</b>
</td>
<td>
<form:radiobutton name="gender"  value="Male" path="phoneNumber"/>&nbsp;Male
<form:radiobutton name="gender" value="Female" path="phoneNumber"/>&nbsp;Female
</td>
</tr>

<tr>
<td>
<b>Date Of Birth(mm/dd/yyyy)</b>
</td>
<td><form:input class= "form-control" path="dob"/></td>
</tr>

<tr>
<td>
<b>UserName</b>
</td>
<td><form:input class= "form-control" path="userName"/></td>
</tr>

<tr>
<td>
<b>Password</b>
</td>
<td><form:password class= "form-control" path="password"/></td>
</tr>

</table>

<table class="table-condensed">
<colgroup> 
<col width="210"/>
<col width="22"/>
</colgroup>
<tr>
<td><a href = "${pageContext.request.contextPath}/">Proceed to Login Page</a></td>
<td><input type="submit" value="Submit" class="btn btn-default" name="Save"/></td>
</tr>
</table>
</form:form>
</div>
</body>

</html>