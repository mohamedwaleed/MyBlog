'use strict';


var app = angular.module('app');


var Session = function($window , $rootScope , $cookieStore ,ROLE){
    this.roles = [ROLE.guest] ;
    this.save = function(loggedInUser,rememberMe){
        this.roles = [ROLE.guest] ;
        for(var i = 0 ; i < loggedInUser.authorities.length ; i ++){
            this.roles.push(loggedInUser.authorities[i].authority);
        }
        $rootScope.loggedInUser = loggedInUser;
        $window.sessionStorage['loggedInUser'] = JSON.stringify(loggedInUser);
        if(rememberMe === true){
              // Find tomorrow's date.
              var expireDate = new Date();
              expireDate.setDate(expireDate.getDate() + 1);
              $cookieStore.put('loggedInUser' , loggedInUser,{'expires':expireDate});
        }
    };
    this.destroy = function(){
        this.roles = [ROLE.guest] ;
        $rootScope.loggedInUser = null;
        $window.sessionStorage['loggedInUser'] = null;
        if($cookieStore.get('loggedInUser') !== null){
            $cookieStore.remove('loggedInUser');
        }
    };
    return this;
};

app.factory('Session' , ['$window','$rootScope','$cookieStore','ROLE',function($window,$rootScope,$cookieStore,ROLE){
    return new Session($window ,$rootScope,$cookieStore,ROLE);
}]);
