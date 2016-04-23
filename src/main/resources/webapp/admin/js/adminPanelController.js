'use strict';


angular.module('app')
    .controller('adminPanelCtrl',['$rootScope','$scope','$state','$http',function($rootScope,$scope,$state,$http){

        $scope.logoutFromAdminPanel = function(){
            $rootScope.isAdminPanelAuthenticated = false;
        };

    }]);
