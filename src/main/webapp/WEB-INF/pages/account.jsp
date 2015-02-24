<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../template/taglib.jsp"%>

<style>
.view {
	width: 140px;
	height: 140px;
	margin: 10px;
	float: left;
	border: 5px solid #fff;
	overflow: hidden;
	position: relative;
	text-align: center;
	box-shadow: 0px 0px 5px #aaa;
	cursor: default;
}

.view .mask {
	width: 140px;
	height: 140px;
	position: absolute;
	overflow: hidden;
	top: 0;
	left: 0;
}

.view img {
	display: block;
	position: relative;
	height: auto;
	width: auto;
	max-height: 140px;
	max-width: 140px;
	margin-left: auto;
	margin-right: auto;
	margin-bottom: auto;
	margin-top: auto;
}

.view a.info {
	background: url(static/img/link.png) center no-repeat;
	display: inline-block;
	text-decoration: none;
	padding: 0;
	text-indent: -9999px;
	width: 20px;
	height: 20px;
}

.third-effect .mask {
	opacity: 0;
	overflow: visible;
	border: 100px solid rgba(0, 0, 0, 0.7);
	box-sizing: border-box;
	transition: all 0.4s ease-in-out;
}

.third-effect a.info {
	position: relative;
	top: -10px; /* Center the link */
	opacity: 0;
	transition: opacity 0.5s 0s ease-in-out;
}

.third-effect:hover .mask {
	opacity: 1;
	border: 100px solid rgba(0, 0, 0, 0.7);
}

.third-effect:hover a.info {
	opacity: 1;
	transition-delay: 0.3s;
	cursor: hand;
	opacity: 1;
}

.btn-file {
	position: relative;
	overflow: hidden;
}

.btn-file input[type=file] {
	position: absolute;
	top: 0;
	right: 0;
	min-width: 100%;
	min-height: 100%;
	font-size: 100px;
	text-align: right;
	filter: alpha(opacity = 0);
	opacity: 0;
	outline: none;
	background: white;
	cursor: inherit;
	display: block;
}

input[readonly] {
	background-color: white !important;
	cursor: text !important;
}
</style>

<c:if test="${kosong eq true }">
	<div class="alert alert-danger">Anda belum memilih gambar</div>
</c:if>
<div class="col-md-2">
	<table class="table">
		<thead>
			<tr>
				<th><strong>Foto (140x140) </strong></th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>
					<div class="view third-effect">
						<c:choose>
							<c:when test="${account.picture == null }">
								<img src="${pageContext.request.contextPath }/static/img/blank_user.png" alt="Empty Image" class="img-responsive img-rounded" />
							</c:when>
							<c:otherwise>
								<img src="${pageContext.request.contextPath }/account/image.apsb" alt="My Avatar" class="img-responsive img-rounded" />
							</c:otherwise>
						</c:choose>
						<div class="mask">
							<a class="info" data-toggle="modal" data-target="#modalImage" href="#" title="Upload Image"></a>
						</div>
					</div>
				</td>
			</tr>
		</tbody>
	</table>
</div>

<div class="col-md-4">
	<table class="table">
		<thead>
			<tr>
				<th><strong>Profil Saya</strong></th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td><strong>NIS:</strong></td>
				<td><c:out value="${account.nis }" /></td>
			</tr>
			<tr>
				<td><strong>Telp:</strong></td>
				<td><c:out value="${account.phone }" /></td>
			</tr>
			<tr>
				<td><strong>Agama:</strong></td>
				<td><c:out value="${account.agama }" /></td>
			</tr>
			<tr>
				<td><strong>Alamat:</strong></td>
				<td><c:out value="${account.address }" /></td>
			</tr>
		</tbody>
	</table>
</div>
<div class="col-md-6">
	<table class="table">
		<thead>
			<tr>
				<th><strong>Akun Saya</strong></th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td><strong>Tanggal Lahir: </strong></td>
				<td><c:out value="${account.birthday }" /></td>
			</tr>
			<tr>
				<td><strong>Username: </strong></td>
				<td><c:out value="${account.username }" /></td>
			</tr>
			<tr>
				<td><strong>Nama: </strong></td>
				<td><c:out value="${account.nickname }" /> <c:out value="${account.familyName }" /></td>
			</tr>
			<tr>
				<td><strong>Email: </strong></td>
				<td><c:out value="${account.email }" /></td>
			</tr>
		</tbody>
	</table>
