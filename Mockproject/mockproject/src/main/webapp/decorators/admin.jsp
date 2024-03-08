<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"
	prefix="dec"%>
<!DOCTYPE html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!-- Tell the browser to be responsive to screen width -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Metiz</title>
<!-- Favicon icon -->
<link rel="icon" type="image/png" sizes="16x16"
	href="<c:url value ='/template/admin/assets/images/favicon.png'/>">
<!-- Custom CSS -->
<link
	href="<c:url value = '/template/admin/assets/plugins/chartist/dist/chartist.min.css'/>"
	rel="stylesheet">
<!-- Custom CSS -->
<link href="<c:url value = '/template/admin/css/style.min.css'/>"
	rel="stylesheet">
<link rel="stylesheet"
	href="<c:url value = '/template/admin/venobox/dist/venobox.min.css'/>" />
<link rel="stylesheet"
	href="<c:url value = '/template/admin/venobox/dist/venobox.min.css'/>" />

</head>

<body>
	
	<div class="preloader">
		<div class="lds-ripple">
			<div class="lds-pos"></div>
			<div class="lds-pos"></div>
		</div>
	</div>
	<!-- ============================================================== -->
	<!-- Main wrapper - style you can find in pages.scss -->
	<!-- ============================================================== -->
	<div id="main-wrapper" data-layout="vertical" data-navbarbg="skin6"
		data-sidebartype="full" data-sidebar-position="absolute"
		data-header-position="absolute" data-boxed-layout="full">
		<!-- ============================================================== -->
		<!-- Topbar header - style you can find in pages.scss -->
		<!-- ============================================================== -->
		<header class="topbar" data-navbarbg="skin6">
			<nav class="navbar top-navbar navbar-expand-md navbar-dark">
				<div class="navbar-header" data-logobg="skin6">
					<!-- ============================================================== -->
					<!-- Logo -->
					<!-- ============================================================== -->
					<a class="navbar-brand"
						href="${pageContext.request.contextPath }/admin"> <!-- Logo icon -->
						<b class="logo-icon"> <!--You can put here icon as well // <i class="wi wi-sunset"></i> //-->
							<!-- Dark Logo icon --> <img
							src="<c:url value ='/template/admin/assets/images/logo-icon.png'/>"
							alt="homepage" class="dark-logo" />

					</b> <!--End Logo icon --> <!-- Logo text --> <span class="logo-text">
							<!-- dark Logo text --> <img
							src="<c:url value ='/template/admin/assets/images/logo-text.png'/>"
							alt="homepage" class="dark-logo" />

					</span>
					</a>
					<!-- ============================================================== -->
					<!-- End Logo -->
					<!-- ============================================================== -->
					<!-- ============================================================== -->
					<!-- toggle and nav items -->
					<!-- ============================================================== -->
					<a
						class="nav-toggler waves-effect waves-light text-dark d-block d-md-none"
						href="javascript:void(0)"><i class="ti-menu ti-close"></i></a>
				</div>
				<!-- ============================================================== -->
				<!-- End Logo -->
				<!-- ============================================================== -->
				<div class="navbar-collapse collapse" id="navbarSupportedContent"
					data-navbarbg="skin6">

					<!-- ============================================================== -->
					<!-- toggle and nav items -->
					<!-- ============================================================== -->
					

					<!-- ============================================================== -->
					<!-- Right side toggle and nav items -->
					<!-- ============================================================== -->
					<ul class="navbar-nav me-auto mt-md-0"> 
						<!-- ============================================================== -->
						<!-- User profile and search -->
						<!-- ============================================================== -->
						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle waves-effect waves-dark" href="#"
							id="navbarDropdown" role="button" data-bs-toggle="dropdown"
							aria-expanded="false"> <img
								src="<c:url value= '/template/admin/assets/images/users/1.jpg' />"
								alt="user" class="profile-pic me-2">
								<c:set var="IntegerID" value = "${user.id }"/>
								${user.username } - ID: <c:out value="${IntegerID }"/>
						</a>
							<ul class="dropdown-menu show" aria-labelledby="navbarDropdown"></ul>
						</li>
					</ul>
					
					<ul class="navbar-nav">
						<!--  
						==============================================================
						Search -->
						<li class="nav-item hidden-sm-down">
							<form class="app-search ps-3">
								 <a href = "${pageContext.request.contextPath}/logout" class="btn btn-warning d-none d-md-inline-block text-white"><i
									class="fas fa-sign-out-alt"></i></a>
							</form>
						</li>
					</ul>
				</div>
			</nav>
		</header>
		<!-- ============================================================== -->
		<!-- End Topbar header -->
		<!-- ============================================================== -->
		<!-- ============================================================== -->
		<!-- Left Sidebar - style you can find in sidebar.scss  -->
		<!-- ============================================================== -->
		<aside class="left-sidebar" data-sidebarbg="skin6">
			<!-- Sidebar scroll-->
			<div class="scroll-sidebar">
				<!-- Sidebar navigation-->
				<nav class="sidebar-nav">
					<ul id="sidebarnav">
						
						<li class="sidebar-item"><a
							class="sidebar-link waves-effect waves-dark sidebar-link"
							href="<c:url value = '/admin/user'/>" aria-expanded="false"><i
								class="me-3 fas fa-user-circle" aria-hidden="true"></i><span
								class="hide-menu">Quản lý người dùng</span></a></li>
						<li class="sidebar-item"><a
							class="sidebar-link waves-effect waves-dark sidebar-link"
							href="<c:url value = '/admin/room'/>" aria-expanded="false">
								<i class="me-3 far fa-building" aria-hidden="true"></i><span
								class="hide-menu">Quản lý phòng chiếu</span>
						</a></li>
						<li class="sidebar-item"><a
							class="sidebar-link waves-effect waves-dark sidebar-link"
							href="<c:url value = '/admin/showtime'/>" aria-expanded="false"><i
								class="me-3 far fa-calendar-alt" aria-hidden="true"></i><span
								class="hide-menu">Quản lý suất chiếu</span></a></li>
						<li class="sidebar-item"><a
							class="sidebar-link waves-effect waves-dark sidebar-link"
							href="<c:url value = '/admin/film'/>" aria-expanded="false"><i
								class="me-3 fas fa-film" aria-hidden="true"></i><span
								class="hide-menu">Quản lý phim</span></a></li>
						<li class="sidebar-item"><a
							class="sidebar-link waves-effect waves-dark sidebar-link"
							href="<c:url value = '/admin/category'/>" aria-expanded="false"><i
								class="me-3 fas fa-hashtag" aria-hidden="true"></i><span
								class="hide-menu">Quản lý thể loại phim</span></a></li>
						<li class="sidebar-item"><a
							class="sidebar-link waves-effect waves-dark sidebar-link"
							href="<c:url value = '/admin/ticket'/>" aria-expanded="false"><i
								class="me-3 fas fa-ticket-alt" aria-hidden="true"></i><span
								class="hide-menu">Quản lý vé phim</span></a></li>

					</ul>

				</nav>
				<!-- End Sidebar navigation -->
			</div>
			<!-- End Sidebar scroll-->
		</aside>
		<!-- ============================================================== -->
		<!-- End Left Sidebar - style you can find in sidebar.scss  -->
		<!-- ============================================================== -->
		<!-- ============================================================== -->
		<!-- Page wrapper  -->
		<!-- ============================================================== -->
		<div class="page-wrapper">

			<!-- Container fluid  -->
			<!-- ============================================================== -->
			<div class="container-fluid">
				<!-- Render từng page vào giao diện này-->
				<dec:body />


			</div>
			<!-- ============================================================== -->
			<!-- End Container fluid  -->
			<!-- ============================================================== -->
			<!-- ============================================================== -->
			<!-- footer -->
			<!-- ============================================================== -->
			<footer class="footer text-center">
				Metiz <a href="#">Liên hệ</a>
			</footer>
			<!-- ============================================================== -->
			<!-- End footer -->
			<!-- ============================================================== -->
		</div>
		<!-- ============================================================== -->
		<!-- End Page wrapper  -->
		<!-- ============================================================== -->
	</div>
	<!-- ============================================================== -->
	<!-- End Wrapper -->
	<!-- ============================================================== -->
	<!-- ============================================================== -->
	<!-- All Jquery -->
	<!-- ============================================================== -->
	<script
		src="<c:url value= '/template/admin/assets/plugins/jquery/dist/jquery.min.js' />"></script>
	<!-- Bootstrap tether Core JavaScript -->
	<script
		src="<c:url value = '/template/admin/assets/plugins/bootstrap/dist/js/bootstrap.bundle.min.js'/>"></script>
	<script
		src="<c:url value = '/template/admin/js/app-style-switcher.js'/>"></script>
	<!--Wave Effects -->
	<script src="<c:url value = '/template/admin/js/waves.js'/>"></script>
	<!--Menu sidebar -->
	<script src="<c:url value = '/template/admin/js/sidebarmenu.js'/>"></script>
	<!--Custom JavaScript -->
	<script src="<c:url value = '/template/admin/js/custom.js'/>"></script>
	<script type="text/javascript"
			src="<c:url value='/template/admin/js/main.js' />"></script>
	<!--This page JavaScript -->
	<!--flot chart-->
	<script
		src="<c:url value = '/template/admin/assets/plugins/flot/jquery.flot.js'/>"></script>
	<script
		src="<c:url value = '/template/admin/assets/plugins/flot.tooltip/js/jquery.flot.tooltip.min.js'/>"></script>
	<script
		src="<c:url value = '/template/admin/js/pages/dashboards/dashboard1.js'/>"></script>
	<script
		src="<c:url value = '/template/admin/venobox/dist/venobox.min.js'/>"></script>
	<script type="text/javascript"
		src="<c:url value = 'https://cdn.datatables.net/1.12.1/js/jquery.dataTables.min.js'/>"></script>
	<script type="text/javascript"
		src="<c:url value = 'https://cdn.datatables.net/1.12.1/js/dataTables.bootstrap4.min.js'/>"></script>
	<script type="text/javascript"
		src="<c:url value = 'https://cdn.ckeditor.com/4.19.0/standard/ckeditor.js'/>">
		
	</script>

	<script>
		new VenoBox({
			selector : '.my-image-links',
			numeration : true,
			infinigall : true,
			share : true,
			spinner : 'rotating-plane'
		});
	</script>
	<script type="text/javascript">
		var table = $('#table-list-film');
		if (table) {
			table.DataTable();

		}
	</script>
	<script type="text/javascript">
		CKEDITOR.replace('descriptionid');
	</script>
</body>

</html>