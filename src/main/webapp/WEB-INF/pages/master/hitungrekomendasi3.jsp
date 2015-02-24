<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../template/taglib.jsp"%>
<h3 class="text-center">Rangking Penerima Beasiswa</h3>

<div class="row">
	<div class="table-responsive">
		<table class="table table-bordered table-striped table-hover" id="tableUser">
			<thead>
				<tr>
					<th>Nis</th>
					<th>Nama</th>
					<th>Ganjil</th>
					<th>Genap</th>
					<th>Jarak</th>
					<th>Kendaraan</th>
					<th>PenghasilanOrtu</th>
					<th>JumlahS ke(i)</th>
					<th>VectorS</th>
					<th>VectorV</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${userSpk }" var="users">
					<tr>
						<td><c:out value="${users.nis }" /></td>
						<td><c:out value="${users.nickname }" /></td>
						<td><c:out value="${users.rapot1 }" /></td>
						<td><c:out value="${users.rapot2 }" /></td>
						<td><c:out value="${users.jarak }" /></td>
						<td><c:out value="${users.kendaraan }" /></td>
						<td><c:out value="${users.penghasilanOrtu }" /></td>
						<td><c:out value="${users.jumlahS }" /></td>
						<td><c:out value="${users.vectorS }" /></td>
						<td><c:out value="${users.vectorV }" /></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>
<div class="row">
	<div class="panel panel-default">
		<div class="panel-body">
			<strong>*Ket Bobot :</strong> 
			Rapot1 = <strong>${bobot.raport1 }</strong>; 
			Rapot2 = <strong>${bobot.raport2 }</strong>; 
			Jarak = <strong>${bobot.jarak }</strong>; 
			PenghasilanOrtu = <strong>${bobot.penghasilanOrtu }</strong>; 
			Kendaraan = <strong>${bobot.kendaraan }</strong>;
			Jumlah = <strong>${bobot.jumlahBobot }</strong>
		</div>
		<div class="panel-body">
			<strong>*Normalisasi :</strong> 
			Rapot1 = <strong>${normalisasi.raport1 }</strong>; 
			Rapot2 = <strong>${normalisasi.raport2 }</strong>; 
			Jarak = <strong>${normalisasi.jarak }</strong>; 
			PenghasilanOrtu = <strong>${normalisasi.penghasilanOrtu }</strong>; 
			Kendaraan = <strong>${normalisasi.kendaraan }</strong>;
		</div>
		<div class="panel-footer">
			<strong>*Ket :</strong> 
			Ganjil = Nilai Rata-rata Rapot Semester Ganjil;
			Genap = Nilai Rata-rata Rapot Semester Genap
		</div>
	</div>
</div>
<div class="row">
	<div class="col-sm-offset-10 col-sm-2">
		<div class="btn-group" role="group">
			<a href='<spring:url value="/master/hitungrekomendasi/1.apsb"/>' class="btn btn-default">Batal</a> 
			<a href='<spring:url value="/master/hitungrekomendasi3/save.apsb"/>' class="btn btn-primary">Simpan</a>
		</div>
	</div>
</div>
<script type="text/javascript">
	$(document).ready(function() {
		$("#tableUser").dataTable({
			 "order": [[ 9, "desc" ]]
		});
	});
</script>