<%@include file="tagImports.jsp" %>  
<script type="text/javascript">
$( document ).ready(function() {
});
</script>
<html>
<%
    response.setHeader("Cache-Control", "no-cache");
    response.setDateHeader("Expires", 0);
%> 
<body>
<%@include file="merchantNavBar.jsp" %>
<div class="container">
<c:if test="${not empty failure}">
   <h4>${failure}</h4>   
</c:if>
<form:form modelAttribute="dummyTransaction" action="${pageContext.request.contextPath}/merchantSelfCreditUpdateSummary">
<table class="table-condensed">
<tr>
<th>Debit from (${merchant.merchantAccountNumber})</th>
<td/>
</tr>
<tr>
<td>
Enter Amount :
</td>
<td>
<form:input path="amount" class= "form-control"/>
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