<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="row">
	<!-- column -->
	<div class="col-sm-12">
		<div class="card">
			<div class="card-body">
				<h4 class="card-title">Danh sách vé người dùng đã đặt</h4>
				<h4 class ="card-title">Số lượng vé đã đặt: ${soluong }</h4>
				<div class="table-responsive">
					<table class="table user-table no-wrap text-center align-middle"
						id="table-list-film">
						<thead>
							<tr>
								<th class="border-top-0 col-md-1">#</th>
								<th class="border-top-0 col-md-2">Tài khoản đặt</th>
								<th class="border-top-0 col-md-1">Suất chiếu</th>
								<th class="border-top-0 col-md-1">Tên phim</th>
								<th class="border-top-0 col-md-1">Tên phòng</th>
								<th class="border-top-0 col-md-1">Chỗ ngồi</th>
								<th class="border-top-0 col-md-1">Ngày đặt</th>
								<th class="border-top-0 col-md-1"><i class="fas fa-trash"></i></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="ticket" items="${listTicketAlreadyBooked}">
								<tr>
									<td class="col-md-1">${ticket.ticket_id }</td>
									<td class="col-md-2">${ticket.username }</td>
									<td class="col-md-1">${ticket.showtime_id }</td>
									<td class="col-md-1">${ticket.film_name }</td>
									<td class="col-md-1">${ticket.room_name }</td>
									<td class="col-md-1">${ticket.seat_name }</td>
									<td class="col-md-1">${ticket.created_at }</td>
									<td class="col-md-1"><a class="btn btn-danger text-light"
										href="${pageContext.request.contextPath}/admin/ticket/deleteticket/${ticket.ticket_id}"
										role="button">Xóa</a></td>
								</tr>
							</c:forEach>

						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>
