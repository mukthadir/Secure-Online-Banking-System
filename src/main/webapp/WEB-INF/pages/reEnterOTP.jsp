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

<form:form  id="form" modelAttribute="userForEnteringOTP" action="${pageContext.request.contextPath}/OTPConfirmation">
<table class="table-condensed">
<tr>
<th><h1>Password Retrieval</h1></th>
</tr>
<tr>
<td>
<b>Enter OTP:</b>
</td>
<td><form:password id="otp" class= "form-control" path="otp" /></td>
</tr>
</table>

<table class="table-condensed">
<colgroup> 
<col width="210"/>
<col width="22"/>
</colgroup>
<tr>
<td/>
<td><input type="submit" value="Submit" class="btn btn-default"/></td>
<td><a href = "${pageContext.request.contextPath}/">Proceed to Login Page</a></td>
</tr>
</table>

</form:form>
</div>
