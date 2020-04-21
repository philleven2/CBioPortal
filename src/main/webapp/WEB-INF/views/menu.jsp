<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 

<html lang="en">

<head>

<!-- The below 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- CSS dependencies -->
<link href="static/theme/bootstrap.min.css" rel="stylesheet">
<link href="static/theme/custom.css" rel="stylesheet">

<title>cBioPortal</title>

<!-- JS dependencies -->
<script src="static/js/jquery-3.4.1.min.js"></script>
<script src="static/js/popper.min.js"></script>
<script src="static/js/bootstrap.min.js"></script>
<script src="static/js/bootbox.min.js"></script>

<%
String selProfile = (String) request.getAttribute("selProfile");
%>

<script type="text/javascript">
<!--

	$.getScript("static/js/trimAll.js");
	
	$("#MsgDiv").empty();
	
-->
</script>

</head>

<body>

	<nav class="navbar navbar-light py-0 justify-content-start">
	
		<span class="navbar-brand navbar-text"><img src="static/image/cBioPortal.png" alt="cBioPortal" width="159" height="36">
			&nbsp;&nbsp; cBioPortal v1.0.0
		</span>          

		<form class="form-inline" name="frmLogs" action="logs" method="GET">
		    <button class="btn btn-sm btn-default" type="submit"
		    data-toggle="tooltip" title="View logs.">Logs</button>
		</form>

		<form class="form-inline" name="frmRefresh" action="back" method="GET">
			<button class="btn btn-sm btn-default" type="submit"
				data-toggle="tooltip" title="Refresh dashboard.">Refresh</button>
		</form>

	   	<form class="form-inline" name="frmLogout" action="logout" method="POST">
		    <button class="btn btn-sm btn-default" type="submit"
		    data-toggle="tooltip" title="Logout.">Logout</button>
		</form>

	</nav>

	<div class="container-fluid" style="margin-top: 20px;">

		<form:form class="form small" name="frmProfile" method="GET" action="getProfile">

			<div class="form-group row">

				<label class="control-label col-sm-1">Profile:</label>
				<div class="col-sm-10">
					<select class="selectpicker shadow" name="profile">
						
						<!-- If selected profile -->
						<c:if test="${'chol_nccs_2013_mutations' == selProfile}">

							<option value="chol_nccs_2013_mutations" selected>chol_nccs_2013_mutations</option>

						</c:if>

						<!-- If not selected profile -->
						<c:if test="${'chol_nccs_2013_mutations' != selProfile}">

							<option value="chol_nccs_2013_mutations">chol_nccs_2013_mutations</option>

						</c:if>
						
						<!-- If selected profile -->
						<c:if test="${'acc_tcga_mutations' == selProfile}">

							<option value="acc_tcga_mutations" selected>acc_tcga_mutations</option>

						</c:if>

						<!-- If not selected profile -->
						<c:if test="${'acc_tcga_mutations' != selProfile}">

							<option value="acc_tcga_mutations">acc_tcga_mutations</option>

						</c:if>

						<!-- If selected profile -->
						<c:if test="${'gbm_tcga_mutations' == selProfile}">

							<option value="gbm_tcga_mutations" selected>gbm_tcga_mutations</option>

						</c:if>

						<!-- If not selected profile -->
						<c:if test="${'gbm_tcga_mutations' != selProfile}">

							<option value="gbm_tcga_mutations">gbm_tcga_mutations</option>

						</c:if>

					</select>
					
					<!-- Get profile -->
					<button name="btnProfile" class="btn btn-sm btn-primary"
						onclick="submitForm(this); return false;">Get Profile</button>
					
				</div>
			</div>

			<c:if test="${not empty profiles}">

				<div class="form-group row">
					<label class="control-label col-sm-1">Name:</label>
					<div class="col-sm-3">
						<input class="form-control form-control-sm shadow" type="text" placeholder="${profiles.name}" disabled>
					</div>
					
					<label class="control-label col-sm-1">Study:</label>
					<div class="col-sm-5">
						<input class="form-control form-control-sm shadow" type="text" placeholder="${profiles.study}" disabled>
				    </div>
				</div>

				<div class="form-group row">
					<label class="control-label col-sm-1">Type:</label>
					<div class="col-sm-3">
						<input class="form-control form-control-sm shadow" type="text" placeholder="${profiles.cancerType}" disabled>
				    </div>
				</div>
			
			</c:if>

			<!-- Container for error message -->
			<div id="MsgDiv" class="msgDiv" onclick="hideDiv()">${msg}</div>


		</form:form>

	</div>

	<script type="text/javascript">
	<!--
	
	document.frmProfile.profile.focus();

	// Tool tips
	$(document).ready(function() {
		
		  $('[data-toggle="tooltip"]').tooltip(); 
		  
	});
	
	// Confirm selection
	function confirmSelection (form, msg) {
		
		bootbox.confirm(msg, function(result) {
			
			if (result == true) {
				
				form.submit();
				
			}
			
	    });
		
	}
	
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
		frmProfile.submit();
	
	}

	-->	
	</script>

</body>

</html>