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
<form:form modelAttribute="person" action="${pageContext.request.contextPath}/redirectToHomePage">
<table class="table-condensed">

<tr>
<th>
<h2>Password Reset(OTP)</h2>
</th>
</tr>

<tr>
<td>
Enter OTP
</td>
<td>
<form:input path="OTP"/>
</td>
</tr>

<tr>
<td>
<input type="submit" value="Proceed to login"/>
</td>
<td/>
</tr>
</table>
</form:form>
</div>