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
<c:if test="${not empty success}">
   <h4>${success}</h4>   
</c:if>
<c:if test="${not empty employeeMsg}">
   <h4>${employeeMsg}</h4>   
</c:if>

<table class="table-condensed">
<tr>
<th>Checkings Account(${merchant.merchantAccountNumber})</th>
<td>The balance is: ${balance}</td>
</tr>
</table>

</div>

 
</body>

</html>