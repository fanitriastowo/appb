<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../template/taglib.jsp"%>
<h3 class="text-center">Daftar Penerima Beasiswa</h3>

<div class="row">
	<c:choose>
		<c:when test="${not empty spks}">
			<div class="table-responsive">
			<table class="table table-bordered table-striped table-hover" id="tableUser">
				<thead>
					<tr>
						<th>Nis</th>
						<th>Name</th>
						<th>Score</th>
						<th>Ket</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${spks }" var="spk">
						<tr>
							<td><c:out value="${spk.user.nis }" /></td>
							<td><c:out value="${spk.user.nickname }" /> <c:out value="${spk.user.familyName }" /></td>
							<td><c:out value="${spk.vectorV }" /></td>
							<td><c:out value="${spk.user.ket }" /></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			</div>
			<br/>
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