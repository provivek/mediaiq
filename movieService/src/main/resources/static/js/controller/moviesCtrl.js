App.controller('MoviesCtrl', ['$scope','$rootScope', '$http','$mdMedia', '$mdDialog', function($scope, $rootScope, $http, $mdMedia, $mdDialog) {
	
	var lawnchair = Lawnchair({name: 'movies', record: 'movie', adapter: 'dom'}, function(){});
	$scope.allMovies = "NONE";
	$scope.item = {imdbID:'',Title:'',Poster:'',Year:'', Genre: '', Director : '', Actors : '', Plot : ''};
	$scope.allMoviesRec = {};
	$scope.imdbIDStore;
	$scope.allMoviesData = function () {
		console.log('TTTTTTTTT');
		// get all the keys
		var myArray = new Array();
		lawnchair.keys(function(keys) {
	      if(keys && keys.length) {
	        	for(var i=0; i< keys.length; i++) {
		        	myArray.push('{imdbID:'+keys[i]+'}');
		        }
	        }
	    });
		
		var allMoviesArray = new Array();
		lawnchair.batch(myArray, function() {
	        // classic utility...
	        this.each(function(record, index) { 
	        	if(record.value){
	        		allMoviesArray.push(record.value);
	        	}
	        })
	    });
		/*var allMoviesArraySortLatest = new Array();
		for (var i = allMoviesArray.length-1; i >= 0; i--) {
			allMoviesArraySortLatest.push(allMoviesArray[i]);
		}*/
		console.log(allMoviesArray);
		$scope.allMoviesRec = allMoviesArray;
		return allMoviesArray;
	};
	
	$scope.test = function() {
		alert('Test');
	}
	
	 $scope.status = '  ';
	 $scope.customFullscreen = $mdMedia('xs') || $mdMedia('sm');
	
	  $scope.showAdvanced = function(ev) {
		    var useFullScreen = ($mdMedia('sm') || $mdMedia('xs'))  && $scope.customFullscreen;
		    $mdDialog.show({
		      controller: DialogController,
		      templateUrl: 'templates/moviesdialog.html',
		      parent: angular.element(document.body),
		      targetEvent: ev,
		      clickOutsideToClose:true,
		      fullscreen: useFullScreen
		    })
		    .then(function(answer) {
		      $scope.status = 'You said the information was "' + answer + '".';
		    }, function() {
		      $scope.status = 'You cancelled the dialog.';
		    });

		    $scope.$watch(function() {
		      return $mdMedia('xs') || $mdMedia('sm');
		    }, function(wantsFullScreen) {
		      $scope.customFullscreen = (wantsFullScreen === true);
		    });

		  };
	
	
}]);

function DialogController($scope, $mdDialog) {
	  $scope.hide = function() {
	    $mdDialog.hide();
	  };
	  $scope.cancel = function() {
	    $mdDialog.cancel();
	  };
	  $scope.answer = function(answer) {
	    $mdDialog.hide(answer);
	  };
	}