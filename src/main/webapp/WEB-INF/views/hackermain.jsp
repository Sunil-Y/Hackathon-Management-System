<%@ page session="false"%>
<%@ taglib prefix="spring"
	uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="jstlcore" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="jstlfmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
<head>
<title>Welcome to Festival Event Registration System</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<link rel="StyleSheet" href="<jstlcore:url value="/css/struts2.css" />"
	type="text/css" />
<script type="text/javascript">
	function validateForm() {
		var ename = document.forms["logForm"]["eventname"].value;
		if (ename == null || ename == "") {
			alert("Please provide Eventname to Search");
			return false;
		}
	}
</script>

</head>

<body>
	<jstlcore:set var="contextPath"
		value="${pageContext.request.contextPath}" />
	<jstlcore:set var="shacker" value="${sessionScope.VISITOR}" />
	<form name="logForm" method="post">

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
											<div id="menu" align="center">
												<a
													href="${contextPath}/hacker/generatePdf.htm?vid=${hacker.personID}">Generate
													PDF</a>
											</div>
										</td>
									</tr>
									<tr>

										<td colspan="10" align="center"
											style="font-weight: bold; color: red;">${Error}</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td width="1000">
								<table cellpadding="5" width="100%">
									<tr>
										<td>
											<div align="center" id="content">
												<h3>
													Festival Portal Page: Welcome <span
														style="background-color: yellow;"><jstlcore:out
															value="${hacker.firstName}"></jstlcore:out></span> to your
													portal page!!
												</h3>
											</div>
											<table cellpadding="4" border="1">
												<tr>
													<td width="1000">
														<div id="coursesbody" align="left">
															<p>
																<strong>Your personal information</strong> is below:
															</p>
															<p class="style1">
																Username :
																<jstlcore:out value="${hacker.userName}"></jstlcore:out>
															</p>
															<p class="style1">
																Hacker ID:
																<jstlcore:out value="${hacker.personID}"></jstlcore:out>
															</p>
															<p class="style1">
																Email:
																<jstlcore:out value="${hacker.email.emailAddress}"></jstlcore:out>
															</p>
															<p class="style1">
																Phone Number:
																<jstlcore:out value="${hacker.phoneNumber}"></jstlcore:out>
															</p>
															<p class="style1">
																Address:
																<jstlcore:out value="${hacker.address}"></jstlcore:out>
															</p>
														</div>
													</td>
												</tr>
											</table> <br />
											<div align="center">
												<img
													src="<jstlcore:url value="/images/greenhorizontalline.jpg" />"
													height="5" width="100%" />
											</div>
											<div id="content" align="center">
												<p class="content">
													<strong>To release a ticket you have for an
														up-coming attraction, please find the attraction below,
														and click the Release link.</strong>
												</p>
												<table width="96%" border="1" align="center">
													<tr bgcolor="#66CC99">
														<th scope="col">Event ID</th>
														<th scope="col">Event Name</th>
														<th scope="col">Description</th>
														<th scope="col">Place</th>
														<th scope="col">Duration</th>
														<th scope="col">Event Type</th>
														<th scope="col">Action</th>
													</tr>
													<jstlcore:forEach items="${hacker.registeredEvents}"
														var="regEvent">
														<tr align="center">
															<td align="center"><jstlcore:out
																	value="${regEvent.eventid}"></jstlcore:out></td>
															<td align="center"><jstlcore:out
																	value="${regEvent.name}"></jstlcore:out></td>
															<td align="center"><jstlcore:out
																	value="${regEvent.description}"></jstlcore:out></td>
															<td align="center"><jstlcore:out
																	value="${regEvent.place}"></jstlcore:out></td>
															<td align="center"><jstlcore:out
																	value="${regEvent.duration}"></jstlcore:out></td>
															<td align="center"><jstlcore:out
																	value="${regEvent.eventtype}"></jstlcore:out></td>
															<td width="100px" bgcolor="#CCCC99"><a
																href="${contextPath}/event/eventunreg.htm?eid=${regEvent.eventid}&vid=${hacker.personID}">Release</a>
															</td>
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
							</td>

						</tr>

					</table>




					<div align="center">
						<img
							src="<jstlcore:url value="/images/greenhorizontalline.jpg" />"
							height="5" width="100%" />
					</div> <br />
					<div id="content" align="center">
						<p class="content">
							<strong>To get a ticket for an up-coming attraction,
								please find the attraction below, and click the Register link.</strong>
						</p>

						<table class="content" width="96%" border="1" align="center">
							<tr bgcolor="#66CC99">
								<th scope="col">Event id</th>
								<th scope="col">Event name<br /> <img
									src="<jstlcore:url value="/images/upointer.jpg" />" /><img
									src="<jstlcore:url value="/images/dpointer.jpg" />" />
								</th>
								<th scope="col">Description</th>
								<th scope="col">Place</th>
								<th scope="col">Duration</th>
								<th scope="col">Event type</th>
								<th scope="col">Available Tickets</th>
							</tr>
							<jstlcore:forEach items="${requestScope.allEvents}" var="event">
								<tr>
									<td align="center"><jstlcore:out value="${event.eventid}"></jstlcore:out></td>
									<td align="center"><jstlcore:out value="${event.name}"></jstlcore:out></td>
									<td align="center"><jstlcore:out
											value="${event.description}"></jstlcore:out></td>
									<td align="center"><jstlcore:out value="${event.place}"></jstlcore:out></td>
									<td align="center"><jstlcore:out value="${event.duration}"></jstlcore:out></td>
									<td align="center"><jstlcore:out
											value="${event.eventtype}"></jstlcore:out></td>
									<jstlcore:choose>
										<jstlcore:when test="${event.seatsavailable != 0}">
											<td width="100px" bgcolor="#CCCC99" align="center"><jstlcore:out
													value="${event.seatsavailable}"></jstlcore:out> <strong>
													seats left.</strong> <input type="hidden" name="eid"
												value="${event.eventid}"> <a
												href="${contextPath}/event/eventreg.htm?eid=${event.eventid}&vid=${hacker.personID}">Register</a>
											</td>
										</jstlcore:when>
										<jstlcore:otherwise>
											<td width="100px" bgcolor="#CCCC99" align="center"><strong>No
													seats left</strong></td>
										</jstlcore:otherwise>
									</jstlcore:choose>
								</tr>
							</jstlcore:forEach>


						</table>

					</div>
				</td>
			</tr>
		</table>
	</form>
</body>

</html>
