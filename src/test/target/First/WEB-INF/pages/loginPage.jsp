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
<form:form modelAttribute="person" action="${pageContext.request.contextPath}/homePage">
<table class="table-condensed">

<tr>
<td>
<h2>Login</h2>
</td>
<td>&nbsp;</td>
</tr>

<tr>
<td>User Name</td><td/>
<td><form:input class= "form-control" path="userName"/></td>
</tr>

<tr>
<td>Password</td><td/>
<td><form:password class= "form-control" path="password"/></td>
</tr>

<tr class="blank_row">
    <td colspan="3"></td>
</tr>
</table>
<table class="table-condensed">
<colgroup> 
<col width="110"/>
<col width="22"/>
<col width="22"/>
</colgroup>

<tr>
<td/>
<td><input type="submit" value="Submit" class="btn btn-default" name="login"/></td>
<td><input type="submit" value="New User" class="btn btn-default" name="newUser"/></td>
</tr>
<tr>
<td/>
<td colspan="2"><a href="${pageContext.request.contextPath}/passwordReset">Forgot Password?</a></td>
</tr>
</table>
</form:form>
</div>
</body>
</html>