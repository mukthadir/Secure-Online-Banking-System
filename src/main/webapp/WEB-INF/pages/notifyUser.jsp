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
<h4>The user has been notified of the payment. Please wait until its acknowledged.</h4>
</div>
</body>
</html>
