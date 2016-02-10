'use strict';

var stalkerControllers = angular.module('stalkerControllers', []);

stalkerControllers.controller('TokenCtrl', ['$scope', '$http', function ($scope, $http) {
   $http.get('/getTokens').success(function (data) { 
        $scope.tokens = data; 
    });

}]);