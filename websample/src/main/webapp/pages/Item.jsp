<%@page language="java" session="true" %>
<html ng-app>
<head>
<script type="text/javascript" src="/JS/angular.min.js"></script>
</head>
<body>
<div style="background-color:#BDBDBD;height:100%;padding:3%;" ng-controller="testController">
<br/>
<br/>
<!--    ====================================================  Confirm Page  =====================================  -->
<div style="width:45%;" ng-show="isConfirm">

<h>Please Confirm Your List!!</h>
<hr/>
<table>
 <tr>
    <th style="text-align:left;width:25%;"><span style="text-align:center;color:white;">Item Name</span></th>
    <th style="width:25%;"><span style="text-align:center;color:white;">Quantity</span></th>
    <th style="width:25%;"><span style="text-align:center;color:white;">Price/Piece</span></th>
    <th style="width:33%;"><span style="text-align:center;color:white;">Total Price</span></th>
    <th style="width:28%;"><span style="text-align:center;color:white;">Remove</span></th>
  </tr>
  
<tr  style="text-align:left;" ng-repeat="added in addedList"><td  style="text-align:left;width:25%;color:white;">{{added.name}}</td><td style="width:5%;text-align:center;"><input style="width:55%;" ng-model="modelName[$index]"  ng-change="updateCart($index)" value={{added.qty}} /></td><td style="width:25%;text-align:center;color:white;">{{added.price}}</td><td ng-model="totalItemPrice" style="width:25%;text-align:center;color:white;">{{(added.price|number)*(added.qty|number)}}</td><td><button ng-click="removeItem($index);" >Remove</button></td></tr></table>
<br/><br/>
<div align="right" style="color:white;">Total:- {{total}}</div>
<hr/>
<br/>

<div align="center"><table><tr><td style="width:70%;"><button  ng-click="cancelOrder();">Cancel/Update</button></td><td align="right"><div align="right"><button ng-click="Posted();">Proceed</button></div></td></tr></table></div>
</div>

<!-- ========================================================== Item List Page =================================== -->
<div ng-show="!isConfirm" style="width:95%;margin-left:5%;" align="left" >
<%
String name = (String)session.getAttribute("Name");
String username = (String)session.getAttribute("username");
String department = (String)session.getAttribute("Department");


%>
<h style="color:blue;">Hello <span style="color:brown;text-weight:bold;font-size:20px;"><%= username %></span>, This is a test Application For Stationary Management.</h>
<br/>
<br/>
<div style="display:inline-block;width:100%;">
<div style="width:50%;float:left;" >
<form  method="post" action="/Launch">
Item Name:
<br/>
<br/>

<input style="width:30%;;display:inline;" align="left" name="username" type="text" ng-model="username"  ng-change="typeInProcess()" list="title"/><span width="100%" ng-show="username.length">QTY<input value="" style="width:10%;" type="number" ng-model="qty"/> </span>  <a id="add" href="#" ng-click="addPerson(slectedItem)" >Add</a>
<br/><br/><br/>
</form>
</div>

<!-------------------------------------------------------- For Red BG --------------------------------------->


<div ng-show="addedList.length" align="right" style="float:right;width:40%;background-color:#81F79F;padding:3%;">   
<p style="text-align:center;color:white;"  ng-show="addedList.length">Item Selected:</p><p style="text-align:center;" ng-show="!addedList.length">Empty Cart:</p>
<ng-show="addedList.length"hr/>
<table>
 <tr ng-show="addedList.length">
    <th style="text-align:left;width:25%;"><span style="text-align:center;color:white;">Item Name</span></th>
    <th style="width:25%;"><span style="text-align:center;color:white;">Quantity</span></th>
    <th style="width:25%;"><span style="text-align:center;color:white;">Price/Piece</span></th>
    <th style="width:33%;"><span style="text-align:center;color:white;">Total Price</span></th>
    <th style="width:28%;"><span style="text-align:center;color:white;">Remove</span></th>
  </tr>
  
<tr  style="text-align:left;" ng-repeat="added in addedList"><td  style="text-align:left;width:25%;color:white;">{{added.name}}</td><td style="width:5%;text-align:center;"><input style="width:55%;" ng-model="modelName[$index]"  ng-change="updateCart($index)" value={{added.qty}} /></td><td style="width:25%;text-align:center;color:white;">{{added.price}}</td><td ng-model="totalItemPrice" style="width:25%;text-align:center;color:white;">{{(added.price|number)*(added.qty|number)}}</td><td><button ng-click="removeItem($index);" >Remove</button></td></tr></table>
<hr/>
<div align="right"  style="color:white;">Total:- {{total}}</div>
<br/><br/>
<div ng-show="addedList.length" align="right"><button ng-click = "confirmItem();">Submit</button></div>

</div>


<!-- --------------------------------------------  For Green BG -------------------------------------------------->


<div ng-show="!addedList.length" style="float:right;width:40%;background-color:rgb(80,0,0) ;padding:3%;">   
<p style="text-align:center;"  ng-show="addedList.length">Item Selected:</p><p style="text-align:center;color:white;" ng-show="!addedList.length">Empty Cart:</p>
<ng-show="addedList.length"hr/>
<table>
 <tr ng-show="addedList.length">
    <th style="text-align:left;width:25%;"><span style="text-align:center;">Item Name</span></th>
    <th style="width:25%;"><span style="text-align:center;">Quantity</span></th>
    <th style="width:25%;"><span style="text-align:center;">Price/Piece</span></th>
    <th style="width:33%;"><span style="text-align:center;">Total Price</span></th>
    <th style="width:28%;"><span style="text-align:center;">Remove</span></th>
  </tr>
  <ng-show="addedList.length"hr/>
