<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../template/taglib.jsp"%>
<div class="text-center">
	<div class="alert alert-danger">
		<h4>
			<c:out value="${messageProperty}" />
		</h4>
	</div>

	<hr>
	<h4>
		<c:out value=" Status Code : ${statusCode}" />
	</h4>
	<h4>
		<c:out value="URL : ${requestUrl}" />
	</h4>
</div>