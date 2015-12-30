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
<%@include file="userNavBar.jsp" %>
<div class="container">
<h4>Successful Transfer.</h4>
</div>
</body>
</html>
