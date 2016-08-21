<!DOCTYPE html>
<html lang="en">
<head>
<title>DocumenTek</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/webjars/bootstrap/3.2.0/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/webjars/bootstrap-table/1.9.1/src/bootstrap-table.css">
<link rel="stylesheet"
	href="//rawgit.com/vitalets/x-editable/master/dist/bootstrap3-editable/css/bootstrap-editable.css">

<link rel="stylesheet" href="./css/soccerAnalysis.css">
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
				<a class="navbar-brand" id="documentek">SPORT ANALYTICS</a>
			</div>
			<div class="collapse navbar-collapse" id="myNavbar"
			>
				<ul class="nav navbar-nav navbar-right">
					<li><a id="clientManager" aria-haspopup="true"
						class="userDependant">LOAD VIDEO </a></li>
					<li><a id="documentManager" aria-haspopup="true"
						class="userDependant"> GENERATE REPORT </a></li>
					
			</div>
		</div>
	</nav>

	<%@include file="./modal.jsp"%>
	<!-- Placed at the end of the document so the pages load faster -->

	
	<script
		src="${pageContext.request.contextPath}/webjars/bootstrap/3.2.0/js/bootstrap.min.js"></script>

	
	<script
		src="${pageContext.request.contextPath}/webjars/angularjs/1.5.5/angular.js"
		type="text/javascript"></script>



	<script
		src="${pageContext.request.contextPath}/webjars/jquery-form/3.51/jquery.form.js"
		type="text/javascript"></script>
</body>
</html>


