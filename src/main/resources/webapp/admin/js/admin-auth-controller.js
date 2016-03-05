'use strict';


angular.module('app')
    .controller('admin-auth-controller',['$rootScope','$scope','$state','$http',function($rootScope,$scope,$state,$http){


        $scope.authenticateAdminPanel = function () {
            var username = $scope.user.username;
            var password = $scope.user.password;
            var request = {
                method: 'POST',
                url: 'user/confirmAdminPanelPassword',
                data: {
                    username: username,
                    password: password
                }
            };

            $http(request).then(function(result){
                $rootScope.isAdminPanelAuthenticated = true;
                $state.go('adminPanel.dashboard');
            },function(result){
                $rootScope.isAdminPanelAuthenticated = false;
                $state.go('app.dashboard');
            });
        };


    }]);
