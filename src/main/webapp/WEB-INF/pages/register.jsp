<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../template/taglib.jsp"%>

<form:form commandName="user" action="${pageContext.request.contextPath }/register.apsb" cssClass="form-horizontal registerForm" method="POST">
	<c:if test="${success eq true }">
		<div class="alert alert-success">Registrasi Berhasil</div>
	</c:if>
	<div class="form-group">
		<label for="gender" class="col-sm-2 control-label">Jenis Kelamin:</label>
		<div class="col-sm-8">
			<form:select path="gender" cssClass="form-control" >
				<form:option value="L">Laki-laki</form:option>
				<form:option value="P">Perempuan</form:option>
			</form:select>
			<form:errors path="gender" />
		</div>
	</div>
	<div class="form-group">
		<label for="agama" class="col-sm-2 control-label">Agama:</label>
		<div class="col-sm-8">
			<form:select path="agama" cssClass="form-control" >
				<form:option value="Islam">Islam</form:option>
				<form:option value="Kristen">Kristen</form:option>
				<form:option value="Katolik">Katolik</form:option>
				<form:option value="Hindu">Hindu</form:option>
				<form:option value="Budha">Budha</form:option>
				<form:option value="Lain">Lain-lain</form:option>
			</form:select>
			<form:errors path="agama" />
		</div>
	</div>
	<div class="form-group">
		<label for="username" class="col-sm-2 control-label">Username:</label>
		<div class="col-sm-8">
			<form:input path="username" cssClass="form-control" placeholder="Username" />
			<form:errors path="username" />
		</div>
	</div>
	<div class="form-group">
		<label for="password" class="col-sm-2 control-label">Password:</label>
		<div class="col-sm-8">
			<form:password path="password" cssClass="form-control" placeholder="Password" />
			<form:errors path="password" />
		</div>
	</div>
	<div class="form-group">
		<label for="password" class="col-sm-2 control-label">Confirm Password:</label>
		<div class="col-sm-8">
			<input type="password" class="form-control" name="confirm_password" id="confirm_password" placeholder="Confirm Password" />
		</div>
	</div>
	<div class="form-group">
		<label for="email" class="col-sm-2 control-label">Email:</label>
		<div class="col-sm-8">
			<form:input path="email" cssClass="form-control" placeholder="Email" />
			<form:errors path="email" />
		</div>
	</div>
	<div class="form-group">
		<label for="phone" class="col-sm-2 control-label">Telp:</label>
		<div class="col-sm-8">
			<form:input path="phone" cssClass="form-control" placeholder="Phone Number" />
			<form:errors path="phone" />
		</div>
	</div>

	<div class="form-group">
		<div class="col-sm-offset-2 col-sm-10">
			<button type="submit" class="btn btn-primary">Daftar</button>
		</div>
	</div>
</form:form>


<script type="text/javascript">
	$(document).ready(function() {
		$(".registerForm").validate({
			rules : {
				username : {
					required : true,
					minlength : 3,
					remote : {
						url : '<spring:url value="/register/available.apsb" />',
						type : "get",
						data : {
							username : function() {
								return $("#username").val()
							}
						}
					}
				},
				password : {
					required : true,
					minlength : 3
				},
				confirm_password : {
					required : true,
					minlength : 3,
					equalTo : "#password"
				},
				email : {
					required : true,
					email : true
				},
				phone : {
					required : true,
					number : true
				}
			},
			messages : {
				username : {
					required : "Username Mohon diisi",
					minlength : "Minimal 3 Karakter",
					remote : "Username Sudah Tersedia"
				},
				password : {
					required : "Password Mohon diisi",
					minlength : "Minimal 3 Karakter"
				},
				confirm_password : {
					required : "Konfirmasi Password Mohon diisi",
					minlength : "Minimal 3 Karakter",
					equalTo : "Konfirmasi Password Tidak sama"
				},
				email : {
					required : "Email Mohon Diisi",
					email : "Format Email Tidak Valid"
				},
				phone : {
					required : "Nomor Telepon Harap Diisi",
					number : "Nomor Telepon Tidak Valid"
				}
			},
			highlight : function(element) {
				$(element).closest('.form-group').removeClass('has-success').addClass('has-error');
			},
			unhighlight : function(element) {
				$(element).closest('.form-group').removeClass('has-error').addClass('has-success');
			}
		});
	})
</script>