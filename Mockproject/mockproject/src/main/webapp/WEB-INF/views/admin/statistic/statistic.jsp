<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="row">

	<div class="col-sm-4">
		<div class="card">
			<div class="card-body ">
				<h4 class="d-flex align-items-center card-title">Doanh thu
					ngày hôm qua (${yesterday })</h4>
				<div class="text-end">
					<h2 class="font-light mb-0">${incomyesterday }VND</h2>
					<span class="text-muted"></span>
				</div>


			</div>
		</div>
	</div>
	<div class="col-sm-4">
		<div class="card">
			<div class="card-body ">
				<h4 class="d-flex align-items-center card-title">Doanh thu này hôm nay(${today} )</h4>

				<div class="text-end">
					<h2 class="font-light mb-0">${incometoday }VND</h2>
					<span class="text-muted"></span>
				</div>


			</div>
		</div>
	</div>
	
	<div class="col-sm-4">
		<div class="card">
			<div class="card-body">
				<h4 class="card-title">Doanh thu theo tuần (${past7days } đến
					${today })</h4>
				<h4 class="card-title"></h4>
				<div class="text-end">
					<h2 class="font-light mb-0">${incomeweek }VND</h2>
				</div>
			</div>
		</div>
	</div>
	<!-- Column -->
	<c:if test="${status == -1}">
		<div class="alert alert-warning text-center" role="alert">
			<h4 class="alert-heading">${message }</h4>
		</div>
	</c:if>

	<div class="row">
		<div class="col col-5 form-floating d-flex justify-content-start">
			<h5>Từ ngày</h5>
		</div>
		<div class="col col-5  form-floating d-flex justify-content-start">
			<h5>Đến ngày</h5>
		</div>
	</div>
	<form class="row" method="GET" action="${pageContext.request.contextPath}/admin/datetodate">
		<div class="col col-5 form-floating d-flex justify-content-end">
			<input type="date" id="pastid" name="pastday" class="form-control" />
		</div>
	
		<div class="col col-5  form-floating d-flex justify-content-start">
			<input type="date" id="futureid" name="futureday" class="form-control"  />
		</div>
		
		<div class="col fomr-floating d-flex justify-content-center ">
			<button type="submit" class="btn btn-large btn-primary">Hiển
				thị</button>
		</div>
	</form>
	<c:if test="${status == 1 }">
		<div class="alert alert-success mt-3 text-center" role="alert">
			<h4 class="alert-heading">${message }</h4>
		</div>
	</c:if>
	<div class="row mt-3">
		<div class="col-sm-6">
			<div class="card">
				<div class="card-body ">
					<h4 class="d-flex align-items-center card-title">Doanh thu</h4>
					<div class="text-start">
						<h2 class="font-light mb-0">${incomeForDate2Date } VND</h2>
						<span class="text-muted"></span>
					</div>


				</div>
			</div>
		</div>
		<div class="col-sm-6">
			<div class="card">
				<div class="card-body ">
					<h4 class="d-flex align-items-center card-title">Số vé bán
						được</h4>
					<div class="text-start">
						<h2 class="font-light mb-0">${seats } vé</h2>
						<span class="text-muted"></span>
					</div>


				</div>
			</div>
		</div>
		<%-- <canvas id="myChart"></canvas>
		<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
		<script>
		const ctx= document.getElementById('myChart');
		
		new Chart(ctx,{
			type:'bar',
			data:{
				labels:['ads', 'abc','aaa'],
				datasets:[{
					label:'số lượng vé bán được',
		            data:[],
		            borderWidth: 1
		            }]
			},
			options:{
				scales:{
					y:{
						beginAtzero:true
					}
				}
			}
		});
		</script> --%>
	</div>
</div>