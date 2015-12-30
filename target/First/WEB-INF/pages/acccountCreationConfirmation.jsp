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
<td><a href = "${pageContext.request.contextPath}/">Proceed to Login Page</a></td>
</c:if>
</div>
</body>
</html>