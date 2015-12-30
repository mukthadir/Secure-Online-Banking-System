<%@include file="tagImports.jsp" %>

<html>
<head>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap.css"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" />
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap-theme.css" />
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap-theme.min.css" />
</head>	
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
};
</style>
<body>
<div class="navbar navbar-default">
  <div class="container">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">Employee</a>
    </div>
    <ul class="nav navbar-nav">
      <li class="active"><a href="${pageContext.request.contextPath}/emp/home">Home</a></li>
      <li><a href="#" data-hover="dropdown" class="dropdown-toggle">Transactions<b class="caret"></b></a>
            <ul class="dropdown-menu">
<li><a href="${pageContext.request.contextPath}/transaction/create">Create</a></li>
            	<li><a href="${pageContext.request.contextPath}/transaction/modify">Modify</a></li>
            	<li><a href="${pageContext.request.contextPath}/transaction/delete">Delete</a></li> </ul>
      </li>
      <li><a href="#" data-hover="dropdown" class="dropdown-toggle">User Requests<b class="caret"></b></a>
            <ul class="dropdown-menu">
            	<li><a href="${pageContext.request.contextPath}/approvereq">Approve Requests</a></li>

            
            </ul>
      </li>
    </ul>
    <p class="navbar-text pull-right">${email} | <a href="${pageContext.request.contextPath}/">Log Out</a></p>
  </div>
</div>

<div id="main" style="width:100%;overflow:hidden">
<h3 style="text-align:center;">Approve Requests</h3>
<form:form>
	<table style="width:80%;display: table-row-group;
vertical-align: middle;
border-color: inherit;
border-collapse: collapse;
margin-left:10%">
  		<tr>
    		<th>User Name</th>		
    		<th>New Email</th>
    		<th>Approve</th>
  		</tr>
 			<c:if test="${not empty critical}">
  			<c:set var="count" value="0" scope="page" />
			<c:forEach items="${critical}" var="a">
			<c:set var="count" value="${count + 1}" scope="page"/>
  		<tr>
  			<td><input  value="${a.getUsername()}" readonly="true" class="input"/></td>
    		<td><input  value="${a.getEmail()}" readonly="true" class="input"/></td>
    				<td><select name="values">
  					<option value="Yes">Yes</option>
				<select></td>
 		</tr>
		</c:forEach>
		</c:if>
	</table>
				<input id="approve" type="submit" value="Approve" class="btn btn-default" name="approve"/>
	</form:form>
</div>

</body>

</html>