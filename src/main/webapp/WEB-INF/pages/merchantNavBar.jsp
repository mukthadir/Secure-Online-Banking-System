<div class="navbar navbar-default">
  <div class="container">
    <div class="navbar-header">
      <a class="navbar-brand" href="${pageContext.request.contextPath}/merchantCheckings">Merchant</a>
    </div>
    <ul class="nav navbar-nav"> 
   <li class="dropdown">
            <li><a href="${pageContext.request.contextPath}/merchantCheckings">Account Summary</a></li>
            <li><a href="${pageContext.request.contextPath}/renderRequestPayment">Request payment</a></li>
            <li><a href="${pageContext.request.contextPath}/merchantSelfDebit">Debit</a></li>
            <li><a href="${pageContext.request.contextPath}/merchantSelfCredit">Credit</a></li>
            <li><a href="${pageContext.request.contextPath}/merchantFundsTransfer">Transfer</a></li>
            <li><a href="${pageContext.request.contextPath}/updateMerchantProfile">Edit profile</a></li>
            <li><a href="${pageContext.request.contextPath}/merchantTransactions">Transactions</a></li>
    </li>
    </ul>
    
    <p class="navbar-text pull-right"> ${merchant.merchantEmail} | <a href="${pageContext.request.contextPath}/">Log Out</a></p>
  </div>
</div>
