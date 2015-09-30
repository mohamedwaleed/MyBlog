'use strict';
/*
 main app module, each new module must include as a child for this module.
 */

// Declare app level module which depends on filters, and services
var app = angular.module('app', [
        'ngAnimate',
        'ngCookies',
        'ngResource',
        'ngStorage',
        'ngSanitize',
        'ui.router',
        'ui.bootstrap',
        'ui.load',
        'ui.jq',
        'ui.validate',
        'oc.lazyLoad',
        'pascalprecht.translate',
        'angularUtils.directives.dirPagination',
        'angular-growl',
        'angularTreeview',
        'angularFileUpload'
])
    .run(
    ['$rootScope', '$state', '$stateParams', '$location', '$window',
        function ($rootScope, $state, $stateParams, $location, $window) {
            $rootScope.$on('$stateChangeStart',
                function (event, toState, toParams, fromState, fromParams) {
                    console.log(toState.name);
//                    event.preventDefault();
//                    $state.go(toState.name);
                });
        }
    ]
)
    .config(
    ['$stateProvider', '$urlRouterProvider', '$controllerProvider', '$compileProvider', '$filterProvider', '$provide',
        function ($stateProvider, $urlRouterProvider, $controllerProvider, $compileProvider, $filterProvider, $provide) {
                $urlRouterProvider.otherwise('/dashboard');
                $stateProvider
                .state('app', {
                    abstract: true,
                    url: '/',
                    templateUrl: 'main.html'
                }).state('login', {
                   url: '/login',
                   templateUrl: 'auth/html/login.html',
                   controller: 'loginCtrl'
                }).state('app.dashboard', {
                   url: 'dashboard',
                   templateUrl: 'dashboard.html'
                });

        }
    ]
);

