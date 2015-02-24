<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../template/taglib.jsp"%>
<h3 class="text-center">Daftar Ranking Penerima Beasiswa</h3>

<div class="row">
	<c:choose>
		<c:when test="${not empty spks}">
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
						<th>VectorS</th>
						<th>Jumlah VectorS</th>
						<th>VectorV</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${spks }" var="spk">
						<tr>
							<td><c:out value="${spk.user.nis }" /></td>
							<td><c:out value="${spk.user.nickname }" /> <c:out value="${spk.user.familyName }" /></td>
							<td><c:out value="${spk.rapot1 }" /></td>
							<td><c:out value="${spk.rapot2 }" /></td>
							<td><c:out value="${spk.jarak }" /></td>
							<td><c:out value="${spk.kendaraan }" /></td>
							<td><c:out value="${spk.penghasilanOrtu }" /></td>
							<td><c:out value="${spk.jumlahS }" /></td>
							<td><c:out value="${spk.vectorS }" /></td>
							<td><c:out value="${spk.vectorV }" /></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			</div>
			<div class="panel panel-default">
				<div class="panel-body text-center">
					<strong>*Ket Bobot :</strong> 
					Ganjil = <strong><c:out value="${bobot.bRapot1 }"/> </strong>; 
					Genap = <strong><c:out value="${bobot.bRapot2 }"/> </strong>; 
					Jarak = <strong><c:out value="${bobot.bJarak }"/> </strong>; 
					PenghasilanOrtu = <strong><c:out value="${bobot.bPenghasilanOrtu }"/> </strong>; 
					Kendaraan = <strong><c:out value="${bobot.bKendaraan }"/> </strong>;
					Jumlah = <strong><c:out value="${bobot.bRapot1 + bobot.bRapot2 + bobot.bJarak + bobot.bPenghasilanOrtu + bobot.bKendaraan } " /></strong>
				</div>
				<div class="panel-footer">
					<strong>*Ket :</strong> 
					Ganjil = Nilai Rata-rata Rapot Semester Ganjil
					Genap = Nilai Rata-rata Rapot Semester Genap
				</div>
			</div>
			<br/>
			<security:authorize access="hasRole('ROLE_ADMIN')">
				<div class="text-center">
					<p>Cetak Laporan : <a href='<spring:url value="/master/final/exportPdf/${bobot.news.id }.apsb" />'>Cetak</a></p>
					<p>Cetak Detail : <a href='<spring:url value="/master/final/exportDetailPdf/${bobot.news.id }.apsb" />'>Cetak</a></p>
				</div>
			</security:authorize>
		</c:when>
		<c:otherwise>
		<div class="text-center">
			<div class="alert alert-info">
				<p>Data Masih Kosong</p>
			</div>
		</div>
		</c:otherwise>
	</c:choose>
</div>
<script type="text/javascript">
	$(document).ready(function() {
		$("#tableUser").dataTable({
			 "order": [[ 9, "desc" ]]
		});
	});
</script>
