var app = angular.module('yummy', [
    'ngRoute',
    'ngMaterial',
    'ui.router',
    'angular-loading-bar',
    'ngMessages',
    'duScroll',
    'google.places',
    'ngMap',
    'ngAnimate',
]);




app.config(function ($stateProvider, $urlRouterProvider, $mdThemingProvider) {

   $urlRouterProvider.otherwise('/home');

    $mdThemingProvider.theme('indigo')
        .primaryPalette('indigo')
        .accentPalette('indigo');


    $stateProvider
        .state('home', {
            templateUrl: 'views/home.html',
            controller: 'homeController',
            url: '/home'
        })
        .state('restaurant', {
            templateUrl: 'views/restaurant.html',
            controller: 'restaurantController',
            url: '/restaurant'
        })
        .state('rating', {
            templateUrl: 'views/rating.html',
            controller: 'ratingController',
            url: '/rating'
        })
        .state('findus', {
            templateUrl: 'views/findus.html',
            controller: 'findusController',
            url: '/findus',
            params: {
                restaurant: undefined
            }
        })
        .state('register', {
            templateUrl: 'views/register.html',
            controller: 'registerController',
            url: '/register'
        })

        .state('order', {
            templateUrl: 'views/order.html',
            controller: 'orderController',
            url: '/order'
        })
        .state('restaurantUpdate', {
            templateUrl: 'views/restaurantUpdate.html',
            controller: 'restaurantUpdateController',
            url: '/restaurantUpdate',
            params: {
                rrr: undefined
            }
        })
        .state('customerRegister',{
            templateUrl:'views/customerRegister.html',
            controller:'customerRegisterController',
            url:'/customerRegister',
            params:{
                rest:undefined
            }
        })


});


app.run(function ($rootScope) {
    $rootScope.signOut = function () {
        $rootScope.loggedIn = false ;
        localStorage.setItem("userName" , null);
    };

    var user = localStorage.getItem("userName");
    var CARTITEM = localStorage.getItem("CARTITEM");



    if(user!= undefined){
        if(user!=null){
            if(user!="null"){

                $rootScope.user = JSON.parse(user);
                $rootScope.loggedIn = true ;
            }
        }
    }




    if(CARTITEM!= undefined){
        if(CARTITEM!=null){
            if(CARTITEM !="null"){

                $rootScope.allItems = JSON.parse(CARTITEM);

            }
        }
    }

});

