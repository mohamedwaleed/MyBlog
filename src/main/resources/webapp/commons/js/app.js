'use strict';
/*
 main app module, each new module must include as a child for this module.
 */

// Declare app level module which depends on filters, and services
var app = angular.module('app', [
        'ngCookies',
        'ui.router',
        'uiRouterStyles'
]).run(
    ['$rootScope', '$state', '$stateParams', '$location', '$window','$cookies','AuthService', 'Session','$log',
        function ($rootScope, $state, $stateParams, $location, $window,$cookies,AuthService,Session,$log) {
            $rootScope.$on('$stateChangeStart',
                function (event, toState, toParams, fromState, fromParams) {

                     if(( ($cookies.getObject('loggedInUser') !== undefined && $cookies.getObject('loggedInUser') !== null)
                         ||
                        ($window.sessionStorage["loggedInUser"] !== "null"
                        && $window.sessionStorage["loggedInUser"] !== undefined) ) ){

                             $rootScope.loggedInUser = $cookies.getObject('loggedInUser') || JSON.parse($window.sessionStorage["loggedInUser"]);
                            if(Session.roles.length == 1) {
                                // if we did not have any role
                                for (var i = 0; i < $rootScope.loggedInUser.authorities.length; i++) {
                                    Session.roles.push($rootScope.loggedInUser.authorities[i].authority);
                                    if ($rootScope.loggedInUser.authorities[i].authority === "ROLE_ADMIN") {
                                        $rootScope.isAdmin = true;
                                    }
                                }
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
    ['$stateProvider', '$urlRouterProvider','ROLE',
        function ($stateProvider, $urlRouterProvider,ROLE) {
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
                }).state('adminPanel', {
                   url: '/adminPanel',
                   templateUrl: 'admin/html/admin-panel.html',
                   authorizedRoles: [ROLE.admin],
                   data: {
                       css: ['admin/css/sb-admin.css', 'admin/css/plugins/morris.css', 'admin/font-awesome/css/font-awesome.min.css']
                   }
                });

        }
    ]
);

