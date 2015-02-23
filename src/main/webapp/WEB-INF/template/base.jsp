<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<tilesx:useAttribute name="current" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath }/static/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/static/css/bootstrap-datetimepicker.min.css" type="text/css" media="screen" />
<link rel="stylesheet" href="${pageContext.request.contextPath }/static/css/font-awesome.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/static/css/navbar.css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/static/css/slider.css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/static/css/jquery.dataTables.min.css">
<link rel="icon" href="${pageContext.request.contextPath }/static/img/favicon.ico">
<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/jquery.validate.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/moment.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/moment_id.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/bootstrap-datetimepicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/bootstrap-slider.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/jquery.dataTables.min.js"></script>
<title><tiles:getAsString name="title" /></title>
</head>
<body>
	<div class="container">
		<div class="row">
			<!-- Static navbar -->
			<div class="navbar navbar-default" role="navigation">
				<div class="container-fluid">
					<div class="navbar-header">
						<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
							<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span>
						</button>
						<a class="navbar-brand" href='<spring:url value="/"/>'><img src="${pageContext.request.contextPath }/static/img/toga.jpg" alt="APSB" class="img-rounded"></a>
					</div>

					<div class="navbar-collapse collapse">
						<ul class="nav navbar-nav">
							<li class="${current == 'home' ? 'active' : '' }"><a href='<spring:url value="/"/>'>Beranda</a></li>
							<security:authorize access="hasRole('ROLE_ADMIN')">
								<li class="dropdown ${current == 'master' ? 'active' : '' }"><a href="#" class="dropdown-toggle" data-toggle="dropdown">Master <span class="caret"></span></a>
									<ul class="dropdown-menu" role="menu">
										<li><a href='<spring:url value="/master/users/1.apsb"/>'>Pengguna</a></li>
										<li><a href='<spring:url value="/master/kendaraan/1.apsb"/>'>Kendaraan</a></li>
										<li><a href='<spring:url value="/master/news-admin/1.apsb"/>'>Berita</a></li>
									</ul>
								</li>
								<li class="${current == 'hitung' ? 'active' : '' }"><a href='<spring:url value="/master/news-hitung/1.apsb"/>'>Hitung</a></li>		
							</security:authorize>
							<security:authorize access="hasRole('ROLE_USER')">
								<li class="${current == 'news' ? 'active' : '' }"><a href='<spring:url value="/news/1.apsb"/>'>Berita</a></li>
							</security:authorize>
							<security:authorize access="!isAuthenticated()">
								<li class="${current == 'news' ? 'active' : '' }"><a href='<spring:url value="/news-anonim/1.apsb"/>'>Berita</a></li>
							</security:authorize>
							<security:authorize access="isAuthenticated()">
								<security:authorize access="hasRole('ROLE_USER')">
									<li class="${current == 'help' ? 'active' : '' }"><a href='<spring:url value="/help-user.apsb"/>'>Bantuan</a></li>
								</security:authorize>
								<security:authorize access="hasRole('ROLE_ADMIN')">
									<li class="${current == 'help' ? 'active' : '' }"><a href='<spring:url value="/help-admin.apsb"/>'>Bantuan</a></li>
								</security:authorize>
							</security:authorize>
							<security:authorize access="!isAuthenticated()">
								<li class="${current == 'help' ? 'active' : '' }"><a href='<spring:url value="/help-default.apsb"/>'>Bantuan</a></li>
							</security:authorize>
						</ul>

						<ul class="nav navbar-nav navbar-right">
							<security:authorize access="!isAuthenticated()">
								<li class="${current == 'register' ? 'active' : '' }"><a href='<spring:url value="/register.apsb"/>'>Registrasi</a></li>
								<li class="${current == 'login' ? 'active' : '' }"><a href='<spring:url value="/login.apsb"/>'>Login</a></li>
							</security:authorize>
							<security:authorize access="isAuthenticated()">
								<li class="dropdown ${current == 'account' ? 'active' : '' }"><a href="#" class="dropdown-toggle" data-toggle="dropdown">Akun<span class="caret"></span></a>
									<ul class="dropdown-menu" role="menu">
										<security:authorize access="hasRole('ROLE_USER')">
											<li><a href='<spring:url value="/account.apsb"/>'>Akun</a></li>
											<li><a href='<spring:url value="/account/setting.apsb"/>'>Pengaturan</a></li>
										</security:authorize>
										<security:authorize access="hasRole('ROLE_ADMIN')">
											<li><a href='<spring:url value="/account/setting-admin.apsb"/>'>Pengaturan</a></li>
										</security:authorize>
										<li class="divider"></li>
										<li><a href='<spring:url value="/logout.apsb"/>'>Logout</a></li>
									</ul></li>
							</security:authorize>
						</ul>
					</div>
				</div>
				<!--.container-fluid -->
			</div>
			<!-- .navbar -->
		</div>
		<!-- .row -->
	</div>

	<div class="container">
		<tiles:insertAttribute name="body" />
	</div>
	<br>
	<footer>
		<div class="container">
			<tiles:insertAttribute name="footer" />
		</div>
	</footer>
</body>
</html>