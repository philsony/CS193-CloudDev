angular.module('hplus.modules.registerdoctor')

  .controller('RegisterDoctorController',
    function($scope, globalFactory, doctorFactory, modalFactory){

      $scope.contactNoRegex = "\\d{7,}";
      var errorMessage = "";

      $scope.go = function(path){
        globalFactory.go(path);
      };

      $scope.specialization = [
        {
          name: "Cardiology",
          id: 1
        },
        {
          name: "Dentistry",
          id: 2
        },
        {
          name: "Nephrology",
          id: 3
        }
      ];

      $scope.checkStatus = function(status){
        var retType;

        if(status){
          $scope.button = "Register Doctor";
          retType = "edit-button";
        } else {
          $scope.button = "Please fill out all of the fields correctly";
          retType = "delete-button";
        }

        return retType;
      }

      var validity = function(){
        var state;
        var yearDiff;

        if($scope.doctor.birthday.getFullYear() < 1970){
          yearDiff = Date.now() + $scope.doctor.birthday.getTime();
        } else {
          yearDiff = Date.now() - $scope.doctor.birthday.getTime();
        }
        
        yearDiff = new Date(yearDiff);
        yearDiff = parseInt(Math.abs(yearDiff.getUTCDate() - 1970));
        
        errorMessage = "";

        if($scope.doctor.password == $scope.doctor.passwordAgain){
          state = true;
        } else {
          state = false;
          errorMessage += "Passwords don't match!";
        }

        if(yearDiff > 24 && yearDiff < 66){
          state = true;
        } else {
          state = false;
          if(errorMessage != ""){
            errorMessage += "\n";
          }
          errorMessage += "Invalid age! You can't be " + yearDiff + " years old!";
        }

        return state;
      }

      $scope.registerDoctor = function(){
        if (validity()){
          doctorFactory.registerDoctor($scope.doctor, $scope.initComponents);
       //   $scope.initComponents();
        } else {
          modalFactory.setContents(errorMessage);
        }
      };
      
      $scope.initComponents = function(){
    	  $scope.doctor.firstname = "Max";
    	  $scope.doctor.lastname = "Max";
    	  $scope.doctor.specialization = "";
    	  $scope.doctor.address = "jdadsd";
    	  $scope.doctor.number = "08987894";
    	  $scope.doctor.birthday = "08/12/1992";
    	  $scope.doctor.username = "smds";
    	  $scope.doctor.password = "asd";
    	  $scope.doctor.passwordAgain = "asd";
    	  $scope.doctor.email = "maxtimeout@gmail.com";
      };

    }
  );