'use strict';

angular.module('app')
    .controller('registrationCtrl' , ['$scope','$rootScope','RegistrationModel',
 function($scope,$rootScope,RegistrationModel){

   $scope.username = null;
   $scope.password = null;
   $scope.email = null;
   $scope.gender = null;
   $scope.register = function(){

        var onSuccess = function(){
            alert("Registration successfully");
        };
        var onFail = function(){
            alert("Registration Failed");
        };
        var jsonPayLoad = {
            username: $scope.username,
            password:$scope.password,
            email: $scope.email,
            gender: $scope.gender
        };
        RegistrationModel.createNewUser(jsonPayLoad,onSuccess,onFail);

   };

 }]);
