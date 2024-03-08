<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<h1>Chỉnh sửa người dùng</h1>
<div class="row justify-content-center">
	<div class="col-6">
		<form:form class="form-floating" modelAttribute="userModel"
			method="POST" action="" >
			<div class="form-floating row">
				<form:input type="hidden" path="id" class="form-control" id="filmid"
					value="${userModel.id }" />
			</div>
			<div class="form-floating row">
				<form:input type="text" path="full_name" class="form-control"
					id="nameid"
					value="${userModel.full_name }" />
				<label for="nameid">Họ tên</label>
			</div>
			<div class="form-floating row">
				<form:input type="text" path="username" class="form-control"
					value="${userModel.username }" disabled="true" />
				<label for="usernameid">Tài khoản người dùng </label>
			</div>
			
			<div class="form-floating row">
				<form:input type="text" path="role" class="form-control" id="roleid"
					value="${userModel.role }" disabled="true"/>
				<label for="roleid">Vai trò </label>
			</div>
			<div class="form-floating row">
				<form:input type="email" path="email" class="form-control"
					id="emailid" value="${ userModel.email}" />
				<label for="emailid">Email</label>
			</div>
			<div class="form-floating row">
				<form:input type="tel"  path="phone" class="form-control"
					id="phoneid" value="${userModel.phone }" maxlength="10" />
				<label for="phoneid">Số điện thoại </label>
			</div>

			<div class="row justify-content-center mt-5">
				<button type="submit" class="btn btn-primary">Chỉnh sửa</button>
			</div>
		</form:form>
	</div>
</div>
