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
<c:if test="${not empty duplicateuserId}">
   <h4>${duplicateuserId}</h4>   
</c:if>
<c:if test="${not empty duplicateEmail}">
   <h4>${duplicateEmail}</h4>   
</c:if>

<c:if test="${not empty failure}">
   <h4>${failure}</h4>   
</c:if>
<form:form  modelAttribute="user" action="${pageContext.request.contextPath}/saveNewUserDetails">
<table class="table-condensed">
<tr>
<th><h1>New User Registration</h1></th>
</tr>
<tr>
<td>
<b>First name</b>
</td>
<td><form:input class= "form-control" path="firstName" /></td>
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
<b>SSN</b>
</td>
<td><form:input class= "form-control" path="ssn"/></td>
</tr>

<tr>
<td>
<b>Date Of Birth(mm/dd/yyyy)</b>
</td>
<td><form:input class= "form-control" path="dob" type="date" min="1940-12-12" max="2015-01-01" /></td>
</tr>

<tr>
<td>
<b>Gender</b>
</td>
<td>
<form:radiobutton path="gender" value="M"/>Male
<form:radiobutton path="gender" value="F"/>Female
</td>
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
<td>
<b>UserName</b>
</td>
<td><form:input class= "form-control" path="username"/></td>
</tr>

</table>

<table class="table-condensed">
<colgroup> 
<col width="210"/>
<col width="22"/>
</colgroup>
<tr>
<td/>
<td><input type="submit" value="Submit" class="btn btn-default" name="Save"/></td>
<td><a href = "${pageContext.request.contextPath}/">Proceed to Login Page</a></td>
</tr>
</table>
</form:form>
</div>
</body>

</html>