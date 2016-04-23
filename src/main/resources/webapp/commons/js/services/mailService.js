'use strict';
/* Define a Service for Example Model using resource object*/

var app = angular.module('app');

app.service('MailService', ['$resource',
    function ($resource) {
        return $resource('mail',{},{
            sendEmail:{
                method: "POST"
              }
        });
    }]);

