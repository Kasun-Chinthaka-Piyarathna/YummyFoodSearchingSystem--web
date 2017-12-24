
(function () {
    'use strict';

    angular
        .module('yummy')
        .controller('findusController', findusController);


    findusController.$inject = ['$scope', 'findusService' , '$stateParams' , '$rootScope','$mdDialog' ,'restaurantService'];

    function findusController($scope , findusService , $stateParams  , $rootScope,$mdDialog , restaurantService) {


        if($stateParams.restaurant!= undefined) {
            if ($stateParams.restaurant != null) {
                if ($stateParams.restaurant != "null") {

                    localStorage.CARTITEM = null;
                    $rootScope.allItems = [];
                    $scope.restaurant = $stateParams.restaurant;
                    $rootScope.savedRestaurant = $scope.restaurant;

                    localStorage.setItem("restaurant", JSON.stringify($scope.restaurant));



                }else {
                    var resta = localStorage.getItem("restaurant");
                    $rootScope.savedRestaurant = JSON.parse(resta);
                    $scope.restaurant = $rootScope.savedRestaurant;
                }
            }else {
                var resta = localStorage.getItem("restaurant");
                $rootScope.savedRestaurant = JSON.parse(resta);
                $scope.restaurant = $rootScope.savedRestaurant;
            }
        }else {
            var resta = localStorage.getItem("restaurant");
            $rootScope.savedRestaurant = JSON.parse(resta);
            $scope.restaurant = $rootScope.savedRestaurant;
        }



                    $scope.imagePath = 'images/hotel2.jpg';

                    // $scope.restaurant = $stateParams.restaurant;
                    // $rootScope.allItems = [];





                    restaurantService.getAllCommentsByRestaurant($scope.restaurant.Restaurant_ID).then(function sucess(data) {

                        var ratingCount = 0;

                        $.each(data, function (i, item) {
                            ratingCount += Number(item.Rating);
                        });

                        $scope.rating = Math.round((ratingCount / data.length) / 10) * 10;


                    });




                    $scope.user = {
                        name: 'John Doe',
                        email: '',
                        phone: '',
                        address: 'Mountain View, CA',
                        donation: 19.99
                    };

                    $scope.filters = {
                        currentLocation: undefined,
                        destination: undefined

                    };

                    $scope.addToCart = function (item, quantity) {

                        //
                        // var cart = localStorage.getItem("CARTITEM");
                        // $rootScope.allItems = JSON.parse(cart);
                        // $scope.allItems = $rootScope.allItems;

                        if(!($rootScope.allItems==null)) {
                            for (var i = 0; i < $rootScope.allItems.length; i++) {
                                if ($rootScope.allItems[i].Name == item.Name) {
                                    $rootScope.allItems[i].selectedQuantity = quantity;
                                    var success;
                                    success = "Done";
                                }
                            }
                            if(!(success == "Done")) {
                                item.selectedQuantity = quantity;
                                // item.resid =localStorage.restaurant.Restaurant_ID;
                                $rootScope.allItems.push(item);
                                localStorage.setItem("CARTITEM", JSON.stringify($rootScope.allItems));
                            }
                        }else {

                            item.selectedQuantity = quantity;
                            // item.resid =localStorage.restaurant.Restaurant_ID;
                            $rootScope.allItems.push(item);
                            localStorage.setItem("CARTITEM", JSON.stringify($rootScope.allItems));
                        }

                    };


                    $scope.showPath = function (map) {
                        $scope.filters.currentLocation = $scope.currentLocation.formatted_address;
                        $scope.filters.destination = $scope.restaurant.Location;
                        $rootScope.currentlocation.push($scope.filters.currentLocation);
                        // $rootScope.distanceCal = [];
                        var dis = map.directionsRenderers[0].directions.routes[0].legs[0].distance.text;
                        $rootScope.distanceCal.push(map.directionsRenderers[0].directions.routes[0].legs[0].distance.text);
                        // $scope.distanceCal = map.directionsRenderers[0].directions.routes[0].legs[0].distance.text;


                        // {{map.directionsRenderers[0].directions.routes[0].overview_path.length}}

                        //
                        // $rootScope.calculatedDistance = Math.round(($scope.ngmap.map.directionsRenderers[0].directions.routes[0].legs[0].distance.value)/1000) ;
                        // $rootScope.calculatedDistance= map.directionsRenderers[0].directions.routes[0].overview_path.length;
                        //
                        // $rootScope.calculatedDistance = Math.round((map.directionsRenderers[0].directions.routes[0].legs[0].distance) / 1000);


                    };


                    $scope.showAdvanced = function (ev, restaurant) {


                        $mdDialog.show({


                            controller: DialogController,
                            templateUrl: 'images/dialog2.tmpl.html',
                            parent: angular.element(document.body),
                            targetEvent: ev,
                            clickOutsideToClose: true,

                            fullscreen: $scope.customFullscreen,
                            locals: {
                                items: restaurant
                            }


                        })
                            .then(function (answer) {
                                $scope.status = 'You said the information was "' + answer + '".';
                            }, function () {
                                $scope.status = 'You cancelled the dialog.';
                            });
                    };


                    function DialogController($scope, $mdDialog, items, restaurantService, $rootScope) {//, cpfLoadingBar


                        $scope.filters = {
                            rid: items.Restaurant_ID,
                            cid: $rootScope.user.Customer_ID
                        };


                        $scope.addRating = function () {
                            restaurantService.addRatingsToRestaurant($scope.filters).then(function success() {
                                $mdDialog.hide();
                                // cpfLoadingBar.complete();
                            });
                        };


                        $scope.hide = function () {
                            $mdDialog.hide();
                        };

                        $scope.cancel = function () {
                            $mdDialog.cancel();
                        };

                        $scope.answer = function (answer) {
                            $mdDialog.hide(answer);
                        };
                    }




//end
    }
})();

