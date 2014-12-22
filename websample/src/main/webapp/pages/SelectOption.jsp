<html ng-app>
<head>

<script type="text/javascript" src="/JS/angular.min.js"></script>


</head>
<br/><br/><br/>
<div align="center"><h style="color:brown;font-size:24px;">Please select one option to Proceed!!</h></div>
<div ng-controller="selectOptions">
<br/><br/><br/><br/><br/>
<div style="width:60%;margin-left:20%;display: block;"><a ng-click="shop();" href=""><img src="/img/cart.png" width="35%" height="35%" /></a><a href="" ng-click="getFeed()"><img style="float:right;" src="/img/feedB.png" width="35%" height="35%" /></a></div>
</div>
</html>

<script>

function selectOptions($http,$scope)
{
	
	$scope.shop = function()
	{
		console.log("Came in shop..");
		window.location =  "/pages//Item.jsp";
	};
	
	$scope.getFeed = function()
	{
		window.location =  "/pages/Feed.jsp" ;
	};
	
}



</script>