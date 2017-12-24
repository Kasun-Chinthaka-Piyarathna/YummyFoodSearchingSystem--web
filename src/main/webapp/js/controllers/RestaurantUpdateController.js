

(function () {
    'use strict';

    angular
        .module('yummy')
        .controller('restaurantUpdateController', restaurantUpdateController);

    restaurantUpdateController.$inject = ['$rootScope', 'restaurantUpdateService','$state','$stateParams','$scope','$mdDialog'];

    function  restaurantUpdateController($rootScope,restaurantUpdateService,$state,$stateParams,$scope,$mdDialog) {


        $scope.rrr = $stateParams.rrr;

        $scope.filters = {};




        $scope.insertFoodItem2 = function (rrr) {
            restaurantUpdateService.insertFoodItem($scope.filters,rrr).then(function (data) {

                $scope.result = data ;

            });


        };


        $scope.showAdvanced = function (ev,AvailableFoodItem,rrr) {



            $mdDialog.show({


                controller: DialogController,
                templateUrl: 'images/dialog3.tmpl.html',
                parent: angular.element(document.body),
                targetEvent: ev,
                clickOutsideToClose: true,
                fullscreen: $scope.customFullscreen,
                locals: {
                    items: AvailableFoodItem,
                    res:rrr
                }
            })
                .then(function (answer) {
                    $scope.status = 'You said the information was "' + answer + '".';
                }, function () {
                    $scope.status = 'You cancelled the dialog.';
                });
        };


        //

        function DialogController($scope, $mdDialog, items,res, restaurantUpdateService) {


            $scope.filters = {
                fi : items.Name,
                ri: res.Restaurant_ID,


                // cid : $rootScope.user.Customer_ID



            };



            $scope.update= function () {
                restaurantUpdateService.UpdateFoodItem($scope.filters).then(function success() {
                    $mdDialog.hide();
                    // cpfLoadingBar.complete();m
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

        ///////


    }

})();
