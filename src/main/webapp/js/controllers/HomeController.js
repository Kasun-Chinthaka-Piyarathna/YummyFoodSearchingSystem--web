

(function () {
    'use strict';

    angular
        .module('yummy')
        .controller('homeController', homeController);



    // app.config(function ($mdThemingProvider) {
    //     $mdThemingProvider
    //         .theme('default')
    //         .primaryPalette('indigo')
    //         .accentPalette('pink')
    //         .warnPalette('red')
    //         .backgroundPalette('blue-grey');
    // });

    homeController.$inject = ['$scope', 'homeService' ,'$state' , '$q'];

    function homeController($scope , homeService , $state , $q ) {



        $scope.querySearch = function (query) {

             var def = $q.defer();
             homeService.searchRestaurantsByName(query).then(function (data) {
               def.resolve(data);
             });
             return def.promise;
        };

         $scope.imagePath = 'js/Lib/slider/images/icon1.png';

        // $scope.heroImage = {
        //     background: 'url(js/Lib/slider/images/dextop.jpg)'
        // };



        $scope.searchResults = function () {
            homeService.getRestaurantsByFoodAndCity($scope.filters).then(function (data) {

                $scope.restuarents = data ;

            });


        };
        $scope.searchResults2 = function () {
            homeService.getRestaurantsByName($scope.filters).then(function (data) {

                $scope.restuarents2 = data ;

            });


        };

        $scope.searchResults3 = function () {
            homeService.getRestaurantsByLocation($scope.filters).then(function (data) {

                $scope.restuarents3 = data ;

            });


        };


        $scope.viewRestuarent = function (restaurant) {

            $state.go('findus' , {restaurant : restaurant} );

        };



        $scope.viewAllRestuarents = function () {

            $state.go('restaurant');

        };

    }
})();

