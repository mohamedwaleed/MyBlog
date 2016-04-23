'use strict';
/* Define a Service for Example Model using resource object*/

var app = angular.module('app');

app.service('RegistrationService', ['$resource',
    function ($resource) {
        return $resource('user',{},{
        });
    }]);

