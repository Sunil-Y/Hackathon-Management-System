<%@ page session="false"%>
<%@ taglib prefix="jstlcore" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="jstlfmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
<title>Welcome to Festival Event Registration System</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<link rel="StyleSheet" href="<jstlcore:url value="/css/struts2.css" />"  type="text/css" />


<script type="text/javascript">
function validateForm()
{
var uname=document.forms["logForm"]["USERNAME"].value;
var password=document.forms["logForm"]["PASSWORD"].value;
if (uname==null || uname=="")
{
alert("Please provide Username");
return false;
}
if (password==null || password=="")
{
alert("Please provide Password");
return false;
}
}
</script>
</head>
<style>
.error {
	color: red;
	font-size: 13px; 
	font-weight: bold;
}
</style>

<body>
<br/><br/><br/><br/><br/><br/>
<jstlcore:set var="contextPath" value="${pageContext.request.contextPath}" />

<form method="post" name="logForm" action="${contextPath}/hacker/searchHacker.htm" onsubmit="return validateForm()">
<table width="80%" align="center" border="2" bordercolor="#339999">
	<tr>
		<td align="Center">
		<div id="header">&nbsp;
		<div align="center">Festival Event Registration System</div>
		</div>
		<!-- header end -->
		<br/>
		<table>
			<tr>
				<!--content begin -->
				<td colspan="2" align="center">
				<div id="content">
				<h3>Portal Login Page</h3>
				</div>
				</td>
			</tr>
			<tr>
				<td>Hacker Name</td>
				<td><input type="text" name="USERNAME"></td>
			</tr>
			<tr>
				<td>Password</td>
				<td><input type="password" name="PASSWORD">
				</td>
			</tr>
			<tr>
				<td colspan="2" align="right"><input type='submit' value=" Login "></input> <br />
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center"><span class="error">${ERROR}</span></td>
			</tr>
			<tr>
				<td></td>
			</tr>
			<tr>
				<td></td>
			</tr>
			<tr>
				<td>
				<div id="content">New Hacker:</div>
				<div id="content"><a href="${contextPath}/hacker/register.htm">Register
				here</a></div>
				</td>
			</tr>
		</table>
		<br />
		</td>
	</tr>
</table>

</form>

</body>

</html>
