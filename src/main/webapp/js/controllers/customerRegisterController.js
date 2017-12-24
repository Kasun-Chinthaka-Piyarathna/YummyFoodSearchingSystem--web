

(function () {
    'use strict';

    angular
        .module('yummy')
        .controller('customerRegisterController', customerRegisterController);

    customerRegisterController.$inject = ['$scope', 'customerRegisterService' ,'cfpLoadingBar' , '$rootScope','$state'];




    function customerRegisterController($scope , customerRegisterService , cfpLoadingBar , $rootScope ,$state) {




        $scope.SignUp = function () {
            customerRegisterService.customerSignUp($scope.filters).then(function (data) {

                $scope.restuarents = data;

            });
        };

        $scope.SignIn = function () {
            customerRegisterService.customerSignIn($scope.filters).then(function success(result) {

                if(result!="404"){

                    $rootScope.loggedIn = true ;
                    $rootScope.user = result[0] ;
                    localStorage.setItem("userName" , JSON.stringify(result[0]));
                    $state.go("home");

                }
                else{
                    $scope.signinmessage = "User name or password Incorrect";
                }
            } , function err(msg ,err) {
                alert(err);
            });
        };

    }


})();

