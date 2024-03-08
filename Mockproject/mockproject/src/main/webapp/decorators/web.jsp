<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title><dec:title default="Trang chủ" /></title>
<link rel="icon" type="image/png" sizes="16x16"
	href="<c:url value ='/template/admin/assets/images/favicon.png'/>">
<!-- css -->
<link
	href="<c:url value='/template/web/libs/bootstrap/css/bootstrap.min.css' />"
	rel="stylesheet" />
<link
	href="<c:url value='/template/web/libs/font-awesome/css/font-awesome.min.css' />"
	rel="stylesheet" />
<link
	href="<c:url value='/template/web/libs/owl-carousel/assets/owl.carousel.css' />"
	rel="stylesheet" />
<link href="<c:url value='/template/web/css/style.css' />"
	rel="stylesheet" />
</head>
<body>
	<div id="wrapper">
		<!-- header -->
		<%@ include file="/common/web/header.jsp"%>
		<!-- header -->

		<dec:body />

		<!-- footer -->
		<%@ include file="/common/web/footer.jsp"%>
		<!-- footer -->

		<script type="text/javascript"
			src="<c:url value='/template/web/libs/jquery/jquery-1.12.4.min.js' />"></script>
		<script type="text/javascript"
			src="<c:url value='/template/web/libs/owl-carousel/owl.carousel.js' />"></script>
		<script type="text/javascript"
			src="<c:url value='/template/web/js/main.js' />"></script>
		<script type="text/javascript"
			src="<c:url value = 'https://cdn.datatables.net/1.12.1/js/jquery.dataTables.min.js'/>"></script>
	</div>
	<script type="text/javascript">
		var table = $('#table-tickets');
		if (table) {
			table.DataTable();

		}
	</script>

</body>
</html>