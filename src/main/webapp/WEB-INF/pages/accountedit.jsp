<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../template/taglib.jsp"%>

<form:form commandName="accountEdit" action="${pageContext.request.contextPath }/account/account-edit/update.apsb" cssClass="form-horizontal editForm" method="POST">
	<form:hidden path="id" />
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-info">
				<div class="panel-heading">
					<h4 class="text-center">
						<strong>Data Pribadi</strong>
					</h4>
				</div>
				<div class="panel-body">
					<div class="col-lg-6">
						<div class="form-group">
							<label for="nis" class="col-lg-2 col-md-2 col-sm-2 control-label">NIS:</label>
							<div class="col-lg-9 col-md-9 col-sm-9">
								<form:input path="nis" cssClass="form-control" />
								<form:errors path="nis" />
							</div>
						</div>
						<div class="form-group">
							<label for="nickname" class="col-lg-2 col-md-2 col-sm-2 control-label">Nama Panggilan:</label>
							<div class="col-lg-9 col-md-9 col-sm-9">
								<form:input path="nickname" cssClass="form-control" />
								<form:errors path="nickname" />
							</div>
						</div>
						<div class="form-group">
							<label for="familyName" class="col-lg-2 col-md-2 col-sm-2 control-label">Nama Lengkap:</label>
							<div class="col-lg-9 col-md-9 col-sm-9">
								<form:input path="familyName" cssClass="form-control" />
								<form:errors path="familyName" />
							</div>
						</div>
					</div>
					<div class="col-lg-6">
						<div class="form-group">
							<label for="phone" class="col-lg-2 col-md-2 col-sm-2 control-label">Telp:</label>
							<div class="col-lg-9 col-md-9 col-sm-9">
								<form:input path="phone" cssClass="form-control" />
								<form:errors path="phone" />
							</div>
						</div>
						<div class="form-group">
							<label for="address" class="col-lg-2 col-md-2 col-sm-2 control-label">Alamat:</label>
							<div class="col-lg-9 col-md-9 col-sm-9">
								<form:textarea path="address" cssClass="form-control" cols="3" rows="2" />
								<form:errors path="address" />
							</div>
						</div>
						<div class="form-group">
							<label for="birthday" class="col-lg-2 col-md-2 col-sm-2 control-label">Tanggal Lahir:</label>
							<div class="col-lg-9 col-md-9 col-sm-9">
								<div class='input-group date' id='datepicker1'>
									<form:input path="birthday" cssClass="form-control" data-date-format="YYYY/MM/DD" />
									<span class="input-group-addon"> <span class="glyphicon glyphicon-calendar"></span>
									</span>
									<form:errors path="birthday" />
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-info">
				<div class="panel-heading">
					<h4 class="text-center">
						<strong>Panel Nilai</strong>
					</h4>
				</div>
				<div class="panel-body">
					<div class="col-lg-6">
						<div class="form-group">
							<label for="rapot1" class="col-lg-5 col-md-2 col-sm-2 control-label">Rata-Rata Nilai Raport Ganjil:</label>
							<div class="col-lg-5 col-md-9 col-sm-9">
								<form:input path="rapot1" cssClass="form-control" />
								<form:errors path="rapot1" />
							</div>
						</div>
					</div>
					<div class="col-lg-6">
						<div class="form-group">
							<label for="rapot2" class="col-lg-5 col-md-2 col-sm-2 control-label">Rata-Rata Nilai Raport Ganjil:</label>
							<div class="col-lg-5 col-md-9 col-sm-9">
								<form:input path="rapot2" cssClass="form-control" />
								<form:errors path="rapot2" />
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-info">
				<div class="panel-heading">
					<h4 class="text-center">
						<strong>Panel Geografis</strong>
					</h4>
				</div>
				<div class="panel-body">
					<div class="col-lg-4">
						<div class="form-group">
							<label for="jarak" class="col-lg-4 col-md-2 col-sm-2 control-label">Jarak:</label>
							<div class="col-lg-6 col-md-9 col-sm-9">
								<form:input path="jarak" cssClass="form-control" />
								<form:errors path="jarak" />
							</div>
						</div>
					</div>
					<div class="col-lg-4">
						<div class="form-group">
							<label for="kendaraan" class="col-lg-4 col-md-2 col-sm-2 control-label">Kendaraan:</label>
							<div class="col-lg-6 col-md-9 col-sm-9">
								<form:select path="kendaraan" cssClass="form-control">
									<form:options items="${kendaraanList }" itemValue="name" itemLabel="name" />
								</form:select>
								<form:errors path="kendaraan" />
							</div>
						</div>
					</div>
					<div class="col-lg-4">
						<div class="form-group">
							<label for="penghasilanOrtu" class="col-lg-4 col-md-2 col-sm-2 control-label">Penghasilan Orangtua:</label>
							<div class="col-lg-6 col-md-9 col-sm-9">
								<form:input path="penghasilanOrtu" cssClass="form-control" />
								<form:errors path="penghasilanOrtu" />
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="col-md-1 col-md-offset-11">
		<div class="form-group">
			<input type="submit" class="btn btn-lg btn-primary" value="Simpan" />
		</div>
	</div>
</form:form>

<script type="text/javascript">
	$(function() {
		$('#datepicker1').datetimepicker({
			language : 'id',
			pickTime : false,
			viewMode : 'years',
			maxDate : new Date()
		});
		$(".editForm").validate({
			rules : {
				nis : {
					required : true,
					number : true,
					minlength : 3,
				},
				nickname : {
					required : true,
					minlength : 3
				},
				rapot1 : {
					required : true,
					number : true,
					minlength : 2,
					max : 100.0,
					min : 50.0
				},
				rapot2 : {
					required : true,
					number : true,
					minlength : 2,
					max : 100.0,
					min : 50.0
				},
				jarak : {
					required : true,
					number : true,
					minlength : 1,
					min : 2.0
				},
				penghasilanOrtu : {
					required : true,
					number : true,
					minlength : 6,
					min : 100000
				},
				inputfile : {
					required : true,
				}
			},
			messages : {
				nis : {
					required : "NIS Harap Diisi",
					number : "Input Hanya Berupa Angka",
					minlength : "Input Minimal 3 Karakter",
					remote : "Nis Sudah Tersedia"
				},
				nickname : {
					required : "Firstname Harap Diisi",
					minlength : "Input Minimal 3 Karakter"
				},
				rapot1 : {
					required : "Nilai Harap Diisi",
					number : "Input Hanya Berupa Angka",
					minlength : "Minimal 2 Digit Angka",
					max : "Maksimal nilai 100.0",
					min : "Minimal Nilai 50.0"
				},
				rapot2 : {
					required : "Nilai Harap Diisi",
					number : "Input Hanya Berupa Angka",
					minlength : "Minimal 2 Digit Angka",
					max : "Maksimal nilai 100.0",
					min : "Minimal Nilai 50.0"
				},
				jarak : {
					required : "Jarak Harap Diisi",
					number : "Input Hanya Berupa Angka",
					minlength : "Minimal 1 Digit Angka",
					min : "Minimal 2 Km"
				},
				penghasilanOrtu : {
					required : "Penghasilan Ortu Harap Diisi",
					number : "Input Hanya Berupa Angka",
					minlength : "Minimal 6 Digit",
					min : "Minimal Nilai 100000"
				},
				inputfile : {
					required : "Gambar Tidak boleh Kosong",
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