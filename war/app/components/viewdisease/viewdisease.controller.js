app = angular.module('hplus.modules.viewdisease');

  app.controller('ViewDiseaseController',
    function($scope, globalFactory){

      $scope.go = function(path){
        globalFactory.go(path);
      };
      $scope.recordList = [
           {
             name: "Doe, Jane",
             date: "Feb. 20, 2016",
             disease: "Heart Attack",
             id: 1
           },
           {
             name: "Rizal, Jose",
             date: "Apr. 10, 2013",
             disease: "Heart Attack",
             id: 2
           },
           {
             name: "Lietzkirg, Jaiyra",
             date: "May. 19, 2011",
             disease: "Heart Attack",
             id: 3
           },
           {
             name: "Doe, Jane",
             date: "Feb. 20, 2016",
             disease: "Heart Attack",
             id: 4
           },
           {
             name: "Doe, Jane",
             date: "Feb. 20, 2016",
             disease: "Heart Attack",
             id: 5
           },
           {
             name: "Doe, Jane",
             date: "Feb. 20, 2016",
             disease: "Heart Attack",
             id: 6
           },
           {
             name: "Doe, Jane",
             date: "Feb. 20, 2016",
             disease: "Heart Attack",
             id: 7
           },
           {
             name: "Doe, Jane",
             date: "Feb. 20, 2016",
             disease: "Heart Attack",
             id: 8
           },
           {
             name: "Doe, Jane",
             date: "Feb. 20, 2016",
             disease: "Heart Attack",
             id: 9
           },
           {
             name: "Doe, Jane",
             date: "Feb. 20, 2016",
             disease: "Heart Attack",
             id: 10
           },
           {
             name: "Doe, Jane",
             date: "Feb. 20, 2016",
             disease: "Heart Attack",
             id: 11
           }
         ];
    });