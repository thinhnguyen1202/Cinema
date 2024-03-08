<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="row">
	<div class="row">
		<c:if test="${status == 1 }">
			<div class="alert alert-success" role="alert">
				<h4 class="alert-heading">${message }</h4>
			</div>
		</c:if>
	</div>
	<div class="col-sm-12">
		<div class="card">
			<div class="card-body">
				<h4 class="card-title">Danh sách phòng chiếu</h4>
				<div class="table-responsive">
					<table class="table user-table no-wrap text-center align-middle"
						id="table-list-film">
						<thead>
							<tr>
								
								<th class="border-top-0 col-md-2">Tên phòng</th>
								<th class="border-top-0 col-md-2">Tình trạng</th>
								<th class="border-top-0 col-md-2">Tổng số ghế</th>
								
							</tr>
						</thead>
						<tbody>
							<c:forEach var="room" items="${listRoom}">
								<tr>
									
									<td class="col-md-2">${room.name }</td>
									<td class="col-md-2"><c:if test="${room.status== false}"><span>Bình Thường</span></c:if>
									<c:if test="${room.status==true}"><span>Bảo trì</span></c:if></td>	
									<td class="col-md-2">${room.total }</td>
									

								</tr>
							</c:forEach>

						</tbody>
					</table>
					<div class="row">

						<a class="btn btn-primary btn-lg"
							href="${pageContext.request.contextPath}/admin/room/create">
							Thêm mới phòng chiếu </a>

					</div>

				</div>
			</div>
		</div>
	</div>
</div>
