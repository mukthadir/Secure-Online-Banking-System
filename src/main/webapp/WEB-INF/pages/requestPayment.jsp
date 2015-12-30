<%@include file="tagImports.jsp" %>  
<%@include file="merchantNavBar.jsp" %>
<form:form modelAttribute="dummyTransaction" action="${pageContext.request.contextPath}/settleBalanceAfterMerchantPayment"> 
<div class="container">
<table class="table-condensed">
<c:if test="${success}">
   <h4>${success}</h4>   
</c:if>
<c:if test="${not empty employeeMsg}">
   <h4>${employeeMsg}</h4>   
</c:if>
<c:if test="${not empty failure}">
   <h4>${failure}</h4>   
</c:if>
<tr>
<td>Amount to be settled:</td>
<td><form:input class= "form-control" path="amount" onkeypress="javascript:return isNumber(event)"/></td>
</tr>
<tr>
<td>Customer account number: </td>
<td><form:input class= "form-control" path="accountSummaryByUserSending.accountNumber" onkeypress="javascript:return isNumber(event)"/></td>
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
</div>
</form:form>
