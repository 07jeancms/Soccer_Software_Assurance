<!DOCTYPE html>
<html lang="en" ng-app="loadVideoModule">
<head>
<title>DocumenTek</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
video, input {
    display: block;
}

input {
    width: 100%;       
}

.info {
    background-color: aqua;            
}

.error {
    background-color: red;
    color: white;
}
</style>
</head>
<body>

	<%@include file="./navbar.jsp"%>

	<!-- Body -->
	<div class="container body">
		<div id="title" class="titleDiv"></div>

		<hr />

		<!-- Item Input Form -->
		<div class="clientForm" ng-controller="loadVideoCtrl">

			<h1>Select the soccer video to analyse </h1>
			
			<input type="file" accept="video/*" ng-model="videoToAnalyze" />
			{{videoToAnalyze}}
			<video controls autoplay class="centered"></video>

		</div>
		
		<button class="btn">Analyse video and generate report</button>
		

	</div>
	<%@include file="./modal.jsp"%>
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="./assets/loadVideo.js"></script>
</body>
</html>
