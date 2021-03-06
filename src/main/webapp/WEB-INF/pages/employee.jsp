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
            
            </ul>
      </li>
    </ul>
    <p class="navbar-text pull-right">${email} | <a href="${pageContext.request.contextPath}/">Log Out</a></p>
  </div>
</div>

<div id="main" style="width:100%;overflow:hidden">
<h3 style="text-align:center;">Authorize Transactions</h3>
<%-- ${pageContext.request.contextPath}/Processed --%>
<form action="${pageContext.request.contextPath}/Processed">
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
    		<th>Date</th>
    		<th>Authorize</th>
  		</tr>
  					<c:if test="${not empty container}">
			<c:forEach items="${container}" var="a">
  		<tr>

    		<td><input type="number" readonly="true" value="${a.getTransactionId()}"/></td>
    		<td><input type="number" readonly="true" value="${a.getAccountSummaryByUserSending().accountNumber}"/></td>		
			<td><input type="number" readonly="true" value="${a.getAccountSummaryByUserReceiving().accountNumber}"/></td>
    		<td><input type="number" readonly="true" value="${a.getAmount()}"/></td>
    		<td><input readonly="true" value="${a.getTime()}"/></td>
    		<td><select name="values">
  					<option value="Approve">Approve</option>
  					<option value="Reject">Reject</option>
				</select>
			</td>
 		</tr>
			</c:forEach>
			</c:if>
	</table>
				<input id="authorize" type="submit" value="Authorize" class="btn btn-default" style="float:right;margin-right:10%;margin-top:5px;" name="authorize"/>
	</form>
</div>

</body>

</html>