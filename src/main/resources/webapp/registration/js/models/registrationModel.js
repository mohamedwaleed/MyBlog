'use strict';

function RegistrationModel(RegistrationService) {
    this.createNewUser = function(user,onSuccess,onFail){
        var promise = RegistrationService.save(user).$promise;

         promise.then(function(response){
            if(onSuccess) {
                onSuccess();
            }
          },function(response){
            if(onFail) {
                onFail();
            }
          });
    };
}


var app = angular.module('app');
app.factory('RegistrationModel', ['RegistrationService',
    function(RegistrationService) {
        var registrationModel= new RegistrationModel(RegistrationService);
        return registrationModel;
    }]);




