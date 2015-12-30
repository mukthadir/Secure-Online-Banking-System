<%@include file="tagImports.jsp" %>
<script type="text/javascript">
$( document ).ready(function() {
	$("#firstName").attr("disabled","disabled");
	$("#lastName").attr("disabled","disabled");
});
</script>
<html>
<head>
<%
	response.setHeader("Pragma", "no-cache");
    response.setHeader("Cache-Control", "no-cache");
    response.setDateHeader("Expires", 0);
%>
</head> 

<head>	
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap.css"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" />
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap-theme.css" />
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap-theme.min.css" />
</head>
<body>
<%@include file="userNavBar.jsp" %>
<div class="container">
<c:if test="${not empty success}">
   <h4>${success}</h4>   
</c:if>
<c:if test="${not empty failure}">
   <h4>${failure}</h4>   
</c:if>
   

<form:form modelAttribute="user" action="${pageContext.request.contextPath}/saveProfileChanges">
<table class="table-condensed">
<tr>
<th><h1>Edit Profile</h1></th>
</tr>
<tr>
<td>
<b>First name</b>
</td>
<td><form:input class= "form-control" id="firstName" path="firstName" /></td>
</tr>

<tr>
<td>
<b>Last name</b>
</td>
<td><form:input class= "form-control" id="lastName" path="lastName" /></td>
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
<td><form:input type="date" class= "form-control" path="dob" min="1940-12-12" max="2015-01-01"/></td>
</tr>

</table> 

<table class="table-condensed">
<colgroup> 
<col width="210"/>
<col width="22"/>  
</colgroup>
<tr>
<td><input type="submit" value="Save" class="btn btn-default" name="Save"/></td>
</tr>
</table>
</form:form>
</div>
</body>

</html>