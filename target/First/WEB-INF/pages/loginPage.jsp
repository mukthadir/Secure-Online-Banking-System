<%@include file="tagImports.jsp" %>
<%@include file="imageLogo.jsp" %>
<%@ page import="net.tanesha.recaptcha.ReCaptcha" %>
<%@ page import="net.tanesha.recaptcha.ReCaptchaFactory" %>
<%@ page import="net.tanesha.recaptcha.ReCaptchaImpl" %>
<script src="${pageContext.request.contextPath}/resources/js/keyboard.js"></script>
<script type="text/javascript">
$( document ).ready(function() {
	$("#keyboard").hide();	
	$("#write").focus(function() {
		$("#keyboard").show();
		});
});  
</script> 

<html>
<%
	response.setHeader("Pragma", "no-cache");
    response.setHeader("Cache-Control", "no-cache");
    response.setDateHeader("Expires", 0);
%>

<head>	
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap.css"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" />
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap-theme.css" />
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap-theme.min.css" />
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/style.css" />
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/login.css" />
</head>	
<body> 
<div class="container">
<c:if test="${not empty failure}">
   <h4>${failure}</h4>   
</c:if>
</div>
<div class="container">
<form:form modelAttribute="login" action="${pageContext.request.contextPath}/homePage">
<table class="table-condensed">

<tr>
<td>
<h2>Login</h2>
</td>
<td>&nbsp;</td>
</tr>

<tr>
<td>User Name </td>
<td><form:input class= "form-control" path="loginId"/></td>
<td id="name-invalid"><h5>Invalid character </h5></td>
				<td id="error-mssg"><h5>Cannot be empty!</h5>
</tr>
<tr>
<td>Password</td> 
<td><form:password class="form-control" id="write" path="password"></form:password></td>
<td id="pwd-invalid"><h5>Invalid character </h5></td>
				<td id="error-mssg-pwd"><h5>Cannot be empty!</h5></td>
</tr>
</table>
  
<table class="table-condensed">
<tr>
<td>
	<ul id="keyboard">
		<li class="symbol"><span class="off">`</span><span class="on">~</span></li>
		<li class="symbol"><span class="off">1</span><span class="on">!</span></li>
		<li class="symbol"><span class="off">2</span><span class="on">@</span></li>
		<li class="symbol"><span class="off">3</span><span class="on">#</span></li>
		<li class="symbol"><span class="off">4</span><span class="on">$</span></li>
		<li class="symbol"><span class="off">5</span><span class="on">%</span></li>
		<li class="symbol"><span class="off">6</span><span class="on">^</span></li>
		<li class="symbol"><span class="off">7</span><span class="on">&amp;</span></li>
		<li class="symbol"><span class="off">8</span><span class="on">*</span></li>
		<li class="symbol"><span class="off">9</span><span class="on">(</span></li>
		<li class="symbol"><span class="off">0</span><span class="on">)</span></li>
		<li class="symbol"><span class="off">-</span><span class="on">_</span></li>
		<li class="symbol"><span class="off">=</span><span class="on">+</span></li>
		<li class="delete lastitem">delete</li>
		<li class="tab">tab</li>
		<li class="letter">q</li>
		<li class="letter">w</li>
		<li class="letter">e</li>
		<li class="letter">r</li>
		<li class="letter">t</li>
		<li class="letter">y</li>
		<li class="letter">u</li>
		<li class="letter">i</li>
		<li class="letter">o</li>
		<li class="letter">p</li>
		<li class="symbol"><span class="off">[</span><span class="on">{</span></li>
		<li class="symbol"><span class="off">]</span><span class="on">}</span></li>
		<li class="symbol lastitem"><span class="off">\</span><span class="on">|</span></li>
		<li class="capslock">caps lock</li>
		<li class="letter">a</li>
		<li class="letter">s</li>
		<li class="letter">d</li>
		<li class="letter">f</li>
		<li class="letter">g</li>
		<li class="letter">h</li>
		<li class="letter">j</li>
		<li class="letter">k</li>
		<li class="letter">l</li>
		<li class="symbol"><span class="off">;</span><span class="on">:</span></li>
		<li class="symbol"><span class="off">'</span><span class="on">&quot;</span></li>
		<li class="return lastitem">return</li>
		<li class="left-shift">shift</li>
		<li class="letter">z</li>
		<li class="letter">x</li>
		<li class="letter">c</li>
		<li class="letter">v</li>
		<li class="letter">b</li>
		<li class="letter">n</li>
		<li class="letter">m</li>
		<li class="symbol"><span class="off">,</span><span class="on">&lt;</span></li>
		<li class="symbol"><span class="off">.</span><span class="on">&gt;</span></li>
		<li class="symbol"><span class="off">/</span><span class="on">?</span></li>
		<li class="right-shift lastitem">shift</li>
		<li class="space lastitem">&nbsp;</li>
	</ul>
</td>
</tr>
</table>
<table class="table-condensed">
<colgroup> 
<col width="110"/>
<col width="22"/>
<col width="22"/>
</colgroup>
<tr>
<td/>
<td>
<%
		ReCaptcha c;
		if(request.isSecure()) {
	           c = ReCaptchaFactory.newReCaptcha("6Ldw_PwSAAAAACP73FFz-1dVEtSoBLy4jzVFRrRK", "6Ldw_PwSAAAAABXD4A_8dRl81MQOYf1aOvZSFSJ6", false);
    			((ReCaptchaImpl) c).setRecaptchaServer("https://www.google.com/recaptcha/api");
		} else {
	           c = ReCaptchaFactory.newReCaptcha("6Ldw_PwSAAAAACP73FFz-1dVEtSoBLy4jzVFRrRK", "6Ldw_PwSAAAAABXD4A_8dRl81MQOYf1aOvZSFSJ6", false);
	           ((ReCaptchaImpl) c).setRecaptchaServer("http://www.google.com/recaptcha/api");
			
		}
          out.print(c.createRecaptchaHtml(null, null));
        %>
</td>
</tr>
<tr>
<td/>
<td><input type="submit" value="Submit" class="btn btn-default" name="login"/></td>
<td><input type="submit" value="New User" class="btn btn-default" name="newUser"/></td>
</tr>
<tr>
<td/>
<td colspan="2"><a href="${pageContext.request.contextPath}/passwordReset">Forgot Password?</a></td>
</tr>
</table>
</form:form>
</div>
</body>
</html> 