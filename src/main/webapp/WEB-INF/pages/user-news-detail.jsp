<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../template/taglib.jsp"%>

<div class="panel panel-info">
	<div class="panel-heading">
		<h5 class="text-center panel-title">
			Detail Berita
			<c:out value="${news.title }" />
		</h5>
	</div>
	<div class="panel-body">
		<table class="table table-striped">
			<thead>
				<tr>
					<th class="text-center">Tanggal</th>
					<th class="text-center">Tanggal Ditutup</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td class="text-center"><c:out value="${news.publishedDate }" /></td>
					<td class="text-center"><c:out value="${news.expiredDate }" /></td>
				</tr>
			</tbody>
		</table>

		<table class="table table-striped">
			<tbody>
				<tr>
					<th class="text-center">Penjelasan</th>
				</tr>
				<tr>
					<td class="text-center"><p><c:out value="${news.description }" /></p></td>
				</tr>
				<tr>
					<th class="text-center">Isi</th>
				</tr>
				<tr>
					<td class="text-center"><p><c:out value="${news.content }" /></p></td>
				</tr>
			</tbody>
		</table>
	</div>
</div>