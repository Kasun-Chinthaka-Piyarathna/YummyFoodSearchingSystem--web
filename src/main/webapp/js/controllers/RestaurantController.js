

(function () {
    'use strict';

    angular
        .module('yummy')
        .controller('restaurantController', restaurantController);

    restaurantController.$inject = ['$scope', 'restaurantService','$state','$mdDialog'];

    function restaurantController($scope , restaurantService,$state ,$mdDialog) {


        $scope.imagePath = 'images/hotel1.jpg';

            restaurantService.getAllRestaurants($scope.filters).then(function (data) {

                $scope.restuarentsAll = data ;

            });


        $scope.heroImage = {
            background: 'url(js/Lib/slider/images/dextop2.jpg)'
        };


        $scope.viewRestaurant = function (restaurant) {

            $state.go('findus' , {restaurant : restaurant} );

        };

        //start cutom dialog

        $scope.status = '  ';
        $scope.customFullscreen = false;
        $scope.showAdvanced = function(ev , restaurant) {




                // $state.go('dialog' , {restaurant22 : restaurant} );




           // $scope.restaurant = restaurant;

            // restaurantService.getAllRestaurants($scope.filters).then(function (data) {
            //
            //     $scope.restuarentsAll = data ;
            //
            // });

            $mdDialog.show({


                controller: DialogController,
                templateUrl: 'images/dialog1.tmpl.html',
                parent: angular.element(document.body),
                targetEvent: ev,
                clickOutsideToClose:true,
                fullscreen: $scope.customFullscreen,
                locals: {
                    items: restaurant
                }
            })
                .then(function(answer) {
                    $scope.status = 'You said the information was "' + answer + '".';
                }, function() {
                    $scope.status = 'You cancelled the dialog.';
                });
        };

        //

         function DialogController($scope, $mdDialog , items , restaurantService  ) {




             restaurantService.getAllCommentsByRestaurant(items.Restaurant_ID).then(function success(data) {
                 $scope.comments =  data;



             });




                 // data[0].CommentData



            $scope.hide = function() {
                $mdDialog.hide();
            };

            $scope.cancel = function() {
                $mdDialog.cancel();
            };

            $scope.answer = function(answer) {
                $mdDialog.hide(answer);
            };
        }
        //end cutom dialog


    }


})();



