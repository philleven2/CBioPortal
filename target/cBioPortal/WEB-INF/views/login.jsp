<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="en">

<head>

<!-- The below 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- CSS dependencies -->
<link href="static/theme/bootstrap.min.css" rel="stylesheet">
<link href="static/theme/login.css" rel="stylesheet" type="text/css">

<!-- JS dependencies -->
<script src="static/js/jquery-3.4.1.min.js"></script>
<script src="static/js/bootstrap.min.js"></script>

<title>cBioPortal Login</title>

<style>
body {
	background-image: url("static/image/jungle_path.jpg");
	background-size: cover;
	background-repeat: no-repeat;
}
</style>

</head>

<body onload='document.loginForm.username.focus();'>

	<div class="container">

		<div id="login-box">

			<h6>
				<img src="static/image/cBioPortal.png" alt="cBioPortal" width="159" height="36">&nbsp;&nbsp;cBioPortal Login
			</h6>

			<h6>Login with Username and Password</h6>

			<c:if test="${not empty error}">
				<div class="error">${error}</div>
			</c:if>

			<c:if test="${not empty msg}">
				<div class="msg">${msg}</div>
			</c:if>

			<form name='loginForm' action="<c:url value='/login' />"
				method='POST'>

				<table class="table">

					<tr>
						<td>User:</td>
						<td><input type='text' name='username' value=''></td>
					</tr>

					<tr>
						<td>Password:</td>
						<td><input type='password' name='password' /></td>
					</tr>

					<tr>
						<td colspan='2'>
							<button type="submit" class="btn btn-primary">Submit</button>
						</td>
					</tr>

				</table>

				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" />

			</form>

		</div>

	</div>

</body>

</html>