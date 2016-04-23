'use strict';
/*
 main app module, each new module must include as a child for this module.
 */

// Declare app level module which depends on filters, and services
var app = angular.module('app', [
        'ngCookies',
        'ui.router',
        'uiRouterStyles',
        'ui.tinymce',
        'ngResource',
        'angularUtils.directives.dirPagination',
        'ngSanitize',
        'hm.readmore',
        'ngAnimate',
        'ui.bootstrap'
]).run(
    ['$rootScope', '$state', '$stateParams', '$location', '$window','$cookies','AuthService', 'Session','$log','$http',
        function ($rootScope, $state, $stateParams, $location, $window,$cookies,AuthService,Session,$log,$http) {
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
                            console.log(toState.name.substr(0,10));
                            console.log($rootScope.isAdminPanelAuthenticated);
                           if(toState.name === 'login'){
                               $state.go('app.dashboard');
                           }else if(toState.name.substr(0,10) === 'adminPanel' && ($rootScope.isAdminPanelAuthenticated == undefined || $rootScope.isAdminPanelAuthenticated === false) ){
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
                   templateUrl: 'dashboard/html/dashboard.html',
                   controller: 'mainDashboardCtrl',
                   authorizedRoles: [ROLE.admin,ROLE.user,ROLE.guest]
                }).state('adminPanel', {
                   url: '/adminPanel',
                   templateUrl: 'admin/html/admin-panel.html',
                   controller: 'adminPanelCtrl',
                   authorizedRoles: [ROLE.admin],
                   data: {
                       css: ['admin/css/sb-admin.css', 'admin/css/plugins/morris.css', 'admin/font-awesome/css/font-awesome.min.css']
                   }
                }).state('adminPanel.addPost', {
                url: '/add-post',
                templateUrl: 'admin/html/addPost.html',
                controller: 'addPostCtrl',
                authorizedRoles: [ROLE.admin]
                 }).state('adminPanel.dashboard', {
                        url: '/dashboard',
                        templateUrl: 'admin/html/dashboard.html',
                        controller: 'dashboardCtrl',
                        authorizedRoles: [ROLE.admin]
               }).state('adminPanel.managePosts', {
                        url: '/manage-posts',
                        templateUrl: 'admin/html/managePosts.html',
                        controller: 'managePostsCtrl',
                        authorizedRoles: [ROLE.admin]
              }).state('adminPanel.editPost', {
                        url: '/edit-post',
                        templateUrl: 'admin/html/editPost.html',
                        controller: 'editPostsCtrl',
                        params: {
                          post: null
                        },
                        authorizedRoles: [ROLE.admin]
              }).state('app.post', {
                        url: 'post/:postId',
                        templateUrl: 'post/html/post.html',
                        controller: 'postCtrl',
                        authorizedRoles: [ROLE.admin,ROLE.user,ROLE.guest]
              }).state('app.registration', {
                        url: 'registration',
                        templateUrl: 'registration/html/registration.html',
                        controller: 'registrationCtrl',
                        authorizedRoles: [ROLE.admin,ROLE.user,ROLE.guest]
              }).state('app.about', {
                        url: 'about',
                        templateUrl: 'about/html/about.html',
                        controller: 'aboutCtrl',
                        authorizedRoles: [ROLE.admin,ROLE.user,ROLE.guest]
              });



        }
    ]
);

