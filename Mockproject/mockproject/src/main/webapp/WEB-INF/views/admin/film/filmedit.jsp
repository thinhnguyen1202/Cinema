 <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<h1>Chỉnh sửa phim</h1>
<div class="row justify-content-center">
	<div class="col-6">
		<form:form class="form-floating" modelAttribute="editFilmWithImage"
			method="POST" action="" enctype="multipart/form-data" id="form-filmedit">
			<div class="form-floating row">
				<form:input type="hidden" path="id" class="form-control" id="filmid"
					value="${filmInfo.id }" />
				<label for="nameid">Tên Phim</label>
			</div>
			<div class="form-floating row">
				<form:input type="text" path="name" class="form-control" id="nameid"
					 value="${filmInfo.name }" />
				<label for="nameid">Tên Phim</label><span class="form-message"></span>
			</div>
			<div class="form-floating row">
				<form:input type="text" path="actors" class="form-control"
					id="actorsid"  value="${filmInfo.actors }" />
				<label for="actorsid">Diễn viên </label><span class="form-message"></span>
			</div>
			<div class="form-floating row">
				<form:input type="text" path="producer" class="form-control"
					id="producerid"  value="${filmInfo.producer }" />
				<label for="producerid">Đạo diễn </label><span class="form-message"></span>
			</div>
			<div class="form-floating row">
				<form:input type="date" path="created_at" class="form-control"
					id="dateid"  value="${ filmInfo.created_at}" />
				<label for="dateid">Ngày khởi chiếu</label><span class="form-message"></span>
			</div>
			<div class="form-floating row">
				<form:input type="number" path="duration" class="form-control"
					id="durationid"  value="${ filmInfo.duration}" />
				<label for="durationid">Thời lượng</label><span class="form-message"></span>
			</div>
			
			<div class="row mt-3">
				<label for="descriptionid mt-3">Miêu tả</label>
				<form:textarea style="height: 300px;" type="textarea"
					path="description" class="form-control" id="descriptionid"
					placeholder="3" value="${filmInfo.description }" />
				
			</div>

			<div class="">
				<p>Danh mục hiện tại đã có</p>
				<c:forEach var="nameOfCurrentCategory"
					items="${filmInfo.listCategoryName}">
					
					<button type="button" class="btn btn-primary" style = "display: inline-block;">
						<span class="badge badge-secondary">${nameOfCurrentCategory }</span>
					</button>
				</c:forEach>
			</div>
			<div class=" row">
				<form:label path="listCategoryName"> Chỉnh sửa lại danh mục </form:label>
				<form:select path="listCategoryName" multiple="true">
					<form:options items="${CategoryName }" />
				</form:select>
			</div>

		
			<div class="form-group row">
				<label for="fileDatasid" class="form-label"> Poster phim </label>
				<div class="row">
					<div class="col">
						<a class="my-image-links"
							href="${pageContext.request.contextPath}/upload/${filmInfo.imagepath}"><img
							src="${pageContext.request.contextPath}/upload/${filmInfo.imagepath}"
							class="img-thumbnail" alt="image alt" /></a>
					</div>


				</div>
				<form:input path="fileDatas" class="form-control" type="file"
					id="fileDatasid" value="${filmInfo.imagepath }" />

			</div>
			<div class="row justify-content-center">
				<button type="submit" class="btn btn-primary">Chỉnh sửa</button>
			</div>
		</form:form>
	</div>
</div>