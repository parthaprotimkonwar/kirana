	var myApp = angular.module('myApp', [ 'ngRoute']);

	myApp.config(['$routeProvider',
                    function($routeProvider) {
                      $routeProvider.
                        when('/view1', {
                          templateUrl: 'partial/view1.html',
                          controller: 'landingPageController'
                        }).
                        when('/view2', {
                          templateUrl: 'partial/view2.html',
                          controller: 'landingPageController'
                        }).
                        otherwise({
                          redirectTo: '/view1'
                        });
                    }]);

	myApp.factory('myFactory', ['$http', function($http){
		var factory=[];
		factory.femployee = [
	                {"name":"partha", "id":"1"},
	                {"name":"anurag", "id":"2"},
	                {"name":"nitin", "id":"3"},
	                {"name":"deepak", "id":"4"},
	                {"name":"vikas", "id":"5"},
	                {"name":"biswa", "id":"6"},
	               ];
		$http({method: 'GET', url: 'http://localhost:8080/websample/secretEmployee1'}).
	    success(function(data, status, headers, config) {
	    	alert(data);
	    }).
	    error(function(data, status, headers, config) {
	    	alert(data);
	    });
		return factory;
	}]);
	
	myApp.controller('landingPageController', function($scope, myFactory){
		$scope.employee = myFactory.femployee;
		$scope.saveDetails = function() {
			$scope.employee.push({name:$scope.username,id:$scope.userid});
		}
});

