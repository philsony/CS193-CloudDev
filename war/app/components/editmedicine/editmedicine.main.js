var editmedicine = require('html-loader!./editmedicine.html');

angular.module('hplus.modules.editmedicine', [])

  .config(function ($routeProvider){  
    $routeProvider      
      .when('/admin/update/medicine',{
        template: editmedicine
      })
  });

  require('./editmedicine.controller.js');