<tr  style="text-align:left;" ng-repeat="added in addedList"><td  style="text-align:left;width:25%;">{{added.name}}</td><td style="width:5%;text-align:center;"><input style="width:55%;" ng-model="modelName[$index]"  ng-change="updateCart($index)" value={{added.qty}} /></td><td style="width:25%;text-align:center;">{{added.price}}</td><td ng-model="totalItemPrice" style="width:25%;text-align:center;">{{(added.price|number)*(added.qty|number)}}</td><td><button ng-click="removeItem($index);" >Remove</button></td></tr></table>
<hr/>
<div align="right" style="color:white;">Total:- {{total}}</div>
<br/><br/>
<div ng-show="addedList.length" align="right"><button ng-click = "confirmItem();">Submit</button></a></div>

</div>





</div>


<datalist ng-show="username.length" id="title">
    
  
<option ng-model="slectedItem" ng-show="username.length" ng-repeat="usr in users | filter:username">{{usr.name}}</option>

</datalist>

<br/>
<br/>







</div>
</div>

<script  type="text/javascript">





function testController($scope,$http)
{
	console.log("Hello ----> 3");
	$scope.Math = window.Math;
	$scope.total = 0e10;
	 $scope.typeInPro = false;
	 $scope.isConfirm = false;
	$scope.users = [{name:'NotePad Small',password:'Rak',price:10},
					{name:'NotePad Large',password:'Sam',price:20},
					{name:'Black Pen',password:'Rom',price:30},
					{name:'Blue Pen',password:'Asp',price:40},
					{name:'Dairy',password:'Zoe',price:50},
					{name:'Card',password:'Brd',price:60},
					{name:'Envelop',password:'Ch',price:70},
					{name:'Stickers',password:'Pcm',price:80},
					{name:'Chart',password:'Nan',price:90},
					{name:'Eraser',password:'Bas',price:15}] ;
	
	$scope.modelName = new Array();
	for(var i=0;i<$scope.users.length;i++)
		{
		$scope.modelName[i] = "input"+i;
		}
	
	$scope.addedList = [];
	
	$scope.addPerson = function(){
		var i = 0;
		var index = 0;
		var isExist = 0;
		for(i=0;i<$scope.users.length;i++)
			{
			if($scope.users[i].name == $scope.username)
			{	index = i;
			break;
			}
			}
		
		for(i=0;i<$scope.addedList.length;i++)
		{
		if($scope.addedList[i].name == $scope.username)
		{	
			$scope.modelName[i] = $scope.modelName[i]*1;
			$scope.addedList[i].qty = $scope.addedList[i].qty*1;
			$scope.addedList[i].qty += $scope.qty*1;
			$scope.modelName[i] += $scope.qty*1; 
			$scope.addedList[i].total = $scope.addedList[i].price*$scope.addedList[i].qty;
			isExist = 1;
			break;
			
		}
		}
		if(isExist != 1)
			{
		$scope.addedList.push({'name' :$scope.username,'qty':$scope.qty,'price':$scope.users[index].price,'total':$scope.qty*$scope.users[index].price});
		$scope.modelName[i] = $scope.qty*1;
		$scope.addedList[i].total = $scope.modelName[i]*$scope.users[index].price;
		
			}
		
		$scope.total = 0;
		 for(var i=0;i<$scope.addedList.length;i++)
			 $scope.total += $scope.addedList[i].total;
		
	};

	 $scope.typeInProcess= function() {  
		 if($scope.username == '' || $scope.username.length <= 1)
			 {
			 $scope.typeInPro = false;
			 }
		 else
	   $scope.typeInPro = true;
	 };
	 
	 $scope.updateCart = function(index)
	 {
		 
		 //alert($scope.modelName[index] + " - "+$scope.addedList[index].price);
		 $scope.addedList[index].total = $scope.modelName[index]*$scope.addedList[index].price;
		 $scope.addedList[index].qty =  $scope.modelName[index]*1;
		 $scope.total = 0;
		 for(var i=0;i<$scope.addedList.length;i++)
			 $scope.total += $scope.addedList[i].total;
		// $scope.addedList[index].total
		 //alert($scope.addedList[index].total);
	 };
	 
	 $scope.removeItem = function(index)
	 {
		 $scope.total = $scope.total - $scope.addedList[index].total;
		 $scope.addedList.splice(index, 1);
	 };
	
	 $scope.Posted = function()
		{
			var dataSend = $scope.addedList;
			console.log("Entered Posted- "+dataSend[0].name);
			$http({
				method: "post",
				url: "Order",
				headers: {'Content-Type': 'application/x-www-form-urlencoded'},
				data: dataSend
				}).success(function(data){
		        console.log("Succesfully requested..");
		        window.location = "/SpringTest/Completed.jsp";
		    })
		};
	 
	$scope.confirmItem = function()
	{
		$scope.isConfirm = true;
		console.log("Inside Confirm order:- "+$scope.isConfirm) ;
		
	};
	
	$scope.cancelOrder = function()
	{
		$scope.isConfirm = false;
		console.log("Inside Confirm order:- "+$scope.isConfirm) ;
		
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