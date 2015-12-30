<%@include file="tagImports.jsp" %>  
<script type="text/javascript">
$( document ).ready(function() {
	$("#merchantId").attr("disabled","disabled");
	$("#merchantAccountNumber").attr("disabled","disabled");
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
<c:if test="${not empty failure}">
   <h4>${failure}</h4>   
</c:if>

<c:if test="${not empty employeeMsg}">
   <h4>${employeeMsg}</h4>   
</c:if>
<form:form action="${pageContext.request.contextPath}/saveMerchantProfile" modelAttribute="merchant">
<table class="table-condensed">

<tr>
<th>Merchant Id:</th>
<td><form:input class= "form-control" id="merchantId" path="merchantId"/></td>
</tr>

<tr>
<th>Merchant Name:</th>
<td><form:input class= "form-control" id="merchantName" path="merchantName"/></td>
</tr>

<tr>
<th>Merchant Address:</th>
<td><form:input class= "form-control" path="merchantAddress"/></td>
</tr>

<tr>
<th>Merchant Income Tax Number:</th>
<td><form:input class= "form-control" path="merchantIncomeTaxNumber"/></td>
</tr>

<tr>
<th>Merchant Email:</th>
<td><form:input class= "form-control" path="merchantEmail"/></td>
</tr>

<tr>
<th>Merchant Account:</th>
<td><form:input class= "form-control" id="merchantAccountNumber" path="merchantAccountNumber"/></td>
</tr>
</table>
<table class="table-condensed">
<colgroup> 
<col width="210"/>
<col width="22"/> 
</colgroup>
<tr>
<td><input type="submit" value="Save" class="btn btn-default" name="Save"/></td>
</tr>
</table>

</form:form>
</div>
</body>
</html>