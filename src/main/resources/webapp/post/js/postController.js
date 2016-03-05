'use strict';

var app = angular.module('app');

app.controller('postCtrl' , ['$scope','$rootScope','$stateParams','PostModel','CommentModel',
    function($scope,$rootScope,$stateParams,PostModel,CommentModel){

        var postId = $stateParams.postId;
        $scope.post = PostModel.getPost(postId);
        $scope.authorName = "";
        $scope.commentContent = "";
        $scope.loading = false;
        $scope.addComment = function(){
            $scope.loading = true;
            $scope.commentSubmisionError = false;
            var commentInfo = {
                date: new Date(),
                content: $scope.commentContent,
                author: $scope.authorName
            };
            var onSucces = function(commentList){
                $scope.post.commentList = commentList;
                $scope.loading = false;
            };
            var onFail = function(){
                $scope.commentSubmisionError = true;
                $scope.loading = false;
            };
            CommentModel.addComment(commentInfo,postId,onSucces,onFail);
        };
        $scope.deleteComment = function(commentId,commentIndex){
            var onSucces = function(){
                $scope.post.commentList.splice(commentIndex,1);
                alert("Comment deleted successfully")
            };
            var onFail = function(){
                alert("Error in deleting comment")
            };
            CommentModel.deleteComment(postId,commentId,onSucces,onFail);
        };
    }]);
