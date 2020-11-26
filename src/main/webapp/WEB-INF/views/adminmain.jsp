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
										<td width="160">
											<div id="menu">
												<a href="${contextPath}/event/newEvent.htm">Add_Event</a>
											</div>
										</td>
										<td width="160">
											<div id="menu">
												<a href="${contextPath}/event/ajax.htm">Ajax</a>
											</div>
										</td>
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
										Enter eventname to search <input type="text" name="eventname"></input> <input
											type="submit" value="Search"></input>
									</p>
									



									<table class="content" width="96%" border="1" align="center">
										<tr bgcolor="#669966">
											<th scope="col">Event id</th>
											<th scope="col">Event name<br /> <img
													src="<jstlcore:url value="/images/upointer.jpg" />" /><img
													src="<jstlcore:url value="/images/dpointer.jpg" />" />
											</th>
											<th scope="col">description</th>
											<th scope="col">Places</th>
											<th scope="col">Duration</th>
											<th scope="col">Event type</th>
											<th scope="col">Available Tickets</th>
											<th scope="col">Update</th>
											<th scope="col">Delete</th>

										</tr>
										<jstlcore:forEach items="${requestScope.allEvents}"
											var="event">
											<tr>
												<td align="center"><jstlcore:out
														value="${event.eventid}"></jstlcore:out></td>
												<td align="center"><jstlcore:out value="${event.name}"></jstlcore:out></td>
												<td align="center"><jstlcore:out
														value="${event.description}"></jstlcore:out></td>
												<td align="center"><jstlcore:out value="${event.place}"></jstlcore:out></td>
												<td align="center"><jstlcore:out
														value="${event.duration}"></jstlcore:out></td>
												<td align="center"><jstlcore:out
														value="${event.eventtype}"></jstlcore:out></td>
												<jstlcore:choose>
													<jstlcore:when test="${event.seatsavailable != 0}">
														<td width="100px" bgcolor="#CCCC99" align="center"><jstlcore:out
																value="${event.seatsavailable}"></jstlcore:out> <strong>
																seats left.</strong></td>
													</jstlcore:when>
													<jstlcore:otherwise>
														<td width="100px" bgcolor="#CCCC99" align="center"><strong>No
																seats left</strong></td>
													</jstlcore:otherwise>
												</jstlcore:choose>

												<td align="center"><a
													href="${contextPath}/event/updateEvent.htm?eventId=${event.eventid}">
														Update </a></td>
												<td align="center"><a
													href="${contextPath}/event/deleteEvent.htm?eventId=${event.eventid}">
														Delete </a></td>
											</tr>
										</jstlcore:forEach>
										<tr>
											<td colspan="10" align="center"
												style="font-weight: bold; color: red;">${RegError}</td>
										</tr>
									</table>

								</div>
							</td>
						</tr>
					</table>
		</table>
	</form>
</body>

</html>
