<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Đổi mật khẩu</title>
</head>
<main id="main" class="site-main login-main">
	<div class="container">
		<div class="row">
			<div class="col-md-6">
				<div class="form-login">
					<form action="doi-mat-khau" method="POST" id="form-register"
						role="form" autocomplete="off">
						<h3 class="title">Đổi mật khẩu</h3>
						<c:if test="${status == 1 }">
							<div class="alert alert-success text-center" role="alert">
								<h4>${message}</h4>
							</div>
						</c:if>
						<c:if test="${status == -1 }">
							<div class="alert alert-danger text-center" role="alert">
								<h4>${message}</h4>
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
						<label>Mật khẩu mới</label>
							<input type="password"
								class="form-control" id="password" name="password" 
								placeholder="Mật khẩu"> <span class="form-message"></span>
						</div>
						<div class="form-group">
						<label>Nhập lại mật khẩu mới</label>
							<input type="password"
								class="form-control" name="password2" id="password_confirmation" 
								placeholder="Nhập lại mật khẩu"> <span
								class="form-message"></span>
						</div>
						<div class="form-group">
							<input type="hidden" class="form-control"
								id="fullname" name="full_name" placeholder="Tên đầy đủ của bạn"
								value="${user.full_name }"> <span class="form-message"></span>
						</div>
						<div class="form-group">
							<input type="hidden"
								class="form-control" id="phone" name="phone" value="${user.phone}"
								placeholder="Số điện thoại"> <span class="form-message" ></span>
						</div>
						<div class="form-group">
							<input type="hidden" class="form-control"
								id="email" name="email" placeholder="Email"
								value="${user.email }"> <span class="form-message"></span>
						</div>
						<button class="form-submit" type="submit">Cập nhật mật khẩu</button>
					</form>
				</div>
			</div>
			
		</div>
	</div>
</main>