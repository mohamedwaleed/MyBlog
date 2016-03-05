/**
 * Created by mohamed on 3/1/16.
 */
'use strict';
angular.module('app')
    .controller('managePostsCtrl',['$rootScope','$scope','$state','PostModel',function($rootScope,$scope,$state,PostModel){

        $scope.currentPage = 1;
        //define a function which is reasonable for changing examples list data based on page number.
        $scope.pageChanged = function (pageNumber) {
            console.log("page number = " + pageNumber);
            $scope.page = PostModel.listPosts(pageNumber - 1);
        };
        $scope.pageChanged(1);


        $scope.deletePost = function(postId){
            var onSuccess = function(){
                alert("Post deleted succesfully");
            };
            var onFail = function(response){
                alert("Error in deleting the post with id = " + postId);
            };
            var r = confirm("Do you want to delete this post ?");
            if (r == true) {
                PostModel.deletePost(postId,onSuccess,onFail);
                for(var i = 0; i < $scope.page.pageContent.length ; i++){
                    if($scope.page.pageContent[i].id === postId){
                        $scope.page.pageContent.splice(i,1);
                        break;
                    }
                }
            }
        };
    }]);
