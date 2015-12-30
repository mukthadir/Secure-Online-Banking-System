<div class="navbar navbar-default">
  <div class="container">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">User</a>
    </div>
    <ul class="nav navbar-nav">
   <li class="dropdown">
        <a href="#" data-toggle="dropdown" data-hover="dropdown" class="dropdown-toggle">Accounts </a>
        <ul class="dropdown-menu"> 
            <li><a href="${pageContext.request.contextPath}/checkingsAccount">Checkings</a></li>
        </ul>
    </li>
    <li><a href="${pageContext.request.contextPath}/editProfile" >Edit profile</a></li>
    <li><a href="${pageContext.request.contextPath}/userCredit" >Credit</a></li>
    <li><a href="${pageContext.request.contextPath}/userDebit" >Debit</a></li>
    <li><a href="${pageContext.request.contextPath}/userTransactions">Transactions</a></li>
    <li class="dropdown">
        <a href="#" data-toggle="dropdown" data-hover="dropdown" class="dropdown-toggle">Transfers </a>
        <ul class="dropdown-menu"> 
             <li><a href="${pageContext.request.contextPath}/transfer">Using their account number</a></li>
        </ul>
    </li>
    </ul>
    
    <p class="navbar-text pull-right">Welcome ${user.firstName} | <a href="${pageContext.request.contextPath}/">Log Out</a></p>
  </div>
</div>
