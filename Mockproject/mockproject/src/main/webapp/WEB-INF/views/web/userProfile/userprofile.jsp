<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Hồ sơ của tôi</title>
</head>
<main id="main" class="site-main login-main">
	<div class="container">
		<div class="row">
			<div class="col-md-6">
				<div class="form-login">
					<form action="ho-so-cua-toi" method="POST" id="form-register"
						role="form" autocomplete="off">
						<h3 class="title">Hồ sơ của tôi</h3>
						<c:if test="${statusforuserprofile == 1 }">
							<div class="alert alert-success text-center" role="alert">
								<h4>${messageforuserprofile }</h4>
							</div>
						</c:if>
						<c:if test="${statusforuserprofile == -1 }">
							<div class="alert alert-danger text-center" role="alert">
								<h4>${messageforuserprofile }</h4>
							</div>
						</c:if>
						<div class="form-group">
							<c:set var="IntegerID" value="${user.id }" />
							<input type="hidden" class="form-control" name="id"
								value="${IntegerID}" readonly> <span
								class="form-message"></span>
						</div>
						<div class="form-group">
							<label>Tài khoản</label> <input type="text" class="form-control"
								name="username" id="username" value="${user.username }" readonly>
							<span class="form-message"></span>
						</div>
						<div class="form-group">
							<input type="hidden"
								class="form-control" id="password" name="password" value="${user.password}"
								placeholder="Mật khẩu"> <span class="form-message"></span>
						</div>
						<div class="form-group">
							<input type="hidden"
								class="form-control" name="password2" id="password_confirmation" value="${user.password}"
								placeholder="Nhập lại mật khẩu"> <span
								class="form-message"></span>
						</div>
						<div class="form-group">
							<label>Họ tên</label> <input type="text" class="form-control"
								id="fullname" name="full_name" placeholder="Tên đầy đủ của bạn"
								value="${user.full_name }"> <span class="form-message"></span>
						</div>
						<div class="form-group">
							<label>Số điện thoại</label> <input type="tel"
								class="form-control" id="phone" name="phone" value="${user.phone}"
								placeholder="Số điện thoại"> <span class="form-message"
								></span>
						</div>
						<div class="form-group">
							<label>Email</label> <input type="email" class="form-control"
								id="email" name="email" placeholder="Email"
								value="${user.email }"> <span class="form-message"></span>
						</div>
						<button class="form-submit" type="submit">Cập nhật tài
							khoản</button>
					</form>
				</div>
			</div>
			
		</div>
	</div>
</main>