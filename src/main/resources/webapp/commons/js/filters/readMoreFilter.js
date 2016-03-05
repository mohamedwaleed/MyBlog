/**
 * Created by mohamed on 3/4/16.
 */

angular.module('app')
    .filter('readMoreFilter', function() {
        return function(text,limit) {
            var returnText = text;
            console.log(text);
            if(text.length > limit){
                returnText = returnText.substr(0,limit) + ' ...';
            }
            console.log(returnText);

            return returnText;
        };
    })