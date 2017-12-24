function TestController($scope, $stateParams, $http) {
    $scope.init = function(){
        $http({
            method: 'GET',
            url: '/yummy-food-app/rest/foodservice/getAllRestaurantsTest'
        }).then(function successCallback(response) {
            // this callback will be called asynchronously
            // when the response is available
            $scope.restaurants = response.data;
        }, function errorCallback(response) {
            // called asynchronously if an error occurs
            // or server returns response with an error status.
            Console.log("Error while retrieving restaurants")
        });
    };
    $scope.init();
}