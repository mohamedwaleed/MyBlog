'use strict';


angular.module('app')
 .factory('AuthService',['$rootScope','$http','$log','$cookieStore','$state','$window','Session',
    function($rootScope,$http,$log,$cookieStore,$state,$window,Session){
        var onAuthSuccess = function(rememberMe){

             var request = {
                 method: 'GET',
                 url: 'user/getLoggedInUser',
                 data: {}
            };

            $http(request).then(function(result){

                var loggedInUser = {};
                angular.extend(loggedInUser,result.data);
                Session.save(loggedInUser,rememberMe);
                $log.log("login success");
                $state.go("app.dashboard");

            },function(result){

            });

        };
        return {

            login: function(user){
                var data ="username=" + user.username +"&password=" + user.password +"&_spring_security_remember_me=" + user.remember +"&submit=Login";
                var request = {
                     method: 'POST',
                     url: 'authentication',
                     headers: {
                        'Content-Type': 'application/x-www-form-urlencoded'
                     },
                     data: data,
                     ignoreAuthModule: 'ignoreAuthModule'
                };
                $http(request).then(function(result){
                    onAuthSuccess(user.remember);
                },function(result){
                    $log.log("login failed");
                });
            },
            logout: function(){
                var request = {
                     method: 'GET',
                     url: '?logout'
                };
                $http(request).then(function(result){
                    $log.log("logout success");
                },function(result){
                    $log.log("logout failed");
                });
                Session.destroy();
            },
            isAuthorized: function(authorizedRoles){
                console.log(authorizedRoles);
                console.log(Session.roles);
                for(var i = 0 ; i < authorizedRoles.length ; i ++){
                    var exist = Session.roles.indexOf(authorizedRoles[i]);
                    if(exist !== -1)
                      return true;
                }
                return false;
            },
            isAuthenticated: function(){

                return ($cookieStore.get('loggedInUser') !== undefined) || ($window.sessionStorage["loggedInUser"] !== null)  || ($rootScope.loggedInUser !== null);
            }

        };

 }]);
