<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../template/taglib.jsp"%>

<h3>List of Users</h3>
<c:choose>
	<c:when test="${not empty users.content }">
	<div class="table-responsive">
		<table class="table table-bordered table-striped table-hover">
			<thead>
				<tr>
					<th>Nis</th>
					<th>Nama</th>
					<th>L/P</th>
					<th>Email</th>
					<th>Telp</th>
					<th>Alamat</th>
					<th>Pilih Tombol</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${users.content }" var="user">
					<tr>
						<td><c:out value="${user.nis }" /></td>
						<td><c:out value="${user.nickname }" /> <c:out value="${user.familyName }" /></td>
						<td><c:out value="${user.gender }" /></td>
						<td><c:out value="${user.email }" /></td>
						<td><c:out value="${user.phone }" /></td>
						<td><c:out value="${user.address }" /></td>
						<td>
							<a href='<spring:url value="/master/users/detail/${user.id }.apsb" />' class="btn btn-info btn-sm"><span class="glyphicon glyphicon-user"></span>Detail</a> 
							<a href='<spring:url value="/master/users/delete/${user.id }.apsb"/>' class="btn btn-sm btn-danger triggerRemove"><span class="glyphicon glyphicon-trash"></span>Hapus</a>
						</td>
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
						<li><a href='<spring:url value="/master/users/1.apsb"/>'>&lt;&lt;</a></li>
						<li><a href='<spring:url value="/master/users/${current - 1 }.apsb"/>'>&lt;</a></li>
					</c:otherwise>
				</c:choose>
				
				<!-- Page Number -->
				<c:forEach var="i" begin="${begin}" end="${end}">
					<c:choose>
						<c:when test="${i == current}">
							<li class="active"><a href='<spring:url value="/master/users/${i }.apsb"/>'><c:out value="${i}" /></a></li>
						</c:when>
						<c:otherwise>
							<li><a href='<spring:url value="/master/users/${i }.apsb"/>'><c:out value="${i}" /></a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				
				<!-- Next And Last Page -->
				<c:choose>
					<c:when test="${current == users.totalPages}">
						<li class="disabled"><a href="#">&gt;</a></li>
						<li class="disabled"><a href="#">&gt;&gt;</a></li>
					</c:when>
					<c:otherwise>
						<li><a href='<spring:url value="/master/users/${current + 1 }.apsb"/>'>&gt;</a></li>
						<li><a href='<spring:url value="/master/users/${users.totalPages }.apsb"/>'>&gt;&gt;</a></li>
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

<!-- Modal Remove -->
<div class="modal fade" id="modalRemove" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="sr-only">Tutup</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">Hapus Pengguna</h4>
			</div>
			<div class="modal-body">
				<strong> Anda Yakin untuk menghapus Pengguna ini? </strong>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Batal</button>
				<a href="" class="btn btn-danger btnRemove">Hapus</a>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
	$(document).ready(function() {
		$('.triggerRemove').click(function(e) {
			e.preventDefault();
			$('#modalRemove .btnRemove').attr("href", $(this).attr("href"));
			$('#modalRemove').modal();
		});
	});
</script>