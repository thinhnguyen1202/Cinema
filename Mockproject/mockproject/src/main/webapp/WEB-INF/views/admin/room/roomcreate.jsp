<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>

<h1>Thêm mới phòng chiếu</h1>
<div class="row justify-content-center">
	<div class="col-6">
		<div class="row justify-content-center text-center ">
			<c:if test="${status == -1 }">
				<div class="alert alert-danger align-middle" role="alert">
					<h4 class="alert-heading">${message }</h4>
				</div>
			</c:if>
			<c:if test="${status == -2 }">
				<div class="alert alert-danger align-middle" role="alert">
					<h4 class="alert-heading">${message }</h4>
				</div>
			</c:if>
		</div>
		<form:form class="form-floating" modelAttribute="roomCreate"
			method="POST" action="">
			<div class="form-floating row mt-3">
				<form:input type="text" path="name" class="form-control"
					id="roomnameid" value="" />
				<label for="roomnameid">Tên phòng chiếu</label>
			</div>
			<div class="form-floating row mt-3">
				<form:checkbox path ="status" name="bth" value="0"/>Bình thường
            	<form:checkbox path ="status" name="bt" value="1"/>Bảo trì
             </div>
			
			<div class="form-floating row mt-3">
				<form:input type="number" path="row" class="form-control" id="rowid"
					value="${roomCreate.row }" min="5" max="10" />
				<label for="rowid">Số lượng hàng (Mặc định: 6)</label>
			</div>
			<div class="form-floating row mt-3">
				<form:input type="number" path="col" class="form-control" id="colid"
					value="${roomCreate.col }" min="5" max="10" />
				<label for="colid">Số lượng cột (Mặc định: 10)</label>
			</div>
			<div class="row mt-3">
				<button type="submit" class="btn btn-primary">Tạo mới</button>

			</div>

		</form:form>
	</div>
</div>