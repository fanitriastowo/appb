<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../template/taglib.jsp"%>
<h3 class="text-center">Daftar Calon Penerima Beasiswa</h3>

<div class="table-responsive">
	<table class="table table-bordered table-striped table-hover" id="userTable">
		<thead>
			<tr>
				<th>Nis</th>
				<th>Nama</th>
				<th>Email</th>
				<th>Rapot1</th>
				<th>Rapot2</th>
				<th>Jarak</th>
				<th>Kendaraan</th>
				<th>PenghasilanOrtu</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${userList }" var="users">
				<tr>
					<td><c:out value="${users.nis }" /></td>
					<td><c:out value="${users.nickname }" /> <c:out value="${users.familyName }" /></td>
					<td><c:out value="${users.email }" /></td>
					<td><c:out value="${users.rapot1 }" /></td>
					<td><c:out value="${users.rapot2 }" /></td>
					<td><c:out value="${users.jarak }" /></td>
					<td><c:out value="${users.kendaraan.name }" /></td>
					<td><c:out value="${users.penghasilanOrtu }" /></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
<div class="panel panel-default">
	<div class="panel-heading">
		<div class="text-center"><strong>Bobot</strong></div>
	</div>
	<div class="panel-body">
		<div class="table-responsive">
			<table class="table table-striped table-bordered table-condensed">
				<thead>
					<tr>
						<th>Raport1</th>
						<th>Raport2</th>
						<th>Jarak</th>
						<th>Kendaraan</th>
						<th>PenghasilanOrtu</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td><c:out value="${bobot.bRapot1 }" /></td>
						<td><c:out value="${bobot.bRapot2 }" /></td>
						<td><c:out value="${bobot.bJarak }" /></td>
						<td><c:out value="${bobot.bKendaraan }" /></td>
						<td><c:out value="${bobot.bPenghasilanOrtu }" /></td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</div>
<div class="row">
	<div class="col-lg-2 col-md-offset-10">
		<button class="btn btn-block btn-primary" data-toggle="modal" data-target="#bobotModal">Tentukan Bobot</button>
		<a href='<spring:url value="/master//hitungrekomendasi2.apsb" />' class="btn btn-block btn-primary">Next</a>
	</div>
</div>

<form:form commandName="bobot" action="${pageContext.request.contextPath }/master/hitungrekomendasi/bobot.apsb" method="POST" cssClass="form-horizontal">
	<!-- Modal Insert -->
	<div class="modal fade" id="bobotModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-sm">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">Tentukan Bobot Kriteria</h4>
				</div>
				<div class="modal-body">
					<form:hidden path="id" />
					<div class="form-group">
						<label for="raport1" class="col-sm-6 control-label">Rapot 1:</label>
						<div class="col-sm-6">
							<form:input path="bRapot1" cssClass="form-control slider"/>
						</div>
					</div>
					<div class="form-group">
						<label for="raport2" class="col-sm-6 control-label">Rapot 2:</label>
						<div class="col-sm-6">
							<form:input path="bRapot2" cssClass="form-control slider"/>
						</div>
					</div>
					<div class="form-group">
						<label for="jarak" class="col-sm-6 control-label">Jarak:</label>
						<div class="col-sm-6">
							<form:input path="bJarak" cssClass="form-control slider"/>
						</div>
					</div>
					<div class="form-group">
						<label for="kendaraan" class="col-sm-6 control-label">Kendaraan:</label>
						<div class="col-sm-6">
							<form:input path="bKendaraan" cssClass="form-control slider"/>
						</div>
					</div>
					<div class="form-group">
						<label for="penghasilanOrtu" class="col-sm-6 control-label">Penghasilan Orangtua:</label>
						<div class="col-sm-6">
							<form:input path="bPenghasilanOrtu" cssClass="form-control slider"/>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					<input type="submit" class="btn btn-primary" value="Next" />
				</div>
			</div>
		</div>
	</div>
</form:form>

<script type="text/javascript">
	$(document).ready(function() {
		$('#bRapot1').slider({
			min : 1,
			max : 5,
			step : 1,
			orientation : 'horizontal',
			value : $("#bRapot1").val(),
			selection : 'before',
			tooltip : 'show'
		});
		$('#bRapot2').slider({
			min : 1,
			max : 5,
			step : 1,
			orientation : 'horizontal',
			value : $("#bRapot2").val(),
			selection : 'before',
			tooltip : 'show'
		});
		$('#bJarak').slider({
			min : 1,
			max : 5,
			step : 1,
			orientation : 'horizontal',
			value : $("#bJarak").val(),
			selection : 'before',
			tooltip : 'show'
		});
		$('#bKendaraan').slider({
			min : 1,
			max : 5,
			step : 1,
			orientation : 'horizontal',
			value : $("#bKendaraan").val(),
			selection : 'before',
			tooltip : 'show'
		});
		$('#bPenghasilanOrtu').slider({
			min : 1,
			max : 5,
			step : 1,
			orientation : 'horizontal',
			value : $("#bPenghasilanOrtu").val(),
			selection : 'before',
			tooltip : 'show'
		});
		$("#userTable").dataTable({});
	});
</script>