<html ng-app>
<head>

<script type="text/javascript" src="/JS/angular.min.js"></script>

<!-- ===========================================================================  -->

<!-- ==================================================================================== -->



<style type="text/css">
a
{
text-decoration:none;
color:black;
font-weight:bold;
}

</style>


</head>
<body onload="">
<div ng-controller="selectOptions" ng-init="checkFeed(categoryList[0].value,'firstStart');">




<div  id="mainGrey" style="background-image:url('/img/greyBg.png');">
<div style="background-image:url('/img/greyBg_.png');padding-bottom:5%;margin-bottom:2%;">
<div align="center" style="display:block;"><img style="position:fixed;top:1em;right:1em;" height="100px" width="100px" src="/img/pa.png" style="float:right;"/><br/><br/>
<h style="color:white;font-size:26px;">Welcome to Feed updates!!</h><br/><br/><span style="position:fixed;top:8em;right:24em;"><select style="width:150%" ng-change="checkFeed(myCategory.value,'firstStart')" ng-model="myCategory" ng-options="category.displayName for category in categoryList"></select></span></div>

</div >

<!-- ------------------------------------------------------ SHOWING FAVORITE LIST ------------------------------------------------------>


<div style="display:block;"  ng-init="content=''"  ng-show="!IsFav">
<a href="" style="width:7%;color:black;display:block;margin-left:4%;" ng-click="checkFeed('http://online.wsj.com/xml/rss/3_7085.xml','0')">General Feeds</a>


      
<span ng-mouseover="hover=true;content=newsItemFav.content;source=newsItemFav.image" ng-mouseleave="hover=false;content=''"  style="color:black;display:block;width:90%;margin:2%;background-image:url('/img/black1.png');padding:2%;background-repeat:no-repeat;background-size:   cover; " ng-repeat="newsItemFav in favEntriesArray">

<div style="width:55%">
<div style="display:inline-block;width:80%;">
<a style="width:10%;" target="_blank" id="iconImg"  href="{{newsItemFav.link}}"><img class="blog" height="100px" width="100px"  align="middle"  src="{{newsItemFav.image}}" /><span  style="margin:2%;">{{newsItemFav.title}}</span></a>
</div>

<div style="display:inline-block;width:10%;">
<a  style="cursor:pointer;" ng-click="delFav(newsItemFav,$index)"><span ng-show="!newsItemFav.favColor" ng-style="{color:'black'}"><img height="35px" width="35px"  align="middle" src="/img/remove.png"  title="Remove from your favorite list."/></span></a>
</div>
</div>

<span ng-show="hover" style="color:black;width:40%;position: fixed;top: 12em;right: 6em;"><div align="center"><img style="text-align:center;" src="{{newsItemFav.image}}" height="400px" width="400px"></div><br/>{{content}}</span>

</span>

</div>


<!-- ------------------------------------------------------ SHOWING GENERAL LIST ------------------------------------------------------>


<div style="display:block;"  ng-init="content=''"  ng-show="IsFav">
<a href="" style="width:7%;color:black;display:block;margin-left:4%;" ng-click="getFavList()">Get Favorites</a><a href="" style="width:7%;color:black;display:block;margin-left:4%;" ng-click="clearAll()"> (Clear All) </a>
      
<span ng-mouseover="hover=true;content=newsItem.content;source=newsItem.image" ng-mouseleave="hover=false;content=''"  style="color:black;display:block;width:40%;margin:2%;background-image:url('/img/black1.png');padding:2%;background-repeat:no-repeat;background-size:   cover; " ng-repeat="newsItem in entriesArray">

<div style="width:120%">
<div style="display:inline-block;width:80%;">
	<a id="iconImg" target="_blank" href="{{newsItem.link}}"><img class="blog" height="100px" width="100px"  align="middle"  src="{{newsItem.image}}" /><span  style="margin:2%;">{{newsItem.title}}</span></a>
</div>

<div style="display:inline-block;width:10%;">
	<a  style="cursor:pointer;" ng-click="makeFav(newsItem)"><span style="" ng-show="!newsItem.favColor" ng-style="{color:'black'}"><img height="35px" width="35px" align="middle" src="/img/redStar.png" title="Add as your favorite news."/></span></a>
	<a   style="cursor:pointer;" ng-click="delFav(newsItem,$index)"><span ng-show="newsItem.favColor" ng-style="{color:'red'}"><img  height="35px" width="35px" align="middle"  src="/img/greyStar.png" title="Remove from your favorite news." /></span></a>
</div>
</div>


<span ng-show="hover" style="color:black;width:40%;position: fixed;top: 12em;right: 6em;"><div align="center"><img style="text-align:center;" src="{{newsItem.image}}" height="400px" width="400px"></div><br/>{{content}}</span>

</span>

