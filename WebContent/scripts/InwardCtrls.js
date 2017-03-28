var WMS=angular.module("WMS");
WMS.controller("AddInwardCtrl",['$scope','$http','$window','AllFactory','$mdDialog',
                                function($scope,$http,$window,AllFactory,$mdDialog){
					//to show div which contains code for add material to inward 
	$scope.inwDetails=[];
	$scope.mtrls=[];
	$scope.inward={};
	$scope.inwardDetails={};
	$scope.disableW=false;
	$scope.dUWare=true;
	$scope.mNotSelected=true;
	$scope.whenAddMaterialClick=false;
	$scope.inward.inDate=new Date();
	

	
	$http.get('/WarehouseMgmt/warehouse/warehouseDetailsJson')
    .then(function(response){
    	$scope.warehouses=response.data; 
    }); 

	$http.get('/WarehouseMgmt/material/materialDetailsJson')
    .then(function(response){
    	$scope.materials=response.data; 
    });

	$http.get("/WarehouseMgmt/inventory/inventoryDetailsJson").then(function(response){
		$scope.inventory=response.data;
	});	
	
	$scope.cancel=function(){
		$mdDialog.cancel();
	}
	//----------------------- show div for adding material--------------------//

	
	//----------------------- add material to inward list ------------------//
	$scope.addMaterial=function(){
		$scope.whenAddMaterialClick=true;
		$scope.inwDetails.push($scope.inwardDetails);

		
		

			//ng-model for material select 
			$scope.mNotSelected=true;
			$scope.inwardDetails={};
			$scope.all={};
		
	}

	//----------------------- ng-change for warehouse select method----------//
	$scope.sendW=function(warehouseId){
		$scope.dUWare=false;
		$scope.disableW=true;
	}

	var all={};
	// --------------------- ng-change method of material select -----------//
	$scope.addM=function(all){
		$scope.mNotSelected=false;
		$scope.inwardDetails.materialID=all.materialID;
		$scope.inwardDetails.materialName=all.materialName;
		$scope.inwardDetails.unitOfMeasure=all.unitOfMeasure;
	}

	
	// --------------------- submit all materials ------------------------//
	$scope.submit=function(){

		$scope.inward.inDate=new Date();
		$scope.all={'inward':$scope.inward,'inwardDetails':{"materialList":$scope.inwDetails}};
		$scope.NewAll = JSON.stringify($scope.all);
		console.log($scope.NewAll);
/*		debugger;*/

		$http({
	        method  : 'POST',
	        url     : '/WarehouseMgmt/inward/add_inward',
	        data    : $scope.NewAll,
	        headers : {'Content-Type': 'application/json; charset=utf-8'}
	        
	       }).then(function(){

	            $window.location.reload();
	       });
		
	}
		
	
}]);

WMS.controller("InwardCtrl",['$scope','$http','$window','AllFactory','$mdDialog', 
                             function($scope,$http,$window,AllFactory,$mdDialog) {
		$http.get('/WarehouseMgmt/inward/viewInwardJson').then(function(response){
			$scope.inwards=response.data;
		})
		$scope.genInward=function(ev){
			$mdDialog.show({
	    		 targetEvent: ev,
	    		 templateUrl: '/WarehouseMgmt/pages/addInward.jsp',
	    		 controller:'AddInwardCtrl',
	    		 parent: angular.element(document.body)
	    	});
		}
	$scope.inwardDet=function(ev,id){
			AllFactory.setInward(id);
			$mdDialog.show({
	    		 targetEvent: ev,
	    		 templateUrl: '/WarehouseMgmt/pages/inwardDetails.jsp',
	    		 controller:'InwardDetailsCtrl',
	    		 parent: angular.element(document.body),
	    		 clickOutsideToClose:true,
	    	});

		}
	
	
}]);

WMS.controller("InwardDetailsCtrl",['$scope','$http','$window','AllFactory', function($scope,$http,$window,AllFactory) {
				$scope.inwardDetail=[];
				$scope.inwardId=AllFactory.getInward();
				$http.get('/WarehouseMgmt/inward/inwardDetailsJson')
				.then(function(response){
					$scope.inwardDetails=response.data;
					for(var i=0;i<$scope.inwardDetails.length;i++){

						if($scope.inwardId==$scope.inwardDetails[i].inward.inId)
							{
							$scope.inwardDetail.push($scope.inwardDetails[i]);
							}
					}

				});
		
				
}])

