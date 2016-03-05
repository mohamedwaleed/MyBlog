'use strict';


angular.module('app')
    .controller('addPostCtrl',['$rootScope','$scope','$state','$http','PostModel',function($rootScope,$scope,$state,$http,PostModel){

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
        $scope.title = "";
        $scope.content = "";
        $scope.createPost = function(){
            var onSuccess = function(){
                alert("Post created succesfully");
            };
            var onFail = function(response){
                alert("Error in creating the post");
            };
            var result =  PostModel.createPost({
                title: $scope.title,
                date: new Date(),
                content: $scope.content,
                author: "Mohamed Waleed",
                image: ""
            },onSuccess,onFail);

        };
    }]);
