/**
 * Created by Kasun Chinthaka on 1/25/2017.
 */
(function () {
    'use strict';

    angular
        .module('yummy')
        .factory('registerService', registerService);

    registerService.$inject = ['$http'];

    function registerService($http) {

        var webApi = "http://localhost:8080/rest/";


        var service = {
            registerRestaurant: registerRestaurant,
            customerSignUp:customerSignUp,
            customerSignIn:customerSignIn,
            restaurantSignIn:restaurantSignIn

        };

        return service;

        function customerSignUp(filters) {
            return $http.get(webApi + 'foodservice/customerSignUp?cname=' + filters.cname + '&phone=' + filters.phone+ '&email=' + filters.email+ '&nic=' + filters.nic+ '&username=' + filters.username+ '&pwd=' + filters.pwd).then(handleSuccess, handleError('Error getting drivers'));
        }


        function customerSignIn(filters) {
            return $http.get(webApi + 'foodservice/customerSignIn?cname=' + filters.logusername +  '&pwd=' + filters.logpwd).then(handleSuccess, handleError('Error getting drivers'));
        }



        // function customerSignIn(filters) {
        //     return $http.get(webApi + 'foodservice/customerSignIn?cname=' + filters.logusername + '&pwd=' + filters.logpwd ).then(handleSuccess, handleError(error));
        // }


        function registerRestaurant(filters) {
            return $http.get(webApi + 'foodservice/register?rname='+filters.restaurantName +'&uname='+filters.userName+'&pwd='+filters.password+'&ofac='+filters.orderingFacility+'&email='+filters.email+'&rconta='+filters.contactNumber+'&city='+filters.nearestCity+'&fooditems='+filters.availableFoodItems + '&location=' + filters.location.formatted_address + '&image=' + filters.image).then(handleSuccess, handleError('Error getting drivers'));
        }

        function restaurantSignIn(filters) {
            return $http.get(webApi + 'foodservice/restaurantSignIn?cname=' + filters.rlogusername +  '&pwd=' + filters.rlogpwd).then(handleSuccess, handleError('Error getting drivers'));
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

