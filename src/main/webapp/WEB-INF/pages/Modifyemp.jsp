<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="tagImports.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Modify Internal User</title>
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
<script>
$(document).ready(function() {
$(".checkbox").click(function(e)
	{
		var $this=$(this);
		if($this.prop("checked"))
			{
				
				$this.parent().parent().find($(".input")).prop("readonly",false);
			}
		else
			{
				if(!($this.parent().parent().find($(".input")).is('[readonly=false]')))
					$this.parent().parent().find($(".input")).prop("readonly",true);
			}
		})
});
</script>
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
    <p class="navbar-text pull-right">${email} | <a href="${pageContext.request.contextPath}/">Log Out</a></p>
  </div>
</div>

	<img src="${pageContext.request.contextPath}/resources/images/bankLogo.png"/>
	<h3 style="text-align:center;"> Modify Internal User</h3>
	<%-- ${pageContext.request.contextPath}/modifyemp/submit --%>
	<form:form modelAttribute="Employee" action="${pageContext.request.contextPath}/modifyemp/submit">
	<table id="table" style="width:80%;margin-left:10%;display: table-row-group;
vertical-align: middle;
border-color: inherit;
border-collapse: collapse;">
  		<tr>
    		<th>Name</th>
    		<th>Username</th>	
    		<th>Email Address</th>	
    		<th>Designation</th>
    		<th>Salary</th>
    		<th>Enable</th>
    		<th>Confirm</th>
  		</tr>
  		<c:if test="${not empty emptable}">
  			<c:set var="count" value="0" scope="page" />
			<c:forEach items="${emptable}" var="a">
			<c:set var="count" value="${count + 1}" scope="page"/>
  		<tr>
    		<td><form:input readonly="true" value="${a.getEmployeeName()}"  path="employeeName" class="input"/></td>
    		<td><form:input readonly="true" value="${a.getEmployeeUsername()}"  path="employeeUsername" class="input"/></td>
    		<td><form:input readonly="true" value="${a.getEmployeeEmailId()}"  path="employeeEmailId" class="input"/></td>
    		<td><form:select path="employeeDesignation" class="input" readonly="true">
    				<form:option class="input" readonly="true" value="${a.getEmployeeDesignation()}">${a.getEmployeeDesignation()}</form:option>
  					<form:option class="input" readonly="true" value="Manager">Manager</form:option>
  					<form:option class="input" readonly="true" value="Clerk">Clerk</form:option>
  					<form:option class="input" readonly="true" value="Technical Lead">Technical Lead</form:option>
  					<form:option class="input" readonly="true" value="HR">HR</form:option>
				</form:select>			
    		<td><form:input readonly="true" value="${a.getEmployeeSalary()}"  path="employeeSalary" class="input"/></td>
    		<td><input type="checkbox" name="delete" class="checkbox"/></td>
    			<td><select name="values">
  					<option value="Yes">Yes</option>
  					<option value="No">No</option>
				<select></td>
 		</tr>
			</c:forEach>
			</c:if>
	</table>
				<input id="delete" type="submit" value="Submit" style="margin-right:10%;margin-top:5px;float:right;'"class="btn btn-default" name="Submit"/>
	</form:form>
</body>
</html>