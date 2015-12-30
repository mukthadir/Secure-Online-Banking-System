<%@include file="tagImports.jsp" %>  
<script type="text/javascript">
$( document ).ready(function() {
});
</script>
<html>
 
<body>
<div class="navbar navbar-default">
  <div class="container">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">User</a>
    </div>
    <ul class="nav navbar-nav">
   <li class="dropdown">
        <a href="#" data-toggle="dropdown" data-hover="dropdown" class="dropdown-toggle">Accounts </a>
        <ul class="dropdown-menu"> 
            <li><a href="admin.jsp">Savings</a></li>
            <li><a href="#">Checkings</a></li>
        </ul>
    </li>
    <li><a href="#">Bill Pay</a></li>
    <li><a href="#">Edit profile</a></li>
    <li class="dropdown">
        <a href="#" data-toggle="dropdown" data-hover="dropdown" class="dropdown-toggle">Transfers </a>
        <ul class="dropdown-menu"> 
            <li><a href="#">Using their email address</a></li>
            <li><a href="#">Using their account number</a></li>
        </ul>
    </li>
    </ul>
    
    <p class="navbar-text pull-right">Last logged @ ${person.lastLoggedInTimeStamp} | ${person.emailAddress} | <a href="${pageContext.request.contextPath}/">Log Out</a></p>
  </div>
</div>
 
</body>

</html>