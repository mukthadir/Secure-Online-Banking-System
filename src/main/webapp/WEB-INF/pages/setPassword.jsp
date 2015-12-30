<%@include file="tagImports.jsp" %>
<%@include file="imageLogo.jsp" %>
<script type="text/javascript">

	$(document).ready(function() {
		$("#errorPassword").hide();
		$('#form').on('submit', function() {
			var password1 = $("#password1").val();
			var password2 = $("#password2").val();
			if(/^([a-z0-9]{5,})$/.test(password1)) {
				if (password1 == password2) {
					return true;
				}
				return false;

			} else {
				$("#errorPassword").show();
				return false;
				}
			
		});
		
	});
</script>

<html>
<head>	
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap.css"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" />
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap-theme.css" />
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap-theme.min.css" />
</head>	
<body>
<div class="container">
<div id="errorPassword">The password should contain a lower case letter and a numeral and the length must be atleast 5 digits</div>
<c:if test="${not empty success}">
   <h4>${success}</h4>   
</c:if>

<form:form  id="form" modelAttribute="login" action="${pageContext.request.contextPath}/saveUser">
<table class="table-condensed">
<tr>
<th><h1>Set a Password</h1></th>
</tr>
<tr>
<td>
<b>Password:</b>
</td>
<td><input type="password" id="password1" class= "form-control"/></td>
</tr>
<tr>
<td>
<b>Confirm Password:</b>
</td>
<td><form:password id="password2" class= "form-control" path="password" /></td>
</tr>
</table>

<table class="table-condensed">
<colgroup> 
<col width="210"/>
<col width="22"/>
</colgroup>
<tr>
<td/>
<td><input type="submit" value="Create Account" class="btn btn-default" name="Save" id="save"/></td>
</tr>
</table>

</form:form>
</div>
