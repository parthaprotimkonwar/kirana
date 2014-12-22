<html ng-app>
<head>
<script type="text/javascript" src="/SpringTest/JS/angular.min.js"></script>
</head>

<body>

<div controller="confirmController">
Hello, Please confirm your list..{{$scope.data}}


<%
    double num = Math.random();
    
  %>
      <h2>You'll have a luck day!</h2><p>(<%= num %>)</p>
      
      <ul>{{data[0].name}}
      <li ng-repeat="data in dataList">Item name is : {{data.name}}</li>
      </ul>
 
 </div>
 
 <script  type="text/javascript">
 
 function confirmController($scope,$http,productService)
 {
	 $scope.dataList =[];
	 
	 $scope.$on('eventName', function (event, data) {
	$scope.dataList = data;	 
	 });
 }
 
 </script>
 </body>
</html>