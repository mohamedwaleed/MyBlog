'use strict';


angular.module('app')
 .factory('AuthService',['$rootScope','$http','$log','$cookieStore','$state',
    function($rootScope,$http,$log,$cookieStore,$state){

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
                    var request = {
                         method: 'GET',
                         url: 'user/getLoggedInUser',
                         data: {}
                    };
                    $log.log("login success");
                    $http(request).then(function(result){
                        var loggedInUser = {};
                        angular.extend(loggedInUser,result.result);
                        $cookieStore.put('loggedInUser' , loggedInUser);
                    },function(result){

                    });
                },function(result){
                    $log.log("login failed");
                });
            },
            logout: function(){


            }
        };

 }]);
