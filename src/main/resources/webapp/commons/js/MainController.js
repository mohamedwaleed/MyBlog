'use strict';

var app = angular.module('app');

app.controller('MainController' , ['$scope','$rootScope','AuthService','$state','$window','PostModel',
 function($scope,$rootScope,AuthService,$state,$window,PostModel){

      $scope.logout = function(){
         AuthService.logout();
      };
     $scope.postsByYears = {};

     var groupByYear = function(posts){

         for(var i = 0 ; i < posts.length; i++) {
             var date = new Date(posts[i].date);
             var year = date.getFullYear();
             if($scope.postsByYears[year] === undefined  || $scope.postsByYears[year] === null){
                 $scope.postsByYears[year] = [posts[i]];
             }else {
                 $scope.postsByYears[year].push(posts[i]);
             }
         }
     };
     PostModel.listAllPosts(groupByYear);

 }]);
