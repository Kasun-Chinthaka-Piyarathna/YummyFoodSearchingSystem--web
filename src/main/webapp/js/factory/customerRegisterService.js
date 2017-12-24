/**
 * Created by Tharindu on 3/14/2017.
 */
/**
 * Created by Kasun Chinthaka on 1/25/2017.
 */
(function () {
    'use strict';

    angular
        .module('yummy')
        .factory('customerRegisterService', customerRegisterService);

    customerRegisterService.$inject = ['$http'];

    function customerRegisterService($http) {

        var webApi = "http://localhost:8080/rest/";


        var service = {

            customerSignUp:customerSignUp,
            customerSignIn:customerSignIn,


        };

        return service;

        function customerSignUp(filters) {
            return $http.get(webApi + 'foodservice/customerSignUp?cname=' + filters.cname + '&phone=' + filters.phone+ '&email=' + filters.email+ '&nic=' + filters.nic+ '&username=' + filters.username+ '&pwd=' + filters.pwd).then(handleSuccess, handleError('Error getting drivers'));
        }


        function customerSignIn(filters) {
            return $http.get(webApi + 'foodservice/customerSignIn?cname=' + filters.logusername +  '&pwd=' + filters.logpwd).then(handleSuccess, handleError('Error getting drivers'));
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