</div>
<div class="col-md-12">
	<div class="table-responsive">
		<table class="table table-striped table-bordered table-hover">
			<thead>
				<tr>
					<th>Ganjil</th>
					<th>Genap</th>
					<th>Jarak</th>
					<th>Kendaraan</th>
					<th>Penghasilan Ortu</th>
					<th>Rapot Scan</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td><c:out value="${account.rapot1 }" /></td>
					<td><c:out value="${account.rapot2 }" /></td>
					<td><c:out value="${account.jarak }" /></td>
					<td><c:out value="${account.kendaraan.name }" /></td>
					<td><c:out value="${account.penghasilanOrtu }" /></td>
					<td><c:choose>
							<c:when test="${account.scanned eq true }">
								<c:out value="Sudah" />
							</c:when>
							<c:otherwise>
								<c:out value="Belum" />
							</c:otherwise>
						</c:choose></td>
				</tr>
			</tbody>
		</table>
	</div>
</div>
<div class="col-md-12">
	<div class="col-md-4">
		<strong>Ket* : </strong>
		<p>Ganjil = Nilai Rata-rata Semester Ganjil</p>
		<p>Genap = Nilai Rata-rata Semester Genap</p>
	</div>
	<div class="col-md-offset-10">
		<a class="btn btn-block btn-primary" data-toggle="modal" data-target="#modalRapot" href="#">Unggah Rapot</a> 
		<a class="btn btn-block btn-primary" href='<spring:url value="/account/account-edit.apsb"/>'>Ubah Akun</a>
	</div>
</div>

<form role="form" action="${pageContext.request.contextPath }/account/upload.apsb" method="post" class="form-horizontal uploadForm" enctype="multipart/form-data">
	<!-- Modal Insert -->
	<div class="modal fade" id="modalImage" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">Unggah Foto</h4>
				</div>
				<div class="modal-body">

					<div class="form-group">
						<label for="file" class="col-sm-2 control-label">Unggah Foto:</label>
						<div class="col-sm-10">
							<div class="input-group">
								<span class="input-group-btn"> <span class="btn btn-primary btn-file"> Browse&hellip; <input type="file" accept="image/*" name="fileUpload">
								</span>
								</span> <input type="text" name="fileUploadName" class="form-control" readonly>
							</div>
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
</form>

<form role="form" action="${pageContext.request.contextPath }/account/upload/rapot.apsb" method="post" class="form-horizontal modalRapot" enctype="multipart/form-data">
	<!-- Modal Insert -->
	<div class="modal fade" id="modalRapot" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">Unggah Rapot</h4>
				</div>
				<div class="modal-body">

					<div class="form-group">
						<label for="rapot1" class="col-sm-3 control-label">Semester Ganjil:</label>
						<div class="col-sm-9">
							<div class="input-group">
								<span class="input-group-btn"> <span class="btn btn-primary btn-file"> Pilih&hellip; <input type="file" accept="image/*" name="rapot1">
								</span>
								</span> <input type="text" class="form-control" readonly>
							</div>
						</div>
					</div>

					<div class="form-group">
						<label for="rapot2" class="col-sm-3 control-label">Semester Genap:</label>
						<div class="col-sm-9">
							<div class="input-group">
								<span class="input-group-btn"> <span class="btn btn-primary btn-file"> Pilih&hellip; <input type="file" accept="image/*" name="rapot2">
								</span>
								</span> <input type="text" class="form-control" readonly>
							</div>
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
</form>

<script type="text/javascript">
	$(document).on('change', '.btn-file :file', function() {
		var input = $(this), numFiles = input.get(0).files ? input.get(0).files.length : 1, label = input.val().replace(/\\/g, '/').replace(/.*\//, '');
		input.trigger('fileselect', [ numFiles, label ]);
	});

	$(document).ready(function() {
		$('.btn-file :file').on('fileselect', function(event, numFiles, label) {
			var input = $(this).parents('.input-group').find(':text'), log = numFiles > 1 ? numFiles + ' files selected' : label;
			if (input.length) {
				input.val(log);
			} else {
				if (log)
					alert(log);
			}
		});
	});
</script>