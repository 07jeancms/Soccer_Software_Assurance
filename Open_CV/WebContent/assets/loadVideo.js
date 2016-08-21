/**
 * ToDo App
 */

(function localFileVideoPlayer() {
	'use strict'
  var URL = window.URL || window.webkitURL
 
  var playSelectedFile = function (event) {
    var file = this.files[0]
    var type = file.type
    var videoNode = document.querySelector('video')
   
    var fileURL = URL.createObjectURL(file)
    videoNode.src = fileURL
  }
  var inputNode = document.querySelector('input')
  inputNode.addEventListener('change', playSelectedFile, false)
})()


var loadVideoModule = angular.module('loadVideoModule', []);

loadVideoModule
		.controller(
				"loadVideoCtrl",
				[
						'$scope',
						'$http',
						function($scope, $http) {
							
						} ]);





