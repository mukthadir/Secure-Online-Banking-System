<%@include file="tagImports.jsp" %>  
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script type="text/javascript">
$( document ).ready(function() {
});
</script>
<html>
<body>
<%@include file="userNavBar.jsp" %>
<div class="container">
<c:if test="${success}">
   <h4>${success}</h4>   
</c:if>
<c:if test="${not empty employeeMsg}">
   <h4>${employeeMsg}</h4>   
</c:if>
<c:if test="${not empty failure}">
   <h4>${failure}</h4>   
</c:if>

<table class="table-condensed"> 
<tr>
<th>Checkings Account(${user.checkingsAccountNumber})</th>
<td>The balance is: ${balance}</td>
</tr>
</table>
<c:if test="${not empty transactions}">
<c:if test="${not empty transactions.transactionList[0].transactionId}">
<table class="table-condensed">
<tr>
<th><h3>Merchant Transactions to be approved</h3></th>
<td/>
</tr>
</table>
<form:form modelAttribute="transactions" action="${pageContext.request.contextPath}/approveMerchantTransaction">
<table class="table-condensed" border="1">
<tr>
<td><h4>Merchant's Account number</h4></td>
<td><h4>Amount Requested</h4></td>
<td><h4>Transaction ID</h4></td>
<td><h4>Date</h4></td>
<td><h4>User Approval</h4></td>
<td><h4>User Rejected</h4></td>

</tr>
<c:forEach items="${transactions.transactionList}" var="row" varStatus="status">
<tr>
	<td>${row.accountSummaryByUserReceiving.accountNumber}</td>
	<td>${row.amount}</td>
	<td>${row.transactionId}</td>
	<td>${row.time}</td>
	<td><form:checkbox path="transactionList[${status.index}].isApprovedByUser" /></td>
	<td><form:checkbox path="transactionList[${status.index}].isRejectedByUser" /></td>
	<form:hidden path="transactionList[${status.index}].transactionId"/>
	<form:hidden path="transactionList[${status.index}].accountSummaryByUserReceiving.accountNumber"/>
	<form:hidden path="transactionList[${status.index}].accountSummaryByUserSending.accountNumber"/>
	<form:hidden path="transactionList[${status.index}].amount"/>	
</tr>
</c:forEach>
</table>
<table class="table-condensed">
<colgroup> 
<col width="100"/>
<col width="100"/>
<col width="100"/>
<col width="100"/>
</colgroup>
</table>
<tr>
<td/><td/><td/>
<td><input type="submit" value="Submit" class="btn btn-default" name="Save"/></td>
</tr>

</form:form>
</c:if>
</c:if>
</div>

 
</body>
<table>
</table>
</html>