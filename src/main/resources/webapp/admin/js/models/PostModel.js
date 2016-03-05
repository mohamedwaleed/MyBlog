'use strict';

/* Model Class for apply model logic and call service APIs*/
function PostModel(PostService,$sce) {

    this.page = {};
    this.topPosts = [];
    this.allPosts = [];
    this.post = {};
    var postModelThis = this;
    this.createPost = function(postInfo,onSucces,onFail){
        var jsonPayload = {
            title: postInfo.title,
            date: postInfo.date,
            content: postInfo.content,
            author: postInfo.author,
            image: postInfo.image
        };
        var promise = PostService.save({action:''},jsonPayload).$promise;

        promise.then(function(response){
            if(onSucces) {
                onSucces();
            }
        },function(response){
            if(onFail) {
                onFail();
            }
        });
    };

    this.listPosts = function(pageNum){
        var pageSize = 10;
        var promise = PostService.get({pageNum:pageNum,pageSize:pageSize}).$promise;
        promise.then(function(pagedResult){
            console.log(pagedResult.result);
            angular.extend(postModelThis.page, pagedResult.result);
        },function(pagedResult){
            console.log("error");
        });
        return postModelThis.page;
    };
    this.listAllPosts = function(groupByFunction){
        var promise = PostService.query({action:'list'}).$promise;
        promise.then(function(result){
            for(var i = 0 ; i < result.length ;i++){
                result[i].content = $sce.trustAsHtml(result[i].content);
            }
            angular.extend(postModelThis.allPosts, result);
            if(groupByFunction){
                groupByFunction(postModelThis.allPosts)
            }
        },function(pagedResult){
            console.log("error");
        });
    };
    this.deletePost = function(postId,onSucces,onFail){
        var promise = PostService.delete({action:postId}).$promise;
        var result = false;
        promise.then(function(response){
            if(onSucces) {
                onSucces();
            }
        },function(response){
            if(onFail) {
                onFail();
            }
        });
    };
    this.editPost = function(post,onSucces,onFail){
        var jsonPayload = post;
        var promise = PostService.update({action:post.id},jsonPayload).$promise;
        var result = false;
        promise.then(function(response){
            if(onSucces) {
                onSucces();
            }
        },function(response){
            if(onFail) {
                onFail();
            }
        });
    };
    this.getPost = function(postId){
        var promise = PostService.get({action:postId}).$promise;
        promise.then(function(result){
            result.content = $sce.trustAsHtml(result.content);
            angular.extend(postModelThis.post, result);
            console.log(result);
        },function(pagedResult){
            console.log("error");
        });
        return postModelThis.post;
    };
    this.getTop3Posts = function(){
        var promise = PostService.query({action:'list3'}).$promise;
        promise.then(function(result){
            for(var i = 0 ; i < result.length ;i++){
                result[i].content = $sce.trustAsHtml(result[i].content);
            }
            angular.extend(postModelThis.topPosts,result);
        },function(result){

        });
        return postModelThis.topPosts;
    };

}


var app = angular.module('app');
app.factory('PostModel', ['PostService','$sce',
    function(PostService,$sce) {
        var postModel= new PostModel(PostService,$sce);
        return postModel;
    }]);




