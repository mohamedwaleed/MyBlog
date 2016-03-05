/**
 * Created by mohamed on 3/1/16.
 */
'use strict';
angular.module('app')
    .controller('editPostsCtrl',['$rootScope','$scope','$stateParams','PostModel',function($rootScope,$scope,$stateParams,PostModel){

        $scope.tinymceOptions = {
            theme: 'modern',
            plugins: [
                'advlist autolink lists link image charmap print preview hr anchor pagebreak',
                'searchreplace wordcount visualblocks visualchars code fullscreen',
                'insertdatetime media nonbreaking save table contextmenu directionality',
                'emoticons template paste textcolor'
            ],
            toolbar1: 'insertfile undo redo | styleselect | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | link image',
            toolbar2: 'print preview media | forecolor backcolor emoticons',
            height: '295px',
            width: '700px',
            resize : false
        };
        $scope.post = $stateParams.post;
        $scope.editPost = function(){
            var onSuccess = function(){
                alert("Post edited succesfully");
            };
            var onFail = function(response){
                alert("Error in editing the post");
            };
            PostModel.editPost($scope.post,onSuccess,onFail);
        };
    }]);
