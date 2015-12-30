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
Payment Successful.
</div>
</body>
</html>