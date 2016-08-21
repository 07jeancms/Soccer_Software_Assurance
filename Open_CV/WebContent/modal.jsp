<!DOCTYPE html>
<html lang="en">
<head>
<!-- Theme Made By www.w3schools.com - No Copyright -->
<title>DocumenTek</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<style>
.modal {
	text-align: center;
}

@media screen and (min-width: 768px) {
	.modal:before {
		display: inline-block;
		vertical-align: middle;
		content: " ";
		height: 100%;
	}
}

.modal-dialog {
	display: inline-block;
	text-align: left;
	vertical-align: middle;
}
</style>
<body>
	<!-- Modal Start here-->
	<div class="modal fade bs-example-modal-sm centered" id="messageModal"
		tabindex="-1" role="dialog" aria-hidden="true" data-backdrop="static">
		<div class="modal-dialog modal-sm">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title" id="messageModalText">
						<span class="glyphicon glyphicon-time"> </span>Buscando los
						documentos
					</h4>
				</div>
				<div class="modal-body">
					<div class="progress">
						<div
							class="progress-bar progress-bar-info
                    progress-bar-striped active"
							style="width: 100%"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Modal -->
	<div class="modal fade centered" id="alertModal" tabindex="-1" role="dialog"
		aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<h4 class="modal-title" id="alertModalText"></h4>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Ok</button>
				</div>
			</div>
		</div>
	</div>
	<!-- Modal -->
	<div class="modal fade centered" id="directionModal" tabindex="-1" role="dialog"
		aria-hidden="true">
		<div class="modal-dialog" role="directionModal">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title" id="directionModalHeaderText">
						
					</h4>
				</div>
				<div class="modal-body" id="directionModalBody">
					
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Ok</button>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade centered" id="confirmationModal" tabindex="-1" role="dialog"
		aria-hidden="true">
		<div class="modal-dialog" role="directionModal">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title" id="confirmationModalHeaderText">
						
					</h4>
				</div>
				<div class="modal-body" id="confirmationModalBody">
					
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Ok</button>
					<button type="button" class="btn btn-danger" data-dismiss="modal">Cancel</button>
				</div>
			</div>
		</div>
	</div>
	
	<script src="./assets/modal.js"></script>
</body>
</html>
