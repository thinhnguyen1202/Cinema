<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
</script>
<h1>Thêm mới phim</h1>
<div class="row justify-content-center">
	<div class="col-6">
		<form:form class="form-floating" modelAttribute="uploadFilmwithImage"
			method="POST" id="form-createfilm" enctype="multipart/form-data">

			<div class="form-floating row">
				<form:input type="text" path="name" class="form-control" id="namefilm"
					 />
				<label for="namefilm">Tên Phim</label><span class="form-message"></span>
			</div>
			<div class="form-floating row">
				<form:input type="text" path="actors" class="form-control"
					id="actorsid" placeholder="Tom Hank" />
				<label for="actorsid">Diễn viên </label><span class="form-message"></span>
			</div>
			<div class="form-floating row">
				<form:input type="text" path="producer" class="form-control"
					id="producerid" placeholder="Marvel" />
				<label for="producerid">Đạo diễn </label><span class="form-message"></span>
			</div>
			<div class="form-floating row">
				<form:input type="date" path="showDateString" class="form-control"
					id="dateid" placeholder="3" />
				<label for="dateid">Ngày khởi chiếu</label><span class="form-message"></span>
			</div>
			<div class="form-floating row">
				<form:input type="number" path="duration" class="form-control"
					id="durationid" placeholder="3" />
				<label for="durationid">Thời lượng</label><span class="form-message"></span>
			</div>
			
			<div class="row mt-3 form-group shadow-textarea">
				<label for="descriptionid pd-3">Miêu tả</label>
				<form:textarea   cssClass = "form-control z-depth-1 col"
					path="description" class="" id="descriptionid" rows = "6"  />

			</div>
			<div class=" row mt-3">
				<form:label path="listCategoryName"> Danh mục </form:label>
				<form:select path="listCategoryName" multiple="true">
					<form:options items="${listCategoryName }" />
				</form:select>
			</div>

			
	
			<div class="form-group row">
				<label for="fileDatasid" class="form-label"> Poster phim </label><span class="form-message"></span>
				<form:input path="fileDatas" class="form-control" type="file"
					id="fileDatasid" />

			</div>
			<div class="row">
				<button type="submit" class="btn btn-primary">Tạo mới</button>
			</div>

		</form:form>
	</div>
</div>