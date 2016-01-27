'use strict';

var app = angular.module('app');

app.controller('MainController' , ['$scope','$rootScope','AuthService','$state','$window',
 function($scope,$rootScope,AuthService,$state,$window){

      $scope.logout = function(){
         AuthService.logout();
      };

 }]);
