'use strict';
/* Define a Service for Example Model using resource object*/

var app = angular.module('app');

app.service('PostService', ['$resource',
  function ($resource) {
    return $resource('posts/:action',{},{
      update:{
        method: "PUT"
      }
    });
  }]);

