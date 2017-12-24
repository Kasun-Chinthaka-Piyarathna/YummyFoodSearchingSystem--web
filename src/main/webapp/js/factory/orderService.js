/**
 * Created by Kasun Chinthaka on 1/25/2017.
 */
(function () {
    'use strict';

    angular
        .module('yummy')
        .factory('orderService', orderService)
        .config(function ($mdThemingProvider) {
            $mdThemingProvider.theme('dark-grey').backgroundPalette('grey').dark();
            $mdThemingProvider.theme('dark-orange').backgroundPalette('orange').dark();
            $mdThemingProvider.theme('dark-purple').backgroundPalette('deep-purple').dark();
            $mdThemingProvider.theme('dark-blue').backgroundPalette('blue').dark();
        });

    orderService.$inject = ['$http'];

    function orderService($http) {

        var webApi = "http://localhost:8080/rest/";


        var service = {
            SendOrderDetailsToDatabase : SendOrderDetailsToDatabase,
            sendCartDetails : sendCartDetails
        };

        return service;

        function SendOrderDetailsToDatabase(filters) {
            return $http.get(webApi + 'foodservice/order?rid='+filters.rid + '&cid=' + filters.cid + + '&dc=' + filters.allitems + + '&op=' + filters.totalPrice + + '&cartid=' + filters.email ).then(handleSuccess, handleError('Error getting single driver'));
        }


        function sendCartDetails( allItems,savedRestaurant,user) {
            return $http.get(webApi + 'foodservice/shoppingcart?rid='+savedRestaurant.Restaurant_ID + '&cid=' + user.Customer_ID +  '&item=' + allItems).then(handleSuccess, handleError('Error getting single driver'));
        }

        // function sendOrder(data) {
        //     return $http.get(webApi + 'foodservice/sendemail?to=' + filters.towhome + '&from=' + filters.fromwhome + '&subject=' + filters.subject + '&html=' + filters.htmlbody).then(handleSuccess, handleError('Error getting drivers'));
        //     return $http.get(webApi + 'foodservice/order?rid=' +restaurant.Restaurant_ID + '&cid=' +customer.CustomerID +'&date=' +customer.date+ '&time' +customer.time+ '');
        // }
        //


        // $rootScope.user


        // UNDONE: Duplicate method on all services
        function handleSuccess(res) {
            return res.data;
        }

        function handleError(error) {
            return function () {
                return {success: false, message: error};
            };
        }
    }


})();

