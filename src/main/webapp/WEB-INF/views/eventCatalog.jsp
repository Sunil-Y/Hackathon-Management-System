<%@ page contentType="text/html; charset=UTF-8" session="false"%>
<%@ taglib prefix="jstlcore" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="jstlfmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring"
	uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<title>Welcome to Festival Event Registration System</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<link rel="StyleSheet" href="<jstlcore:url value="/css/struts2.css" />"
	type="text/css" />
<script type="text/javascript">
	function previousPage() {
		history.go(-1);
	}
	function validateForm() {
		var ename = document.forms["logForm"]["eventname"].value;
		if (ename == null || ename == "") {
			alert("Please provide Eventname to Search..");
			return false;
		}
	}
</script>
<script>
	function ajaxEvent() {

		var xmlHttp;
		try // Firefox, Opera 8.0+, Safari
		{
			xmlHttp = new XMLHttpRequest();
		} catch (e) {
			try // Internet Explorer
			{
				xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
			} catch (e) {
				try {
					xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
				} catch (e) {
					alert("Your browser does not support AJAX!");
					return false;
				}
			}
		}

		xmlHttp.onreadystatechange = function() {
			if (xmlHttp.readyState == 4) {
				document.getElementById("coursediv").innerHTML = xmlHttp.responseText;
			}
		}

		var queryString = document.getElementById("queryString").value;

		xmlHttp.open("POST", "../ajaxservice.htm?course=" + queryString, true);
		xmlHttp.send();
	}
</script>
<style type="text/css">
<!--
.style1 {
	font-size: 12px
}
-->
</style>
</head>
<body>
	<jstlcore:set var="contextPath"
		value="${pageContext.request.contextPath}" />
	<form name="logForm" method="post"
		action="${contextPath}/event/searchEventByNameCatalog.htm">
		<br /> <br /> <br />
		<table width="80%" align="center" border="2">
			<tr>
				<td>
					<div id="header">
						&nbsp;
						<div align="center">Festival Registration System</div>
					</div>

					<table>
						<tr>
							<td width="100%">
								<table align="right" cellpadding="2">
									<tr>
										<td width="90">
											<div id="menu" align="center">
												<a href="${contextPath}/hacker/logout.htm"> Logout </a>
											</div>
										</td>
										<jstlcore:if test="${not empty requestScope.adminSession}">
											<td width="160">
												<div id="menu">
													<a href="${contextPath}/event/newEvent.htm">Add_Event</a>
												</div>
											</td>
										</jstlcore:if>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td width="900">
								<div align="center">
									<img
										src="<jstlcore:url value="/images/greenhorizontalline.jpg" />"
										height="5" width="100%" />
								</div> <br />
								<div id="content" align="center">
									<h3>Up-coming Events</h3>
									<p class="content">
										Enter eventname to search <input type="text" name="eventname"
											id="queryString" onkeyup="ajaxEvent()"></input> <input
											type="submit" value="Search"></input>

									<div id="coursediv"></div>


									
								</div>
							</td>
						</tr>
					</table>
		</table>
	</form>
</body>

</html>
