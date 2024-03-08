<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Pay Ticket</title>
</head>
<body>
	<main class="site-main">
		<form class="pay-form" method="post" action="thanh-toan-thanh-cong">
			<h3>${film.name }</h3>
			<div class="if">
				<div class="pay-if">

					<h4 class="add">Metiz Cinema</h4>
					<span class="actors">Diễn viên: ${film.actors}</span> <span
						class="authors">Đạo diễn: ${film.producer }</span>
					<div class="time">
						<span class="time-p">Giờ chiếu:
						<c:if test="${showtime.start_time.getMinutes() < 10 }">											
							<strong>${ showtime.start_time.getHours()}:0${ showtime.start_time.getMinutes()}</strong>
							- <strong>${ showtime.end_time.getHours()}:${ showtime.end_time.getMinutes()}</strong>
						</c:if>
						<c:if test="${showtime.start_time.getMinutes() >= 10 }">							
							<strong>${ showtime.start_time.getHours()}:${ showtime.start_time.getMinutes()}</strong>
							- <strong>${ showtime.end_time.getHours()}:${ showtime.end_time.getMinutes()}</strong>
											
						</c:if></span>
					</div>
					<div class="room-chair">
						<span class="room">${room.name }</span> - <span class="room">Ghế 
						<c:forEach var="seat" items="${seatsActive}">
							${seat.name }
						</c:forEach>
						</span>
					</div>

				</div>
				<div class="pay-inf-u">
					<span class="name">${user.full_name }</span> <span class="email">${user.email }</span>
					<span class="phone">${user.phone }</span>
				</div>
			</div>
			<div class="tp">
				<span class="total">Tổng đơn hàng: ${showtime.showtime_price * totalSeatsBooking} VND</span>
				<button type="submit">Xác nhận đặt vé</button>
			</div>

		</form>
	</main>
</body>
</html>