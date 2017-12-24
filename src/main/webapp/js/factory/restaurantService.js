
(function () {
    'use strict';

    angular
        .module('yummy')
        .factory('restaurantService', restaurantService)
        .config(function($mdThemingProvider) {
        $mdThemingProvider.theme('dark-grey').backgroundPalette('grey').dark();
        $mdThemingProvider.theme('dark-orange').backgroundPalette('orange').dark();
        $mdThemingProvider.theme('dark-purple').backgroundPalette('deep-purple').dark();
        $mdThemingProvider.theme('dark-blue').backgroundPalette('blue').dark();
    });

    restaurantService.$inject = ['$http'];

    function restaurantService($http) {

        var webApi = "http://localhost:8080/rest/";


        var service = {
            getAllRestaurants: getAllRestaurants,
            getAllCommentsByRestaurant : getAllCommentsByRestaurant,
            addRatingsToRestaurant : addRatingsToRestaurant

        };

        return service;


        function getAllRestaurants() {
            return $http.get(webApi + 'foodservice/getAllRestaurantsTest').then(handleSuccess, handleError('Error getting drivers'));
        }

        function getAllCommentsByRestaurant(id) {
            return $http.get(webApi + 'foodservice/viewcomments?rid=' + id).then(handleSuccess, handleError('Error getting drivers'));
        }

        function addRatingsToRestaurant(filters) {
            return $http.get(webApi + 'foodservice/comment?rid=' + filters.rid + '&cid=' + filters.cid+ '&comment=' + filters.comment+ '&rating=' + filters.starrate+ '&delivery_status=' + filters.delivery_status + '&time_status=' + filters.time_status).then(handleSuccess, handleError('Error getting drivers'));
        }



        // UNDONE: Duplicate method on all services
        function handleSuccess(res) {
            return res.data;
        }

        function handleError(error) {
            return function () {
                return { success: false, message: error };
            };
        }
    }





})();



