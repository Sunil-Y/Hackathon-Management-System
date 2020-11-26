<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="jstlcore" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="jstlfmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
<head>
<title>Welcome to Festival Registration System</title>
<script type="text/javascript">
function isNumeric(value) {
	  if (value=="" || value == null || !value.toString().match(/^[-]?\d*\.?\d*$/))
	  { return false;
	  }
	  return true;
	}		
	function echeck(str) {
		   var emailPattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;   
			   return emailPattern.test(str);   							
	}
	function validateForm()
	{
	var eventName=document.forms["regform"]["eventName"].value;
	var desc=document.forms["regform"]["desc"].value;
	var place=document.forms["regform"]["place"].value;
	var duration=document.forms["regform"]["duration"].value;	
	var eventType=document.forms["regform"]["eventType"].value;
	var ticket=document.forms["regform"]["ticket"].value;	
	if (eventName==null || eventName=="")
	  {
	  alert("Please provide event name");
	  return false;
	  }
	if (desc==null || desc=="")
	  {
	  alert("Please provide event description");
	  return false;
	  }
	if (place==null || place=="")
	  {
	  alert("Please provide place");
	  return false;
	  }
	if (duration==null || duration=="")
	  {
	  alert("Please provide duration");
	  return false;
	  }
	if (eventType==null || eventType=="")
	  {
	  alert("Please provide event type");
	  return false;
	  }
	if (ticket==null || ticket=="" || ticket==0)
	  {
	  alert("Please provide ticket");
	  return false;
	  }
	
	if(isNumeric(ticket)==false)
	{
		alert("Invalid ticket");
		return false;
	}
}
	function cancelRegistration()
	{
		history.go(-1);
	}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<link rel="StyleSheet" href="<jstlcore:url value="/css/struts2.css" />" type="text/css" />
<style type="text/css">
<!--
.style1 {
	font-size: 12
}
-->
</style>
<style>
.error {
	color: red;
	font-weight: bold;
	font-size: 20px;
}
</style>
</head>

<body>	
	<br/><br/>
	<jstlcore:set var="contextPath"
		value="${pageContext.request.contextPath}" />
	<form action="${contextPath}/event/addEvent.htm" name="regform" method="post" onsubmit="return validateForm()">
	<input type="hidden" name="isAdd" value="yes"/>
	<table width="80%" align="center"  border="2">
		<tbody><tr>
			<td>
			<div id="header">&nbsp;
			<div align="center">Festival Registration System</div>
			</div>
			
			<table>
				<tbody><tr>
					<td width="100%">
					<table align="right" cellpadding="2">
						<tbody><tr>
							<td width="90">
							<div id="menu" align="center"><a href="${contextPath}/event/eventCatalog.htm">
							EventCatalog_Page </a></div>
							</td>							
						</tr>
					</tbody></table>
					</td>
				</tr>
				<tr>
					<td width="900">
					<div id="content">
					
					<table align="center" border="0">
						
						<tbody><tr>
							<td align="center" colspan="2">
							<h3>New Event Registration Page</h3>
							</td>
						</tr>
						<tr><td align="center" colspan="2" style="font-style: italic;">Fields marked (<span style="font-weight: bold;color: red; font-size: 15px;">*</span>) are Mandatory</td></tr>
						<tr><td></td><td></td></tr>
						<tr><td></td><td></td></tr>
						<tr>	
										
							<td><span style="font-weight: bold;color: red;font-size: 15px;">*</span>
							Event Name:</td><td> 
							   <input type="text" name="eventName" size="25" ></input></td> 							
					   </tr>
					   <tr>			
							<td><span style="font-weight: bold;color: red;font-size: 15px;">*</span>
							Event Description: </td><td><input type="text" name="desc" size="25" ></input></td>							
					   </tr>
					   <tr>
							<td><span style="font-weight: bold;color: red;font-size: 15px;">*</span>
							Place:</td><td><input type="text" name="place" size="25" ></input></td> 							
					   </tr>
					   <tr>
						    <td><span style="font-weight: bold;color: red;font-size: 15px;">*</span>
						    Duration:</td><td><input type="text" name="duration" size="27" ></input></td>						   
					   </tr>
					   <tr>
						    <td><span style="font-weight: bold;color: red;font-size: 15px;">*</span>
						    Event Type:</td><td><input type="text" name="eventType" size="27" ></input></td>						   
					   </tr>
					   <tr>
						   <td><span style="font-weight: bold;color: red;font-size: 15px;">*</span>
						    Available Tickets: </td><td> <input type="text" name="ticket" size="25" ></input></td> 						    
					   </tr>
					 	
					 				  			    	 
					   <tr>	
						   <td colspan="2" align="center">	
						   	 <input value="Add New Event" type="submit">
						   </td>
						</tr>
						<tr>						
						</tr>
					</tbody></table>
					</div>
					</td>
					<!-- content end -->
				</tr>
			</tbody>
			</table>
			</td>			
		</tr>
		<tr><td colspan="4" align="center"><div style="font-size: 15px; color: red; font-weight: bold;">${REGISTRATIONSTATUSMESSAGE}</div></td></tr>
	</tbody></table>
	
	</form>
</body>

</html>
