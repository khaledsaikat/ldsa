'use strict';

/**
 * @ngdoc overview
 * @name testXApp
 * @description
 * # testXApp
 *
 * Main module of the application.
 */
angular
  .module('testXApp', [
    'ngAnimate',
    'ngCookies',
    'ngResource',
    'ngRoute',
    'ngSanitize',
    'ngTouch'
  ])
  .config(function ($routeProvider) {
    $routeProvider
      .when('/', {
        templateUrl: 'views/main.html',
        controller: 'MainCtrl',
        controllerAs: 'main'
      })
      .when('/about', {
        templateUrl: 'views/about.html',
        controller: 'AboutCtrl',
        controllerAs: 'about'
      })
	  .when('/analysis', {
		templateUrl: 'views/analysis.html'
	  })
	  .when('/database',{
		templateUrl: 'views/database.html'
	  })
	  .when('/request',{
		templateUrl: 'views/request.html'
	  })
	  .when('/token',{
		templateUrl: 'views/token.html'
	  })
      .otherwise({
        redirectTo: '/'
      });
  });
