function LaunchCtrl($scope, $http)
{

    $scope.showCart = false;
    $scope.itemSearch = false;
    $scope.cartItem = 0;
    $(document).ready(function()
    {


       
        $scope.citySelected = localStorage.getItem('citySelected'); 
        if($scope.citySelected == null || $scope.citySelected == undefined || $scope.citySelected == "Retry")
        {
        	$scope.citySelected = "City";
        	 $("#locationPopup").css('display', 'block');
        	
        }
        else
        {
        	
        	$('#cityInput').val($scope.citySelected)
        	$("#cityDone").click();
        	
        }
        
        $(document).on("click", "#citySelected", function()
		        {
        	  		$scope.citySelected = "Retry";
        	  		localStorage.setItem('citySelected',$scope.citySelected);
        			window.location.href = "/pages/loadView.html";
		          

		        });

        



    });

    $("#cityDone").click(function()
    {


        if ($('#cityInput').val() == '')
        {
            alert('City can not be left blank');
        }
        else
        {
            $scope.showCart = true;
            $scope.citySelected = $('#cityInput').val();
            $("#locationPopupInternal").animate(
            {

                height: 5, // 100 + 50 / 2
                marginTop: 10,
                width: 5,
                marginRight: 45 // 50 / -2
            }, 900, function()
            {


                $('#locationPopup').css('display', 'none');
                // $("#append").replaceWith("<a id=\"cityChoosen\" href=\"\" style=\"float:right;margin-right:5%;\">"+$scope.citySelected+"</a>");
            });
            $("#headerBar").css('display', 'block');
            $scope.$apply(function()
            {
                $scope.itemSearch = true;
            });
            
            localStorage.setItem('citySelected',$scope.citySelected);
            
        }

        


    });




    $scope.cities = [
    {
        name: 'Bangalore',
        pin: '1001'
    },
    {
        name: 'Pune',
        pin: '1001'
    },
    {
        name: 'Mumbai',
        pin: '1001'
    },
    {
        name: 'Delhi',
        pin: '1001'
    },
    {
        name: 'Lucknow',
        pin: '1001'
    },
    {
        name: 'Kolkata',
        pin: '1001'
    },
    {
        name: 'Hyderabad',
        pin: '1001'
    },
    {
        name: 'Chennai',
        pin: '1001'
    },
    {
        name: 'Chandigarh',
        pin: '1001'
    },
    {
        name: 'Goa',
        pin: '1001'
    }];

    $scope.items = [
    {
        name: 'Tomato Ketchup 200ml',
        pin: '1001',
        price: '98',
        quantCategory: 'Number',
        quantId:'5'
    },
    {
        name: 'Brown Bread 250gm',
        pin: '1002',
        price: '18',
        quantCategory: 'Number',
        quantId:'5'
    },
    {
        name: 'Rice',
        pin: '1003',
        price: '48',
        quantCategory: 'KgHeavy',
        quantId:'1'
    },
    {
        name: 'Egg',
        pin: '1004',
        price: '35',
        quantCategory: 'Number',
        quantId:'5'
    },
    {
        name: 'Milk 500ml',
        pin: '1005',
        price: '38',
        quantCategory: 'Number',
        quantId:'5'
    },
    {
        name: 'Chips 200gm',
        pin: '1006',
        price: '18',
        quantCategory: 'Number',
        quantId:'5'
    },
    {
        name: 'Oil 2ltr',
        pin: '1007',
        price: '128',
        quantCategory: 'Number',
        quantId:'5'
    },
    {
        name: 'Maggie small',
        pin: '1008',
        price: '10',
        quantCategory: 'Number',
        quantId:'5'
    },
    {
        name: 'Soup 150ml',
        pin: '1009',
        price: '48',
        quantCategory: 'Number',
        quantId:'5'
    },
    {
        name: 'Biscuit 150gm',
        pin: '1010',
        price: '15',
        quantCategory: 'Number',
        quantId:'5'
    },
    {
        name: 'Potato',
        pin: '1010',
        price: '15',
        quantCategory: 'KgLight',
        quantId:'0'
    }];
    
    //itemQuantInput
   /* $scope.itemSelectAfter = function()
    {
    	debugger;
   	 var e = document.getElementById("itemS");
	    var strUser = e.options[e.selectedIndex].value;
	    alert(strUser);
    }*/
    
    $scope.quantKgLight = [{quantCategory:'KgLight',id:'0'},{label:'1Kg',id:'1000'},{label:'2Kg',id:'1001'},{label:'3Kg',id:'1002'},{label:'4Kg',id:'1003'},{label:'5Kg',id:'1004'}];
    $scope.quantKgHeavy = [{quantCategory:'KgHeavy',id:'1'},{label:'2Kg',id:'1000'},{label:'5Kg',id:'1001'},{label:'10Kg',id:'1002'},{label:'15Kg',id:'1003'},{label:'20Kg',id:'1004'}];
    $scope.quantGm = [{quantCategory:'Gm',id:'2'},{label:'100gm',id:'1000'},{label:'200gm',id:'1001'},{label:'250gm',id:'1002'},{label:'500gm',id:'1003'},{label:'1Kg',id:'1004'}];
    $scope.quantLtr = [{quantCategory:'Ltr',id:'3'},{label:'1Ltr',id:'1000'},{label:'2Ltr',id:'1001'},{label:'3Ltr',id:'1002'},{label:'4Ltr',id:'1003'},{label:'5Ltr',id:'1004'},{label:'6Ltr',id:'1005'},{label:'7Ltr',id:'1006'},{label:'8Ltr',id:'1007'},{label:'9Ltr',id:'1008'},{label:'10Ltr',id:'1009'}];
    $scope.quantLtrLight = [{quantCategory:'LtrLight',id:'4'},{label:'100ml',id:'1000'},{label:'200ml',id:'1001'},{label:'250ml',id:'1002'},{label:'350ml',id:'1003'},{label:'500ml',id:'1004'},{label:'700ml',id:'1005'}];
    $scope.quantNumber = [{quantCategory:'Number',id:'5'},{label:'1',id:'1000'},{label:'2',id:'1001'},{label:'3',id:'1002'},{label:'4',id:'1003'},{label:'5',id:'1004'},{label:'6',id:'1005'},{label:'7',id:'1006'},{label:'8',id:'1007'},{label:'9',id:'1008'},{label:'10',id:'1009'}];
       
    $scope.quantArray = [$scope.quantKgLight,$scope.quantKgHeavy,$scope.quantGm,$scope.quantLtr,$scope.quantLtrLight,$scope.quantNumber];
    
    $scope.quantityList = [];
    var listQuant =[];
    $('#itemInput').bind('change', function () {

	 var itemObj = $('#itemS > option:selected').attr('data-info');
     itemObj = JSON.parse(itemObj);
     $scope.quantityList = $scope.quantArray[itemObj.quantId];
     listQuant = $scope.quantityList.slice(1, $scope.quantityList.length+1);
     
     $("#itemQuantInput").autocomplete({
         source: listQuant,
         minLength: 0,
         delay: 0
        
     }).on('focus', function(event) {
    	    var self = this;
    	    $(self).autocomplete( "search", "");
     });
    });
    
   
    // =============================  START CART====================
   var retrievedCart = localStorage.getItem('storedCart');
   //var retrievedCart = null;

    if(retrievedCart == null || retrievedCart == undefined || retrievedCart == "" || Number(localStorage.getItem('mainTotal')) == 0)
    {	
    $scope.addedList = {}; // To store added menu item
    $scope.cartLength = 0; // Keep track of cart length
    $scope.mainTotal = 0; // Storing total cost of full cost
    $scope.inputQty = 1; // Initialise the item quantity added as 1
    }
    else
    	{
    
    	 $scope.addedList = JSON.parse(retrievedCart); 
    	 $scope.cartLength = Number(localStorage.getItem('cartLength')); 
    	 $scope.mainTotal =Number(localStorage.getItem('mainTotal')); 
    	 $scope.cartItem = Number(localStorage.getItem('cartItem')); 
    	}
    

    // Function to add item to cart
    $scope.addItemToCart = function(itemS)
    {
        // To get Object as String, converting it to Object and initialising basic properties. 
        var itemObj = $('#itemS > option').attr('data-info');
        var oldItemTotal ;
        var quantNumber;
        itemObj = JSON.parse(itemObj);
        itemObj.total = Number(itemObj.price);
        var unitCheck = $scope.quantityList[0].id;
        debugger;
        quantNumber = $("#itemQuantInput").val();
        if(unitCheck != '5' && unitCheck != 5)
        itemObj.unit = (quantNumber).replace(/[0-9]/g, ''); // gives alphabet
        else
        	itemObj.unit = "-";	
        
      
        // Adding the object to cart list
        if ($scope.addedList[itemObj.pin] == undefined)
        	{
            $scope.cartLength = $scope.cartLength + 1;
            itemObj.qty = (quantNumber).replace(/[^\d]/g, ''); // gives digit
            $scope.addedList[itemObj.pin] = itemObj;
            $scope.addedList[itemObj.pin].total = Number(itemObj.qty) * Number(itemObj.price);
            $scope.mainTotal += Number(itemObj.qty) * Number(itemObj.price);
            $scope.cartItem = Number($scope.cartItem) +1;
            }
        else
        	{
        		oldItemTotal = $scope.addedList[itemObj.pin].total;
        		var num  = Number((quantNumber).replace(/[^\d]/g, '')); // gives digit
        		itemObj.qty = Number($scope.addedList[itemObj.pin].qty) + num;
        		$scope.addedList[itemObj.pin] = itemObj;
        		$scope.addedList[itemObj.pin].total = Number(itemObj.qty) * Number(itemObj.price);
        		if($scope.cartItem == 1)
        		$scope.mainTotal = Number(itemObj.qty) * Number(itemObj.price);
        		else
        			$scope.mainTotal += Number($scope.addedList[itemObj.pin].total) - Number(oldItemTotal);	
                
        	}
        	 
        
        
        
        
        
        storeCartDetails();
        
    }

    // Function to update cart on editing quantity from cart.
    $scope.updateCart = function(inputQty, itemObj)
    {
        var oldItemTotal = $scope.addedList[itemObj.pin].total;
        $scope.addedList[itemObj.pin].qty = inputQty;
        $scope.addedList[itemObj.pin].total = $scope.addedList[itemObj.pin].price * inputQty;
   
        if($scope.cartItem == 1)
        	{
        	 $scope.mainTotal = $scope.addedList[itemObj.pin].total;
        
        	}
       
        else
        	{
        	$scope.mainTotal += Number($scope.addedList[itemObj.pin].total) - Number(oldItemTotal);
        	}
        
        
        
        storeCartDetails();
    }
    
    $scope.itemSelectecd = function(item)
    {
    	
    	alert(item);
    }

    // Function to remove a item from cart
    $scope.removeItem = function(itemObj)
    {
        $scope.mainTotal = Number($scope.mainTotal) - Number($scope.addedList[itemObj.pin].total);
        delete $scope.addedList[itemObj.pin];
        $scope.cartLength = $scope.cartLength - 1;
        $scope.cartItem = $scope.cartItem -1;
        storeCartDetails();
    }

    // Function to empty full cart
    $scope.emptyCart = function()
    {
        $scope.mainTotal = 0;
        $scope.cartLength = 0;
        $scope.addedList = {};
        $scope.cartItem = 0;
        storeCartDetails();
    }
    
    
    function storeCartDetails()
    {
    	localStorage.setItem('storedCart', JSON.stringify($scope.addedList));
    	localStorage.setItem('cartLength',$scope.cartLength);
    	localStorage.setItem('mainTotal',$scope.mainTotal);
    	localStorage.setItem('cartItem',$scope.cartItem);
    	
    	
    }




}