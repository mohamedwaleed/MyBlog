'use strict';


angular.module('app')
    .controller('contactCtrl',['$scope','$uibModalInstance','ContactModel',function($scope,$uibModalInstance,ContactModel){

          $scope.ok = function () {
            var jsonPayLoad = {
                name: $scope.name,
                email: $scope.email,
                message: $scope.message
            };
            var onSuccess = function(){
                alert("Sent successfully");
            };
            var onFail = function(){
                alert("Sent failed");
            };
            ContactModel.sendEmail(jsonPayLoad,onSuccess,onFail);
            $uibModalInstance.close();
          };

          $scope.cancel = function () {
            $uibModalInstance.dismiss('cancel');
          };
    }]);
