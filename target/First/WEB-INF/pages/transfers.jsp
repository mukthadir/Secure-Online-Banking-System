<%@include file="tagImports.jsp" %>  
<script type="text/javascript">
$( document ).ready(function() {
});
</script>
<html> 
<body>
<%@include file="userNavBar.jsp" %>
<div class="container">
<c:if test="${not empty failure}">
   <h2>${failure}</h2>  
</c:if>
<c:if test="${not empty success}">
   <h2>${success}</h2>  
</c:if>

</div>
<div class="container">
<form:form modelAttribute="dummyTransaction" action="${pageContext.request.contextPath}/settleBalanceAfterTransfer">
<table class="table-condensed">
<tr>
<td>Amount to be transferred</td>
<td><form:input class= "form-control" path="amount" onkeypress="javascript:return isNumber(event)"/></td>
</tr>
<tr>
<td>Receiver account number</td>
<td><form:input class= "form-control" path="accountSummaryByUserReceiving.accountNumber" onkeypress="javascript:return isNumber(event)"/></td>
</tr>
</table>

<table class="table-condensed">
<colgroup> 
<col width="210"/>
<col width="22"/>
</colgroup>
<tr>
<td><input type="submit" value="Submit" class="btn btn-default" name="Make Payment"/></td>
</tr>
</table>

</form:form>

</div>