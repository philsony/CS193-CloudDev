var explorediseases = require('html-loader!./explorediseases.html');

angular.module('hplus.modules.explorediseases', [])

  .config(function ($routeProvider){  
    $routeProvider      
      .when('/admin/list/disease',{
        template: explorediseases
      })
  });
  require('./explorediseases.directive.js');
  require('./explorediseases.controller.js');
  require('./xxcard.controller.js');