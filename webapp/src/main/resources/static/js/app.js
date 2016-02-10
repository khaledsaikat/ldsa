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
	'route-segment',
	'view-segment',
    'ngSanitize',
    'ngTouch',
    'stalkerControllers'
  ])
  .config(function($routeSegmentProvider, $routeProvider, $locationProvider) {
    $locationProvider.html5Mode(true);
    $routeSegmentProvider.options.autoLoadTemplates = true;
	$routeSegmentProvider
      .when('/main', 'main')
	  .when('/login', 'login')
      .when('/about', 'about')
	  .when('/analysis', 'analysis')
	  .when('/database', 'database')
	  .when('/request', 'request')
	  .when('/request/users', 'request.users')
	  .when('/request/relationships', 'request.relationships')
	  .when('/request/media', 'request.media')
	  .when('/request/comments', 'request.comments')
	  .when('/request/likes', 'request.likes')
	  .when('/request/tags', 'request.tags')
	  .when('/request/locations', 'request.locations')
	  .when('/token', 'token')
	  .segment('main', { 
		templateUrl: 'partials/main.html'
	  })
	  .segment('login', {
		default: true,
		templateUrl: 'partials/login.html'  
	  })
	  .segment('about', {
		templateUrl: 'partials/about.html'  
	  })
	  .segment('analysis', {
		templateUrl: 'partials/analysis.html'  
	  })
	  .segment('database', {
		templateUrl: 'partials/database.html'  
	  })
	  .segment('request', {
		templateUrl: 'partials/request.html'  
	  })
	  .within()
		.segment('users', {
			default: true,
			templateUrl: 'partials/request/users.html',
            controller: 'RequestUserCtrl'
		})
		.segment('relationships', {
			templateUrl: 'partials/request/relationships.html'
		})
		.segment('media', {
			templateUrl: 'partials/request/media.html'
		})
		.segment('comments', {
			templateUrl: 'partials/request/comments.html'
		})
		.segment('likes', {
			templateUrl: 'partials/request/likes.html'
		})
		.segment('tags', {
			templateUrl: 'partials/request/tags.html'
		})
		.segment('locations', {
			templateUrl: 'partials/request/locations.html'
		})
		.up()
	  .segment('token', {
		templateUrl: 'partials/token.html',
        controller: 'TokenCtrl'
	  });
	  $routeProvider.otherwise({redirectTo: '/main'});
  });
