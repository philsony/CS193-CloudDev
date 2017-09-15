angular.module('hplus.modules.modal', [])
	.controller('modalCtrl',['SweetAlert', function(SweetAlert){
		var vm = this;
		
		vm.alert = function(){
			SweetAlert.swal("Record Updated!"); //simple alert
		}
		
		vm.confirm = function(){
			
			SweetAlert.swal({
				title: "Are you sure?", //Bold text
				text: "Your will not be able to recover this record !", //light text
				type: "warning", //type -- adds appropiriate icon
				showCancelButton: true, // displays cancel btton
				confirmButtonColor: "#254d5e",
				confirmButtonText: "Yes, delete it!",
				closeOnConfirm: false, //do not close popup after click on confirm, usefull when you want to display a subsequent popup
				closeOnCancel: false
			}, 
			function(isConfirm){ //Function that triggers on user action.
				if(isConfirm){
					SweetAlert.swal("Record Deleted!");
				} else {
					SweetAlert.swal("Your file is safe!");
				}
			});
		}
	}]);