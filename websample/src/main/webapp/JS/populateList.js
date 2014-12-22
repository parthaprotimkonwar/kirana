angular.module('ngAppCart', []).config(function($httpProvider)
{
    $httpProvider.defaults.useXDomain = true;
    $httpProvider.defaults.withCredentials = true;
    delete $httpProvider.defaults.headers.common["X-Requested-With"];
});

var app = angular.module('ngAppCart', ['ngRoute']);
//var mainUrl = "http://192.168.1.2:8080/websample/rest/landing";


// PARTHA
var mainUrl = "http://localhost:9999/start/";

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


function LaunchCtrl($scope, $http)
{
    //var server_url_location = "http://192.168.1.2:8080/websample/rest/landing/locations";
    //var server_url_item = "http://192.168.1.2:8080/websample/rest/landing/items/";


    // PARTHA
    //var server_url_location = "http://localhost:9999/start/city";
	var server_url_location = "http://localhost:8080/rest/landing/cities";
    var server_url_item = "http://localhost:9999/start/item/";

    $scope.CheckScopeBeforeApply = function()
    {
        if (!$scope.$$phase)
        {
            $scope.$apply();
        }
    }


    //var server_url = "/start/login";
    angular.module('', ['ngAnimate']);
    $scope.showCart = false;
    $scope.itemSearch = false;
    $scope.cartItem = 0;
    $scope.isDisabledQuant = true;
    $scope.errorShow = false;
    $scope.locationId;
    $scope.listCategoryItem = [''];
    var categoryObj = [
    {},
    {}];
    $scope.reLocateCity = false;
    $scope.reLocateLocation = false;
    $scope.reLocateshop = false;
    $scope.checkOutShow = false;
    $scope.showCartUserType = false;
    $scope.showRegisterForm = false;
    $scope.showGuestForm = false;
    $scope.showdeliveryAddressForm = false;
    $scope.showPaymentOption = false;

    var requestList = {
        cityList: 'cityList',
        locationList: 'locationList',
        shopList: 'shopList',
        itemList: 'itemList'
    };

    var imageBgArray = ['/img/groceryBg1.png', '/img/groceryBg2.png', '/img/groceryBg3.png'];
    $scope.quantKgLight = [
    {
        quantCategory: 'KgLight',
        id: '0'
    },
    {
        label: '1Kg',
        id: '1000'
    },
    {
        label: '2Kg',
        id: '1001'
    },
    {
        label: '3Kg',
        id: '1002'
    },
    {
        label: '4Kg',
        id: '1003'
    },
    {
        label: '5Kg',
        id: '1004'
    }];
    $scope.quantKgHeavy = [
    {
        quantCategory: 'KgHeavy',
        id: '1'
    },
    {
        label: '2Kg',
        id: '1000'
    },
    {
        label: '5Kg',
        id: '1001'
    },
    {
        label: '10Kg',
        id: '1002'
    },
    {
        label: '15Kg',
        id: '1003'
    },
    {
        label: '20Kg',
        id: '1004'
    }];
    $scope.quantGm = [
    {
        quantCategory: 'Gm',
        id: '2'
    },
    {
        label: '100gm',
        id: '1000'
    },
    {
        label: '200gm',
        id: '1001'
    },
    {
        label: '250gm',
        id: '1002'
    },
    {
        label: '500gm',
        id: '1003'
    },
    {
        label: '1Kg',
        id: '1004'
    }];
    $scope.quantLtr = [
    {
        quantCategory: 'Ltr',
        id: '3'
    },
    {
        label: '1Ltr',
        id: '1000'
    },
    {
        label: '2Ltr',
        id: '1001'
    },
    {
        label: '3Ltr',
        id: '1002'
    },
    {
        label: '4Ltr',
        id: '1003'
    },
    {
        label: '5Ltr',
        id: '1004'
    },
    {
        label: '6Ltr',
        id: '1005'
    },
    {
        label: '7Ltr',
        id: '1006'
    },
    {
        label: '8Ltr',
        id: '1007'
    },
    {
        label: '9Ltr',
        id: '1008'
    },
    {
        label: '10Ltr',
        id: '1009'
    }];
    $scope.quantLtrLight = [
    {
        quantCategory: 'LtrLight',
        id: '4'
    },
    {
        label: '100ml',
        id: '1000'
    },
    {
        label: '200ml',
        id: '1001'
    },
    {
        label: '250ml',
        id: '1002'
    },
    {
        label: '350ml',
        id: '1003'
    },
    {
        label: '500ml',
        id: '1004'
    },
    {
        label: '700ml',
        id: '1005'
    }];
    $scope.quantNumber = [
    {
        quantCategory: 'Number',
        id: '5'
    },
    {
        label: '1',
        id: '1000'
    },
    {
        label: '2',
        id: '1001'
    },
    {
        label: '3',
        id: '1002'
    },
    {
        label: '4',
        id: '1003'
    },
    {
        label: '5',
        id: '1004'
    },
    {
        label: '6',
        id: '1005'
    },
    {
        label: '7',
        id: '1006'
    },
    {
        label: '8',
        id: '1007'
    },
    {
        label: '9',
        id: '1008'
    },
    {
        label: '10',
        id: '1009'
    }];

    $scope.quantArray = {SIZE_NORMAL_MORE:$scope.quantNumber,SIZE_NORMAL_LESS:$scope.quantKgHeavy,SIZE_NORMAL_MORE2:$scope.quantGm,SIZE_NORMAL_MORE3:$scope.quantLtr,SIZE_NORMAL_MORE4:$scope.quantLtrLight,SIZE_NORMAL_MORE5:$scope.quantNumber};

    $scope.quantityList = [];
    var listQuant = [];
    $scope.cityList = [];
    $scope.locationList = [];
    $scope.shopList = [];
    $(document).ajaxStop(function()
    {
        console.log("Inside ajaxstope....");
    });
    $(document).ready(function()
    {



        $scope.toggler = function(event)
        {

        	
            if (event.target.parentNode.tagName == "A")
                $(event.target.parentNode).toggleClass('active, inactive');
            else
                $(event.target).toggleClass('active, inactive');




        }

        $scope.hideCart = function()
        {

            $("#cartTable").fadeOut('slow');
            $("#cartSymbol").effect("shake",
            {
                times: 3
            }, 1000);


        }

        $scope.removeBrowseDiv = function()
        {

            $('#browseList').hide("scale",
            {
                direction: 'vertical'
            }, 400);
        }

        $scope.showBrowseDiv = function()
        {

            ;
            $('#browseList').show('slow', 'swing');

        }

        $scope.showCartAgain = function()
        {

            $scope.showRegisterForm = false;
            $scope.showGuestForm = false;
            if ($('#searchBarDiv').is(':visible'))
            {
                $("#cartTable").fadeIn('slow');
            }
            else
            {

                //$("#cartUserType").fadeOut('slow');

                $.when($('#cartUserType').fadeOut(500))
                    .done(function()
                    {
                        $("#cartTable,#searchBarDiv").show();



                        $("#cartTable,#searchBarDiv").animate(
                        {
                            opacity: 1,
                            marginLeft: "5%",
                        }, 1000, function() {

                        });
                    });



            }



        }

        $scope.citySelected = localStorage.getItem('citySelected');
        $scope.reLocateCity = localStorage.getItem('reLocateCity');
        $scope.reLocateLocation = localStorage.getItem('reLocateLocation');
        $scope.reLocateshop = localStorage.getItem('reLocateshop');
        $scope.citySelectedId = localStorage.getItem('citySelectedId');
        $scope.locationSelected = localStorage.getItem('locationSelected');
        $scope.locationSelectedId = localStorage.getItem('locationSelectedId');
        $scope.shopSelected = localStorage.getItem('shopSelected');
        $scope.shopSelectedId = localStorage.getItem('shopSelectedId');



        if ($scope.citySelected == null || $scope.citySelected == undefined)
        {




            $scope.cityList = $scope.locationRequest($http);

            $scope.CheckScopeBeforeApply();




            $("#locationPopup").css('display', 'block');

        }
        else
        {

            if ($scope.reLocateCity == "true")
            {

                $scope.cityList = $scope.locationRequest($http);
                $scope.CheckScopeBeforeApply();

                $("#locationInput").css('display', 'none');
                $("#shopInput").css('display', 'none');
                $("#locationPopup").css('display', 'block');

            }
            else if ($scope.reLocateLocation == "true")
            {

                var itemObj = $scope.citySelectedId;
                
                //var urlPassed = "http://localhost:9999/start/location/";

                // PARTHA
                var urlPassed = "http://localhost:9999/start/location/";
                $scope.listRequest($http, $scope, urlPassed, itemObj, requestList.locationList);

                $("#cityInput").css('display', 'none');
                $("#shopInput").css('display', 'none');
                $("#locationPopup").css('display', 'block');
            }
            else if ($scope.reLocateshop == "true")
            {



                itemObj = $scope.locationSelectedId;
                // urlPassed = "http://localhost:9999/start/shop/";

                // PARTHA
                urlPassed = "http://localhost:9999/start/shop/";
                $scope.listRequest($http, $scope, urlPassed, itemObj, requestList.shopList);
                $("#locationInput").css('display', 'none');
                $("#cityInput").css('display', 'none');
                $("#locationPopup").css('display', 'block');
            }

            else
            {

                $('#cityInput').val($scope.citySelected);
                $('#locationInput').val($scope.locationSelected);
                $('#shopInput').val($scope.shopSelected);
                $scope.itemSearch = true;
                $("#cityDone").click();

            }




        }

        $(document).on("click", "#citySelected", function(event)
        {
            $scope.reLocateLocation = false;
            $scope.reLocateCity = true;
            $scope.reLocateshop = false;
            localStorage.setItem('citySelected', $scope.citySelected);
            localStorage.setItem('reLocateCity', $scope.reLocateCity);
            localStorage.setItem('reLocateLocation', $scope.reLocateLocation);
            localStorage.setItem('reLocateshop', $scope.reLocateshop);

            window.location.href = "/pages/loadView.html";


        });

        $(document).on("click", "#locationSelectedNav", function(event)
        {
            $scope.reLocateLocation = true;
            $scope.reLocateCity = false;
            $scope.reLocateshop = false;
            localStorage.setItem('citySelected', $scope.citySelected);
            localStorage.setItem('reLocateCity', $scope.reLocateCity);
            localStorage.setItem('reLocateLocation', $scope.reLocateLocation);
            localStorage.setItem('reLocateshop', $scope.reLocateshop);

            window.location.href = "/pages/loadView.html";


        });

        $(document).on("click", "#shopSelectedNav", function(event)
        {
            $scope.reLocateLocation = false;
            $scope.reLocateCity = false;
            $scope.reLocateshop = true;
            localStorage.setItem('citySelected', $scope.citySelected);
            localStorage.setItem('reLocateCity', $scope.reLocateCity);
            localStorage.setItem('reLocateLocation', $scope.reLocateLocation);
            localStorage.setItem('reLocateshop', $scope.reLocateshop);

            window.location.href = "/pages/loadView.html";


        });




        // ADING EVENT LISTENER FOR FILLING QUANTITY INPUT AS PER THE ITEM SELECTED
        document.getElementById('itemInput').addEventListener('input', function()
        {

            var itemObj = $('#itemSelectList > option:selected').attr('data-info');
            itemObj = JSON.parse(itemObj);
            ;
            var quantItem = itemObj.sizeId;
            $scope.quantityList = $scope.quantArray[quantItem];
            listQuant = $scope.quantityList.slice(1, $scope.quantityList.length + 1);
            if (listQuant.length > 0)
            {


                $scope.isDisabledQuant = false;
                $scope.CheckScopeBeforeApply();



                $("#itemQuantInput").autocomplete(
                {
                    source: listQuant,
                    minLength: 0,
                    delay: 0

                }).on('focus', function(event)
                {
                    var self = this;
                    $(self).autocomplete("search", this.value);;
                });
            }
        });




        // ADING EVENT LISTENER FOR FILLING LOCATION INPUT AS PER THE CITY SELECTED
        document.getElementById('cityInput').addEventListener('input', function()
        {

            var inputVal = $('#cityInput').val();
            var selectDrop = $('#citySelectList');
            var val = $(selectDrop).find('option[value="' + inputVal + '"]');
            var itemObj = val.attr('data-city');

            // PARTHA
            var urlPassed = "http://localhost:9999/start/location/";


            $scope.listRequest($http, $scope, urlPassed, itemObj, requestList.locationList);
            $scope.locationSelected = "";
            $("#locationInput").show();

        });

        // ADING EVENT LISTENER FOR FILLING SHOP INPUT AS PER THE LOCATION SELECTED 
        document.getElementById('locationInput').addEventListener('input', function()
        {

            var inputVal = $('#locationInput').val();
            var selectDrop = $('#locationSelectList');
            var val = $(selectDrop).find('option[value="' + inputVal + '"]');
            var itemObj = val.attr('data-location');

            // PARTHA
            var urlPassed = "http://localhost:9999/start/shop/";
            $scope.listRequest($http, $scope, urlPassed, itemObj, requestList.shopList);

            $scope.shopSelected = "";
            $("#shopInput").show();

        });

        document.getElementById('shopInput').addEventListener('input', function()
        {
            if ($('#locationInput').val() == '')
                alert("Please specify Location first");

        });




    });



    $("#cityDone").click(function()
    {

        ;
        if ($('#cityInput').val() == '')
        {
            alert('City can not be left blank');
        }
        else if ($('#locationInput').val() == '')
        {
            alert('Location can not be left blank');
        }
        else if ($('#shopInput').val() == '')
        {
            alert('Shop can not be left blank');
        }
        else
        {
            $scope.showCart = true;

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

            $scope.itemSearch = true;
            
            $scope.citySelected = $('#cityInput').val();
            
          
            
           /* if ($('#citySelectList > option').attr('data-city') != undefined)
            {
                $scope.citySelectedId = $('#citySelectList > option:selected').attr('data-city');
                localStorage.setItem('citySelectedId', $scope.citySelectedId);
            }*/
            
            $scope.locationSelected = $('#locationInput').val();
            if ($('#locationSelectList > option').attr('data-location') != undefined)
            {
                $scope.locationId = $('#locationSelectList > option:selected').attr('data-location');
                localStorage.setItem('locationSelectedId', $scope.locationId);
            }


            $scope.shopSelectList = $('#shopInput').val();
            if ($('#shopSelectList > option').attr('data-shop') != undefined)
            {
                $scope.shopId = $('#shopSelectList > option:selected').attr('data-shop');
                localStorage.setItem('shopSelectedId', $scope.shopId);
            }

            $scope.CheckScopeBeforeApply();


            localStorage.setItem('citySelected', $scope.citySelected);
            localStorage.setItem('locationSelected', $scope.locationSelected);
            localStorage.setItem('shopSelected', $scope.shopSelected);




            $scope.reLocateLocation = false;
            $scope.reLocateCity = false;
            $scope.reLocateshop = false;
            localStorage.setItem('reLocateLocation', $scope.reLocateLocation);
            localStorage.setItem('reLocateCity', $scope.reLocateCity);
            localStorage.setItem('reLocateshop', $scope.reLocateshop);


            $("#shopInput").hide();
            $("#locationInput").hide();


        }
        var itemObj = localStorage.getItem('shopSelectedId');
        var urlPassed = "http://localhost:9999/start/item/";

        // PARTHA
        // var urlPassed = "http://192.168.1.5:8080/websample/rest/landing/shops/items/";
        //itemObj = "SHOP_4";
        $scope.listRequest($http, $scope, urlPassed, itemObj, requestList.itemList);
        //$scope.listCategoryRequest($http, $scope.locationId);


    });


    $scope.items = [];




    // =============================  START CART====================
    var retrievedCart = localStorage.getItem('storedCart');
    //var retrievedCart = null;

    if (retrievedCart == null || retrievedCart == undefined || retrievedCart == "" || Number(localStorage.getItem('mainTotal')) == 0)
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
        $scope.mainTotal = Number(localStorage.getItem('mainTotal'));
        $scope.cartItem = Number(localStorage.getItem('cartItem'));
    }


    // Function to add item to cart
    $scope.addItemToCart = function(item, errorCheckSkip, event)
    {
        // To get Object as String, converting it to Object and initialising basic properties. 

        var itemObj;
        var oldItemTotal;
        var quantNumber;


        var unitCheck;
        
        if (errorCheckSkip)
        {
            itemObj = item;
            quantNumber = item.levelmodel;
        }
        else
        {
            itemObj = $('#itemSelectList > option').attr('data-info');
            itemObj = JSON.parse(itemObj);
            quantNumber = $("#itemQuantInput").val();
        }



        if ($('#cartTable').is(":hidden"))
        {

            $("#cartSymbol").toggle("highlight");
            $("#cartSymbol").toggle("highlight");


        }


        if (!errorCheckSkip && ($scope.itemName == "" || $scope.itemName == undefined || $("#itemQuantInput").val() == ""))
        {
            $scope.errorShow = true;

        }
        else
        {
            $scope.errorShow = false;



            $scope.quantityList = $scope.quantArray[itemObj.sizeId];
            listQuant = $scope.quantityList.slice(1, $scope.quantityList.length + 1);

            var unitCheck = $scope.quantityList[0].id;

            itemObj.total = Number(itemObj.price);


            if (unitCheck != '5' && unitCheck != 5)
                itemObj.unit = (quantNumber).replace(/[0-9]/g, ''); // gives alphabet
            else
                itemObj.unit = "-";




            // Adding the object to cart list
            if ($scope.addedList[itemObj.itemId] == undefined)
            {
                $scope.cartLength = $scope.cartLength + 1;
                itemObj.qty = (quantNumber).replace(/[^\d]/g, ''); // gives digit
                $scope.addedList[itemObj.itemId] = itemObj;
                $scope.addedList[itemObj.itemId].total = Number(itemObj.qty) * Number(itemObj.price);
                $scope.mainTotal += Number(itemObj.qty) * Number(itemObj.price);
                $scope.cartItem = Number($scope.cartItem) + 1;
            }
            else
            {
                oldItemTotal = $scope.addedList[itemObj.itemId].total;
                var num = Number((quantNumber).replace(/[^\d]/g, '')); // gives digit
                itemObj.qty = Number($scope.addedList[itemObj.itemId].qty) + num;
                $scope.addedList[itemObj.itemId] = itemObj;
                $scope.addedList[itemObj.itemId].total = Number(itemObj.qty) * Number(itemObj.price);
                if ($scope.cartItem == 1)
                    $scope.mainTotal = Number(itemObj.qty) * Number(itemObj.price);
                else
                    $scope.mainTotal += Number($scope.addedList[itemObj.itemId].total) - Number(oldItemTotal);

            }




            storeCartDetails();
        }

    }

    // Function to update cart on editing quantity from cart.
    $scope.updateCart = function(inputQty, itemObj)
    {
        var oldItemTotal = $scope.addedList[itemObj.itemId].total;
        $scope.addedList[itemObj.itemId].qty = inputQty;
        $scope.addedList[itemObj.itemId].total = $scope.addedList[itemObj.itemId].price * inputQty;

        if ($('#cartTable').is(":hidden"))
        {
            $("#cartSymbol").effect("shake",
            {
                times: 1
            }, 1000);
        }
        if ($scope.cartItem == 1)
        {
            $scope.mainTotal = $scope.addedList[itemObj.itemId].total;

        }

        else
        {
            $scope.mainTotal += Number($scope.addedList[itemObj.itemId].total) - Number(oldItemTotal);
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


        $scope.mainTotal = Number($scope.mainTotal) - Number($scope.addedList[itemObj.itemId].total);
        delete $scope.addedList[itemObj.itemId];
        $scope.cartLength = $scope.cartLength - 1;
        $scope.cartItem = $scope.cartItem - 1;
        storeCartDetails();
    }

    // Function to empty full cart
    $scope.emptyCart = function()
    {

        $("#cartTable").animate(
        {
            opacity: 0.4,
            marginLeft: "-140%",
        }, 1000, function()
        {


            $scope.mainTotal = 0;
            $scope.cartLength = 0;
            $scope.addedList = {};
            $scope.cartItem = 0;
            storeCartDetails();
            $scope.CheckScopeBeforeApply();



        });

    }



    // Function to ask for register or guest user
    $scope.userCategory = function()
    {
        ;

        $('#browseList').hide("scale",
        {
            direction: 'vertical'
        }, 400);

        $("#cartTable,#searchBarDiv").animate(
        {
            opacity: 0,
            marginLeft: "-140%",
        }, 1000, function()
        {

            // $("#cartTable,#searchBarDiv").hide();

            $("#cartTable,#searchBarDiv").hide("fast", function()
            {
                $scope.showCartUserType = true;
                $scope.CheckScopeBeforeApply();
                $("#cartUserType").fadeIn('slow');
            });




        });

        $scope.registerForm = function()
        {
            $scope.showRegisterForm = true;
            $scope.CheckScopeBeforeApply();
            $.when($('#cartUserType').fadeOut(500))
                .done(function()
                {
                    $("#registerUserForm").show('slow');
                });



        }

        $scope.guestForm = function()
        {


            $scope.showGuestForm = true;
            $scope.CheckScopeBeforeApply();
            $.when($('#cartUserType').fadeOut(500))
                .done(function()
                {
                    $("#guestUserForm").show('slow');
                });

        }

        $scope.removeRegOrGuestForm = function()
        {
            $scope.showRegisterForm = false;
            $scope.showGuestForm = false;
            $scope.CheckScopeBeforeApply();
            $("#cartUserType").fadeIn('slow');


        }




        $scope.submitGuest = function()
        {
            $scope.showRegisterForm = false;
            $scope.showGuestForm = false;
            $scope.showCartUserType = false;
            $scope.showdeliveryAddressForm = true;
            $scope.CheckScopeBeforeApply();
            $("#cartTable,#searchBarDiv").show();
            $("#cartTable,#searchBarDiv").animate(
            {
                opacity: 1,
                marginLeft: "5%",
            }, 1000, function() {

            });


        }

        $scope.submitRegister = function()
        {
            $scope.showRegisterForm = false;
            $scope.showGuestForm = false;
            $scope.showCartUserType = false;
            $scope.showdeliveryAddressForm = true;
            $scope.CheckScopeBeforeApply();
            $("#cartTable,#searchBarDiv").show();
            $("#cartTable,#searchBarDiv").animate(
            {
                opacity: 1,
                marginLeft: "5%",
            }, 1000, function() {

            });


        }
        
        $scope.submitPayment = function()
        {
        	
        	$scope.showPaymentOption = false;
        	$scope.orderCompleteShow = true;
        	
        	
        }
        
        $scope.orderDone = function()
        {
        	$scope.showPaymentOption = false;
        	$scope.orderCompleteShow = false;
        	 $scope.mainTotal = 0;
             $scope.cartLength = 0;
             $scope.addedList = {};
             $scope.cartItem = 0;
             storeCartDetails();
             $scope.CheckScopeBeforeApply();
        	 $("#cartTable,#searchBarDiv").show();
             $("#cartTable,#searchBarDiv").animate(
             {
                 opacity: 1,
                 marginLeft: "5%",
             }, 1000, function() {

             });
        }

        $scope.selectPaymentOption = function()
        {
            $scope.showRegisterForm = false;
            $scope.showGuestForm = false;
            $scope.showCartUserType = false;


            $.when($('#cartTable,#searchBarDiv,#deliveryAddressForm').fadeOut(500))
                .done(function()
                {
                    $scope.showPaymentOption = true;
                    $scope.CheckScopeBeforeApply();
                });



        }




    }



    function storeCartDetails()
    {
        localStorage.setItem('storedCart', JSON.stringify($scope.addedList));
        localStorage.setItem('cartLength', $scope.cartLength);
        localStorage.setItem('mainTotal', $scope.mainTotal);
        localStorage.setItem('cartItem', $scope.cartItem);


    }

    function storeResult(cityListReturned, variable)
    {


        variable = cityListReturned;

        $scope.CheckScopeBeforeApply();
    }

    $scope.locationRequest = function($http)
    {

        //var dataSend = "username="+$scope.username+"&password="+$scope.password+"&company="+$scope.company;
        //alert($scope.username + " - " + $scope.password + " - " + $scope.company)
        var dataSend = "[{\"name\":\"partha\"},{\"name\":\"efprotim\"}]";

        var cityListReturned;
        //var urlLoc = "http://localhost:9999/start/city";

        // PARTHA
        var urlLoc = "http://localhost:8080/rest/landing/cities";



        $http(
        {
            method: "GET",
            url: urlLoc
        }).success(function(data)
        {

            console.log("Success data = " + JSON.stringify(data));
            $scope.cityList = data;

            $scope.CheckScopeBeforeApply();


        }).error(function(data, status, headers, config)
        {
            // called asynchronously if an error occurs // or server returns response with an error status. 
            console.log("Error = > " + status);


        });

    }




    $scope.listRequest = function($http, $scope, urlReq, reqParam, valueStored)
    {

        //var dataSend = "username="+$scope.username+"&password="+$scope.password+"&company="+$scope.company;
        //alert($scope.username + " - " + $scope.password + " - " + $scope.company)
        var dataSend = "[{\"name\":\"partha\"},{\"name\":\"efprotim\"}]";
        //urlReq = "http://localhost:9999/start/item/";
        //urlReq = server_url_item+locationId;
        
        // PARTHA
        var finalUrl = urlReq + reqParam;
        var cityListReturned;

        var itemData = [];
        var k = 0;
        var categoryIndex = 0;
        var subCategoryIndex = 0;
        var itemIndex = 0;




        $http(
        {
            method: "GET",
            url: finalUrl
        }).success(function(data)
        {



            



            if (valueStored == requestList.cityList)
            	{
            	 $scope.cityList = data;
            	 
            	}               
            else if (valueStored == requestList.locationList)
            	{

                $scope.locationList = data;
                localStorage.setItem('citySelectedId', reqParam);
            	}
            else if (valueStored == requestList.shopList)
                $scope.shopList = data;
            else if (valueStored == requestList.itemList)
            {

                // $scope.listCategoryItem = data;
                var items;
                
                
                for (var category in data)
                {
                    
                	var catObj = new Object();
                    if (data.hasOwnProperty(category))
                    {
                        var catArray = category.split(":");
                        catObj.categoryName = catArray[1];
                        catObj.categoryId = catArray[0];
                        catObj.subCategory = new Array();
                        subCategoryIndex = 0;
                        
                        for (var subCategory in data[category])
                        {
                        	var subCatObj = new Object();
                            if (data[category].hasOwnProperty(subCategory))
                            {
                            	 
                            	catArray = subCategory.split(":");
                            	subCatObj.subCategoryName = catArray[1];
                            	subCatObj.subCategoryId = catArray[0];
                            	subCatObj.items = data[category][subCategory];
                            	catObj.subCategory[subCategoryIndex++] = subCatObj; 
                                for (var items in data[category][subCategory])
                                {
                                    itemData[k++] = data[category][subCategory][items];
                                    $scope.model = data[category][subCategory][items].sizeId;
                                    
                                }
                               
                            }
                       }
                       
                    }
                    $scope.listCategoryItem[categoryIndex++] = catObj;
                    
                }
               
                $scope.items = itemData;
                console.log("Item Category  = >"+JSON.stringify($scope.listCategoryItem));
                /* $scope.listCategoryItem = data;
                	  	var items;
                	  	
                	  	for(var i=0;i<data.length;i++)
                	  		{
                	  		items = data[i].items;
                	  		for(var j=0;j<items.length;j++)
                	  			{
                	  			itemData[k++] = items[j];
                	  			var model = items[j].levelmodel;
                	  			$scope.model = $scope.quantArray[items[j].sizeId];
                	  		
                	  			}
                	  			
                	  		}
                	  		
                	  $scope.items = itemData;*/
            }




            $scope.CheckScopeBeforeApply();




        }).error(function(data, status, headers, config)
        {
            // called asynchronously if an error occurs // or server returns response with an error status. 
            console.log("Error = > " + status + " data= " + data);


        });




    }


}

LaunchCtrl.$inject = ['$scope', '$http'];