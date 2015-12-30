<%@include file="tagImports.jsp" %>
<%@include file="imageLogo.jsp" %>
<html>
<head>	
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap.css"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" />
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap-theme.css" />
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap-theme.min.css" />
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/registration.css" />
	<script src="${pageContext.request.contextPath}/resources/js/addemp.js"></script>
	<script>
	function validate()
	{		
		var letters=/^[A-Za-z]+$/;
		if(!document.getElementById("first-name1").value.match(letters))
			{
				document.getElementById("name-rules").style.display="block";
				return false;
			}
		letters=/^\S+@\S+\.\S+$/;
		if(!document.getElementById("email-address").value.match(letters))
			{
				document.getElementById("email-rule").style.display="block";
				return false;
			}
	};
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
    <p class="navbar-text pull-right">${email}| <a href="${pageContext.request.contextPath}/">Log Out</a></p>
  </div>
</div>
	<div class="container">
		<form:form modelAttribute="person" action="${pageContext.request.contextPath}/add/newuser" onSubmit="return validate();">
			<table class="table-condensed">
				<tr>
					<th><h1>New External user</h1></th>
				</tr>
				<tr>
					<td><p id="mandatory">All fields are mandatory</p></td>
				</tr>
				<tr> 
					<td><b>First name</b></td>
					<td><form:input class= "form-control" path="firstName" name="first-name" id="first-name1" required="required"/></td>
					<td>
						<img src="${pageContext.request.contextPath}/resources/images/image.jpg" id="name" style="height:22px;width:22px"/>
						<div id="name-rules">Accepted Characters: A-Z and a-z</div>
						<div id="firstname-required">This field is mandatory</div></td>
					</tr>
				
				<tr>
					<td><b>UserName</b></td>
					<td>
						<form:input class= "form-control" path="username" maxlength="16" id="usernameip" required="required"/></td>
						<td>
							<img src="${pageContext.request.contextPath}/resources/images/image.jpg" style="height:22px;width:22px" id="username"/>
							<div id="user-rule">Allowed: A_Za-z_-</div>
							<div id="username-required">This field is mandatory</div></td>
				</tr>
				
				<tr>
					<td><b>Email</b></td>
					<td><form:input class= "form-control" path="emailAddress" name="email-address" id="email-address" required="required"/></td>
					<td>
						<img src="${pageContext.request.contextPath}/resources/images/image.jpg" style="height:22px;width:22px" id="email"/>
						<div id="email-rule">example@domain.com</div>
						<div id="email-required">This field is mandatory</div></td>
				</tr>
				



			</table>

			<table class="table-condensed">
			<colgroup> 
				<col width="210"/>
				<col width="22"/>
			</colgroup>
			<tr>
				<td><input type="submit" value="Submit" class="btn btn-default" name="Save"></td>
			</tr>
			</table>
		</form:form>
		<%-- <c:if test="${not empty success}">
		<script>alert("${success}")</script></c:if> --%>
	</div>
</body>

</html>