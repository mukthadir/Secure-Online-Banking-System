<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="tagImports.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Modify External User</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap.css"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" />
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap-theme.css" />
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap-theme.min.css" />
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
}</style>

</head>
<body>
	<div class="navbar navbar-default">
  <div class="container">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">Admin</a>
    </div>
       <ul class="nav navbar-nav">
      <li class="active"><a href="${pageContext.request.contextPath}/admin/home">Home</a></li>
      <li><a href="#" data-hover="dropdown" class="dropdown-toggle">Internal Users<b class="caret"></b></a>
            <ul class="dropdown-menu">
            	<li><a href="${pageContext.request.contextPath}/addemp">Add</a></li>
            	<li><a href="${pageContext.request.contextPath}/modifyemp">Modify</a></li>
            	<li><a href="${pageContext.request.contextPath}/delemp">Delete</a></li>
            </ul>
      </li>
      <li><a href="#" data-hover="dropdown" class="dropdown-toggle">External Users<b class="caret"></b></a>
            <ul class="dropdown-menu">
            	<li><a href="${pageContext.request.contextPath}/adduser">Add</a></li>
            	<li><a href="${pageContext.request.contextPath}/modifyuser">Modify</a></li>
            	<li><a href="${pageContext.request.contextPath}/deluser">Delete</a></li>
            </ul>
      </li>
      <li><a id="PII" href="${pageContext.request.contextPath}/pii">PII Access</a></li>
      <li><a id="Log" href="${pageContext.request.contextPath}/syslog">System Log</a></li>
    </ul>
    <p class="navbar-text pull-right">${email}| <a href="${pageContext.request.contextPath}/">Log Out</a></p>
  </div>
</div>

	<img src="${pageContext.request.contextPath}/resources/images/bankLogo.png"/>
	<h3 style="text-align:center;">Request PII Access before approving user requests. You can requset PII access from the link present on the Menu Bar.</h3>
	<h3 style="text-align:center;"> Modify External User</h3>
	<form>
	<div id="main">
	<div id="left">
	<table id="table" style="width:80%;display: table-row-group;
vertical-align: middle;
border-color: inherit;
border-collapse: collapse;margin-left:10%;margin-right:10%;">
  		<tr>
  			<th>User Name</th>
    		<th>New SSN</th>
<!--     		<th>Confirm</th> -->
    		
  		</tr>
  		  	<c:if test="${not empty critical}">
  			<c:set var="count" value="0" scope="page" />
			<c:forEach items="${critical}" var="a">
			<c:set var="count" value="${count + 1}" scope="page"/>
  		<tr>
  			<td><input  value="${a.getUsername()}" readonly="true" class="input"/></td>
    		<td><input  value="xxx-xx-xxxx" readonly="true" class="input"/></td>
    		<!-- <td><input type="checkbox" name="delete" class="checkbox"/></td>
    				<td><select name="values">
  					<option value="Yes">Yes</option>
  					<option value="No">No</option>
				<select></td> -->
 		</tr>
		</c:forEach>
		</c:if>
	</table>
	</div>
	</div>
	</form>
</body>
</html>