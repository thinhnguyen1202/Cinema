<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Lịch chiếu</title>
</head>
<body>
<main class="site-main showtimes">
		<div class="list-showtimes">
			<div class="container">
			<div class="heading">
					<h3>Chọn Ngày Chiếu</h3>
					<div class="calendar">
					<div class="month">
					  <strong id="center-month">
					     <script>
					       var d=new Date();
					       var m=d.getMonth()+1;
					       document.write("Tháng"+m);
				         </script>
					  </strong>
					</div>
		         <div class="row">
		          <ul class="days-movie-showing">
					<c:forEach var="date1" items="${listDateofShowtime}">
					 	<li class="list-time">
							<div class="dayofweek">
							   <script>
							    var day = ${date1.getDay()};
                                var dayarr =["Chủ Nhật","Thứ 2","Thứ 3","Thứ 4","Thứ 5","Thứ 6","Thứ 7"];
                                day=dayarr[day];
                                document.write(day);
							   </script>
							   </div>
						
							<a class ="badge" href="${pageContext.request.contextPath}/showtime/${date1}">
							
							<span class="time-item">${date1.getDate()}</span>
							
							</a>
							
						  
							</li>
						
					  </c:forEach>
					</ul>
				</div>		
				
		
			</div>
					
			</div>
			<div class="heading">
					<h3>Chọn Lịch Chiếu</h3>
					<form method="post"
					action="${pageContext.request.contextPath}/booking/chon-ghe">
					<c:forEach var="date" items="${listDateofShowtime1}">
						<div class="st-item">
							<div class="list-time">
								<span class="date">${date}</span>
								<c:forEach var="showtime" items="${listShowtime}">

									<c:forEach var="film" items="${film}">
								
								<c:if test ="${film == showtime.film_id }">
								 <div class="desc">
											<span class="title" title="${showtime.film_name }">${showtime.film_name }</span>
								</div>
								 <div class="thumb">
											 <img
												src="${pageContext.request.contextPath}/upload/${showtime.film_imagepath}"
												alt="photo"> 
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
										<button type="submit" class="btn-booking-now"><i class="fa fa-arrow-right"></i>Mua vé ngay</button>
									</c:if>	
												
								    </div>
								   </c:if>
								  </c:forEach>
								</c:forEach>

							</div>
						</div>
					</c:forEach>
				</form>
			
				</div>
			</div>
		</div>
				
	</main>
	
</body>
</html>