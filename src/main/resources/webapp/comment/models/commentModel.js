'use strict';

/* Model Class for apply model logic and call service APIs*/
function CommentModel(CommentService) {
    this.addComment = function(commentInfo,postId,onSucces,onFail){
        var jsonPayload = {
            date: commentInfo.date,
            content: commentInfo.content,
            author: commentInfo.author
        };

        var promise = CommentService.saveAndReturnArray({postId:postId},jsonPayload).$promise;

        promise.then(function(result){
            if(onSucces) {
                onSucces(result);
            }
        },function(result){
            if(onFail) {
                onFail();
            }
        });
    }
    this.deleteComment = function(postId,commentId,onSucces,onFail){
        var promise = CommentService.delete({postId:postId,commentId:commentId}).$promise;

        promise.then(function(result){
            if(onSucces) {
                onSucces();
            }
        },function(result){
            if(onFail) {
                onFail();
            }
        });
    };
}


var app = angular.module('app');
app.factory('CommentModel', ['CommentService',
    function(CommentService) {
        var commentModel= new CommentModel(CommentService);
        return commentModel;
    }]);




