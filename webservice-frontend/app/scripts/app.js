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
    'ngTouch'
  ])
  .config(function($routeSegmentProvider, $routeProvider) {
    $routeSegmentProvider.options.autoLoadTemplates = true;
	$routeSegmentProvider
      .when('/main', 'main')
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
		default: true, 
		templateUrl: 'views/main.html',
        controller: 'MainCtrl'  
	  })
	  .segment('about', {
		templateUrl: 'views/about.html',
        controller: 'AboutCtrl'  
	  })
	  .segment('analysis', {
		templateUrl: 'views/analysis.html'  
	  })
	  .segment('database', {
		templateUrl: 'views/database.html'  
	  })
	  .segment('request', {
		templateUrl: 'views/request.html'  
	  })
	  .within()
		.segment('users', {
			templateUrl: 'views/users.html'
		})
		.segment('relationships', {
			templateUrl: 'views/relationships.html'
		})
		.segment('media', {
			templateUrl: 'views/media.html'
		})
		.segment('comments', {
			templateUrl: 'views/comments.html'
		})
		.segment('likes', {
			templateUrl: 'views/likes.html'
		})
		.segment('tags', {
			templateUrl: 'views/tags.html'
		})
		.segment('locations', {
			templateUrl: 'views/locations.html'
		})
		.up()
	  .segment('token', {
		templateUrl: 'views/token.html'  
	  });
	  $routeProvider.otherwise({redirectTo: '/main'});
  });
