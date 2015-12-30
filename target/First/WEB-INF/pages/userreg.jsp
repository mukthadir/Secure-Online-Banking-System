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
			<form action="${pageContext.request.contextPath}/userregistration">
				<table class="table-condensed">
					<tr>
						<th><h1></h1></th>
					</tr>
					<tr> 
						<td><b>User Name:</b></td>
						<td><input class= "form-control" path="first_name" name="username" id="first_name" required="required"/></td>
						<td>
							<img src="${pageContext.request.contextPath}/resources/images/image.jpg" id="first_name" style="height:22px;width:22px"/>
							<div id="first_name-rules">Accepted Characters: A-Z and a-z</div>
							<div id="first_name-required">This field is mandatory</div></td>
					</tr>
					<tr> 
						<td><b>First Name:</b></td>
						<td><input class= "form-control" path="first_name" name="first_name" id="first_name" required="required"/></td>
						<td>
							<img src="${pageContext.request.contextPath}/resources/images/image.jpg" id="first_name" style="height:22px;width:22px"/>
							<div id="first_name-rules">Accepted Characters: A-Z and a-z</div>
							<div id="first_name-required">This field is mandatory</div></td>
					</tr>
					<tr> 
						<td><b>Last Name:</b></td>
						<td><input class= "form-control" path="last_name" name="last_name" id="last_name" required="required"/></td>
						<td>
							<img src="${pageContext.request.contextPath}/resources/images/image.jpg" id="last_name" style="height:22px;width:22px"/>
							<div id="last_name-rules">Accepted Characters: A-Z and a-z</div>
							<div id="last_name-required">This field is mandatory</div></td>
					</tr>
					<tr> 
						<td><b>Email:</b></td>
						<td><input class= "form-control" path="email" name="email" id="email" required="required"/></td>
						<td>
							<img src="${pageContext.request.contextPath}/resources/images/image.jpg" id="email" style="height:22px;width:22px"/>
							
							<div id="email-required">This field is mandatory</div></td>
					</tr>
					<tr> 
						<td><b>SSN:</b></td>
						<td><input class= "form-control" path="ssn" name="ssn" id="ssn" required="required"/></td>
						<td>
							<img src="${pageContext.request.contextPath}/resources/images/image.jpg" id="ssn" style="height:22px;width:22px"/>
							
							<div id="ssn-required">This field is mandatory</div></td>
					</tr>
					<tr> 
						<td><b>Date of Birth:</b></td>
						<td><input class= "form-control" path="dob" name="dob" id="dob" required="required"/></td>
						<td>
							<img src="${pageContext.request.contextPath}/resources/images/image.jpg" id="dob" style="height:22px;width:22px"/>
							
							<div id="dob-required">This field is mandatory</div></td>
					</tr>
					<tr>
						<td>
							<b>Gender</b>
						</td>
						<td>
							<input type="radio" path="gender" value="M" required="required" name="gender"/>Male
							<input type="radio" path="gender" value="F" required="required" name="gender"/>Female
						</td>
					</tr>
						<tr>
						<td>
							<b>Security Question 1: What is your grandfather's occupation?</b>
						</td>
						<td><input class= "form-control" path="securityA1" id="security1"/></td>
						
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
						<td><input class= "form-control" path="securityA2"/></td>
						<td>
							<img src="${pageContext.request.contextPath}/resources/images/image.jpg" id="security2" style="height:22px;width:22px" id="security2"/>
							<div id="security-rule2">Allowed: A_Za-z</div>
							<div id="sec2-required">This field is mandatory</div>
						</td>
					</tr>
					<tr> 
						<td><b>OTP:</b></td>
						<td><input class= "form-control" path="OTP" name="OTP" id="OTP" required="required"/></td>
						<td>
							<img src="${pageContext.request.contextPath}/resources/images/image.jpg" id="OTP" style="height:22px;width:22px"/>
							
							<div id="OTP-required">This field is mandatory</div></td>
					</tr>
					<tr> 
						<td><b>Password:</b></td>
						<td><input type="password" class= "form-control" path="password" name="password" id="password" required="required"/></td>
						<td>
							<img src="${pageContext.request.contextPath}/resources/images/image.jpg" id="password" style="height:22px;width:22px"/>
							<div id="password-rules">Accepted Characters: A-Z and a-z</div>
							<div id="password-required">This field is mandatory</div></td>
					</tr>
					<tr> 
						<td><b>Confirm Password:</b></td>
						<td><input type="password" class= "form-control" path="confirm_password" name="confirm_password" id="confirm_password" required="required"/></td>
						<td>
							<img src="${pageContext.request.contextPath}/resources/images/image.jpg" id="confirm_password" style="height:22px;width:22px"/>
							<div id="password-rules">Accepted Characters: A-Z and a-z</div>
							<div id="password-required">This field is mandatory</div></td>
					</tr>
				</table>
				<input type="submit" value="submit" name="submit">
			</form>
		</div>
	</body>	
</html>