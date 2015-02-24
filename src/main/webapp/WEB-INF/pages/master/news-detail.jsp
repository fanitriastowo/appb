<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../template/taglib.jsp"%>

<h2>Daftar Peserta Seleksi Beasiswa <strong>${news.title }</strong></h2>
<div class="text-right">
	<span class="badge">Jumlah User : ${countUser }</span>
</div>
<c:choose>
	<c:when test="${not empty userList }">
	<div class="table-responsive">
		<table class="table table-hover table-striped table-bordered" id="userTable">
			<thead>
				<tr>
					<th>NIS</th>
					<th>Nama</th>
					<th>L/P</th>
					<th>Email</th>
					<th>Telp</th>
					<th>Alamat</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${userList }" var="user">
					<tr>
						<td><c:out value="${user.nis }" /></td>
						<td><c:out value="${user.nickname }" /> <c:out value="${user.familyName }" /></td>
						<td><c:out value="${user.gender }" /></td>
						<td><c:out value="${user.email }" /></td>
						<td><c:out value="${user.phone }" /></td>
						<td><c:out value="${user.address }" /></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
		<br />
		<div class="text-right">
			<c:choose>
				<c:when test="${news.completed eq true }">
					<a href='<spring:url value="/master/final/${news.id }.apsb"/>'	class="btn btn-primary">Lihat Hasil</a>
				</c:when>
				<c:otherwise>
					<a href='<spring:url value="/master/hitungrekomendasi/${news.id }.apsb"/>' class="btn btn-primary">Hitung Rekomendasi</a> 
				</c:otherwise>
			</c:choose>
		</div>
	</c:when>
	<c:otherwise>
		<div class="text-center">
			<div class="alert alert-info">Belum ada yang mendaftar beasiswa</div>
		</div>
	</c:otherwise>
</c:choose>

<script type="text/javascript">
	$(document).ready(function() {
		$('#userTable').dataTable({});
	});
</script>