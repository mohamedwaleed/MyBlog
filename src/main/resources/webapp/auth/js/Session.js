'use strict';


var app = angular.module('app');


var Session = function($window , $rootScope , $cookies ,ROLE){
    this.roles = [ROLE.guest] ;
    this.save = function(loggedInUser,rememberMe){
        this.roles = [ROLE.guest] ;
        $rootScope.isAdmin = false;
        for(var i = 0 ; i < loggedInUser.authorities.length ; i ++){
            this.roles.push(loggedInUser.authorities[i].authority);
            if(loggedInUser.authorities[i].authority === "ROLE_ADMIN"){
                $rootScope.isAdmin = true;
            }
        }

        $rootScope.loggedInUser = loggedInUser;
        $window.sessionStorage['loggedInUser'] = JSON.stringify(loggedInUser);
        if(rememberMe === true){
              // Find tomorrow's date.
              var expireDate = new Date();
              expireDate.setDate(expireDate.getDate() + 1);
              $cookies.put('loggedInUser' , JSON.stringify(loggedInUser),{'expires':expireDate});
        }
    };
    this.destroy = function(){
        this.roles = [ROLE.guest] ;
        $rootScope.loggedInUser = null;
        $window.sessionStorage['loggedInUser'] = null;
        if($cookies.get('loggedInUser') !== null){
            $cookies.remove('loggedInUser');
        }
    };
    return this;
};

app.factory('Session' , ['$window','$rootScope','$cookies','ROLE',function($window,$rootScope,$cookies,ROLE){
    return new Session($window ,$rootScope,$cookies,ROLE);
}]);
