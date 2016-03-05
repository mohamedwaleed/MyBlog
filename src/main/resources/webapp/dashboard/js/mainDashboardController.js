'use strict';


angular.module('app')
    .controller('mainDashboardCtrl',['$rootScope','$scope','$state','$http','PostModel',function($rootScope,$scope,$state,$http,PostModel){
        $scope.topPosts = PostModel.getTop3Posts();

    }]);
