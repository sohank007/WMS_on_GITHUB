WMS.controller("AddInwardCtrl",['$scope','$http','$window','AllFactory','$mdDialog',
                                function($scope,$http,$window,AllFactory,$mdDialog){
	debugger;
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
	var orgId=AllFactory.getOrganisationId();
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
	$scope.sendW=function(warehouseId){
		$scope.dUWare=false;
		$scope.disableW=true;
	}
	
	/*var allCat={};*/
	//----------------------- ng-change for category select method----------//
	$scope.addCat=function(CatId){
		debugger;
		AllFactory.setCategory(CatId);
		$http.get('/WarehouseMgmt/material/materialByCategoryId/'+CatId)
	    .then(function(response){
	    	$scope.materials=response.data; 
	    });
		
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
		$scope.inwardDetails.materialCode = all.materialCode;

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
	$scope.organizations=["ORG-11","ORG-12","ORG-13","ORG-14"];
	$scope.roles=["CTR","ADM","WHM"];
	$scope.setOrg=function(id){
		AllFactory.setOrganisationId(id);
		
		/*console.log(AllFactory.getOrganisationId());
		console.log(AllFactory.getRole());*/
		var orgId=AllFactory.getOrganisationId();
		var role=AllFactory.getRole();
		$http.get('/WarehouseMgmt/inward/inwardByOrgId/'+orgId).then(function(response){
			$scope.inwards=response.data;
		})
	};
	
	$scope.setRole=function(role){
		AllFactory.setRole(role);
		console.log(AllFactory.getRole());
	}
	var orgId=AllFactory.getOrganisationId();
	$http.get('/WarehouseMgmt/inward/inwardByOrgId/'+orgId).then(function(response){
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

