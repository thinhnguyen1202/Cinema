<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="row">
	<div class="col-sm-12">
		<div class="card">
			<div class="card-body">
				<h4 class="card-title">Danh sách thể loại phim</h4>
				<div class="table-responsive">
					<table class="table user-table no-wrap text-center align-middle"
						id="table-list-film">
						<thead>
							<tr>
								<th class="border-top-0 col-md-1">#</th>
								<th class="border-top-0 col-md-2">Thể loại</th>
								
							</tr>
						</thead>
						<tbody>
							<c:forEach var="category" items="${listCategory}">
								<tr>
									<td class="col-md-1">${category.id }</td>
									<td class="col-md-2">${category.name }</td>
							
								</tr>
								
							</c:forEach>

						</tbody>
					</table>
					<div class="row">
						
							<a  class = "btn btn-primary btn-lg" href="${pageContext.request.contextPath}/admin/category/create"> Thêm mới danh mục phim </a>
						
					</div>

				</div>
			</div>
		</div>
	</div>
</div>
