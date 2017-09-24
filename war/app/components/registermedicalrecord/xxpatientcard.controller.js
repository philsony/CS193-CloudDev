angular.module('hplus.modules.registermedicalrecord')

  .controller('PatientCardController',
    function($scope, $location, globalFactory, doctorFactory){
      
      var user = doctorFactory.getUser();
      
      $scope.isAdmin = function(){
        return user.isAdmin;
      };

      $scope.go = function(path){
        globalFactory.go(path);
      };

      $scope.delete = function(doctor){
        doctorFactory.deleteDoctor(doctor);
      };
    }
  );