angular.module('filuname').component('hitCountTable', {
	templateUrl : '/js/filuname/hitcount-table.tmpl.html',
	controller : function HitCountController($http) {
		that = this;
		$http.get('/servlet/report/request?date=2016-01-06').then(
        	function(response) { 
        		that.hitcounts = response.data; 
        	}
        );
	}
});


