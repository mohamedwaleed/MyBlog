'use strict';

function ContactModel(MailService) {
    this.sendEmail = function(emailInfo,onSuccess,onFail){
        var promise = MailService.sendEmail(emailInfo).$promise;
        promise.then(function(){
            if(onSuccess)
                onSuccess();
        },function(){
            if(onFail){
                onFail();
            }
        });
    };
};


var app = angular.module('app');
app.factory('ContactModel', ['MailService',
    function(MailService) {
        var contactModel= new ContactModel(MailService);
        return contactModel;
    }]);




