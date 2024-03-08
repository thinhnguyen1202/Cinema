<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Lịch sử mua vé</title>
</head>
<div class="container">
	<c:if test="${status == 1 }">
		<div class="row text-center align-center"
			style="display: flex; height: 300px; align-items: center; justify-content: center">
			<h1 class="h1">${message }</h1>
		</div>
		<c:if test="${statusForDelete == 1 }">
			<div class="alert alert-success" role="alert">
				${messageForDelete }
			</div>
		</c:if>
		<c:if test="${statusForDelete == -1 }">
			<div class="alert alert-danger" role="alert">
				${messageForDelete }
			</div>
		</c:if>
		<c:if test="${statusForBookingTickets == -1 }">
			<div class="alert alert-danger" role="alert">
				${messageForBookingTickets }
			</div>
		</c:if>
		<c:if test="${statusForBookingTickets == 1 }">
			<div class="alert alert-success" role="alert">
				${messageForBookingTickets }
			</div>
		</c:if>
		<div class="tab">
			<div class="" id="">
				<div class="table-responsive mt-5">
					<table class="table" id="table-tickets">
						<thead class=" text-center">
							<tr>
								<th scope="col">Mã vé</th>
								<th scope="col">Mã suất chiếu</th>
								<th scope="col">Tên Phim</th>
								<th scope="col">Phòng</th>
								<th scope="col">Ghế</th>
								<th scope="col">Giờ bắt đầu</th>
								<th scope="col">Ngày đặt vé</th>
								<th scope="col">Hủy vé</th>
							</tr>
						</thead>
						<tbody>


							<c:forEach var="ticket" items="${usertickets }">

								<tr>
									<td>${ticket.ticket_id }</td>
									<td>${ticket.showtime_id }</td>
									<td>${ticket.film_name }</td>
									<td>${ticket.room_name }</td>
									<td>${ticket.seat_name }</td>
									<td>${ticket.start_time_string}</td>
									<td>${ticket.created_at_string }</td>
									<c:if test="${ticket.canCancel  == 1}">
									<td class="tk"><a class="button" href="${pageContext.request.contextPath}/lich-su-mua-ve/deleteticket/${ticket.ticket_id}" role="button">Hủy</a></td>
									</c:if>
								</tr>

							</c:forEach>
						</tbody>
					</table>

				</div>
			</div>
		</div>
	</c:if>
	<c:if test="${status == -1 }">
		<div class=" row text-center align-center"
			style="height: 100vh; display: flex; justify-content: center; align-items: center;">
			<h1 class="h1">${message }</h1>
		</div>
	</c:if>

</div>