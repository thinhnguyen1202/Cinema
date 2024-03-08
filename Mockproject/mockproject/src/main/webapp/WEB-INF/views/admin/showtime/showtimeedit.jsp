<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<h1>Danh sách suất chiếu</h1>
<div class="row">
	<!-- column -->
	<div class="col-sm-12">
		<div class="card">
			<div class="card-body">

				<div class="table-responsive">
					<table class="table user-table no-wrap text-center align-middle"
						id="table-list-film">
						<thead>
							<tr>
								<th class="border-top-0 col-md-1">#</th>
								<th class="border-top-0 col-md-2">Tên phim</th>
								<th class="border-top-0 col-md-1">Tên phòng</th>
								<th class="border-top-0 col-md-1">Băt đầu</th>
								<th class="border-top-0 col-md-1">Kết thúc</th>
								<th class="border-top-0 col-md-1">Ngày</th>
								<th class="border-top-0 col-md-1">Ngày tạo</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="showtime" items="${allTheShowtime}">
								<tr>
									<td class="col-md-1">${showtime.id }</td>
									<td class="col-md-2">${showtime.film_name }</td>
									<td class="col-md-1">${showtime.room_name }</td>
									<td class="col-md-1">${showtime.start_time }</td>
									<td class="col-md-1">${showtime.end_time }</td>
									<td class="col-md-1">${showtime.showdate }</td>
									<td class="col-md-1">${showtime.created_date}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>

				</div>
				<form:form class="form-floating mt-5" modelAttribute="showtimeedit"
					method="POST" action="" id="form-showtimeedit">
					<div class="form-floating row mt-3">
						<form:input type="hidden" path="id"
							class="form-control" id="idid" value="${showtimeedit.id }" />
						<label for="idid"></label>
					</div>
					<div class="form-floating row mt-3">
						<form:input type="date" path="showDateString" class="form-control"
							id="dateid" value="${showtimeedit.showdate }" />
						<label for="dateid"> Ngày chiếu</label><span class="form-message"></span>
					</div>
					<div class="row mt-3">
						<form:label path="room_name"> Phòng chiếu </form:label>
						<form:select cssClass="form-control form-control-lg"
							path="room_name">
							<form:options items="${listRoomName }" />
						</form:select>
					</div>
					<div class="row mt-3">
						<form:label path="film_name"> Phim </form:label>
						<form:select cssClass="form-control form-control-lg"
							path="film_name" multiple="false">
							<form:options items="${listFilmName }" />
						</form:select>
					</div>
					<div class="form-floating row mt-3">
						<form:input cssClass="" type="time" path="start_time_string"
							class="form-control" id="start_timeid" value="${showtimeedit.start_time_time_type }" />
						<label for="start_timeid"> Giờ bắt đầu</label><span class="form-message"></span>
					</div>


					<div class="row mt-3">
						<button type="submit" class="btn btn-primary">Chỉnh sửa</button>
					</div>
				</form:form>
			</div>
		</div>
	</div>
</div>
