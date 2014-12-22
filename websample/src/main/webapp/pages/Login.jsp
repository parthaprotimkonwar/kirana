<html ng-app>
<head>

<script type="text/javascript" src="/JS/angular.min.js"></script>



 <script type="text/javascript" src="/JS/jquery-1.11.1.js"></script>
 <script type="text/javascript" src="/JS/jquery-ui.js"></script>

<script type="text/javascript" src="/JS/jquery.min.js"></script> 


<!--
 <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.3.2/jquery.min.js"></script>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.7.2/jquery-ui.min.js"></script>
 
-->
 



</head>
<body>

<div ng-controller="starting" style='text-align:center'>
 
</div>


   
<div ng-controller="testController">
<div style="margin-left:5%;">

<h>Test Application of Angular JS LOGIN</h>
<br/>
<br/>
<form method="post" action="/test">
User Name:
<br/>
<br/>
<input style="width:25%;" name="username" type="text" ng-model="username"  ng-change="typeInProcess()" list="title"/>



<br/>
<br/>
Password:
<br/>
<br/>
<input  style="width:25%;" name="password" type="password" ng-model="password" />
<br/>
<br/>
Company Name Test:
<br/>
<br/>
<input  style="width:25%;" ng-change="half()" name="company" type="text" ng-model="company" />HH{{companyRes}}
<br/>
<br/>
<input style="width:12%;" type="submit"  value="Submit" />
</form>



<hr/>

</div>
</div>




<script  type="text/javascript">



function starting($scope)
{
	//$("#myCanvas").effect("bounce", { times:4, distance:200 ,easing: 'easeOutBounce'}, 400);
}


function testController($scope,$http)
{
	
	 $scope.typeInPro = false;
	$scope.users = [{name:'Rakesh',password:'Rak'},
					{name:'Sam',password:'Sam'},
					{name:'Rom',password:'Rom'},
					{name:'Aspo',password:'Asp'},
					{name:'Zoe',password:'Zoe'},
					{name:'Brd',password:'Brd'},
					{name:'Chs',password:'Ch'},
					{name:'Med',password:'Pcm'},
					{name:'Mil',password:'Nan'},
					{name:'Ric',password:'Bas'}] ;
	
	$scope.addedList = [];
	$scope.companyRes = "Init";
	$scope.half = function(){
		alert("half");
		$scope.companyRes = $scope.company + "Hi";
	};
	
	$scope.addPerson = function(){
		$scope.addedList.push({'name' :$scope.username,'qty':$scope.qty});
	};

	 $scope.typeInProcess= function() {  
		 if($scope.username == '' || $scope.username.length <= 1)
			 {
			 $scope.typeInPro = false;
			 }
		 else
	   $scope.typeInPro = true;
	 };
	 
	$scope.attemptedList = [{name:'test1',password:'test1'}];
	$scope.attempted = function()
	{
		var dataSend = "username="+$scope.username+"&password="+$scope.password+"&company="+$scope.company;
		//alert($scope.username + " - " + $scope.password + " - " + $scope.company)
		$http({
			method: "post",
			url: "/Launch",
			headers: {'Content-Type': 'application/x-www-form-urlencoded'},
			data: dataSend
			}).success(function(data){
				console.log("Success --> data "+data);
	        $scope.attemptedList.push({'text' : data.text});
	    })
	};
	
	$scope.removeUser = function()
	{
		var item = {name:$scope.username,password:$scope.password};
		var index = $scope.attemptedList.indexOf(item);
		$scope.attemptedList.splice(index,1);
		
	};
}

</script>

</body>
</html>