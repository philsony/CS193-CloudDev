angular.module('hplus.modules.modal')

  .controller('ModalController',
    function($scope, $rootScope, globalFactory, modalFactory, doctorFactory){

      /*
      Two types of modals:  "notify" -- Alerts the user of an event
                            "confirm" -- Asks the user for confirmation.
      */
      $scope.modalContents = {}

      $scope.close = function(){
        $scope.modalContents = {};
      };

      $scope.ret = function(state){
        if($scope.modalContents.data != null){
          if(state) {
            $scope.modalContents.data($scope.modalContents.object, $scope.modalContents.clean);
          }
        }
      };

      $scope.$on('modalReceived', function(event, modalContents) {
        $scope.modalContents = modalContents;
      });

      $scope.isType = function(type){
        if (typeof(type) == "string" && type == $scope.modalContents.type){
          return true;
        } else {
          return false;
        }
      }

      $scope.go = function(path){
        globalFactory.go(path);
      };
    }
  );