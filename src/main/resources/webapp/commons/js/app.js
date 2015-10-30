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
]).run(
    ['$rootScope', '$state', '$stateParams', '$location', '$window','$cookieStore','AuthService', 'Session',
        function ($rootScope, $state, $stateParams, $location, $window,$cookieStore,AuthService,Session) {
            $rootScope.$on('$stateChangeStart',
                function (event, toState, toParams, fromState, fromParams) {

                     if(( ($cookieStore.get('loggedInUser') !== undefined && $cookieStore.get('loggedInUser') !== null)
                         ||
                        ($window.sessionStorage["loggedInUser"] !== "null"
                        && $window.sessionStorage["loggedInUser"] !== undefined) ) ){

                         $rootScope.loggedInUser = $cookieStore.get('loggedInUser') || JSON.parse($window.sessionStorage["loggedInUser"]);

                         for(var i = 0 ; i < $rootScope.loggedInUser.authorities.length ; i ++){
                             Session.roles.push($rootScope.loggedInUser.authorities[i].authority);
                         }
                     }

                       if(AuthService.isAuthorized(toState.authorizedRoles)){
                            if(AuthService.isAuthenticated()){
                               if(toState.name === 'login'){
                                   $state.go('app.dashboard');
                               }
                            }
                        }else{
                                $state.go('app.dashboard');
                        }

                });
        }
    ]
).config(
    ['$stateProvider', '$urlRouterProvider', '$controllerProvider', '$compileProvider', '$filterProvider', '$provide','ROLE',
        function ($stateProvider, $urlRouterProvider, $controllerProvider, $compileProvider, $filterProvider, $provide,ROLE) {
                $urlRouterProvider.otherwise('/dashboard');
                $stateProvider
                .state('app', {
                    abstract: true,
                    url: '/',
                    templateUrl: 'main.html',
                    controller: 'MainController'
                }).state('login', {
                   url: '/login',
                   templateUrl: 'auth/html/login.html',
                   controller: 'loginCtrl',
                   authorizedRoles: [ROLE.admin,ROLE.user,ROLE.guest]
                }).state('app.dashboard', {
                   url: 'dashboard',
                   templateUrl: 'dashboard.html',
                   authorizedRoles: [ROLE.admin,ROLE.user,ROLE.guest]
                });

        }
    ]
);

