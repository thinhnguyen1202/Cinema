<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<h1>Thêm mới người dùng</h1>
<div class="row justify-content-center">

	<div class="col-6">
		<div class="row justify-content-center text-center ">
			<c:if test="${status == -1 }">
				<div class="alert alert-danger align-middle" role="alert">
					<h4 class="alert-heading">${message }</h4>
				</div>
			</c:if>
			<c:if test="${status == -4 }">
				<div class="alert alert-warning align-middle" role="alert">
					<h4 class="alert-heading">${message }</h4>
				</div>
			</c:if>
		</div>
		<form:form class="form-floating" modelAttribute="userModel"
			method="POST" action="" id="form-createuser">
			<div class="form-floating row">
				<form:input type="text" path="username" class="form-control"
					value="${userModel.username }" id="usernameid" />
				<label for="usernameid">Tài khoản </label><span class="form-message"></span>
			</div>
			<div class="form-floating row">
				<form:input type="password" path="password" class="form-control"
					value="${userModel.password }" id="passwordid" />
				<label for="passwordid">Mật khẩu  </label><span class="form-message"></span>
			</div>
			<div class="form-floating row">
				<form:input type="password" path="password2" class="form-control"
					value="${userModel.password2 }" id="password2id" />
				<label for="password2id">Nhập lại mật khẩu </label><span class="form-message"></span>
			</div>
			<div class="form-floating row">
			<label for="role">Role</label><span class="form-message"></span>
				<form:select path="role" cssClass="form-control form-control-lg"
					id="role" value="${ userModel.role}" multiple="false" >
					<form:options items1="role" value="ROLE_USER"/>
					<form:options items2="role" value="ROLE_ADMIN" />
				
				</form:select>
			</div>

			<div class="form-floating row">
				<form:input type="text" path="full_name" class="form-control"
					id="full_nameid" value="${userModel.full_name }" />
				<label for="full_nameid">Họ tên</label>
			</div>
			<div class="form-floating row">
				<form:input type="email" path="email" class="form-control"
					id="emailid" value="${ userModel.email}" />
				<label for="emailid">Email</label><span class="form-message"></span>
			</div>
			<div class="form-floating row">
				<form:input type="tel" path="phone" class="form-control"
					id="phoneid" value="${userModel.phone }" maxlength="10" />
				<label for="phoneid">Số điện thoại </label><span class="form-message"></span>
			</div>

			<div class="row justify-content-center mt-5">
				<button type="submit" class="btn btn-primary">Thêm mới</button>
			</div>
		</form:form>
	</div>
</div>
