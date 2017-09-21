angular.module('hplus.modules.navbar')

  .controller('NavbarController',
    function($scope, $location, globalFactory, doctorFactory){

      $scope.user = doctorFactory.getUser();

      $scope.registerButton = "Register Disease";
      $scope.link = "#!/admin/register/disease";

      $scope.$on("changeNavbar", function(event, user){
        $scope.user = user;
      });

      $scope.$on("changeNavbarOut", function(event){
        $scope.user = null;
      });

      $scope.checkPage = function(){
        var state = false;
        var location = $location.path();

        if(location.indexOf("register") != -1){
          state = false;
        } else if(location.indexOf("doctor") != -1 && location != "/admin/view/doctordetails") {
          $scope.registerButton = "Register Doctor";
          $scope.link = "#!/admin/register/doctor";
        }

        return state;
      }

      $scope.selected = function(page){
        var currLoc = $location.path();
        var btnClass = "navbar__btn";

        if(currLoc.indexOf(page) != -1 && currLoc != "/admin/view/doctordetails"){
          btnClass += " navbar__btn--selected";
        }

        return btnClass;
      }

      $scope.go = function(path){
        globalFactory.go(path);
      };
    }
  );