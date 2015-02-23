<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../template/taglib.jsp"%>

<div class="page-header ">
	<h1>Selamat Datang Pengunjung</h1>
</div>

<div class="jumbotron">

	<div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
		<!-- Indicators -->
		<ol class="carousel-indicators">
			<li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
			<li data-target="#carousel-example-generic" data-slide-to="1"></li>
			<li data-target="#carousel-example-generic" data-slide-to="2"></li>
			<li data-target="#carousel-example-generic" data-slide-to="3"></li>
			<li data-target="#carousel-example-generic" data-slide-to="4"></li>
		</ol>

		<!-- Wrapper for slides -->
		<div class="carousel-inner" role="listbox">
			<div class="item active">
				<img class="img-responsive center-block" src="${pageContext.request.contextPath }/static/img/1.jpg" alt="Image 1">
				<div class="carousel-caption">Image 1</div>
			</div>
			<div class="item">
				<img class="img-responsive center-block" src="${pageContext.request.contextPath }/static/img/2.jpg" alt="Image 2">
				<div class="carousel-caption">Image 2</div>
			</div>
			<div class="item">
				<img class="img-responsive center-block" src="${pageContext.request.contextPath }/static/img/3.jpg" alt="Image 3">
				<div class="carousel-caption">Image 3</div>
			</div>
			<div class="item">
				<img class="img-responsive center-block" src="${pageContext.request.contextPath }/static/img/4.jpg" alt="Image 4">
				<div class="carousel-caption">Image 4</div>
			</div>
			<div class="item">
				<img class="img-responsive center-block" src="${pageContext.request.contextPath }/static/img/5.jpg" alt="Image 5">
				<div class="carousel-caption">Image 5</div>
			</div>
		</div>

		<!-- Controls -->
		<a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev"> <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span> <span class="sr-only">Previous</span></a> 
		<a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next"> <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span> <span class="sr-only">Next</span></a>
	</div>

	<br>
	<p>
		<strong>Aplikasi Penentuan Penerima Beasiswa</strong>, adalah sebuah sistem yang digunakan untuk menentukan penerima beasiswa di SMA Negeri Patikraja
	</p>
	<security:authorize access="isAnonymous()">
		<a href='<spring:url value="/help-default.apsb"/>' class="btn btn-lg btn-primary">Baca Selengkapnya</a>
	</security:authorize>
	<security:authorize access="hasRole('ROLE_USER')">
		<a href='<spring:url value="/help-user.apsb"/>' class="btn btn-lg btn-primary">Baca Selengkapnya</a>
	</security:authorize>
	<security:authorize access="hasRole('ROLE_ADMIN')">
		<a href='<spring:url value="/help-admin.apsb"/>' class="btn btn-lg btn-primary">Baca Selengkapnya</a>
	</security:authorize>
</div>
