<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- ============================================================== -->
<!-- Start Page Content -->
<!-- ============================================================== -->
<div class="row">
	<!-- column -->
	<div class="col-sm-12">
		<div class="card">
			<div class="card-body">
				<h4 class="card-title">Danh sách lịch chiếu hiện có</h4>
				<div class="row">
					<c:if test="${status == 1 }">
						<div class="alert alert-success" role="alert">
							<h4 class="alert-heading">${message }</h4>
						</div>
					</c:if>
					<c:if test="${status == -1 }">
						<div class="alert alert-danger" role="alert">
							<h4 class="alert-heading">${message }</h4>
						</div>
					</c:if>
					<%-- <c:if test="${status == -2 }">
						<div class="alert alert-warning" role="alert">
							<h4 class="alert-heading">${message }</h4>
						</div>
					</c:if> --%>
					<c:if test="${status == -3 }">
						<div class="alert alert-danger" role="alert">
							<h4 class="alert-heading">${message }</h4>
						</div>
					</c:if>

				</div>
				<div class="table-responsive">
					<table class="table user-table no-wrap text-center align-middle"
						id="table-list-film">
						<thead>
							<tr>
								<th class="border-top-0 col-md-1">#</th>
								<th class="border-top-0 col-md-2">Tên phim</th>
								<th class="border-top-0 col-md-1">Phòng</th>
								<th class="border-top-0 col-md-1">Bắt đầu</th>
								<th class="border-top-0 col-md-1">Kết thúc</th>
								<th class="border-top-0 col-md-1">Ngày</th>
								<th class="border-top-0 col-md-2">Poster</th>
								<th class="border-top-0 col-md-1">Ngày tạo</th>
								<th class="border-top-0 col-md-1"><i class=" fas fa-cogs"></i></th>
								<th class="border-top-0 col-md-1"><i class="fas fa-trash"></i></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="showtime" items="${showtimeList}">
								<tr>
									<td class="col-md-1">${showtime.id }</td>
									<td class="col-md-2">${showtime.film_name }</td>
									<td class="col-md-1">${showtime.room_name }</td>
									<td class="col-md-1">${showtime.start_time.getHours()}:${showtime.start_time.getMinutes()}</td>
									<td class="col-md-1">${showtime.end_time.getHours()}:${showtime.end_time.getMinutes()}</td>
									<td class="col-md-1">${showtime.showdate }</td>
									<td class="col-md-2"><a class="my-image-links"
										href="${pageContext.request.contextPath}/upload/${showtime.film_imagepath}"><img
											src="${pageContext.request.contextPath}/upload/${showtime.film_imagepath}"
											class="img-thumbnail" alt="image alt" /></a></td>
									<td class="col-md-1">${showtime.created_date}</td>
									<td class="col-md-1"><a class="btn btn-warning"
										href="${pageContext.request.contextPath}/admin/showtime/editshowtime/${showtime.id }"
										role="button">Sửa</a></td>
									<td class="col-md-1"><a class="btn btn-danger text-light"
										href="${pageContext.request.contextPath}/admin/showtime/deleteshowtime/${showtime.id}"
										role="button">Xóa</a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>

				</div>
				<div class="row mt-3">
					<a class="btn btn-primary btn-lg"
						href="${pageContext.request.contextPath}/admin/showtime/createfirstsence">Thêm
						mới suất chiếu</a>
				</div>
			</div>
		</div>
	</div>
</div>
