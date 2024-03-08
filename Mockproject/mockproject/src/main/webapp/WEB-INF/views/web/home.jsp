<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Metiz</title>
</head>
<body>
	<main class="site-main home-main">
		<div class="slider">
	
			<div class="owl-carousel main-slider">
				<c:forEach var="film" items="${filmsPlaying}">
					<div class="item">
						<a href="${pageContext.request.contextPath}/film/film-detail/${film.id}"><img
							src="${pageContext.request.contextPath}/upload/${film.imagepath}"
							alt=""></a>
					</div>
				</c:forEach>
			</div>
		</div>
		<div class="list-film">
			<div class="container">
				<ul class="tab-control">
					<li><a href="#playing">Đang chiếu</a></li>
					<li><a href="#coming">Sắp chiếu</a></li>
				</ul>
				<div class="tab-content">
					<div class="tab" id="playing">
						<div class="row">
							<c:forEach var="film" items="${filmsPlaying}">
								<div class="col-md-3">
									<a class="item"
										href="${pageContext.request.contextPath}/film/film-detail/${film.id}">
										<div class="thumb">
											<img
												src="${pageContext.request.contextPath}/upload/${film.imagepath}"
												alt="photo">
										</div>
										<div class="desc">
											<span class="title" title="${film.name }">${film.name }</span>
											<div class="info">
												<span class="time"> ${film.duration *60 } Phút</span>
												 <span class="date">${film.created_at.getDate()}/${film.created_at.getMonth()+1}/${film.created_at.getYear() + 1900 }</span>
											</div>
										</div>
									</a>
								</div>
							</c:forEach>
						</div>
						<div id="loadmore">
							<a href="#">Load more</a>
						</div>
					</div>
					<div class="tab" id="coming">
						<div class="row">
							<c:forEach var="filmcoming" items="${filmsComing}">
								<div class="col-md-3">

									<a class="item" href="${pageContext.request.contextPath}/film/film-detail/${filmcoming.id}">
										<div class="thumb">
											<img
												src="${pageContext.request.contextPath}/upload/${filmcoming.imagepath}"
												alt="photo">
										</div>
										<div class="desc">
											<span class="title" title="${filmcoming.name }">${filmcoming.name }</span>
											<div class="info"> 
												<span class="time"> ${filmcoming.duration * 60 } Phút</span> 
												<span class="date">${filmcoming.created_at.getDate()}/${filmcoming.created_at.getMonth()+1}/${filmcoming.created_at.getYear() + 1900 }</span>
											</div>
										</div>
									</a>
								</div>
							</c:forEach>
						</div>
						<div id="loadmore-coming">
							<a href="#">Load more</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</main>
	<!-- .site-main -->
</body>
</html>