(function () {
    'use strict';

    angular
        .module('yummy')
        .factory('restaurantUpdateService', restaurantUpdateService);

    restaurantUpdateService.$inject = ['$http'];

    function restaurantUpdateService($http) {

        var webApi = "http://localhost:8080/rest/";


        var service = {
            UpdateFoodItem:UpdateFoodItem,
            insertFoodItem:insertFoodItem
        };

        return service;


        function UpdateFoodItem(filters) {


            return $http.get(webApi + 'foodservice/updateRestaurant?ri='+filters.ri+'&fi='+filters.fi+'&up='+filters.up+'&qu='+filters.qu).then(handleSuccess, handleError('Error getting drivers'));
        }

        function insertFoodItem(filters,rrr) {


            return $http.get(webApi + 'foodservice/insertFoodItem?id='+rrr.Restaurant_ID+'&name='+filters.FName+'&quantity='+filters.Quantity+'&price='+filters.UnitPrice).then(handleSuccess, handleError('Error getting drivers'));
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