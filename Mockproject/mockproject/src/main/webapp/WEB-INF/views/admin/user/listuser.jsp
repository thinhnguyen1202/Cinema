<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="row">
	<div class="row">
		<!-- For message -->
		<div class="row">
			<c:if test="${status == 1 }">
				<div class="alert alert-success" role="alert">
					<h4 class="alert-heading">${message }</h4>
				</div>
			</c:if>
		</div>
		<!-- column -->
		<div class="col-sm-12">
			<div class="card">
				<div class="card-body">
					<h4 class="card-title">Danh sách người dùng</h4>
					<div class="table-responsive">
						<table class="table user-table no-wrap text-center align-middle"
							id="table-list-film">
							<thead>
								<tr>
									<th class="border-top-0 col-md-1">#</th>
									<th class="border-top-0 col-md-2">Tài Khoản</th>
									<th class="border-top-0 col-md-1">Vai trò</th>
									<th class="border-top-0 col-md-1">Email</th>
									<th class="border-top-0 col-md-1">Họ tên</th>
									<th class="border-top-0 col-md-1">Điện thoại</th>
									<th class="border-top-0 col-md-1">Thời gian tạo</th>
									<th class="border-top-0 col-md-1"><i class=" fas fa-cogs"></i></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="user" items="${listUser}">
									<tr>
										<td class="col-md-1">${user.id }</td>
										<td class="col-md-2">${user.username }</td>										
										<td class="col-md-1">${user.role }</td>
										<td class="col-md-1">${user.email }</td>
										<td class="col-md-1">${user.full_name }</td>
										<td class="col-md-1">${user.phone }</td>
										<td class="col-md-1">${user.created_at }</td>
										<td class="col-md-1"><a class="btn btn-warning"
											href="${pageContext.request.contextPath}/admin/user/edituser/${user.id}"
											role="button">Sửa</a></td>
									</tr>
								</c:forEach>

							</tbody>
						</table>

					</div>
					<div class="row mt-3">
						<a class="btn btn-primary btn-lg"
							href="${pageContext.request.contextPath}/admin/user/create">Thêm
							mới người dùng</a>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>