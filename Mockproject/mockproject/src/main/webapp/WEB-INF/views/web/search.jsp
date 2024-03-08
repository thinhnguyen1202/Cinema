<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Search</title>
</head>
<body>
	<main class="site-main home-main search">
		<div class="bn"
			style="background-image: url(<c:url value ='/template/web/images/searchbanner.png'/>);">
			<div class="ct">
				<h1>Tìm kiếm</h1>
				<span>Theo từ khóa '${input }'</span>
			</div>
		</div>
		<div class="list-film">
			<div class="tab-content">
				<div class="container">
					<div class="tab" id="playing">
						<div class="row">
							<c:forEach var="film" items="${Listfilms}">
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
												<span class="time"> ${film.duration * 60 } Phút</span> <span
													class="date">${film.created_at.getDate()}/${film.created_at.getMonth()}/${film.created_at.getYear() + 1900}</span>
											</div>
										</div>
									</a>
								</div>
							</c:forEach>
						</div>
					</div>
				</div>
			</div>
		</div>
	</main>
</body>
</html>