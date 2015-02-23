<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../template/taglib.jsp"%>
<h1>Petunjuk Penggunaan User</h1>

<div class="col-lg-2"></div>
<div class="col-lg-8">
	<div class="embed-responsive embed-responsive-16by9">
		<video width="720" height="480" controls src="${pageContext.request.contextPath }/static/video/user" poster="${pageContext.request.contextPath }/static/img/3.jpg" class="embed-responsive-item">
		</video>
	</div>
	<div class="text-center">
		Unduh versi PDF : <a href='<spring:url value = "/static/video/user.pdf" />'>Unduh</a>
	</div>
</div>
<div class="col-lg-2"></div>