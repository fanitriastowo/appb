<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../template/taglib.jsp"%>

<style>
body {
	padding-top: 20px;
	padding-bottom: 20px;
	background-color: #eee;
}

.form-signin {
	max-width: 330px;
	padding: 15px;
	margin: 0 auto;
}

.form-signin .form-signin-heading, .form-signin .checkbox {
	margin-bottom: 10px;
}

.form-signin .checkbox {
	font-weight: normal;
}

.form-signin .form-control {
	position: relative;
	height: auto;
	-webkit-box-sizing: border-box;
	-moz-box-sizing: border-box;
	box-sizing: border-box;
	padding: 10px;
	font-size: 16px;
}

.form-signin .form-control:focus {
	z-index: 2;
}

.form-signin input[type="text"] {
	margin-bottom: -1px;
	border-bottom-right-radius: 0;
	border-bottom-left-radius: 0;
}

.form-signin input[type="password"] {
	margin-bottom: 10px;
	border-top-left-radius: 0;
	border-top-right-radius: 0;
}
</style>

<div class="row">
	<form id="loginForm" class="form-signin" role="form" action='<spring:url value="/j_spring_security_check"/>' method="post">
		<c:if test="${success eq false }">
			<div class="alert alert-danger">Username atau Password tidak valid!</div>
		</c:if>
		<h2 class="form-signin-heading">Silahkan Login</h2>
		<input type="text" id="j_username" name="j_username" class="form-control" placeholder="Username" required autofocus /> <input type="password" id="j_password" name="j_password" class="form-control"
			placeholder="Password" required />
		<button class="btn btn-lg btn-primary btn-block" type="submit">Login</button>
	</form>
</div>