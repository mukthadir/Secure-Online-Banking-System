<%@include file="tagImports.jsp" %>  
<%@include file="merchantNavBar.jsp" %>
<form:form modelAttribute="dummyTransaction" action="${pageContext.request.contextPath}/settleBalanceAfterMerchantPayment"> 
<table class="table-condensed">
<tr>
<td>Amount to be settled:</td>
<td><form:input class= "form-control" path="amount"/></td>
</tr>
<tr>
<td>Customer account number: </td>
<td><form:input class= "form-control" path="accountSummaryByUserSending.accountNumber"/></td>
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
