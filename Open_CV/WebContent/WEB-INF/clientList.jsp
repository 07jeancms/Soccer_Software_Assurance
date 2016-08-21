<!DOCTYPE html>
<html lang="en">
<head>
<!-- Theme Made By www.w3schools.com - No Copyright -->
<title>DocumenTek</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/webjars/bootstrap-table/1.9.1/src/bootstrap-table.css">


<style>
.jumbotron {
	background-color: #2D6B6B;
	color: #fff;
	padding: 100px 25px;
}

.container-fluid {
	padding: 60px 50px;
}

.navbar {
	margin-bottom: 0;
	background-color: #2D6B6B;
	z-index: 9999;
	border: 0;
	font-size: 12px !important;
	line-height: 1.42857143 !important;
	letter-spacing: 4px;
	border-radius: 0;
}

.navbar li a, .navbar .navbar-brand {
	color: #fff !important;
}

.navbar-nav li a:hover, .navbar-nav li.active a {
	color: #2D6B6B !important;
	background-color: #fff !important;
}

.navbar-default .navbar-toggle {
	border-color: transparent;
	color: #fff !important;
}

.clientContainer {
	height: 400px;
}

.title {
	font-size: 24px !important;
	line-height: 1.42857143 !important;
	letter-spacing: 4px;
	border-radius: 0;
}

.add {
	font-size: 18px;
	color: #2D6B6B !important;
}

.body {
	padding-top: 70px;
}

table tr td {
	width: 10px;
	padding-top: 10px;
	padding-bottom: 10px;
}

.active {
	background-color: #2D6B6B;
}

table {
	table-layout: fixed;
}

.documentTable {
	height: 250px;
	overflow-y: auto;
}
</style>

</head>
<body>
	<nav class="navbar navbar-default navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#myNavbar">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand"
					href="${pageContext.request.contextPath}/documenTek.jsp">DOCUMENTEK</a>
			</div>
			<div class="collapse navbar-collapse" id="myNavbar">
				<ul class="nav navbar-nav navbar-right">
					<li><a
						href="${pageContext.request.contextPath}/clientList.jsp">CLIENTES</a></li>
					<li><a href="${pageContext.request.contextPath}/document.jsp">DOCUMENTOS</a></li>
					<li><a
						href="${pageContext.request.contextPath}/autoDocument.jsp">AUTO-DOCUMENTOS</a></li>
				</ul>
			</div>
		</div>
	</nav>

	<!-- Body -->
	<div class="container body">

		<table>
			<tr>
				<td>
					<p class="title">CLIENTES</p>
				</td>
				<td></td>
				<td><a href="${pageContext.request.contextPath}/clients.jsp">
						<span class="glyphicon glyphicon-plus-sign add"></span>
				</a></td>
			<tr>
		</table>

		<!-- Item Input Form -->
		<table id="table" data-search="true" data-detail-view="true"
			data-detail-formatter="detailFormatter">
		</table>

	</div>


	<!-- Placed at the end of the document so the pages load faster -->

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<script
		src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/webjars/bootstrap-datepicker/1.4.0/js/bootstrap-datepicker.js"
		type="text/javascript"></script>
	<script
		src="https://rawgit.com/wenzhixin/bootstrap-table/master/src/bootstrap-table.js"
		type="text/javascript"></script>

	<script
		src="${pageContext.request.contextPath}/webjars/momentjs/2.10.6/moment.js"
		type="text/javascript"></script>

	<script src="assets/clientList.js"></script>
</body>
</html>
