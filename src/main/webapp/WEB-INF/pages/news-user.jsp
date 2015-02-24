<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../template/taglib.jsp"%>
<h1>Latest News</h1>

<c:if test="${success eq true }">
	<div class="alert alert-success">Anda Berhasil Mengikuti Seleksi Beasiswa, Silahkan Tunggu Pengumuman</div>
</c:if>

<c:if test="${sudahTerdaftar eq true }">
	<div class="alert alert-danger">Anda Sudah Terdaftar Dalam Seleksi Beasiswa</div>
</c:if>

<c:if test="${notCompleted eq true }">
	<div class="alert alert-danger">Daftar Nilai Anda Belum lengkap, Silahkan lengkapi terlebih dahulu di Akun Anda</div>
</c:if>

<c:if test="${isScanned eq true }">
	<div class="alert alert-danger">Anda belum mengupload hasil rapot, Silahkan lengkapi terlebih dahulu di Akun Anda</div>
</c:if>

<c:choose>
	<c:when test="${not empty newsList.content }">
		<div class="table-responsive">
			<table class="table table-hover">
				<thead>
					<tr>
						<th>Tanggal</th>
						<th>Tanggal Berakhir</th>
						<th>Judul</th>
						<th>Penjelasan</th>
						<security:authorize access="hasRole('ROLE_USER')">
							<th>Klik Tombol</th>
						</security:authorize>
						<security:authorize access="!isAuthenticated()">
							<th>Detail</th>
						</security:authorize>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${newsList.content }" var="newsList">
						<tr>
							<td><c:out value="${newsList.publishedDate}" /></td>
							<td><c:out value="${newsList.expiredDate}" /></td>
							<td><c:out value="${newsList.title}" /></td>
							<td><c:out value="${newsList.description}" /></td>

							<td>
							<c:choose>
								<c:when test="${user.joined eq true }">
									<c:choose>
										<c:when test="${newsList.completed eq false}">
											<security:authorize access="isAuthenticated()">
												<security:authorize access="hasRole('ROLE_USER')">
													<div class="alert alert-info">
														<p>Pengumuman masih dalam proses. Silahkan Menunggu</p>
													</div>
												</security:authorize>
											</security:authorize>
										</c:when>
										<c:otherwise>
											<security:authorize access="hasRole('ROLE_USER')">
												<a href='<spring:url value="/news/hasil/${newsList.id }.apsb"/>'>Lihat Pengumuman</a>
											</security:authorize>
										</c:otherwise>
									</c:choose>
								</c:when>
								<c:when test="${sekarang >= newsList.expiredDate }">
									<security:authorize access="isAuthenticated()">
										<security:authorize access="hasRole('ROLE_USER')">
											<div class="alert alert-info">
												<p>Beasiswa Sudah Ditutup</p>
											</div>
										</security:authorize>
									</security:authorize>
								</c:when>
								<c:otherwise>
									<security:authorize access="isAuthenticated()">
										<security:authorize access="hasRole('ROLE_USER')">
											<a href='<spring:url value="/news/join/${newsList.id }.apsb"/>' class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-share-alt"></span>Ikut</a>
										</security:authorize>
									</security:authorize>
								</c:otherwise>
							</c:choose>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<nav class="text-center">
			<ul class="pagination">

				<!-- Previous and First Page -->
				<c:choose>
					<c:when test="${current == 1}">
						<li class="disabled"><a href="#">&lt;&lt;</a></li>
						<li class="disabled"><a href="#">&lt;</a></li>
					</c:when>
					<c:otherwise>
						<li><a href='<spring:url value="/news/1.apsb"/>'>&lt;&lt;</a></li>
						<li><a href='<spring:url value="/news/${current - 1 }.apsb"/>'>&lt;</a></li>
					</c:otherwise>
				</c:choose>

				<!-- Page Number -->
				<c:forEach var="i" begin="${begin}" end="${end}">
					<c:choose>
						<c:when test="${i == current}">
							<li class="active"><a href='<spring:url value="/news/${i }.apsb"/>'><c:out value="${i}" /></a></li>
						</c:when>
						<c:otherwise>
							<li><a href='<spring:url value="/news/${i }.apsb"/>'><c:out value="${i}" /></a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>

				<!-- Next And Last Page -->
				<c:choose>
					<c:when test="${current == newsList.totalPages}">
						<li class="disabled"><a href="#">&gt;</a></li>
						<li class="disabled"><a href="#">&gt;&gt;</a></li>
					</c:when>
					<c:otherwise>
						<li><a href='<spring:url value="/news/${current + 1 }.apsb"/>'>&gt;</a></li>
						<li><a href='<spring:url value="/news/${newsList.totalPages }.apsb"/>'>&gt;&gt;</a></li>
					</c:otherwise>
				</c:choose>
			</ul>
		</nav>
	</c:when>
	<c:otherwise>
		<div class="text-center">
			<div class="alert alert-info">
				<p>Data Masih Kosong</p>
			</div>
		</div>
	</c:otherwise>
</c:choose>
