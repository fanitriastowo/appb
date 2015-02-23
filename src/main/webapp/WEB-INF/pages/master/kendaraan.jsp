<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../template/taglib.jsp"%>
<!-- Button trigger modal -->
<div class="row">
	<div class="col-md-1 col-lg-1">
		<button class="btn btn-primary btn-block" data-toggle="modal" data-target="#myModal">
			<span class="glyphicon glyphicon-plus"></span>Tambah
		</button>
	</div>
</div>
<form:form commandName="kendaraan" action="${pageContext.request.contextPath }/master/kendaraan/save.apsb" method="POST" cssClass="form-horizontal kendaraanForm">
	<!-- Modal Insert -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">Tambah Kategori Jenis Kendaraan Siswa</h4>
				</div>
				<div class="modal-body">

					<div class="form-group">
						<label for="name" class="col-sm-2 control-label">Jenis Kendaraan:</label>
						<div class="col-sm-10">
							<form:input path="name" cssClass="form-control" />
							<form:errors path="name" />
						</div>
					</div>
					<div class="form-group">
						<label for="point" class="col-sm-2 control-label">Poin:</label>
						<div class="col-sm-10">
							<form:input path="point" cssClass="form-control" />
							<form:errors path="point" />
						</div>
					</div>

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Tutup</button>
					<input type="submit" class="btn btn-primary" value="Simpan" />
				</div>
			</div>
		</div>
	</div>
</form:form>
<h3>Daftar Jenis Kendaraan</h3>
<div class="table-responsive">
<table class="table table-bordered table-striped table-hover">
	<thead>
		<tr>
			<th>Kendaraan</th>
			<th>Poin</th>
			<th>Pilih Tombol</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${kendaraanList.content }" var="kendaraans">
			<tr>
				<td><c:out value="${kendaraans.name }" /></td>
				<td><c:out value="${kendaraans.point }" /></td>
				<td>
					<a href='<spring:url value="/master/kendaraan/update/${kendaraans.id }.apsb"/>' class="btn btn-sm btn-primary"><span class="glyphicon glyphicon-pencil"></span>Ubah</a> 
					<a href='<spring:url value="/master/kendaraan/delete/${kendaraans.id }.apsb"/>' class="btn btn-danger btn-sm triggerRemove"><span class="glyphicon glyphicon-trash"></span>Hapus</a>
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
				<li><a href='<spring:url value="/master/kendaraan/1.apsb"/>'>&lt;&lt;</a></li>
				<li><a href='<spring:url value="/master/kendaraan/${current - 1 }.apsb"/>'>&lt;</a></li>
			</c:otherwise>
		</c:choose>

		<!-- Page Number -->
		<c:forEach var="i" begin="${begin}" end="${end}">
			<c:choose>
				<c:when test="${i == current}">
					<li class="active"><a href='<spring:url value="/master/kendaraan/${i }.apsb"/>'><c:out value="${i}" /></a></li>
				</c:when>
				<c:otherwise>
					<li><a href='<spring:url value="/master/kendaraan/${i }.apsb"/>'><c:out value="${i}" /></a></li>
				</c:otherwise>
			</c:choose>
		</c:forEach>

		<!-- Next And Last Page -->
		<c:choose>
			<c:when test="${current == kendaraanList.totalPages}">
				<li class="disabled"><a href="#">&gt;</a></li>
				<li class="disabled"><a href="#">&gt;&gt;</a></li>
			</c:when>
			<c:otherwise>
				<li><a href='<spring:url value="/master/kendaraan/${current + 1 }.apsb"/>'>&gt;</a></li>
				<li><a href='<spring:url value="/master/kendaraan/${kendaraanList.totalPages }.apsb"/>'>&gt;&gt;</a></li>
			</c:otherwise>
		</c:choose>
	</ul>
</nav>
<div class="text-center">
	Unduh Versi PDF : <a href='<spring:url value="/master/kendaraan/exportPdf.apsb" />'>Unduh</a>
</div>

<!-- Modal Remove -->
<div class="modal fade" id="modalRemove" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel">Hapus Jenis Kendaraan</h4>
			</div>
			<div class="modal-body">
				<h4 class="text-center text-warning">Anda yakin untuk menghapus?</h4>
				<div class="alert alert-danger text-center" role="alert">
					<strong>Perhatian!</strong> Data Jenis Kendaraan yang dihapus dapat mempengaruhi data siswa
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Batal</button>
				<a href="" class="btn btn-danger removeBtn">Hapus</a>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
	$(document).ready(function() {
		$(".triggerRemove").click(function(e) {
			e.preventDefault();
			$("#modalRemove .removeBtn").attr("href", $(this).attr("href"));
			$("#modalRemove").modal();
		});
		$(".kendaraanForm").validate({
			rules : {
				name : {
					required : true,
				},
				point : {
					required : true,
					number : true,
					max : 100.0
				}
			},
			messages : {
				name : {
					required : "Jenis Kendaraan harap diisi"
				},
				point : {
					required : "Point harap diisi",
					number : "Dimohon hanya angka yang diinputkan",
					max : "Point Tidak Lebih dari 100.0"
				}
			},
			highlight : function(element) {
				$(element).closest('.form-group').removeClass('has-success').addClass('has-error');
			},
			unhighlight : function(element) {
				$(element).closest('.form-group').removeClass('has-error').addClass('has-success');
			}
		});
	});
</script>