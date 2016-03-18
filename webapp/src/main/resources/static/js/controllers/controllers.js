'use strict';

var stalkerControllers = angular.module('stalkerControllers', []);

stalkerControllers.controller('TokenCtrl', ['$scope', '$http', function ($scope, $http) {
   $http.get('/api/getTokens').success(function (data) { 
        $scope.tokens = data; 
    });
}]);

stalkerControllers.controller('RequestUserCtrl', ['$scope', '$http', function ($scope, $http) {
    $scope.tokens = [
        {
            'id': '1',
            'userName': 'User 1'
        },
        {
            'id': '2',
            'userName': 'User 2'
        }
    ];
}]);