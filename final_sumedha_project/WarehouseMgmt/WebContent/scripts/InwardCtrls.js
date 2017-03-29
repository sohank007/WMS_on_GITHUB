/*WMS.controller("AddInwardCtrl",['$scope','$http','$window','AllFactory','$mdDialog',
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
	var orgId= $window.localStorage['orgId'];

	
	$http.get('/WarehouseMgmt/warehouse/warehouseByOrgId/'+orgId)
    .then(function(response){
    	$scope.warehouses=response.data; 
    }); 

	$http.get('/WarehouseMgmt/material/materialByOrgId/'+orgId)
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

	


	//----------------------- ng-change for warehouse select method----------//
	$scope.sendW=function(warehouseId){
		$scope.dUWare=false;
		$scope.disableW=true;
	}

	var all={};
	// --------------------- ng-change method of material select -----------//
	$scope.addM=function(all){
		$scope.sampleMaterial=all;
		$scope.mNotSelected=false;
		$scope.inwardDetails.materialID=all.materialID;
		$scope.inwardDetails.materialName=all.materialName;
		$scope.inwardDetails.unitOfMeasure=all.unitOfMeasure;
		$scope.inwardDetails.unitPrice=all.unitPrice;

	}

	//----------------------- add material to inward list ------------------//
	$scope.addMaterial=function(){
		$scope.whenAddMaterialClick=true;
		$scope.inwDetails.push($scope.inwardDetails);

		var mIndex=$scope.materials.indexOf($scope.sampleMaterial);
		$scope.materials.splice(mIndex,1);
		

			//ng-model for material select 
			$scope.mNotSelected=true;
			$scope.inwardDetails={};
			$scope.all={};
		
	}
	
	
	
	// --------------------- submit all materials ------------------------//
	$scope.submit=function(){

		$scope.inward.inDate=new Date();
		$scope.inward.organisationId=orgId;
		$scope.all={'inward':$scope.inward,'inwardDetails':{"materialList":$scope.inwDetails}};
		$scope.NewAll = JSON.stringify($scope.all);
		console.log($scope.NewAll);
		debugger;

		$http({
	        method  : 'POST',
	        url     : '/WarehouseMgmt/inward/add_inward',
	        data    : $scope.NewAll,
	        headers : {'Content-Type': 'application/json; charset=utf-8'}
	        
	       }).then(function(response){
	    	   $mdDialog.show({
        		   template: "<md-dialog id='alertAddMaterial'>" +   
        		  
        		   " <div style='margin: 10px 10px 0px;'>" +"<label style='font-weight:100'>Your inward with id " 
        		   +response.data +" has been inserted successfully.</label> </div>"+
        		   "<div style='margin: 10px 10px;border-radius:4px;'" +
        		   " layout='row' layout-align='center center'>" +
        		   "    <md-button class='md-raised' ng-click='closeDialog()' >" +
        		   '      Ok' +
        		   '    </md-button>' +
        		   '  </div>' +
        		   '</md-dialog>',
        		   controller: function($scope, $mdDialog,$window){
        		      $scope.closeDialog = function(){
        		       $mdDialog.hide();
        		       $window.location.reload();
        		     };
        		   },
        		  
        		});
	       });
		
	}
		
	
}]);*/

