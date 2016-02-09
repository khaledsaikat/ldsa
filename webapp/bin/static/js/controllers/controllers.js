'use strict';

var stalkerControllers = angular.module('stalkerControllers', []);

stalkerControllers.controller('TokenCtrl', ['$scope', '$http', function ($scope, $http) {
    /*$http.get('/api/token').success(function (data) { 
        $scope.tokens = data; 
    });*/
    $scope.tokens = [
        {
            'imgUri': 'http://placehold.it/300x300',
            'username': 'Test User 1',
            'accessToken': '123456789'
        },
        {
            'imgUri': 'http://placehold.it/100x100',
            'username': 'Test User 2',
            'accessToken': '987654321'
        }
    ];
}]);