<%@page import="mockproject.model.RoomModel"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Booking Seat</title>
</head>
<body>
	<main class="seat">
		<form
			action="${pageContext.request.contextPath}/booking/thanh-toan/${showtime.id}"
			method="post" id="seat">
			<div class="container">
				<div class="row">
					<div class="col-md-9">
						<div class="note">
							<div class="list-note">
								<div class="enable">
									<span></span> <strong>Ghế trống</strong>
								</div>
								<div class="selected">
									<span></span> <strong>Ghế đã chọn</strong>
								</div>
								<div class="selecting">
									<span></span> <strong>Ghế bạn đang chọn</strong>
								</div>
							</div>
						</div>
						<div class="screen">
							<h3 class="heading">MÀN HÌNH</h3>
							<div class="chairs">
								<c:forEach var="seat" items="${seats}">
									<c:if test="${seat.status == true}">
										<input type="checkbox" id="${seat.id}" name="seat"
											value="${seat.id}" disabled>
										<label class="chair selected" for="${seat.id}"> <span>${seat.name }</span>
										</label>

									</c:if>
									<c:if test="${seat.status == false}">
										<input type="checkbox" id="${seat.id}" name="seat"
											value="${seat.id}">
										<label class="chair" for="${seat.id}"> <span>${seat.name }</span>
										</label>
									</c:if>
								</c:forEach>
								<span class="form-message"></span>
							</div>
						</div>

					</div>
					<div class="col-md-3">
						<div class="bill">
							<div class="inf">
								<h4>Thông Tin Vé</h4>
								<span class="film-name">${film.name }</span> <span
									class="name-rap">PHÒNG RẠP METIZ CINEMA</span> <span class="time">Suất chiếu:
									<c:if test="${showtime.start_time.getMinutes() < 10 }">											
											
												<strong>${ showtime.start_time.getHours()}:0${ showtime.start_time.getMinutes()}</strong>
												- <strong>${ showtime.end_time.getHours()}:${ showtime.end_time.getMinutes()}</strong>
											
										</c:if>
										<c:if test="${showtime.start_time.getMinutes() >= 10 }">							
											
												<strong>${ showtime.start_time.getHours()}:${ showtime.start_time.getMinutes()}</strong>
												- <strong>${ showtime.end_time.getHours()}:${ showtime.end_time.getMinutes()}</strong>
											
										</c:if>
								</span> <span class="room">Phòng: ${room.name } </span>
								<div class="tbn-thtt">
									<button type="submit" class="pay">Tiến hành thanh toán</button>
									${message }
									
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</form>
	</main>
</body>
</html>