</div>


  
</div>
<div align="center" ng-show="loaded" style="margin-top:8%;"><span  style="font-size:34px;color:black;text-align:center;">Loading....</span></div>
</div>

</div>

</body>
</html>

<script>

function selectOptions($http,$scope)
{
	//
	$scope.loaded = true;
	$scope.IsFav = true;
	$scope.categoryList = new Array();
	$scope.entriesArray = new Array();
	$scope.favEntriesArray = new Array();
	$scope.red = false;
	 $scope.categoryList = [
	                     {value: 'http://online.wsj.com/xml/rss/3_7085.xml', displayName: 'World News'},
	                     {value: 'http://online.wsj.com/xml/rss/3_7455.xml', displayName: 'Technology'},
	                     {value: 'http://online.wsj.com/xml/rss/3_7201.xml', displayName: 'Lifestyle'},
	                     {value: 'http://online.wsj.com/xml/rss/3_7031.xml', displayName: 'Business'}
	                  ];
	$scope.myCategory = $scope.categoryList[0];
	
	
	$scope.checkList = function(url)
	{
		 $http.jsonp(url)
		  .success(function(data,status){
				  console.log('Success  ok, Status = '+status);  
				  console.log(data);
		  }).
	    error(function(data, status, headers, config) {
	    	 console.log("Error =========== ");
		      console.log("status = "+status);
		      console.log("data = "+data);
	    });
		
		
	}
	
	$scope.makeFav = function(news)
	{
		
		console.log("news == >"+news.favColor);
		if(news.favColor == false)
			{
			console.log("IF");
			news.favColor = true;
			}
		else
			{
			console.log("ELSE");
			news.favColor = false;
			}
			
		var newsStr = JSON.stringify(news);
		var newsObj = new Object();
		newsObj = {news:news};
		console.log("Object ========= >"+newsObj);
		var jsonObj = JSON.parse(newsStr);
		console.log("news == >"+newsStr);
		console.log("news jsonObj == >"+jsonObj);
		var dataSend = "news="+newsStr;
		 $http({
		      method: 'POST',
		      url: '/start/store',
		      headers: {'Content-Type': 'application/x-www-form-urlencoded'},
			  data: dataSend
		    }).success(function (data) 
		      {
		    	console.log("Succesfully stored.."+data);
		    	alert("Succesfully stored as your favorite News.");
		    	
		      }).error(function (data) 
				      {
			    	console.log("Error in storing..");
			    	alert("Error in storing, Please try again.");
			    	
			      });
		 
		console.log("Finished");
			
			
	}
	
	
	
	$scope.clearAll = function()
	{
	
			
	
		var dataSend = "news=";
		 $http({
		      method: 'POST',
		      url: '/start/deleteAll',
		      headers: {'Content-Type': 'application/x-www-form-urlencoded'},
			  data: dataSend
		    }).success(function (data) 
		      {
		    	console.log("Succesfully deleted.."+data);
		    	$scope.favEntriesArray.splice(0, $scope.favEntriesArray.length); 
		    	alert("Succesfully removed All your favorite News.");
		    	
		    	if($scope.favEntriesArray.length == 0)
		    		{
		    		$scope.checkFeed('http://online.wsj.com/xml/rss/3_7085.xml','0');
		    		}
		    	
		    	
		      }).error(function (data) 
				      {
			    	console.log("Error in deleteing..");
			    	alert("Error in deleting, Please try again.");
			    	
			      });
		 
		console.log("Finished");
			
			
	}
	
	
	$scope.delFav = function(news,id)
	{
		
		console.log("news == >"+news.favColor);
		if(news.favColor == false)
			{
			console.log("IF");
			news.favColor = true;
			}
		else
			{
			console.log("ELSE");
			news.favColor = false;
			}
			
		var newsStr = JSON.stringify(news);
		var newsObj = new Object();
		newsObj = {news:news};
		console.log("Object ========= >"+newsObj);
		var jsonObj = JSON.parse(newsStr);
		console.log("news == >"+newsStr);
		console.log("news jsonObj == >"+jsonObj);
		var dataSend = "news="+newsStr;
		 $http({
		      method: 'POST',
		      url: '/start/delete',
		      headers: {'Content-Type': 'application/x-www-form-urlencoded'},
			  data: dataSend
		    }).success(function (data) 
		      {
		    	console.log("Succesfully deleted.."+data);
		    	$scope.favEntriesArray.splice(id, 1); 
		    	alert("Succesfully removed from your favorite News.");
		    	
		    	if($scope.favEntriesArray.length == 0)
		    		{
		    		$scope.checkFeed('http://online.wsj.com/xml/rss/3_7085.xml','0');
		    		}
		    	
		    	
		      }).error(function (data) 
				      {
			    	console.log("Error in deleteing..");
			    	alert("Error in deleting, Please try again.");
			    	
			      });
		 
		console.log("Finished");
			
			
	}
	
	$scope.getFavList = function(isFavFlag)
	{
		
		
		var dataRet = "userName=";
		 $http({
		      method: 'POST',
		      url: '/start/get',
		      headers: {'Content-Type': 'application/x-www-form-urlencoded'},
			  data: dataRet
		    }).success(function (data) 
		      {
		    	console.log("Succesfully retrieved .."+data);
		    	var totalEntries = data;
		    	
				console.log("Total length = "+totalEntries.length);
				if(isFavFlag == 'firstStart')
				$scope.IsFav = true;
				else
					$scope.IsFav = false;
				//console.log("Total entries got = "+JSON.stringify(totalEntries));
				if($scope.IsFav == false && totalEntries.length <= 2 && totalEntries.length > 0)
					{
					document.getElementById("mainGrey").style.height = "100%";
					console.log(" Height 100% ");
					}
				else
					{
					document.getElementById("mainGrey").style.height = "";
					console.log(" Height Blank");
					}
				for (var i=0;i<totalEntries.length;i++)
					{
					$scope.favEntriesArray[i] = new Object();
					$scope.favEntriesArray[i].title = totalEntries[i].title;
					$scope.favEntriesArray[i].link = totalEntries[i].link;
					$scope.favEntriesArray[i].content = totalEntries[i].content;
					$scope.favEntriesArray[i].favColor = false;
					$scope.favEntriesArray[i].image = totalEntries[i].image;
					console.log(" Title = "+$scope.favEntriesArray[i].title + " Image= "+$scope.favEntriesArray[i].image);
					}
				
				if(totalEntries.length <= 0)
					$scope.favEntriesArray.splice(0, $scope.favEntriesArray.length); 
				//console.log("Total entries got = "+JSON.stringify(totalEntries));
				//$scope.loaded = false;
		    	
		      }).error(function (data) 
				      {
			    	console.log("Error in storing..");
			    	alert("Error in retrieving, Please try again.");
			      });
		 
		console.log("Finished");
			
			
	}
	
	
	$scope.checkFeed = function(url,isFavFlag)
	{
		

		
		var dataSend = "";
		document.getElementById("mainGrey").style.height = "";
		if(isFavFlag == 'firstStart')
			{
				$scope.getFavList(isFavFlag);
			}
		$scope.loaded = true;
		console.log("isFavFlag "+isFavFlag);
		if(isFavFlag == '1')
			$scope.IsFav = false;
		else
			$scope.IsFav = true;
	
		
		console.log("Feed data start...");
		$scope.hover = false;
		
		$scope.content = "";
		$scope.source = "";
		$scope.onHover = function(){
			
			$scope.hover = true;	
		}
		

		 $http.jsonp('//ajax.googleapis.com/ajax/services/feed/load?v=1.0&num=50&callback=JSON_CALLBACK&q=' + encodeURIComponent(url))
		  .success(function(data,status){
				  console.log('Success  ok, Status = '+status);  
				  console.log(data);
			var str = JSON.stringify(data);
			var jsonObj = JSON.parse(str);
			var favExist = 0;
			//var jsonObj = eval(str);
			var totalEntries = jsonObj.responseData.feed.entries;
			console.log("Total length = "+totalEntries.length);
			//console.log("Total entries got = "+JSON.stringify(totalEntries));
			for (var i=1;i<totalEntries.length;i++)
				{
				$scope.entriesArray[i-1] = new Object();
				$scope.entriesArray[i-1].title = totalEntries[i].title;
				$scope.entriesArray[i-1].link = totalEntries[i].link;
				$scope.entriesArray[i-1].content = totalEntries[i].content;
				console.log()
				for(var j=0;j<$scope.favEntriesArray.length;j++)
					{
					favExist = 1;	
					console.log("Inside Compare "+$scope.favEntriesArray[j].title);
						if($scope.favEntriesArray[j].title == $scope.entriesArray[i-1].title)
							{
								$scope.entriesArray[i-1].favColor = true;
								break;
							}
						else
							{
								$scope.entriesArray[i-1].favColor = false;
							}
					
					}
				if(favExist == 0)
					$scope.entriesArray[i-1].favColor = false;
				if(totalEntries[i].mediaGroups != undefined)
					$scope.entriesArray[i-1].image = totalEntries[i].mediaGroups[0].contents[0].url;
				else
					$scope.entriesArray[i-1].image = "/img/feed.png";
				console.log(" Title = "+$scope.entriesArray[i-1].title + " Image= "+$scope.entriesArray[i-1].image);
				}
			//console.log("Total entries got = "+JSON.stringify(totalEntries));
			$scope.loaded = false;
		  })
		  .error(function(data, status){
			  console.log("Error --> Feed data status = "+status+" data = "+data); 
			
				  });
	    
		// 
	};
		
}




</script>
