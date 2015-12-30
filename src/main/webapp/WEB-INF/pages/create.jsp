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
      <a class="navbar-brand" href="${pageContext.request.contextPath}/emp/home">Employee</a>
    </div>
    <ul class="nav navbar-nav">
      <li class="active"><a href="/">Home</a></li>
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
<h3 style="text-align:center;">Create Transactions</h3>
<form:form modelAttribute="container" action="${pageContext.request.contextPath}/transaction/create/submit">
	<table style="width:80%;display: table-row-group;
vertical-align: middle;
border-color: inherit;
border-collapse: collapse;
margin-left:10%">
  		<tr>
    		<th>Sender</th>		
    		<th>Receiver</th>
    		<th>Amount</th>
  		</tr>
  		<tr>
    			<td><form:input class= "form-control" path="accountSummaryByUserSending.accountNumber"/>
    			</td>
    			<td><form:input class= "form-control" path="accountSummaryByUserReceiving.accountNumber"/>
    			</td>		
				<td><form:input class= "form-control" path="amount" name="amount" id="amount" required="required" min="0" maxlength="10"/></td>
 		</tr>
	</table>
				<input id="create" type="submit" value="Create" class="btn btn-default" style="float:right;" name="create"/>
	</form:form>
</div>

</body>

</html>