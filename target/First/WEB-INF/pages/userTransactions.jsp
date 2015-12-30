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
<c:if test="${noTransactions}">
   <h4>${noTransactions}</h4>   
</c:if>
<form:form modelAttribute="transactionsTable" action=""></form:form>
<table class="table-condensed" border="1">
<tr>
<td><h4>Date</h4></td>
<td><h4>Debit</h4></td>
<td><h4>Credit</h4></td>
<td><h4>Balance</h4></td>
<td><h4>Status</h4></td>
</tr>
<c:forEach items="${transactionsTable}" var="trans" varStatus="status">
<tr>
	<td>${trans.date}</td>
	<td>${trans.debit}</td>
	<td>${trans.credit}</td>	
	<td>${trans.balance}</td>
	<td>
	<c:if test="${trans.beingReviewed == true}">Approved</c:if>
	<c:if test="${trans.beingReviewed == false}">In Progress</c:if>	
	</td>
</tr>
</c:forEach>
</table>
</div>

</body>

</html>