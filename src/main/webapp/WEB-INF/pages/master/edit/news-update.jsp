<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../../template/taglib.jsp"%>

<h3>Ubah Berita</h3>

<form:form action="${pageContext.request.contextPath }/master/news-admin/update/save.apsb" commandName="newsUpdate" method="POST" cssClass="form-horizontal newsForm">
	<form:hidden path="id" />

	<div class="form-group">
		<label for="title" class="col-sm-2 control-label">Judul:</label>
		<div class="col-sm-6">
			<form:input path="title" cssClass="form-control" />
			<form:errors path="title" />
		</div>
	</div>

	<div class="form-group">
		<label for="description" class="col-sm-2 control-label">Penjelasan:</label>
		<div class="col-sm-6">
			<form:textarea path="description" cssClass="form-control" rows="2" cols="3" />
			<form:errors path="description" />
		</div>
	</div>

	<div class="form-group">
		<label for="content" class="col-sm-2 control-label">Isi:</label>
		<div class="col-sm-6">
			<form:textarea path="content" cssClass="form-control" cols="3" rows="2" />
			<form:errors path="content" />
		</div>
	</div>
	
	<div class="form-group">
		<label for="quote" class="col-sm-2 control-label">Jml Penerima:</label>
		<div class="col-sm-6">
			<form:input path="quote" cssClass="form-control" />
			<form:errors path="quote" />
		</div>
	</div>

	<div class="form-group">
		<label for="publishedDate" class="col-sm-2 control-label">Tanggal:</label>
		<div class="col-sm-6">
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
		<div class="col-sm-6">
			<div class='input-group date' id='expiredDate'>
				<form:input path="expiredDate" cssClass="form-control" data-date-format="YYYY/MM/DD" />
				<span class="input-group-addon"> <span class="glyphicon glyphicon-calendar"></span>
				</span>
				<form:errors path="expiredDate" />
			</div>
		</div>
	</div>

	<div class="form-group">
		<div class="col-sm-offset-2 col-sm-10">
			<a href='<spring:url value="/master/news-admin/1.apsb"/>' class="btn btn-default">Kembali</a> 
			<input type="submit" class="btn btn-default btn-primary" value="Ubah">
		</div>
	</div>
</form:form>

<script type="text/javascript">
	$(document).ready(function() {
		$('#publishedDate').datetimepicker({
			language : 'id',
			pickTime : false
		});
		$('#expiredDate').datetimepicker({
			language : 'id',
			pickTime : false
		});
	});
	$(".newsForm").validate({
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
</script>