angular.module('filuname').component('hitCountTable', {
	templateUrl : '/js/filuname/hitcount-table.tmpl.html',
	controller : function HitCountController($scope, $http) {
		_this = this;

		$scope.$watch("$ctrl.selectedDate", function(newValue, oldValue){
			if (typeof newValue != 'undefined' && newValue.match(/\d\d\d\d-\d\d-\d\d/g)) {
				$http.get('/servlet/report/request?date=' + newValue).then(
			    	function(response) { 
			    		_this.hitcounts = response.data; 
//			    		$scope.$apply();
			    	}
			    );
			}
		});		
	}
});


