<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../template/taglib.jsp"%>
<h1>Latest News</h1>

<button class="btn btn-default btn-sm" data-toggle="modal" data-target="#addModal">
	<span class="glyphicon glyphicon-plus"></span>Tambah
</button>
<br>
<br>
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
				<th>Jml Penerima</th>
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
					<td><c:out value="${newsAdmin.quote}" /> Siswa</td>
					<security:authorize access="hasRole('ROLE_ADMIN')">
						<td>
							<a href='<spring:url value="/master/news-admin/update/${newsAdmin.id }.apsb"/>' class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-pencil"></span>Ubah</a>
							<a href='<spring:url value="/master/news-admin/delete/${newsAdmin.id }.apsb"/>' class="btn btn-danger btn-sm triggerRemove"><span class="glyphicon glyphicon-trash"></span>Delete</a>  
							<a href='<spring:url value="/master/news-admin/detail/${newsAdmin.id }.apsb"/>' class="btn btn-info btn-sm"><span class="glyphicon glyphicon-user"></span>Detail</a>
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

<form:form commandName="news" action="${pageContext.request.contextPath }/master/news-admin/save.apsb" method="post" cssClass="form-horizontal modalAdd">
	<!-- Add Modal -->
	<div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="addModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Tutup</span>
					</button>
					<h4 class="modal-title" id="addModalLabel">Tambah Berita</h4>
				</div>
				<div class="modal-body">

					<div class="form-group">
						<label for="jarak" class="col-sm-2 control-label">Judul:</label>
						<div class="col-sm-10">
							<form:input path="title" cssClass="form-control" />
							<form:errors path="title" />
						</div>
					</div>

					<div class="form-group">
						<label for="point" class="col-sm-2 control-label">Penjelasan:</label>
						<div class="col-sm-10">
							<form:input path="description" cssClass="form-control" />
							<form:errors path="description" />
						</div>
					</div>

					<div class="form-group">
						<label for="point" class="col-sm-2 control-label">Isi:</label>
						<div class="col-sm-10">
							<form:textarea path="content" cssClass="form-control" cols="3" rows="5"/>
							<form:errors path="content" />
						</div>
					</div>

					<div class="form-group">
						<label for="publishedDate" class="col-sm-2 control-label">Tanggal:</label>
						<div class="col-sm-10">
							<div class='input-group date' id='publishedDate'>
								<form:input path="publishedDate" cssClass="form-control" data-date-format="YYYY/MM/DD" />
								<span class="input-group-addon"> <span class="glyphicon glyphicon-calendar"></span>
								</span>
								<form:errors path="publishedDate" />
							</div>
						</div>
					</div>

					<div class="form-group">
						<label for="expiredDate" class="col-sm-2 control-label">Tanggal Ditutup:</label>
						<div class="col-sm-10">
							<div class='input-group date' id='expiredDate'>
								<form:input path="expiredDate" cssClass="form-control" data-date-format="YYYY/MM/DD" />
								<span class="input-group-addon"> <span class="glyphicon glyphicon-calendar"></span>
								</span>
								<form:errors path="expiredDate" />
							</div>
						</div>
					</div>

					
					<div class="form-group">
						<label for="quote" class="col-sm-2 control-label">Jml Penerima:</label>
						<div class="col-sm-10">
							<form:input path="quote" cssClass="form-control" />
							<form:errors path="quote" />
						</div>
					</div>

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Tutup</button>
					<input type="submit" class="btn btn-primary" value="Simpan">
				</div>
			</div>
		</div>
	</div>
</form:form>

<!-- Modal Remove -->
<div class="modal fade" id="modalRemove" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="sr-only">Tutup</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">Hapus Berits</h4>
			</div>
			<div class="modal-body">
				<strong>Apakah Anda yakin akan menghapus? </strong>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Batal</button>
				<a href="" class="btn btn-danger btnRemove">Hapus</a>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
	var now = new Date(); 
	var expired = moment().add(3, 'd');
	$(document).ready(function() {
		$('.triggerRemove').click(function(e) {
		 	e.preventDefault();
		 	$('#modalRemove .btnRemove').attr("href", $(this).attr("href"));
		 	$('#modalRemove').modal();
		});
		$('#publishedDate').datetimepicker({
			language : 'id',
			pickTime : false,
			minDate : now,
			maxDate : now
		});
		$('#expiredDate').datetimepicker({
			language : 'id',
			pickTime : false,
			minDate : expired,
			maxDate : moment().add(14, 'd')
		});
		$(".modalAdd").validate({
			rules : {
				title : {
					required : true
				},
				description : {
					required : true
				},
				content : {
					required : true
				},
				publishedDate : {
					required : true
				},
				expiredDate : {
					required : true
				},
				quote : {
					required : true
				}
			},
			messages : {
				title : {
					required : "Judul Harap diisi Kendaraan harap diisi"
				},
				description : {
					required : "Deskripsi harap diisi"
				},
				content : {
					required : "harap diisi"
				},
				publishedDate : {
					required : "harap diisi"
				},
				expiredDate : {
					required : "harap diisi"
				},
				quote : {
					required : "harap diisi"
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
