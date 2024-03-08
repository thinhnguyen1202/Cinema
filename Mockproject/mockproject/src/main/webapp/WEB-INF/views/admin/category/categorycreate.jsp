<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>

<h1>Thêm mới danh mục phim</h1>
<div class="row justify-content-center">
	<div class="col-6">
		<form:form class="form-floating" modelAttribute="uploadCategory"
			method="POST" id="form-createcategory">
			<div class="form-floating row">
				
				<form:input type="text" path="name" class="form-control" id="nameid"
					 />
				<label for="nameid">Tên danh mục</label><span class="form-message"></span>
		
			</div>
			<div class="row">
				<button type="submit" class="btn btn-primary">Tạo mới</button>

			</div>

		</form:form>
	</div>
</div>