WMS.controller("AddInwardCtrl",['$scope','$http','$window','AllFactory','$mdDialog',
                                function($scope,$http,$window,AllFactory,$mdDialog){
/*	debugger;*/
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
	var orgId=$window.localStorage['orgId'];
	var category = AllFactory.getCategory();
	
	$http.get('/WarehouseMgmt/warehouse/warehouseByOrgId/'+orgId)
    .then(function(response){
    	$scope.warehouses=response.data; 
    }); 
	
	$http.get('/WarehouseMgmt/category/categoryByOrgId/'+orgId)
    .then(function(response){
    	$scope.categories=response.data; 
    }); 

	$http.get("/WarehouseMgmt/inventory/inventoryDetailsJson").then(function(response){
		$scope.inventory=response.data;
	});	
	
	$scope.cancel=function(){
		$mdDialog.cancel();
	}
	//----------------------- show div for adding material--------------------//

	


	//----------------------- ng-change for warehouse select method----------//
	$scope.sendW=function(warehouseObj){
		$scope.dUWare=false;
		$scope.disableW=true;
		$scope.inward.warehouseId=warehouseObj.warehouseId;
	}
	
	/*var allCat={};*/
	//----------------------- ng-change for category select method----------//
	$scope.addCat=function(CatObj){
/*		debugger;*/
		AllFactory.setCategory(CatObj);
		$http.get('/WarehouseMgmt/material/materialByCategoryId/'+CatObj.categoryId)
	    .then(function(response){
	    	$scope.materials=response.data; 
	    });
		
	}

	
	// --------------------- ng-change method of material select -----------//
	$scope.addM=function(all){
		debugger;
		if(all!==null){
		$scope.sampleMaterial=all;
		$scope.mNotSelected=false;
		$scope.inwardDetails.materialID=all.materialID;
		$scope.inwardDetails.materialName=all.materialName;
		$scope.inwardDetails.unitOfMeasure=all.unitOfMeasure;
		$scope.inwardDetails.unitPrice=all.unitPrice;
		$scope.inwardDetails.materialCode = all.materialCode;
	}
	}

	//----------------------- add material to inward list ------------------//
	$scope.addMaterial=function(){
		debugger;
		$scope.whenAddMaterialClick=true;
		$scope.inwDetails.push($scope.inwardDetails);

		var mIndex=$scope.materials.indexOf($scope.sampleMaterial);
		$scope.materials.splice(mIndex,1);
		

			//ng-model for material select 
			$scope.mNotSelected=true;
			$scope.inwardDetails={};
			/*$scope.all={};*/
			$scope.materialName=null;
			
		
	}
	
	
	
	// --------------------- submit all materials ------------------------//
	$scope.submit=function(){
		debugger;
	/*	$scope.inward.inDate=new Date();*/
		$scope.inward.organisationId=orgId;
		$scope.all={'inward':$scope.inward,'inwardDetails':{"materialList":$scope.inwDetails}};
		$scope.NewAll = JSON.stringify($scope.all);
		console.log($scope.NewAll);
/*		debugger;*/

		$http({
	        method  : 'POST',
	        url     : '/WarehouseMgmt/inward/add_inward',
	        data    : $scope.NewAll,
	        headers : {'Content-Type': 'application/json; charset=utf-8'}
	        
	       }).then(function(response){
	    	   $mdDialog.show({
        		   template: "<md-dialog id='alertAddMaterial'>" +   
        		  
        		   " <div style='margin: 10px 10px 0px;'>" +"<label style='font-weight:100'>Your inward with id " 
        		   +response.data +" has been inserted successfully.</label> </div>"+
        		   "<div style='margin: 10px 10px;border-radius:4px;'" +
        		   " layout='row' layout-align='center center'>" +
        		   "    <md-button class='md-raised' ng-click='closeDialog()' >" +
        		   '      Ok' +
        		   '    </md-button>' +
        		   '  </div>' +
        		   '</md-dialog>',
        		   controller: function($scope, $mdDialog,$window){
        		      $scope.closeDialog = function(){
        		       $mdDialog.hide();
        		       $window.location.reload();
        		     };
        		   },
        		  
        		});
	       });
		
	}
		
	
}]);

WMS.controller("InwardCtrl",['$scope','$http','$window','AllFactory','$mdDialog', 
                             function($scope,$http,$window,AllFactory,$mdDialog) {
		var orgId= $window.localStorage['orgId'];
		$http.get('/WarehouseMgmt/inward/inwardByOrgId/'+orgId).then(function(response){
			$scope.inwards=response.data;
		})
		
		$http.get('/WarehouseMgmt/warehouse/warehouseByOrgId/'+orgId)
			.then(function(response) {
					$scope.warehouses = response.data;
			});
		
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
	
	$scope.sorting=true;
	vari="inId";
	$scope.reverse=true;
	$scope.sortingMaterial=function(vari){
		$scope.variable=vari;
		if($scope.sorting==true){//true for up arrow
			$scope.sorting=false;
			$scope.reverse=true;

		}
		else{//false for down arrow
			$scope.sorting=true;
			$scope.reverse=false;
		}
	}
	
}]);

WMS.controller("InwardDetailsCtrl",['$scope','$http','$window','AllFactory', function($scope,$http,$window,AllFactory) {
				$scope.inwardDetail=[];
				$scope.inwardId=AllFactory.getInward();
				var orgId= $window.localStorage['orgId'];
				$http.get('/WarehouseMgmt/inward/inwardDetailsJson')
				.then(function(response){
					$scope.inwardDetails=response.data;
					for(var i=0;i<$scope.inwardDetails.length;i++){

						if($scope.inwardId==$scope.inwardDetails[i].inward.inId)
							{
							$scope.inwardDetail.push($scope.inwardDetails[i]);
							}
					}

					   $http
						.get(
								'/WarehouseMgmt/category/categoryByOrgId/' + orgId)
						.then(function(response) {
							$scope.categories = response.data;
							console.log($scope.categories);
					});
					   
						$http.get('/WarehouseMgmt/material/materialByOrgId/'+orgId)
				        .then(function successCallback(response){
				        $scope.material_details=response.data; 
				        });
					
				});
		
				
}])

