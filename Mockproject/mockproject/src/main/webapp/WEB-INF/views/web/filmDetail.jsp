<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${film.name}</title>
</head>
<body>
	<main class="site-main film-detail">
		<section class="ad" style="background-image: linear-gradient(to top, rgba(0, 0, 0, 0.7) 0%, rgba(0, 0, 0, 0.7) 100%), url(${pageContext.request.contextPath}/upload/${film.imagepath});">
			<div class="container">
				<div class="row">
					<div class="col-md-3">
						<div class="poster">
							<img src="${pageContext.request.contextPath}/upload/${film.imagepath}" alt="photo">
						</div>
					</div>
					<div class="col-md-6">
						<div class="inf-detail">
							<h3 class="title">${film.name}</h3>
							<div class="rating">
								<% 
									int len = (int) request.getAttribute("starOfFilm");
									for(int i = 1; i <= 5; i ++) {
										if(i <= len) { %>
											<i class="fa fa-star on"></i>
										<% }else { %>
											<i class="fa fa-star off"></i>
										<% }
									} %>
							</div>
							<span class="category">
								<c:forEach var="cate" items="${film.listCategory}">
									<strong> ${cate.name} </strong>
								</c:forEach>
							
							</span>
							<div class="control">
								<a href="${pageContext.request.contextPath}/booking/${film.id}">Mua vé</a>
							</div>
							<p class="excerpt">${film.description }</p>
							<div class="inf">
								<div class="start">
									<span class="tt"><i class="fa fa-calendar"></i> Khởi
										chiếu</span> <span class="date">${film.created_at.getDate()}/${film.created_at.getMonth()+1}/${film.created_at.getYear() + 1900}</span>
								</div>
								<div class="time">
									<span class="tt"><i class="fa fa-clock-o"></i>Thời lượng</span>
									<span class="date"> ${film.duration * 60 } Phút</span>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</section>
		<section class="model-rating">
                <div class="container">
                    <div class="dialog">
                        <div class="content">
                            <form class="body" action="${pageContext.request.contextPath}/film/film-detail/${film.id}/rating" method="post">
                                <div class="heading">Đánh giá phim</div>
                                <div class="rating">
                                    <input type="radio" name="rating" value="5" id="5"><label for="5"><i class="fa fa-star"></i></label>
                                    <input type="radio" name="rating" value="4" id="4"><label for="4"><i class="fa fa-star"></i></label>
                                    <input type="radio" name="rating" value="3" id="3"><label for="3"><i class="fa fa-star"></i></label>
                                    <input type="radio" name="rating" value="2" id="2"><label for="2"><i class="fa fa-star"></i></label>
                                    <input type="radio" name="rating" value="1" id="1"><label for="1"><i class="fa fa-star"></i></label>
                                </div>
                                <div class="control">
                                    <button type="submit" id="rating-sm">Gửi</button>
                                </div>
                                <span>${feedback}</span>
                            </form>
                        </div>
                    </div>
                </div>
            </section>
	</main>
</body>
</html>