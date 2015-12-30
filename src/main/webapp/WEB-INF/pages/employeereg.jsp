<%@include file="tagImports.jsp" %>
<%@include file="imageLogo.jsp" %>
<html>
<head>	
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap.css"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" />
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap-theme.css" />
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap-theme.min.css" />
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/registration.css" />
	<script src="${pageContext.request.contextPath}/resources/js/register.js"></script>
</head>
<body>
	<div class="container">
		<form action="${pageContext.request.contextPath}/empregistration" method="POST">
				<table class="table-condensed">
					<tr>
						<th><h1></h1></th>
					</tr>
					<tr> 
						<td><b>Username:</b></td>
						<td><input class= "form-control" name="username" id="username" required="required"/></td>
						<td>
							<img src="${pageContext.request.contextPath}/resources/images/image.jpg" id="name" style="height:22px;width:22px"/>
							<div id="name-rules">Accepted Characters: A-Z and a-z</div>
							<div id="username-required">This field is mandatory</div></td>
					</tr>
					<tr> 
						<td><b>Email:</b></td>
						<td><input class= "form-control" name="email" id="email" required="required"/></td>
						<td>
							<img src="${pageContext.request.contextPath}/resources/images/image.jpg" id="email" style="height:22px;width:22px"/>
							<div id="email-rules">Accepted Characters: A-Z and a-z</div>
							<div id="email-required">This field is mandatory</div></td>
					</tr>
					<tr> 
						<td><b>OTP:</b></td>
						<td><input class= "form-control" name="OTP" id="OTP" required="required"/></td>
						<td>
							<img src="${pageContext.request.contextPath}/resources/images/image.jpg" id="OTP" style="height:22px;width:22px"/>
							
							<div id="OTP-required">This field is mandatory</div></td>
					</tr>
					<tr> 
						<td><b>Password:</b></td>
						<td><input type="password" class= "form-control" name="password" id="password" required="required"/></td>
						<td>
							<img src="${pageContext.request.contextPath}/resources/images/image.jpg" id="password" style="height:22px;width:22px"/>
							<div id="password-rules">Accepted Characters: A-Z and a-z</div>
							<div id="password-required">This field is mandatory</div></td>
					</tr>
					<tr> 
						<td><b>Confirm Password:</b></td>
						<td><input type="password" class= "form-control" name="confirm_password" id="confirm_password" required="required"/></td>
						<td>
							<img src="${pageContext.request.contextPath}/resources/images/image.jpg" id="confirm_password" style="height:22px;width:22px"/>
							<div id="password-rules">Accepted Characters: A-Z and a-z</div>
							<div id="password-required">This field is mandatory</div></td>
					</tr>
										
					<tr>
						<td>
							<b>Security Question 1: What is your grandfather's occupation?</b>
						</td>
						<td><input class= "form-control" name="securityA1" id="security1"/></td>
						
						<td>
						<img src="${pageContext.request.contextPath}/resources/images/image.jpg" style="height:22px;width:22px" id="security1"/>
						<div id="security-rule1">Allowed: A_Za-z</div>
						<div id="sec1-required">This field is mandatory</div>
						</td>
					</tr>
					<tr>
						<td>
							<b>Security Question 2: What is you mother's maiden name?</b>
						</td>
						<td><input class= "form-control" name="securityA2"/></td>
						<td>
							<img src="${pageContext.request.contextPath}/resources/images/image.jpg" id="security2" style="height:22px;width:22px" id="security2"/>
							<div id="security-rule2">Allowed: A_Za-z</div>
							<div id="sec2-required">This field is mandatory</div>
						</td>
					</tr>
				</table>
				<input type="submit" id="submit" value="submit" name="submit">
		</form>
	</div>
</body>
</html>