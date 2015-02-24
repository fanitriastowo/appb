<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../template/taglib.jsp"%>
<h1>Latest News</h1>

<c:choose>
	<c:when test="${not empty newsAdmin }">
	<div class="table-responsive">
		<table class="table table-striped table-bordered table-hover">
		<thead>
			<tr>
				<th>Tanggal</th>
				<th>Tanggal Ditutup</th>
				<th>Judul</th>
				<th>Penjelasan</th>
				<security:authorize access="hasRole('ROLE_ADMIN')">
					<th>Pilih Tombol</th>
				</security:authorize>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${newsAdmin.content }" var="newsAdmin">
				<tr>
					<td><c:out value="${newsAdmin.publishedDate}" /></td>
					<td><c:out value="${newsAdmin.expiredDate}" /></td>
					<td><c:out value="${newsAdmin.title}" /></td>
					<td><c:out value="${newsAdmin.description}" /></td>
	
					<security:authorize access="hasRole('ROLE_ADMIN')">
						<td>
							<a	href='<spring:url value="/master/news-admin/detail/${newsAdmin.id }.apsb"/>' class="btn btn-info btn-sm"><span class="glyphicon glyphicon-user"></span>Hitung</a>
						</td>
					</security:authorize>
				</tr>
			</c:forEach>
		</tbody>
		</table>
	</div>
		<nav class="text-center">
			<ul class="pagination">
		
				<!-- Previous and First Page -->
				<c:choose>
					<c:when test="${current == 1}">
						<li class="disabled"><a href="#">&lt;&lt;</a></li>
						<li class="disabled"><a href="#">&lt;</a></li>
					</c:when>
					<c:otherwise>
						<li><a href='<spring:url value="/master/news-admin/1.apsb"/>'>&lt;&lt;</a></li>
						<li><a href='<spring:url value="/master/news-admin/${current - 1 }.apsb"/>'>&lt;</a></li>
					</c:otherwise>
				</c:choose>
		
				<!-- Page Number -->
				<c:forEach var="i" begin="${begin}" end="${end}">
					<c:choose>
						<c:when test="${i == current}">
							<li class="active"><a href='<spring:url value="/news/${i }.apsb"/>'><c:out value="${i}" /></a></li>
						</c:when>
						<c:otherwise>
							<li><a href='<spring:url value="/master/news-admin/${i }.apsb"/>'><c:out value="${i}" /></a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
		
				<!-- Next And Last Page -->
				<c:choose>
					<c:when test="${current == newsAdmin.totalPages}">
						<li class="disabled"><a href="#">&gt;</a></li>
						<li class="disabled"><a href="#">&gt;&gt;</a></li>
					</c:when>
					<c:otherwise>
						<li><a href='<spring:url value="/master/news-admin/${current + 1 }.apsb"/>'>&gt;</a></li>
						<li><a href='<spring:url value="/master/news-admin/${newsAdmin.totalPages }.apsb"/>'>&gt;&gt;</a></li>
					</c:otherwise>
				</c:choose>
			</ul>
		</nav>
	</c:when>
	<c:otherwise>
		<div class="text-center">
			<div class="alert alert-info">
				Data Masih Kosong
			</div>
		</div>
	</c:otherwise>
</c:choose>