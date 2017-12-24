

(function () {
    'use strict';

    angular
        .module('yummy')
        .controller('ratingController', ratingController);


    ratingController.$inject = ['$scope','ratingService' ,'$state'];

    function ratingController($scope,ratingService ,$state) {
        $scope.imagePath = 'images/hotel2.jpg';

    }
})();


