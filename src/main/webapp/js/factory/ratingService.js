(function () {
    'use strict';

    angular
        .module('yummy')
        .factory('ratingService', ratingService);

    ratingService.$inject = ['$http'];

    function ratingService($http) {

        var webApi = "http://localhost:8080/rest/";


        var service = {
            getRestaurantsByFoodAndCity: getRestaurantsByFoodAndCity,
            getRestaurantsByName: getRestaurantsByName,
            getRestaurantsByLocation: getRestaurantsByLocation
        };

        return service;


        function getRestaurantsByFoodAndCity(filters) {
            return $http.get(webApi + 'foodservice/foodWise?fo='+filters.foodName +'&ci='+filters.location).then(handleSuccess, handleError('Error getting drivers'));
        }

        function getRestaurantsByName(filters) {
            return $http.get(webApi + 'foodservice/restaurantWise?res='+filters.restaurantName).then(handleSuccess, handleError('Error getting single driver'));
        }

        function getRestaurantsByLocation(filters) {
            return $http.get(webApi + 'foodservice/locationWise?loc='+filters.nearestCity ).then(handleSuccess, handleError('Error getting single driver'));
        }


        function name1(filters) {
            return $http.get(webApi + 'api/drivers?id=' + driver.id).then(handleSuccess, handleError('Error getting single driver'));
        }
        function name2(filters) {
            return $http.get(webApi + 'api/drivers?id=' + driver.id).then(handleSuccess, handleError('Error getting single driver'));
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



