<%@include file="tagImports.jsp" %>  
<html>
<%
    response.setHeader("Cache-Control", "no-cache");
    response.setDateHeader("Expires", 0);
%> 
<body>
<%@include file="userNavBar.jsp" %>
<div class="container">
<c:if test="${not empty failure}">
   <h4>${failure}</h4>   
</c:if>
<form:form modelAttribute="dummyTransaction" action="${pageContext.request.contextPath}/updateBalanceAfterDebit">
<table class="table-condensed">
<tr>
<th>Debit from (${user.checkingsAccountNumber})</th>
<td/>
</tr>
<tr>
<td>
Enter Amount :
</td>
<td>
<form:input path="amount" id="amount" class= "form-control" onkeypress="javascript:return isNumber(event)"/>
</td>
</tr>
<tr>
<td/>
<td>
<input type="Submit" class="btn btn-default" value="Make Payment"/>
</td>
</tr>
</table>

</form:form>
</div>

 
</body>

</html>