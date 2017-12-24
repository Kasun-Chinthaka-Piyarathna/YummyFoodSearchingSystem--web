

(function () {
    'use strict';

    angular
        .module('yummy')
        .controller('registerController', registerController);

    registerController.$inject = ['$scope', 'registerService' ,'cfpLoadingBar' , '$rootScope','$state'];




    function registerController($scope , registerService , cfpLoadingBar , $rootScope ,$state) {

        $scope.imagePath = 'img/washedout.png';

        $scope.register = function () {
            registerService.registerRestaurant($scope.restaurant).then(function (data) {

                $scope.resonseResgistration = data ;

            });
        };




        //restaurant sign in
        $scope.RSignIn = function () {
            registerService.restaurantSignIn($scope.filters).then(function (data) {

                $scope.rrr = data;


            });
        };
        //going to update page
        $scope.Update = function (rrr) {

            $state.go('restaurantUpdate' , {rrr : rrr} );

        };




    }

    //upload a photo


    app.controller('AppCtrl', ['$scope', '$mdSidenav', '$document', '$http', function($scope, $mdSidenav, $document, $http){


        $scope.toggleSidenav = function(menuId) {
            $mdSidenav(menuId).toggle();
        };


        $scope.scroll = function(){

            var someElement = angular.element(document.getElementById('bottom'));
            var container   = angular.element(document.getElementById('container'));
            $scope.something = "clickeddd";
            container.scrollTo(someElement,0,1000);
        }

    }]);
})();

