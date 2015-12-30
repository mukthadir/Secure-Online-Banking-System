<%@include file="tagImports.jsp" %>

<html>
<head>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap.css"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" />
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap-theme.css" />
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap-theme.min.css" />
</head>	
<style>
tr {
display: table-row;
vertical-align: inherit;
border-color: inherit;
}
th {
ont-size: 1.4em;
text-align: center;
padding:3px;
background-color: #555555;
border:1px solid #555555;
color: white;}
td {
font-size: 1.2em;
border: 1px solid #d4d4d4;
padding: 5px;
text-align:center;
};
</style>
<script type="text/javascript">
$(document).ready(function() {
	$(".checkbox").click(function(e)
		{
			var $this=$(this);
			if($this.prop("checked"))
				{
					$this.parent().parent().find($('.input')).prop("readonly",false);
				}
			else
				{
					if(!($this.parent().parent().find($('.input')).is('[readonly=false]')))
						$this.parent().parent().find($('.input')).prop("readonly",true);
				}
			})
	});
</script>
<body>
<div class="navbar navbar-default">
  <div class="container">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">Employee</a>
    </div>
    <ul class="nav navbar-nav">
      <li class="active"><a href="${pageContext.request.contextPath}/emp/home">Home</a></li>
      <li><a href="#" data-hover="dropdown" class="dropdown-toggle">Transactions<b class="caret"></b></a>
            <ul class="dropdown-menu">
            	<li><a href="${pageContext.request.contextPath}/transaction/create">Create</a></li>
            	<li><a href="${pageContext.request.contextPath}/transaction/modify">Modify</a></li>
            	<li><a href="${pageContext.request.contextPath}/transaction/delete">Delete</a></li>
            </ul>
      </li>
      <li><a href="#" data-hover="dropdown" class="dropdown-toggle">User Requests<b class="caret"></b></a>
            <ul class="dropdown-menu">
            	<li><a href="${pageContext.request.contextPath}/approvereq">Approve Requests</a></li>
            	<li><a href="${pageContext.request.contextPath}/forwardreq">Forward Requests</a></li>
            
            </ul>
      </li>
    </ul>
    <p class="navbar-text pull-right">${email} | <a href="${pageContext.request.contextPath}/">Log Out</a></p>
  </div>
</div>

<div id="main" style="width:100%;overflow:hidden">
<h3 style="text-align:center;">Authorize Transactions</h3>
<form:form modelAttribute="container" action="${pageContext.request.contextPath}/transaction/modify/submit">
	<table style="width:80%;display: table-row-group;
vertical-align: middle;
border-color: inherit;
border-collapse: collapse;
margin-left:10%">
  		<tr>
    		<th>Transaction ID</th>
    		<th>Sender</th>		
    		<th>Receiver</th>
    		<th>Amount</th>
    		<th>Message</th>
    		<th>Enable</th>
    		<th>Confirm</th>
  		</tr>
  			<c:if test="${not empty transactions}">
			<c:forEach items="${transactions}" var="a">
  		<tr>
    		<td><form:input readonly="true" value="${a.getTID()}"  path="TID"/></td>
    		<td><form:input readonly="true" value="${a.getSender()}"  path="Sender"/></td>		
			<td><form:input readonly="true" value="${a.getReceiver()}"  path="Receiver" class="input"/></td>
    		<td><form:input readonly="true" value="${a.getAmount()}"  path="Amount" class="input"/></td>
    		<td><form:input readonly="true" value="${a.getMessage()}"  path="Message" class="input"/></td>
    		<td><input type="checkbox" name="modify" class="checkbox"/></td>
    		<td><form:select path="flag">
  					<form:option value="Yes">Yes</form:option>
  					<form:option value="No">No</form:option>
				</form:select>
 		</tr>
 		</c:forEach>
 		</c:if>
	</table>
				<input id="modify" type="submit" value="Modify" class="btn btn-default" name="modify"/>
	</form:form>
</div>

</body>

</html>