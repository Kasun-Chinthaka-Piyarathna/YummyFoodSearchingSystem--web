

(function () {
    'use strict';

    angular
        .module('yummy')
        .controller('orderController', orderController);






    orderController.$inject = ['$scope', 'orderService' ,'$state' , '$mdDialog' , '$rootScope'];

    function orderController($scope , orderService , $state,$mdDialog,  $rootScope ) {


      if($rootScope.allItems != undefined && $rootScope.allItems.length !=0) {
          var price = 0 ;
          $.each($rootScope.allItems, function (i, item) {
              price = price + Number(item.Unit_Price) * Number(item.selectedQuantity) ;
          });
          $scope.totalPrice = price ;



          var resta = localStorage.getItem("restaurant");
          $rootScope.savedRestaurant = JSON.parse(resta);
          $scope.restaurant = $rootScope.savedRestaurant;



      }else {


          var cart = localStorage.getItem("CARTITEM");
          $rootScope.allItems = JSON.parse(cart);
          $scope.allItems = $rootScope.allItems;


          var user = localStorage.getItem("userName");
          $rootScope.user = JSON.parse(user);
          $scope.user = $rootScope.user;




          var resta = localStorage.getItem("restaurant");
          $rootScope.savedRestaurant = JSON.parse(resta);
          $scope.restaurant = $rootScope.savedRestaurant;
      }




        orderService.sendCartDetails($rootScope.allItems,$rootScope.savedRestaurant,$rootScope.user).then(function (data) {

            $scope.cartId = data ;




        });


        $scope.date = new Date();



             $scope.showConfirm = function (ev) {
                 // Appending dialog to document.body to cover sidenav in docs app
                 var confirm = $mdDialog.confirm()
                     .title('Would you like to deliver now')
                     .textContent('Your personal details and order details will be sent to the Restaurants.')
                     .ariaLabel('Lucky day')
                     .targetEvent(ev)
                     .ok('Please do it!')
                     .cancel('Cancel the Order');

                 $mdDialog.show(confirm).then(function () {

                     var order = {
                         items : $rootScope.allItems,
                         restaurent : $rootScope.savedRestaurant,
                         customer : $rootScope.user
                     };


                     $scope.filters = {

                         rid : $rootScope.savedRestaurant.Restaurant_ID,
                         cid : $rootScope.user.Customer_ID,
                         contact : $rootScope.user.Contact_Number,
                         email : $rootScope.user.Email,
                         cart_id :$scope.cartId,
                         totalPrice : $scope.totalPrice,
                         date :  $scope.date,
                         currentlocation :  $rootScope.currentlocation,
                         deliveryCost : $rootScope.calculatedDistance * 30



                     };


                     orderService.sendOrder(order).then(function () {





                      $scope.SendOrderDetails = function () {
                          orderService.SendOrderDetailsToDatabase($scope.filters).then(function success() {
                              $mdDialog.hide();
                              // cpfLoadingBar.complete();
                          });
                      };




                  });
                 }, function () {
                     $scope.status = 'You decided to keep your debt.';
                 });
             };





        // end

        $scope.imagePath22 = 'images/footers.jpg';








        $scope.querySearch = function (query) {
            orderService.searchRestaurantsByName(query).then(function (data) {
                $scope.item=data;

            });



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

