<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Chọn lịch chiếu</title>
</head>
<body>
	<main class="site-main showtimes">
		<div class="st-bn"
			style="background-image: linear-gradient(to top, rgba(0, 0, 0, 0.7) 0%, rgba(0, 0, 0, 0.7) 100%), url(${pageContext.request.contextPath}/upload/${film.imagepath});">
			<div class="ct">
				<h1>${film.name }</h1>
				<p class="type">
					<c:forEach var="cate" items="${film.listCategory}">
						<strong> ${cate.name} </strong>
					</c:forEach>
				</p>
				<span class="time">${film.duration * 60 } Phút</span>
			</div>

		</div>
		<div class="list-showtimes">
			<div class="container">
				<div class="heading">
					<h2>Mua vé trực tuyến</h2>
				</div>
				<form method="post"
					action="${pageContext.request.contextPath}/booking/chon-ghe">
					<c:forEach var="date" items="${listDateofShowtime}">
						<div class="st-item">
							<div class="list-time">
								<span class="date">${date}</span>
								<c:forEach var="showtime" items="${listShowtime}">

									<c:if test="${ date ==  showtime.showdate}">
										<c:if test="${showtime.start_time.getMinutes() < 10 }">
											<input type="radio" id="book-time-${showtime.id}"
												name="book-time" value="${showtime.id}">
											<label for="book-time-${showtime.id}" class="time-item">
												<strong>${ showtime.start_time.getHours()}:0${ showtime.start_time.getMinutes()}</strong>
												- <strong>${ showtime.end_time.getHours()}:${ showtime.end_time.getMinutes()}</strong>
												  <p>${showtime.room_name }</p>
											</label>
										</c:if>
										<c:if test="${showtime.start_time.getMinutes() >= 10 }">
											<input type="radio" id="book-time-${showtime.id}"
												name="book-time" value="${showtime.id}">
											<label for="book-time-${showtime.id}" class="time-item">
												<strong>${ showtime.start_time.getHours()}:${ showtime.start_time.getMinutes()}</strong>
												- <strong>${ showtime.end_time.getHours()}:${ showtime.end_time.getMinutes()}</strong>
												<p>${showtime.room_name }</p>
											</label>
										</c:if>
									</c:if>
								</c:forEach>

							</div>
							<button type="submit" class="btn-booking-now">
								<i class="fa fa-arrow-right"></i>Mua vé ngay
							</button>

						</div>
					</c:forEach>
				</form>
			</div>
		</div>
	</main>
</body>
</html>