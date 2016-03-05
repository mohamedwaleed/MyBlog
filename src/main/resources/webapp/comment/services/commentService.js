'use strict';
/* Define a Service for Example Model using resource object*/

var app = angular.module('app');

app.service('CommentService', ['$resource',
    function ($resource) {
        return $resource('posts/:postId/comment/:commentId',{},{
            update:{
                method: "PUT"
            },
            saveAndReturnArray:{
                method: "POST",
                isArray:true
            }

        });
    }]);

