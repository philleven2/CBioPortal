<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="en">

<head>

<!-- The below 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- CSS dependencies -->
<link href="static/theme/bootstrap.min.css" rel="stylesheet">
<link href="static/theme/custom.css" rel="stylesheet">

<title>View Logs</title>

<!-- JS dependencies -->
<script src="static/js/jquery-3.4.1.min.js"></script>
<script src="static/js/bootstrap.min.js"></script>
<script src="static/js/bootbox.min.js"></script>

<%
// Page size
String pagSize = (String) request.getAttribute("pagSize");
int pagSiz = Integer.parseInt(pagSize);

// Number of rows
String nbrRows = (String) request.getAttribute("nbrRows");
int nbrRws = Integer.parseInt(nbrRows);

// From row
String fromRow = (String) request.getAttribute("fromRow");
int fromRw = Integer.parseInt(fromRow);

// To row
String toRow = (String) request.getAttribute("toRow");
int toRw = Integer.parseInt(toRow);

// Search
String schText = (String) request.getAttribute("schText");
%>

</head>

<body>

<div class="container-fluid">

	<form class="form small" name="frmView">
	
		<table class="table table-striped custom" style="margin-top: 5px; font-size: 12px; width: 100% !important;">
		
			<tbody>
			
				<tr style="background: menu">
					<td colspan="4" class="font-weight-bold">
						<img src="static/image/cBioPortal.png" alt="cBioPortal" width="159" height="36">&nbsp;&nbsp;View Logs
					</td>
				</tr>

				<tr>
					<td align="right" width="15%">Date Created</td>
					<td width="10%">Logger</td>
					<td width="5%">Level</td>
					<td style="word-break: break-all;" width="70%">Message</td>
				</tr>
			
				<c:if test="${not empty logs}">
							
					<c:forEach var="logs" items="${logs}">
					
						<tr>
							<td valign="top" align="right">${logs.dateCreated}</td>
							<td valign="top">${logs.logger}</td>
							<td>${logs.level}</td>
							<td>${logs.message}</td>
						</tr>
						
					</c:forEach>
				
				</c:if>
				
				<c:if test="${empty logs}">
				
					<tr>
						<td colspan="4">No records found.</td>
					<tr>
				
				</c:if>
				
				<tr style="background: menu">
					<td colspan="4">
					
					<!-- Back -->
					<button class="btn btn-sm btn-primary" onclick="location.href='back'; return false;">Back</button>
					
					<!-- Page up -->
					<button class="btn btn-sm btn-primary" name="btnPageUp" onclick="pageUp(); return false;">Page Up</button>
								
					<!-- Page down -->
					<button class="btn btn-sm btn-primary" name="btnPageDown" onclick="pageDown(); return false;">Page Down</button>
					
					<!-- Refresh -->
					<button class="btn btn-sm btn-primary" onclick="location.href='logs'; return false;">Refresh</button>
					
					</td>
				</tr>
				
			</tbody>
		
		</table>
	
	</form>
	
	<!-- Search -->
	<form name="frmSearch" action="logs" method="get" onSubmit="return">

		<table class="table table-borderless" 
			style="margin-top: 5px; font-size: 12px; width: 50% !important;">
		
			<tr>
				<td>Message</td>
				<td><input name="schText" type="text" size="50" maxlength="50" value="<%= schText %>"></td>

				<td>
				<!-- Search -->
				<button class="btn btn-sm btn-primary" name="btnSearch" onclick="submitForm(this); return false;">Search</button>
				
				<!-- Clear -->
				<button class="btn btn-sm btn-primary" name="btnClear" onclick="clearSearch(); return false;">Clear</button>
				</td>
			</tr>

		</table>
	
	</form>
	
	<div id="MsgDiv" class="msgDiv" onclick="hideDiv()">
		${msg}
	</div>

</div>

<script>

// Hide MsgDiv
function hideDiv() {
	
	document.getElementById("MsgDiv").style.display = "none";
	
}

// Show MsgDiv
function showDiv() {
	
	document.getElementById("MsgDiv").style.display = "block";
	
}

// Show MsgDiv
showDiv();

// Submit form
function submitForm(e) {

	// Submit form
	frmSearch.submit();

}

// Clear search
function clearSearch() {

	document.frmSearch.schText.style.backgroundColor = "white";
	document.frmSearch.schText.value = "";

	$("#MsgDiv").empty();
	
}

// Enable/disable page up
if (<%= fromRw %> > <%= pagSiz - 1 %>) {

	document.frmView.btnPageUp.disabled = false;
	
} else {

	document.frmView.btnPageUp.disabled = true;

}

// Enable/disable page down
if (<%= toRw %> < <%= nbrRws %>) {

	document.frmView.btnPageDown.disabled = false;
	
} else {


	document.frmView.btnPageDown.disabled = true;

}

// Page up
function pageUp() {

	window.location = "/cBioPortal/logs?mv=PageUp&fromRow=<%= fromRow %>&toRow=<%= toRow %>&schText=<%= schText %>";
	
}

// Page down
function pageDown() {

	window.location = "/cBioPortal/logs?mv=PageDown&fromRow=<%= fromRow %>&toRow=<%= toRow %>&schText=<%= schText %>";

}

</script>

</body>

</html>