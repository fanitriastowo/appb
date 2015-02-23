<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../../template/taglib.jsp"%>

<h3>Edit Kendaraan</h3>

<form:form action="${pageContext.request.contextPath }/master/kendaraan/update.apsb" commandName="kendaraanUpdate" method="POST" cssClass="form-horizontal kendaraanForm">
	<form:hidden path="id" />

	<div class="form-group">
		<label for="jarak" class="col-sm-2 control-label">Nama:</label>
		<div class="col-sm-6">
			<form:input path="name" cssClass="form-control" />
			<form:errors path="name" />
		</div>
	</div>

	<div class="form-group">
		<label for="point" class="col-sm-2 control-label">Poin:</label>
		<div class="col-sm-6">
			<form:input path="point" cssClass="form-control" />
			<form:errors path="point" />
		</div>
	</div>

	<div class="form-group">
		<div class="col-sm-offset-2 col-sm-10">
			<a href='<spring:url value="/master/kendaraan/1.apsb"/>' class="btn btn-default">Kembali</a> <input type="submit" class="btn btn-default btn-primary" value="Ubah">
		</div>
	</div>
</form:form>

<script type="text/javascript">
	$(document).ready(function() {
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
					max : "Point Tidak Lebih Dari 100.0"
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