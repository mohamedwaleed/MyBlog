'use strict';


angular.module('app')
    .controller('loginCtrl',['$scope','AuthService',function($scope,AuthService){
        $scope.user = {
            username: undefined,
            password: undefined,
            remember: false
        };
        $scope.login = function(){

            AuthService.login($scope.user);

        };

    }]);
