'use strict';

angular.module('formAutofill', []);

angular.module('formAutofill').directive('formAutofillFix', function() {
  return function(scope, elem, attrs) {
    // Fix autofill issues where Angular doesn't know about autofilled inputs
    if(attrs.ngSubmit) {
        scope.$on('triggerRefresh', function (event, data) {
             elem.find('input, textarea, select').trigger('input').trigger('change').trigger('keydown');
        });
    }
  };
});