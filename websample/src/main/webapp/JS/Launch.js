angular.module('ngAppCart', []).config(function($httpProvider)
{
    $httpProvider.defaults.useXDomain = true;
    $httpProvider.defaults.withCredentials = true;
    delete $httpProvider.defaults.headers.common["X-Requested-With"];
});

var app = angular.module('ngAppCart', ['ngRoute']);


app.config(function($routeProvider)
{
    $routeProvider
        .when('/launch',
        {
            controller: 'LaunchCtrl',
            templateUrl: '/pages/loadView.html'
        })
        .when('/checkout',
        {
            controller: 'LaunchCtrl',
            templateUrl: '/pages/checkout.html'
        })

});
