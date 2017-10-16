var filuname = angular.module('filuname', []);

/*
filuname.controller('HitCountListController', function HitCountListController($scope) {
	$scope.hitcounts = [ {
		website : 'www.bing.com',
		count : 14065457
	}, {
		website : 'www.ebay.com.au',
		count : 19831166
	}, {
		website : 'www.facebook.com',
		count : 104346720
	} ];
});*/


filuname.controller('HitCountListController', function($scope, $http) {
    $http.get('/servlet/report/request?date=2016-01-06').
        then(function(response) {
            $scope.hitcounts = response.data;
        });
});