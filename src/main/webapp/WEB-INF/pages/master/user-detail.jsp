<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../template/taglib.jsp"%>

<h3>
	<c:out value="${detail.username }" />
</h3>
<h5>
	<c:out value="${detail.email }" />
</h5>
<div class="table-responsive">
<table class="table table-striped table-bordered table-hover">
	<thead>
		<tr>
			<th>Nama</th>
			<th>Telp</th>
			<th>Alamat</th>
			<th>Nama Seleksi Beasiswa</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td><c:out value="${detail.nickname }" /> <c:out value="${detail.familyName}" /></td>
			<td><c:out value="${detail.phone}" /></td>
			<td><c:out value="${detail.address }" /></td>
			<td><c:out value="${detail.news.title }" /></td>
		</tr>
	</tbody>
</table>
</div>