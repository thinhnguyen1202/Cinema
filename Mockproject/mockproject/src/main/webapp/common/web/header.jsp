<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<header class="site-header">
	<div class="container">
		<div class="row">
			<div class="col-md-6">
				<nav class="header-nav">
					<ul>
						<li><a href="${pageContext.request.contextPath}/showtime">Lịch chiếu</a></li>
						
					</ul>
				</nav>
			</div>
			<div class="site-logo">
				<a href="<c:url value="/home" />"> <img
					src="<c:url value ='/template/web/images/Logo.png'/>" alt="photo">
				</a>
			</div>
			<div class="col-md-6">
				<div class="right-header">
					<form action="search" method="post" class="search-form">
						<input type="text" name="search-keyword"
							placeholder="Nhập từ khóa tìm kiếm...">
						<button type="submit" class="sm-s">
							<i class="fa fa-search"></i>
						</button>
					</form>
					<%
					if (session.getAttribute("user") == null) {
					%>
					<a class="login-icon" href="${pageContext.request.contextPath}/signin">
						<i class="fa fa-user"></i>
					</a>
					<%
					} else {
					%>
					<div class="user-drop-down">
						<div class="login-icon">
							<i class="fa fa-user"></i>
						</div>
						<ul class="menu">
							<li><a href="${pageContext.request.contextPath}/ho-so-cua-toi">Trang cá nhân</a></li>
							<li><a href="${pageContext.request.contextPath}/doi-mat-khau">Đổi mật khẩu</a></li>
							<li><a href="${pageContext.request.contextPath }/lich-su-mua-ve">Vé của tôi</a></li>
							<li><a href="${pageContext.request.contextPath }/logout">Đăng xuất</a></li>
						</ul>
					</div>
					<%
					}
					%>
				</div>
			</div>
		</div>
	</div>
</header>
<!-- .site-header -->