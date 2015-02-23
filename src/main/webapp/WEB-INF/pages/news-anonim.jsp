<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../template/taglib.jsp"%>
<h1>Berita Beasiswa Terakhir</h1>

<c:choose>
	<c:when test="${not empty newsList.content }">
	<div class="table-responsive">
		<table class="table table-hover">
			<thead>
				<tr>
					<th>Tanggal</th>
					<th>Tanggal Berakhir</th>
					<th>Judul</th>
					<th>Penjelasan</th>
					<th>Detail</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${newsList.content }" var="newsList">
					<tr>
						<td><c:out value="${newsList.publishedDate}" /></td>
						<td><c:out value="${newsList.expiredDate}" /></td>
						<td><c:out value="${newsList.title}" /></td>
						<td><c:out value="${newsList.description}" /></td>
						<td><a href='<spring:url value="/news/detail/${newsList.id }.apsb"/>' class="btn btn-info btn-sm"><span class="glyphicon glyphicon-share-alt"></span>Detail</a></td>
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
					<li><a href='<spring:url value="/news-anonim/1.apsb"/>'>&lt;&lt;</a></li>
					<li><a href='<spring:url value="/newsnews-anonim/${current - 1 }.apsb"/>'>&lt;</a></li>
				</c:otherwise>
			</c:choose>

			<!-- Page Number -->
			<c:forEach var="i" begin="${begin}" end="${end}">
				<c:choose>
					<c:when test="${i == current}">
						<li class="active"><a href='<spring:url value="/news-anonim/${i }.apsb"/>'><c:out value="${i}" /></a></li>
					</c:when>
					<c:otherwise>
						<li><a href='<spring:url value="/news-anonim/${i }.apsb"/>'><c:out value="${i}" /></a></li>
					</c:otherwise>
				</c:choose>
			</c:forEach>

			<!-- Next And Last Page -->
			<c:choose>
				<c:when test="${current == newsList.totalPages}">
					<li class="disabled"><a href="#">&gt;</a></li>
					<li class="disabled"><a href="#">&gt;&gt;</a></li>
				</c:when>
				<c:otherwise>
					<li><a href='<spring:url value="/news-anonim/${current + 1 }.apsb"/>'>&gt;</a></li>
					<li><a href='<spring:url value="/news-anonim/${newsList.totalPages }.apsb"/>'>&gt;&gt;</a></li>
				</c:otherwise>
			</c:choose>
		</ul>
	</nav>
	</c:when>
	<c:otherwise>
		<div class="text-center">
			<div class="alert alert-info">
				<p>Data Masih Kosong</p>
			</div>
		</div>
	</c:otherwise>
</c:choose>
