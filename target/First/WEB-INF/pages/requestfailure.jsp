<%@include file="tagImports.jsp" %>


<html>
	<head>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap.css"/>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" />
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap-theme.css" />
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap-theme.min.css" />
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/registration.css" />
	
	
	
	</head>
	<body>
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
            	<li><a href="${pageContext.request.contextPath}/transaction/delete">Delete</a></li>
            </ul>
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
		<div class="container" style="vertical-align:center">
			<h3>We are unable to process your request. Please try again later.</h3>
			</div>
	</body>
</html>